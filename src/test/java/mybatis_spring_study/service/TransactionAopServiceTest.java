package mybatis_spring_study.service;

import static org.junit.Assert.*;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.junit.After;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import mybatis_spring_study.dto.Department;
import mybatis_spring_study.dto.Employee;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/context-root.xml" })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TransactionAopServiceTest {
	private static final Log log = LogFactory.getLog(TransactionAopServiceTest.class);

	@After // 테스트 한 번 후 줄띄움
	public void tearDown() throws Exception {
		System.out.println();
	}

	@Autowired
	private TransactionAopService service;

	@Test(expected = DuplicateKeyException.class)
	public void testATrRegister_Fail_Department() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Department department= new Department(1, "화장실청소", 10); 		// DuplicateKeyException
		Employee employee= new Employee(1007, "깨끗이", "부장", new Employee(4377), 4100000, department);
		
		service.trRegister(department, employee);
	}

	@Test(expected = DuplicateKeyException.class)
	public void testBTrRegister_Fail_Employee() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Department department= new Department(7, "화장실청소", 10); 		// DuplicateKeyException
		Employee employee= new Employee(4377, "깨끗이", "부장", new Employee(4377), 4100000, department);
		
		service.trRegister(department, employee);
	}

	@Test
	public void testCTrRegister_Success() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Department department= new Department(7, "화장실청소", 99); 		// DuplicateKeyException
		Employee employee= new Employee(7777, "깨끗이", "부장", new Employee(4377), 9500000, department);
		
		service.trRegister(department, employee);
	}

	@Test(expected = RuntimeException.class)
	public void testDTrUnRegister_Fail_Department() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Department department= new Department(100); 		
		Employee employee= new Employee(7777);
		
		service.trUnRegister(department, employee);
	}

	@Test(expected = RuntimeException.class)
	public void testETrUnRegister_Fail_Employee() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Department department= new Department(7); 		
		Employee employee= new Employee(9999);
		
		service.trUnRegister(department, employee);
	}

	@Test
	public void testFTrUnRegister_Success() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Department department= new Department(7); 		// 삭제완료
		Employee employee= new Employee(7777);			// 삭제완료
		
		service.trUnRegister(department, employee);
	}

}

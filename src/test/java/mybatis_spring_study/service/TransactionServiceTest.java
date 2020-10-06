package mybatis_spring_study.service;

import static org.junit.Assert.fail;

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
@ContextConfiguration(locations = {"classpath:/context-root.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TransactionServiceTest {
	private static final Log log = LogFactory.getLog(EmployeeServiceTest.class);
	
	@After		// 메서드 한개 테스트 후 줄띄움
	public void tearDown() throws Exception {
		System.out.println();
	}

	@Autowired
	private TransactionService service;

	@Test(expected = DuplicateKeyException.class)
	public void testARegisterTransaction_Dept_Fail() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Department department= new Department(1, "태스크포스", 10); // DuplicateKeyException
		Employee employee= new Employee(1004, "박신혜", "과장", new Employee(4377), 4100000, department);
		
		service.registerTransaction(department, employee);
	}
	
	@Test(expected = DuplicateKeyException.class)
	public void testBRegisterTransaction_Emp_Fail() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Department department= new Department(6, "태스크포스", 10);
		Employee employee= new Employee(4377, "박신혜", "과장", new Employee(4377), 4100000, department);
		
		service.registerTransaction(department, employee);
	}
	
	@Test
	public void testCRegisterTransaction_Success() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Department department= new Department(6, "태스크포스", 10);
		Employee employee= new Employee(1006, "박신혜", "과장", new Employee(4377), 4100000, department);
		
		service.registerTransaction(department, employee);
	}

	@Test(expected = RuntimeException.class)
	public void testDUnRegisterTransaction() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Department department= new Department(100); // RuntimeException -> rollback
		Employee employee= new Employee(1006); // rollback 되므로삭제되면안됨
		
		service.unRegisterTransaction(department, employee);
	}
	
	@Test(expected = RuntimeException.class)
	public void testEUnRegisterTransaction() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Department department= new Department(6); // 정상삭제
		Employee employee= new Employee(9999); // runtimeException -> 삭제안됨
		
		service.unRegisterTransaction(department, employee);
	}
	
	@Test
	public void testFUnRegisterTransaction() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Department department= new Department(6); // 정상삭제
		Employee employee= new Employee(1006); // runtimeException -> 삭제안됨
		
		service.unRegisterTransaction(department, employee);
	}

}
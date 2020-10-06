package mybatis_spring_study.service;

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

import mybatis_spring_study.config.ContextRoot;
import mybatis_spring_study.dto.Department;
import mybatis_spring_study.dto.Employee;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ContextRoot.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TransactionServiceTest {
	protected static final Log log = LogFactory.getLog(TransactionServiceTest.class);

	@After		// 결과화면에서 테스트 하나 실행 후 한줄 띄워서 다른 테스트 실행하기 위해서
	public void tearDown() throws Exception {
		System.out.println();
	}

	@Autowired
	private TransactionService service;

	@Test(expected = DuplicateKeyException.class)
	public void testARegisterTransaction_Fail_Dept() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Department department = new Department(1, "태스크포스", 10);			// 무결성제약조건
		Employee employee = new Employee(1005, "박신혜", "과장", new Employee(4377), 5000000, department);
		
		service.registerTransaction(department, employee);
	}
	
	@Test(expected = DuplicateKeyException.class)
	public void testBRegisterTransaction_Fail_Emp() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Department department = new Department(6, "태스크포스", 10);			
		Employee employee = new Employee(4377, "박신혜", "과장", new Employee(4377), 5000000, department);	// 무결성제약조건
		
		service.registerTransaction(department, employee);
	}
	
	@Test
	public void testCRegisterTransaction_Success() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Department department = new Department(6, "태스크포스", 10);
		Employee employee = new Employee(1006, "박신혜", "과장", new Employee(4377), 5000000, department);
		
		service.registerTransaction(department, employee);
	}

	@Test(expected = RuntimeException.class)
	public void testDUnRegisterTransaction_Fail_Dept() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Department department = new Department(100);	// runtimeException => rollback
		Employee employee = new Employee(1006);
		
		service.unRegisterTransaction(department, employee);
	}
	
	@Test(expected = RuntimeException.class)
	public void testEUnRegisterTransaction_Fail_Emp() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Department department = new Department(6);
		Employee employee = new Employee(9999);			// runtimeException => rollback
		
		service.unRegisterTransaction(department, employee);
	}
	
	@Test
	public void testFUnRegisterTransaction_Success() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Department department = new Department(6);
		Employee employee = new Employee(1006);
		
		service.unRegisterTransaction(department, employee);
	}

}

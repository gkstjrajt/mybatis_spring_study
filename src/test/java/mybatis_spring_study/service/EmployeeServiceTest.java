package mybatis_spring_study.service;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import mybatis_spring_study.dto.Department;
import mybatis_spring_study.dto.Employee;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/context-root.xml"})
public class EmployeeServiceTest {
	private static final Log log = LogFactory.getLog(EmployeeServiceTest.class);

	@After
	public void tearDown() throws Exception {
		System.out.println();
	}

	@Autowired
	private EmployeeService service;

	@Test
	public void testAddEmployee() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Employee employee = new Employee(1005, "박규영", "과장", new Employee(4377), 4100000, new Department(1));
		int res = service.addEmployee(employee);
		Assert.assertEquals(1, res);
	}

	@Test
	public void testDelEmployee() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		int res = service.delEmployee(new Employee(1005));
		Assert.assertEquals(1, res);
	}

}

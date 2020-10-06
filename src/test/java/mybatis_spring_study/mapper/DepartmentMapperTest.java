package mybatis_spring_study.mapper;

import java.util.List;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import mybatis_spring_study.config.ContextRoot;
import mybatis_spring_study.dto.Department;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ContextRoot.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DepartmentMapperTest {
	protected static final Log log = LogFactory.getLog(DepartmentMapperTest.class);

	@After					// 결과화면에서 테스트 하나 실행 후 한줄 띄워서 다른 테스트 실행하기 위해서
	public void tearDown() throws Exception {
		System.out.println();
	}

	@Autowired
	private DepartmentMapper mapper;

	@Test
	public void test01SelectDepartmentByAll() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		List<Department> list = mapper.selectDepartmentByAll();
		Assert.assertNotNull(list);
		list.stream().forEach(System.out::println);
	}

	@Test
	public void test02InsertDepartment() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Department department = new Department(5, "마케팅", 12);
		int res = mapper.insertDepartment(department);
		Assert.assertEquals(1, res);
	}

	@Test
	public void test03UpdateDepartment() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Department department = new Department(5, "마케팅", 30);
		int res = mapper.updateDepartment(department);
		Assert.assertEquals(1, res);
	}

	@Test
	public void test04SelectDepartmentByNo() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Department department = mapper.selectDepartmentByNo(new Department(5));
		Assert.assertNotNull(department);
		System.out.println(department);
	}

	@Test
	public void test05DeleteDepartment() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Department department = new Department(5);
		int res = mapper.deleteDepartment(department);
		Assert.assertEquals(1, res);
	}

}

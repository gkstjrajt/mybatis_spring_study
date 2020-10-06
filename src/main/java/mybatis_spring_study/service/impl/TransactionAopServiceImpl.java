package mybatis_spring_study.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mybatis_spring_study.dto.Department;
import mybatis_spring_study.dto.Employee;
import mybatis_spring_study.mapper.DepartmentMapper;
import mybatis_spring_study.mapper.EmployeeMapper;
import mybatis_spring_study.service.TransactionAopService;

@Service		
public class TransactionAopServiceImpl implements TransactionAopService {
	
	@Autowired
	private DepartmentMapper deptMapper;
	
	@Autowired
	private EmployeeMapper empMapper;

	@Override
	public void trRegister(Department department, Employee employee) {
		// 부서가 등록되고 난 후 해당 부서에 사원을 추가
		deptMapper.insertDepartment(department);
		empMapper.insertEmployee(employee);
	}

	@Override
	public void trUnRegister(Department department, Employee employee) {
		// 사원 삭제 후 부서 삭제		삭제 실패해도 0을 리턴해서 성공한거처럼 되니까 res로 리턴값 받아서 0이 리턴되면 실패되게 함
		int res = empMapper.deleteEmployee(employee);
		res += deptMapper.deleteDepartment(department);
		if(res != 2) {
			throw new RuntimeException();
		}
	}

}

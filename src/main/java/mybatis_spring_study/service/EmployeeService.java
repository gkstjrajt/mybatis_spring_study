package mybatis_spring_study.service;

import mybatis_spring_study.dto.Employee;

public interface EmployeeService {
	int addEmployee(Employee employee);
	int delEmployee(Employee employee);
}

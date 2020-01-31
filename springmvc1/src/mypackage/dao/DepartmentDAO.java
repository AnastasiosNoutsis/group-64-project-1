package mypackage.dao;

import java.util.List;

import mypackage.entity.Department;

public interface DepartmentDAO {

	public List<Department> getDepartments();

	public void saveDepartment(Department department);

	public Department getDepartment(int dep_id);

	public void deleteDepartment(int dep_id);

}

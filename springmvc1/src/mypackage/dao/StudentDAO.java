package mypackage.dao;

import java.util.List;

import mypackage.entity.Student;

public interface StudentDAO {
	
	public List<Student> getStudents();
	
	public void saveStudent(Student student);

	public Student getStudent(String username);

	public void deleteStudent(String username);
	
	

}

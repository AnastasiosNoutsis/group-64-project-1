package mypackage.dao;

import java.util.List;

import mypackage.entity.StudentApplication;

public interface StudentApplicationDAO {

	public List<StudentApplication> getStudentApplications();
	
	public void saveStudentApplication(StudentApplication studentApp);

	public StudentApplication getStudentApplication(int app_id);

	public void deleteStudentApplication(int app_id);

}

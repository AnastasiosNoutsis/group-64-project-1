package mypackage.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mypackage.entity.StudentApplication;

@Repository
public class StudentApplicationDAOImpl implements StudentApplicationDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public List<StudentApplication> getStudentApplications() {
		Session currentSession = sessionFactory.getCurrentSession();

		// Create my query
		Query<StudentApplication> query = currentSession.createQuery("from StudentApplication", StudentApplication.class);

		// Execute the query and get the results in a list
		List<StudentApplication> studentApplications = query.getResultList();

		// return the list of users' applications
		return studentApplications;
	}

	
	@Override
	@Transactional
	public void saveStudentApplication(StudentApplication studentApp) {
		Session currentSession = sessionFactory.getCurrentSession();

//		System.out.println("USERDAOIMPL.saveUser(User user) --->");
//		System.out.println("username = " + user.getUsername());
//		System.out.println("password= " + user.getPassword());
//		System.out.println("enabled = " + user.getEnabled());
//		System.out.println("roles = " + user.getRole());

		currentSession.saveOrUpdate(studentApp);
	}
	

	@Override	
	@Transactional
	public StudentApplication getStudentApplication(int app_id) {
		Session currentSession = sessionFactory.getCurrentSession();

		// get and return User application
		StudentApplication studentApplication = currentSession.get(StudentApplication.class, app_id);
		return studentApplication;
	}
	


	@Override
	@Transactional
	public void deleteStudentApplication(int app_id) {
		Session currentSession = sessionFactory.getCurrentSession();

		// find the Student's application
		StudentApplication studentApplication = currentSession.get(StudentApplication.class, app_id);

		// delete Student's application
		currentSession.delete(studentApplication);
	}



}

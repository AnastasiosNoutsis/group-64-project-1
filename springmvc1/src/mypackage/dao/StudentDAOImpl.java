package mypackage.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mypackage.entity.Student;

@Repository
public class StudentDAOImpl implements StudentDAO {

	@Autowired
	private SessionFactory sessionFactory;
	

	@Override
	@Transactional
	public List<Student> getStudents() {
		Session currentSession = sessionFactory.getCurrentSession();

		// Create my query
		Query<Student> query = currentSession.createQuery("from Student", Student.class);

		// Execute the query and get the results in a list
		List<Student> students = query.getResultList();

		// return the list of users
		return students;
	}

	@Override
	@Transactional
	public void saveStudent(Student student) {
		Session currentSession = sessionFactory.getCurrentSession();
		
//		System.out.println("USERDAOIMPL.saveUser(User user) --->");
//		System.out.println("username = " + user.getUsername());
//		System.out.println("password= " + user.getPassword());
//		System.out.println("enabled = " + user.getEnabled());
//		System.out.println("roles = " + user.getRole());
		
		currentSession.saveOrUpdate(student);

	}

	@Override
	@Transactional
	public Student getStudent(String username) {
		Session currentSession = sessionFactory.getCurrentSession();

		// get and return User
		Student student = currentSession.get(Student.class, username);
		return student;
	}

	@Override
	@Transactional
	public void deleteStudent(String username) {
		Session currentSession = sessionFactory.getCurrentSession();

		// find the Student
		Student student = currentSession.get(Student.class, username);

		// delete Student
		currentSession.delete(student);
	}

}

package mypackage.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mypackage.entity.Employee;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public List<Employee> getEmployees() {
		Session currentSession = sessionFactory.getCurrentSession();

		// Create my query
		Query<Employee> query = currentSession.createQuery("from Employee", Employee.class);

		// Execute the query and get the results in a list
		List<Employee> employees = query.getResultList();

		// return the list of employers
		return employees;
	}

	@Override
	@Transactional
	public void saveEmployee(Employee employee) {
		Session currentSession = sessionFactory.getCurrentSession();

//		System.out.println("EMPLOYERDAOIMPL.saveEmployer(Employer employer) --->");
//		System.out.println("authority = " + employer.getDescription());
//		System.out.println("description = " + employer.getDescription());

		currentSession.saveOrUpdate(employee);
	}

	@Override
	@Transactional
	public Employee getEmployee(String username) {
		Session currentSession = sessionFactory.getCurrentSession();

		// get and return object
		Employee employee = currentSession.get(Employee.class, username);
		return employee;
	}

	@Override
	@Transactional
	public void deleteEmployee(String username) {
		Session currentSession = sessionFactory.getCurrentSession();

		// find the object
		Employee employee = currentSession.get(Employee.class, username);

		// delete object
		currentSession.delete(employee);
	}

}

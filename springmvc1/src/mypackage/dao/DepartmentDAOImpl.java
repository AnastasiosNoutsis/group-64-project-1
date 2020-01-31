package mypackage.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mypackage.entity.Department;

@Repository
public class DepartmentDAOImpl implements DepartmentDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public List<Department> getDepartments() {
		Session currentSession = sessionFactory.getCurrentSession();

		// Create my query
		Query<Department> query = currentSession.createQuery("from Department", Department.class);

		// Execute the query and get the results in a list
		List<Department> departments = query.getResultList();

		// return the list of departments 
		return departments;
	}

	@Override
	@Transactional
	public void saveDepartment(Department department) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(department);
	}

	@Override
	@Transactional
	public Department getDepartment(int dep_id) {
		Session currentSession = sessionFactory.getCurrentSession();
		Department department = currentSession.get(Department.class, dep_id);
		return department;
	}

	@Override
	@Transactional
	public void deleteDepartment(int dep_id) {
		Session currentSession = sessionFactory.getCurrentSession();
		Department department = currentSession.get(Department.class, dep_id);
		currentSession.delete(department);
	}

}

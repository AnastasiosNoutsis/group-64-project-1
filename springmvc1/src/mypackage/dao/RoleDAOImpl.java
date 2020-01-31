package mypackage.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mypackage.entity.Role;

@Repository
public class RoleDAOImpl implements RoleDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public List<Role> getRoles() {
		Session currentSession = sessionFactory.getCurrentSession();

		// Create my query
		Query<Role> query = currentSession.createQuery("from Role", Role.class);

		// Execute the query and get the results in a list
		List<Role> roles = query.getResultList();

		// return the list of roles
		return roles;
	}

	@Override
	@Transactional
	public void saveRole(Role role) {
		Session currentSession = sessionFactory.getCurrentSession();

//		System.out.println("ROLEDAOIMPL.saveRole(Role role) --->");
//		System.out.println("authority = " + role.getAuthority());
//		System.out.println("description = " + role.getDescription());

		currentSession.saveOrUpdate(role);
	
	}

	@Override
	@Transactional
	public Role getRole(String authority) {
		Session currentSession = sessionFactory.getCurrentSession();

		// get and return object
		Role role = currentSession.get(Role.class, authority);
		return role;
	}

	@Override
	@Transactional
	public void deleteRole(String authority) {
		Session currentSession = sessionFactory.getCurrentSession();
		
		// find the object
		Role role= currentSession.get(Role.class, authority);

		// delete object
		currentSession.delete(role);
	}

}

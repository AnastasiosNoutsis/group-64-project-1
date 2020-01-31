package mypackage.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mypackage.entity.User;

@Repository
public class UserDAOImpl implements UserDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public List<User> getUsers() {
		Session currentSession = sessionFactory.getCurrentSession();

		// Create my query
		Query<User> query = currentSession.createQuery("from User", User.class);

		// Execute the query and get the results in a list
		List<User> users = query.getResultList();

		// return the list of users
		return users;
	}

	@Override
	@Transactional
	public void saveUser(User user) {
		Session currentSession = sessionFactory.getCurrentSession();
		
//		System.out.println("USERDAOIMPL.saveUser(User user) --->");
//		System.out.println("username = " + user.getUsername());
//		System.out.println("password= " + user.getPassword());
//		System.out.println("enabled = " + user.getEnabled());
//		System.out.println("roles = " + user.getRole());
		
		currentSession.saveOrUpdate(user);
	}

	@Override
	@Transactional
	public User getUser(String username) {
		Session currentSession = sessionFactory.getCurrentSession();

		// get and return User
		User user = currentSession.get(User.class, username);
		return user;
	}

	@Override
	@Transactional
	public void deleteUser(String username) {
		Session currentSession = sessionFactory.getCurrentSession();

		// find the User
		User user = currentSession.get(User.class, username);

		// delete User
		currentSession.delete(user);
	}

}

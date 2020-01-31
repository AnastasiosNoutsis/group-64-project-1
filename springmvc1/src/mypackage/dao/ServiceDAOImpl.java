package mypackage.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mypackage.entity.Service;

@Repository
public class ServiceDAOImpl implements ServiceDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public List<Service> getServices() {
		Session currentSession = sessionFactory.getCurrentSession();

		// Create my query
		Query<Service> query = currentSession.createQuery("from Service", Service.class);

		// Execute the query and get the results in a list
		List<Service> services = query.getResultList();

		// return the list of services
		return services;
	}

	@Override
	@Transactional
	public void saveService(Service service) {
		Session currentSession = sessionFactory.getCurrentSession();

//		System.out.println("<-------------------------------->");
//		System.out.println("FROM ServiceDAOImpl.saveService(Service service): ");
//		System.out.println("service_name: " + service.getService_name());
//		System.out.println("description: " + service.getDescription());
//		System.out.println("roles: " + service.getRoles());
//		System.out.println("<-------------------------------->");
		
		currentSession.saveOrUpdate(service);
	}

	@Override
	@Transactional
	public Service getService(String service_name) {
		Session currentSession = sessionFactory.getCurrentSession();

		// get and return Service
		Service service = currentSession.get(Service.class, service_name);
		return service;
	}

	@Override
	@Transactional
	public void deleteService(String service_name) {
		Session currentSession = sessionFactory.getCurrentSession();
		
		// find the Service
		Service service = currentSession.get(Service.class, service_name);

		// delete service
		currentSession.delete(service);
	}


}

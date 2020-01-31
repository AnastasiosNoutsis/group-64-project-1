package mypackage.dao;

import java.util.List;

import mypackage.entity.Service;

public interface ServiceDAO {

	public List<Service> getServices();

	public void saveService(Service service);

	public Service getService(String service_name);

	public void deleteService(String service_name);
}

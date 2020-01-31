package mypackage.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "role")
public class Role {

	@Id
	@Column(name = "authority")
	private String authority;

	@Column(name = "description")
	private String description;

	@OneToMany(mappedBy = "role", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
			CascadeType.REFRESH })
	private List<User> users;

	@ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, 
            CascadeType.DETACH, CascadeType.REFRESH})
	@JoinTable(name = "role_access_service", 
		joinColumns = @JoinColumn(name = "authority"), 
		inverseJoinColumns = @JoinColumn(name = "service_name"))
	private List<Service> services;

	public Role() {

	}

	public Role(String authority, String description) {
		this.authority = authority;
		this.description = description;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	// add convenience methods for bi-directional relation
	public void add(User user) {
		if (users == null) {
			users = new ArrayList<>();
		}
		users.add(user);
		user.setRole(this);
	}

	public List<Service> getServices() {
		return services;
	}

	public void setServices(List<Service> services) {
		this.services = services;
	}

	// add convenience methods for bi-directional relation
	public void addService(Service service) {
		if (services == null) {
			services = new ArrayList<>();
		}
		services.add(service);
	}

	@Override
	public String toString() {
		return "Role [authority=" + authority + ", description=" + description + "]";
	}

}

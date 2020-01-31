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
import javax.persistence.Table;

@Entity
@Table(name = "service")
public class Service {

	@Id
	@Column(name = "service_name")
	private String service_name;

	@Column(name = "description")
	private String description;

	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
			CascadeType.REFRESH })
	@JoinTable(name = "role_access_service", joinColumns = @JoinColumn(name = "service_name"), inverseJoinColumns = @JoinColumn(name = "authority"))
	private List<Role> roles;

	public Service() {

	}

	public Service(String service_name, String description) {
		this.service_name = service_name;
		this.description = description;
	}
	
	public Service(String service_name, String description, List<Role> roles) {
		this.service_name = service_name;
		this.description = description;
		this.roles = roles;
	}

	public String getService_name() {
		return service_name;
	}

	public void setService_name(String service_name) {
		this.service_name = service_name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	
	// add convenience methods for bi-directional relation
	public void addRole(Role role) {
		if (roles == null) {
			roles = new ArrayList<>();
		}
		
		if (!roles.contains(role)) {
			roles.add(role);
		}

	}

	@Override
	public String toString() {
		return "Service [service_name=" + service_name + ", description=" + description + "]";
	}

}

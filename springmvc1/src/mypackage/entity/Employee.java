package mypackage.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="employee")
@PrimaryKeyJoinColumn(name="username")  
public class Employee extends User {

	@Column(name="description")
	private String description;

//	@OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
//			CascadeType.REFRESH })
//	private List<Department> departments;

	
	public Employee() {

	}

	public Employee(String username, String password, int enabled, String am, String first_name, String last_name,
			String email, String description) {
		super(username, password, enabled, am, first_name, last_name, email);
		this.description = description;
		//this.departments = null;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	

//	public List<Department> getDepartments() {
//		return departments;
//	}
//
//	public void setDepartments(List<Department> departments) {
//		this.departments = departments;
//	}
//	
//	public void addDepartment(Department department) {
//		if (departments == null) {
//			departments = new ArrayList<>();
//		}
//		departments.add(department);
//		department.setEmployerInCharge(this);
//	}
	
	
	
	@Override
	public String toString() {
		return "Employee [description=" + description + "]";
	}
	
	

}

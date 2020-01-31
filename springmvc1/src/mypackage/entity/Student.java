package mypackage.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "student")
@PrimaryKeyJoinColumn(name="username")
public class Student extends User {
	
	@OneToOne(mappedBy="student", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
	private StudentApplication application;

	//fetch = FetchType.EAGER, 
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
	@JoinColumn(name = "dep_id")
	private Department department;

	public Student() {

	}

	public Student(String username, String password, int enabled, String am, String first_name, String last_name,
			String email) {
		super(username, password, enabled, am, first_name, last_name, email);
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
	
	

	public StudentApplication getApplication() {
		return application;
	}

	public void setApplication(StudentApplication application) {
		this.application = application;
	}

	@Override
	public String toString() {
		return "Student [" + super.toString() + ", application=" + application + ", department=" + department + "]";
	}


	
}

package mypackage.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "department")
public class Department {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "dep_name")
	private String dep_name;

	@Column(name = "orio_dorean_sitisis")
	private float orio_dorean_sitisis;
	
	@Column(name = "xoritikotita")
	private int xoritikotita;

	@Column(name = "location")
	private String location;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "department", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
			CascadeType.REFRESH })
	private List<Student> students;

	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
	@JoinColumn(name="supervisor_username")
	private Employee employeeInCharge;

	public Department() {

	}

	public Department(int id, String dep_name, float orio_dorean_sitisis, int xoritikotita, String location) {
		this.id = id;
		this.dep_name = dep_name;
		this.orio_dorean_sitisis = orio_dorean_sitisis;
		this.xoritikotita=xoritikotita;
		this.location = location;
		this.employeeInCharge = null;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDep_name() {
		return dep_name;
	}

	public void setDep_name(String dep_name) {
		this.dep_name = dep_name;
	}

	public float getOrio_dorean_sitisis() {
		return orio_dorean_sitisis;
	}

	public void setOrio_dorean_sitisis(float orio_dorean_sitisis) {
		this.orio_dorean_sitisis = orio_dorean_sitisis;
	}
	
	public int getXoritikotita() {
		return xoritikotita;
	}

	public void setXoritikotita(int xoritikotita) {
		this.xoritikotita = xoritikotita;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(ArrayList<Student> students) {
		this.students = students;
	}

	public void addStudent(Student student) {
		if (students == null) {
			students = new ArrayList<>();
		}
		students.add(student);
		student.setDepartment(this);
	}

	public Employee getEmployeeInCharge() {
		return employeeInCharge;
	}

	public void setEmployeeInCharge(Employee employee) {
		this.employeeInCharge = employee;
	}

	@Override
	public String toString() {
		return "Department [id=" + id + ", dep_name=" + dep_name + ", orio_dorean_sitisis=" + orio_dorean_sitisis
				+ ", xoritikotita=" + xoritikotita  + ", location=" + location + ", students=" + students + ", employeeInCharge=" + employeeInCharge + "]";
	}


}

package mypackage.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import mypackage.dao.DepartmentDAO;
import mypackage.dao.RoleDAO;
import mypackage.dao.StudentDAO;
import mypackage.entity.Department;
import mypackage.entity.Role;
import mypackage.entity.Student;

@Controller
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentDAO studentDAO; // Δηλώνω το interface - ΠΡΟΣΟΧΗ

	@Autowired
	private RoleDAO roleDAO; // Δηλώνω το interface - ΠΡΟΣΟΧΗ

	@Autowired
	private DepartmentDAO departmentDAO;
	
	@GetMapping("/list")
	public String listStudents(Model model) {
		// get users from DAO
		List<Student> students = studentDAO.getStudents();

		// add the users to the model
		model.addAttribute("students", students);
		
		// add page title
		model.addAttribute("pageTitle", "Λίστα εγγεγραμμένων φοιτητών");

		return "list-students";
	}
	
	
	@GetMapping("/listByDepartmentSupervisor/{emp_username}")
	public String listByDepartmentSupervisor(Model model, @PathVariable("emp_username") String emp_username) {
		//System.out.println("DIAGNOSTIC from StudentController:listByDepartmentSupervisor");
		//System.out.println("username=" + emp_username);
		
		List<Student> selectedStudents = new ArrayList<Student>();
		
		List<Department> departments = departmentDAO.getDepartments();
		for (Department d: departments) {
			//System.out.println("from FOR loop... : " + d.getDep_name());
			//System.out.println(d.getEmployeeInCharge());
			if (d.getEmployeeInCharge()!=null) {
				//System.out.println(d.getEmployeeInCharge().getUsername());
				
				if (d.getEmployeeInCharge().getUsername().equalsIgnoreCase(emp_username)) {
					//System.out.println("Students=" + d.getStudents().size());
					for (int i=0; i<d.getStudents().size(); i++) {
						selectedStudents.add(d.getStudents().get(i));						
					}
//					List<Student> stds = d.getStudents();
//					for(Student s: stds) {
//						selectedStudents.add(s);					
//					}
				}
			}
		}
		
		//System.out.println("Length=" + selectedStudents.size());
//		for (int i=0; i<selectedStudents.size(); i++) {
//			System.out.println(selectedStudents.get(i).getUsername());
//		}
		
		// get users from DAO
		//List<Student> students = studentDAO.getStudentsOfDepartmentsSupervisor(supervisor_employee);
		// add the users to the model
		model.addAttribute("students", selectedStudents);
		//System.out.println("DIAGNOSTIC from StudentController:listByDepartmentSupervisor");

		model.addAttribute("emp_username", emp_username);

		// add page title
		model.addAttribute("pageTitle", "Λίστα εγγεγραμμένων φοιτητών");

		return "list-students-by-department-supervisor";
		//return "list-students";
	}
	
	@GetMapping("/listByDepartmentSupervisor/{emp_username}/{username}/enableStudent")
	public String enableStudent(@PathVariable("emp_username") String emp_username, @PathVariable("username") String username) {
		System.out.println("Inside StudentController:enableStudent method...");
		
		System.out.println("stu_username: " + username);
		Student stu = studentDAO.getStudent(username);
		stu.setEnabled(1);
		studentDAO.saveStudent(stu);
		
		System.out.println("emp_username: " + emp_username);
		
		
		return "redirect:/student/listByDepartmentSupervisor/{emp_username}";
	}
	

	@GetMapping("/{username}")
	public String getStudent(Model model, @PathVariable("username") String username) {
		// get the users
		Student student = studentDAO.getStudent(username);

		// add the user to the model
		model.addAttribute("student", student);
		
		List<Department> departments = departmentDAO.getDepartments();
		model.addAttribute("departments", departments);

		// add page title
		model.addAttribute("pageTitle", "Στοιχεία φοιτητή");

		return "student-form";
	}

	@GetMapping("/addStudent")
	public String showAddForm(Model model) {
		// create model attribute to get form data
		List<Role> roles = roleDAO.getRoles();
		model.addAttribute("roles", roles);
		
		Student student = new Student();
		student.setRole(roleDAO.getRole("ROLE_STUDENT"));
		model.addAttribute("student", student);

		List<Department> departments = departmentDAO.getDepartments();
		model.addAttribute("departments", departments);

		// add page title
		model.addAttribute("pageTitle", "Δημιουργία φοιτητή");

		return "student-form";
	}

	@PostMapping("saveStudent")
	public String saveStudent(@ModelAttribute("student") Student student, BindingResult result) {

//		int dep_id = 1;
//		System.out.println("1.DEP_ID=" + dep_id);
//
//		if (result.hasErrors()) {
//			dep_id = Integer.parseInt((String) result.getFieldValue("department.id"));
//			System.out.println("2. DEP_ID=" + dep_id);
//		}
//		System.out.println("3. DEP_ID="+dep_id);
		
		// save the user using DAO
		Role role = roleDAO.getRole(student.getRole().getAuthority());
		student.setRole(role);
		
		Department department = departmentDAO.getDepartment(student.getDepartment().getId());
		student.setDepartment(department);
		
		studentDAO.saveStudent(student);

		return "redirect:/student/list";
	}

	@GetMapping("{username}/deleteStudent")
	public String deleteStudent(@PathVariable("username") String username) {
		// deletes the user using DAO
		studentDAO.deleteStudent(username);

		return "redirect:/student/list";
	}

}

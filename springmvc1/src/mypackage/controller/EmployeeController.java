package mypackage.controller;

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
import mypackage.dao.EmployeeDAO;
import mypackage.dao.RoleDAO;
import mypackage.entity.Department;
import mypackage.entity.Employee;
import mypackage.entity.Role;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeDAO employeeDAO; // Δηλώνω το interface - ΠΡΟΣΟΧΗ

	@Autowired
	private RoleDAO roleDAO; // Δηλώνω το interface - ΠΡΟΣΟΧΗ

	@Autowired
	private DepartmentDAO departmentDAO;

	@GetMapping("/list")
	public String listEmployees(Model model) {
		// get users from DAO
		List<Employee> employees = employeeDAO.getEmployees();

		// add the users to the model
		model.addAttribute("employees", employees);
		
		// add page title
		model.addAttribute("pageTitle", "Λίστα εγγεγραμμένων υπαλλήλων");

		return "list-employees";
	}

	@GetMapping("/{username}")
	public String getEmployee(Model model, @PathVariable("username") String username) {
		// get the users
		Employee employee = employeeDAO.getEmployee(username);

		// add the user to the model
		model.addAttribute("employee", employee);

		// add page title
		model.addAttribute("pageTitle", "Στοιχεία υπαλλήλου");

		return "employee-form";
	}

	@GetMapping("/addEmployee")
	public String showAddForm(Model model) {
		// create model attribute to get form data
		Employee employee = new Employee();
		model.addAttribute("employee", employee);

		List<Role> roles = roleDAO.getRoles();
		model.addAttribute("roles", roles);

		List<Department> departments = departmentDAO.getDepartments();
		model.addAttribute("departments", departments);

		// add page title
		model.addAttribute("pageTitle", "Δημιουργία υπαλλήλου");

		return "employee-form";
	}

	@PostMapping("saveEmployee")
	public String saveEmployee(@ModelAttribute("employee") Employee employee, BindingResult result) {

//		if (result.hasErrors()) {
//			
//			System.out.println("FROM EmployerController.saveEmployer: " + result.getFieldValue("departments"));
//			System.out.println("FIELD employer_name DATA=" + employer.getUsername());
//			
//			String deps_string = (String) result.getFieldValue("departments");
//			String[] depsStringArray = deps_string.split(",");
//			List<Department> departments = new ArrayList<Department>();
//            
//			for (int i=0;i<depsStringArray.length;i++) {
//            	Department dep = departmentDAO.getDepartment(Integer.parseInt(depsStringArray[i]));
//            	departments.add(dep);
//            }
//			
//			employer.setDepartments(departments);
//			
//		}
		
		// save the user using DAO
		Role role = roleDAO.getRole(employee.getRole().getAuthority());
		employee.setRole(role);
		
		// Αποθήκευση
		employeeDAO.saveEmployee(employee);

		return "redirect:/employee/list";
	}

	@GetMapping("{username}/deleteEmployee")
	public String deleteEmployee(@PathVariable("username") String username) {
		// deletes the user using DAO
		employeeDAO.deleteEmployee(username);

		return "redirect:/employee/list";
	}

}

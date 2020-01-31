package mypackage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import mypackage.dao.DepartmentDAO;
import mypackage.dao.EmployeeDAO;
import mypackage.entity.Department;
import mypackage.entity.Employee;

@Controller
@RequestMapping("/department")
public class DepartmentController {

	@Autowired
	private EmployeeDAO employeeDAO; // Δηλώνω το interface - ΠΡΟΣΟΧΗ
	
	@Autowired
	private DepartmentDAO departmentDAO;
	
	@GetMapping("/list")
	public String listDepartments(Model model) {
		// get departments from DAO
		List<Department> departments = departmentDAO.getDepartments();
		

		// add the departments to the model
		model.addAttribute("departments", departments);

		return "list-departments";
	}

	@GetMapping("/{dep_id}")
	public String getDepartment(Model model, @PathVariable("dep_id") int dep_id) {
		// get the departments
		Department department = departmentDAO.getDepartment(dep_id);

		// add the department to the model
		model.addAttribute("department", department);
		
		List<Employee> employees = employeeDAO.getEmployees();
		model.addAttribute("employees", employees);

		// add page title
		model.addAttribute("pageTitle", "Στοιχεία τμήματος");

		return "department-form";
	}

	@GetMapping("/addDepartment")
	public String showAddForm(Model model) {
		// create model attribute to get form data
		Department department = new Department();
		model.addAttribute("department", department);

//		List<Role> roles = roleDAO.getRoles();
//		model.addAttribute("roles", roles);

		List<Employee> employees = employeeDAO.getEmployees();
		model.addAttribute("employees", employees);

		// add page title
		model.addAttribute("pageTitle", "Δημιουργία τμήματος");

		return "department-form";
	}

	@PostMapping("saveDepartment")
	public String saveDepartment(@ModelAttribute("department") Department department) {

		// save the department using DAO
		Employee employee = employeeDAO.getEmployee(department.getEmployeeInCharge().getUsername());
		department.setEmployeeInCharge(employee);

		departmentDAO.saveDepartment(department);

		return "redirect:/department/list";
	}

	@GetMapping("{dep_id}/deleteDepartment")
	public String deleteDepartment(@PathVariable("dep_id") int dep_id) {
		// deletes the department using DAO
		departmentDAO.deleteDepartment(dep_id);

		return "redirect:/department/list";
	}
}

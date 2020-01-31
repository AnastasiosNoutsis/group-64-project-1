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

import mypackage.dao.RoleDAO;
import mypackage.entity.Role;

@Controller
@RequestMapping("/role")
public class RoleController {

	@Autowired
	private RoleDAO roleDAO; // Δηλώνω το interface - Προσοχή

	@GetMapping("/list")
	public String listRoles(Model model) {
		// get roles from DAO
		List<Role> roles = roleDAO.getRoles();

		// add the roles to the model
		model.addAttribute("roles", roles);
		return "list-roles";
	}

	@GetMapping("/{authority}")
	public String getRole(Model model, @PathVariable("authority") String authority) {
		// get the roles
		Role role = roleDAO.getRole(authority);
		
		// add page title
		model.addAttribute("pageTitle", "Στοιχεία ρόλου");
				
		// add the role to the model
		model.addAttribute("role", role);
		return "role-form";
	}

	@GetMapping("/addRole")
	public String showAddForm(Model model) {
		// create model attribute to get form data
		Role role = new Role();
		model.addAttribute("role", role);

		// add page title
		model.addAttribute("pageTitle", "Δημιουργία ρόλου");
		return "role-form";
	}

	@PostMapping("saveRole")
	public String saveRole(@ModelAttribute("role") Role role) {
		// save the role using DAO
		//System.out.println("FROM ROLECONTROLLER...");
		//System.out.println(role);
		roleDAO.saveRole(role);

		return "redirect:/role/list";
	}

	@GetMapping("{authority}/deleteRole")
	public String deleteRole(@PathVariable("authority") String authority) {
		// deletes the role using DAO
//		System.out.println("ROLE=" + authority);
//		System.out.println(roleDAO.getRole(authority));
		roleDAO.deleteRole(authority);

		return "redirect:/role/list";

	}
}
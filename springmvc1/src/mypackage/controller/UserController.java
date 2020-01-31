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
import mypackage.dao.UserDAO;
import mypackage.dao.RoleDAO;
import mypackage.entity.Role;
import mypackage.entity.User;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserDAO userDAO; // Δηλώνω το interface - ΠΡΟΣΟΧΗ

	@Autowired
	private RoleDAO roleDAO; // Δηλώνω το interface - ΠΡΟΣΟΧΗ

	@GetMapping("/list")
	public String listUsers(Model model) {
		// get users from DAO
		List<User> users = userDAO.getUsers();

		// add the users to the model
		model.addAttribute("users", users);

		return "list-users";
	}

	@GetMapping("/{username}")
	public String getUser(Model model, @PathVariable("username") String username) {
		// get the users
		User user = userDAO.getUser(username);

		// add the user to the model
		model.addAttribute("user", user);

		// add page title
		model.addAttribute("pageTitle", "Στοιχεία χρήστη");

		return "user-form";
	}

	@GetMapping("/addUser")
	public String showAddForm(Model model) {
		// create model attribute to get form data
		User user = new User();
		model.addAttribute("user", user);

		List<Role> roles = roleDAO.getRoles();
		model.addAttribute("roles", roles);

		// add page title
		model.addAttribute("pageTitle", "Δημιουργία χρήστη");

		return "user-form";
	}

	@PostMapping("saveUser")
	public String saveUser(@ModelAttribute("user") User user) {
		
		// save the user using DAO
		Role role = roleDAO.getRole(user.getRole().getAuthority());
		user.setRole(role);
		userDAO.saveUser(user);

		return "redirect:/user/list";
	}
	

	@GetMapping("{username}/deleteUser")
	public String deleteUser(@PathVariable("username") String username) {
		// deletes the user using DAO
		userDAO.deleteUser(username);

		return "redirect:/user/list";
	}

}

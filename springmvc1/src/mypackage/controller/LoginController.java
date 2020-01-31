package mypackage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	@GetMapping("/login")
	public String showLogin() {
		return "login";
	}

	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/denied")
	public String showDenied() {
		return "denied";
	}
	
}
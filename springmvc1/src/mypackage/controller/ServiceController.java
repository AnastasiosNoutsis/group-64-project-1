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

import mypackage.dao.ServiceDAO;
import mypackage.dao.RoleDAO;
import mypackage.entity.Role;
import mypackage.entity.Service;

@Controller
@RequestMapping("/service")
public class ServiceController {

	@Autowired
	private ServiceDAO serviceDAO; // Δηλώνω το interface - Προσοχή

	@Autowired
	private RoleDAO roleDAO; // Δηλώνω το interface - Προσοχή

	@GetMapping("/list")
	public String listServices(Model model) {
		// get services from DAO
		List<Service> services = serviceDAO.getServices();

		// add the services to the model
		model.addAttribute("services", services);

		return "list-services";
	}

	@GetMapping("/{service_name}")
	public String getService(Model model, @PathVariable("service_name") String service_name) {
		// get the services
		Service service = serviceDAO.getService(service_name);

		// add the service to the model
		model.addAttribute("service", service);

		// get the roles
		List<Role> roles = roleDAO.getRoles();
		model.addAttribute("roles", roles);

		// add page title
		model.addAttribute("pageTitle", "Στοιχεία υπηρεσίας");
		
//		System.out.println("<-------------------------------->");
//		System.out.println("FROM ServiceController/{"+ service_name + "}");
//		System.out.println("1. service.SERVICE_NAME: " + service.getService_name());
//		System.out.println("2. service.Description: " + service.getDescription());
//		System.out.println("3. service.ROLES: " + service.getRoles());
//		System.out.println("<-------------------------------->");

		return "service-form";
	}

	@GetMapping("/addService")
	public String showAddForm(Model model) {
		// create model attribute to get form data
		Service service = new Service();
		
		model.addAttribute("service", service);

		// get the roles
		List<Role> roles = roleDAO.getRoles();
		model.addAttribute("roles", roles);
		
		// add page title
		model.addAttribute("pageTitle", "Δημιουργία υπηρεσίας");
		
		return "service-form";
	}

	
		
	@PostMapping("/saveService")
//	public String saveService(@ModelAttribute("service") Service service) {
	public String saveService(@ModelAttribute("service") Service service, BindingResult result /*, Model model*/) {
		// save the service using DAO
		if (result.hasErrors()) {
			System.out.println("FROM ServiceController.saveService: " + result.getFieldValue("roles"));
			System.out.println("FIELD service_name DATA=" + service.getService_name());
//			System.out.println("FIELD service_name DATA=" + result.getFieldValue("service_name"));
//			//service.setService_name(result.getFieldValue("service_name"));
			String roles_string = (String) result.getFieldValue("roles");
			String[] rolesStringArray = roles_string.split(",");
			List<Role> roles = new ArrayList<Role>();
            
			for (int i=0;i<rolesStringArray.length;i++) {
            	Role role = roleDAO.getRole(rolesStringArray[i]);
            	roles.add(role);
            }
            
			service.setRoles(roles);
//            
//            System.out.println("SERVICE obj: service_name=" + service.getService_name());
//            //System.out.println("SERVICE obj roles: " + service.getRoles());
//			//return "error";
//			
		}
		
//		System.out.println("<-------------------------------->");
//		System.out.println("FROM ServiceController/saveService");
//		System.out.println("1. service.SERVICE_NAME: " + service.getService_name());
//		System.out.println("2. service.Description: " + service.getDescription());
//		System.out.println("3. service.ROLES: " + service.getRoles());
//		System.out.println("<-------------------------------->");

		serviceDAO.saveService(service);
		
		return "redirect:/service/list";
	}

	
	@GetMapping("{service_name}/deleteService")
	public String deleteService(@PathVariable("service_name") String service_name) {
		// deletes the service using DAO
		serviceDAO.deleteService(service_name);

		return "redirect:/service/list";

	}
}
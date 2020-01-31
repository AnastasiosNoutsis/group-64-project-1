package mypackage.controller;

import java.util.ArrayList;
import java.util.Collections;
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
import mypackage.dao.StudentApplicationDAO;
import mypackage.dao.StudentDAO;
import mypackage.entity.Department;
import mypackage.entity.Student;
import mypackage.entity.StudentApplication;

@Controller
@RequestMapping("/studentApplication")
public class StudentApplicationController {

	@Autowired
	private DepartmentDAO departmentDAO; // Δηλώνω το interface - ΠΡΟΣΟΧΗ
	
	@Autowired
	private StudentDAO studentDAO; // Δηλώνω το interface - ΠΡΟΣΟΧΗ
	
	@Autowired
	private StudentApplicationDAO studentApplicationDAO; // Δηλώνω το interface - ΠΡΟΣΟΧΗ
	
	@GetMapping("/list")
	public String listStudentsApplications(Model model) {
		// get users from DAO
		List<StudentApplication> studentsApplications = studentApplicationDAO.getStudentApplications();
		
		List<Student> students = studentDAO.getStudents();
		// add the users to the model
		model.addAttribute("students", students);
		
		// add the users to the model
		model.addAttribute("studentsApplications", studentsApplications);
		
		// add page title
		model.addAttribute("pageTitle", "Λίστα αιτήσεων φοιτητών");

		return "list-students-applications";
	}
	
	@GetMapping("/list-results")
	public String listStudentsApplicationsResults(Model model) {
		
		List<Department> departments = departmentDAO.getDepartments();
		
		// Για κάθε τμήμα
		for(Department dep: departments) {
			
			String dep_name = dep.getDep_name();
			int dynitikoi_dikaiouxoi = (int) (dep.getXoritikotita()*dep.getOrio_dorean_sitisis()/100.0);
			
			// Βρίσκω τους φοιτητές και τις αιτήσεις τους
			List<Student> dep_students = dep.getStudents();
			ArrayList<StudentApplication> dep_stu_apps = new ArrayList<StudentApplication>();
			// Για κάθε φοιτητή του τμήματος που έχει υποβάλλει αίτηση κρατάω σε λίστα την αίτησή του
			for (Student dep_stu : dep_students) {
				StudentApplication app = dep_stu.getApplication();
				if (app != null) {
					if (app.getEggrisi() == 1) {
						dep_stu_apps.add(app);
					}
				}
			}
			
			// Τώρα θα ταξινομήσω τις αιτήσεις σε φθίνουσα σειρά με βάση τη βαθμολογια τους
			if (dep_stu_apps.size()>0) {
				/* Sorting on StuAppVathmos property*/
				System.out.println("Τμήμα:" + dep_name);
				Collections.sort(dep_stu_apps, StudentApplication.StuAppVathmos);
				for (int i=0; i<dep_stu_apps.size(); i++) {
					StudentApplication cur_app = dep_stu_apps.get(i);
					System.out.println("APP_ID:" + cur_app.getApp_id() + ", Vathmos:" + cur_app.getVathmos());
					cur_app.setSeira(i + 1);
					if (i + 1 <= dynitikoi_dikaiouxoi) {
						cur_app.setResult(1);
					} else {
						cur_app.setResult(-1);
					}
					studentApplicationDAO.saveStudentApplication(cur_app);
				}
				
			}
						
		}
		

		// get users from DAO
		List<Student> students = studentDAO.getStudents();

		// get users' applications from DAO
		List<StudentApplication> studentsApplications = studentApplicationDAO.getStudentApplications();
		
		// add the users to the model
		model.addAttribute("students", students);
		
		// add the users to the model
		model.addAttribute("studentsApplications", studentsApplications);
		
		// add page title
		model.addAttribute("pageTitle", "Αποτελέσματα αιτήσεων φοιτητών");

		return "list-students-applications-results";
	}
	
	
	@GetMapping("/{app_id}")
	public String getStudentApplicationByAppId(Model model, @PathVariable("app_id") int app_id) {
		// get the user's application
		StudentApplication studentApplication = studentApplicationDAO.getStudentApplication(app_id);

		// add the user to the model
		model.addAttribute("studentApplication", studentApplication);
		
//		List<Department> departments = departmentDAO.getDepartments();
//		model.addAttribute("departments", departments);

		// add page title
		model.addAttribute("pageTitle", "Στοιχεία αίτησης φοιτητή");

		return "student-application-form";
	}
	
	@GetMapping("/showApp/{username}")
	public String getStudentApplicationByUsername(Model model, @PathVariable("username") String username) {
		// get the user's application
		Student student = studentDAO.getStudent(username);
		StudentApplication studentApplication = studentApplicationDAO.getStudentApplication(student.getApplication().getApp_id());

		// add the user to the model
		model.addAttribute("studentApplication", studentApplication);
		
//		List<Department> departments = departmentDAO.getDepartments();
//		model.addAttribute("departments", departments);

		// add page title
		model.addAttribute("pageTitle", "Στοιχεία αίτησης φοιτητή");

		return "student-application-form";
	}
	
	
	@GetMapping("createStudentApplication/{username}")
	public String createStudentApplicationForm(Model model, @PathVariable("username") String username) {
		// create model attribute to get form data
		//System.out.println("StudentApplicationController::createStudentApplication");
		Student student = studentDAO.getStudent(username);
		//System.out.println("ΥΠΑΡΧΕΙ Ο ΦΟΙΤΗΤΗΣ: " + student.getUsername());
		String department = student.getDepartment().getDep_name();
		model.addAttribute("department", department);
		//System.out.println("ΤΜΗΜΑ ΦΟΙΤΗΤΗ: " + student.getDepartment().getDep_name());
		
//		if (student.getApplication()!=null) {
//			System.out.println("ΕΧΕΙ ΚΑΝΕΙ ΑΙΤΗΣΗ ME APP_ID: " + student.getApplication().getApp_id());
//		} else {
//			System.out.println("ΔΕΝ ΕΧΕΙ ΚΑΝΕΙ ΑΙΤΗΣΗ!");
//		}
		
		//StudentApplication stuApp = studentApplicationDAO.getStudentApplication(student.getApplication().getApp_id());
		StudentApplication studentApplicationTEMP;
		int state; // Κατάσταση αίτησης (1=υπάρχει αίτηση / 0=δεν υπάρχει αίτηση)
		// Αν υπάρχει ήδη αίτηση
		if (student.getApplication()!=null) {
			state=1; // 1 = υπάρχει αίτηση
			model.addAttribute("state", state);
			StudentApplication studentApplication = studentApplicationDAO.getStudentApplication(student.getApplication().getApp_id());
			studentApplicationTEMP = studentApplication;
			model.addAttribute("studentApplication", studentApplication);
			// add page title
			model.addAttribute("pageTitle", "Στοιχεία αίτησης");
			//return "redirect:/studentApplication/showApp/{username}";
		} else {
			state=0; // 0 = δεν υπάρχει αίτηση
			model.addAttribute("state", state);
			StudentApplication studentApplication = new StudentApplication();
			studentApplication.setStudent(student);
			studentApplicationTEMP = studentApplication;
			model.addAttribute("studentApplication", studentApplication);
			// add page title
			model.addAttribute("pageTitle", "Δημιουργία αίτησης");
		}
		
		model.addAttribute("studentApplicationTEMP", studentApplicationTEMP);
		
//		System.out.println("StudentApplicationController::createStudentApplication");
//		System.out.println("DATA FOR JSP PAGE:");
//		System.out.println("Eggrisi:" + studentApplicationTEMP.getEggrisi() + ", VATHMOS:" + studentApplicationTEMP.getVathmos());
//		System.out.println("SEIRA:" + studentApplicationTEMP.getSeira() + ", RESULT:" + studentApplicationTEMP.getResult());
		
		return "student-application-form";
	}
	
	@PostMapping("saveStudentApplication")
	public String saveStudentApplication(@ModelAttribute("studentApplication") StudentApplication studentApplication, BindingResult result) {
		//System.out.println("FROM StudentApplicationController::saveStudentApplication");
		
		// save application
		studentApplicationDAO.saveStudentApplication(studentApplication);
		
		return "redirect:/";
	}
	
	@GetMapping("{emp_username}/approveApplication/{stu_username}")
	public String approveApplication(@PathVariable("emp_username") String emp_username, @PathVariable("stu_username") String stu_username){
		
		Student stud = studentDAO.getStudent(stu_username);
		int app_id = stud.getApplication().getApp_id();
		
//		System.out.println("APP_ID="+app_id);
//		System.out.println("APP_USERNAME="+stu_username);
		
		StudentApplication stuApp = studentApplicationDAO.getStudentApplication(app_id);
		
		stuApp.setEggrisi(1);	// 1=εγκρίνεται
		studentApplicationDAO.saveStudentApplication(stuApp);
		
		return "redirect:/student/listByDepartmentSupervisor/{emp_username}";		
	}
	
	@GetMapping("{emp_username}/disapproveApplication/{stu_username}")
	public String disapproveApplication(@PathVariable("emp_username") String emp_username, @PathVariable("stu_username") String stu_username){
		Student stud = studentDAO.getStudent(stu_username);
		int app_id = stud.getApplication().getApp_id();
		
//		System.out.println("APP_ID="+app_id);
//		System.out.println("APP_USERNAME="+stu_username);
		
		StudentApplication stuApp = studentApplicationDAO.getStudentApplication(app_id);
		
		stuApp.setEggrisi(-1);	// -1=ΔΕΝ εγκρίνεται
		studentApplicationDAO.saveStudentApplication(stuApp);
		
		return "redirect:/student/listByDepartmentSupervisor/{emp_username}";	
	}
	

}

package lv.venta.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import lv.venta.services.IFilteringService;

@Controller
public class FilterController {

	@Autowired
	private IFilteringService filterService;
	
	@GetMapping("/info/showAllStudents")//localhost:8080/info/showAllStudents
	public String getAllStudentsFunc(Model model) {
		model.addAttribute("students", filterService.retrieveAllStudents());
		return "all-students-page";//will show all-students-page.html
	}
	
	@GetMapping("/info/showAllProfessors")//localhost:8080/info/showAllProfessors
	public String getAllProfessorsFunc(Model model) {
		model.addAttribute("professors", filterService.retrieveAllProfessors());
		return "all-professors-page";//will show all-professors-page.html
	}
	
	
	//TODO add this for Grades
	@GetMapping("/info/showAllGrades") //localhost:8080/info/showAllGrades
	public String getAllGradesFunc(Model model) {
		model.addAttribute("grades", filterService.retrieveAllGrades());
		return "all-grades-page";//will show all-grades-page.html
	}
	
	
	//TODO add this for Courses
	@GetMapping("/info/showAllCourses")//localhost:8080/info/showAllCourses
	public String getAllCourseFunc(Model model) {
		model.addAttribute("courses", filterService.retrieveAllCourses());
		return "all-courses-page";//will show all-courses-page.htm
	}
	//info/showAllGrades/students?id=3
	@GetMapping("/info/showAllGrades/students/{id}")
	public String getAllGradesByStudentId(@PathVariable(name="id") long id, 
			Model model) {
		try
		{
			model.addAttribute("grades", filterService.retrieveAllGradesByStudentId(id));
			return "all-grades-page";
		}
		catch (Exception e) {
			model.addAttribute("msg", e.getMessage());
			return "error-page";//will show error-page.html
		}
		}
	
	
	
	
	
	
	
	
}

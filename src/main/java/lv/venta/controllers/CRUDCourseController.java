package lv.venta.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import lv.venta.models.Course;
import lv.venta.models.Professor;
import lv.venta.services.ICRUDCourseService;

@Controller
@RequestMapping("/course")
public class CRUDCourseController {
	
	@Autowired
	private ICRUDCourseService courseService;
	
	
	
	
	@GetMapping("/all") //localhost:8080/course/all
	public String getAllCourseGetFunc(Model model) {
		model.addAttribute("courses", courseService.retrieveAllCourses());
		return "all-course-page";
	}
	
	
	@GetMapping("/all/{id}") //localhost:8080/course/all/1
	public String getOneCourseByIdGetFunc(@PathVariable("id") long id, Model model) {
		try {
			model.addAttribute("course", courseService.retrieveOneCourseById(id));
			return "one-course-page";
		} catch (Exception e) {
			return "error-page";
		}
		
	}
	
	@GetMapping("/insert") // localhost:8080/course/insert
	public String insertCourseFunc(Course course) 
	{
		return "insert-page";
	}



	@PostMapping("/insert")
	public String insertCoursePostFunc(@Valid Course course, BindingResult result)
	{
		if (!result.hasErrors()) {
			courseService.insertCourseByParams(course.getTitle(), course.getCreditpoints(), (ArrayList<Professor>)course.getProfessors());
			return "redirect:/course/all";
		} else {
			return "insert-page";
		}

	}


	@GetMapping("/update/{id}") // localhost:8080/course/update/1
	public String updateCourseByIdGetFunc(@PathVariable("id") int id, Model model) {
		try {
			model.addAttribute("course", courseService.retrieveOneCourseById(id));
			return "update-page";
		} catch (Exception e) {
			return "error-page";
		}

	}


	@PostMapping("/update/{id}")
	public String updateCourseByIdPostFunc(@PathVariable("id") int id, @Valid Course course, BindingResult result)
	{
		if (!result.hasErrors()) {

			try {
				
				Course temp = courseService.updateCourseByParams(id, course.getTitle(), course.getCreditpoints(), (ArrayList<Professor>)course.getProfessors());
				return "redirect:/course/" + temp.getIdc();
			} catch (Exception e) {
				return "redirect:/course/error";
			}
		} else {
			return "update-page";
		}

	}

	@GetMapping("/error")
	public String errorFunc() {
		return "error-page";
	}

	

	@GetMapping("/delete/{id}")
	public String deleteCourseById(@PathVariable("id") int id, Model model) {
		try {
			courseService.deleteCourseById(id);
			model.addAttribute("courses",courseService.retrieveAllCourses());
			return "all-courses-page";
		} catch (Exception e) {
			return "error-page";
		}

	}

}

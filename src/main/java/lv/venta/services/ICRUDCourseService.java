package lv.venta.services;

import java.util.ArrayList;

import lv.venta.models.Course;
import lv.venta.models.Professor;



public interface ICRUDCourseService {
	//CRUD  -create - retrieve - update - delete 
	
		//retrieve all
		ArrayList<Course> retrieveAllCourses();

		//retrieve one by id
		Course retrieveOneCourseById(int id) throws Exception;
		
		//retrieve one by title
		ArrayList<Course> retrieveAllCoursesByTitle(String title) throws Exception;
		
		//create (insert)
		Course insertCourseByParams(String title, int creditpoints, ArrayList<Professor> professors);
		
		//update
		Course updateCourseByParams(int id, String title, int creditpoints, ArrayList<Professor> professors) throws Exception;
		
		//delete
		void deleteCourseById(int id) throws Exception;
}

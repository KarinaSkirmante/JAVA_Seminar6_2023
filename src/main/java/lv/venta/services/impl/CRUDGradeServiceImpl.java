package lv.venta.services.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.dto.GradeDTO;
import lv.venta.models.Course;
import lv.venta.models.Grade;
import lv.venta.models.Student;
import lv.venta.repos.ICourseRepo;
import lv.venta.repos.IGradeRepo;
import lv.venta.repos.IStudentRepo;
import lv.venta.services.ICRUDGradeService;


@Service
public class CRUDGradeServiceImpl implements ICRUDGradeService{

	@Autowired	
	private IGradeRepo gradeRepo;
	
	@Autowired
	private IStudentRepo studentRepo;
		
	@Autowired
	private ICourseRepo courseRepo;
	
	@Override
	public ArrayList<GradeDTO> retrieveAllDataForGrades() {
		ArrayList<GradeDTO> result = new ArrayList<>();
		
		ArrayList<Grade> allGrades = (ArrayList<Grade>) gradeRepo.findAll();
		for(Grade temp : allGrades) {
			GradeDTO gradeDTO = new GradeDTO(temp.getGvalue(),
					temp.getStudent().getName(), temp.getStudent().getSurname(),
					temp.getCourse().getTitle());
			result.add(gradeDTO);
		}
		
		return result;
	}

	@Override
	public void insertGradeByGradeDTO(GradeDTO gradeDTO) throws Exception {
		ArrayList<Student> students = studentRepo.
				findByNameAndSurname(gradeDTO.getStudentName(), gradeDTO.getStudentSurname());
		
		ArrayList<Course> courses = courseRepo.findByTitle(gradeDTO.getCourseTitle());
		
		if(students!=null && courses!=null) {
			for(int i = 0; i < students.size(); i++) {
				for(int j = 0 ; j < courses.size(); j++) {
					//TODO paŗabudit, vai studentam jau neeksistē šajā priekšmetā atzīme
					Grade grade = new Grade(gradeDTO.getGrValue(), students.get(i),
							courses.get(j));
					
					gradeRepo.save(grade);
				}
			}
		}
		else
		{
			throw new Exception("Students vai kurss neeksistē");
		}

	}

}

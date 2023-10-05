package lv.venta.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lv.venta.dto.GradeDTO;
import lv.venta.services.ICRUDGradeService;

@RestController //strādāsim ne vairs ar html, bet datus kā json sūtīsim
@RequestMapping("/grade")
public class CRUDGradeController {
	
	@Autowired
	private ICRUDGradeService gradeService;
	
	@GetMapping("/all") //localhost:8080/grade/all
	public ResponseEntity<ArrayList<GradeDTO>> getAllGradesGetFunc(){
		ArrayList<GradeDTO> data = gradeService.retrieveAllDataForGrades();
		return new ResponseEntity<ArrayList<GradeDTO>>(data, HttpStatusCode.valueOf(200));
	}
	
	
	@PostMapping("/insert")//localhost:8080/grade/insert
	public ResponseEntity<ArrayList<GradeDTO>> insertNewGrade(@RequestBody @Valid GradeDTO gradeDTO) {
		//1. caur servisu jāsagalbā
		try {
			gradeService.insertGradeByGradeDTO(gradeDTO);
			ArrayList<GradeDTO> data = gradeService.retrieveAllDataForGrades();
			return new ResponseEntity<ArrayList<GradeDTO>>(data, HttpStatusCode.valueOf(200));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<ArrayList<GradeDTO>>(HttpStatusCode.valueOf(404));//nav atrasts kods, jo students via kurss neeksistē
		
		//2. jaaizdomā ko atgriezts jo šis funkcijas un atgriezt
		//3. pielietot validāciju anotācijas priekš GradeDTO klases
		//4. @Valid anotāciju pievienot uzreiz aiz @RequastBody
		
	}

}

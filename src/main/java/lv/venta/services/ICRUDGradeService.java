package lv.venta.services;

import java.util.ArrayList;

import lv.venta.dto.GradeDTO;
import lv.venta.models.Grade;

public interface ICRUDGradeService {
	ArrayList<GradeDTO> retrieveAllDataForGrades();
	void insertGradeByGradeDTO(GradeDTO gradeDTO)  throws Exception;
	//TODO update, delete, retrieveOne
}

package lv.venta.repos;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import lv.venta.models.Professor;

public interface IProfessorRepo extends CrudRepository<Professor, Long> {

	ArrayList<Professor> findAllByCoursesIdc(long id);

}

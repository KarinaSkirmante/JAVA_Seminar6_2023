package lv.venta.models;

import java.util.ArrayList;
import java.util.Collection;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name = "professor_table")//table in DB
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Professor extends Person{
	
	
	
	@Column(name = "Degree")
	@NotNull
	private Degree degree;
	
	@ManyToMany(mappedBy = "professors")
	@ToString.Exclude
	private Collection<Course> courses = new ArrayList<>();
	
	public Professor(String name, String surname, Degree degree) {
		super(name, surname);
		this.degree = degree;
	}
	public void addCourse(Course inputCourse) {
		if(!courses.contains(inputCourse)) {
			courses.add(inputCourse);
		}
	}
	
	public void removeCourse(Course inputCourse) {
		if(courses.contains(inputCourse)) {
			courses.remove(inputCourse);
		}	
	}
	
	
}

package com.klochan.course.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.HashSet;
import java.util.Set;

import static org.hibernate.annotations.CascadeType.ALL;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo( generator = ObjectIdGenerators.PropertyGenerator.class, property = "courseId", scope = Course.class )
public class Course {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@PositiveOrZero
	private Integer courseId;
	@NotNull
	private String name;
	@PositiveOrZero
	private int number;
	@PositiveOrZero
	private float cost;
	@OneToMany( fetch = FetchType.EAGER )
	@Fetch( FetchMode.SUBSELECT )
	@Cascade( CascadeType.ALL )
	@Valid
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set< Student > students = new HashSet<>();
	@ManyToMany( fetch = FetchType.EAGER )
	@Fetch( FetchMode.SUBSELECT )
	@Cascade( { ALL } )
	@Valid
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@JoinTable( name = "Course_professors",
	            joinColumns = { @JoinColumn( name = "courseId" ) },
	            inverseJoinColumns = { @JoinColumn( name = "professorId" ) } )
	private Set< Professor > professors = new HashSet<>();

	public void addStudent( Student student ) {
		students.add( student );
	}

	public void removeStudent( Student student ) {
		students.remove( student );
	}

	public void addProfessor( Professor professor ) {
		professors.add( professor );
	}
}

package com.klochan.course.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.hibernate.annotations.Cascade;
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
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIdentityInfo( generator = ObjectIdGenerators.PropertyGenerator.class,
                   property = "professorId",
                   scope = Professor.class )
public class Professor {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@PositiveOrZero
	private Integer professorId;
	@NotNull
	private String name;
	@NotNull
	private String address;
	@NotNull
	private String phone;
	@PositiveOrZero
	private Float salary;
	@Cascade( ALL )
	@ManyToMany( fetch = FetchType.EAGER, mappedBy = "professors" )
	@Fetch( FetchMode.SUBSELECT )
	@Valid
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set< Course > courses = new HashSet<>();

	public void addCourse( Course course ) {
		courses.add( course );
	}
}

package com.klochan.course.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.hibernate.annotations.CascadeType.ALL;

@Entity
@Data

@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo( generator = ObjectIdGenerators.PropertyGenerator.class,
                   property = "studentId",
                   scope = Student.class )

public class Student {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@PositiveOrZero
	private Integer studentId;
	@NotNull
	private String name;
	@NotNull
	private String address;
	@NotNull
	private String phone;
	@Email
	@NotNull
	private String email;
	@PositiveOrZero
	private Integer creditBook;
	@OneToMany( fetch = FetchType.EAGER )
	@Fetch( FetchMode.SUBSELECT )
	@JoinColumn( name = "courseStatusId" )
	@Cascade( ALL )
	@Valid
	@EqualsAndHashCode.Exclude
	private Set< CourseStatus > courseStatusList = new HashSet<>();

	public float getAverageScore() {
		return (float) courseStatusList.stream().mapToDouble( CourseStatus::getAverageScore ).average().orElse( 0 );
	}

	public boolean canEnrollTo( Course course ) {
		return courseStatusList.stream().noneMatch( cs -> cs.getCourse().equals( course ) );
	}

	public void enrollTo( Course course ) {
		if ( canEnrollTo( course ) ) courseStatusList.add( new CourseStatus( course ) );
		course.addStudent( this );
	}

	public void complete( Course course ) {
		getCourseStatus( course ).setFinalScore( (int) getCourseStatus( course ).getAverageScore() );
		getCourseStatus( course ).setCompleted( true );
		course.removeStudent( this );
	}

	@JsonIgnore
	public List< Course > getCompletedCourses() {
		return courseStatusList.stream()
		                       .filter( CourseStatus::isCompleted )
		                       .map( CourseStatus::getCourse )
		                       .collect( Collectors.toList() );
	}

	@JsonIgnore
	public List< Course > getActiveCourses() {
		return courseStatusList.stream()
		                       .filter( courseStatus -> !courseStatus.isCompleted() )
		                       .map( CourseStatus::getCourse )
		                       .collect( Collectors.toList() );
	}

	@JsonIgnore
	public CourseStatus getCourseStatus( Course course ) {
		return courseStatusList.stream()
		                       .filter( courseStatus -> course.equals( courseStatus.getCourse() ) )
		                       .findFirst()
		                       .orElseThrow( EntityNotFoundException::new );
	}


}

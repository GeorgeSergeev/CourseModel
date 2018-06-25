package com.klochan.course.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.ArrayList;
import java.util.List;

import static org.hibernate.annotations.CascadeType.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@JsonIdentityInfo( generator = ObjectIdGenerators.PropertyGenerator.class,
                   property = "courseStatusId",
                   scope = CourseStatus.class )
public class CourseStatus {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@PositiveOrZero
	private Integer courseStatusId;
	private boolean completed;
	@ElementCollection
	private List< Integer > scores = new ArrayList<>();
	@PositiveOrZero
	private int finalScore;
	@Valid
	@OneToOne
	@JoinColumn( name = "courseId" )
	@Cascade( ALL )
	@NotNull
	private Course course;

	public CourseStatus( @Valid Course course ) {
		this.course = course;
	}

	@JsonIgnore
	public float getAverageScore() {
		return (float) scores.stream().mapToInt( i -> i ).average().orElse( 0 );
	}

	public void addScore( int score ) {
		scores.add( score );
	}

}

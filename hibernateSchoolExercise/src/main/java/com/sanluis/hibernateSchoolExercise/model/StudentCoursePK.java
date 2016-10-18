package com.sanluis.hibernateSchoolExercise.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class StudentCoursePK implements Serializable{

	
	private static final long serialVersionUID = 6764088904472411903L;
	
	@Column(name="students_id")
	private Integer students_id;
	
	@Column(name="courses_id")
	private Integer courses_id;

	public Integer getStudents_id() {
		return students_id;
	}

	public void setStudents_id(Integer students_id) {
		this.students_id = students_id;
	}

	public Integer getCourses_id() {
		return courses_id;
	}

	public void setCourses_id(Integer courses_id) {
		this.courses_id = courses_id;
	}	
}

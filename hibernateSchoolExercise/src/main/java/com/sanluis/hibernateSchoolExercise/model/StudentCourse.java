package com.sanluis.hibernateSchoolExercise.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="courses")
public class StudentCourse {
	
	@EmbeddedId
    private StudentCoursePK studentCoursePK;
	
	@Column(name="mark")
	private Integer mark;
	
	@Column(name="year")
	private Integer year;

	public StudentCoursePK getStudentCoursePK() {
		return studentCoursePK;
	}

	public void setStudentCoursePK(StudentCoursePK studentCoursePK) {
		this.studentCoursePK = studentCoursePK;
	}

	public Integer getMark() {
		return mark;
	}

	public void setMark(Integer mark) {
		this.mark = mark;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	
	
}

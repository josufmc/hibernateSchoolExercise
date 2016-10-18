package com.sanluis.hibernateSchoolExercise.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="courses")
public class Course {
	
	@Id
	@GeneratedValue
	@Column(name="courses_id")
	private Integer id;
	
	@Column(name="course")
	private String course;
	
	@OneToMany(mappedBy="course", fetch=FetchType.EAGER)
	private Set<StudentCourse> studentCourseList;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public Set<StudentCourse> getStudentCourseList() {
		return studentCourseList;
	}

	public void setStudentCourseList(Set<StudentCourse> studentCourseList) {
		this.studentCourseList = studentCourseList;
	}
	
	
	
}

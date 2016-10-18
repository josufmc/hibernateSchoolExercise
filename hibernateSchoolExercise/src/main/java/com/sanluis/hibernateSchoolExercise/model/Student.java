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
@Table(name="students")
public class Student {
	
	@Id
	@GeneratedValue
	@Column(name="students_id")
	private Integer id;
	
	@Column(name="student")
	private String student;
	
	@OneToMany(mappedBy="student", fetch=FetchType.EAGER)
	private Set<StudentCourse> studentCourseList;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStudent() {
		return student;
	}

	public void setStudent(String student) {
		this.student = student;
	}

	public Set<StudentCourse> getStudentCourseList() {
		return studentCourseList;
	}

	public void setStudentCourseList(Set<StudentCourse> studentCourseList) {
		this.studentCourseList = studentCourseList;
	}
	
	
	
	
}

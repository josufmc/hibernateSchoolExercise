package com.sanluis.hibernateSchoolExercise.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name="students_courses")
public class StudentCourse {
	
	@EmbeddedId
    private StudentCoursePK studentCoursePK;
	
	@ManyToOne
    @MapsId("students_id")
    @JoinColumn(name = "students_id")
	private Student student;
	
	@ManyToOne
    @MapsId("courses_id")
    @JoinColumn(name = "courses_id")
	private Course course;
	
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

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	
	
}

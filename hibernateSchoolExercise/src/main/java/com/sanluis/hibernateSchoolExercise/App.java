package com.sanluis.hibernateSchoolExercise;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.sanluis.hibernateSchoolExercise.model.Course;
import com.sanluis.hibernateSchoolExercise.model.Student;
import com.sanluis.hibernateSchoolExercise.model.StudentCourse;


/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {

		Configuration cfg = new Configuration()
				.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver")
				.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/school")
				.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect")
				.setProperty("hibernate.cache.use_second_level_cache", "false")
				.setProperty("hibernate.connection.username", "root").setProperty("hibernate.connection.password", "")
				// .setProperty("hibernate.show_sql", "true")
				.setProperty("hibernate.current_session_context_class", "thread")
				.addAnnotatedClass(Course.class)
				.addAnnotatedClass(Student.class)
				.addAnnotatedClass(StudentCourse.class)
				;

		SessionFactory sf = cfg.buildSessionFactory();
		Session s = sf.getCurrentSession();

		
		s.beginTransaction();
		EntityManager em = s.getEntityManagerFactory().createEntityManager();
		CriteriaBuilder builder = em.getCriteriaBuilder();
		
		// Obtenemos los estudiantes
		CriteriaQuery<Student> criteriaStudent = builder.createQuery(Student.class);
		Root<Student> rootStudent = criteriaStudent.from(Student.class);
		criteriaStudent.select(rootStudent);
		List<Student> studentList = em.createQuery(criteriaStudent).getResultList();
		
		// Obtenemos los cursos
		CriteriaQuery<Course> criteriaCourse = builder.createQuery(Course.class);
		Root<Course> rootCourse = criteriaCourse.from(Course.class);
		criteriaCourse.select(rootCourse);
		List<Course> courseList = em.createQuery(criteriaCourse).getResultList();
		
		// Obtenemos la nota media
		
		s.getTransaction().commit();
		
		System.out.println("\r\n--STUDENTS--");
		for(Student student : studentList){
			System.out.println("->" + student.getStudent());
			Set<StudentCourse> courseSet = student.getStudentCourseList();
			for(StudentCourse sc : courseSet){
				System.out.println("  " + sc.getCourse().getCourse() + " - " + sc.getMark());
			}
		}
		
		System.out.println("\r\n--COURSES--");
		for(Course course : courseList){
			System.out.println(course.getCourse() + " (" + course.getStudentCourseList().size() + ")");
			Set<StudentCourse> courseSet = course.getStudentCourseList();
			for(StudentCourse sc : courseSet){
				System.out.println("  " + sc.getStudent().getStudent());
			}
		}
		
		
		
		System.out.println("OK!");
	}
}

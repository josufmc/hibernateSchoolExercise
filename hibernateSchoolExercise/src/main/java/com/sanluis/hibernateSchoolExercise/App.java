package com.sanluis.hibernateSchoolExercise;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.sanluis.hibernateSchoolExercise.model.Course;
import com.sanluis.hibernateSchoolExercise.model.IntegerWrapper;
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
		
		/*
		// Obtenemos la nota media
		Query q = em.createQuery("select avg(s.mark) from StudentCourse s");
		Double mark = (Double) q.getSingleResult();
		
		// Obtenemos el año que más nota ha obtenido
		Query q2 = em.createQuery("select s.year from StudentCourse s WHERE s.mark = (SELECT max(s2.mark) from StudentCourse s2)");
		q2.setMaxResults(1);
		Integer year = (Integer) q2.getSingleResult();
		*/
		
		CriteriaQuery<IntegerWrapper> criteriaInt = builder.createQuery(IntegerWrapper.class);
		// PASO 2: Configurar la clausula FROM
		Root<StudentCourse> root = criteriaInt.from(StudentCourse.class);
		Path<Integer> year = root.get("year");
		criteriaInt.select(builder.max(builder.construct(IntegerWrapper.class, year)));
		//Predicate tituloB = builder.like(root.get("titulo"), "%b%");
		//criteriaInt.where(tituloB);
		List<IntegerWrapper> integerList = em.createQuery(criteriaInt).getResultList();
		
		// Obtenemos la nota media (B)
		
		// Obtenemos el año que más nota ha obtenido (B)
		
		s.getTransaction().commit();
		
		// Creamos un curso
		s = sf.getCurrentSession();
		s.beginTransaction();
		Course newCourse = new Course();
		newCourse.setCourse("Curso nuevo!");
		s.save(newCourse);
		s.flush();
		s.getTransaction().commit();
		
		// Editamos el curso
		s = sf.getCurrentSession();
		s.beginTransaction();
		newCourse.setCourse("Curso9!");
		s.saveOrUpdate(newCourse);
		s.flush();
		s.getTransaction().commit();
		
		// Borramos el curso
		s = sf.getCurrentSession();
		s.beginTransaction();
		s.remove(newCourse);
		s.flush();
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
		
		
		System.out.println("\r\n--YEARS--");
		for(IntegerWrapper integ : integerList){
			System.out.println(integ.getValue());
		}
		
		System.out.println("\r\n--AVG Mark--");
		//System.out.println(mark);
		
		System.out.println("\r\n--Year with optimal mark--");
		//System.out.println(year);
		
		
		System.out.println("OK!");
	}
}

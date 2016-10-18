package com.sanluis.hibernateSchoolExercise;

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
				.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/world")
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

		// Obtenemos los paises
		s.beginTransaction();

		s.getTransaction().commit();
		System.out.println("OK!");
	}
}

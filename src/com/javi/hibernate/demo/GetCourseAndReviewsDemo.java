package com.javi.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.javi.hibernate.demo.entity.Course;
import com.javi.hibernate.demo.entity.Instructor;
import com.javi.hibernate.demo.entity.InstructorDetail;
import com.javi.hibernate.demo.entity.Review;
import com.javi.hibernate.demo.entity.Student;

public class GetCourseAndReviewsDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.addAnnotatedClass(Review.class)
								.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			
			// start a transaction
			session.beginTransaction();
			
			// get the course
			int theId = 10;
			Course tempCourse = session.get(Course.class,  theId);
			
			// print the course
			System.out.println(tempCourse);
			
			// print the course reviews
			System.out.println(tempCourse.getReviews());//reviews are configured for lazy fetch. When we load the course, we only get the course. Then later on we can get the course reviews separately. We are loading those reviews on demand 
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
			
		}
		
		finally {
			
			// add clean up code
			
			session.close();
			
			factory.close();
		}

	}

}

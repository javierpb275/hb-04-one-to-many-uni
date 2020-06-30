package com.javi.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.javi.hibernate.demo.entity.Instructor;
import com.javi.hibernate.demo.entity.InstructorDetail;
import com.javi.hibernate.demo.entity.Student;

public class DeleteDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			
			// start a transaction
			session.beginTransaction();
			
			// get instructor by primary key / id
			int theId = 3;
			
			Instructor tempInstructor =
					session.get(Instructor.class, theId);//Will return null if not found
			
			System.out.println("Found instructor: " + tempInstructor);
			
			// delete the instructors
			if (tempInstructor != null) {
				
				System.out.println("Deleting: " + tempInstructor);
				
				// Note: will ALSO delete associated "details" object
				// because of CascadeType.ALL
				//
				session.delete(tempInstructor);
				
			}
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
			
		}
		
		finally {
			factory.close();
		}

	}

}

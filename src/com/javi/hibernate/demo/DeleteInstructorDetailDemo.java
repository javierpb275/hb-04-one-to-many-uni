package com.javi.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.javi.hibernate.demo.entity.Instructor;
import com.javi.hibernate.demo.entity.InstructorDetail;
import com.javi.hibernate.demo.entity.Student;

public class DeleteInstructorDetailDemo {

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
			
			// get the instructor detail object
			int theId = 3;
			InstructorDetail tempInstructorDetail =
					session.get(InstructorDetail.class, theId);//Returns null if not found
			
			// print the instructor detail
			System.out.println("tempInstructorDetail: " + tempInstructorDetail);
			
			
			// print the associated instructor
			System.out.println("the associated instructor: " + 
								tempInstructorDetail.getInstructor());//NullPointerException
			
			// delete the instructor detail
			System.out.println("Deleting tempInstructorDetail: " + tempInstructorDetail);
			
			// remove the associated object reference
			// break bi-directional link by removing the associated object reference. That's why the instructor stay in the database
			
			tempInstructorDetail.getInstructor().setInstructorDetail(null);
			
			session.delete(tempInstructorDetail);
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
			
		}
		
		catch (Exception exc) {
			exc.printStackTrace();
		}
		
		finally {
			
			//handle connection leak issue by doing:
			session.close();
			
			
			factory.close();
			
		}

	}

}

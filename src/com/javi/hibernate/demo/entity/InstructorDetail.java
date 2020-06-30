package com.javi.hibernate.demo.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

//annotate the class as an entity and map to db table
@Entity
@Table(name="instructor_detail")
public class InstructorDetail {
	
	// define the fields and annotate the fields with db column names
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="youtube_channel")
	private String youtubeChannel;
	
	@Column(name="hobby")
	private String hobby;
	
	// add new field for instructor (also add getter/setters)
	
	// add @OneToOne annotation (this will give us bidirectional relationship between Instructor and InstructorDetail)
	@OneToOne(mappedBy="instructorDetail", 
			cascade= {CascadeType.DETACH, CascadeType.MERGE, //update cascade type so we are using all cascade types except for REMOVE (meaning delete). So we're not cascading any delete operations to the instructor. That's why the instructor stay in the database
					CascadeType.PERSIST, CascadeType.REFRESH})//Refers to "instructorDetail" property in "Instructor" class. CascadeType.ALL: Cascade all operations to the associated instructor
	private Instructor instructor;

	
	public Instructor getInstructor() {
		return instructor;
	}



	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}
	
	
	// create constructors


	public InstructorDetail() {
		
	}



	public InstructorDetail(String youtubeChannel, String hobby) {
		this.youtubeChannel = youtubeChannel;
		this.hobby = hobby;
	}


	// generate getter/setter methods

	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getYoutubeChannel() {
		return youtubeChannel;
	}



	public void setYoutubeChannel(String youtubeChannel) {
		this.youtubeChannel = youtubeChannel;
	}



	public String getHobby() {
		return hobby;
	}



	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	
	
	// generate toString() method
	
	@Override
	public String toString() {
		return "InstructorDetail [id=" + id + ", youtubeChannel=" + youtubeChannel + ", hobby=" + hobby + "]";
	}
	
	
}

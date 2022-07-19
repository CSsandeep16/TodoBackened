package com.todo.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.hateoas.RepresentationModel;

//import org.springframework.hateoas.RepresentationModel;

import lombok.Data;

@Data
@Entity
@Table(name = "todos")

public class Todo extends RepresentationModel<Todo> {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotBlank(message = "Should not be empty")
	@Column(name = "username" , nullable = false)
	private String user;
	
	
	@Size(min = 9, message = "min 9 characters required")
	@Column
	private String description;
	
	@Column
	private Date targetDate;
	
	@Column
	private boolean isDone;
//	public Todo(int id, String user, String description, Date targetDate, boolean isDone) {
//		super();
//		this.id = id;
//		this.user = user;
//		this.description = description;
//		this.targetDate = targetDate;
//		this.isDone = isDone;
//	}
	

}
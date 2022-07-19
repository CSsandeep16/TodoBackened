package com.todo.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.todo.models.Todo;
import com.todo.services.TodosService;

//@Service
public class ToDoServiceInMemoryImpl implements TodosService {
	
	private static List<Todo> todos = new ArrayList<>();
	private static int todosCount = 3;
	
	
//	static {
//		todos.add(new Todo(1,"Sandy", "Study Spring Boot", new Date(), false));
//		todos.add(new Todo(2,"Sandy", "Study Spring JPA", new Date(), false));
//		todos.add(new Todo(3,"Ram", "Study Spring Boot", new Date(), false));
//	}

	public List<Todo> getAllTodos() {
		// TODO Auto-generated method stub
		return todos;
	}

	@Override
	public List<Todo> getTodosByUser(String name) {
		// TODO Auto-generated method stub
		System.out.println("****** fetch todos for name " + name);
		List<Todo> userTodo = new  ArrayList<>();
		
		for (Todo todo: todos) {
			if (todo.getUser().equals(name)) {
				userTodo.add(todo);
			}
		}
		return userTodo;
	}

	@Override
	public Todo getTodo(int id) {
		// TODO Auto-generated method stub
		System.out.println("****** fetch todos for id " + id);
        Todo  idTodo = null;
		
		for (Todo todo: todos) {
			if (todo.getId() == id) {
				idTodo = todo;
				break;
			}
		}
		return idTodo;
	}

	@Override
	public Todo addTodo(Todo todo) {
		todo.setId(++todosCount);
		todos.add(todo);
		// TODO Auto-generated method stub
		return todo;
	}

	@Override
	public Todo updateTodo(Todo newTodo, String name, int id) {
		// TODO Auto-generated method stub
		Todo existingTodo = this.getTodo(id);
			if (existingTodo != null && existingTodo.getUser().equals(name) && existingTodo.getId() == id) {
				existingTodo.setDescription(newTodo.getDescription());
				existingTodo.setTargetDate(newTodo.getTargetDate());
				existingTodo.setDone(newTodo.isDone());
				return existingTodo;
			}
		return null;
	}

	@Override
	public boolean deleteTodo(int id) {
		// TODO Auto-generated method stub
		for (Todo todo: todos) {
			if(todo.getId() == id) {
				return todos.remove(todo);
			}
		}
		return false;
	}

}

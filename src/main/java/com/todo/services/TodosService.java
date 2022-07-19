package com.todo.services;

import java.util.List;

import com.todo.models.Todo;

public interface TodosService {
  
	public List<Todo> getAllTodos();
	public List<Todo> getTodosByUser(String name);
	public Todo getTodo(int id);
	public Todo addTodo(Todo todo);
	public Todo updateTodo(Todo newTodo,String name, int id);
	public boolean deleteTodo(int id);
}

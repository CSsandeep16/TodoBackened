package com.todo.services.impl;

import java.util.List;
import java.util.Optional;

import javax.swing.SortOrder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.todo.models.Todo;
import com.todo.repositories.TodosRepository;
import com.todo.services.TodosService;

@Service
public class TodosDatabaseImpl implements TodosService {
	
	@Autowired
	private TodosRepository todosrepository;

	@Override
	public List<Todo> getAllTodos() {
		// TODO Auto-generated method stub
		return todosrepository.findAll(Sort.by(Sort.Direction.ASC, "user"));
	}

	@Override
	public List<Todo> getTodosByUser(String name) {
		// TODO Auto-generated method stub
		return todosrepository.findByUser(name);
	}

	@Override
	public Todo getTodo(int id) {
		Optional<Todo> todo = todosrepository.findById(id);
		if (todo.isPresent())
			return todo.get();
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Todo addTodo(Todo todo) {
		// TODO Auto-generated method stub
		return todosrepository.save(todo);
	}

	@Override
	public Todo updateTodo(Todo newTodo, String name, int id) {
		// TODO Auto-generated method stub
		Optional<Todo> todo = todosrepository.findById(id);
		if (todo.isPresent() && todo.get().getUser().equals(name)) {
			Todo updatedtodo = todo.get();
			updatedtodo.setDescription(newTodo.getDescription());
			updatedtodo.setTargetDate(newTodo.getTargetDate());
			updatedtodo.setDone(newTodo.isDone());
			return todosrepository.save(updatedtodo);
		}
		
		return null;
	}

	@Override
	public boolean deleteTodo(int id) {
		// TODO Auto-generated method stub
		Optional<Todo> todo = todosrepository.findById(id);
		if (todo.isPresent()) {
			todosrepository.deleteById(id);
			return true;
		}
		return false;
	}

}

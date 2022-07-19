package com.todo.controllers;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
//import org.springframework.hateoas.Link;
//import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.todo.exceptions.ResourceNotFoundException;
import com.todo.models.Todo;
import com.todo.services.TodosService;

@RestController
@RequestMapping("/api/v1/todos")
public class TodosController {
	
	@Autowired
	private TodosService toDoService;
	
	//All Todos
     @GetMapping()
	public List<Todo> getAllTodos() {
    	 List<Todo> todos = toDoService.getAllTodos();
    	 
    	 for (Todo todo: todos) {
    		 Link link = WebMvcLinkBuilder.linkTo(TodosController.class).slash(todo.getId()).withSelfRel();
    		 todo.add(link);
    	 }
    	 
    	 return todos;
	}
     
     //todos/user/{name}
     @GetMapping("/user/{name}")
 	public List<Todo> getTodos(@PathVariable String name) {
    	 List<Todo> todo = toDoService.getTodosByUser(name);
    	 if (todo.isEmpty()) {
    		 throw new ResourceNotFoundException("Custom Exception");
    	 }
     	 return todo;
 	}
     
     
     //todos/{id}
     @GetMapping("/{id}")
     public Todo getTodo(@PathVariable int id) {
    	 Todo todo = toDoService.getTodo(id);
    	 if (todo == null) {
    		// throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not found");
    		 throw new ResourceNotFoundException("Custom Exception");
    	 }
    	 
     	 return toDoService.getTodo(id);
 	}
     
     @PostMapping()
     public ResponseEntity<Todo>  addTodo(@Valid @RequestBody Todo todo) {
    	 Todo newTodo = toDoService.addTodo(todo);
    	 if (newTodo == null) {
    		 return ResponseEntity.noContent().build();
    	 }
    	 
    	 URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newTodo.getId()).toUri();
    	 return ResponseEntity.created(location).build();
    	// return  new ResponseEntity<Todo>(newTodo,HttpStatus.CREATED);
     }
     
     @PutMapping("/user/{name}/{id}")
     public ResponseEntity<Todo>  updateTodo(@Valid @RequestBody Todo todo, @PathVariable String name, @PathVariable int id ) {
    	 Todo newTodo = toDoService.updateTodo(todo, name, id);
    	 if (newTodo == null) {
    		 throw new ResourceNotFoundException("Not Found!!!!!");
    	 }
    	 
//    	 URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newTodo.getId()).toUri();
//    	 return ResponseEntity.created(location).build();
    	return  new ResponseEntity<Todo>(newTodo,HttpStatus.OK);
     }
     
     @DeleteMapping("/{id}")
     public ResponseEntity<String>  deleteTodo(@PathVariable int id) {
    	 boolean deleted = toDoService.deleteTodo(id);
    	 if (!deleted) {
    		 throw new ResourceNotFoundException("Not Found");
    	 }
    	 return new ResponseEntity<String>("Successfully Deleted", HttpStatus.OK);
    	
     }
     
     
     @ResponseStatus(value = HttpStatus.BAD_REQUEST)
     @ExceptionHandler(value = {MethodArgumentNotValidException.class})
     
     public Map<String, String> handleValidationException(MethodArgumentNotValidException ex) {
    	 Map<String,String> errors = new HashMap<>();
    	 ex.getBindingResult().getAllErrors().forEach((error) -> {
    	  String fieldName = ((FieldError)error).getField();
    	  String errorMessage = ((FieldError)error).getDefaultMessage();
    	  errors.put(fieldName, errorMessage); }
    	  ); 
    	 return errors;
     }
}

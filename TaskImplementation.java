package com.project.todo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskImplementation {

	//	using a HashMap to map each user to their list of TODO items:
	private Map<String, List<TODO>> todosByUser;
	private String currentUser;

	public TaskImplementation() {
		todosByUser = new HashMap<>();
		currentUser = null;
	}

	public void addUser(String user) {
		todosByUser.put(user, new ArrayList<>());
	}

	public void switchUser(String user) {
		currentUser = user;
	}

	public void printTodos() {
		List<TODO> todos = todosByUser.get(currentUser);
		if (todos == null || todos.isEmpty()) {
			System.out.println("No TODO items found.");
			return;
		}

		for (TODO todo : todos) {
			System.out.printf("%s [%s] (Priority: %d, Due date: %s)%n", todo.getTodo(), todo.getStatus(),
					todo.getPriority(), todo.getDueDate());
		}

		List<TODO> overdueTodos = todos.stream().filter(todo -> todo.getStatus().equals("OPEN"))
				.filter(todo -> LocalDate.parse(todo.getDueDate()).isBefore(LocalDate.now()))
				.collect(Collectors.toList());

		if (!overdueTodos.isEmpty()) {
			System.out.println("The following TODO items are overdue:");
			for (TODO todo : overdueTodos) {
				System.out.printf("%s (Due date: %s)%n", todo.getTodo(), todo.getDueDate());
			}
		}
	}

	public void addTodo() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter user name: ");
		String user = scanner.nextLine();
		System.out.print("Enter description: ");
		String description = scanner.nextLine();
		System.out.print("Enter priority (1-10): ");
		int priority = scanner.nextInt();
		scanner.nextLine(); // consume newline
		System.out.print("Enter due date (yyyy-MM-dd): ");
		String dueDate = scanner.nextLine();

		List<TODO> todos = todosByUser.get(currentUser);
		if (todos == null) {
			System.out.println("User not found.");
			return;
		}

		TODO todo = new TODO(description, "OPEN", priority, dueDate, user);
		todos.add(todo);
	}

	public void markTodoAsDone() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter user name: ");
		String user = scanner.nextLine();
		List<TODO> todos = todosByUser.get(currentUser);
		if (todos == null || todos.isEmpty()) {
			System.out.println("No TODO items found.");
			return;
		}
		System.out.println("Enter TODO to change to DONE");
		String text = scanner.nextLine().toLowerCase();
		for (int i = 0; i < todos.size(); i++) {
			TODO todo = todos.get(i);
			if(todo.getTodo().equals(text)) {
				todo.setStatus("DONE");
				System.out.println("Your todo as Marked Done");
				break;
			}
		}
	}

	public void changePriorDue() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter user name: ");
		String user = scanner.nextLine();
		List<TODO> todos = todosByUser.get(currentUser);
		if (todos == null || todos.isEmpty()) {
			System.out.println("No TODO items found.");
			return;
		}
		System.out.println("Enter TODO to change");
		String change = scanner.nextLine().toLowerCase();
		scanner.nextLine();
		for (int i = 0; i < todos.size(); i++) {
			TODO todo = todos.get(i);
			if(todo.getTodo().equals(change)) {
				System.out.print("Enter priority (1-10): ");
				int priority = scanner.nextInt();
				todo.setPriority(priority);
				System.out.print("Enter due date (yyyy-MM-dd): ");
				String dueDate = scanner.nextLine();
				todo.setDueDate(dueDate);
				System.out.println("Priority and Due date is Updated sucessfully!");
				break;
			}
		}
	}
	
	public void filterTodos(String filter) {
	    Scanner scanner = new Scanner(System.in);
	    System.out.print("Enter user name: ");
	    String user = scanner.nextLine();
	    
	    List<TODO> todos = todosByUser.get(currentUser);
	    if (todos == null || todos.isEmpty()) {
	        System.out.println("No TODO items found.");
	        return;
	    }
	    
	    List<TODO> filteredTodos;
	    switch (filter) {
	        case "OPEN":
	            filteredTodos = todos.stream()
	                .filter(todo -> todo.getStatus().equals("OPEN"))
	                .collect(Collectors.toList());
	            break;
	        case "DONE":
	            filteredTodos = todos.stream()
	                .filter(todo -> todo.getStatus().equals("DONE"))
	                .collect(Collectors.toList());
	            break;
	        default:
	            filteredTodos = todos;
	            break;
	    }
	    
	    if (filteredTodos.isEmpty()) {
	        System.out.println("No TODO items found.");
	        return;
	    }
	    
	    for (TODO todo : filteredTodos) {
	        System.out.printf("%s [%s] (Priority: %d, Due date: %s)%n", todo.getTodo(), todo.getStatus(),
	            todo.getPriority(), todo.getDueDate());
	    }
	}

	public TODO getNextTodo() {
	    List<TODO> todos = todosByUser.get(currentUser);
	    if (todos == null || todos.isEmpty()) {
	        return null;
	    }
	    
	    List<TODO> openTodos = todos.stream()
	        .filter(todo -> todo.getStatus().equals("OPEN"))
	        .collect(Collectors.toList());
	    
	    if (openTodos.isEmpty()) {
	        return null;
	    }
	    
	    TODO nextTodo = openTodos.get(0);
	    for (TODO todo : openTodos) {
	        LocalDate dueDate = LocalDate.parse(todo.getDueDate());
	        LocalDate nextDueDate = LocalDate.parse(nextTodo.getDueDate());
	        if (dueDate.isBefore(LocalDate.now().plusDays(3)) && dueDate.isBefore(nextDueDate)) {
	            nextTodo = todo;
	        } else if (todo.getPriority() > nextTodo.getPriority()) {
	            nextTodo = todo;
	        }
	    }
	    
	    return nextTodo;
	}
}

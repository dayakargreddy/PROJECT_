package com.project.todo;
import java.util.*;
public class SolutionMain {


		public static void main(String[] args) {
			
			TaskImplementation doImplementation= new TaskImplementation();
			Scanner ip=new Scanner(System.in);
			while(true) {
				System.out.println("\n------Manage your TODOs------");
				System.out.println("Type\n1 to add a new User (Recomended to first add your name and create your own TODO List)\n2 to Switch user");
				System.out.println("3 to print all your TODOs\n4 to add a new TODO\n5 to mark a TODO as DONE\n6 to change priority and due date of a TODO");
				System.out.println("7 to print either OPEN or CLOSE TODOs\n8 to print nearing(Urgent) TODOs\n9 to quit\n>Your choice? ");
				int choice = ip.nextInt();
				switch(choice) {
				case 1:
					System.out.println("Enter the User name to add user");
					String user = ip.next().toUpperCase();
					doImplementation.addUser(user);
					break;
					
				case 2:
					System.out.println("Enter the name to switch user");
					String user1 = ip.next().toUpperCase();
					doImplementation.switchUser(user1);
					break;
					
				case 3:
					doImplementation.printTodos();
					break;

				case 4: 
					doImplementation.addTodo();
					break;

				case 5: 
					doImplementation.markTodoAsDone();
					break;

				case 6:
					doImplementation.changePriorDue();
					break;
				
				case 7:
					System.out.println("Type 'OPEN' to see all open TODO or Type'DONE' to see all Done TODO");
					String inputText = ip.nextLine();
					doImplementation.filterTodos(inputText);
					break;
					

				case 8:
			
					doImplementation.getNextTodo();
					break;
				
				case 9:
					System.out.println("------Thats all the list you got for now,you can exit now------");
					System.exit(0);
					break;

				default:
					try {
						String msg= "invalid input! Please enter valid choid";
						throw new InvalidChoiceException(msg);

					}
					catch(Exception e) {

						System.out.println(e.getMessage());

					}

				}
				System.out.println("======================");
			}

		}


		}


package com.project.todo;

public class TODO {
		private String todo;
	    private String status;
	    private int priority;
	    private String dueDate;
	    private String user;
		public TODO(String todo, String status, int priority, String dueDate, String user) {
			super();
			this.todo = todo;
			this.status = status;
			this.priority = priority;
			this.dueDate = dueDate;
			this.user = user;
		}
		public String getTodo() {
			return todo;
		}
		public void setTodo(String todo) {
			this.todo = todo;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public int getPriority() {
			return priority;
		}
		public void setPriority(int priority) {
			this.priority = priority;
		}
		public String getDueDate() {
			return dueDate;
		}
		public void setDueDate(String dueDate) {
			this.dueDate = dueDate;
		}
		public String getUser() {
			return user;
		}
		public void setUser(String user) {
			this.user = user;
		}
		@Override
		public String toString() {
			return  status+": " + todo +"( priority=" + priority + ", dueDate=" + dueDate+ ", user=" + user + ")";
		}
	    
	    
}

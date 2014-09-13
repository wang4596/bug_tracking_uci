/*
 * Team D Bug Tracker Project
 * 9/7/2014		Hema Omprakash					Created file.
 * 9/7/2014		Melissa Stratton		Added getAssignee and setAssignee.
 */

package com.beans;

public class TicketBean {
	
		private int id;
		private String project;
		private String status;
		private String priority;
		private String summary;
		private String description;
		private String createdBy;
		private String updatedBy;
		private String assignee;
		private String clientEmail;
		  
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getProject() {
			return project;
		}
		public void setProject(String project) {
			this.project = project;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public String getPriority() {
			return priority;
		}
		public void setPriority(String priority) {
			this.priority = priority;
		}
		public String getSummary() {
			return summary;
		}
		public void setSummary(String summary) {
			this.summary = summary;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public String getCreatedBy() {
			return createdBy;
		}
		public void setCreatedBy(String createdBy) {
			this.createdBy = createdBy;
		}
		public String getUpdatedBy() {
			return updatedBy;
		}
		public void setUpdatedBy(String updatedBy) {
			this.updatedBy = updatedBy;
		}
		public String getAssignee() {
			return assignee;
		}
		public void setAssignee(String assignee) {
			this.assignee = assignee;
		}
		public String getClientEmail(){
			return clientEmail;
		}
		public void setClientEmail(String clientEmail){
			this.clientEmail = clientEmail;
		}
	
} 

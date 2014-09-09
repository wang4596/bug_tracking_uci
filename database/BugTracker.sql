/*
 * Team D Bug Tracker Project
 * 8/28/2014  	Saurabh Pandit 		Created File
 * 9/9/2014		Melissa Stratton	Added Assignee to Tickets table
 */

create database BugTracker;
create table BugTracker.Tickets(
	Id INT NOT NULL AUTO_INCREMENT,
	Summary VARCHAR(400) NOT NULL,
	Description VARCHAR(2000) NOT NULL,
	Status VARCHAR(40) NOT NULL,
	ProjectName VARCHAR(200) NOT NULL,
	Priority VARCHAR(40) NOT NULL,
	Aassignee VARCHAR(400) NULL,
	Created_By VARCHAR(400) NOT NULL,
	Updated_By VARCHAR(400) NOT NULL,
	Created_Date DATETIME DEFAULT NULL,
	Updated_Date TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	PRIMARY KEY ( Id )
	
);
/*
 * If Tickets table already exists and you don't want to drop and create it use following SQL statement to add Assignee field to Tickets table.
 * ALTER TABLE BugTracker.Tickets ADD COLUMN Assignee VARCHAR(400) NULL AFTER Priority;
 */
drop table BugTracker.Users;
create table BugTracker.Users(
	emailID VARCHAR(200) NOT NULL,
	firstname VARCHAR(40),
	lastname VARCHAR(40),
	password VARCHAR(32) NOT NULL,
	role VARCHAR(32) NOT NULL,
	Created_Date DATETIME DEFAULT NULL,
	Updated_Date TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	PRIMARY KEY ( emailID )
);

create table BugTracker.Projects(
	Id INT NOT NULL AUTO_INCREMENT,
	ProjectName VARCHAR(200) NOT NULL,
	PRIMARY KEY ( Id )
);
INSERT INTO BugTracker.Projects (ProjectName) VALUES ("Project 1");
INSERT INTO BugTracker.Projects (ProjectName) VALUES ("Project 2");
INSERT INTO BugTracker.Projects (ProjectName) VALUES ("Project 3");
INSERT INTO BugTracker.Projects (ProjectName) VALUES ("Project 4");
INSERT INTO BugTracker.Projects (ProjectName) VALUES ("Project 5");
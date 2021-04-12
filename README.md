"# rekrutacja-school" 

## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [API](#api)

## General info
Recrutation task
	
## Technologies
Project is created with:
* Java version: 11
* Spring Boot version: 2.4.4
* MySQL 8.0

	
## API

* GET /student/findById/{studentId} - returns Student by given ID if exists;

* POST /student/save - adds Student to database;

* PUT /student/update/{studentId} - updates Student with given ID;

* DELETE /student/remove - removes Student with given ID if exists;

* POST /student/addTeacher - adds Teacher to Student (relationship Many To Many);

* GET /student/findByTeacher/{teacherId} - returns Page<Student> result list with reationship with given Teacher; Result can be paginated and sorted by id, firstName, lastName, age, email, specialization;

* GET /student/findByFirstName/{firstName} - returns Page<Student> result list with given first name; Result can be paginated and sorted by id, firstName, lastName, age, email, specialization;

* GET /student/findByLastName/{lastName} - returns Page<Student> result list with given last name; Result can be paginated and sorted by id, firstName, lastName, age, email, specialization;

* GET /student/all - returns all students from database; Result can be paginated and sorted by id, firstName, lastName, age, email, specialization;

* GET /teacher/findById/{teacherId} - returns Teacher by given ID if exists;

* POST /teacher/save - adds Teacher to database;

* PUT /teacher/update/{teacherId} - updates Teacher with given ID;

* DELETE /teacher/remove - removes Teacher with given ID if exists;

* POST /teacher/addStudent - adds Student to Teacher (relationship Many To Many);

* GET /teacher/findByStudent/{teacherId} - returns Page<Teacher> result list with reationship with given Student; Result can be paginated and sorted by id, firstName, lastName, age, email, subject;

* GET /teacher/findByFirstName/{firstName} - returns Page<Teacher> result list with given first name; Result can be paginated and sorted by id, firstName, lastName, age, email, subject;

* GET /teacher/findByLastName/{lastName} - returns Page<Teacher> result list with given last name; Result can be paginated and sorted by id, firstName, lastName, age, email, subject;

* GET /teacher/all - returns all teachers from database; Result can be paginated and sorted by id, firstName, lastName, age, email, subject;



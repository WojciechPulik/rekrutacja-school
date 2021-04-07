package pl.wpulik.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import pl.wpulik.school.model.Student;
import pl.wpulik.school.model.Teacher;
import pl.wpulik.school.service.StudentService;
import pl.wpulik.school.service.TeacherService;

@RestController
public class HomeController {
	
	private TeacherService teacherService;
	private StudentService studentService;
	
	public HomeController() {}
	
	@Autowired
	public HomeController(TeacherService teacherService, StudentService studentService) {
		this.teacherService = teacherService;
		this.studentService = studentService;
	}
	
	@PostMapping("/student/save")
	public ResponseEntity<Student> addStudent(@RequestBody Student student){
		try {
			Student _student = studentService.addStudent(student);
			return new ResponseEntity<>(_student, HttpStatus.CREATED);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/teacher/save")
	public ResponseEntity<Teacher> addTeacher(@RequestBody Teacher teacher){
		try {
			Teacher _teacher = teacherService.addTeacher(teacher);
			return new ResponseEntity<>(_teacher, HttpStatus.CREATED);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	

}

package pl.wpulik.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@DeleteMapping("/student/remove")
	public ResponseEntity<String> removeStudent(@RequestParam Long studentId){
		try {
			studentService.removeStudent(studentId);
			return new ResponseEntity<>("Student has been removed", HttpStatus.OK);
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
	
	@DeleteMapping("/teacher/remove")
	public ResponseEntity<String> removeTeacher(@RequestParam Long teacherId){
		try {
			teacherService.removeTeacher(teacherId);
			return new ResponseEntity<>("Teacher has been removed", HttpStatus.OK);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/teacher/all")
	public ResponseEntity<Page<Teacher>> fetchAllTeachers(Pageable pageable){
		try {
			Page<Teacher> pageTeachers = teacherService.findAllPaginated(pageable);
			return new ResponseEntity<>(pageTeachers, HttpStatus.FOUND);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/student/all")
	public ResponseEntity<Page<Student>> fetchAllStudents(Pageable pageable){
		try {
			Page<Student> pageStudents = studentService.findAllPaginated(pageable);
			return new ResponseEntity<>(pageStudents, HttpStatus.FOUND);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	

}

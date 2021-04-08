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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pl.wpulik.school.model.Student;
import pl.wpulik.school.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {
	
	private StudentService studentService;
	
	public StudentController() {}

	@Autowired
	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}
	
	@PostMapping("/save")
	public ResponseEntity<Student> addStudent(@RequestBody Student student){
		try {
			Student _student = studentService.addStudent(student);
			return new ResponseEntity<>(_student, HttpStatus.CREATED);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/remove")
	public ResponseEntity<Long> removeStudent(@RequestParam Long studentId){
		try {
			boolean isRemoved = studentService.removeStudent(studentId);
			if(isRemoved)
				return new ResponseEntity<>(studentId, HttpStatus.OK);
			else
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/all")
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

















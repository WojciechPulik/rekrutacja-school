package pl.wpulik.school.controller;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pl.wpulik.school.dto.StudentDto;
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
	
	@GetMapping("/findById/{studentId}")
	public ResponseEntity<StudentDto> getStudentById(@PathVariable Long studentId){
		try {
			Student student = studentService.getById(studentId);
			return new ResponseEntity<>(StudentDto.mapToDto(student), HttpStatus.FOUND);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/save")
	public ResponseEntity<StudentDto> addStudent(@RequestBody StudentDto studentDto){
		try {
			Student student = studentService.addStudent(StudentDto.mapToEntity(studentDto));
			return new ResponseEntity<>(StudentDto.mapToDto(student), HttpStatus.CREATED);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/update/{studentId}")
	public ResponseEntity<StudentDto> updateStudent(@PathVariable Long studentId, @RequestBody StudentDto studentDto){
		try {
			Student student = studentService.updateStudent(studentId, StudentDto.mapToEntity(studentDto));
			return new ResponseEntity<>(StudentDto.mapToDto(student), HttpStatus.OK);				
		}catch (NoSuchElementException e1) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}catch (Exception e2) {
			System.err.println(e2.getMessage());
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
	
	@PostMapping("/addTeacher")
	public ResponseEntity<StudentDto> addTeacherToStudent(@RequestParam Long teacherId, @RequestParam Long studentId){
		try {
			Student student = studentService.addTeacherToStudent(teacherId, studentId);
			return new ResponseEntity<>(StudentDto.mapToDto(student), HttpStatus.OK);
		}catch(Exception e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/findByTeacher/{teacherId}")
	public ResponseEntity<Page<StudentDto>> getStudentsByTeacherId(Pageable pageable, @PathVariable Long teacherId ){
		try {
			Page<StudentDto> students = studentService.findStudentsByTeacherId(pageable, teacherId);
			return new ResponseEntity<>(students, HttpStatus.FOUND);		
		}catch (NoSuchElementException e1) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);	
		} catch (Exception e2) {
			System.err.println(e2.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/findByFirstName/{firstName}")
	public ResponseEntity<Page<StudentDto>> getStudentsByFirstName(Pageable pageable, @PathVariable String firstName){
		try {
			Page<StudentDto> students = studentService.findByFirstName(pageable, firstName);
			return new ResponseEntity<>(students, HttpStatus.FOUND);
		}catch (NoSuchElementException e1) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);	
		} catch (Exception e2) {
			System.err.println(e2.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/findByLastName/{lastName}")
	public ResponseEntity<Page<StudentDto>> getStudentsByLastName(Pageable pageable, @PathVariable String lastName){
		try {
			Page<StudentDto> students = studentService.findByLastName(pageable, lastName);
			return new ResponseEntity<>(students, HttpStatus.FOUND);
		}catch (NoSuchElementException e1) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);	
		} catch (Exception e2) {
			System.err.println(e2.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/all")
	public ResponseEntity<Page<StudentDto>> fetchAllStudents(Pageable pageable){
		try {
			Page<StudentDto> pageStudents = studentService.findAllPaginated(pageable);
			return new ResponseEntity<>(pageStudents, HttpStatus.FOUND);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	

}


















package pl.wpulik.school.controller;

import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pl.wpulik.school.dto.TeacherDto;
import pl.wpulik.school.model.Teacher;
import pl.wpulik.school.service.TeacherService;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
	
	private TeacherService teacherService;
	
	public TeacherController() {}

	@Autowired
	public TeacherController(TeacherService teacherService) {
		this.teacherService = teacherService;
	}
	
	@GetMapping("/findById/{teacherId}")
	public ResponseEntity<TeacherDto> getTeacherById(@PathVariable Long teacherId){
		try {
			Teacher teacher = teacherService.getById(teacherId);
			return new ResponseEntity<>(TeacherDto.mapToDto(teacher), HttpStatus.FOUND);			
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/save")
	public ResponseEntity<TeacherDto> addTeacher(@Valid @RequestBody TeacherDto teacherDto, BindingResult result){
		validationErrorMessages(result);
		try {
			Teacher teacher = teacherService.addTeacher(TeacherDto.mapToEntity(teacherDto));
			return new ResponseEntity<>(TeacherDto.mapToDto(teacher), HttpStatus.OK);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/update/{teacherId}")
	public ResponseEntity<TeacherDto> updateTeacher(@PathVariable Long teacherId, @Valid @RequestBody TeacherDto teacherDto,
			BindingResult result){
		validationErrorMessages(result);
		try {
			Teacher teacher = teacherService.updateTeacher(teacherId, TeacherDto.mapToEntity(teacherDto));
			return new ResponseEntity<>(TeacherDto.mapToDto(teacher), HttpStatus.OK);		
		}catch (NoSuchElementException e1) {
			System.err.println(e1.getMessage());
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);		
		}catch (Exception e2) {
			System.err.println(e2.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	private void validationErrorMessages(BindingResult result) {
		if (result.hasErrors()) {
	        List<ObjectError> errors = result.getAllErrors();
	        errors.forEach(err -> System.out.println(err.getDefaultMessage()));
	    }
	}
	
	@DeleteMapping("/remove")
	public ResponseEntity<Long> removeTeacher(@RequestParam Long teacherId){
		try {
			boolean isRemoved = teacherService.removeTeacher(teacherId);
			if(isRemoved)
				return new ResponseEntity<>(teacherId, HttpStatus.OK);
			else
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/addStudent")
	public ResponseEntity<TeacherDto> addStudentToTeacher(@RequestParam Long studentId, @RequestParam Long teacherId){
		try {
			Teacher teacher = teacherService.addStudentToTeacher(studentId, teacherId);
			return new ResponseEntity<>(TeacherDto.mapToDto(teacher), HttpStatus.OK);			
		} catch(Exception e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/findByStudent/{studentId}")
	public ResponseEntity<Page<TeacherDto>> getTeachersByStudentId(Pageable pageable, @PathVariable Long studentId){
		try {
			Page<TeacherDto> teachers = teacherService.findTeachersByStudentId(pageable, studentId);
			return new ResponseEntity<>(teachers, HttpStatus.FOUND);
		} catch (NoSuchElementException e1) {
			System.err.println(e1.getMessage());
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);	
		} catch (Exception e2) {
			System.err.println(e2.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/findByFirstName/{firstName}")
	public ResponseEntity<Page<TeacherDto>> getTeachersByFirstName(Pageable pageable, @PathVariable String firstName){
		try {
			Page<TeacherDto> teachers = teacherService.findTeacherByFirstName(pageable, firstName);
			return new ResponseEntity<>(teachers, HttpStatus.FOUND);
		}
		 catch (NoSuchElementException e1) {
			System.err.println(e1.getMessage());
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);	
		} catch (Exception e2) {
			System.err.println(e2.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/findByLastName/{lastName}")
	public ResponseEntity<Page<TeacherDto>> getTeachersByLastName(Pageable pageable, @PathVariable String lastName){
		try {
			Page<TeacherDto> teachers = teacherService.findTeacherByLastName(pageable, lastName);
			return new ResponseEntity<>(teachers, HttpStatus.FOUND);
		}
		 catch (NoSuchElementException e1) {
			System.err.println(e1.getMessage());
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);	
		} catch (Exception e2) {
			System.err.println(e2.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/all")
	public ResponseEntity<Page<TeacherDto>> fetchAllTeachers(Pageable pageable){
		try {
			Page<TeacherDto> pageTeachers = teacherService.findAllPaginated(pageable);
			return new ResponseEntity<>(pageTeachers, HttpStatus.FOUND);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	
	

}

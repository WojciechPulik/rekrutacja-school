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
	
	@PostMapping("/save")
	public ResponseEntity<Teacher> addTeacher(@RequestBody Teacher teacher){
		try {
			Teacher _teacher = teacherService.addTeacher(teacher);
			return new ResponseEntity<>(_teacher, HttpStatus.OK);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
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
	
	@GetMapping("/all")
	public ResponseEntity<Page<Teacher>> fetchAllTeachers(Pageable pageable){
		try {
			Page<Teacher> pageTeachers = teacherService.findAllPaginated(pageable);
			return new ResponseEntity<>(pageTeachers, HttpStatus.FOUND);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	
	

}

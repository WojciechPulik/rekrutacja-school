package pl.wpulik.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

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
	
	
	

}

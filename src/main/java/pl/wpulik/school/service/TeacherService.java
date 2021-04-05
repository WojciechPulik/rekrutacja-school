package pl.wpulik.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.wpulik.school.repository.TeacherRepository;

@Service
public class TeacherService {
	
	private TeacherRepository teacherRepository;
	
	public TeacherService() {}

	@Autowired
	public TeacherService(TeacherRepository teacherRepository) {
		this.teacherRepository = teacherRepository;
	}
	
	
	
	

}

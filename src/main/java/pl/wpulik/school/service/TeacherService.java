package pl.wpulik.school.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.wpulik.school.model.Teacher;
import pl.wpulik.school.repository.TeacherRepository;

@Service
@Transactional
public class TeacherService {
	
	private TeacherRepository teacherRepository;
	
	public TeacherService() {}

	@Autowired
	public TeacherService(TeacherRepository teacherRepository) {
		this.teacherRepository = teacherRepository;
	}
	
	public Teacher addTeacher(Teacher teacher) {
		return teacherRepository.save(teacher);
	}
	
	
	
	

}

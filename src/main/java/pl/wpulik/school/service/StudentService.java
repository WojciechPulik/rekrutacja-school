package pl.wpulik.school.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.wpulik.school.repository.StudentRepository;

@Service
@Transactional
public class StudentService {
	
	private StudentRepository studentRepository;
	
	public StudentService() {}

	@Autowired
	public StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}
	
	

}

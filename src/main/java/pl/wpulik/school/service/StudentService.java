package pl.wpulik.school.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import pl.wpulik.school.model.Student;
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
	
	public Student addStudent(Student student) {
		return studentRepository.save(student);
	}
	
	public boolean removeStudent(Long id) {
		boolean exists = studentRepository.existsById(id);
		if(exists)
			studentRepository.deleteById(id);
		return exists;
	}
	
	public Page<Student> findAllPaginated(Pageable pageable){
		return studentRepository.findAll(pageable);
	}

}

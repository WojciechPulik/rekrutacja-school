package pl.wpulik.school.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	
	public Teacher updateTeacher(Long id, Teacher teacher) {
		Teacher teacherToUpdate = teacherRepository.findById(id).get();
		teacherToUpdate.setId(id);
		if(teacher.getFirstName() != null)
			teacherToUpdate.setFirstName(teacher.getFirstName());
		if(teacher.getLastName() != null)
			teacherToUpdate.setLastName(teacher.getLastName());
		if(teacher.getAge() != null)
			teacherToUpdate.setAge(teacher.getAge());
		if(teacher.getEmail() != null)
			teacherToUpdate.setEmail(teacher.getEmail());
		if(teacher.getSubject() != null)
			teacherToUpdate.setSubject(teacher.getSubject());
		return teacherRepository.save(teacherToUpdate);
	}
	
	public boolean removeTeacher(Long id) {
		boolean exists = teacherRepository.existsById(id);
		if(exists)
			teacherRepository.deleteById(id);
		return exists;
	}
	
	public Page<Teacher> findAllPaginated(Pageable pageable){
		return teacherRepository.findAll(pageable);
	}
	
	
	
	

}

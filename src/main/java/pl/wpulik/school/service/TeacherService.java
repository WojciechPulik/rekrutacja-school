package pl.wpulik.school.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import pl.wpulik.school.dto.TeacherDto;
import pl.wpulik.school.model.Student;
import pl.wpulik.school.model.Teacher;
import pl.wpulik.school.repository.StudentRepository;
import pl.wpulik.school.repository.TeacherRepository;

@Service
@Transactional
public class TeacherService {
	
	private TeacherRepository teacherRepository;
	private StudentRepository studentRepository;
	
	public TeacherService() {}

	@Autowired
	public TeacherService(TeacherRepository teacherRepository, StudentRepository studentRepository) {
		this.teacherRepository = teacherRepository;
		this.studentRepository = studentRepository;
	}
	
	public Teacher getById(Long teacherId) {
		return teacherRepository.getOne(teacherId);
	}
	
	public Teacher addTeacher(Teacher teacher) {
		return teacherRepository.save(teacher);
	}
	
	public Teacher updateTeacher(Long id, Teacher teacher) {
		Teacher teacherToUpdate = teacherRepository.findById(id).get();
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
	
	public Teacher addStudentToTeacher(Long studentId, Long teacherId) {
		Student student = studentRepository.findById(studentId).get();
		Teacher teacher = teacherRepository.findById(teacherId).get();
		teacher.getStudents().add(student);
		return teacher;
	}
	
	public Page<TeacherDto> findTeachersByStudentId(Pageable pageable, Long studentId){
		return teacherRepository.findAllByStudentId(pageable, studentId)
				.map(TeacherDto::mapToDto);
	}
	
	public Page<TeacherDto> findTeacherByFirstName(Pageable pageable, String firstName){
		return teacherRepository.findByFirstName(pageable, firstName)
				.map(TeacherDto::mapToDto);
	}
	
	public Page<TeacherDto> findTeacherByLastName(Pageable pageable, String lastName){
		return teacherRepository.findByLastName(pageable, lastName)
				.map(TeacherDto::mapToDto);
	}
 	
	public Page<TeacherDto> findAllPaginated(Pageable pageable){
		Page<Teacher> teachers = teacherRepository.findAll(pageable);
		Page<TeacherDto> teachersDto = teachers.map(TeacherDto::mapToDto);
		return teachersDto;
	}
	
	
	
	

}

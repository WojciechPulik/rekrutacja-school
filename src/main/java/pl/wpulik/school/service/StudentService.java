package pl.wpulik.school.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import pl.wpulik.school.dto.StudentDto;
import pl.wpulik.school.model.Student;
import pl.wpulik.school.model.Teacher;
import pl.wpulik.school.repository.StudentRepository;
import pl.wpulik.school.repository.TeacherRepository;

@Service
@Transactional
public class StudentService {
	
	private StudentRepository studentRepository;
	private TeacherRepository teacherRepository;
	
	public StudentService() {}

	@Autowired
	public StudentService(StudentRepository studentRepository, TeacherRepository teacherRepository) {
		this.studentRepository = studentRepository;
		this.teacherRepository = teacherRepository;
	}
	
	public Student getById(Long studentId) {
		return studentRepository.getOne(studentId);
	}
	
	public Student addStudent(Student student) {
		return studentRepository.save(student);
	}
	
	public Student updateStudent(Long id, Student student) {
		Student studentToUpdate = studentRepository.findById(id).get();
		if(student.getFirstName() != null)
			studentToUpdate.setFirstName(student.getFirstName());
		if(student.getLastName() != null)
			studentToUpdate.setLastName(student.getLastName());
		if(student.getAge() != null)
			studentToUpdate.setAge(student.getAge());
		if(student.getEmail() != null)
			studentToUpdate.setEmail(student.getEmail());
		if(student.getSpecialization() != null)
			studentToUpdate.setSpecialization(student.getSpecialization());
		return studentRepository.save(studentToUpdate);
	}
	
	public boolean removeStudent(Long id) {
		boolean exists = studentRepository.existsById(id);
		if(exists)
			studentRepository.deleteById(id);
		return exists;
	}
	
	public Student addTeacherToStudent(Long teacherId, Long studentId) {
		Student student = studentRepository.findById(studentId).get();
		Teacher teacher = teacherRepository.findById(teacherId).get();
		student.getTeachers().add(teacher);
		return student;
	}
	
	public Page<StudentDto> findStudentsByTeacherId(Pageable pageable, Long teacherId){
		return studentRepository.findAllByTeacherId(pageable, teacherId)
				.map(StudentDto::mapToDto);
	}
	
	public Page<StudentDto> findAllPaginated(Pageable pageable){
		Page<Student> students = studentRepository.findAll(pageable);
		Page<StudentDto> studentsDto = students.map(StudentDto::mapToDto);
		return studentsDto;
	}

}

package pl.wpulik.school.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pl.wpulik.school.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{

	Page <Student> findAll(Pageable pageable);
	
	Page <Student> findByFirstName(Pageable pageable, String firstName);
	
	@Query("SELECT s FROM student s JOIN s.teachers t WHERE t.id = :teacherId ")
	Page <Student> findAllByTeacherId(Pageable pageable, @Param("teacherId") Long teacherId);
}

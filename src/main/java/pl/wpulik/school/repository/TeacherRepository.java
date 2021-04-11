package pl.wpulik.school.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pl.wpulik.school.model.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
	
	Page<Teacher> findAll(Pageable pageable);
	
	@Query("SELECT t FROM teacher t JOIN t.students s WHERE s.id = :studentId ")
	Page <Teacher> findAllByStudentId(Pageable pageable, @Param("studentId") Long studentId);

}

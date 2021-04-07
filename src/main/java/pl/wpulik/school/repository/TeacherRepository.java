package pl.wpulik.school.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.wpulik.school.model.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
	
	Page<Teacher> findAll(Pageable pageable);

}

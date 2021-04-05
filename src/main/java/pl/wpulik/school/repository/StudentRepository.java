package pl.wpulik.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.wpulik.school.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{

}

package pl.wpulik.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.wpulik.school.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{

}

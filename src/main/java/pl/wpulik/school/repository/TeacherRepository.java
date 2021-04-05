package pl.wpulik.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.wpulik.school.model.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

}

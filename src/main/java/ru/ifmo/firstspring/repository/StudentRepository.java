package ru.ifmo.firstspring.repository;

import org.springframework.data.repository.CrudRepository;
import ru.ifmo.firstspring.entity.Student;

public interface StudentRepository extends CrudRepository<Student, Integer> {
}

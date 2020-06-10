package ru.ifmo.firstspring.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.ifmo.firstspring.entity.University;

import java.util.Optional;

public interface UniversityRepository extends CrudRepository<University, Integer>,
        JpaSpecificationExecutor<University> {

    // SELECT * FROM table WHERE [спецификация]
    // название таблицы spring получит из JpaSpecificationExecutor<University>
    // получение одной записи(спецификация)
    // получение нескольких записей(спецификация)
    // получение количества(спецификация)

    // JPQL
    @Query("SELECT u FROM University u WHERE u.universityName = :universityName")
    Optional<University> findByName(@Param("universityName") String universityName);
}



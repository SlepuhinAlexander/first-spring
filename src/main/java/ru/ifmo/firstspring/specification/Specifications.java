package ru.ifmo.firstspring.specification;

import org.springframework.data.jpa.domain.Specification;
import ru.ifmo.firstspring.entity.Event;
import ru.ifmo.firstspring.entity.Student;
import ru.ifmo.firstspring.entity.University;
import ru.ifmo.firstspring.entity.University_;

import javax.persistence.criteria.*;
import java.time.LocalDateTime;

public class Specifications {

    // SELECT * FROM table WHERE [спецификация]
    // название таблицы spring получит из JpaSpecificationExecutor<University>
    // получение одной записи(спецификация)
    // получение нескольких записей(спецификация)
    // получение количества(спецификация)

    // SELECT * FROM event WHERE eventTitle LIKE %текст%
    public static Specification<Event> inTitle(final String inTitle){
        return (Specification<Event>) (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.like(root.get("eventTitle"), "%"+inTitle+"%");
    }

    // SELECT * FROM event WHERE eventStart BETWEEN from AND to;
    public static Specification<Event> eventsByDates(final LocalDateTime from,
                                                     final LocalDateTime to){
        return (Specification<Event>) (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.between(root.get("eventStart"), from, to);
    }

    // SELECT count(student.id)
    // FROM university
    // JOIN student
    // ON ...
    // WHERE universityName = uName;
    public static Specification<University> studentCount(String uName){

        return (Specification<University>) (root, criteriaQuery, criteriaBuilder) -> {
            CriteriaQuery<Long> longQuery = criteriaBuilder.createQuery(Long.class);

            Join<University, Student> studentJoin = root.join(University_.students);

            Predicate condition = criteriaBuilder
                    .equal(root.get(University_.universityName), uName);

            longQuery.select(criteriaBuilder.count(studentJoin))
                    .where(condition)
                    .groupBy(root.get("id"));

            return longQuery.getRestriction();


/*   uName = Q
            id uName   id sName u_id
            1   Q       1   dd    1   1
            2   F       3   kk    2   1
            1   Q       3   hh    1   2
*/


        };

    }

}

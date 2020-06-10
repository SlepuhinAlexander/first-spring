package ru.ifmo.firstspring.specification;

import org.springframework.data.jpa.domain.Specification;
import ru.ifmo.firstspring.entity.Event;

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

}

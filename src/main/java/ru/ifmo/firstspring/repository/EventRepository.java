package ru.ifmo.firstspring.repository;

import org.springframework.data.repository.CrudRepository;
import ru.ifmo.firstspring.entity.Event;

public interface EventRepository extends CrudRepository<Event, Integer> {
    // методы CrudRepository
    // добавление / обновление
    // удаление
    // извлечение всех записей
    // извлечение по первичному ключу
}

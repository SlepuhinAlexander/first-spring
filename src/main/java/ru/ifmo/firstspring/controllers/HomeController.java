package ru.ifmo.firstspring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.ifmo.firstspring.repository.EventRepository;
import ru.ifmo.firstspring.repository.UniversityRepository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static ru.ifmo.firstspring.specification.Specifications.*;

@Controller // bean beanContainer
public class HomeController  {

    private EventRepository eventRepository;
    private UniversityRepository universityRepository;

    @Autowired
    public HomeController(EventRepository eventRepository, UniversityRepository universityRepository) {
        this.eventRepository = eventRepository;
        this.universityRepository = universityRepository;
    }

    @RequestMapping("/")
    public String index(){
        System.out.println("---inTitle---");
        // SELECT * FROM event WHERE eventTitle LIKE %текст%
        eventRepository.findAll(inTitle("java")).forEach(System.out::println);

        System.out.println("---byDates---");
        // SELECT * FROM event WHERE eventStart BETWEEN from AND to;
        LocalDateTime from = LocalDateTime.now().minus(2, ChronoUnit.WEEKS);
        LocalDateTime to = LocalDateTime.now().plus(2, ChronoUnit.WEEKS);

        eventRepository.findAll(eventsByDates(from, to)).forEach(System.out::println);

        // SELECT * FROM event WHERE условие OR/AND условие
        eventRepository.findAll(Specification.where(
                inTitle("java").and(eventsByDates(from, to))
        )).forEach(System.out::println);

        System.out.println("---studentsCount---");
        System.out.println(universityRepository.count(studentCount("ИТМО")));

        return "index";
    }


}

/*
html    контроллер(репозиторий)    данные

html
контроллер(сервис)
сервис(репозиторий) - обработка данных
данные
*/

/*
клиент -> сообщение -> сервер
клиент <- сообщение <- сервер

сообщение:
    строка запроса / статус
    HTTP заголовки
    тело сообщения

"/" | spring
*/

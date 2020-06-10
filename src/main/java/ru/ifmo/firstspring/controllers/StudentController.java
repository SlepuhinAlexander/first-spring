package ru.ifmo.firstspring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.ifmo.firstspring.entity.Event;
import ru.ifmo.firstspring.entity.Student;
import ru.ifmo.firstspring.entity.University;
import ru.ifmo.firstspring.repository.EventRepository;
import ru.ifmo.firstspring.repository.StudentRepository;
import ru.ifmo.firstspring.repository.UniversityRepository;

@Controller
@RequestMapping("/student")
public class StudentController {

    private StudentRepository studentRepository;
    private UniversityRepository universityRepository;
    private EventRepository eventRepository;

    @Autowired
    public StudentController(StudentRepository studentRepository, UniversityRepository universityRepository, EventRepository eventRepository) {
        this.studentRepository = studentRepository;
        this.universityRepository = universityRepository;
        this.eventRepository = eventRepository;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String showForm(Model model){
        model.addAttribute("student", new Student());
        model.addAttribute("universities", universityRepository.findAll());
        model.addAttribute("events", eventRepository.findAll());
        return "add_student";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addStudent(
            @ModelAttribute Student student,
            @RequestParam(name = "universityId") int universityId,
            @RequestParam(name = "eventId") int eventId

    ){


        University university =
                universityRepository.findById(universityId).get();
        Event event = eventRepository.findById(eventId).get();

        // репозиторий : boolean existsById(ID id);
        // результат-Optional : ifPresent()
        // 1 qwe uniq 2
        // 2 qwe 2
        // получение студента из бд
        student.getEvents().add(event);
        student.setUniversity(university);

        university.getStudents().add(student);
        event.getStudents().add(student);

        studentRepository.save(student);
        return "redirect:/student/add";
    }


}

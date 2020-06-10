package ru.ifmo.firstspring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.ifmo.firstspring.entity.University;
import ru.ifmo.firstspring.repository.UniversityRepository;

@Controller
@RequestMapping("/university")
public class UniversityController {
//    @Autowired
    private UniversityRepository repository;

    @Autowired
    public UniversityController(UniversityRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String showForm(Model model){
        // в html передается объект new University(),
        // к которому мы с можем обраться по имени university
        model.addAttribute("university", new University());
        return "add_university";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addUniversity(@ModelAttribute University university){
        repository.save(university); // добавление объекта в бд
        return "redirect:/university/add";
    }

    @RequestMapping(value = "/show")
    public String showData(Model model){
        model.addAttribute("universities", repository.findAll());
        return "universities";
    }


}




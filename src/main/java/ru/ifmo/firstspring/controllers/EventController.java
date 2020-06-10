package ru.ifmo.firstspring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.ifmo.firstspring.entity.Event;
import ru.ifmo.firstspring.repository.EventRepository;

@Controller
@RequestMapping(value = "/event")
public class EventController {

    private EventRepository eventRepository;

    @Autowired
    public EventController(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String showForm(Model model){
        model.addAttribute("event", new Event());
        return "add_event";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String submitForm(@ModelAttribute Event event){
        eventRepository.save(event);
        return "redirect:/event/add";
    }


    @RequestMapping(value = "/show")
    public String showData(Model model){
        model.addAttribute("events", eventRepository.findAll());
        return "events";
    }

}

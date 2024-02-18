package com.example.Homework_ValidationForm1;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ActorController {

    private ActorRepository actorRepository;
    private NationalityRepository nationalityRepository;

    public ActorController(ActorRepository actorRepository, NationalityRepository nationalityRepository) {
        this.actorRepository = actorRepository;
        this.nationalityRepository = nationalityRepository;
    }

    @GetMapping("/actors/add")
    public String showAddActorForm(Model model) {
        Iterable<Nationality> nationalities = nationalityRepository.findAll();
        model.addAttribute("actor", new Actor());
        model.addAttribute("nationalities", nationalities);
        return "add-actor";
    }

    @PostMapping("/actors/add")
    public String addActor(@ModelAttribute Actor actor) {
        actorRepository.save(actor);

        return "redirect:/actors";
    }

    @GetMapping("/actors")
    public String showActors(Model model) {
        Iterable<Actor> actors = actorRepository.findAll();
        model.addAttribute("actors", actors);
        return "actors";
    }
}

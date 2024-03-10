package com.example.Homework_ValidationForm1;

import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ActorController {

    private ActorRepository actorRepository;
    private NationalityRepository nationalityRepository;

    public ActorController(ActorRepository actorRepository, NationalityRepository nationalityRepository) {
        this.actorRepository = actorRepository;
        this.nationalityRepository = nationalityRepository;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/actors/add")
    public String showAddActorForm(Model model) {
        Iterable<Nationality> nationalities = nationalityRepository.findAll();
        model.addAttribute("actor", new Actor());
        model.addAttribute("nationalities", nationalities);
        return "add-actor";
    }

    @PostMapping("/actors/add")
    public String addActor(@ModelAttribute @Valid Actor actor, Model model, RedirectAttributes redirectAttributes) {


        try {
            actorRepository.save(actor);
            redirectAttributes.addFlashAttribute("message", "Нов актьор е добавен успешно!");
            return "redirect:/actors";
        } catch (Exception e) {
            model.addAttribute("error", new Error(e.getMessage(), e.getCause()));
            return "index";
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/actors")
    public String showActors(Model model) {
        Iterable<Actor> actors = actorRepository.findAll();
        model.addAttribute("actors", actors);
        return "actors";
    }
}

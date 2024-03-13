package com.example.Homework_ValidationForm1;

import com.example.Homework_ValidationForm1.DTO.ActorDTO;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
public class ActorController {

    private ActorRepository actorRepository;
    private NationalityRepository nationalityRepository;

    @Autowired
    private ActorService actorService;
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
    public String addActor( ActorDTO actorDTO, Model model, RedirectAttributes redirectAttributes) {


        System.out.println();
        try {
            Actor actor = actorService.toActor(actorDTO);

            actorRepository.save(actor);
            redirectAttributes.addFlashAttribute("message", "Нов актьор е добавен успешно!");
            return "redirect:/actors";
        } catch (DataIntegrityViolationException e) {
            // Грешка при запазване в базата данни
            model.addAttribute("error", "Възникна грешка при добавяне на актьора!");
            return "index";
        } catch (Exception e) {
            model.addAttribute("error", new Error(e.getMessage(), e.getCause()));
            return "index";
        }
    }


    @GetMapping("/actors")
    public String showActors(Model model) {
        Iterable<Actor> actors = actorRepository.findAll();

        List<ActorDTO> actorDTOs = actorService.toActorDTOs(actors);

        model.addAttribute("actors", actorDTOs);
        return "actors";
    }
}

package com.example.Homework_ValidationForm1;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;

@Controller
public class FilmController {
    private FilmRepository filmRepository;
    private ActorRepository actorRepository;

    public FilmController(FilmRepository filmRepository, ActorRepository actorRepository) {
        this.filmRepository = filmRepository;
        this.actorRepository = actorRepository;
    }

    @GetMapping("/films/add")
    public String showAddFilmForm(Model model){
        Iterable<Actor> actors = actorRepository.findAll();
        model.addAttribute("film", new Film());
        model.addAttribute("actors", actors);
        return "add-film";
    }

    @PostMapping("/films/add")
    public String addFilm(@ModelAttribute Film film,
                          HttpServletRequest request, RedirectAttributes redirectAttributes, BindingResult bindingResult){
        String[] actorIdsStr = request.getParameterValues("actors");
        Set<Actor> actors = new HashSet<>();

        for (String actorIdStr : actorIdsStr
             ) {
            Long actorId = Long.parseLong(actorIdStr);
            Optional<Actor> optionalActor = actorRepository.findById(actorId);
            if (optionalActor != null) {
                var actor = optionalActor.get();
                actors.add(actor);
            }
        }

        film.setActors(actors);
        if (film.getActors().size() > 4) {
            System.out.println("Максималният брой актьори е 4.");
            redirectAttributes.addFlashAttribute("errorMessage", "Максималният брой актьори е 4.");
            return "redirect:/films/add";
        }


        filmRepository.save(film);
        return "redirect:/films";
    }

    @GetMapping("/films")
    public String getAllFilms(Model model) {
        Iterable<Film> films = filmRepository.findAll();
        model.addAttribute("films", films);
        return "films";
    }
}

package com.example.Homework_ValidationForm1;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class NationalityController {

    private NationalityRepository nationalityRepository;

    public NationalityController(NationalityRepository nationalityRepository) {
        this.nationalityRepository = nationalityRepository;
    }

    @GetMapping("/nationalities/add")
    public String showAddNationalityForm(Model model) {
        model.addAttribute("nationality", new Nationality());
        return "add-nationality";
    }

    @PostMapping("/nationalities/add")
    public String addNationality(@ModelAttribute Nationality nationality) {
        this.nationalityRepository.save(nationality);
        return "redirect:/nationalities";
    }

    @GetMapping("/nationalities")
    public String showNationalities(Model model) {
        Iterable<Nationality> nationalities = nationalityRepository.findAll();
        model.addAttribute("nationalities", nationalities);
        return "nationalities";
    }
}

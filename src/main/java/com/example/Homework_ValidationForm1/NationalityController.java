package com.example.Homework_ValidationForm1;

import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class NationalityController {

    private NationalityRepository nationalityRepository;

    public NationalityController(NationalityRepository nationalityRepository) {
        this.nationalityRepository = nationalityRepository;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/nationalities/add")
    public String showAddNationalityForm(Model model) {
        model.addAttribute("nationality", new Nationality());
        return "add-nationality";
    }

    @PostMapping("/nationalities/add")
    public String addNationality(@Valid @ModelAttribute Nationality nationality,
                                 BindingResult bindingResult, Model model) {

            if (bindingResult.hasErrors()) {
                List<FieldError> populationErrors = bindingResult.getFieldErrors("population");
                List<FieldError> nameErrors = bindingResult.getFieldErrors("name");

                model.addAttribute(populationErrors);
                model.addAttribute(nameErrors);
                return "add-nationality";
            }
            this.nationalityRepository.save(nationality);
//            redirectAttributes.addFlashAttribute("message", "Нова националност/държава е добавена успешно!");
            return "redirect:/nationalities";


    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/nationalities")
    public String showNationalities(Model model) {
        Iterable<Nationality> nationalities = nationalityRepository.findAll();
        model.addAttribute("nationalities", nationalities);
        return "nationalities";
    }
}

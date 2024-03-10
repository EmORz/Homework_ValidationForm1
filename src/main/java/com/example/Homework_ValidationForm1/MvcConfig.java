package com.example.Homework_ValidationForm1;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/actors/add").setViewName("add-actor");
        registry.addViewController("/actors").setViewName("actors");
        registry.addViewController("/films").setViewName("films");
        registry.addViewController("/films/add").setViewName("add-film");
        registry.addViewController("/nationalities/add").setViewName("add-nationality");
        registry.addViewController("/nationalities").setViewName("nationalities");

        registry.addViewController("/login").setViewName("login");
    }
}

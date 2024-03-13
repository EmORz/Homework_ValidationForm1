package com.example.Homework_ValidationForm1.DTO;

import com.example.Homework_ValidationForm1.Actor;
import com.example.Homework_ValidationForm1.Film;
import com.example.Homework_ValidationForm1.Gender;
import com.example.Homework_ValidationForm1.Nationality;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Set;

public class ActorDTO {

    @NotNull
    @Size(min = 2, max = 50)
    private String first_name;
    @NotNull
    @Size(min = 2, max = 50)
    private String last_name;

    @ManyToOne
    private Nationality nationality;

    @ManyToMany
    private Set<Film> films;

    @Min(1)
    private Integer age;

    @NotNull
    private Gender gender;

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public Nationality getNationality() {
        return nationality;
    }

    public void setNationality(Nationality nationality) {
        this.nationality = nationality;
    }

    public Set<Film> getFilms() {
        return films;
    }

    public void setFilms(Set<Film> films) {
        this.films = films;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public ActorDTO toActorDTO(Actor actor) {
        ActorDTO actorDTO = new ActorDTO();
        actorDTO.setFirst_name(actor.getFirst_name());
        actorDTO.setLast_name(actor.getLast_name());
        // ... задайте другите полета по подобен начин ...
        return actorDTO;
    }
}

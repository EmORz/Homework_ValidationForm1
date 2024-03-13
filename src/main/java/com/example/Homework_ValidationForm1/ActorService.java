package com.example.Homework_ValidationForm1;

import com.example.Homework_ValidationForm1.DTO.ActorDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ActorService {

    public Actor toActor(ActorDTO actorDTO) {
        Actor actor = new Actor();
        actor.setFirst_name(actorDTO.getFirst_name());
        actor.setLast_name(actorDTO.getLast_name());
        actor.setNationality(actorDTO.getNationality());
        actor.setFilms(actorDTO.getFilms());
        actor.setAge(actorDTO.getAge());
        actor.setGender(actorDTO.getGender());

        return actor;
    }
    public List<ActorDTO> toActorDTOs(Iterable<Actor> actors) {
        List<ActorDTO> actorDTOs = new ArrayList<>();
        for (Actor actor : actors) {
            actorDTOs.add(toActorDTO(actor));  // Използваме метода toActorDTO
        }
        return actorDTOs;
    }

    public ActorDTO toActorDTO(Actor actor) {
        ActorDTO actorDTO = new ActorDTO();
        actorDTO.setFirst_name(actor.getFirst_name());
        actorDTO.setLast_name(actor.getLast_name());
        actorDTO.setNationality(actor.getNationality());
        actorDTO.setFilms(actor.getFilms());
        actorDTO.setAge(actor.getAge());
        actorDTO.setGender(actor.getGender());
        return actorDTO;
    }
}

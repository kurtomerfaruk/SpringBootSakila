package com.kurtomerfaruk.springbootsakila.controllers;

import com.kurtomerfaruk.springbootsakila.models.Actor;
import com.kurtomerfaruk.springbootsakila.services.ActorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Omer Faruk KURT kurtomerfaruk@gmail.com
 * @version 1.0.0
 * @since 14.05.2023 16:27
 */
@RestController
@RequestMapping("/api/actors")
public class ActorController {

    private ActorService actorService;

    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Actor createActor(@RequestBody Actor actor){
        return actorService.saveActor(actor);
    }

    @GetMapping
    public List<Actor> getActors(){
        return actorService.getAllActors();
    }

    @GetMapping("{id}")
    public ResponseEntity<Actor> getActorById(@PathVariable("id") Integer actorId){
        return actorService.getActorId(actorId)
                .map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.notFound().build());
    }

    @PutMapping("{id}")
    public ResponseEntity<Actor> updateActor(@PathVariable("id") Integer actorId, @RequestBody Actor actor){
        return actorService.getActorId(actorId)
                .map(savedActor -> {

                    savedActor.setFirstName(actor.getFirstName());
                    savedActor.setLastName(actor.getLastName());
                    savedActor.setLastUpdate(actor.getLastUpdate());

                    Actor updatedActor = actorService.updateActor(savedActor);
                    return new ResponseEntity<>(updatedActor, HttpStatus.OK);

                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteActor(@PathVariable("id") Integer actorId){

        actorService.deleteActor(actorId);

        return new ResponseEntity<String>("Actor deleted successfully!.", HttpStatus.OK);

    }
}

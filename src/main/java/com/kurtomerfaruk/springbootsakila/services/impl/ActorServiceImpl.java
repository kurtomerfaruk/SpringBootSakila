package com.kurtomerfaruk.springbootsakila.services.impl;

import com.kurtomerfaruk.springbootsakila.models.Actor;
import com.kurtomerfaruk.springbootsakila.services.ActorService;
import com.kurtomerfaruk.springbootsakila.repositories.ActorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Omer Faruk KURT kurtomerfaruk@gmail.com
 * @version 1.0.0
 * @since 14.05.2023 16:16
 */
@Service
public class ActorServiceImpl implements ActorService {

    private ActorRepository actorRepository;

    public ActorServiceImpl(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    @Override
    public Actor saveActor(Actor actor) {
        return actorRepository.save(actor);
    }

    @Override
    public List<Actor> getAllActors() {
        return actorRepository.findAll();
    }

    @Override
    public Optional<Actor> getActorId(Integer id) {
        return actorRepository.findById(id);
    }

    @Override
    public Actor updateActor(Actor actor) {
       return actorRepository.save(actor);
    }

    @Override
    public void deleteActor(Integer id) {
        actorRepository.deleteById(id);
    }
}

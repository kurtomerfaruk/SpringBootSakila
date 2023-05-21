package com.kurtomerfaruk.springbootsakila.services;

import com.kurtomerfaruk.springbootsakila.models.Actor;

import java.util.List;
import java.util.Optional;

/**
 * @author Omer Faruk KURT kurtomerfaruk@gmail.com
 * @version 1.0.0
 * @since 14.05.2023 16:13
 */
public interface ActorService {
    Actor saveActor(Actor actor);
    List<Actor> getAllActors();
    Optional<Actor> getActorId(Integer id);
    Actor updateActor(Actor actor);
    void deleteActor(Integer id);
}

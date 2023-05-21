package com.kurtomerfaruk.springbootsakila.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kurtomerfaruk.springbootsakila.models.Actor;
import com.kurtomerfaruk.springbootsakila.repositories.ActorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * @author Omer Faruk KURT kurtomerfaruk@gmail.com
 * @version 1.0.0
 * @since 14.05.2023 16:43
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ActorControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        //actorRepository.deleteAll();
    }

    @Test
    public void givenActorObject_whenCreateActor_thenReturnSavedActor() throws Exception {
        Actor actor = Actor.builder()
                .firstName("Baran")
                .lastName("Kurt")
                .lastUpdate(new Date())
                .build();

        ResultActions response = mockMvc.perform(
                post("/api/actors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(actor))
        );

        response.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName",is(actor.getFirstName())))
                .andExpect(jsonPath("$.lastName",is(actor.getLastName())));

    }

    @Test
    public void givenActorId_whenGetActorById_thenReturnActorObject() throws Exception{
        // given - precondition or setup
        Actor actor = Actor.builder()
                .firstName("OFaruk")
                .lastName("Kurt")
                .lastUpdate(new Date())
                .build();
        actorRepository.save(actor);

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(get("/api/actors/{id}", actor.getActorId()));

        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.firstName", is(actor.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(actor.getLastName())));

    }

    @Test
    public  void givenInvalidActorId_whenGetActorId_thenReturnEmpty() throws Exception{
        Integer actorId=11111;
        Actor actor = Actor.builder()
                .firstName("Deneme1")
                .lastName("Deneme2")
                .lastUpdate(new Date())
                .build();

        actorRepository.save(actor);

        ResultActions response = mockMvc.perform(get("/api/actors/{id}",actorId));

        response.andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    public void givenUpdatedActor_whenUpdateActor_thenReturnUpdateActorObject() throws Exception{
        Actor savedActor = Actor.builder()
                .firstName("Ali")
                .lastName("Demir")
                .lastUpdate(new Date())
                .build();

        actorRepository.save(savedActor);

        Actor updatedActor = Actor.builder()
                .firstName("Veli")
                .lastName("Demir")
                .lastUpdate(new Date())
                .build();

        ResultActions response = mockMvc.perform(put("/api/actors/{id}",savedActor.getActorId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedActor)));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.firstName",is(updatedActor.getFirstName())))
                .andExpect(jsonPath("$.lastName",is((updatedActor.getLastName()))));

    }

    @Test
    public void givenUpdatedActor_whenUpdatedActor_thenReturn404() throws Exception{
        Integer actorId = 147258;
        Actor savedActor = Actor.builder()
                .firstName("Ali1")
                .lastName("Demir1")
                .lastUpdate(new Date())
                .build();

        actorRepository.save(savedActor);

        Actor updatedActor = Actor.builder()
                .firstName("Veli2")
                .lastName("Demir2")
                .lastUpdate(new Date())
                .build();

        ResultActions response = mockMvc.perform(put("/api/actors/{id}",actorId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedActor)));

        response.andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    public void givenActorId_whenDeleteActor_thenReturn200() throws Exception{
        Actor savedActor = Actor.builder()
                .firstName("Ali12")
                .lastName("Demir12")
                .lastUpdate(new Date())
                .build();

        actorRepository.save(savedActor);

        ResultActions response = mockMvc.perform(delete("/api/actors/{id}",savedActor.getActorId()));

        response.andExpect(status().isOk())
                .andDo(print());
    }
}

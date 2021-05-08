package com.petprojects.articles.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.petprojects.articles.model.Topic;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TopicTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    TopicController topicController;

    @Test
    public void create() throws Exception {
        mockMvc.perform(
                    post("/topic")
                            .content(asJsonString(new Topic(1, "New topic", Collections.emptySet())))
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON)
                    )
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void getAllReturn200() throws Exception {
        mockMvc.perform(get("/topic"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



}

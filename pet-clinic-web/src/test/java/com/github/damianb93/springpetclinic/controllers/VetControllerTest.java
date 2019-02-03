package com.github.damianb93.springpetclinic.controllers;

import com.github.damianb93.springpetclinic.model.Vet;
import com.github.damianb93.springpetclinic.services.VetService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class VetControllerTest {

    @Mock
    VetService service;

    @InjectMocks
    VetController controller;

    private Set<Vet> returnSet;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        returnSet = new HashSet<>();
        returnSet.add(Vet.builder().id(1L).build());
        returnSet.add(Vet.builder().id(2L).build());

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void listVets() throws Exception {

        when(service.findAll()).thenReturn(returnSet);

        mockMvc.perform(get("/vets"))
            .andExpect(status().isOk())
            .andExpect(view().name("vets/index"))
            .andExpect(model().attribute("vets", Matchers.hasSize(2)));
    }

}
package com.github.damianb93.springpetclinic.controllers;

import com.github.damianb93.springpetclinic.model.Pet;
import com.github.damianb93.springpetclinic.services.PetService;
import com.github.damianb93.springpetclinic.services.VisitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class VisitControllerTest {

    @Mock
    PetService petService;

    @Mock
    VisitService visitService;

    @InjectMocks
    VisitController visitController;

    private MockMvc mockMVc;
    private Pet pet;

    @BeforeEach
    void setUp() {
        pet = Pet.builder().id(1L).build();

        mockMVc = MockMvcBuilders.standaloneSetup(visitController).build();
    }

    @Test
    void initCreationForm() throws Exception {
        when(petService.findById(anyLong())).thenReturn(pet);

        mockMVc.perform(get("/owners/1/pets/1/visits/new"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("pet"))
                .andExpect(model().attributeExists("visit"))
                .andExpect(view().name("pets/createOrUpdateVisitForm"));
    }

    @Test
    void processCreationForm() throws Exception {
        when(petService.findById(anyLong())).thenReturn(pet);

        mockMVc.perform(post("/owners/1/pets/1/visits/new"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"));

        verify(visitService).save(any());
    }

}
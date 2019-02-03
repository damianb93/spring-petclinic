package com.github.damianb93.springpetclinic.services.springdatajpa;

import com.github.damianb93.springpetclinic.model.Pet;
import com.github.damianb93.springpetclinic.repositories.PetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PetServiceSDJpaImplTest {

    private final Long OBJ_ID = 1L;

    @Mock
    private PetRepository repository;

    @InjectMocks
    private PetServiceSDJpaImpl service;

    private Pet returnObj;

    @BeforeEach
    void setUp() {
        returnObj = Pet.builder().id(OBJ_ID).build();
    }

    @Test
    void findAll() {
        Set<Pet> returnSet = new HashSet<>();
        returnSet.add(Pet.builder().id(1L).build());
        returnSet.add(Pet.builder().id(2L).build());

        when(repository.findAll()).thenReturn(returnSet);

        Set<Pet> set = service.findAll();

        assertNotNull(set);
        assertEquals(returnSet.size(), set.size());

        verify(repository).findAll();
    }

    @Test
    void findById() {
        when(repository.findById(any())).thenReturn(Optional.of(returnObj));

        Pet obj = service.findById(OBJ_ID);

        assertNotNull(obj);
        assertEquals(OBJ_ID, obj.getId());

        verify(repository).findById(any());
    }

    @Test
    void findByNonExistentId() {
        when(repository.findById(any())).thenReturn(Optional.empty());

        Pet obj = service.findById(OBJ_ID);

        assertNull(obj);

        verify(repository).findById(any());
    }

    @Test
    void save() {
        Pet objToSave = Pet.builder().id(OBJ_ID).build();

        when(repository.save(any())).thenReturn(objToSave);

        Pet savedObj = service.save(objToSave);

        assertNotNull(savedObj);
        assertEquals(OBJ_ID, savedObj.getId());

        verify(repository).save(any());
    }

    @Test
    void delete() {
        service.delete(returnObj);

        verify(repository).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(OBJ_ID);

        verify(repository).deleteById(anyLong());
    }

}
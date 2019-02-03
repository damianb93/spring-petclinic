package com.github.damianb93.springpetclinic.services.springdatajpa;

import com.github.damianb93.springpetclinic.model.Vet;
import com.github.damianb93.springpetclinic.repositories.VetRepository;
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
class VetServiceSDJpaImplTest {

    private final Long OBJ_ID = 1L;

    @Mock
    private VetRepository repository;

    @InjectMocks
    private VetServiceSDJpaImpl service;

    private Vet returnObj;

    @BeforeEach
    void setUp() {
        returnObj = Vet.builder().id(OBJ_ID).build();
    }

    @Test
    void findAll() {
        Set<Vet> returnSet = new HashSet<>();
        returnSet.add(Vet.builder().id(1L).build());
        returnSet.add(Vet.builder().id(2L).build());

        when(repository.findAll()).thenReturn(returnSet);

        Set<Vet> set = service.findAll();

        assertNotNull(set);
        assertEquals(returnSet.size(), set.size());

        verify(repository).findAll();
    }

    @Test
    void findById() {
        when(repository.findById(any())).thenReturn(Optional.of(returnObj));

        Vet obj = service.findById(OBJ_ID);

        assertNotNull(obj);
        assertEquals(OBJ_ID, obj.getId());

        verify(repository).findById(any());
    }

    @Test
    void findByNonExistentId() {
        when(repository.findById(any())).thenReturn(Optional.empty());

        Vet obj = service.findById(OBJ_ID);

        assertNull(obj);

        verify(repository).findById(any());
    }

    @Test
    void save() {
        Vet objToSave = Vet.builder().id(OBJ_ID).build();

        when(repository.save(any())).thenReturn(objToSave);

        Vet savedObj = service.save(objToSave);

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
package com.github.damianb93.springpetclinic.services.map;

import com.github.damianb93.springpetclinic.model.Owner;
import com.github.damianb93.springpetclinic.model.Pet;
import com.github.damianb93.springpetclinic.model.PetType;
import com.github.damianb93.springpetclinic.model.Visit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VisitServiceMapImplTest {

    private VisitServiceMapImpl visitServiceMap;
    private Pet pet;
    private final Long visitId = 1L;

    @BeforeEach
    void setUp() {
        visitServiceMap = new VisitServiceMapImpl();
        PetServiceMapImpl petServiceMap = new PetServiceMapImpl();

        Long petId = 1L;

        Owner owner = Owner.builder().id(1L).build();
        PetType petType = PetType.builder().id(1L).build();
        pet = Pet.builder().id(petId).build();


        pet.setPetType(petType);
        pet.setOwner(owner);
        petServiceMap.save(pet);

        Visit visit = Visit.builder().id(visitId).pet(pet).build();
        visitServiceMap.save(visit);
    }

    @Test
    void findAll() {
        assertEquals(1, visitServiceMap.findAll().size());
    }

    @Test
    void findById() {
        Visit visit = visitServiceMap.findById(visitId);

        assertNotNull(visit);
        assertEquals(visitId, visit.getId());
    }

    @Test
    void findByNonExistentId() {
        assertNull(visitServiceMap.findById(999L));
    }


    @Test
    void saveWithId() {
        Long id = 2L;
        Visit visit = Visit.builder().id(id).pet(pet).build();
        Visit savedVisit = visitServiceMap.save(visit);

        assertNotNull(savedVisit);
        assertEquals(id, savedVisit.getId());
    }

    @Test
    void saveNullObject() {
        assertThrows(RuntimeException.class, () -> visitServiceMap.save(null));
    }

    @Test
    void saveWithoutId() {
        Visit savedVisit = visitServiceMap.save(Visit.builder().pet(pet).build());

        assertNotNull(savedVisit);
        assertNotNull(savedVisit.getId());
    }

    @Test
    void saveWithExistentId() {
        Visit visit = Visit.builder().id(visitId).pet(pet).build();
        assertThrows(RuntimeException.class, () -> visitServiceMap.save(visit));
    }

    @Test
    void saveWithoutPetObject() {
        assertThrows(RuntimeException.class, () -> visitServiceMap.save(Visit.builder().build()));
    }

    @Test
    void delete() {
        visitServiceMap.delete(visitServiceMap.findById(visitId));
        assertEquals(0, visitServiceMap.findAll().size());
    }

    @Test
    void deleteById() {
        visitServiceMap.deleteById(visitId);
        assertEquals(0, visitServiceMap.findAll().size());
    }

}
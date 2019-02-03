package com.github.damianb93.springpetclinic.services.map;

import com.github.damianb93.springpetclinic.model.Pet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Executable;

import static org.junit.jupiter.api.Assertions.*;

class PetServiceMapImplTest {

    private PetServiceMapImpl petServiceMap;
    private final Long petId = 1L;

    @BeforeEach
    void setUp() {
        petServiceMap = new PetServiceMapImpl();

        Pet pet = Pet.builder().id(petId).build();
        petServiceMap.save(pet);
    }

    @Test
    void findAll() {
        assertEquals(1, petServiceMap.findAll().size());
    }

    @Test
    void findById() {
        Pet pet = petServiceMap.findById(petId);

        assertNotNull(pet);
        assertEquals(petId, pet.getId());
    }

    @Test
    void findByNonExistentId() {
        assertNull(petServiceMap.findById(999L));
    }


    @Test
    void saveWithId() {
        Long id = 2L;
        Pet pet = Pet.builder().id(id).build();
        Pet savedPet = petServiceMap.save(pet);

        assertNotNull(savedPet);
        assertEquals(id, savedPet.getId());
    }

    @Test
    void saveNullObject() {
        assertThrows(RuntimeException.class, () -> petServiceMap.save(null));
    }

    @Test
    void saveWithoutId() {
        Pet savedPet = petServiceMap.save(Pet.builder().build());

        assertNotNull(savedPet);
        assertNotNull(savedPet.getId());
    }

    @Test
    void saveWithExistentId() {
        Pet pet = Pet.builder().id(petId).build();
        assertThrows(RuntimeException.class, () -> petServiceMap.save(pet));
    }

    @Test
    void delete() {
        petServiceMap.delete(petServiceMap.findById(petId));
        assertEquals(0, petServiceMap.findAll().size());
    }

    @Test
    void deleteById() {
        petServiceMap.deleteById(petId);
        assertEquals(0, petServiceMap.findAll().size());
    }
}
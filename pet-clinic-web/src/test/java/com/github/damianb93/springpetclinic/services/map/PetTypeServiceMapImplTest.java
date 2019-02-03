package com.github.damianb93.springpetclinic.services.map;

import com.github.damianb93.springpetclinic.model.PetType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PetTypeServiceMapImplTest {

    private PetTypeServiceMapImpl petTypeService;
    private final Long petTypeId = 1L;

    @BeforeEach
    void setUp() {
        petTypeService = new PetTypeServiceMapImpl();
        PetType petType = PetType.builder().id(petTypeId).build();

        petTypeService.save(petType);
    }

    @Test
    void findAll() {
        assertEquals(1, petTypeService.findAll().size());
    }

    @Test
    void findById() {
        PetType petType = petTypeService.findById(petTypeId);

        assertNotNull(petType);
        assertEquals(petTypeId, petType.getId());
    }

    @Test
    void findByNonExistentId() {
        assertNull(petTypeService.findById(999L));
    }

    @Test
    void saveWithId() {
        Long id = 2L;
        PetType petType = PetType.builder().id(id).build();
        PetType savedPetType = petTypeService.save(petType);

        assertNotNull(savedPetType);
        assertEquals(id, savedPetType.getId());
    }

    @Test
    void saveNullObject() {
        assertThrows(RuntimeException.class, () -> petTypeService.save(null));
    }

    @Test
    void saveWithoutId() {
        PetType savedPetType = petTypeService.save(PetType.builder().build());

        assertNotNull(savedPetType);
        assertNotNull(savedPetType.getId());
    }

    @Test
    void saveWithExistentId() {
        PetType petType = PetType.builder().id(petTypeId).build();
        assertThrows(RuntimeException.class, () -> petTypeService.save(petType));
    }

    @Test
    void delete() {
        petTypeService.delete(petTypeService.findById(petTypeId));
        assertEquals(0, petTypeService.findAll().size());
    }

    @Test
    void deleteById() {
        petTypeService.deleteById(petTypeId);
        assertEquals(0, petTypeService.findAll().size());
    }
}
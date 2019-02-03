package com.github.damianb93.springpetclinic.services.map;

import com.github.damianb93.springpetclinic.model.Speciality;
import com.github.damianb93.springpetclinic.model.Vet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VetServiceMapImplTest {

    private VetServiceMapImpl vetServiceMap;
    private final Long vetId = 1L;

    @BeforeEach
    void setUp() {
        vetServiceMap = new VetServiceMapImpl(new SpecialityServiceMapImpl());

        Vet vet = Vet.builder().id(vetId).build();
        vetServiceMap.save(vet);
    }

    @Test
    void findAll() {
        assertEquals(1, vetServiceMap.findAll().size());
    }

    @Test
    void findById() {
        Vet vet = vetServiceMap.findById(vetId);

        assertNotNull(vet);
        assertEquals(vetId, vet.getId());
    }

    @Test
    void findByNonExistentId() {
        assertNull(vetServiceMap.findById(999L));
    }


    @Test
    void saveWithId() {
        Long id = 2L;
        Vet vet = Vet.builder().id(id).build();
        Vet savedVet = vetServiceMap.save(vet);

        assertNotNull(savedVet);
        assertEquals(id, savedVet.getId());
    }

    @Test
    void saveNullObject() {
        assertThrows(RuntimeException.class, () -> vetServiceMap.save(null));
    }

    @Test
    void saveWithoutId() {
        Vet savedVet = vetServiceMap.save(Vet.builder().build());

        assertNotNull(savedVet);
        assertNotNull(savedVet.getId());
    }

    @Test
    void saveWithExistentId() {
        Vet vet = Vet.builder().id(vetId).build();
        assertThrows(RuntimeException.class, () -> vetServiceMap.save(vet));
    }

    @Test
    void saveWithSpeciality() {
        Vet vet = Vet.builder().build();
        Speciality speciality = Speciality.builder().build();

        vet.getSpecialities().add(speciality);
        Vet savedVet = vetServiceMap.save(vet);

        assertNotNull(savedVet);
        assertNotNull(savedVet.getId());
        assertNotNull(savedVet.getSpecialities());
        assertNotNull(speciality.getId());
    }

    @Test
    void saveWithNullSpeciality() {
        Vet vet = Vet.builder().build();

        vet.getSpecialities().add(null);
        assertThrows(RuntimeException.class, () -> vetServiceMap.save(vet));
    }

    @Test
    void delete() {
        vetServiceMap.delete(vetServiceMap.findById(vetId));
        assertEquals(0, vetServiceMap.findAll().size());
    }

    @Test
    void deleteById() {
        vetServiceMap.deleteById(vetId);
        assertEquals(0, vetServiceMap.findAll().size());
    }
}
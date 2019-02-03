package com.github.damianb93.springpetclinic.services.map;

import com.github.damianb93.springpetclinic.model.Speciality;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpecialityServiceMapImplTest {


    private SpecialityServiceMapImpl specialityServiceMap;
    private final Long specialityId = 1L;

    @BeforeEach
    void setUp() {
        specialityServiceMap = new SpecialityServiceMapImpl();

        Speciality speciality = Speciality.builder().id(specialityId).build();
        specialityServiceMap.save(speciality);
    }

    @Test
    void findAll() {
        assertEquals(1, specialityServiceMap.findAll().size());
    }

    @Test
    void findById() {
        Speciality speciality = specialityServiceMap.findById(specialityId);

        assertNotNull(speciality);
        assertEquals(specialityId, speciality.getId());
    }

    @Test
    void findByNonExistentId() {
        assertNull(specialityServiceMap.findById(999L));
    }


    @Test
    void saveWithId() {
        Long id = 2L;
        Speciality speciality = Speciality.builder().id(id).build();
        Speciality savedSpeciality = specialityServiceMap.save(speciality);

        assertNotNull(savedSpeciality);
        assertEquals(id, savedSpeciality.getId());
    }

    @Test
    void saveNullObject() {
        assertThrows(RuntimeException.class, () -> specialityServiceMap.save(null));
    }

    @Test
    void saveWithoutId() {
        Speciality savedSpeciality = specialityServiceMap.save(Speciality.builder().build());

        assertNotNull(savedSpeciality);
        assertNotNull(savedSpeciality.getId());
    }

    @Test
    void saveWithExistentId() {
        Speciality speciality = Speciality.builder().id(specialityId).build();
        assertThrows(RuntimeException.class, () -> specialityServiceMap.save(speciality));
    }

    @Test
    void delete() {
        specialityServiceMap.delete(specialityServiceMap.findById(specialityId));
        assertEquals(0, specialityServiceMap.findAll().size());
    }

    @Test
    void deleteById() {
        specialityServiceMap.deleteById(specialityId);
        assertEquals(0, specialityServiceMap.findAll().size());
    }
}
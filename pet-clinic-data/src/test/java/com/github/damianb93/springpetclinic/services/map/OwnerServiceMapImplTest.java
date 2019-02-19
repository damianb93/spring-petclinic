package com.github.damianb93.springpetclinic.services.map;

import com.github.damianb93.springpetclinic.model.Owner;
import com.github.damianb93.springpetclinic.model.Pet;
import com.github.damianb93.springpetclinic.model.PetType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OwnerServiceMapImplTest {

    private OwnerServiceMapImpl ownerServiceMap;
    private final Long ownerId = 1L;
    private final String ownerLastName = "James";

    @BeforeEach
    void setUp() {
        ownerServiceMap = new OwnerServiceMapImpl(new PetServiceMapImpl(), new PetTypeServiceMapImpl());
        ownerServiceMap.save(Owner.builder().id(ownerId).lastName(ownerLastName).build());
        ownerServiceMap.save(Owner.builder().id(2L).lastName("Smith").build());
    }

    @Test
    void findByLastName() {
        Owner owner = ownerServiceMap.findByLastName(ownerLastName);

        assertNotNull(owner);
        assertEquals(ownerId, owner.getId());
    }

    @Test
    void findByNotExistentLastName() {
        Owner owner = ownerServiceMap.findByLastName("Not Existent");

        assertNull(owner);
    }

    @Test
    void findAllByEmptyLastName() {
        Set<Owner> owners = ownerServiceMap.findAllByLastName("");

        assertEquals(2, owners.size());
    }

    @Test
    void findAllByPartOfLastName() {
        Set<Owner> owners = ownerServiceMap.findAllByLastName("ames");

        assertEquals(1, owners.size());
    }

    @Test
    void findAllByDifferentCaseLastName() {
        Set<Owner> owners = ownerServiceMap.findAllByLastName("AmEs");
        
        assertEquals(1, owners.size());
    }

    @Test
    void findAll() {
        assertEquals(2, ownerServiceMap.findAll().size());
    }

    @Test
    void findById() {
        Owner owner = ownerServiceMap.findById(ownerId);

        assertNotNull(owner);
        assertEquals(ownerId, owner.getId());
    }

    @Test
    void findByNonExistentId() {
        assertNull(ownerServiceMap.findById(999L));
    }

    @Test
    void saveWithId() {
        Long id = 3L;
        Owner owner = Owner.builder().id(id).build();
        Owner savedOwner = ownerServiceMap.save(owner);

        assertNotNull(savedOwner);
        assertEquals(id, savedOwner.getId());
    }

    @Test
    void saveNullObject() {
        assertThrows(RuntimeException.class, () -> ownerServiceMap.save(null));
    }

    @Test
    void saveWithoutId() {
        Owner savedOwner = ownerServiceMap.save(Owner.builder().build());

        assertNotNull(savedOwner);
        assertNotNull(savedOwner.getId());
    }

    @Test
    void saveWithExistentId() {
        Owner owner = Owner.builder().id(ownerId).build();
        assertThrows(RuntimeException.class, () -> ownerServiceMap.save(owner));
    }

    @Test
    void saveWithPetAndPetTypeNoIds() {
        Owner owner = Owner.builder().build();
        Pet pet = Pet.builder().build();
        PetType petType = PetType.builder().build();

        pet.setPetType(petType);
        owner.getPets().add(pet);

        Owner savedOwner = ownerServiceMap.save(owner);

        assertNotNull(savedOwner);
        assertNotNull(savedOwner.getId());
        assertNotNull(savedOwner.getPets());
        assertNotNull(pet.getId());
        assertNotNull(pet.getPetType());
        assertNotNull(pet.getPetType().getId());
    }

    @Test
    void saveWithPetButWithoutPetType() {
        Owner owner = Owner.builder().build();
        Pet pet = Pet.builder().build();
        owner.getPets().add(pet);

        assertThrows(RuntimeException.class, () -> ownerServiceMap.save(owner));
    }

    @Test
    void delete() {
        ownerServiceMap.delete(ownerServiceMap.findById(ownerId));
        assertEquals(1, ownerServiceMap.findAll().size());
    }

    @Test
    void deleteById() {
        ownerServiceMap.deleteById(ownerId);
        assertEquals(1, ownerServiceMap.findAll().size());
    }
}
package com.github.damianb93.springpetclinic.repositories;

import com.github.damianb93.springpetclinic.model.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet, Long> {
}

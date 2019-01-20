package com.github.damianb93.springpetclinic.repositories;

import com.github.damianb93.springpetclinic.model.PetType;
import org.springframework.data.repository.CrudRepository;

public interface PetTypeRepository extends CrudRepository<PetType, Long> {
}

package com.github.damianb93.springpetclinic.repositories;

import com.github.damianb93.springpetclinic.model.Owner;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface OwnerRepository extends CrudRepository<Owner, Long> {

    Owner findByLastName(String lastName);
    Set<Owner> findAllByLastNameContainingIgnoreCase(String lastName);
}

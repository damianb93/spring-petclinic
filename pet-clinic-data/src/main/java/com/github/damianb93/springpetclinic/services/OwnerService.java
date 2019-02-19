package com.github.damianb93.springpetclinic.services;

import com.github.damianb93.springpetclinic.model.Owner;

import java.util.Set;

public interface OwnerService extends CrudService<Owner, Long> {

    Owner findByLastName(String lastName);
    Set<Owner> findAllByLastName(String lastName);
}

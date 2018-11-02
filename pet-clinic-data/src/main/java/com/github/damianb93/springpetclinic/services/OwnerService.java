package com.github.damianb93.springpetclinic.services;

import com.github.damianb93.springpetclinic.model.Owner;

public interface OwnerService extends CrudService<Owner, Long> {

    Owner findByLastName(String lastName);
}

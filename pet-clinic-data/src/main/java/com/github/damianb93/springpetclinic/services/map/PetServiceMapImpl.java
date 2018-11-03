package com.github.damianb93.springpetclinic.services.map;

import com.github.damianb93.springpetclinic.model.Pet;
import com.github.damianb93.springpetclinic.services.PetService;
import org.springframework.stereotype.Service;

@Service
public class PetServiceMapImpl extends AbstractMapService<Pet> implements PetService {
}

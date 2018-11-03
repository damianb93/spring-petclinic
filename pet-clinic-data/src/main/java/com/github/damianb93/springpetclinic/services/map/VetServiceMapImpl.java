package com.github.damianb93.springpetclinic.services.map;

import com.github.damianb93.springpetclinic.model.Vet;
import com.github.damianb93.springpetclinic.services.VetService;
import org.springframework.stereotype.Service;

@Service
public class VetServiceMapImpl extends AbstractMapService<Vet, Long> implements VetService {
}

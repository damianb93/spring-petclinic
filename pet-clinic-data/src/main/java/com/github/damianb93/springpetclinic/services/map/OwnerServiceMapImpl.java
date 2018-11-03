package com.github.damianb93.springpetclinic.services.map;

import com.github.damianb93.springpetclinic.model.Owner;
import com.github.damianb93.springpetclinic.services.OwnerService;
import org.springframework.stereotype.Service;

@Service
public class OwnerServiceMapImpl extends AbstractMapService<Owner> implements OwnerService {

    @Override
    public Owner findByLastName(String lastName) {
        return map.values().stream()
            .filter(obj -> obj.getLastName().equals(lastName))
            .findFirst()
            .orElse(null);
    }
}

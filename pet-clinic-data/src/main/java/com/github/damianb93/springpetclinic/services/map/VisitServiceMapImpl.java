package com.github.damianb93.springpetclinic.services.map;

import com.github.damianb93.springpetclinic.model.Visit;
import com.github.damianb93.springpetclinic.services.VisitService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile({"default", "map"})
public class VisitServiceMapImpl extends AbstractMapService<Visit> implements VisitService {
    @Override
    public Set<Visit> findAll() {
        return super.findAll();
    }

    @Override
    public Visit findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Visit save(Visit object) {

        if (object.getPet() == null || object.getPet().getOwner() == null || object.getPet().getPetType() == null ||
            object.getPet().getId() == null || object.getPet().getOwner().getId() == null ||
            object.getPet().getPetType().getId() == null) {
            throw new RuntimeException("Invalid visit");
        }

        return super.save(object);
    }

    @Override
    public void delete(Visit object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}

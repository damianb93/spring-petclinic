package com.github.damianb93.springpetclinic.services.map;

import com.github.damianb93.springpetclinic.model.Vet;
import com.github.damianb93.springpetclinic.services.SpecialityService;
import com.github.damianb93.springpetclinic.services.VetService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class VetServiceMapImpl extends AbstractMapService<Vet> implements VetService {

    private final SpecialityService specialityService;

    public VetServiceMapImpl(SpecialityService specialityService) {
        this.specialityService = specialityService;
    }

    @Override
    public Set<Vet> findAll() {
        return super.findAll();
    }

    @Override
    public Vet findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Vet save(Vet object) {

        if (!object.getSpecialities().isEmpty()) {
            object.getSpecialities().forEach(speciality -> {
                if (speciality.getId() == null) {
                    speciality.setId(specialityService.save(speciality).getId());
                }
            });
        }

        return super.save(object);
    }

    @Override
    public void delete(Vet object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}

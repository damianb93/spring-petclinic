package com.github.damianb93.springpetclinic.formatters;

import com.github.damianb93.springpetclinic.model.PetType;
import com.github.damianb93.springpetclinic.services.PetTypeService;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

@Component
public class PetTypeFormatter implements Formatter<PetType> {

    private final PetTypeService petTypeService;

    public PetTypeFormatter(PetTypeService petTypeService) {
        this.petTypeService = petTypeService;
    }

    @Override
    public PetType parse(String text, Locale locale) throws ParseException {
        Collection<PetType> findPetTypes = petTypeService.findAll();

        for (PetType petType : findPetTypes) {
            if (petType.getName().equalsIgnoreCase(text)) {
                return petType;
            }
        }
        throw new ParseException("Pet type not found " + text, 0);
    }

    @Override
    public String print(PetType object, Locale locale) {
        return object.getName();
    }
}

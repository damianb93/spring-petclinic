package com.github.damianb93.springpetclinic.controllers;

import com.github.damianb93.springpetclinic.model.Owner;
import com.github.damianb93.springpetclinic.model.Pet;
import com.github.damianb93.springpetclinic.model.PetType;
import com.github.damianb93.springpetclinic.services.OwnerService;
import com.github.damianb93.springpetclinic.services.PetService;
import com.github.damianb93.springpetclinic.services.PetTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@Controller
@RequestMapping("owners/{ownerId}/pets")
public class PetController {

    private static final String CREATE_OR_UPDATE_PET_FORM_VIEW = "pets/createOrUpdatePetForm";
    private static final String REDIRECT_TO_OWNER_DETAILS = "redirect:/owners/";

    private final OwnerService ownerService;
    private final PetService petService;
    private final PetTypeService petTypeService;

    public PetController(OwnerService ownerService, PetService petService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.petService = petService;
        this.petTypeService = petTypeService;
    }

    @InitBinder("owner")
    public void initOwnerBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @ModelAttribute("types")
    public Collection<PetType> populatePetTypes() {
        return petTypeService.findAll();
    }

    @ModelAttribute("owner")
    public Owner findOwner(@PathVariable("ownerId") Long ownerId, Model model) {
        Owner owner = ownerService.findById(ownerId);
        Pet pet = Pet.builder().build();

        pet.setOwner(owner);

        model.addAttribute("pet", pet);
        return owner;
    }

    @GetMapping("new")
    public String initCreationForm() {
        return CREATE_OR_UPDATE_PET_FORM_VIEW;
    }

    @PostMapping("new")
    public String processCreationForm(Owner owner, @Valid Pet pet, BindingResult result, Model model) {
        if (StringUtils.hasLength(pet.getName()) && pet.isNew() && owner.getPet(pet.getName(), true) != null) {
            result.rejectValue("name", "duplicate", "already exists");
        }
        if (result.hasErrors()) {
            model.addAttribute("pet", pet);
            return CREATE_OR_UPDATE_PET_FORM_VIEW;
        } else {
            petService.save(pet);
            return REDIRECT_TO_OWNER_DETAILS + owner.getId();
        }
    }

    @GetMapping("{petId}/edit")
    public String initUpdateForm(@PathVariable("petId") Long petId, Model model) {
        model.addAttribute("pet", petService.findById(petId));
        return CREATE_OR_UPDATE_PET_FORM_VIEW;
    }

    @PostMapping("{petId}/edit")
    public String processUpdateForm(@Valid Pet pet, BindingResult result, Owner owner, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("pet", pet);
            return CREATE_OR_UPDATE_PET_FORM_VIEW;
        } else {
            this.petService.save(pet);
            return REDIRECT_TO_OWNER_DETAILS + owner.getId();
        }
    }
}

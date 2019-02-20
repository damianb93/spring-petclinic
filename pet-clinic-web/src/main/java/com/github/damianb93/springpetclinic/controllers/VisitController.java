package com.github.damianb93.springpetclinic.controllers;

import com.github.damianb93.springpetclinic.model.Pet;
import com.github.damianb93.springpetclinic.model.Visit;
import com.github.damianb93.springpetclinic.services.PetService;
import com.github.damianb93.springpetclinic.services.VisitService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("owners/{ownerId}/pets/{petId}/visits")
public class VisitController {

    private static final String CREATE_OR_UPDATE_VISIT_FORM_VIEW = "pets/createOrUpdateVisitForm";
    private static final String REDIRECT_TO_OWNER_DETAILS = "redirect:/owners/";

    private final VisitService visitService;
    private final PetService petService;

    public VisitController(VisitService visitService, PetService petService) {
        this.visitService = visitService;
        this.petService = petService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @ModelAttribute("visit")
    public Visit loadPetWithVisit(@PathVariable("petId") Long petId, Model model) {
        Pet pet = petService.findById(petId);
        model.addAttribute("pet", pet);

        Visit visit = Visit.builder().build();
        pet.getVisits().add(visit);
        visit.setPet(pet);
        return visit;
    }

    @GetMapping("new")
    public String initNewVisitForm() {
        return CREATE_OR_UPDATE_VISIT_FORM_VIEW;
    }

    @PostMapping("new")
    public String processVisitForm(@PathVariable("ownerId") Long ownerId, @Valid Visit visit, BindingResult result) {
        if (result.hasErrors()) {
            return CREATE_OR_UPDATE_VISIT_FORM_VIEW;
        } else {
            visitService.save(visit);
            return REDIRECT_TO_OWNER_DETAILS + ownerId;
        }
    }
}

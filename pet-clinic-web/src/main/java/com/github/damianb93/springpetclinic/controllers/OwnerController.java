package com.github.damianb93.springpetclinic.controllers;

import com.github.damianb93.springpetclinic.model.Owner;
import com.github.damianb93.springpetclinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Set;

@Controller
@RequestMapping("owners")
public class OwnerController {

    private final OwnerService ownerService;

    private static final String FIND_OWNERS_VIEW = "owners/findOwners";
    private static final String OWNERS_LIST_VIEW = "owners/ownersList";
    private static final String OWNER_DETAILS_VIEW = "owners/ownerDetails";
    private static final String CREATE_OR_UPDATE_FORM_VIEW = "owners/createOrUpdateOwnerForm";
    private static final String REDIRECT_TO_OWNER_DETAILS = "redirect:/owners/";

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping
    public String processFindForm(Owner owner, BindingResult bindingResult, Model model) {

        if (owner.getLastName() == null) owner.setLastName("");

        Set<Owner> results = ownerService.findAllByLastName(owner.getLastName());

        if (results.isEmpty()) {
            bindingResult.rejectValue("lastName", "notFound", "not Found");
            return FIND_OWNERS_VIEW;
        } else if (results.size() == 1) {
            owner = results.iterator().next();
            return REDIRECT_TO_OWNER_DETAILS + owner.getId();
        } else {
            model.addAttribute("selections", results);
            return OWNERS_LIST_VIEW;
        }
    }

    @GetMapping("{ownerId}")
    public ModelAndView showOwner(@PathVariable("ownerId") Long ownerId) {
        ModelAndView mav = new ModelAndView(OWNER_DETAILS_VIEW);
        mav.addObject(ownerService.findById(ownerId));

        return mav;
    }

    @GetMapping("find")
    public String findOwners(Model model) {
        model.addAttribute("owner", Owner.builder().build());
        return FIND_OWNERS_VIEW;
    }

    @GetMapping("new")
    public String initCreationForm(Model model) {
        model.addAttribute("owner", Owner.builder().build());
        return CREATE_OR_UPDATE_FORM_VIEW;
    }

    @PostMapping("new")
    public String processCreationForm(@Valid Owner owner, BindingResult result) {
        if (result.hasErrors()) {
            return CREATE_OR_UPDATE_FORM_VIEW;
        } else {
            Owner savedOwner = ownerService.save(owner);
            return REDIRECT_TO_OWNER_DETAILS + savedOwner.getId();
        }
    }

    @GetMapping("{ownerId}/edit")
    public String initUpdateOwnerForm(@PathVariable("ownerId") Long ownerId, Model model) {
        model.addAttribute("owner", ownerService.findById(ownerId));
        return CREATE_OR_UPDATE_FORM_VIEW;
    }

    @PostMapping("{ownerId}/edit")
    public String processUpdateOwnerForm(@PathVariable("ownerId") Long ownerId, @Valid Owner owner, BindingResult result) {
        if (result.hasErrors()) {
            return CREATE_OR_UPDATE_FORM_VIEW;
        } else {
            owner.setId(ownerId);
            Owner modifiedOwner = ownerService.save(owner);
            return REDIRECT_TO_OWNER_DETAILS + modifiedOwner.getId();
        }
    }
}

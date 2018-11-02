package com.github.damianb93.springpetclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"vets", "vets/index", "vets/index.html"})
public class VetController {

    @GetMapping
    public String listVets() {
        return "vets/index";
    }

}

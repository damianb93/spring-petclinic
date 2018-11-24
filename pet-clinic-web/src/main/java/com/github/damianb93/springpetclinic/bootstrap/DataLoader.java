package com.github.damianb93.springpetclinic.bootstrap;

import com.github.damianb93.springpetclinic.model.*;
import com.github.damianb93.springpetclinic.services.OwnerService;
import com.github.damianb93.springpetclinic.services.PetTypeService;
import com.github.damianb93.springpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {
        loadData();
    }

    private void loadData() {
        //LOADING OWNERS
        Owner owner1 = new Owner();
        owner1.setFirstName("Michael");
        owner1.setLastName("Weston");
        owner1.setAddress("123 Brickerel");
        owner1.setCity("Miami");
        owner1.setTelephone("1231231234");

        Owner owner2 = new Owner();
        owner2.setFirstName("Fiona");
        owner2.setLastName("Glenanne");
        owner2.setAddress("123 Brickerel");
        owner2.setCity("Miami");
        owner2.setTelephone("1231231234");

        System.out.println("LOADED OWNERS...");


        //LOADING VETS
        Vet vet1 = new Vet();
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");

        Vet vet2 = new Vet();
        vet2.setFirstName("Jessie");
        vet2.setLastName("Porter");

        System.out.println("LOADED VETS...");


        //LOADING PET TYES
        PetType dog = new PetType();
        dog.setName("Dog");

        PetType cat = new PetType();
        dog.setName("Cat");

        System.out.println("LOADED PET TYPES...");


        //LOADING PETS
        Pet pet1 = new Pet();
        pet1.setName("Rosco");
        pet1.setPetType(dog);
        pet1.setOwner(owner1);
        pet1.setBirthDate(LocalDate.now());

        owner1.getPets().add(pet1);

        Pet pet2 = new Pet();
        pet2.setName("Just cat");
        pet2.setPetType(cat);
        pet2.setOwner(owner2);
        pet2.setBirthDate(LocalDate.now());

        owner2.getPets().add(pet2);

        System.out.println("LOADED PETS...");


        //LOADING SPECIALITIES
        Speciality spec1 = new Speciality();
        spec1.setDescription("radiology");

        Speciality spec2 = new Speciality();
        spec2.setDescription("surgery");

        Speciality spec3 = new Speciality();
        spec3.setDescription("dentistry");

        vet1.getSpecialities().add(spec1);
        vet2.getSpecialities().add(spec2);
        vet2.getSpecialities().add(spec3);

        System.out.println("LOADED SPECIALITIES...");

        //SAVING CONTENT

        ownerService.save(owner1);
        ownerService.save(owner2);

        vetService.save(vet1);
        vetService.save(vet2);
    }
}

package com.github.damianb93.springpetclinic.bootstrap;

import com.github.damianb93.springpetclinic.model.Owner;
import com.github.damianb93.springpetclinic.model.Person;
import com.github.damianb93.springpetclinic.model.PetType;
import com.github.damianb93.springpetclinic.model.Vet;
import com.github.damianb93.springpetclinic.services.OwnerService;
import com.github.damianb93.springpetclinic.services.PetTypeService;
import com.github.damianb93.springpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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

        //LOADING OWNERS
        createPerson(new Owner(), "Michael", "Weston");
        createPerson(new Owner(),  "Fiona", "Glenanne");
        createPerson(new Owner(), "Jack", "Glenanne");

        System.out.println("LOADED OWNERS...");


        //LOADING VETS
        createPerson(new Vet(), "Sam", "Axe");
        createPerson(new Vet(), "Jessie", "Porter");

        System.out.println("LOADED VETS...");


        //LOADING PET TYES
        PetType dog = new PetType();
        dog.setName("Dog");

        PetType cat = new PetType();
        dog.setName("Cat");

        petTypeService.save(dog);
        petTypeService.save(cat);

        System.out.println("LOADED PET TYPES...");

    }

    private <T extends Person> void createPerson (T person, String firstName, String lastName) {
        person.setFirstName(firstName);
        person.setLastName(lastName);

        if (person instanceof Owner) ownerService.save((Owner) person);
        if (person instanceof Vet) vetService.save((Vet) person);
    }
}

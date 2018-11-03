package com.github.damianb93.springpetclinic.bootstrap;

import com.github.damianb93.springpetclinic.model.Owner;
import com.github.damianb93.springpetclinic.model.Person;
import com.github.damianb93.springpetclinic.model.Vet;
import com.github.damianb93.springpetclinic.services.OwnerService;
import com.github.damianb93.springpetclinic.services.VetService;
import com.github.damianb93.springpetclinic.services.map.OwnerServiceMapImpl;
import com.github.damianb93.springpetclinic.services.map.VetServiceMapImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;

    public DataLoader() {
        this.ownerService = new OwnerServiceMapImpl();
        this.vetService = new VetServiceMapImpl();
    }

    @Override
    public void run(String... args) throws Exception {
        createPerson(new Owner(), 1L, "Michael", "Weston");
        createPerson(new Owner(), 2L, "Fiona", "Glenanne");

        System.out.println("LOADED OWNERS...");

        createPerson(new Vet(), 1L, "Sam", "Axe");
        createPerson(new Vet(), 2L, "Jessie", "Porter");

        System.out.println("LOADED VETS...");
    }

    private <T extends Person> void createPerson (T person, Long id, String firstName, String lastName) {
        person.setId(id);
        person.setFirstName(firstName);
        person.setLastName(lastName);

        if (person instanceof Owner) ownerService.save((Owner) person);
        if (person instanceof Vet) vetService.save((Vet) person);
    }
}

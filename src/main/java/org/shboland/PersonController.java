package org.shboland;

import org.shboland.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @PostMapping("/persons")
    public Person createPerson(@RequestBody Person person) {
        return personRepository.save(person);
    }

    @GetMapping("/persons/{personId}")
    public Person getPerson(@PathVariable String personId) {
        return personRepository.findById(Integer.valueOf(personId))
                .orElseThrow(() -> new RuntimeException("No person found for id: " + personId));
    }
}

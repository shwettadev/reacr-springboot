package com.crm.model;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.Collection;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class ContactsController {
    private ContactRepository contactRepository;

    public ContactsController(ContactRepository contactRepository){
        this.contactRepository = contactRepository;
    }

    @GetMapping("/contacts")
    Collection<Contact> contacts(){
        return (Collection<Contact>) contactRepository.findAll();
    }

    @PostMapping("/addContacts")
    ResponseEntity<Contact> createContact(@RequestBody Contact contact) throws URISyntaxException{
        Contact result = contactRepository.save(contact);
        return ResponseEntity.ok().body(result);
    }

    @PutMapping("/updateContacts/{id}")
    ResponseEntity<Contact> updateContact(@PathVariable Long id ,@RequestBody Contact contact) throws URISyntaxException{
        Contact contactById = contactRepository.findById(id).get();
        contactById.setFirstName(contact.getFirstName());
        contactById.setLastName(contact.getLastName());
        contactById.setEmail(contact.getEmail());
        Contact save = contactRepository.save(contactById);
        return ResponseEntity.ok().body(save);
    }
}

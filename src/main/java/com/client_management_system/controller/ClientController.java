package com.client_management_system.controller;

import com.client_management_system.Entity.Client;
import com.client_management_system.service.ClientService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping("/client")
    public ResponseEntity<Client> addClient(@RequestBody Client client) {
        return ResponseEntity.ok(clientService.addClient(client));
    }


    @GetMapping("/clients")
    public ResponseEntity<List<Client>> getAllClients() {
        return ResponseEntity.ok(clientService.getAllClients());
    }


    @GetMapping("/client/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable Long id) {
        return ResponseEntity.ok(clientService.getClientById(id));
    }

    @DeleteMapping("/client/{id}")
    public ResponseEntity<?> deleteClientById(@PathVariable Long id) {
        try{
            clientService.deleteClientById(id);
            return new ResponseEntity<>("/client with id "+ id +" deleted successfully" , HttpStatus.OK);
        }catch (EntityNotFoundException e)
        {
            return  new  ResponseEntity<>(e.getMessage() , HttpStatus.NOT_FOUND);
        }
    }


    @PatchMapping("/client/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable Long id, @RequestBody Client client) {
        return ResponseEntity.ok(clientService.updateClient(id, client));
    }


}

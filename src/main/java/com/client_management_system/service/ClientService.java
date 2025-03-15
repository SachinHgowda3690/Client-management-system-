package com.client_management_system.service;

import com.client_management_system.Entity.Client;
import com.client_management_system.repository.ClientRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;


    public Client addClient(Client client) {
        return clientRepository.save(client);
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Client getClientById(Long id) {
        return clientRepository.findById(id).orElse(null);
    }

    public void deleteClientById(Long id) {
        if (!clientRepository.existsById(id)) {
            throw new EntityNotFoundException("client with id " + id + " not found");
        }

        clientRepository.deleteById(id);
    }

public Client updateClient(Long id,Client updatedClient) {
        Client existingClient = clientRepository.findById(id).orElse(null);

        if (existingClient != null) {
            existingClient.setName(updatedClient.getName());
            existingClient.setEmail(updatedClient.getEmail());
            existingClient.setPhone(updatedClient.getPhone());
            existingClient.setAddress(updatedClient.getAddress());
            return clientRepository.save(existingClient);
        }
        return null;
}




}

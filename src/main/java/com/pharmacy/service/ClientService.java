package com.pharmacy.service;

import com.pharmacy.model.Client;
import com.pharmacy.model.Role;
import com.pharmacy.repository.ClientRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }


    // Cadastrar cliente
    public Client register(Client client) {
        client.setCargo(Role.CLIENTE);

        String passwordHash = passwordEncoder.encode(client.getPasswordHash());

        client.setPasswordHash(passwordHash);

        return clientRepository.save(client);
    }

    //Achar cliente
    public Optional<Client> findByEmail(String email) {
        return clientRepository.findByEmail(email);
    }

    //Autenticação
    public Optional<Client> authenticate(String email, String password) {

        Optional<Client> result = clientRepository.findByEmail(email);

        if(result.isPresent() && passwordEncoder.matches(password, result.get().getPasswordHash())) {
            return result;
        }
        return  Optional.empty();
    }

    //Update Cliente
    public void updateProfile(Client client) {
        clientRepository.updateProfile(client);
    }

    //Update cargo
    public void updateRole(Integer clientId, Role cargo) {
        clientRepository.updateRole(clientId, cargo);
    }
}

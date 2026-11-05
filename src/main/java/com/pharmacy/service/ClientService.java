/*
 * ORIGEM / CONTRIBUIÇÃO: BASE PHARMACY + PROJETO LPR — estrutura e repositório do projeto principal; validações, autenticação BCrypt e alteração de senha adaptadas do Projeto LPR.
 * Observação: este comentário registra a procedência do código integrado; não identifica autoria individual.
 */
package com.pharmacy.service;

import com.pharmacy.model.Client;
import com.pharmacy.model.Role;
import com.pharmacy.repository.ClientRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final BCryptPasswordEncoder passwordEncoder =
            new BCryptPasswordEncoder();

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client register(Client client) {
        if (client == null) {
            throw new IllegalArgumentException("Informe os dados do cliente.");
        }
        if (client.getName() == null || client.getName().isBlank()) {
            throw new IllegalArgumentException("Informe o nome.");
        }
        String email = normalizeEmail(client.getEmail());
        validateEmail(email);
        validatePassword(client.getPasswordHash());

        if (clientRepository.existsByEmail(email)) {
            throw new IllegalArgumentException(
                    "Já existe uma conta com esse e-mail.");
        }

        client.setName(client.getName().trim());
        client.setEmail(email);
        client.setPasswordHash(passwordEncoder.encode(client.getPasswordHash()));
        client.setCargo(Role.CLIENTE);

        return clientRepository.save(client);
    }

    public Optional<Client> findByEmail(String email) {
        if (email == null || email.isBlank()) {
            return Optional.empty();
        }
        return clientRepository.findByEmail(normalizeEmail(email));
    }

    public Optional<Client> authenticate(String email, String password) {
        if (email == null || password == null || password.isBlank()) {
            return Optional.empty();
        }

        return clientRepository.findByEmail(normalizeEmail(email))
                .filter(client -> passwordEncoder.matches(
                        password, client.getPasswordHash()));
    }

    public void updateProfile(Client client, String name,
                              String currentPassword, String newPassword) {
        if (client == null || client.getId() == null) {
            throw new IllegalArgumentException("Cliente inválido.");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Informe o nome.");
        }

        client.setName(name.trim());

        boolean changingPassword = newPassword != null && !newPassword.isBlank();
        if (changingPassword) {
            if (currentPassword == null
                    || !passwordEncoder.matches(
                            currentPassword, client.getPasswordHash())) {
                throw new IllegalArgumentException(
                        "A senha atual está incorreta.");
            }
            validatePassword(newPassword);
            client.setPasswordHash(passwordEncoder.encode(newPassword));
        }

        clientRepository.updateProfile(client);
    }

    public void updateProfile(Client client) {
        if (client == null || client.getId() == null
                || client.getName() == null || client.getName().isBlank()) {
            throw new IllegalArgumentException("Dados do perfil inválidos.");
        }
        clientRepository.updateProfile(client);
    }

    public void updateRole(Integer clientId, Role cargo) {
        if (clientId == null || cargo == null) {
            throw new IllegalArgumentException("Cliente ou cargo inválido.");
        }
        clientRepository.updateRole(clientId, cargo);
    }

    private String normalizeEmail(String email) {
        return email == null ? "" : email.trim().toLowerCase(Locale.ROOT);
    }

    private void validateEmail(String email) {
        if (!email.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")) {
            throw new IllegalArgumentException("E-mail inválido.");
        }
    }

    private void validatePassword(String password) {
        if (password == null || password.length() < 6) {
            throw new IllegalArgumentException(
                    "A senha deve ter pelo menos 6 caracteres.");
        }
    }
}

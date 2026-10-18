// ClientRepository.java
package com.pharmacy.repository;

import com.pharmacy.model.Client;
import com.pharmacy.model.Role;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Optional;

@Repository
public class ClientRepository {

    private final Connection connection;

    public ClientRepository(Connection connection) {
        this.connection = connection;
    }

    public Client save(Client client) {
        String sql = """
                INSERT INTO cliente (nome, email, senha_hash, cargo)
                VALUES (?, ?, ?, ?)
                """;

        try (PreparedStatement statement = connection.prepareStatement(
                sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, client.getName());
            statement.setString(2, client.getEmail());
            statement.setString(3, client.getPasswordHash());
            statement.setString(4, Role.CLIENTE.name());

            statement.executeUpdate();

            try (ResultSet keys = statement.getGeneratedKeys()) {
                if (keys.next()) {
                    client.setId(keys.getInt(1));
                }
            }

            client.setCargo(Role.CLIENTE);
            return client;

        } catch (Exception e) { throw new RuntimeException("Erro ao cadastrar cliente.", e); }
    }

    public Optional<Client> findByEmail(String email) {
        String sql = """
                SELECT id, nome, email, senha_hash, cargo
                FROM cliente
                WHERE email = ?
                """;

        try (PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, email);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Client client = new Client();

                    client.setId(resultSet.getInt("id"));
                    client.setName(resultSet.getString("nome"));
                    client.setEmail(resultSet.getString("email"));
                    client.setPasswordHash(
                            resultSet.getString("senha_hash"));
                    client.setCargo(
                            Role.valueOf(resultSet.getString("cargo")));

                    return Optional.of(client);
                }
            }

            return Optional.empty();

        } catch (Exception e) { throw new RuntimeException("Erro ao buscar cliente por e-mail.", e); }
    }

    public void updateProfile(Client client) {
        String sql = """
                UPDATE cliente
                SET nome = ?, email = ?
                WHERE id = ?
                """;

        try (PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, client.getName());
            statement.setString(2, client.getEmail());
            statement.setInt(3, client.getId());

            statement.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar perfil do cliente.", e); }
    }

    public void updateRole(Integer clientId, Role cargo) {
        String sql = """
                UPDATE cliente
                SET cargo = ?
                WHERE id = ?
                """;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, cargo.name());
            statement.setInt(2, clientId);

            statement.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar cargo do cliente.", e); }
    }
}
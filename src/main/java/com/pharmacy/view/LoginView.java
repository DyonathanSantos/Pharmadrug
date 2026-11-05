/*
 * ORIGEM / CONTRIBUIÇÃO: PHARMADRUG + PROJETO LPR — tela baseada na versão presente no Pharmadrug; autenticação/sessão e navegação integradas com a lógica de sessão do Projeto LPR.
 * Observação: este comentário registra a procedência do código integrado; não identifica autoria individual.
 */
package com.pharmacy.view;

import com.pharmacy.model.Client;
import com.pharmacy.security.SessaoUsuario;
import com.pharmacy.service.ClientService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.Optional;

@Route("login")
@PageTitle("Login | Pharmacy")
public class LoginView extends VerticalLayout {

    private final ClientService clientService;
    private final H2 title = new H2("Pharmacy");
    private final TextField nameField = new TextField("Nome");
    private final EmailField emailField = new EmailField("E-mail");
    private final PasswordField passwordField = new PasswordField("Senha");
    private final Button submitButton = new Button("Entrar");
    private final Button toggleButton = new Button("Criar uma conta");
    private boolean registering = false;

    public LoginView(ClientService clientService) {
        this.clientService = clientService;

        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        nameField.setWidth("300px");
        emailField.setWidth("300px");
        passwordField.setWidth("300px");
        nameField.setVisible(false);

        submitButton.addClickListener(event -> submit());
        toggleButton.addClickListener(event -> toggleMode());

        add(title, nameField, emailField, passwordField,
                submitButton, toggleButton);
    }

    private void toggleMode() {
        registering = !registering;
        nameField.setVisible(registering);

        if (registering) {
            title.setText("Criar conta");
            submitButton.setText("Cadastrar");
            toggleButton.setText("Já tenho uma conta");
        } else {
            title.setText("Pharmacy");
            submitButton.setText("Entrar");
            toggleButton.setText("Criar uma conta");
        }
    }

    private void submit() {
        String email = emailField.getValue() == null
                ? "" : emailField.getValue().trim();
        String password = passwordField.getValue();

        if (email.isBlank() || password == null || password.isBlank()) {
            Notification.show("Preencha o e-mail e a senha.");
            return;
        }

        try {
            if (registering) {
                String name = nameField.getValue() == null
                        ? "" : nameField.getValue().trim();

                Client client = new Client(name, email, password);
                Client registered = clientService.register(client);

                SessaoUsuario.login(registered);
                Notification.show("Conta criada com sucesso!");
                getUI().ifPresent(ui -> ui.navigate("catalogo"));
            } else {
                Optional<Client> result =
                        clientService.authenticate(email, password);

                if (result.isPresent()) {
                    SessaoUsuario.login(result.get());
                    getUI().ifPresent(ui -> ui.navigate("catalogo"));
                } else {
                    Notification.show("E-mail ou senha incorretos.");
                }
            }
        } catch (IllegalArgumentException e) {
            Notification.show(e.getMessage());
        } catch (Exception e) {
            Notification.show("Não foi possível concluir a operação.");
            e.printStackTrace();
        }
    }
}

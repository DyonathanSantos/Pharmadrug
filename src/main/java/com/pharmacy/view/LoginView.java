package com.pharmacy.view;

import com.pharmacy.model.Client;
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

    // Configuração da tela ( campos )
    private final ClientService clientService;

    private final H2 title = new H2("Pharmacy");

    private final TextField nameField =
            new TextField("Nome");

    private final EmailField emailField =
            new EmailField("E-mail");

    private final PasswordField passwordField =
            new PasswordField("Senha");

    private final Button submitButton =
            new Button("Entrar");

    private final Button toggleButton =
            new Button("Criar uma conta");

    private boolean registering = false;

    //Parte visual da tela, juntando o que foi montado acima.
    public LoginView(ClientService clientService) {
        this.clientService = clientService;

        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        setMaxWidth("100%");
        getStyle().set("margin", "0");

        title.getStyle().set("margin-bottom", "0");

        nameField.setWidth("300px");
        emailField.setWidth("300px");
        passwordField.setWidth("300px");

        nameField.setVisible(false);

        submitButton.addClickListener(event -> submit());
        toggleButton.addClickListener(event -> toggleMode());

        add(
                title,
                nameField,
                emailField,
                passwordField,
                submitButton,
                toggleButton
        );
    }

    // Modo ao qual está, usado para ver se é login ou cadastro.
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

    //Ao clicar no botão  é chamado está parte para verficar as informações e salvar o novo cliente
    // Ou para comparar a senha e realizar o login.
    private void submit() {
        String email = emailField.getValue().trim();
        String password = passwordField.getValue();

        if (email.isBlank() || password.isBlank()) {
            Notification.show("Preencha o e-mail e a senha.");
            return;
        }

        try {
            if (registering) {
                String name = nameField.getValue().trim();

                if (name.isBlank()) {
                    Notification.show("Informe seu nome.");
                    return;
                }

                Client client = new Client(name, email, password);
                clientService.register(client);

                Notification.show(
                        "Conta criada! Agora você pode entrar."
                );

                toggleMode();
                passwordField.clear();

            } else {
                Optional<Client> client =
                        clientService.authenticate(email, password);

                if (client.isPresent()) {
                    Notification.show(
                            "Login realizado! Olá, "
                                    + client.get().getName() + "!"
                    );
                } else {
                    Notification.show(
                            "E-mail ou senha incorretos."
                    );
                }
            }
        } catch (Exception e) {
            Notification.show(
                    "Não foi possível concluir a operação. "
                            + "Verifique os dados."
            );
        }
    }
}
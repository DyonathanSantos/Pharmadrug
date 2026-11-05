/*
 * ORIGEM / CONTRIBUIÇÃO: PROJETO LPR — tela de perfil originada do Projeto LPR e adaptada para usar Client/ClientService do projeto principal.
 * Observação: este comentário registra a procedência do código integrado; não identifica autoria individual.
 */
package com.pharmacy.view;

import com.pharmacy.model.Client;
import com.pharmacy.security.SessaoUsuario;
import com.pharmacy.service.ClientService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route("perfil")
@PageTitle("Meu perfil | Pharmacy")
public class PerfilView extends VerticalLayout implements BeforeEnterObserver {

    private final ClientService clientService;

    public PerfilView(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        Client client = SessaoUsuario.getClient();
        if (client == null) {
            event.forwardTo(LoginView.class);
            return;
        }
        montarTela(client);
    }

    private void montarTela(Client client) {
        removeAll();

        TextField nome = new TextField("Nome");
        nome.setValue(client.getName());

        TextField email = new TextField("E-mail");
        email.setValue(client.getEmail());
        email.setReadOnly(true);

        TextField cargo = new TextField("Tipo de conta");
        cargo.setValue(client.getCargo().name());
        cargo.setReadOnly(true);

        PasswordField senhaAtual =
                new PasswordField("Senha atual (para trocar a senha)");
        PasswordField novaSenha =
                new PasswordField("Nova senha (deixe em branco para manter)");

        Button salvar = new Button("Salvar alterações", event -> {
            try {
                clientService.updateProfile(
                        client, nome.getValue(),
                        senhaAtual.getValue(), novaSenha.getValue());
                senhaAtual.clear();
                novaSenha.clear();
                Notification.show("Perfil atualizado!");
            } catch (IllegalArgumentException e) {
                Notification.show(e.getMessage());
            } catch (Exception e) {
                Notification.show("Não foi possível atualizar o perfil.");
                e.printStackTrace();
            }
        });

        Button voltar = new Button("Catálogo",
                event -> getUI().ifPresent(ui -> ui.navigate("catalogo")));
        Button sair = new Button("Sair", event -> SessaoUsuario.logout());

        add(new H2("Meu perfil"), nome, email, cargo,
                senhaAtual, novaSenha,
                new HorizontalLayout(salvar, voltar, sair));
        setAlignItems(Alignment.START);
    }
}

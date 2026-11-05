/*
 * ORIGEM / CONTRIBUIÇÃO: PROJETO LPR — gerenciamento da sessão Vaadin originado do Projeto LPR e adaptado para armazenar a classe Client do projeto principal.
 * Observação: este comentário registra a procedência do código integrado; não identifica autoria individual.
 */
package com.pharmacy.security;

import com.pharmacy.model.Client;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.server.VaadinSession;

public final class SessaoUsuario {

    private SessaoUsuario() {
    }

    public static void login(Client client) {
        VaadinSession.getCurrent().setAttribute(Client.class, client);
    }

    public static Client getClient() {
        return VaadinSession.getCurrent() == null
                ? null
                : VaadinSession.getCurrent().getAttribute(Client.class);
    }

    public static boolean estaLogado() {
        return getClient() != null;
    }

    public static boolean isAdmin() {
        Client client = getClient();
        return client != null && client.getCargo() != null
                && client.getCargo().name().equals("ADMIN");
    }

    public static void logout() {
        VaadinSession session = VaadinSession.getCurrent();
        if (session != null) {
            session.setAttribute(Client.class, null);
        }
        UI.getCurrent().getPage().setLocation("login");
    }
}

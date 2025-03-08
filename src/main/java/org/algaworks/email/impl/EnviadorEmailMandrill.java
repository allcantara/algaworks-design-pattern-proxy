package org.algaworks.email.impl;

import org.algaworks.email.EnviadorEmail;

import java.util.List;

public class EnviadorEmailMandrill implements EnviadorEmail {

    private List<String> emails;

    public EnviadorEmailMandrill(List<String> emails) {
        this.emails = emails;
    }

    @Override
    public void enviar(String mensagem) {
        emails.forEach(email -> {
            System.out.printf("%nEnviando email com Mandrill para [%s] com a seguinte mensagem: [%s]%n", email, mensagem);
            System.out.println("Email enviado com sucesso!");
        });
    }

}


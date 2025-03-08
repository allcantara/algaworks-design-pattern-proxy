package org.algaworks;

import org.algaworks.email.proxy.EnviadorEmailMandrillProxy;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        EnviadorEmailMandrillProxy proxy = new EnviadorEmailMandrillProxy(List.of("teste1@example.com", "bruno@email.com", "teste2@example.com"));
        proxy.enviar("Olá, tudo bem?");
    }

}
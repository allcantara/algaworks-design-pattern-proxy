package org.algaworks.email.proxy;

import com.thoughtworks.xstream.XStream;
import org.algaworks.email.EnviadorEmail;
import org.algaworks.email.impl.EnviadorEmailMandrill;

import java.util.ArrayList;
import java.util.List;

public class EnviadorEmailMandrillProxy implements EnviadorEmail {

    private final List<String> emails;

    public EnviadorEmailMandrillProxy(List<String> emails) {
        this.emails = new ArrayList<>(emails);
    }

    @Override
    public void enviar(String mensagem) {
        removerEmailsBloqueadosAntesEnviar();
        var enviadorEmailMandrill = new EnviadorEmailMandrill(this.emails);
        enviadorEmailMandrill.enviar(mensagem);
    }

    protected void removerEmailsBloqueadosAntesEnviar() {
        XStream xstream = new XStream();
        xstream.alias("emails", List.class);
        xstream.alias("email", String.class);

        List<String> emailsBloqueados = (List<String>) xstream.fromXML(this.getClass().getResource("/emails-bloqueados.xml"));

        this.emails.removeIf(email -> {
            if (emailsBloqueados.contains(email)) {
                System.out.printf("Atenção: O email [%s] está bloqueado%n", email);
                return true;
            }
            return false;
        });
    }

}

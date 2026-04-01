package mx.florinda.notafiscal;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Incoming;

@ApplicationScoped
public class PagamentoConfirmadoConsumer {

    @Inject
    Hash hash;

    @Incoming("pagamentosConfirmados")
    public void consume(PagamentoConfirmadoEvent event) {
        System.out.println("Hash: " + hash.geraHash(event.toString()));
    }

}

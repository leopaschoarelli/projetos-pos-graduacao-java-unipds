package mx.florinda.leitor;

import mx.florinda.modelo.ItemCardapio;

import java.io.IOException;

/*
  Interface define métodos abstratos (contrato/assinatura)
  Em uma interface, public e abstract são implicitos
 */
public interface LeitorItensCardapio {

    ItemCardapio[] processaArquivo() throws IOException;

    static LeitorItensCardapio criaLeitor(String nomeArquivo) {
        LeitorItensCardapio leitor = null;

        if (nomeArquivo.endsWith(".csv")) {
            leitor = new LeitorItensCardapioCSV(nomeArquivo);
        } else if (nomeArquivo.endsWith(".json")) {
            leitor = new LeitorItensCardapioJSON(nomeArquivo);
        }

        return leitor;
    }
}

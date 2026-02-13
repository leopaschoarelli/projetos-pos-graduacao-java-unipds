package mx.florinda.leitor;

import mx.florinda.modelo.ItemCardapio;

import java.io.IOException;

/*
  Interface define métodos abstratos (contrato/assinatura)
  Em uma interface, public e abstract são implicitos
 */
public interface LeitorItensCardapio {

    ItemCardapio[] processaArquivo(String nomeArquivo) throws IOException;

}

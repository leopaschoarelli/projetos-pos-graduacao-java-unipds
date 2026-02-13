package mx.florinda.leitor;

import mx.florinda.modelo.ItemCardapio;

/*
  Interface define métodos abstratos (contrato/assinatura)
  Em uma interface, public e abstract são implicitos
 */
public interface LeitorItensCardapio {

    ItemCardapio[] processaArquivo();

    static LeitorItensCardapio criaLeitor(String nomeArquivo) {
        //criaLeitor(nomeArquivo); // StackOverFlowError
        LeitorItensCardapio leitor;

        if (nomeArquivo.endsWith(".csv")) {
            leitor = new LeitorItensCardapioCSV(nomeArquivo);
        } else if (nomeArquivo.endsWith(".json")) {
            leitor = new LeitorItensCardapioGSON(nomeArquivo);
        } else {
            // Exception => tem que tratar (try/catch) ou lançar (throw) - checked exception
            // RuntimeExceptionm => não é onbrigado a tratar (try/catch) e nem lançar (throw) - unchecked exception
            throw new IllegalArgumentException("Extensão do Arquivo é inválida: " + nomeArquivo);
        }

        return leitor;
    }
}

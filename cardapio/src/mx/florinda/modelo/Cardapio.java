package mx.florinda.modelo;

import mx.florinda.leitor.FabricaLeitorItensCardapio;
import mx.florinda.leitor.LeitorItensCardapio;

import java.io.IOException;

public class Cardapio {

    private final ItemCardapio[] itens;

    public Cardapio(String nomeArquivo) throws IOException {

        var fabricaLeitor = new FabricaLeitorItensCardapio();
        LeitorItensCardapio leitor = fabricaLeitor.criaLeitor(nomeArquivo);
        if (leitor != null) {
            itens = leitor.processaArquivo(nomeArquivo);
        } else {
            IO.println("A extensão do arquivo é inválido: " + nomeArquivo);
            itens = new ItemCardapio[0];
        }

    }

    public double getSomaDosPrecos() {
        double totalDePrecos = 0.0;
        for (ItemCardapio item : itens) {
            totalDePrecos += item.getPreco();
        }
        return totalDePrecos;
    }

    public int getTotalDeItensEmPromoca() {
        int totalItensEmPromocao = 0;

        for (ItemCardapio item : itens) {
            if (item.isEmPromocao()) {
                totalItensEmPromocao++;
            }
        }

        return totalItensEmPromocao;
    }

    public double getPrimeiroPrecoMaiorQueLimite(double precoLimite) {
        double precoMaiorQueLimite = -1.0;
        for (ItemCardapio item : itens) {

            if (item.getPreco() > precoLimite) {
                precoMaiorQueLimite = item.getPreco();
                break;
            }

        }
        return precoMaiorQueLimite;
    }

    public ItemCardapio getItemPorId(long idSelecionado) {
        return itens[((int) idSelecionado) - 1];
    }

    public ItemCardapio[] getItens() {
        return itens;
    }


}

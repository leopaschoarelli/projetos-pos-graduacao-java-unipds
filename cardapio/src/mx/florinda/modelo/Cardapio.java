package mx.florinda.modelo;

import mx.florinda.modelo.isento.ItemCardapioIsento;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Cardapio {

    private final ItemCardapio[] itens;

    public Cardapio(String nomeArquivo) throws IOException {

        IO.println("Nome do arquivo: " + nomeArquivo);

        Path arquivo = Path.of(nomeArquivo);

        String conteudoArquivo = Files.readString(arquivo);
        String[] linhasArquivo = conteudoArquivo.split("\n");

        itens = new ItemCardapio[linhasArquivo.length];
        for (int i = 0; i < linhasArquivo.length; i++) {
            String linha = linhasArquivo[i];
            if (nomeArquivo.endsWith(".csv")) {
                // trato o csv
                String[] partes = linha.split(";");
                long id = Long.parseLong(partes[0]);
                String nome = partes[1];
                String descricao = partes[2];
                double preco = Double.parseDouble(partes[3]);
                CategoriaCardapio categoria = CategoriaCardapio.valueOf(partes[4]);

                ItemCardapio item;
                boolean impostoIsento = Boolean.parseBoolean(partes[7]);
                if (impostoIsento) {
                    item = new ItemCardapioIsento(id, nome, descricao, preco, categoria);
                } else {
                    item = new ItemCardapio(id, nome, descricao, preco, categoria);
                }

                boolean emPromocao = Boolean.parseBoolean(partes[5]);
                if (emPromocao) {
                    double precoComDesconto = Double.parseDouble(partes[6]);
                    item.setPromocao(precoComDesconto);
                }

                /*
                    long id => 0 ✅
                    String nome => 1 ✅
                    String descricao => 2 ✅
                    double preco => 3 ✅
                    CategoriaCardapio categoria => 4 ✅

                    boolean emPromocao => 5 ✅
                    double precoComDesconto => 6 (opcional) ✅

                    boolean impostoIsento (não é atributo) => 7 ✅
                */
                itens[i] = item;
            } else if (nomeArquivo.endsWith(".json")) {
                // trato o json
            } else {
                IO.println("Arquivo com extensão de arquivo inválida: " + nomeArquivo);
            }
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

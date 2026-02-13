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
                // trato o CSV
                
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

                itens[i] = item;
            } else if (nomeArquivo.endsWith(".json")) {
                // trato o JSON de maneira meia boca por enquanto

                linha = linha.replace("\r", "");
                linha = linha.replace("[","");
                linha = linha.replace("]","");
                linha = linha.replace("{","");
                linha = linha.replace("}","");
                linha = linha.replace("\"","");

                String[] partes = linha.split(",");

                ItemCardapio item;

                String parteId = partes[0];
                String[] propriedadeEValorId = parteId.split(":");
                String valorId = propriedadeEValorId[1].trim();
                long id = Long.parseLong(valorId);

                String parteNome = partes[1];
                String[] propriedadeEValorNome = parteNome.split(":");
                String nome = propriedadeEValorNome[1].trim();

                String parteDescricao = partes[2];
                String[] propriedadeEValorDescricao = parteDescricao.split(":");
                String descricao = propriedadeEValorDescricao[1].trim();

                String partePreco = partes[3];
                String[] propriedadeEValorPreco = partePreco.split(":");
                String valorPreco = propriedadeEValorPreco[1].trim();
                double preco = Double.parseDouble(valorPreco);

                String parteCategoria = partes[4];
                String[] propriedadeEValorCategoria = parteCategoria.split(":");
                String valorCategoria = propriedadeEValorCategoria[1].trim();
                CategoriaCardapio categoria = CategoriaCardapio.valueOf(valorCategoria);

                String parteImpostoIsento = partes[7];
                String[] propriedadeEValorImpostoIsento = parteImpostoIsento.split(":");
                String valorImpostoIsento = propriedadeEValorImpostoIsento[1].trim();
                boolean impostoIsento = Boolean.parseBoolean(valorImpostoIsento);
                if  (impostoIsento) {
                    item = new ItemCardapioIsento(id, nome, descricao, preco, categoria);
                } else {
                    item = new ItemCardapio(id, nome, descricao, preco, categoria);
                }

                String parteEmPromocao = partes[5];
                String[] propriedadeEValorEmPromocao = parteEmPromocao.split(":");
                String valorEmPromocao = propriedadeEValorEmPromocao[1].trim();
                boolean emPromocao = Boolean.parseBoolean(valorEmPromocao);
                if (emPromocao) {
                    String partePrecoComDesconto = partes[6];
                    String[] propriedadeEValorPrecoComDesconto = partePrecoComDesconto.split(":");
                    String valorPrecoComDesconto = propriedadeEValorPrecoComDesconto[1].trim();
                    double precoComDesconto = Double.parseDouble(valorPrecoComDesconto);
                    item.setPromocao(precoComDesconto);
                }

                itens[i] = item;

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

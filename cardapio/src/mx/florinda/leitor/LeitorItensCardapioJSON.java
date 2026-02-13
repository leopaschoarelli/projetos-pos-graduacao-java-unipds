package mx.florinda.leitor;

import mx.florinda.modelo.CategoriaCardapio;
import mx.florinda.modelo.ItemCardapio;
import mx.florinda.modelo.isento.ItemCardapioIsento;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class LeitorItensCardapioJSON implements LeitorItensCardapio {

    @Override
    public ItemCardapio[] processaArquivo(String nomeArquivo) throws IOException {
        Path arquivo = Path.of(nomeArquivo);

        String conteudoArquivo = Files.readString(arquivo);
        String[] linhasArquivo = conteudoArquivo.split("\n");

        ItemCardapio[] itens = new ItemCardapio[linhasArquivo.length];
        for (int i = 0; i < linhasArquivo.length; i++) {
            String linha = linhasArquivo[i];

            linha = linha.replace("\r", "");
            linha = linha.replace("[", "");
            linha = linha.replace("]", "");
            linha = linha.replace("{", "");
            linha = linha.replace("}", "");
            linha = linha.replace("\"", "");

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
            if (impostoIsento) {
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

        }

        return itens;
    }

}

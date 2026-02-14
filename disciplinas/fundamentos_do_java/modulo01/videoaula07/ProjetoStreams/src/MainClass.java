import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MainClass {

    public static void main(String[] args) {
        List<Veiculo> lista = new ArrayList<>() {{
           add(new Veiculo("Corsa", "Cinza", 25000, 1998, 160));
           add(new Veiculo("Corolla", "Prata", 70000, 2020, 200));
           add(new Veiculo("Corolla", "Preto", 100000, 2026, 210));
           add(new Veiculo("X1", "Branco", 250000, 2022, 250));
           add(new Veiculo("GLA200", "Prata", 300000, 2025, 280));
        }};

        //lista.stream().forEach(v -> System.out.println(v));
        List<Veiculo> listaOrdenada = lista.stream()
                                           .sorted(Comparator.comparing(Veiculo::getMarca).reversed())
                                           .collect(Collectors.toList()); // mutavel
                                           //.toList(); // imutavel
        System.out.println(listaOrdenada);
        listaOrdenada.add(new Veiculo("Gol", "Azul", 18000, 1995, 190));

        List<Veiculo> corollas = lista.stream()
                                      .filter(v -> v.getMarca().equalsIgnoreCase("Corolla"))
                                      .toList();
        System.out.println(corollas);

        double precoMedio = lista.stream().mapToDouble(Veiculo::getPreco).average().orElse(0.0);
        System.out.println("Preço Médio: " + precoMedio);

        double maximo = lista.stream().mapToDouble(Veiculo::getPreco).max().orElse(0.0);
        System.out.println("Preço máximo: " + maximo);

        double minimo = lista.stream().mapToDouble(Veiculo::getPreco).min().orElse(0.0);
        System.out.println("Preço minímo: " + minimo);

        double mediaDosCorollas = lista.stream()
                                       .filter(v -> v.getMarca().equalsIgnoreCase("Corolla"))
                                       .mapToDouble(Veiculo::getPreco)
                                       .average()
                                       .orElse(0.0);
        System.out.println("Média de Preços dos Corollas: " + mediaDosCorollas);

        List<Veiculo> listaCorolla = lista.stream()
                                          .filter(v -> v.getMarca().equalsIgnoreCase("Corolla"))
                                          .map(v -> converterParaMaiusculos(v))
                                          .toList();
        System.out.println(listaCorolla);
    }

    public static Veiculo converterParaMaiusculos(Veiculo v) {
        return new Veiculo(v.getMarca().toUpperCase(),
                           v.getCor().toUpperCase(),
                           v.getPreco(),
                           v.getAno(),
                           v.getVelMaxima());
    }

}

package mx.florinda.cardapio;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        Database database = new Database();
        List<ItemCardapio> itens = database.listaDeItensCardapio();

        // saber quantos itens de cada categoria realmente tem no cardapio
        // Categoria => Quantidade
        //Map<ItemCardapio.CategoriaCardapio, Integer> itensPorCategoria = new HashMap<>();
        //Map<ItemCardapio.CategoriaCardapio, Integer> itensPorCategoria = new LinkedHashMap<>();
        Map<ItemCardapio.CategoriaCardapio, Integer> itensPorCategoria = new TreeMap<>();
        for (ItemCardapio item : itens) {
            ItemCardapio.CategoriaCardapio categoria = item.categoria();
            if (!itensPorCategoria.containsKey(categoria)) {
                itensPorCategoria.put(categoria, 1);
            } else {
                Integer quantidadeAnterior = itensPorCategoria.get(categoria);
                itensPorCategoria.put(categoria, quantidadeAnterior + 1);
            }
        }
        System.out.println(itensPorCategoria);
        System.out.println("-------------------------------------------");
        itens.stream().collect(Collectors.groupingBy(
                ItemCardapio::categoria,
                //LinkedHashMap::new,
                TreeMap::new,
                Collectors.counting()
        ))
        .forEach((chave, valor) -> System.out.println(chave + "=>" + valor));

        // Exercicio de desafio da aula:
        System.out.println("------------------------------");
        var itemBuscaPorId = database.buscaPorId(10L);
        System.out.println("Buscando produto por ID: " + itemBuscaPorId);

        /*
        // saber quais as categorias realmente tenho no cardapio
        //itens.stream().map(ItemCardapio::categoria).forEach(System.out::println);

        //Set<ItemCardapio.CategoriaCardapio> categegorias = new HashSet<>();
        //Set<ItemCardapio.CategoriaCardapio> categegorias = new LinkedHashSet<>();
        Comparator<ItemCardapio.CategoriaCardapio> comparadorPorNome = Comparator.comparing(ItemCardapio.CategoriaCardapio::name);
        Set<ItemCardapio.CategoriaCardapio> categegorias = new TreeSet<>(comparadorPorNome);
        for (ItemCardapio item : itens) {
            ItemCardapio.CategoriaCardapio cardapio = item.categoria();
            categegorias.add(cardapio);
        }

        for (ItemCardapio.CategoriaCardapio categoria : categegorias) {
            System.out.println(categoria);
        }

        System.out.println("-------------------------------------------");

        itens.stream()
             .map(ItemCardapio::categoria)
             //.collect(Collectors.toCollection(Collectors.toSet()))
             //.collect(Collectors.toCollection(LinkedHashSet::new))
             //.collect(Collectors.toCollection(TreeSet::new))
             .collect(Collectors.toCollection(() -> new TreeSet<>(comparadorPorNome)))
             .forEach(System.out::println);
         */
        /*
        for (ItemCardapio item : itens) {
            System.out.println(item);
        }

        ItemCardapio item = itens.get(4);
        System.out.println(item.nome());

        System.out.println(itens.size());
        itens.remove(1);
        System.out.println(itens.size());

        itens.forEach(System.out::println);
         */
    }
}

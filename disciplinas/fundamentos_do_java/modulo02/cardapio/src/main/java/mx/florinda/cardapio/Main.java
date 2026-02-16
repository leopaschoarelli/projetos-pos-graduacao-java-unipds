package mx.florinda.cardapio;

import java.util.*;

import static mx.florinda.cardapio.ItemCardapio.CategoriaCardapio.*;

public class Main {

    public static void main(String[] args) {

        Database database = new Database();

        // PRECISO DE UM HISTORICO DE VISUALIZAÇÃO DO CARDAPIO
        HistoricoVisualizacao historico = new HistoricoVisualizacao(database);
        historico.registrarVisualizacao(1L);
        historico.registrarVisualizacao(2L);
        historico.registrarVisualizacao(4L);
        historico.registrarVisualizacao(6L);

        System.out.println("-------------------------------------------");

        historico.mostrarTotalItensVisualizados();
        historico.listarVisualizacoes();

    }

    private void codigosExemplosTeste() {
        Database database = new Database();
        List<ItemCardapio> itens = database.listaDeItensCardapio();

        itens.forEach(System.out::println);

        System.out.println("----------------------------------------------------");

        Optional<ItemCardapio> optionalItem = database.itemCardapioPorId(1L);
        String mensagem = optionalItem.map(ItemCardapio::toString).orElse("Não encontrado");
        System.out.println(mensagem);

        System.out.println("----------------------------------------------------");

        // PRECISA MANTER AS CATEGORIAS QUE ESTÃO EM PROMOÇÃO
        Set<ItemCardapio.CategoriaCardapio> categoriasEmPromocao = new TreeSet<>(); // ORDENADO
        categoriasEmPromocao.add(SOBREMESA);
        categoriasEmPromocao.add(ENTRADAS);
        categoriasEmPromocao.forEach(System.out::println);
        System.out.println("----------------------------------------------------");

        Set<ItemCardapio.CategoriaCardapio> categoriaCardapios2 = Set.of(SOBREMESA, ENTRADAS);
        categoriaCardapios2.forEach(System.out::println);
        //categoriaCardapios2.add(PRATOS_PRINCIPAIS); // Não permite incluir
        System.out.println("----------------------------------------------------");

        Set<ItemCardapio.CategoriaCardapio> categoriaCardapios3 = EnumSet.of(SOBREMESA, ENTRADAS);
        categoriaCardapios3.add(PRATOS_PRINCIPAIS);
        categoriaCardapios3.forEach(System.out::println);
        System.out.println("----------------------------------------------------");

        // PRECISO DAS DESCRIÇÕES ASSOCIADAS AS CATEGORIAS EM PROMOÇÃO
        Map<ItemCardapio.CategoriaCardapio, String> promocoes = new EnumMap<>(ItemCardapio.CategoriaCardapio.class);
        promocoes.put(SOBREMESA, "O doce perfeito para você!");
        promocoes.put(ENTRADAS, "Começe sua refeição com um toque de sabor!");
        System.out.println(promocoes);

        /*
        if (optionalItem.isPresent()) {
            ItemCardapio item = optionalItem.get();
            System.out.println(item);
        } else {
            System.out.println("Não encontrado");
        }
         */

        /*
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
         */

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

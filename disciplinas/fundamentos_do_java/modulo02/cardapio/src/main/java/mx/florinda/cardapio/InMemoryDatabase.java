package mx.florinda.cardapio;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentSkipListMap;

public class InMemoryDatabase implements Database {

    // private final Map<Long, ItemCardapio> itensPorId = new HashMap<>(); // Não Thread-Safe
    //private final Map<Long, ItemCardapio> itensPorId = new ConcurrentHashMap<>(); // Thread-Safe e eficiente
    private final Map<Long, ItemCardapio> itensPorId = new ConcurrentSkipListMap<>(); // Thread-Safe e eficiente e mantém os itens ordenados
    //private final Map<ItemCardapio, BigDecimal> auditoriaPrecos = new HashMap<>();
    private final Map<ItemCardapio, BigDecimal> auditoriaPrecos = new IdentityHashMap<>();

    public InMemoryDatabase() {
        /*
        var refrescoDoChaves = new ItemCardapio(1L, "Refresco do Chaves",
                "Suco de limão que parece de tamarindo e tem gosto de groselha.",
                BEBIDAS, new BigDecimal("2.99"), null);
        itensPorId.put(1L, refrescoDoChaves);

        var sanduicheDoChaves = new ItemCardapio(2L, "Sanduíche de Presunto do Chaves",
                "Sanduíche de presunto simples, mas feito com muito amor.",
                PRATOS_PRINCIPAIS, new BigDecimal("3.50"), new BigDecimal("2.99"));
        itensPorId.put(2L, sanduicheDoChaves);

        var tortaDaDonaFlorinda = new ItemCardapio(5L, "Torta de Frango da Dona Florinda",
                "Torta de frango com recheio cremoso e massa crocante.",
                PRATOS_PRINCIPAIS, new BigDecimal("12.99"), new BigDecimal("10.99"));
        itensPorId.put(5L, tortaDaDonaFlorinda);

        var pipocaDoQuico = new ItemCardapio(6L, "Pipoca do Quico",
                "Balde de pipoca preparado com carinho pelo Quico.",
                PRATOS_PRINCIPAIS, new BigDecimal("4.99"), new BigDecimal("3.99"));
        itensPorId.put(6L, pipocaDoQuico);

        var aguaDeJamaica = new ItemCardapio(7L, "Água de Jamaica",
                "Água aromatizada com hibisco e toque de açúcar.",
                BEBIDAS, new BigDecimal("2.50"), new BigDecimal("2.00"));
        itensPorId.put(7L, aguaDeJamaica);

        var cafeDaDonaFlorinda = new ItemCardapio(8L, "Café da Dona Florinda",
                "Café forte para começar o dia com energia.",
                BEBIDAS, new BigDecimal("1.99"), new BigDecimal("1.50"));
        itensPorId.put(8L, cafeDaDonaFlorinda);

        var churrosDoChaves = new ItemCardapio(9L, "Churros do Chaves",
                "Churros recheados com doce de leite, clássicos e irresistíveis.",
                SOBREMESA, new BigDecimal("4.99"), new BigDecimal("3.99"));
        itensPorId.put(9L, churrosDoChaves);

        var gelatinaDoNhonho = new ItemCardapio(10L, "Gelatina Colorida do Nhonho",
                "Gelatina de várias cores, a sobremesa favorita do Nhonho.",
                SOBREMESA, new BigDecimal("3.50"), new BigDecimal("2.99"));
        itensPorId.put(10L, gelatinaDoNhonho);

        var boloDaDonaClotilde = new ItemCardapio(11L, "Bolo de Chocolate da Dona Clotilde",
                "Bolo de chocolate com cobertura de brigadeiro.",
                SOBREMESA, new BigDecimal("5.99"), new BigDecimal("4.99"));
        itensPorId.put(11L, boloDaDonaClotilde);
         */
    }

    @Override
    public List<ItemCardapio> listaDeItensCardapio() {
        return new ArrayList<>(itensPorId.values());
    }

    // Exercicio de desafio da aula:
    @Override
    public Optional<ItemCardapio> itemCardapioPorId(Long id) {
        ItemCardapio itemCardapio = itensPorId.get(id);
        return Optional.ofNullable(itemCardapio);
    }

    @Override
    public boolean removeItemCardapio(Long itemId) {
        ItemCardapio itemCardapioRemovido = itensPorId.remove(itemId);
        return itemCardapioRemovido != null;
    }

    @Override
    public boolean alterarPrecoItemCardapio(Long itemId, BigDecimal novoPreco) {
        ItemCardapio itemAntigo = itensPorId.get(itemId);
        if (itemId == null) {
            return false;
        }
        ItemCardapio itemComPrecoAlterado = itemAntigo.alterarPreco(novoPreco);
        itensPorId.put(itemId, itemComPrecoAlterado);
        auditoriaPrecos.put(itemAntigo, novoPreco);
        return true;
    }

    public void imprimirRastroAuditoriaPrecos() {
        System.out.println("\n Auditoria de preços: ");
        auditoriaPrecos.forEach((itemAntigo, novoPreco) ->
                System.out.printf(" - %s: %s -> %s\n", itemAntigo.nome(), itemAntigo.preco(), novoPreco));
        System.out.println();
    }

    @Override
    public void adicionaItemCardapio(ItemCardapio itemCardapio) {
        itensPorId.put(itemCardapio.id(), itemCardapio);
    }

    @Override
    public int totalItensCardapio() {
        return itensPorId.size();
    }

}

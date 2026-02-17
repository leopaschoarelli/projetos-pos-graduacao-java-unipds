package mx.florinda.cardapio;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface Database {
    List<ItemCardapio> listaDeItensCardapio();

    // Exercicio de desafio da aula:
    Optional<ItemCardapio> itemCardapioPorId(Long id);

    boolean removeItemCardapio(Long itemId);

    boolean alterarPrecoItemCardapio(Long itemId, BigDecimal novoPreco);

    void adicionaItemCardapio(ItemCardapio itemCardapio);

    int totalItensCardapio();

}

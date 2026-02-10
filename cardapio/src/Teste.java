void main() {

    Cardapio cardapio = new Cardapio();

    String linha = IO.readln("Digite um ID de um item de cardápio: ");
    long idSelecionado = Long.parseLong(linha);

    ItemCardapio itemSelecionado = cardapio.itens[((int) idSelecionado) - 1];

    IO.println("--------------------------------------------------------------------------------------------");
    IO.println("== Item Cardápio ==");
    IO.println("ID: " + itemSelecionado.id);
    IO.println("Nome: " + itemSelecionado.nome);
    IO.println("Descrição: " + itemSelecionado.descricao);

    if (itemSelecionado.emPromocao) {

        var porcentagemDesconto = itemSelecionado.calculaPorcentagemDesconto();
        IO.println("Item Em Promoção! 🤑");
        IO.println("Preço: de " + itemSelecionado.preco + " por " + itemSelecionado.precoComDesconto);
        IO.println("Porcentagem de desconto: " + porcentagemDesconto);

    } else {

        IO.println("Preço: " + itemSelecionado.preco);
        IO.println("Item não está em promoção");

    }

    IO.println("Categoria: " + itemSelecionado.obtemNomeCategoria());

    /*
    Taxa de Imposto é 10% para a maioria dos produtos
    mas existem Produtos que são isentos (não tem imposto)
    É calculado em cima do preço efetivo, ou seja, preço ou preço com desconto
    Valor do impost é Maior ou Igual (>=) a Taxa * Preço Efetivo

    Pipoca, Churros e Tacos são isentos .
    */
    IO.println("Imposto: " + itemSelecionado.calculaImposto());
    IO.println("--------------------------------------------------------------------------------------------");
    IO.println("Soma dos preços: " + cardapio.obtemSomaDosPrecos());
    IO.println("Total de itens em promoção: " + cardapio.obtemTotalDeItensEmPromoca());
    double precoLimite = 10.0;
    IO.println("O primeiro preço que é maior que " + precoLimite + ": " + cardapio.obtemPrimeiroPrecoMaiorQueLimite(precoLimite));

    for (ItemCardapio item : cardapio.itens) {
        if (item.preco <= precoLimite) {
            IO.println("Preço menor que " + precoLimite + ": " + item.preco);
        }
    }

    IO.println("--------------------------------------------------------------------------------------------");

}

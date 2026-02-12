import mx.florinda.modelo.Cardapio;
import mx.florinda.modelo.ItemCardapio;

void main() throws IOException {

    /*
    private - só na mesma classe => Manutenabilidade (encapsulamento)
    padrão (sem nada) - só no mesmo pacote
    protected - nomesmo pacote ou em classes filhas mesmo se tiverem em outro pacote
    public - qualquer classe de qualquer pacote
     */

    String nomeArquivo = IO.readln("Digite um nome de arquivo de itens de cardápio: ");

    Cardapio cardapio = new Cardapio(nomeArquivo);

    String linha = IO.readln("Digite um ID de um item de cardápio: ");
    long idSelecionado = Long.parseLong(linha);

    ItemCardapio itemSelecionado = cardapio.getItemPorId(idSelecionado);

    IO.println("--------------------------------------------------------------------------------------------");
    IO.println("== Item Cardápio ==");
    IO.println("ID: " + itemSelecionado.getId());
    IO.println("Nome: " + itemSelecionado.getNome());
    IO.println("Descrição: " + itemSelecionado.getDescricao());

    if (itemSelecionado.isEmPromocao()) {

        var porcentagemDesconto = itemSelecionado.getPorcentagemDesconto();
        IO.println("Item Em Promoção! 🤑");
        IO.println("Preço: de " + itemSelecionado.getPreco() + " por " + itemSelecionado.getPrecoComDesconto());
        IO.println("Porcentagem de desconto: " + porcentagemDesconto);

    } else {

        IO.println("Preço: " + itemSelecionado.getPreco());
        IO.println("Item não está em promoção");

    }

    IO.println("Categoria: " + itemSelecionado.getCategoria());

    /*
    Taxa de Imposto é 10% para a maioria dos produtos
    mas existem Produtos que são isentos (não tem imposto)
    É calculado em cima do preço efetivo, ou seja, preço ou preço com desconto
    Valor do impost é Maior ou Igual (>=) a Taxa * Preço Efetivo

    Pipoca, Churros e Tacos são isentos .
    */
    IO.println("Imposto: " + itemSelecionado.getImposto());
    IO.println("--------------------------------------------------------------------------------------------");
    IO.println("Soma dos preços: " + cardapio.getSomaDosPrecos());
    IO.println("Total de itens em promoção: " + cardapio.getTotalDeItensEmPromoca());
    double precoLimite = 10.0;
    IO.println("O primeiro preço que é maior que " + precoLimite + ": " + cardapio.getPrimeiroPrecoMaiorQueLimite(precoLimite));

    for (ItemCardapio item : cardapio.getItens()) {
        if (item.getPreco() <= precoLimite) {
            IO.println("Preço menor que " + precoLimite + ": " + item.getPreco());
        }
    }

    IO.println("--------------------------------------------------------------------------------------------");

}

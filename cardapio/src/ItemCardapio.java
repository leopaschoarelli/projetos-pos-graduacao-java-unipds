class ItemCardapio {

    // atributos
    long id;
    String nome;
    String descricao;
    boolean emPromocao;
    double preco;
    double precoComDesconto;
    CategoriaCardapio categoria;

    // construtor
    ItemCardapio(long id, String nome, String descricao, double preco, CategoriaCardapio categoria) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.categoria = categoria;
    }

    // metodos
    double calculaPorcentagemDesconto() {
        return (preco - precoComDesconto) / preco * 100;
    }

    CategoriaCardapio obtemNomeCategoria() {
        return categoria;
    }

    void definePromocao(double precoComDesconto) {
        emPromocao = true;
        this.precoComDesconto = precoComDesconto;
    }

    double calculaImposto() {
        double imposto;
        if (emPromocao) {
            imposto = precoComDesconto * 0.1;
        } else {
            imposto = preco * 0.1;
        }
        return imposto;
    }

}

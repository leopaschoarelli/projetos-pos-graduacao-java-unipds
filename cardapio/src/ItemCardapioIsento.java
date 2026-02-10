class ItemCardapioIsento extends ItemCardapio  {

    // construtor
    ItemCardapioIsento(long id, String nome, String descricao, double preco, CategoriaCardapio categoria) {
        super(id, nome, descricao, preco, categoria);
    }

    // reescrita de metodo (override)
    //@Override // anotação opcional
    double calculaImposto() {
        return 0.0;
    }

}
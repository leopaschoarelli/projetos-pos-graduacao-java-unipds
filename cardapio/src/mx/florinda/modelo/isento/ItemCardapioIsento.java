package mx.florinda.modelo.isento;

import mx.florinda.modelo.CategoriaCardapio;
import mx.florinda.modelo.ItemCardapio;

public class ItemCardapioIsento extends ItemCardapio {

    // construtor
    public ItemCardapioIsento(long id, String nome, String descricao, double preco, CategoriaCardapio categoria) {
        super(id, nome, descricao, preco, categoria);
    }

    // reescrita de metodo (override)
    //@Override // anotação opcional
    public double getImposto() {
        return 0.0;
    }

}
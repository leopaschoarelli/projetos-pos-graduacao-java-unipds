package core;

public class Produto {

    private int codigo;
    private String descricao;
    private float preco;
    private int estoque;

    public Produto(int codigo, String descricao, float preco, int estoque) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.preco = preco;
        this.estoque = estoque;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public float getPreco() {
        return preco;
    }

    public int getEstoque() {
        return estoque;
    }

}

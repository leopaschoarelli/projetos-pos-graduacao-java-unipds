package br.com.unipds.leilao.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQuery(name="Item.contaTotalDeItensCadastrados", query = "select count(*) from Item item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Size(min = 5)
    private String nome;

    private BigDecimal precoInicial;

    @OneToOne
    private Leilao leilao;

    @ElementCollection
    @CollectionTable(name = "CategoriasDoItem")
    private List<String> categorias = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getPrecoInicial() {
        return precoInicial;
    }

    public void setPrecoInicial(BigDecimal precoInicial) {
        this.precoInicial = precoInicial;
    }

    public Leilao getLeilao() {
        return leilao;
    }

    public void setLeilao(Leilao leilao) {
        this.leilao = leilao;
    }

    public void aumentaPreco(BigDecimal aumento) {
        this.precoInicial = this.precoInicial.add(aumento);
    }

    public List<String> getCategorias() {
        return categorias;
    }

    public void adicionaCategoria(String categoria) {
        this.categorias.add(categoria);
    }

}

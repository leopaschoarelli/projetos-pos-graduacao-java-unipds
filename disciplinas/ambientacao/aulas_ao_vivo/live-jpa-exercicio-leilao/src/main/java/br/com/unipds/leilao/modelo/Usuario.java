package br.com.unipds.leilao.modelo;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Usuario {

    @Id
    private String email;

    private String nome;

    private String sobrenome;

    @Embedded
    private Endereco endereco;

    @ManyToMany
    @JoinTable(name = "Usuario_FormaDePagamento",
            joinColumns = @JoinColumn(name = "usuario_email"),
            inverseJoinColumns = @JoinColumn(name = "formasDePagamento_id"))
    private List<FormaDePagamento> formasDePagamento = new ArrayList<>();

    @OneToMany(mappedBy = "usuario")
    private List<Lance> lances = new ArrayList<>();

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public List<FormaDePagamento> getFormasDePagamento() {
        return formasDePagamento;
    }

    public void adicionaFormaDePagamento(FormaDePagamento forma) {
        this.formasDePagamento.add(forma);
    }

    public List<Lance> getLances() {
        return lances;
    }

    public void adicionaLance(Lance lance) {
        this.lances.add(lance);
    }

}

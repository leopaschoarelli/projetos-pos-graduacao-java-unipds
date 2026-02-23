package br.com.unipds.leilao.modelo;

import jakarta.persistence.*;

import java.util.Calendar;

@Entity
public class Leilao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String codigo;

    private Calendar dataHoraInicio;

    private Calendar dataHoraFim;

    @OneToOne(mappedBy = "leilao", cascade = CascadeType.ALL)
    private Item item;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Calendar getDataHoraInicio() {
        return dataHoraInicio;
    }

    public void setDataHoraInicio(Calendar dataHoraInicio) {
        this.dataHoraInicio = dataHoraInicio;
    }

    public Calendar getDataHoraFim() {
        return dataHoraFim;
    }

    public void setDataHoraFim(Calendar dataHoraFim) {
        this.dataHoraFim = dataHoraFim;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

}
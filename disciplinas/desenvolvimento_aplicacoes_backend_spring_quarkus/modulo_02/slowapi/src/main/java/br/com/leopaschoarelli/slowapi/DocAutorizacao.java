package br.com.leopaschoarelli.slowapi;

import java.time.LocalDateTime;

public class DocAutorizacao {

    private Long codigoCliente;
    private Integer codigoServico;
    private LocalDateTime dataHora;
    private String chaveAutorizacao;

    public Long getCodigoCliente() {
        return codigoCliente;
    }
    public void setCodigoCliente(Long codigoCliente) {
        this.codigoCliente = codigoCliente;
    }
    public Integer getCodigoServico() {
        return codigoServico;
    }
    public void setCodigoServico(Integer codigoServico) {
        this.codigoServico = codigoServico;
    }
    public LocalDateTime getDataHora() {
        return dataHora;
    }
    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }
    public String getChaveAutorizacao() {
        return chaveAutorizacao;
    }
    public void setChaveAutorizacao(String chaveAutorizacao) {
        this.chaveAutorizacao = chaveAutorizacao;
    }

}

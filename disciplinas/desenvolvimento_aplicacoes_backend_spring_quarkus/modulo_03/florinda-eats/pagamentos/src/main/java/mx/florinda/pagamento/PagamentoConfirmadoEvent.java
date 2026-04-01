package mx.florinda.pagamento;

import java.math.BigDecimal;

public class PagamentoConfirmadoEvent {

    private Long pagamentoId;
    private Long pedidoId;
    private BigDecimal valor;

    public PagamentoConfirmadoEvent(Long pagamentoId, Long pedidoId, BigDecimal valor) {
        this.pagamentoId = pagamentoId;
        this.pedidoId = pedidoId;
        this.valor = valor;
    }

    public Long getPagamentoId() {
        return pagamentoId;
    }

    public Long getPedidoId() {
        return pedidoId;
    }

    public BigDecimal getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return "PagamentoConfirmadoEvent{" +
                "pagamentoId=" + pagamentoId +
                ", pedidoId=" + pedidoId +
                ", valor=" + valor +
                '}';
    }

}

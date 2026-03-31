package br.com.leopaschoarelli.reactiveapi.service;

import br.com.leopaschoarelli.reactiveapi.model.DocFiscal;

public interface IDocFiscalService {

    public void realizarAutorizacaoAPIExterna(Long idCliente, Integer idServico, String protocolo);
    public DocFiscal consultarPorProtocolo(String protocolo);

}

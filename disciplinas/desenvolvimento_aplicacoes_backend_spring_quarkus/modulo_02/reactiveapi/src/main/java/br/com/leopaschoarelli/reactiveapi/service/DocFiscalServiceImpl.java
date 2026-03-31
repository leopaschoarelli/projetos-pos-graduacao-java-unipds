package br.com.leopaschoarelli.reactiveapi.service;

import br.com.leopaschoarelli.reactiveapi.model.DocFiscal;
import br.com.leopaschoarelli.reactiveapi.repo.DocFiscalRepo;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class DocFiscalServiceImpl implements IDocFiscalService {

    private DocFiscalRepo repo;
    private WebClient webClient;

    public DocFiscalServiceImpl(DocFiscalRepo repo, WebClient webClient) {
        this.repo = repo;
        this.webClient = webClient;
    }

    @Override
    public void realizarAutorizacaoAPIExterna(Long idCliente, Integer idServico, String protocolo) {
        webClient.get()
                .uri("http://localhost:8081/api/v1/autorizacao/"+idCliente+"?servico="+idServico)
                .retrieve()
                .bodyToMono(String.class)
                .doOnNext((resposta) -> {
                    System.out.println("DEBUG - Solicitação atendida pela API Externa");
                    DocFiscal docFiscal = new DocFiscal();
                    docFiscal.setProtocolo(protocolo);
                    docFiscal.setDocumento(resposta);
                    repo.save(docFiscal);
                })
                .doOnError((erro) -> {
                    System.out.println("Erro: " + erro);
                })
                .subscribe();
    }

    @Override
    public DocFiscal consultarPorProtocolo(String protocolo) {
        return repo.findByProtocolo(protocolo).orElse(null);
    }
}

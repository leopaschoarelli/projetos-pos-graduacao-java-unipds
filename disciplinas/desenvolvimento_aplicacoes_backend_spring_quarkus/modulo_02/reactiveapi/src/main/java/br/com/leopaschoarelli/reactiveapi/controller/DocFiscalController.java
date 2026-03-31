package br.com.leopaschoarelli.reactiveapi.controller;

import br.com.leopaschoarelli.reactiveapi.dto.ProtocoloDTO;
import br.com.leopaschoarelli.reactiveapi.dto.RequisicaoDTO;
import br.com.leopaschoarelli.reactiveapi.model.DocFiscal;
import br.com.leopaschoarelli.reactiveapi.service.IDocFiscalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
public class DocFiscalController {

    private IDocFiscalService service;

    private DocFiscalController(IDocFiscalService service) {
        this.service = service;
    }

    @GetMapping("/consultar/{protocolo}")
    public ResponseEntity<DocFiscal> consultar(@PathVariable String protocolo) {
        return ResponseEntity.ok(service.consultarPorProtocolo(protocolo));
    }

    @PostMapping("/solicitar")
    public Mono<ResponseEntity<ProtocoloDTO>> solicitar(@RequestBody RequisicaoDTO req) {
        String idProtocolo = UUID.randomUUID().toString();
        service.realizarAutorizacaoAPIExterna(req.idCliente(), req.idServico(), idProtocolo);
        return Mono.just(ResponseEntity.accepted().body(new ProtocoloDTO(idProtocolo)));
    }

}

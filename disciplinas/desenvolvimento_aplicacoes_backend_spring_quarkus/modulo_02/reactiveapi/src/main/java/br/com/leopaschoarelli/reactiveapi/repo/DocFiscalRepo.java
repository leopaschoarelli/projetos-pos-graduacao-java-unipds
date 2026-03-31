package br.com.leopaschoarelli.reactiveapi.repo;

import java.util.Optional;

import org.springframework.data.repository.ListCrudRepository;

import br.com.leopaschoarelli.reactiveapi.model.DocFiscal;

public interface DocFiscalRepo extends ListCrudRepository<DocFiscal, Integer>{

    public Optional<DocFiscal> findByProtocolo(String protocolo);

}

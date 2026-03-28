package br.com.leopaschoarelli.account.repo;

import br.com.leopaschoarelli.account.model.Transaction;
import org.springframework.data.repository.ListCrudRepository;

public interface TransactionRepo extends ListCrudRepository<Transaction, Integer> {

}

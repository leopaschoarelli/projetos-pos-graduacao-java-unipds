package br.com.leopaschoarelli.account.repo;

import br.com.leopaschoarelli.account.model.Account;
import org.springframework.data.repository.ListCrudRepository;

public interface AccountRepo extends ListCrudRepository<Account, Integer> {

}

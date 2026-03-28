package br.com.leopaschoarelli.account.service;

import br.com.leopaschoarelli.account.dto.TransferDTO;
import br.com.leopaschoarelli.account.exception.InvalidAccountException;
import br.com.leopaschoarelli.account.model.Account;
import br.com.leopaschoarelli.account.model.Transaction;
import br.com.leopaschoarelli.account.repo.AccountRepo;
import br.com.leopaschoarelli.account.repo.TransactionRepo;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TransferService implements ITransferService {

    private AccountRepo accountRepo;
    private TransactionRepo transactionRepo;

    public TransferService(AccountRepo accountRepo, TransactionRepo transactionRepo) {
        this.accountRepo = accountRepo;
        this.transactionRepo = transactionRepo;
    }

    @Override
    @Transactional
    public Transaction transferValues(TransferDTO transferDto) {
        Account src = accountRepo.findById(transferDto.debitAccountNumber())
                .orElseThrow(() -> new InvalidAccountException("Account #"+transferDto.debitAccountNumber()+" does not exists"));
        Account dst = accountRepo.findById(transferDto.creditAccountNumber())
                .orElseThrow(() -> new InvalidAccountException("Account #"+transferDto.creditAccountNumber()+ " does not exists"));

        dst.setBalance(dst.getBalance() + transferDto.amount());
        accountRepo.save(dst);
        System.out.println("Account "+dst.getNumber()+ " New Balance $ "+dst.getBalance());
        src.setBalance(src.getBalance() - transferDto.amount());
        accountRepo.save(src);
        System.out.println("Account "+src.getNumber()+ " New Balance $ "+src.getBalance());

        Transaction transaction = new Transaction();
        transaction.setDebitAccount(src);
        transaction.setCreditAccount(dst);
        transaction.setAmount(transferDto.amount());
        transaction.setTimestamp(LocalDateTime.now());
        return transactionRepo.save(transaction);
    }

}

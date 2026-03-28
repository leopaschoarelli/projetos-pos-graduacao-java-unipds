package br.com.leopaschoarelli.account.service;

import br.com.leopaschoarelli.account.dto.TransferDTO;
import br.com.leopaschoarelli.account.model.Transaction;

public interface ITransferService {

    public Transaction transferValues(TransferDTO transferDto);

}

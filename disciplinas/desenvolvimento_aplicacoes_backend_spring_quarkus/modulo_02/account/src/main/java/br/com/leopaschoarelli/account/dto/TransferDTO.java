package br.com.leopaschoarelli.account.dto;

public record TransferDTO(Integer debitAccountNumber, Integer creditAccountNumber, Double amount) {

}

package br.com.leopaschoarelli.account.exception;

public class InvalidBalanceAccountException extends RuntimeException {

    public InvalidBalanceAccountException(String message) {
        super(message);
    }

}

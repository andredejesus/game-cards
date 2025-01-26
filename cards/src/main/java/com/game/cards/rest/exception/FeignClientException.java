package com.game.cards.rest.exception;

public class FeignClientException extends RuntimeException {

    public FeignClientException(String message) {
        super(message);
    }

}

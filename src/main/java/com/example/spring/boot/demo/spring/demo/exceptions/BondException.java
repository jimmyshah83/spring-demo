package com.example.spring.boot.demo.spring.demo.exceptions;

public class BondException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public BondException(String message) {
	super(message);
    }
}

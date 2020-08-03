package gr.bitsplease.bitsplease.exceptions;

public abstract class BusinessException extends Exception {
    public BusinessException(String message) {
        super(message);
    }
}
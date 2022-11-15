package org.example.exception;

public class CapacityOversizeException extends RuntimeException{
    public CapacityOversizeException() {
    }

    public CapacityOversizeException(String message) {
        super(message);
    }

    public CapacityOversizeException(String message, Throwable cause) {
        super(message, cause);
    }

    public CapacityOversizeException(Throwable cause) {
        super(cause);
    }

    public CapacityOversizeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

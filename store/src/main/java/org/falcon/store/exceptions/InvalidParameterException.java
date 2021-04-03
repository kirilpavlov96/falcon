package org.falcon.store.exceptions;

/**
 * Invalid parameter exception class.
 */
public class InvalidParameterException extends RuntimeException {
    public InvalidParameterException() {
        super();
    }

    public InvalidParameterException(String message) {
        super(message);
    }
}

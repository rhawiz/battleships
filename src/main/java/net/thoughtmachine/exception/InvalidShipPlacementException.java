package net.thoughtmachine.exception;


/**
 * Exception for invalid ship placement
 */
public class InvalidShipPlacementException extends Exception {
    public InvalidShipPlacementException(String message) {
        super(message);
    }
}

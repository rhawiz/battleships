package net.thoughtmachine.exception;


/**
 * Exception for invalid battleship placement
 */
public class InvalidBattleshipPlacementException extends Exception {
    public InvalidBattleshipPlacementException(String message) {
        super(message);
    }
}

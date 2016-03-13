package net.thoughtmachine.exception;


/**
 * Exception for when a battleship already exists in coordinates that it was attempted to be placed in
 */
public class BattleshipExistsInCoordsException extends InvalidBattleshipPlacementException {

    public BattleshipExistsInCoordsException() {
        super("Battleship already exists in the given coordinates.");
    }
}

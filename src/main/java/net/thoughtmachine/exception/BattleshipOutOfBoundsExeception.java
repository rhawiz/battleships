package net.thoughtmachine.exception;

/**
 * Exception for when a battleship is attempted to be placed out of the game boards dimensions.
 */
public class BattleshipOutOfBoundsExeception extends InvalidBattleshipPlacementException {

    public BattleshipOutOfBoundsExeception(){
        super("Battleship out of boards boundary.");
    }
}

package net.thoughtmachine.util;


/**
 * Class to store constants
 *
 * @author rhawiz
 */

public class Consts {
    public enum Orientation {

        NORTH('N'),
        EAST('E'),
        SOUTH('S'),
        WEST('W');

        private char orientationChar;

        Orientation(char orientationChar) {
            this.orientationChar = orientationChar;
        }


        /**
         * Get the Orientation given its character representation if valid
         *
         * @param orientationChar character representation of the orientation
         * @return Orientation or null
         */
        public static Orientation getOrientation(char orientationChar) {

            switch (orientationChar) {
                case 'N':
                    return NORTH;
                case 'E':
                    return EAST;
                case 'S':
                    return SOUTH;
                case 'W':
                    return WEST;
            }

            return null;

        }

        /**
         * Get character representation of the orientation i.e. 'N', 'E', 'S', 'W'
         *
         * @return Orientation char representation.
         */
        public char toChar() {
            return this.orientationChar;
        }
    }


    public enum Rotation {
        LEFT('L'),
        RIGHT('R');

        private char rotationChar;

        Rotation(char rotationChar) {
            this.rotationChar = rotationChar;
        }

        /**
         * Get the Rotation given it's character representation if valid
         *
         * @param rotationChar character representation of the rotation.
         * @return Rotation or null
         */
        public static Rotation getRotation(char rotationChar) {
            switch (rotationChar) {
                case 'L':
                    return LEFT;
                case 'R':
                    return RIGHT;
            }
            return null;
        }

        /**
         * Get character representation of the rotation i.e. 'L' or 'R'
         *
         * @return rotation char representation
         */
        public char toChar() {
            return this.rotationChar;
        }

    }

    public enum Operation {
        MOVE('M'),
        SHOOT('S');

        private char operationChar;

        Operation(char operationChar) {
            this.operationChar = operationChar;
        }

        public static Operation getOperation(char operationChar) {
            switch (operationChar) {
                case 'M':
                    return MOVE;
                case 'S':
                    return SHOOT;
            }
            return null;
        }
    }

}


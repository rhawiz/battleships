package net.thoughtmachine.datatype;

/**
 * Coordinate dataype to store an x and y value.
 */
public class Coordinate extends Tuple {

    public Coordinate(int x, int y) {
        super(x, y);

    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return (int) this.x;
    }

    public int getY() {
        return (int) this.y;
    }
}

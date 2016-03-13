package net.thoughtmachine.datatype;

/**
 * Tuple datatype for storing two values
 */
public class Tuple<X, Y> {
    public X x;
    public Y y;

    public Tuple(X x, Y y) {
        this.x = x;
        this.y = y;
    }


    /**
     * Overridden equals method to enable the HashMap to index values based on the x and y values.
     *
     * @param o
     * @return Boolean true or false depending on if the input equals the Tuple
     */
    @Override
    public boolean equals(Object o) {

        if (o == null)
            return false;
        if (this.getClass() != o.getClass())
            return false;

        Tuple e = (Tuple) o;

        return e.x.equals(this.x) && e.y.equals(this.y);
    }


    /**
     *
     * @return hashString Unique idenditfier for the Tuple based on the x and y values.
     */
    @Override
    public int hashCode() {
        String hashString= x.toString() + y.toString();
        return hashString.hashCode();
    }

    public String toString() {
        return String.format("(%s, %s)", x, y);
    }
}

package game.board;

/* A class to represent the coordinates of the board */
public class Coordinates {

    /** Attributes */
    protected int x;
    protected int y;

    /**
     * Creates a pair of (x,y) coordinates 
     * @param x the x coordinate
     * @param width the y coordinate
     */
    public Coordinates(int x, int y){
        this.x = x;
        this.y = y;
    }

    /**
     * Gets the abscissa of this coordinate
     * @return the abscissa of this coordinate
     */
    public int getX(){
        return this.x;
    }

    /**
     * Gets the ordinate of this coordinate
     * @return the ordinate of this coordinate
     */
    public int getY(){
        return this.y;
    }

    /**
     * A method to test the equality of two coordinates
     * @param o the second coordinate
     * @return true if the coordinates are the same (x1 == x2, y1 == y2), false otherwise
     */
    public boolean equals(Object o){
        if (!(o instanceof Coordinates)){
            return false;
        }
        Coordinates other = (Coordinates) o;
        return this.x == other.getX() && this.y == other.getY();
    }

    /**
     * A String representation for this coordinate
     * @return a String representation for this coordinate
     */
    public String toString(){
        return String.format("(%d, %d)", this.x, this.y);
    }

}

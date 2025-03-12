package game.board;

public class Coordinates {

    protected int x;
    protected int y;

    public Coordinates(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public boolean equals(Object o){
        if (!(o instanceof Coordinates)){
            return false;
        }
        Coordinates other = (Coordinates) o;
        return this.x == other.getX() && this.y == other.getY();
    }

    public String toString(){
        return String.format("(%d, %d)", this.x, this.y);
    }

}

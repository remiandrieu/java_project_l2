package game.board;

/** a class to create an invalid position exception */
public class InvalidPositionException extends Exception{

    /**
     * @param message the message that will be returned by the exception
     */
    public InvalidPositionException(String message) {
        super(message);
    }

}

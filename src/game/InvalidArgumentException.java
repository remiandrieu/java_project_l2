package game;

/** a class to create an invalid position exception */
public class InvalidArgumentException extends Exception{

    /**
     * @param message the message that will be returned by the exception
     */
    public InvalidArgumentException(String message) {
        super(message);
    }

}

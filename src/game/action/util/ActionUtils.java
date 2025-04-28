package game.action.util;


import java.util.ArrayList;

import game.board.*;
import listchooser.util.Input;

public class ActionUtils {
    
    /**
     * Asks the player for coordinates.
     * @return the coordinates given by the player
     */
    public static Coordinates askCoordinates() {
        int x = -1;
        int y = -1;
        boolean correct = false;
        
        while (!correct) {
            correct = true;
            System.out.println("enter the x coordinate: ");
            try {
                x = Input.readInt();
            } catch (java.io.IOException e) {
                System.out.println("Please, enter a number");
                correct = false;
                continue;
            }
            System.out.println("enter the y coordinate: ");
            try {
                y = Input.readInt();
            } catch (java.io.IOException e) {
                System.out.println("Please, enter a number");
                correct = false;
                continue;
            }
        }
        return new Coordinates(x, y);
    }

    /**
     * Asks the player for coordinates, doesn't stop until the coordinates are in the list.
     * @param list the list the coordinates must belong to
     * @return the coordinates given by the player
     */
    public static Coordinates askCoordinates(ArrayList<Coordinates> list) {
        boolean correct = false;
        Coordinates coor = new Coordinates(-1, -1);
        
        while (!correct) {
            correct = true;
            coor = ActionUtils.askCoordinates();

            if (! list.contains(coor)){
                System.out.println("Incorrect Position");
                correct = false;
            }
        }
        return coor;
    }
}

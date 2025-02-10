package game.building;
import game.board.*;
import game.board.util.Ressource;
import game.player.*;

/* a class to represent the ports */
public class Port extends Building {

    /**
     * creates a port
     * @param player the player who owns this port
     * @param land the land where the port is placed 
     */
    public Port(Player player, Land land){
        super(player, land);
        this.ressources.put(Ressource.WOOD, 1);
        this.ressources.put(Ressource.SHEEP, 2);
    }

}

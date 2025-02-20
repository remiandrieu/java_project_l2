package game.action;

import java.util.ArrayList;
import java.util.List;

import javax.lang.model.util.Elements;

import game.board.util.Ressource;
import game.player.Player;
import listchooser.InteractiveListChooser;
import listchooser.ListChooser;

public class Trade extends CommonAction{
    
    public Trade() {
        super("trade");
    }

    public boolean isPossible(Player player) {
        return this.availableRessources(player).size() > 0;
    }

    public void act(Player player) {
        ListChooser<Ressource> lc = new InteractiveListChooser<>();
        Ressource chosenRessource = lc.choose("Which ressource do you spend ?", this.availableRessources(player));
		System.out.println("You've chosen : " + chosenRessource);
        player.removeRessoure(chosenRessource, 3);
        List<Ressource> l = new ArrayList<>();
		for (Ressource r : Ressource.values()){
            l.add(r);
        }
        Ressource chosenRessource2 = lc.choose("Which ressource do you want ?", l);
		System.out.println("You've chosen : " + chosenRessource);
        player.addRessoure(chosenRessource2);
    }

    public List<Ressource> availableRessources(Player player){
        ArrayList<Ressource> ressources = new ArrayList<>();
        for (Ressource r: Ressource.values()){
            if (player.getRessources().containsKey(r) && player.getRessources().get(r)>=3){
                ressources.add(r);
            }
        }
        return ressources;
    }
}

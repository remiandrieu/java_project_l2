package game.action;
import java.util.*;

import game.board.util.*;

public abstract class Action {
    protected Map<Ressource,Integer> cost = new HashMap<>();
    protected String label = "abstract Action";
}

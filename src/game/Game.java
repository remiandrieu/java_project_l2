package game;

import java.util.*;

import game.action.*;
import game.board.*;
import game.building.Building;
import game.objective.Objective;
import game.player.*;
import listchooser.*;
import listchooser.util.*;

/* a class to model a game */
public abstract class Game {

    /* Attributes */
    protected int nbPlayers;
    protected List<Player> players;
    protected Board board;
    protected List<Action> actions;
    protected List<Objective> objectives;

    /**
     * Get the list of players of the game
     * @return a list containing the players 
     */
    public List<Player> getPlayers() {
        return this.players;
    }

    /**
     * Get the board used in the game
     * @return the board used in the game
     */
    public Board getBoard() {
        return this.board;
    }

    /**
     * Get the list of possible actions of the game
     * @return a list containing all possible actions 
     */
    public List<Action> getActions() {
        return this.actions;
    }

    /**
     * Create a player specific to the game
     * @param playerName the player's name
     * @return return a player specific to the game
     */
    protected abstract Player createPlayer(String playerName);

    /**
     * Initialize players names
     */
    public void initPlayersNames(){
        int i;
        boolean correct;
        this.players = new ArrayList<>();
        for (i = 1; i <= this.nbPlayers; i++){
            String playerName = "";
            System.out.println("Player " + i + ", enter your name :");
            correct = false;
            while(!correct){
                correct = true;
                playerName = Input.readString();
                for(Player player : this.players){
                    if(player.getName().equals(playerName)){
                        System.out.println("Name already exist");
                        System.out.println("Player " + i + ", enter your name :");
                        correct = false;
                        break;
                    }
                }
            }
            this.players.add(this.createPlayer(playerName));
        }
    }

    /**
     * Initialize players of the game
     */
    public void initPlayers(){
        int nbPlayers = -1;
		boolean correct = false;

        System.out.println("Choose the number of players :");
        while (!correct) {
            correct = true;
            try {
                nbPlayers = Input.readInt();
                if(nbPlayers<2){
                    System.out.println("Minimum 2 players");
                    correct = false;
                }
            } catch (java.io.IOException e) {
                System.out.println("Please, enter a number");
                correct = false;
                continue;
            }
        }
        this.nbPlayers = nbPlayers;
        this.initPlayersNames();
    }

    /**
     * Get an action used to place the first buildings of the game
     * @return return an action used to place the first buildings of the game
     */
    protected abstract StartAction startBuilding();

    /**
     * Initialize the first buildings of the game
     */
    public void placeFirstBuildings() throws InvalidPositionException{
        int i;
        StartAction startAction = startBuilding();
        for (i=0; i < players.size(); i++){
            System.out.println(this.board.boardToString(true));
            startAction.act(players.get(i));
        }
        for (i=players.size()-1; i >= 0; i--){
            System.out.println(this.board.boardToString(true));
            startAction.act(players.get(i));
        }
    }

    /**
     * Set the objective of the player in parameters
     * @param player a player
     */
    protected abstract void setPlayerObjective(Player player);

    /**
     * Initialize an objective for each player
     */
    public void initObjectives(){
        for (Player p : this.players){
            this.setPlayerObjective(p);
            p.getObjective().printObjective();
        }
    }

    /**
     * Initialize the possible actions of the game
     */
    public void initActions(){
        this.actions = new ArrayList<>();
        this.actions.add(new Wait(board));
        this.actions.add(new BuildPort(board));
        this.actions.add(new Trade(board));
        this.actions.add(new TradePort(board));
    }

    /**
     * Initialize the board of the game
     */
    public void initBoard(){
        int length = (int) Math.sqrt(12*(this.players.size() - 2)) + 10;
        int width = length;
		
        this.board = new Board(length, width);
        try {
            this.board.createGrid();
        } catch (InvalidPositionException e) {
            System.out.println(e);
        }
    }

    /**
     * Collect the player's ressources
     * @param player a player
     */
    protected void collectRessources(Player player){
        for (Building b : player.getBuildings()){
            b.playerCollectRessources();
        }
    }

    /**
    * Play a turn for the player in parameters
    * @param player a player
    * @return true if the player's objective is completed, false otherwise
    */
    protected boolean playTurn(Player player) throws InvalidPositionException{
        System.out.println(this.board.boardToString(true));
        this.collectRessources(player);
        System.out.println(player + " : it's your turn !");
        List<Action> possibleActions = new ArrayList<>();
        for (Action a : this.actions){
            if (a.isPossible(player)){
                possibleActions.add(a);
            }
        }
        ListChooser<Action> lc = new InteractiveListChooser<>();
        Action chosenAction = lc.choose("Choose an action to perform.", possibleActions);
        chosenAction.act(player);
        return player.getObjective().isAchieved();
    }

    public void play() throws InvalidPositionException{
        int currentPlayer = -1;
        boolean stop = false;
        while (!stop){
            currentPlayer = (currentPlayer + 1) % this.players.size();
            stop = playTurn(this.players.get(currentPlayer));
        }
        System.out.println(this.players.get(currentPlayer) + "win !");
    }
    
    /**
    * After doing all initialization, start the game
    * Each player plays one after the other
    * When a player has completed their objective, end the game
    */
    public void initGame(){
        try{
            this.initPlayers();
            this.initBoard();
            this.initObjectives();
            this.initActions();
            this.placeFirstBuildings();
        } catch (Exception e){
            System.out.println(e);
        }
    }

    /**
    * After doing all initialization, start the game
    * Each player plays one after the other
    * When a player has completed their objective, end the game
    */
    public void initGame(int length, int width, int nbPlayers){
        try{
            if (length < 10) {throw new InvalidArgumentException("Incorrect length");}
            if (width < 10) {throw new InvalidArgumentException("Incorrect width");}
            if (nbPlayers < 2) {throw new InvalidArgumentException("Incorrect number of players");}
            this.nbPlayers = nbPlayers;
            this.initPlayersNames();
            this.board = new Board(length, width);
            this.board.createGrid();
            this.initObjectives();
            this.initActions();
            this.placeFirstBuildings();
        } catch (Exception e){
            System.out.println(e);
        }
    }

    
}

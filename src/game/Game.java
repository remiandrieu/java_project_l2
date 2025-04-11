package game;

import java.util.*;

import game.action.*;
import game.board.*;
import game.building.Building;
import game.player.*;
import listchooser.InteractiveListChooser;
import listchooser.ListChooser;
import listchooser.util.Input;

public abstract class Game {

    protected List<Player> players;
    protected Board board;
    protected List<Action> actions;

    protected abstract Player createPlayer(String playerName);

    public void initPlayers(){
        int nbPlayers = -1;
		boolean correct = false;
        int i;

        this.players = new ArrayList<>();
        
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

        for (i = 1; i <= nbPlayers; i++){
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

    protected abstract StartAction startBuilding();

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

    public void initActions(){
        this.actions = new ArrayList<>();
        this.actions.add(new Wait(board));
        this.actions.add(new BuildPort(board));
        this.actions.add(new Trade(board));
        this.actions.add(new TradePort(board));
    }

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

    protected void collectRessources(Player player){
        for (Building b : player.getBuildings()){
            b.playerCollectRessources();
        }
    }

    protected void playTurn(Player player) throws InvalidPositionException{
        System.out.println(this.board.boardToString(true));
        this.collectRessources(player);
        System.out .println(player + " : it's your turn !");
        List<Action> possibleActions = new ArrayList<>();
        for (Action a : this.actions){
            if (a.isPossible(player)){
                possibleActions.add(a);
            }
        }
        ListChooser<Action> lc = new InteractiveListChooser<>();
        Action chosenAction = lc.choose("Choose an action to perform.", possibleActions);
        chosenAction.act(player);
    }
    
    public void play(){
        try{
            this.initPlayers();
            this.initBoard();
            this.initActions();
            this.placeFirstBuildings();
            int currentPlayer = 0;
            while (true){
                playTurn(this.players.get(currentPlayer));
                currentPlayer = (currentPlayer + 1) % this.players.size();
            }
        } catch (InvalidPositionException e){
            System.out.println(e);
        }
    }

    public List<Player> getPlayers() {
        return this.players;
    }

    public Board getBoard() {
        return this.board;
    }

    public List<Action> getActions() {
        return this.actions;
    }
}

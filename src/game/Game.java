package game;

import java.util.*;

import game.board.*;
import game.player.*;
import listchooser.util.Input;

public abstract class Game {

    protected List<Player> players;
    protected Board board;

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

    public abstract Player createPlayer(String playerName);

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
            } catch (java.io.IOException e) {
                System.out.println("Please, enter a number");
                correct = false;
                continue;
            }
        }

        for (i = 1; i <= nbPlayers; i++){
            String playerName;
            System.out.println("Player " + i + ", enter your name :");
            playerName = Input.readString();
            this.players.add(this.createPlayer(playerName));
        }
    }
    
    public void play(){
        try{
            initPlayers();
            initBoard();
            System.out.println(this.board.boardToString(true));
            System.out.println(this.players);
        } catch (InvalidPositionException e){
            System.out.println(e);
        }
    }
}

package game.gui;

import java.awt.*;

import javax.swing.JFrame;

import game.Game;

public class GameGUI {

    protected Game game;
    protected BoardPanel boardPanel;
    protected JFrame frame;

    public GameGUI(Game game){
        this.game = game;
    }

    public void initGUI()  {
        try{
            this.boardPanel = new BoardPanel(this.game);
            
            this.frame = new javax.swing.JFrame("Board");

            frame.add(this.boardPanel, BorderLayout.NORTH);
            
            frame.setLocation(0, 0);
            frame.pack();
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            this.frame.setPreferredSize(new Dimension(800, 800));
            this.frame.setVisible(true);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public void refresh(){
        this.boardPanel.revalidate();
        this.boardPanel.repaint();
        this.frame.revalidate();
        this.frame.repaint();
    }

}


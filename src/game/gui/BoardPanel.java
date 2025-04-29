package game.gui;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import javax.swing.*;

import game.Game;
import game.board.*;
import game.building.*;

import java.awt.*;

public class BoardPanel extends JPanel {
    
    static final long serialVersionUID = 1;
    protected Board board;
    protected int width = 800;
    protected int height = 800;
    private BufferedImage[] images = new BufferedImage[5];
    
    public BoardPanel(Game game) {
        try {
            images[0] = ImageIO.read(new File("./src/game/gui/img/harbor.png"));
            images[1] = ImageIO.read(new File("./src/game/gui/img/farm.png"));
            images[2] = ImageIO.read(new File("./src/game/gui/img/bigfarm.png"));
            images[3] = ImageIO.read(new File("./src/game/gui/img/camp.png"));
            images[4] = ImageIO.read(new File("./src/game/gui/img/army.png"));
        } catch (IOException ex) {
            System.out.println("probleme chef");
        }

        this.board = game.getBoard();
        
        this.setPreferredSize(new Dimension(this.width, this.height));
    }

    public void dessinerTile(Graphics g, Tile tile, int x, int y, int taille_case){
        if (tile instanceof Sea){
            g.setColor(Color.BLUE);
        }
        if (tile instanceof Mountain){
            g.setColor(Color.GRAY);	
        }
        if (tile instanceof Pasture){
            g.setColor(Color.GREEN);	
        }
        if (tile instanceof Fields){
            g.setColor(Color.YELLOW);	
        }
        if (tile instanceof Forest){
            g.setColor(new Color(0,100,0));	
        }

        g.fillRect(y*taille_case, x*taille_case, taille_case, taille_case);
        g.setColor(Color.BLACK);
        g.drawRect(y*taille_case, x*taille_case, taille_case, taille_case);
    }

    public void dessinerBuilding(Graphics g, Building building, int x, int y, int taille_case){
        g.setColor(Color.BLACK);
                        
        if (building instanceof DemeterBuilding){
            if (((DemeterBuilding) building).isEvolved())
                g.drawImage(images[2], y*taille_case, x*taille_case , taille_case, taille_case, getFocusCycleRootAncestor());
            else
                g.drawImage(images[1], y*taille_case, x*taille_case , taille_case, taille_case, getFocusCycleRootAncestor());
        }
        else if (building instanceof AresBuilding){
            if (((AresBuilding) building).isEvolved())
                g.drawImage(images[4], y*taille_case, x*taille_case , taille_case, taille_case, getFocusCycleRootAncestor());
            else
                g.drawImage(images[3], y*taille_case, x*taille_case , taille_case, taille_case, getFocusCycleRootAncestor());
            g.drawString("" + ((LandBuilding) building).getDimension(), y*taille_case, x*taille_case+(taille_case/4));
        }
        else
            g.drawImage(images[0], y*taille_case, x*taille_case , taille_case, taille_case, getFocusCycleRootAncestor());
        
        
        g.drawString("" + building.getPlayer().getId(), y*taille_case, (x+1)*taille_case);
    }
    
    public void dessiner(Graphics g) throws InvalidPositionException {
        int x;
        int y;
        int taille_case = Math.min(this.height / this.board.getLength(), this.width / this.board.getWidth());
        
        Font font = new Font("", 0, taille_case/4);
        g.setFont(font);

        for (x=0; x<this.board.getLength(); x++){
            for (y=0; y<this.board.getWidth(); y++){
                
                Tile tile = this.board.getTile(x, y);

                this.dessinerTile(g, tile, x, y, taille_case);

                if (!(tile instanceof Sea)){

                    Land land = (Land) tile;

                    if (land.hasBuilding()){

                        Building building = land.getBuilding();

                        this.dessinerBuilding(g, building, x, y, taille_case);
                        
                    }
                }
            }
        }
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        try{
            dessiner(g);
        }
        catch (InvalidPositionException e){
            System.out.println("erreur lors de l'affichage");
        }
        
    }
    
}
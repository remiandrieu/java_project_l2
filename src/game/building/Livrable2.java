package game.building;

import game.board.*;
import game.player.Player;

public class Livrable2 {
    public static void main(String[] args) throws InvalidPositionException {
        int length = 10;
        int width = 10;
        if (args.length >= 2){
            length = Integer.parseInt(args[0]);
            width = Integer.parseInt(args[1]);
        }
        Board board = new Board(length, width);
        board.createGrid();

        Player player = new Player("player");

        Land land = firstAvailableLand(board, length, width);
        Port harbor = new Port(player, land);
        // Les chances que cette case ne soit pas près de la mer sont faibles, mais pas nulles.
        // Ce cas se produit quand les tuiles (0,0), (0,1) et (1,0) sont des land. Probablement un moyen de gérer ce cas-là.
        System.out.println("Le port est placé sur la case " + land.getPosition());
        System.out.println("Le port coûte " + harbor.getCost());
        System.out.println("Le port rapporte 1 " + harbor.collectRessources());
        System.out.println();


        // Pour chaque bâtiment, on va prendre la première case libre du board, y placer le bâtiment, le faire évoluer si besoin, et puis afficher les informations nécessaires.
        land = firstAvailableLand(board, length, width); 
        AresBuilding camp = new AresBuilding(player, land, 0);
        System.out.println("Le camp est placé sur la case " + land.getPosition());
        System.out.println("Le camp coûte " + camp.getCost());
        System.out.println("Le camp rapporte 1 " + camp.collectRessources());
        System.out.println();
        
        land = firstAvailableLand(board, length, width);
        AresBuilding army = new AresBuilding(player, land, 0);
        army.addWarriors(10); // En plaçant au moins 5 soldats dans le bâtiment, on le fait évoluer
        System.out.println("L'armée est placé sur la case " + land.getPosition());
        System.out.println("L'armée coûte " + army.getCost());
        System.out.println("L'armée rapporte 2 " + army.collectRessources());
        System.out.println();

        land = firstAvailableLand(board, length, width);
        DemeterBuilding farm = new DemeterBuilding(player, land);
        System.out.println("La ferme est placé sur la case " + land.getPosition());
        System.out.println("La ferme " + farm.getCost());
        System.out.println("La ferme rapporte 1 " + farm.collectRessources());
        System.out.println();

        land = firstAvailableLand(board, length, width);
        DemeterBuilding bigFarm = new DemeterBuilding(player, land);
        bigFarm.evolve();
        System.out.println("L'exploitation est placé sur la case " + land.getPosition());
        System.out.println("L'exploitation camp coûte " + bigFarm.getCost());
        System.out.println("L'exploitation rapporte 2 " + bigFarm.collectRessources());
        System.out.println();
        
        System.out.println(board.boardToString(true));
    }
        
    /**
     * Returns the first available land tile on the board i.e. the first tile that isn't a sea and doesn't have a building
     * @param board the board we want the land on
     * @param length the length of the board
     * @param width the width of the board
     * @return the first available land tile on the board
    */
    public static Land firstAvailableLand(Board board, int length, int width){
        Land land = new Forest(); // Définition de base de land, la fonction ne fonctionne pas si on ne définit pas cette variable 
        try{
            int x = 0;
            int y = 0;
            Tile tile = board.getTile(0, 0);
            while(x < length && ((tile instanceof Sea) || !(tile instanceof Sea) && tile.hasBuilding())){
                while(y < width && ((tile instanceof Sea) || !(tile instanceof Sea) && tile.hasBuilding())){
                    tile = board.getTile(x, y);
                    y += 1;
                }
                x += 1;
                y = 0;
            }
            land = (Land) tile;
        } catch(InvalidPositionException e){
        }
        return land;
    } 
}
package game.building;

import game.board.*;
import game.player.Player;

public class Livrable2 {
    public static int posX;
    public static int posY;
    public static void main(String[] args) throws InvalidPositionException {
        int length = 10;
        int width = 10;
        if (args.length == 2){
            length = Integer.parseInt(args[0]);
            width = Integer.parseInt(args[1]);
        }
        else if (args.length == 1 || args.length > 2){
            System.out.println("How to use :");
            System.out.println("Livrable2.java <length> <width>");
            System.out.println("<length> : length of the board >= 3");
            System.out.println("<width> : width of the board >= 3");
            return;
        }
        
        Board board = new Board(length, width);
        board.createGrid();

        Player player = new Player("player");

        Land land = firstAvailableLand(board, length, width);
        Port harbor = new Port(player, land);
        // Les chances que cette case ne soit pas près de la mer sont faibles, mais pas nulles.
        // Ce cas se produit quand les tuiles (0,0), (0,1) et (1,0) sont des land. Probablement un moyen de gérer ce cas-là.
        displayBuilding(harbor);


        // Pour chaque bâtiment, on va prendre la première case libre du board, y placer le bâtiment, le faire évoluer si besoin, et puis afficher les informations nécessaires.
        land = firstAvailableLand(board, length, width); 
        AresBuilding camp = new AresBuilding(player, land, 0);
        displayBuilding(camp);
        
        land = firstAvailableLand(board, length, width);
        AresBuilding army = new AresBuilding(player, land, 0);
        army.addWarriors(10); // En plaçant au moins 5 soldats dans le bâtiment, on le fait évoluer
        displayBuilding(army);

        land = firstAvailableLand(board, length, width);
        DemeterBuilding farm = new DemeterBuilding(player, land);
        displayBuilding(farm);

        land = firstAvailableLand(board, length, width);
        DemeterBuilding bigFarm = new DemeterBuilding(player, land);
        bigFarm.evolve();
        displayBuilding(bigFarm);
        
        
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
            posX = 0;
            posY = 0;
            Tile tile = board.getTile(0, 0);
            while(x < length && ((tile instanceof Sea) || !(tile instanceof Sea) && ((Land) tile).hasBuilding())){
                while(y < width && ((tile instanceof Sea) || !(tile instanceof Sea) && ((Land) tile).hasBuilding())){
                    posX = x;
                    posY = y;
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

    /**
     * Displays the building.
     * @param building the building to display
     */
    public static void displayBuilding(Building building){
        System.out.println(building.toString() + " est placé sur la case (" + posX + ", " + posY + ")");
        System.out.println(building.toString() + " coûte " + building.getCost());
        if (building instanceof Port){
            System.out.println(building.toString() + " rapporte 1 " + building.collectRessources());
        } else {
            if (building instanceof AresBuilding){
                // Il n'y a pas de méthode abstraite isEvolved() dans building ou LandBuilding,
                // on doit donc faire des Cast vers les bâtiments des différents jeux pour vérifier s'il est évolué ou pas.
                // Une solution plus élégante est forcément possible.
                // Par exemple, créer une classe "EvolvedBuilding".
                AresBuilding aresBuilding = (AresBuilding) building;
                if (aresBuilding.isEvolved()){
                    System.out.println(building.toString() + " rapporte 2 " + building.collectRessources());
                } else {
                    System.out.println(building.toString() + " rapporte 1 " + building.collectRessources());
                }
            } else {
                DemeterBuilding demeterBuilding = (DemeterBuilding) building;
                if (demeterBuilding.isEvolved()){
                    System.out.println(building.toString() + " rapporte 2 " + building.collectRessources());
                } else {
                    System.out.println(building.toString() + " rapporte 1 " + building.collectRessources());
                } 
            }
        } 
        System.out.println();
    } 
}
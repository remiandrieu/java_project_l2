package game.action;

import game.board.Board;
import game.board.Forest;
import game.board.InvalidPositionException;
import game.board.Land;
import game.board.Sea;
import game.board.Tile;
import game.board.util.Ressource;
import game.player.AresPlayer;
import game.player.DemeterPlayer;
import game.player.Player;
import game.building.*;

public class ActionMain {
    
    public static void main(String[] args) throws InvalidPositionException{
        

        /*-------------------------------------------------------------- Trade ---------------------------------------------------------------*/
        /*System.out.println("--------------------- Trade --------------------");
        Board tradeBoard = new Board(10, 10);
        Trade trade = new Trade(tradeBoard);
        DemeterPlayer tradePlayer = new DemeterPlayer("Timoleon");
        tradePlayer.addRessoure(Ressource.ORE);
        tradePlayer.addRessoure(Ressource.ORE);
        tradePlayer.addRessoure(Ressource.ORE);
        tradePlayer.addRessoure(Ressource.SHEEP);
        tradePlayer.addRessoure(Ressource.SHEEP);
        tradePlayer.addRessoure(Ressource.SHEEP);
        System.out.println("WHEAT: " + tradePlayer.getRessources().get(Ressource.WHEAT));
        System.out.println("SHEEP: " + tradePlayer.getRessources().get(Ressource.SHEEP));
        System.out.println("ORE: " + tradePlayer.getRessources().get(Ressource.ORE));
        System.out.println("WOOD: " + tradePlayer.getRessources().get(Ressource.WOOD));
        trade.act(tradePlayer);
        System.out.println("WHEAT: " + tradePlayer.getRessources().get(Ressource.WHEAT));
        System.out.println("SHEEP: " + tradePlayer.getRessources().get(Ressource.SHEEP));
        System.out.println("ORE: " + tradePlayer.getRessources().get(Ressource.ORE));
        System.out.println("WOOD: " + tradePlayer.getRessources().get(Ressource.WOOD));*/

        /*-------------------------------------------------------------- TradePort ---------------------------------------------------------------*/
        /*System.out.println("--------------------- TradePort --------------------");
        Board tpBoard = new Board(10, 10);
        TradePort tradePort = new TradePort(tpBoard);
        DemeterPlayer tradePlayer = new DemeterPlayer("Timoleon");
        tradePlayer.addRessoure(Ressource.ORE);
        tradePlayer.addRessoure(Ressource.ORE);
        tradePlayer.addRessoure(Ressource.SHEEP);
        tradePlayer.addRessoure(Ressource.SHEEP);
        tradePlayer.addRessoure(Ressource.WOOD);
        System.out.println("WHEAT: " + tradePlayer.getRessources().get(Ressource.WHEAT));
        System.out.println("SHEEP: " + tradePlayer.getRessources().get(Ressource.SHEEP));
        System.out.println("ORE: " + tradePlayer.getRessources().get(Ressource.ORE));
        System.out.println("WOOD: " + tradePlayer.getRessources().get(Ressource.WOOD));
        tradePort.act(tradePlayer);
        System.out.println("WHEAT: " + tradePlayer.getRessources().get(Ressource.WHEAT));
        System.out.println("SHEEP: " + tradePlayer.getRessources().get(Ressource.SHEEP));
        System.out.println("ORE: " + tradePlayer.getRessources().get(Ressource.ORE));
        System.out.println("WOOD: " + tradePlayer.getRessources().get(Ressource.WOOD));*/

        /*-------------------------------------------------------------- BuildFarm ---------------------------------------------------------------*/
        /*System.out.println("--------------------- BuildFarm --------------------");
        Board bfBoard = new Board(10, 10);
        bfBoard.createGrid();
        DemeterPlayer bfPlayer = new DemeterPlayer("Timoleon");
        BuildFarm bd = new BuildFarm(bfBoard);
        bfPlayer.addRessoure(Ressource.ORE);
        bfPlayer.addRessoure(Ressource.WOOD);
        bfPlayer.addRessoure(Ressource.WOOD);
        System.out.println("WHEAT: " + bfPlayer.getRessources().get(Ressource.WHEAT));
        System.out.println("SHEEP: " + bfPlayer.getRessources().get(Ressource.SHEEP));
        System.out.println("ORE: " + bfPlayer.getRessources().get(Ressource.ORE));
        System.out.println("WOOD: " + bfPlayer.getRessources().get(Ressource.WOOD));
        System.out.println(bfBoard.boardToString(true));
        bd.act(bfPlayer);
        System.out.println("WHEAT: " + bfPlayer.getRessources().get(Ressource.WHEAT));
        System.out.println("SHEEP: " + bfPlayer.getRessources().get(Ressource.SHEEP));
        System.out.println("ORE: " + bfPlayer.getRessources().get(Ressource.ORE));
        System.out.println("WOOD: " + bfPlayer.getRessources().get(Ressource.WOOD));
        System.out.println("buildings: " + bfPlayer.getBuildings());
        System.out.println(bfBoard.boardToString(true));*/

        /*-------------------------------------------------------------- BuildArmy ---------------------------------------------------------------*/
        System.out.println("--------------------- BuildArmy --------------------");
        Board baBoard = new Board(10, 10);
        baBoard.createGrid();
        AresPlayer baPlayer = new AresPlayer("Timoleon");
        BuildArmy ba = new BuildArmy(baBoard);
        baPlayer.addRessoure(Ressource.WOOD);
        baPlayer.addRessoure(Ressource.SHEEP);
        baPlayer.addRessoure(Ressource.WHEAT);
        baPlayer.addWarrior(10);
        for(int i = 0; i < 5; i++){
            Land land = firstAvailableLand(baBoard, 10, 10);
            if(i<4){
                DemeterBuilding farm = new DemeterBuilding(baPlayer, land);
                baPlayer.getBuildings().add(farm);
            } else{
                Port port = new Port(baPlayer, land);
                baPlayer.getBuildings().add(port);
            } 
        }
        System.out.println("WHEAT: " + baPlayer.getRessources().get(Ressource.WHEAT));
        System.out.println("SHEEP: " + baPlayer.getRessources().get(Ressource.SHEEP));
        System.out.println("ORE: " + baPlayer.getRessources().get(Ressource.ORE));
        System.out.println("WOOD: " + baPlayer.getRessources().get(Ressource.WOOD));
        System.out.println("nbWarriors: " + baPlayer.getNbWarrior());
        for (Building building : baPlayer.getBuildings()){
            System.out.println("building: " + building + ", nbWarriors:" + ((building instanceof AresBuilding)?((LandBuilding) building).getDimension():0));
        }
        System.out.println(baBoard.boardToString(true));
        ba.act(baPlayer);
        System.out.println("WHEAT: " + baPlayer.getRessources().get(Ressource.WHEAT));
        System.out.println("SHEEP: " + baPlayer.getRessources().get(Ressource.SHEEP));
        System.out.println("ORE: " + baPlayer.getRessources().get(Ressource.ORE));
        System.out.println("WOOD: " + baPlayer.getRessources().get(Ressource.WOOD));
        System.out.println("nbWarriors: " + baPlayer.getNbWarrior());
        for (Building building : baPlayer.getBuildings()){
            System.out.println("building: " + building + ", nbWarriors:" + ((building instanceof AresBuilding)?((LandBuilding) building).getDimension():0));
        }
        System.out.println(baBoard.boardToString(true));

        /*-------------------------------------------------------------- EvolveFarm ---------------------------------------------------------------*/
        /*System.out.println("--------------------- EvolveFarm --------------------");
        Board efBoard = new Board(10, 10);
        efBoard.createGrid();
        DemeterPlayer efPlayer = new DemeterPlayer("Timoleon");
        efPlayer.addRessoure(Ressource.WHEAT, 1);
        efPlayer.addRessoure(Ressource.SHEEP);
        efPlayer.addRessoure(Ressource.WOOD, 2);

        System.out.println("WHEAT: " + efPlayer.getRessources().get(Ressource.WHEAT));
        System.out.println("SHEEP: " + efPlayer.getRessources().get(Ressource.SHEEP));
        System.out.println("ORE: " + efPlayer.getRessources().get(Ressource.ORE));
        System.out.println("WOOD: " + efPlayer.getRessources().get(Ressource.WOOD));
        
        for(int i = 0; i < 5; i++){
            Land land = firstAvailableLand(efBoard, 10, 10);
            if(i<4){
                DemeterBuilding farm = new DemeterBuilding(efPlayer, land);
                efPlayer.getBuildings().add(farm);
            } else{
                Port port = new Port(efPlayer, land);
                efPlayer.getBuildings().add(port);
            } 
        }

        EvolveFarm evolveFarm = new EvolveFarm(efBoard);       
        DemeterBuilding building = (DemeterBuilding) efPlayer.getBuildings().get(0);
        building.evolve();
        System.out.println(efBoard.boardToString(true));
        evolveFarm.act(efPlayer);
        System.out.println("WHEAT: " + efPlayer.getRessources().get(Ressource.WHEAT));
        System.out.println("SHEEP: " + efPlayer.getRessources().get(Ressource.SHEEP));
        System.out.println("ORE: " + efPlayer.getRessources().get(Ressource.ORE));
        System.out.println("WOOD: " + efPlayer.getRessources().get(Ressource.WOOD));
        System.out.println(efBoard.boardToString(true));*/

        /*-------------------------------------------------------------- EvolveArmy ---------------------------------------------------------------*/
        /*System.out.println("--------------------- EvolveArmy --------------------");
        Board eaBoard = new Board(10, 10);
        eaBoard.createGrid();
        DemeterPlayer eaPlayer = new DemeterPlayer("Timoleon");
        eaPlayer.addRessoure(Ressource.ORE, 3);
        eaPlayer.addRessoure(Ressource.WOOD, 2);

        System.out.println("WHEAT: " + eaPlayer.getRessources().get(Ressource.WHEAT));
        System.out.println("SHEEP: " + eaPlayer.getRessources().get(Ressource.SHEEP));
        System.out.println("ORE: " + eaPlayer.getRessources().get(Ressource.ORE));
        System.out.println("WOOD: " + eaPlayer.getRessources().get(Ressource.WOOD));
        
        for(int i = 0; i < 5; i++){
            Land land = firstAvailableLand(eaBoard, 10, 10);
            if(i<4){
                AresBuilding army = new AresBuilding(eaPlayer, land, 2);
                eaPlayer.getBuildings().add(army);
            } else{
                Port port = new Port(eaPlayer, land);
                eaPlayer.getBuildings().add(port);
            } 
        }

        EvolveArmy evolveArmy = new EvolveArmy(eaBoard);       
        AresBuilding building2 = (AresBuilding) eaPlayer.getBuildings().get(0);
        building2.evolve();
        System.out.println(eaBoard.boardToString(true));
        evolveArmy.act(eaPlayer);
        System.out.println("WHEAT: " + eaPlayer.getRessources().get(Ressource.WHEAT));
        System.out.println("SHEEP: " + eaPlayer.getRessources().get(Ressource.SHEEP));
        System.out.println("ORE: " + eaPlayer.getRessources().get(Ressource.ORE));
        System.out.println("WOOD: " + eaPlayer.getRessources().get(Ressource.WOOD));
        System.out.println(eaBoard.boardToString(true));*/

        /*-------------------------------------------------------------- BuildPort ---------------------------------------------------------------*/
        System.out.println("--------------------- BuildPort --------------------");
        Board bpBoard = new Board(10, 10);
        bpBoard.createGrid();
        AresPlayer bpPlayer = new AresPlayer("Timoleon");
        BuildPort bp = new BuildPort(bpBoard);
        bpPlayer.addRessoure(Ressource.WOOD);
        bpPlayer.addRessoure(Ressource.SHEEP, 2);

        for(int i = 0; i < 5; i++){
            Land land = firstAvailableLand(bpBoard, 10, 10);
            if(i<4){
                AresBuilding army = new AresBuilding(bpPlayer, land, 2);
                bpPlayer.getBuildings().add(army);
            } else{
                Port port = new Port(bpPlayer, land);
                bpPlayer.getBuildings().add(port);
            } 
        }

        System.out.println("WHEAT: " + bpPlayer.getRessources().get(Ressource.WHEAT));
        System.out.println("SHEEP: " + bpPlayer.getRessources().get(Ressource.SHEEP));
        System.out.println("ORE: " + bpPlayer.getRessources().get(Ressource.ORE));
        System.out.println("WOOD: " + bpPlayer.getRessources().get(Ressource.WOOD));
        System.out.println(bpBoard.boardToString(true));
        bp.act(bpPlayer);
        System.out.println("WHEAT: " + bpPlayer.getRessources().get(Ressource.WHEAT));
        System.out.println("SHEEP: " + bpPlayer.getRessources().get(Ressource.SHEEP));
        System.out.println("ORE: " + bpPlayer.getRessources().get(Ressource.ORE));
        System.out.println("WOOD: " + bpPlayer.getRessources().get(Ressource.WOOD));
        System.out.println("buildings: " + bpPlayer.getBuildings());
        System.out.println(bpBoard.boardToString(true));

         /*-------------------------------------------------------------- BuyThief ---------------------------------------------------------------*/
         /*System.out.println("--------------------- BuyThief --------------------");
         Board btBoard = new Board(10, 10);
         DemeterPlayer btPlayer = new DemeterPlayer("Timoleon");
         BuyThief bt = new BuyThief(btBoard);
         btPlayer.addRessoure(Ressource.WOOD);
         btPlayer.addRessoure(Ressource.ORE);
         btPlayer.addRessoure(Ressource.WHEAT);
         System.out.println("WHEAT: " + btPlayer.getRessources().get(Ressource.WHEAT));
         System.out.println("SHEEP: " + btPlayer.getRessources().get(Ressource.SHEEP));
         System.out.println("ORE: " + btPlayer.getRessources().get(Ressource.ORE));
         System.out.println("WOOD: " + btPlayer.getRessources().get(Ressource.WOOD));
         System.out.println("Has thief: " + btPlayer.hasThief());
         bt.act(btPlayer);
         System.out.println("WHEAT: " + btPlayer.getRessources().get(Ressource.WHEAT));
         System.out.println("SHEEP: " + btPlayer.getRessources().get(Ressource.SHEEP));
         System.out.println("ORE: " + btPlayer.getRessources().get(Ressource.ORE));
         System.out.println("WOOD: " + btPlayer.getRessources().get(Ressource.WOOD));
         System.out.println("Has thief: " + btPlayer.hasThief());*/
 
         /*-------------------------------------------------------------- BuyWarriors ---------------------------------------------------------------*/
         /*System.out.println("--------------------- BuyWarriors --------------------");
         Board bwBoard = new Board(10, 10);
         AresPlayer bwPlayer = new AresPlayer("Timoleon");
         BuyWarriors bw = new BuyWarriors(bwBoard);
         bwPlayer.addRessoure(Ressource.ORE);
         bwPlayer.addRessoure(Ressource.SHEEP, 2);
         bwPlayer.addRessoure(Ressource.WHEAT, 2);
         System.out.println("WHEAT: " + bwPlayer.getRessources().get(Ressource.WHEAT));
         System.out.println("SHEEP: " + bwPlayer.getRessources().get(Ressource.SHEEP));
         System.out.println("ORE: " + bwPlayer.getRessources().get(Ressource.ORE));
         System.out.println("WOOD: " + bwPlayer.getRessources().get(Ressource.WOOD));
         System.out.println("nbWarriors: " + bwPlayer.getNbWarrior());
         bw.act(bwPlayer);
         System.out.println("WHEAT: " + bwPlayer.getRessources().get(Ressource.WHEAT));
         System.out.println("SHEEP: " + bwPlayer.getRessources().get(Ressource.SHEEP));
         System.out.println("ORE: " + bwPlayer.getRessources().get(Ressource.ORE));
         System.out.println("WOOD: " + bwPlayer.getRessources().get(Ressource.WOOD));
         System.out.println("nbWarriors: " + bwPlayer.getNbWarrior());*/

        /*-------------------------------------------------------------- PlayThief ---------------------------------------------------------------*/
        /*System.out.println("--------------------- PlayThief --------------------");
        Board ptBoard = new Board(10, 10);
        DemeterPlayer ptPlayer = new DemeterPlayer("Timoleon");
        // Créer des joueurs à voler
        AresPlayer victim1 = new AresPlayer("Victim1");
        DemeterPlayer victim2 = new DemeterPlayer("Victim2");
        // Ajouter des ressources aux victimes
        victim1.addRessoure(Ressource.WHEAT, 3);
        victim2.addRessoure(Ressource.WHEAT, 2);
        // Ajouter un voleur au joueur principal
        ptPlayer.addThief();
        
        System.out.println("Player WHEAT: " + ptPlayer.getRessources().get(Ressource.WHEAT));
        System.out.println("Victim1 WHEAT: " + victim1.getRessources().get(Ressource.WHEAT));
        System.out.println("Victim2 WHEAT: " + victim2.getRessources().get(Ressource.WHEAT));
        System.out.println("Player has thief: " + ptPlayer.hasThief());
        
        PlayThief pt = new PlayThief(ptBoard);
        Player[] playersToSteal = {victim1, victim2};
        pt.act(ptPlayer, playersToSteal); // On simule le vol directement sans interaction
        
        System.out.println("After theft:");
        System.out.println("Player WHEAT: " + ptPlayer.getRessources().get(Ressource.WHEAT));
        System.out.println("Victim1 WHEAT: " + victim1.getRessources().get(Ressource.WHEAT));
        System.out.println("Victim2 WHEAT: " + victim2.getRessources().get(Ressource.WHEAT));
        System.out.println("Player has thief: " + ptPlayer.hasThief());*/
        
        /*-------------------------------------------------------------- Attack ---------------------------------------------------------------*/
        /*System.out.println("--------------------- Attack --------------------");
        Board attBoard = new Board(10, 10);
        attBoard.createGrid();
        AresPlayer attPlayer1 = new AresPlayer("Timoleon1");
        AresPlayer attPlayer2 = new AresPlayer("Timoleon2");
        AresPlayer attPlayer3 = new AresPlayer("Timoleon3");
        attPlayer2.addSecretWeapon();
        attPlayer3.addSecretWeapon();
        System.out.println("Player nb secret weapons: " + attPlayer2.getNbSecretWeapons());
        System.out.println("Player nb secret weapons: " + attPlayer3.getNbSecretWeapons());

        for(int i = 0; i < 10; i++){
            Land land = firstAvailableLand(attBoard, 10, 10);
            if(i%3 == 0){
                AresBuilding army = new AresBuilding(attPlayer1, land, 1);
                attPlayer1.getBuildings().add(army);
            } else if(i%3 == 1){
                AresBuilding army = new AresBuilding(attPlayer2, land, i+1);
                attPlayer2.getBuildings().add(army);
            } else {
                AresBuilding army = new AresBuilding(attPlayer3, land, 1);
                attPlayer3.getBuildings().add(army);
            } 
        }

        Attack attack = new Attack(attBoard);
        System.out.println(attBoard.boardToString(true));
        System.out.println(attack.isPossible(attPlayer2));
        attack.act(attPlayer2);
        System.out.println();
        System.out.println(attBoard.boardToString(true));
        System.out.println("Player nb secret weapons: " + attPlayer2.getNbSecretWeapons());
        System.out.println("Player nb secret weapons: " + attPlayer3.getNbSecretWeapons());*/

        /*-------------------------------------------------------------- PlaceWarriors ---------------------------------------------------------------*/
        /*System.out.println("--------------------- PlaceWarriors --------------------");
        Board pwBoard = new Board(10, 10);
        pwBoard.createGrid();
        AresPlayer pwPlayer = new AresPlayer("Timoleon");
        PlaceWarrior pw = new PlaceWarrior(pwBoard);
        pwPlayer.addRessoure(Ressource.WOOD);
        pwPlayer.addRessoure(Ressource.SHEEP);
        pwPlayer.addRessoure(Ressource.WHEAT);
        pwPlayer.addWarrior(10);

        for(int i = 0; i < 5; i++){
            Land land = firstAvailableLand(pwBoard, 10, 10);
            if(i<4){
                AresBuilding army = new AresBuilding(pwPlayer, land, 2);
                pwPlayer.getBuildings().add(army);
            } else{
                Port port = new Port(pwPlayer, land);
                pwPlayer.getBuildings().add(port);
            } 
        }    
        AresBuilding building3 = (AresBuilding) pwPlayer.getBuildings().get(0);
        building3.evolve();

        System.out.println("WHEAT: " + pwPlayer.getRessources().get(Ressource.WHEAT));
        System.out.println("SHEEP: " + pwPlayer.getRessources().get(Ressource.SHEEP));
        System.out.println("ORE: " + pwPlayer.getRessources().get(Ressource.ORE));
        System.out.println("WOOD: " + pwPlayer.getRessources().get(Ressource.WOOD));
        System.out.println("nbWarriors: " + pwPlayer.getNbWarrior());
        for (Building building : pwPlayer.getBuildings()){
            System.out.println("building: " + building + ", nbWarriors:" + ((building instanceof AresBuilding)?((LandBuilding) building).getDimension():0));
        }
        System.out.println(pwBoard.boardToString(true));
        pw.act(pwPlayer);
        System.out.println("WHEAT: " + pwPlayer.getRessources().get(Ressource.WHEAT));
        System.out.println("SHEEP: " + pwPlayer.getRessources().get(Ressource.SHEEP));
        System.out.println("ORE: " + pwPlayer.getRessources().get(Ressource.ORE));
        System.out.println("WOOD: " + pwPlayer.getRessources().get(Ressource.WOOD));
        System.out.println("nbWarriors: " + pwPlayer.getNbWarrior());
        for (Building building : pwPlayer.getBuildings()){
            System.out.println("building: " + building + ", nbWarriors:" + ((building instanceof AresBuilding)?((LandBuilding) building).getDimension():0));
        }
        System.out.println(pwBoard.boardToString(true));*/

        /*-------------------------------------------------------------- BuySecretWeapon ---------------------------------------------------------------*/
        /*System.out.println("--------------------- BuySecretWeapon --------------------");
        Board bswBoard = new Board(10, 10);
        bswBoard.createGrid();
        AresPlayer bswPlayer = new AresPlayer("Timoleon");
        BuySecretWeapon bsw = new BuySecretWeapon(bswBoard);
        bswPlayer.addRessoure(Ressource.WOOD,2);
        bswPlayer.addRessoure(Ressource.ORE,2);  

        System.out.println("WHEAT: " + bswPlayer.getRessources().get(Ressource.WHEAT));
        System.out.println("SHEEP: " + bswPlayer.getRessources().get(Ressource.SHEEP));
        System.out.println("ORE: " + bswPlayer.getRessources().get(Ressource.ORE));
        System.out.println("WOOD: " + bswPlayer.getRessources().get(Ressource.WOOD));
        System.out.println("nbSecretWeapons: " + bswPlayer.getNbSecretWeapons());
        System.out.println(bswBoard.boardToString(true));
        
        bsw.act(bswPlayer);
        
        System.out.println("WHEAT: " + bswPlayer.getRessources().get(Ressource.WHEAT));
        System.out.println("SHEEP: " + bswPlayer.getRessources().get(Ressource.SHEEP));
        System.out.println("ORE: " + bswPlayer.getRessources().get(Ressource.ORE));
        System.out.println("WOOD: " + bswPlayer.getRessources().get(Ressource.WOOD));
        System.out.println("nbSecretWeapons: " + bswPlayer.getNbSecretWeapons());
        System.out.println(bswBoard.boardToString(true));
        
        bsw.act(bswPlayer);
        
        System.out.println("WHEAT: " + bswPlayer.getRessources().get(Ressource.WHEAT));
        System.out.println("SHEEP: " + bswPlayer.getRessources().get(Ressource.SHEEP));
        System.out.println("ORE: " + bswPlayer.getRessources().get(Ressource.ORE));
        System.out.println("WOOD: " + bswPlayer.getRessources().get(Ressource.WOOD));
        System.out.println("nbSecretWeapons: " + bswPlayer.getNbSecretWeapons());
        System.out.println(bswBoard.boardToString(true));*/

        /*-------------------------------------------------------------- BuyWarriors ---------------------------------------------------------------*/
        System.out.println("--------------------- BuyWarriors --------------------");
        Board bwBoard = new Board(10, 10);
        bwBoard.createGrid();
        AresPlayer bwPlayer = new AresPlayer("Timoleon");
        BuyWarriors bw = new BuyWarriors(bwBoard);
        bwPlayer.addRessoure(Ressource.SHEEP,4);
        bwPlayer.addRessoure(Ressource.WHEAT,4);
        bwPlayer.addRessoure(Ressource.ORE,2);  

        System.out.println("WHEAT: " + bwPlayer.getRessources().get(Ressource.WHEAT));
        System.out.println("SHEEP: " + bwPlayer.getRessources().get(Ressource.SHEEP));
        System.out.println("ORE: " + bwPlayer.getRessources().get(Ressource.ORE));
        System.out.println("WOOD: " + bwPlayer.getRessources().get(Ressource.WOOD));
        System.out.println("nbWarriors: " + bwPlayer.getNbWarrior());
        System.out.println(bwBoard.boardToString(true));
        
        bw.act(bwPlayer);
        
        System.out.println("WHEAT: " + bwPlayer.getRessources().get(Ressource.WHEAT));
        System.out.println("SHEEP: " + bwPlayer.getRessources().get(Ressource.SHEEP));
        System.out.println("ORE: " + bwPlayer.getRessources().get(Ressource.ORE));
        System.out.println("WOOD: " + bwPlayer.getRessources().get(Ressource.WOOD));
        System.out.println("nbWarriors: " + bwPlayer.getNbWarrior());
        System.out.println(bwBoard.boardToString(true));
        
        bw.act(bwPlayer);
        
        System.out.println("WHEAT: " + bwPlayer.getRessources().get(Ressource.WHEAT));
        System.out.println("SHEEP: " + bwPlayer.getRessources().get(Ressource.SHEEP));
        System.out.println("ORE: " + bwPlayer.getRessources().get(Ressource.ORE));
        System.out.println("WOOD: " + bwPlayer.getRessources().get(Ressource.WOOD));
        System.out.println("nbWarriors: " + bwPlayer.getNbWarrior());
        System.out.println(bwBoard.boardToString(true));
    } 

    /**
     * Returns the first available land tile on the board i.e. the first tile that isn't a sea and doesn't have a building
     * @param board the board we want the land on
     * @param length the length of the board
     * @param width the width of the board
     * @return the first available land tile on the board
    */
    public static Land firstAvailableLand(Board board, int length, int width){
        Land land = new Forest(0, 0); // Définition de base de land, la fonction ne fonctionne pas si on ne définit pas cette variable 
        try{
            int x = 0;
            int y = 0;
            Tile tile = board.getTile(0, 0);
            while(x < length && ((tile instanceof Sea) || !(tile instanceof Sea) && ((Land) tile).hasBuilding())){
                while(y < width && ((tile instanceof Sea) || !(tile instanceof Sea) && ((Land) tile).hasBuilding())){
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

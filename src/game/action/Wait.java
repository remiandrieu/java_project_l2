package game.action;

public class Wait extends CommonAction{
    protected String label = "abstract CommonAction";

    public Wait(){
        super("wait");
    }

    public boolean isPossible() {
        return true;
    }

    public void act() {
    }

}

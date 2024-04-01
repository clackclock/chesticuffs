package Game.Slots;
import Game.*;
import Game.Slots.Board.*;

public class Positions{
    private Card fromHand;
    private final Board_Positions onTable;

    public Positions(Board_Positions type, Card inPlay){
        fromHand = inPlay;
        onTable = type;
    }

    public Board_Positions currentPlace(){
        return onTable;
    }

    public int[] getStats() {  return fromHand.getValue(onTable); }

    public int getAtk() { return fromHand.getValue(onTable)[0]; }

    public int getDef() { return fromHand.getValue(onTable)[1]; }

    public Card getSlot() {
        return fromHand;
    }

    public void remove(){fromHand = null;}

    public boolean isEmpty() { return fromHand == null; } //if empty return true else false
}

package Game.Slots;
import Game.*;
import Game.Slots.Board.*;

public class Positions implements boardPosition_Action{
    private Card fromHand;
    private static Board_Positions onTable;

    public Positions(Board_Positions type, Card inPlay){
        fromHand = inPlay;
        onTable = type;
    }

    public Board_Positions currentPlace(){
        return onTable;
    }
    //if openSlotsNum = number of cards in slots return false else true
    //public boolean hasOpenSlots() { return slot.size() != 0; } //onTable.getOSNum()
    public int[] getStats() {  return fromHand.getValue(onTable); }

    public int getAtk() { return fromHand.getValue(onTable)[0]; }

    public int getDef() { return fromHand.getValue(onTable)[1]; }

    public Card getSlot() {
        return fromHand;
    }

    public void remove(){fromHand = null;}
}

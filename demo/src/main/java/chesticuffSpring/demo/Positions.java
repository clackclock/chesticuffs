package chesticuffSpring.demo;

public class Positions{
    private Card fromHand;
    private final Board_Positions onTable;

    // Track local buffs, so we don't change the Card object itself
    private int tempAtk = 0;
    private int tempDef = 0;

    public Positions(Board_Positions type, Card inPlay){
        fromHand = inPlay;
        onTable = type;
    }

    public Board_Positions currentPlace(){
        return onTable;
    }

    public int[] getStats() {  return fromHand.getValue(onTable); }

    public int getAtk() {
        if (fromHand == null) return 0;
        return fromHand.getValue(onTable)[0] + tempAtk;
    }
    public int getDef() {
        if (fromHand == null) return 0;
        return fromHand.getValue(onTable)[1] + tempDef;
    }
    public void addBuff(int atk, int def) {
        this.tempAtk += atk;
        this.tempDef += def;
    }

    public Card getSlot() {
        return fromHand;
    }

    public void remove(){fromHand = null;}

    public boolean isEmpty() { return fromHand == null; } //if empty return true else false

    public String toString(){
        return fromHand.toString();
    }
}

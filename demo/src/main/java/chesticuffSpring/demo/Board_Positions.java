package chesticuffSpring.demo;

public enum Board_Positions { //implements boardPosition_Action
    UBER(0), ATTACK(1), CoreDEFENCE(-1), CORE(2), DEFENCE(3);
    private final int validIndex;

    //3,2,3,1,3

    Board_Positions(int index) {
        validIndex = index;
    }

    public int index() {
        return validIndex;
    }
}

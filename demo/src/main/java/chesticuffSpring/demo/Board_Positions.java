package chesticuffSpring.demo;

public enum Board_Positions {
    UBER(0),        // Row 0
    ATTACK(1),      // Row 1
    CoreDEFENCE(2), // Row 2 (Used to be -1, changed to 2)
    CORE(4),        // Special case or Row 4
    DEFENCE(3);     // Row 3
    //3 slots, 2 slots, 3 slots, 1 slot, 3 slots

    private final int validIndex;
    Board_Positions(int index) {
        validIndex = index;
    }
    public int index() {
        return validIndex;
    }
}
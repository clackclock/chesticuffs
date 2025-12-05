package chesticuffSpring.demo;

import java.util.HashMap;

public class PosMap {
    private final HashMap<Board_Positions, int[]> positionMap = new HashMap<>();

    public PosMap(int[] uAtk, int[] atk, int[] cDef, int[] corn, int[] def) {
        positionMap.put(Board_Positions.UBER, uAtk);
        positionMap.put(Board_Positions.ATTACK, atk);
        positionMap.put(Board_Positions.CoreDEFENCE, cDef);
        positionMap.put(Board_Positions.CORE, corn);
        positionMap.put(Board_Positions.DEFENCE, def);
    }

    public int[] row(Board_Positions pos) {
        return positionMap.get(pos);
    }

}

package Game.Slots;

import java.util.HashMap;

import static Game.Slots.Board.Board_Positions.*;

public class PosMap {
<<<<<<< HEAD
=======

>>>>>>> origin/master
    private final HashMap<Board.Board_Positions, int[]> positionMap = new HashMap<>();

    public PosMap(int[] uAtk, int[] atk, int[] cDef, int[] corn, int[] def) {
        positionMap.put(UBER, uAtk);
        positionMap.put(ATTACK, atk);
        positionMap.put(CoreDEFENCE, cDef);
        positionMap.put(CORE, corn);
        positionMap.put(DEFENCE, def);
    }

    public int[] row(Board.Board_Positions pos) {
        return positionMap.get(pos);
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> origin/master

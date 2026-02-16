package chesticuffSpring.demo;

import org.springframework.stereotype.Service;
import java.util.Random;

@Service
public class ItemService {
    private final Random random = new Random();

    public void useItem(Card itemCard, Positions target, Board playerBoard) {
        int itemID = itemCard.getId();

        switch (itemID) {
            case 16 -> fishingRod(target);
            case 30 -> healingPotion(target);
            case 12 -> door(playerBoard, target);
            case 22 -> waterBucket(target);
        }
    }

    private void fishingRod(Positions target) {
        // 50/50 chance to "reel in" (remove) the card
        if (random.nextBoolean()) {
            target.remove();
        }
    }

    private void healingPotion(Positions target) {
        if (target != null && !target.isEmpty()) {
            // We apply a "Temporary Buff" rather than changing the base Card data
            target.addBuff(0, 3); // 0 Atk, 3 Def
        }
    }

    private void door(Board board, Positions target) {
        // Logic: If there's another door on the UBER row, buff the target
        boolean hasAnotherDoor = board.isCardOnRow(12, Board_Positions.UBER);
        if (hasAnotherDoor && target != null) {
            target.addBuff(2, 0);
        }
    }

    private void waterBucket(Positions target) {
        if (target != null) {
            target.addBuff(-1, -1);
        }
    }
}
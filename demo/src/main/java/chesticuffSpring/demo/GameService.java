package chesticuffSpring.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class GameService {
    @Autowired
    private final CardService cardService;
    public GameService(CardService cardService) {
        this.cardService = cardService;
    }

    public boolean playCard(Player player, int handIndex, Board_Positions pos, int col) {
        List<Card> hand = player.getHand();
        Board board = player.getBoard();

        if (handIndex < 0 || handIndex >= hand.size()) return false;
        if (board.getGrid()[pos.index()][col] != null) return false;

        Card cardToPlay = hand.remove(handIndex);
        board.addToSlots(pos, col, cardToPlay);

        // Using your specific method name
        checkEvolutions(player, cardToPlay.getId());

        return true;
    }
    public void endTurn(Player player, Player opponent) {
        Board playerBoard = player.getBoard();
        Board opponentBoard = opponent.getBoard();
        List<Skill> skills = cardService.getSkills();

        // 1. Calculate Combat (this runs reset, core stats, skills, and combat resolution)
        playerBoard.calculate(opponentBoard, skills);

        // 2. Cleanup Phase: Remove Metal and Ore cards used for temporary buffs
        playerBoard.removeMetalOre();
        opponentBoard.removeMetalOre();

        // 3. Post-Combat Check: See if any builds were destroyed or need resetting
        if (playerBoard.isBoardEmpty()) {
            playerBoard.removeBuild();
        }
    }
    public boolean processBuild(Player player, Recipe recipe) {
        Board board = player.getBoard();

        // 1. Verify requirements (using your Player's helper method)
        if (player.hasIngredients(recipe.ingredients)) {

            // 2. Pay the cost
            player.consumeIngredients(recipe.ingredients);

            // 3. Get the template from CardService
            Card template = cardService.getCardById(recipe.getTargetCardId());

            if (template != null) {
                // 4. Create the Build object
                Build newBuild = new Build(
                        template.getId(),
                        template.getCardName(),
                        template.getValue(Board_Positions.UBER),
                        template.getValue(Board_Positions.ATTACK),
                        template.getValue(Board_Positions.CoreDEFENCE),
                        template.getValue(Board_Positions.CORE),
                        template.getValue(Board_Positions.DEFENCE),
                        template.getCardTypeONE(),
                        template.getCardTypeTWO(),
                        template.getImageID()
                );

                // 5. Update the board state
                board.addBuild(newBuild);
                return true;
            }
        }
        return false;
    }

    //Evolutions look at the board, check for the card then check the number. if it's not there or there is not enough space move on
    public void checkEvolutions(Player player, int justPlacedID) {
        // list directly from our loaded database
        List<Evolution> rules = cardService.getEvolutions();
        Board board = player.getBoard();

        for (Evolution evo : rules) {
            // Only check the evolution rules for the card that was just played
            if (justPlacedID != evo.itemIDToEvolve) continue;

            // 1. Find all matching cards currently on the board
            List<Positions> matches = Arrays.stream(board.getGrid())
                    .flatMap(Arrays::stream)
                    .filter(p -> p != null && !p.isEmpty() && p.getSlot().getId() == evo.itemIDToEvolve)
                    .toList();

            // 2. Check if we have enough cards to trigger the "Craft"
            if (matches.size() >= evo.requiredNumOfItem) {
                System.out.println("Evolution Triggered: " + evo.name);

                // 3. Remove the 'consumed' cards from the Board
                for (int i = 0; i < evo.requiredNumOfItem; i++) {
                    matches.get(i).remove();
                }

                // 4. Fetch the upgraded card template from our "Database" (CardService)
                Card upgradedCard = cardService.getCardById(evo.finalItemID);

                if (upgradedCard != null) {
                    // 5. Add the resulting upgraded cards to the Player's hand
                    for (int p = 0; p < evo.numAddToBoard; p++) {
                        player.getHand().add(upgradedCard);
                    }
                    System.out.println("Added " + evo.numAddToBoard + "x " + upgradedCard.getCardName() + " to hand.");
                }
            } else {
                System.out.println("Evolution progress: " + matches.size() + "/" + evo.requiredNumOfItem);
            }
        }
    }
}
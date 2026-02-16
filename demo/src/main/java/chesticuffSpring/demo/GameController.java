package chesticuffSpring.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/game")
public class GameController {
    private final GameService gameService;
    private final CardService cardService = new CardService();
    private final Player player = new Player();
    private final Player enemyPlayer = new Player();

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    public String showBoard(Model model) {
        model.addAttribute("player", player);
        model.addAttribute("board", player.getBoard());
        model.addAttribute("hand", player.getHand());
        return "game-view"; // This points to your game-view.html (Thymeleaf)
    }
    @PostMapping("/play")
    public String handlePlayCard(@RequestParam int handIndex,
                                 @RequestParam Board_Positions pos,
                                 @RequestParam int col) {

        gameService.playCard(player, handIndex, pos, col);

        // After playing, we redirect back to the board to show the update
        return "redirect:/game";
    }
    @PostMapping("/build")
    public String handleBuild(@RequestParam int recipeID) {
        // Find the recipe the user clicked on
        cardService.getRecipes().stream()
                .filter(r -> r.getTargetCardId() == recipeID)
                .findFirst().ifPresent(choice -> gameService.processBuild(player, choice));

        return "redirect:/game";
    }
    @PostMapping("/end-turn")
    public String handleEndTurn() {
        // In a single-player test, 'enemyPlayer' could be a dummy
        // or a second player instance stored in your Controller
        gameService.endTurn(player, enemyPlayer);

        return "redirect:/game";
    }
}
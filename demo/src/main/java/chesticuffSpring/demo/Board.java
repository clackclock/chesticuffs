package chesticuffSpring.demo;

import java.util.*;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;

public class Board {
    private final Positions[][] board_Grid;
    private final int[] uberRow = {0, 0}, atkRow = {0, 0}, cDefRow = {0, 0}, coreBlock = {0, 0}, defRow = {0, 0};
    private final PosMap posMap = new PosMap(uberRow, atkRow, cDefRow, coreBlock, defRow);
    private Build currentBuild;
    private boolean isThereBuild = false;

    public Board() {
        board_Grid = new Positions[5][3];
    } //4 rows 3 col, everything has its own row in the code, but will be fixed for the UI

    public boolean isBoardEmpty() {
        return Arrays.stream(board_Grid)
                .flatMap(Arrays::stream)
                .allMatch(p -> p == null || p.isEmpty());
    }

    public void addToSlots(Board_Positions pos, int col, Card card) {
        int row = pos.index();
        if (row >= 0 && row < board_Grid.length && col >= 0 && col < 3) {
            board_Grid[row][col] = new Positions(pos, card);
        }
    }
    public void removeFromSlots(Board_Positions pos, int col) {
        int row = pos.index();
        if (row >= 0 && row < board_Grid.length && col >= 0 && col < 3) {
            board_Grid[row][col] = null;
        }
    }
    public void clearRow(Board_Positions pos) {
        int rowIndex = pos.index();
        if (rowIndex != -1) {
            for (int i = 0; i < 3; i++) {
                if (board_Grid[rowIndex][i] != null) board_Grid[rowIndex][i].remove();
            }
        }
    }


// --   CALCULATION LOGIC - THE REALM OF NONSENSE --
    private Map<Board_Positions, Integer> positionSum(ToIntFunction<Positions> mapper) {
        return Arrays.stream(board_Grid).flatMap(Arrays::stream).
                filter(Objects::nonNull).collect(groupingBy(Positions::currentPlace, Collectors.summingInt(mapper)));
    }
    private void resetValues(Map<Board_Positions, Integer> atkMap, Map<Board_Positions, Integer> defMap) {
        for (Board_Positions position : Board_Positions.values()) {
            posMap.row(position)[0] = atkMap.getOrDefault(position, 0);
            posMap.row(position)[1] = defMap.getOrDefault(position, 0);
        }
    }
    public void removeMetalOre() {
        for (int i = 0; i < board_Grid.length; i++) {
            for (int j = 0; j < board_Grid[i].length; j++) {
                Positions p = board_Grid[i][j];

                // Only check if there's actually a card in the slot
                if (p != null && !p.isEmpty()) {
                    Card card = p.getSlot();

                    // Check if either the primary or secondary type matches
                    boolean isMetalOrOre = card.eitherType("METAL") || card.eitherType("ORE");

                    if (isMetalOrOre) {
                        board_Grid[i][j].remove(); // Clear the reference in the slot
                        board_Grid[i][j] = null;    // Remove the position object itself
                    }
                }
            }
        }
    }
    private void modify(Stream<Positions> s, int mAtk, int mDef) { // SHOUT OUT ATHAR FOR SAVING MY CODE^^
        s.forEach(p -> {
            posMap.row(p.currentPlace())[0] += mAtk;
            posMap.row(p.currentPlace())[1] += mDef;
        });
    }
    public void calculate(Board enemy, List<Skill> skillList) {
        // 1. Reset current board stats to the base card values
        resetValues(positionSum(Positions::getAtk), positionSum(Positions::getDef));
        enemy.resetValues(enemy.positionSum(Positions::getAtk), enemy.positionSum(Positions::getDef));

        // 2. Apply Build/Core Bonuses
        applyCoreStats(this);
        applyCoreStats(enemy);

        // 3. Apply Global Skills from JSON - check every card on the board against every skill in the list
        applyAllSkills(this, skillList);
        applyAllSkills(enemy, skillList);

        // 4. Combat Resolution
        resolveCombat(this, enemy);
    }

    private void resolveCombat(Board b1, Board b2) {
        Board_Positions[] combatRows = {Board_Positions.UBER, Board_Positions.ATTACK, Board_Positions.CoreDEFENCE, Board_Positions.DEFENCE};
        for (Board_Positions pos : combatRows) {
            if (b1.posMap.row(pos)[0] > b2.posMap.row(pos)[1]) b2.clearRow(pos);
            if (b2.posMap.row(pos)[0] > b1.posMap.row(pos)[1]) b1.clearRow(pos);
        }
    }


    public boolean isCardOnRow(int cardId, Board_Positions pos) {
        int rowIndex = pos.index();

        // CoreDEFENCE fix: if your index is -1, you need to check both row 1 and 2
        // based on your current board logic.
        if (rowIndex == -1) {
            return checkRowForId(1, cardId) || checkRowForId(2, cardId);
        }

        return checkRowForId(rowIndex, cardId);
    }
    private boolean checkRowForId(int rowIndex, int cardId) {
        // Ensure the index is within the 4-row bounds
        if (rowIndex < 0 || rowIndex >= board_Grid.length) return false;

        for (int col = 0; col < 3; col++) {
            Positions p = board_Grid[rowIndex][col];
            if (p != null && !p.isEmpty() && p.getSlot().getId() == cardId) {
                return true;
            }
        }
        return false;
    }

    private void applyAllSkills(Board board, List<Skill> skills) {
        for (Skill skill : skills) {
            // Find if the 'Skill Provider' card is actually on the board
            boolean providerPresent = Arrays.stream(board.getGrid())
                    .flatMap(Arrays::stream)
                    .anyMatch(p -> p != null && p.getSlot().getId() == skill.cardID);

            if (providerPresent) {
                // Apply the mod to all cards matching the criteria
                for (Positions[] row : board.getGrid()) {
                    for (Positions p : row) {
                        if (p == null) continue;

                        boolean typeMatches = p.getSlot().eitherType(skill.typeCheck);
                        boolean posMatches = (skill.posCheck == null) ||
                                p.currentPlace().name().equals(skill.posCheck);

                        if (typeMatches && posMatches) {
                            board.posMap.row(p.currentPlace())[0] += skill.modValueAtk;
                            board.posMap.row(p.currentPlace())[1] += skill.modValueDef;
                        }
                    }
                }
            }
        }
    }
    private void applyCoreStats(Board b) {
        if (b.hasBuild()) {
            int[] coreMod = b.currentBuild.getCoreMod();
            for (Board_Positions pos : Board_Positions.values()) {
                if (pos == Board_Positions.CORE) continue; // Don't boost the core with itself
                b.posMap.row(pos)[0] += coreMod[0];
                b.posMap.row(pos)[1] += coreMod[1];
            }
        }
    }


    public Positions[][] getGrid() {return board_Grid;}
    public boolean hasBuild() {
        return isThereBuild;
    }
    public void addBuild(Build x) {currentBuild = x; isThereBuild = true;}
    public void removeBuild() {if (hasBuild()) {currentBuild = null; isThereBuild = false;}}
}
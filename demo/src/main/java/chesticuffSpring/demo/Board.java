package chesticuffSpring.demo;

import com.fasterxml.jackson.databind.JsonNode;

import java.io.*;
import java.util.*;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static chesticuffSpring.demo.Board_Positions.*;
import static java.util.stream.Collectors.groupingBy;

public class Board {

    private final Positions[][] board_Grid;
    public Positions[][] getGrid(){ return board_Grid; }

    private final int[] uberRow = {0,0}, atkRow = {0,0}, cDefRow = {0,0}, coreBlock = {0,0}, defRow = {0,0};
    private final PosMap posMap = new PosMap(uberRow, atkRow, cDefRow, coreBlock, defRow);
    public Board(){ board_Grid = new Positions[4][3]; } //4 rows 3 col
    public boolean isBoardEmpty(){
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 3; j++){
                if(board_Grid[0][j] == null && board_Grid[1][j] == null && board_Grid[2][0] == null && board_Grid[2][2] == null && board_Grid[3][j] == null ){
                    return true;
                }
            }
        }
        return false;
    }

    public int[] getUberRow(){ return uberRow; }
    public int[] getAtkRow(){ return atkRow; }
    public int[] getCDefRow(){ return cDefRow; }
    public int[] getCoreBlock(){ return coreBlock; }
    public int[] getDefRow(){return defRow; }

    private Map<Board_Positions, Integer> positionSum(ToIntFunction<Positions> mapper) {
        return Arrays.stream(board_Grid).flatMap(Arrays::stream).
                filter(Objects::nonNull).collect(groupingBy(Positions::currentPlace,  Collectors.summingInt(mapper)));
    }
    private void resetValues(Map<Board_Positions, Integer> atkMap, Map<Board_Positions, Integer> defMap) {
        for (Board_Positions position : Board_Positions.values()) {
            posMap.row(position)[0] = atkMap.getOrDefault(position, 0);
            posMap.row(position)[1] = defMap.getOrDefault(position, 0);
        }
    }
    private void modify(Stream<Positions> s, int mAtk, int mDef) { // credit ^^
        s.forEach( p -> {
            posMap.row(p.currentPlace())[0] += mAtk;
            posMap.row(p.currentPlace())[1] += mDef;
        });
    }

    public void calculate(Board enemy, BoardDataService dataService) throws IOException{
        Map<Board_Positions, Integer> atkMap = positionSum(Positions::getAtk);
        Map<Board_Positions, Integer> defMap = positionSum(Positions::getDef);
        Map<Board_Positions, Integer> enemyAtkMap = enemy.positionSum(Positions::getAtk);
        Map<Board_Positions, Integer> enemyDefMap = enemy.positionSum(Positions::getDef);

        resetValues(atkMap, defMap);
        enemy.resetValues(enemyAtkMap, enemyDefMap);

        // Access modifier data from the service
        JsonNode skillList = dataService.getModifierData().path("deck").path("cards");

        for (JsonNode cList : skillList) {
            int cID = cList.get("cardID").asInt();
            String typeBase = cList.get("skillType").asText();

            int mAtk = cList.get("modValueAtk").asInt();
            int mDef = cList.get("modValueDef").asInt();

            String tC = cList.has("typeCheck") ? cList.get("typeCheck").asText() : "";

            Board_Positions pCheck = cList.has("posCheck")
                    ? Board_Positions.valueOf(cList.get("posCheck").asText())
                    : null;

            Stream<Positions> s = Arrays.stream(board_Grid).flatMap(Arrays::stream).
                    filter(Objects::nonNull).filter(p -> p.getSlot().getId() == cID);
            Stream<Positions> e = Arrays.stream(enemy.board_Grid).flatMap(Arrays::stream).
                    filter(Objects::nonNull).filter(p -> p.getSlot().getId() == cID);

            switch (typeBase) {
                case "type" -> {
                    modify(s.filter(p -> p.getSlot().eitherType(tC)), mAtk, mDef);
                    enemy.modify(e.filter(p -> p.getSlot().eitherType(tC)), mAtk, mDef);
                }
                case "position" -> {
                    modify(s.filter(p -> p.currentPlace() == pCheck), mAtk, mDef);
                    enemy.modify(e.filter(p -> p.currentPlace() == pCheck), mAtk, mDef);
                }
                case "type-position" -> {
                    modify(s.filter(p -> p.currentPlace() == pCheck).filter(p -> p.getSlot().eitherType(tC)), mAtk, mDef);
                    enemy.modify(e.filter(p -> p.currentPlace() == pCheck).filter(p -> p.getSlot().eitherType(tC)), mAtk, mDef);
                }
                case "card" -> {
                    modify(s, mAtk, mDef);
                    enemy.modify(e, mAtk, mDef);
                }
            }
        }

        //add core
        for(int r = 0; r < 2; r++){ //0 is atk && 1 is def, so it sorts itself out
            //combo core input
            if(hasBuild()){
                coreBlock[r] = currentBuild.getCoreMod()[r];
            }
            if(enemy.hasBuild()){
                enemy.getCoreBlock()[r] = enemy.currentBuild.getCoreMod()[r];
            }

            uberRow[r] = uberRow[r] + coreBlock[r]; //atk
            enemy.getUberRow()[r] = enemy.getUberRow()[r] + enemy.getCoreBlock()[r];
            atkRow[r] = atkRow[r] + coreBlock[r];
            enemy.getAtkRow()[r] = enemy.getAtkRow()[r] + enemy.getCoreBlock()[r];
            cDefRow[r] = cDefRow[r] + coreBlock[r];
            enemy.getCDefRow()[r] = enemy.getCDefRow()[r] + enemy.getCoreBlock()[r];
            defRow[r] = defRow[r] + coreBlock[r];
            enemy.getDefRow()[r] = enemy.getDefRow()[r] + enemy.getCoreBlock()[r];
        }

        //overflow was here

        //if board row is greater remove cards from other board vice versa
        //enemy lose action = take away cards
        if(uberRow[0] > enemy.getUberRow()[1] && uberRow[1] > enemy.getUberRow()[0]){
            for(int i = 0; i < 3; i++) {
                if(enemy.getGrid()[0][i] != null) {
                    enemy.getGrid()[0][i].remove();
                }
            }
        } else if(uberRow[0] < enemy.getUberRow()[1] && uberRow[1] < enemy.getUberRow()[0]){
            for(int i = 0; i < 3; i++) {
                if(board_Grid[0][i] != null) {
                    board_Grid[0][i].remove();
                }
            }
        }
        if(atkRow[0] > enemy.getAtkRow()[1] && atkRow[1] > enemy.getAtkRow()[0]){
            for(int i = 0; i < 3; i++) {
                if(enemy.getGrid()[1][i] != null && enemy.getGrid()[1][i].currentPlace() == ATTACK ){
                    enemy.getGrid()[1][i].remove();
                }
            }
        } else if(uberRow[0] < enemy.getUberRow()[1] && uberRow[1] < enemy.getUberRow()[0]){
            for(int i = 0; i < 3; i++) {
                if(board_Grid[1][i] != null && board_Grid[1][i].currentPlace() == ATTACK ){
                    board_Grid[1][i].remove();
                }
            }
        }
        if(cDefRow[0] > enemy.getCDefRow()[1] && cDefRow[1] > enemy.getCDefRow()[0]){
            for(int i = 1; i < 3; i++) {
                for(int j = 0; j < 3; j++) {
                    if (enemy.getGrid()[i][j] != null && enemy.getGrid()[i][j].currentPlace() == CoreDEFENCE) {
                        enemy.getGrid()[i][j].remove();
                    }
                }
            }
        } else if(cDefRow[0] < enemy.getCDefRow()[1] && cDefRow[1] < enemy.getCDefRow()[0]){
            for(int i = 1; i < 3; i++) {
                for(int j = 0; j < 3; j++) {
                    if (board_Grid[i][j] != null && board_Grid[i][j].currentPlace() == CoreDEFENCE) {
                        board_Grid[i][j].remove();
                    }
                }
            }
        }
        if(defRow[0] > enemy.getDefRow()[1] && defRow[1] > enemy.getDefRow()[0]){
            for(int i = 0; i < 3; i++) {
                if(enemy.getGrid()[3][i] != null) {
                    enemy.getGrid()[3][i].remove();
                }
            }
        } else if(defRow[0] < enemy.getDefRow()[1] && defRow[1] < enemy.getDefRow()[0]){
            for(int i = 0; i < 3; i++) {
                if(board_Grid[3][i] != null){
                    board_Grid[3][i].remove();
                }
            }
        }
    }

    private Build currentBuild;
    public boolean hasBuild(){ return isThereBuild; }
    public void removeBuild(){if(hasBuild()){currentBuild = null; isThereBuild = false;} }
    public void addBuild(Build x){ currentBuild = x; isThereBuild = true; }
    private boolean isThereBuild = false;


    public void addToSlots(String name, Card fromHand) {
        System.out.println("Which slot in this position? (0-2) (if CORE, only 0");
        Scanner input = new Scanner(System.in);
        int colChoice = input.nextInt();
        Board_Positions pos = Board_Positions.valueOf(name);
        int index = pos.index();
        if (index == -1) {
            // this must be CoreDef?
            System.out.println("Which Row? (1 or 2)");
            index = input.nextInt();
        }

        if (board_Grid[index][colChoice] == null) {
            board_Grid[index][colChoice] = new Positions(pos, fromHand);
            int atk = board_Grid[index][colChoice].getAtk();
            int def = board_Grid[index][colChoice].getDef();
            posMap.row(pos)[0] += atk;
            posMap.row(pos)[1] += def;
        }

    }
    public void removeFromSlots(String name, int posIndex) {
        Board_Positions pos = Board_Positions.valueOf(name);
        int index = pos.index();
        if (index == -1) {
          Scanner input = new Scanner(System.in);
          System.out.println("Which row? 1 or 2");
          index = input.nextInt();
        }

        posMap.row(pos)[0] -= board_Grid[index][posIndex].getAtk();
        posMap.row(pos)[1] -= board_Grid[index][posIndex].getDef();
        board_Grid[index][posIndex] = null;

    }
    public void removeMetalOre(){
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 3; j++){
                if(board_Grid[i][j] != null) {
                    Card metalOre = board_Grid[i][j].getSlot();
                    if (metalOre.getCardTypeONE().equals("METAL") || metalOre.getCardTypeONE().equals("ORE")) {
                        board_Grid[i][j].remove();
                    }
                }
            }
        }
    }


    //evolutions can scan the board check for the card then check the number if it's not there or there is not enough space move on
    public void evolve(Player cc, int cID, BoardDataService dataService) throws IOException{

        // Access evolution data from the service
        JsonNode eList = dataService.getEvolutionData().path("evolve");

        for(JsonNode c : eList){
            int oCard = c.get("itemIDToEvolve").asInt();
            int checkNum = c.get("requiredNumOfItem").asInt();

            int newCard = c.get("finalItemID").asInt();
            int newCardNum = c.get("numAddToBoard").asInt();

            int tally = 0;
            if(cID == oCard) {
                for (int n = 0; n < 4; n++) {
                    for (int o = 0; o < 3; o++) {
                        if(board_Grid[n][o] != null && board_Grid[n][o].getSlot().getId() == oCard){
                            tally++;
                        }
                    }
                }
                if(tally >= checkNum){
                    // A. Remove old cards
                    for (int n = 0; n < 4; n++) {
                        for (int o = 0; o < 3; o++) {
                            if(board_Grid[n][o] != null && board_Grid[n][o].getSlot().getId() == oCard){
                                board_Grid[n][o].remove();
                            }
                        }
                    }

                    // B. Find and add new cards to hand using the service
                    Card newCardTemplate = dataService.getCardById(newCard);

                    if (newCardTemplate != null) {
                        for(int p = 0; p < newCardNum; p++){
                            cc.getHand().add(newCardTemplate);
                        }
                    } else {
                        System.err.println("Error: Evolution card ID " + newCard + " not found in card data.");
                    }
                } else{
                    System.out.println("Not enough items to evolve.");
                }
            }
        }
    }
}
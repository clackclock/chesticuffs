package Game.Slots;

import Game.Card;
import Game.cardDatabase;
import Game.ComboBuild;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import static Game.Slots.Board.Board_Positions.*;

public class Board {
    public enum Board_Positions { //implements boardPosition_Action
        //3,2,3,1,3
        UBER(), ATTACK(), CoreDEFENCE(), CORE(),DEFENCE()
//        Board_Positions(int openSlots){}
    }

    private final Positions[][] board_Grid;
    private ComboBuild currentBuild;
    private boolean isThereBuild = false;
    public Board(){
        board_Grid = new Positions[4][3]; //4 rows 3 col
    }

    public void addBuild(ComboBuild x){ currentBuild = x; isThereBuild = true; }
    public void removeBuild(){currentBuild = null; isThereBuild = false; }
    public ComboBuild getCurrentBuild(){ return currentBuild; }

    public void addToSlots(String name, Card fromHand) {
        System.out.println("Which slot in this position? (0-2)");
        Scanner input = new Scanner(System.in);
        int colChoice = input.nextInt();
        switch (name) {
            case "UBER" -> {
                //for each uber position on the grid //if there are less than uber open slot amount of uber positions
                if(board_Grid[0][colChoice] == null){
                    board_Grid[0][colChoice] = new Positions(UBER, fromHand);
                }
            }
            case "ATTACK" -> {
                if(board_Grid[1][colChoice] == null ){ // only col 0 & 2
                    board_Grid[1][colChoice] = new Positions(ATTACK, fromHand);
                }
            }
            case "CoreDEFENCE" -> {
                System.out.println("Which Row? (1 or 2)");
                int rowChoice = input.nextInt();
                if(board_Grid[rowChoice][colChoice] == null) { // only 1,1 2,0 2,2
                    board_Grid[rowChoice][colChoice] = new Positions(CoreDEFENCE, fromHand);
                }
            }
            case "CORE" -> {
                if(board_Grid[2][1] == null){
                    board_Grid[2][1] = new Positions(CORE, fromHand);
                }
            }
            case "DEFENCE" -> {
                if(board_Grid[3][colChoice] == null){
                    board_Grid[3][colChoice] = new Positions(DEFENCE, fromHand);
                }
            }
        }
    }
    public void removeFromSlots(String name, int posIndex) {
        switch (name) {
            case "UBER" -> board_Grid[posIndex][0].remove();
            case "ATTACK" -> board_Grid[posIndex][1].remove(); //only 0 and 2 are valid
            case "CoreDEFENCE" -> {
                Scanner input = new Scanner(System.in);
                System.out.println("Which row? 1 or 2");
                int rowSelect = input.nextInt();
                board_Grid[posIndex][rowSelect].remove(); // only 0 and 2 are valid for row 2 and 1 is valid row 1
            }
            case "CORE" -> board_Grid[2][1].remove(); // only 1 is valid
            case "DEFENCE" -> board_Grid[posIndex][3].remove();
        }

    }

    public Positions[][] getGrid(){
        return board_Grid;
    }

    public boolean isBoardEmpty(){
        int tally = 0;
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 3; j++){
                if(board_Grid[i][j] == null){
                    tally++;
                }
            }
        }
        return tally == board_Grid.length;
    }
    public void removeMetalOre(){
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 3; j++){
                Card metalOre = board_Grid[i][j].getSlot();
                if(metalOre.activeTypeOne().equals("METAL") || metalOre.activeTypeOne().equals("ORE")){
                    board_Grid[i][j].remove();
                }
            }
        }
    }

    public boolean hasBuild(){ return isThereBuild; }

    public ComboBuild showValidBuilds(Board playerBoard) throws IOException {
        if(playerBoard.hasBuild()){
            return playerBoard.getCurrentBuild();
        }

        try(FileReader modReader = new FileReader("C:\\Users\\sensa\\IdeaProjects\\testGame\\src\\Game\\CardData\\Formation_Recipes.json")) {
            StringBuilder recipeString = new StringBuilder(" ");
            int i;
            while ((i = modReader.read()) != -1) {
                //System.out.print((char)i);
                recipeString.append((char) i);
            }
            JSONObject jsonMODObject = new JSONObject(recipeString.toString());
            JSONArray cookBook = (JSONArray) jsonMODObject.get("recipe_List");

            int checkResults = 0;

            for(int j = 0; j < cookBook.length(); j++ ){
                JSONObject tmpRecipe = cookBook.getJSONObject(j);
                JSONArray recipeIngredients = tmpRecipe.getJSONArray("ingredients");

                for(int k = 0; k < recipeIngredients.length(); k++){
                    JSONObject tmpIngredients = recipeIngredients.getJSONObject(k);
                    boolean needCard = tmpIngredients.getBoolean("cardCheck");
                    boolean needType = tmpIngredients.getBoolean("typeCheck");
                    JSONArray getPos = tmpIngredients.getJSONArray("position");

                    if(!needCard && needType){
                        String tmpType = tmpIngredients.getString("type");

                        for(int l = 0; l < getPos.length(); l++){
                            JSONObject tmpPos = getPos.getJSONObject(l);

                            boolean needMorePoints = tmpPos.getBoolean("additionalPoints");
                            if(!needMorePoints){
                                String pName = tmpPos.getString("posName");
                                //switch to the corresponding row for the posName to check the types
                                switch(pName) {
                                    default -> System.exit(1);
                                    case "UBER" -> {
                                        for (int p = 0; p < 3; p++) {
                                            if (playerBoard.getGrid()[0][p].getSlot().activeTypeOne().equals(tmpType)) {
                                                checkResults++; // log the correct the go check others
                                            }
                                        }
                                    }
                                    case "ATTACK" -> {
                                        if (playerBoard.getGrid()[1][0].getSlot().activeTypeOne().equals(tmpType) || playerBoard.getGrid()[1][2].getSlot().activeTypeOne().equals(tmpType)) {
                                            checkResults++; // log the correct the go check others
                                        }
                                    }
                                    case "CoreDEFENCE" -> {
                                        if (playerBoard.getGrid()[1][1].getSlot().activeTypeOne().equals(tmpType) || playerBoard.getGrid()[2][0].getSlot().activeTypeOne().equals(tmpType) || playerBoard.getGrid()[2][2].getSlot().activeTypeOne().equals(tmpType)) {
                                            checkResults++; // log the correct the go check others
                                        }
                                    }
                                    case "CORE" -> {
                                        if (playerBoard.getGrid()[2][1].getSlot().activeTypeOne().equals(tmpType)) {
                                            checkResults++;
                                        }
                                    }
                                    case "DEFENCE" -> {
                                        for (int p = 0; p < 3; p++) {
                                            if (playerBoard.getGrid()[3][p].getSlot().activeTypeOne().equals(tmpType)) {
                                                checkResults++; // log the correct the go check others
                                            }
                                        }
                                    }
//
                                }

                            }
                            //points just add to the card ig

                        }
                        //check all positions the check results
                        JSONObject getResult = tmpRecipe.getJSONObject("result");
                        int r = getResult.getInt("passNum");
                        if(checkResults == r){
                            int buildID = tmpRecipe.getInt("formID");
                            cardDatabase fd = new cardDatabase();
                            for(ComboBuild x: fd.formPack){
                                if(x.getId() == buildID){
                                    System.out.println(x.getItemName());
                                    return x;
                                }
                            }
                        }

                    }else if(needCard && !needType){
                        int tmpID = tmpIngredients.getInt("cardID");

                        for(int l = 0; l < getPos.length(); l++){
                            JSONObject tmpPos = getPos.getJSONObject(l);

                            boolean needMorePoints = tmpPos.getBoolean("additionalPoints");
                            if(!needMorePoints){
                                String pName = tmpPos.getString("posName");
                                //switch to the corresponding row for the posName to check the types
                                switch(pName) {
                                    default -> System.exit(1);
                                    case "UBER" -> {
                                        for (int p = 0; p < 3; p++) {
                                            if (playerBoard.getGrid()[0][p].getSlot().getId() == tmpID) {
                                                checkResults++; // log the correct the go check others
                                            }
                                        }
                                    }
                                    case "ATTACK" -> {
                                        if (playerBoard.getGrid()[1][0].getSlot().getId() == tmpID || playerBoard.getGrid()[1][2].getSlot().getId() == tmpID) {
                                            checkResults++; // log the correct the go check others
                                        }
                                    }
                                    case "CoreDEFENCE" -> {
                                        if (playerBoard.getGrid()[1][1].getSlot().getId() == tmpID || playerBoard.getGrid()[2][0].getSlot().getId() == tmpID || playerBoard.getGrid()[2][2].getSlot().getId() == tmpID) {
                                            checkResults++; // log the correct the go check others
                                        }
                                    }
                                    case "CORE" -> {
                                        if (playerBoard.getGrid()[2][1].getSlot().getId() == tmpID) {
                                            checkResults++;
                                        }
                                    }
                                    case "DEFENCE" -> {
                                        for (int p = 0; p < 3; p++) {
                                            if (playerBoard.getGrid()[3][p].getSlot().getId() == tmpID) {
                                                checkResults++; // log the correct the go check others
                                            }
                                        }
                                    }
//                                playerBoard.board_Grid[][].currentPlace();
                                }
                            }
                            //points just add to the card ig

                        }
                        //check all positions the check results
                        JSONObject getResult = tmpRecipe.getJSONObject("result");
                        int r = getResult.getInt("passNum");
                        if(checkResults == r){
                            int buildID = tmpRecipe.getInt("formID");
                            cardDatabase fd = new cardDatabase();
                            for(ComboBuild x: fd.formPack){
                                if(x.getId() == buildID){
                                    System.out.println(x.getItemName());
                                    return x;
                                }
                            }
                        }
                    }


                }

            }

        } catch (IOException ex){
            throw new IOException("Something Has Failed");

        } finally {
            System.out.println("The results are as seen above... tread lightly");
        }

        //return playerBoard.hasBuild();
        return currentBuild;
    }
}
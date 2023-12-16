package Game.Slots;

import Game.Card;

import java.util.Scanner;

import static Game.Slots.Board.Board_Positions.*;

public class Board {
    public enum Board_Positions { //implements boardPosition_Action
        UBER(3), ATTACK(2), CoreDEFENCE(3), CORE(1),DEFENCE(3);
        Board_Positions(int openSlots){}
    }

    private final Positions[][] board_Grid;
    public Board(){
        board_Grid = new Positions[4][3]; //4 rows 3 col
    }

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

}
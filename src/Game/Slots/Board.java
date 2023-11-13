package Game.Slots;

import Game.Card;

import java.util.Scanner;

import static Game.Slots.Board.Board_Positions.*;

public class Board {

    public interface boardPosition_Action {
        Board_Positions currentPlace();
        int[] getStats();
        int getAtk();
        int getDef();
        Card getSlot();
    }

    public enum Board_Positions { //implements boardPosition_Action
        UBER(3), ATTACK(2), CoreDEFENCE(3), CORE(1),DEFENCE(3);
        private int os;
        Board_Positions(int openSlots) {
            openSlots = os;
        }
        public int getOSNum(){
            return os;
        }
    }

    Positions[][] board_Grid;
    public Board(){
        board_Grid = new Positions[3][4];
    }

    public void addToSlots(String name, Card fromHand) {
        switch (name) {
            case "UBER" -> {
                for(int i = 0; i < UBER.getOSNum(); i++){ //for each uber position on the grid
                    if(board_Grid[i][0] == null){ //if there are less than uber open slot amount of uber positions
                        board_Grid[i][0] = new Positions(UBER, fromHand);
                        break;
                    }
                }
            }
            case "ATTACK" -> {
                if(board_Grid[0][1] == null ){
                    board_Grid[0][1] = new Positions(ATTACK, fromHand);
                }
                if(board_Grid[2][1] == null){
                    board_Grid[2][1] = new Positions(ATTACK, fromHand);
                }

            }
//            case "CoreDEFENCE" -> {Positions cd = new Positions(CoreDEFENCE, fromHand);}
//            case "CORE" -> CORE.getSlots().add(fromHand);
            case "DEFENCE" -> {
                for(int i = 0; i < DEFENCE.getOSNum(); i++){
                    if(board_Grid[i][4] == null){
                        board_Grid[i][4] = new Positions(DEFENCE, fromHand);
                        break;
                    }
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
            case "CORE" -> board_Grid[1][2].remove(); // only 1 is valid
            case "DEFENCE" -> board_Grid[posIndex][3].remove();
        }

    }

}
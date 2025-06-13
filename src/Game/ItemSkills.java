package Game;

import Game.Slots.Board;

import java.util.Random;
import java.util.Scanner;

import static Game.Slots.Board.Board_Positions.*;

public class ItemSkills {
    private Card otherCard;
    private boolean Used = false;
    Player boardCheck;
    public ItemSkills(Card currentItemCard, Card affectedCard, Player c){
        cardDatabase getData = new cardDatabase();
        otherCard = affectedCard;
        boardCheck = c;

        System.out.print(otherCard.getId());
        int itemID = currentItemCard.getId();

        for(Card i: getData.pack){
            if(itemID == i.getId() && !Used){
                //use the void corresponding to the item
                fishingRod(itemID);
                healingPotion(itemID);
                door(itemID);
            }
        }

    }
    public void isUsed(){ Used = true;}

    public void fishingRod(int checkID){
        if(checkID == 16){
            Random r = new Random();
            int coinFlip = r.nextInt(2); //head = 0 tails = 1
            if(coinFlip == 0){
                otherCard = null;
            }
        }
    }
    public void healingPotion(int checkID){
        if(checkID == 30){
            Scanner input = new Scanner(System.in);
            System.out.println("Which position is the card in?");
            String chooseType = input.nextLine();
            switch(chooseType){
                case "UBER" -> otherCard.getValue(UBER)[1] = otherCard.getValue(UBER)[1] + 3;

                case "ATTACK" -> otherCard.getValue(ATTACK)[1] = otherCard.getValue(ATTACK)[1] + 3;

                case "CoreDEFENCE" -> otherCard.getValue(CoreDEFENCE)[1] = otherCard.getValue(CoreDEFENCE)[1] + 3;

                case "CORE" -> otherCard.getValue(CORE)[1] = otherCard.getValue(CORE)[1] + 3;

                case "DEFENCE" -> otherCard.getValue(DEFENCE)[1] = otherCard.getValue(DEFENCE)[1] + 3;

            }

        }
    }

    public void door(int checkID){
        if(checkID == 12){
            boolean otherDoorExist = false;
            int[] uRow = boardCheck.getBoard().getUberRow();
            //check the row for another door card so this one can be used as an item
            for(int i = 0; i <= uRow.length; i++) {
                if(boardCheck.getGrid()[0][i].getSlot().getId() == checkID){
                    otherDoorExist = true;
                }
            }
            if (otherDoorExist) {
                Scanner input = new Scanner(System.in);
                System.out.println("Which position is the card in?");
                int choosePos = input.nextInt();
                switch (choosePos) {
                    case 0 -> otherCard.getValue(UBER)[0] = otherCard.getValue(UBER)[0] + 2;
                    case 1 -> otherCard.getValue(UBER)[1] = otherCard.getValue(UBER)[1] + 2;
                    case 2 -> otherCard.getValue(UBER)[2] = otherCard.getValue(UBER)[2] + 2;
                }
            }
        }
    }
}


package Game;

import java.util.Random;
import java.util.Scanner;

import static Game.Slots.Board.Board_Positions.*;

public class ItemSkills {
    private Card otherCard;
    private boolean Used = false;
    public ItemSkills(Card currentItemCard, Card affectedCard){
        cardDatabase getData = new cardDatabase();
        otherCard = affectedCard;

        System.out.print(otherCard.getId());
        int itemID = currentItemCard.getId();

        for(Card i: getData.pack){
            if(itemID == i.getId() && !Used){
                //use the void corresponding to the item
                fishingRod(itemID);
                healingPotion(itemID);
            }
        }

    }
    public void isUsed(){ Used = true;}

    public void fishingRod(int checkID){
        if(checkID == 16){
            Random r = new Random();
            int coinFlip = r.nextInt(0,1); //head = 0 tails = 1
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


}


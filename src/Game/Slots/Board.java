package Game.Slots;
import Game.Card;

import java.util.LinkedList;

import static Game.Slots.Board.positions.*;

public class Board {
    //private static final cardDatabase cd = new cardDatabase();
    public interface Position {
        //LinkedList<Card> slots = new LinkedList<>();
        boolean hasOpenSlots();
        int[] getStats(int cardIndex_onBoard);
        int getAtk(int cardIndex_onBoard);
        int getDef(int cardIndex_onBoard);
        LinkedList<Card> getSlots();
        String printSlots_ToBoard();
    }
    public enum positions implements Position{
        UBER{
            public final LinkedList<Card> slots = new LinkedList<>();
            final int openSlots = 3;
            public int[] getStats(int cardIndex_onBoard){
                return slots.get(cardIndex_onBoard).getValue(UBER);
            }
            public int getAtk(int cardIndex_onBoard){
                if(slots.get(cardIndex_onBoard) == null){
                    return 0;
                }else {
                    return slots.get(cardIndex_onBoard).getValue(UBER)[0];
                }
            }
            public int getDef(int cardIndex_onBoard){
                if(slots.get(cardIndex_onBoard) == null){
                    return 0;
                }else {
                    return slots.get(cardIndex_onBoard).getValue(UBER)[1];
                }
            }
            public boolean hasOpenSlots(){
                return slots.size() != openSlots;
            }
            public LinkedList<Card> getSlots(){ return slots;}
            public String printSlots_ToBoard(){return slots.toString();}
        },
        ATTACK{
            final LinkedList<Card> slots = new LinkedList<>();
            final int openSlots = 2;
            public boolean hasOpenSlots(){
                return slots.size() != openSlots;
            }
            public int[] getStats(int cardIndex_onBoard){
                return slots.get(cardIndex_onBoard).getValue(ATTACK);
            }
            public int getAtk(int cardIndex_onBoard){
                if(slots.get(cardIndex_onBoard) == null){
                    return 0;
                }else {
                    return slots.get(cardIndex_onBoard).getValue(ATTACK)[0];
                }
            }
            public int getDef(int cardIndex_onBoard){
                if(slots.get(cardIndex_onBoard) == null){
                    return 0;
                }else {
                    return slots.get(cardIndex_onBoard).getValue(ATTACK)[1];
                }
            }
            public LinkedList<Card> getSlots(){ return slots;}
            public String printSlots_ToBoard(){
                return slots.toString();
            }
        },
        CoreDEFENCE{
            final LinkedList<Card> slots = new LinkedList<>();
            final int openSlots = 3;
            public boolean hasOpenSlots(){
                return slots.size() != openSlots;
            }
            public int[] getStats(int cardIndex_onBoard){
                return slots.get(cardIndex_onBoard).getValue(CoreDEFENCE);
            }
            public int getAtk(int cardIndex_onBoard){
                if(slots.get(cardIndex_onBoard) == null){
                    return 0;
                }else {
                    return slots.get(cardIndex_onBoard).getValue(CoreDEFENCE)[0];
                }
            }
            public int getDef(int cardIndex_onBoard){
                if(slots.get(cardIndex_onBoard) == null){
                    return 0;
                }else {
                    return slots.get(cardIndex_onBoard).getValue(CoreDEFENCE)[1];
                }
            }
            public LinkedList<Card> getSlots(){ return slots;}
            public String printSlots_ToBoard(){
                return slots.toString();
            }
        },
        CORE{
            final LinkedList<Card> slots = new LinkedList<>();
            final int openSlots = 1;
            public boolean hasOpenSlots(){
                return slots.size() != openSlots;
            }
            public int[] getStats(int cardIndex_onBoard){
                return slots.get(cardIndex_onBoard).getValue(CORE);
            }
            public int getAtk(int cardIndex_onBoard){
                if(slots.get(cardIndex_onBoard) == null){
                    return 0;
                }else {
                    return slots.get(cardIndex_onBoard).getValue(CORE)[0];
                }
            }
            public int getDef(int cardIndex_onBoard){
                if(slots.get(cardIndex_onBoard) == null){
                    return 0;
                }else {
                    return slots.get(cardIndex_onBoard).getValue(CORE)[1];
                }
            }
            public LinkedList<Card> getSlots(){ return slots;}
            public String printSlots_ToBoard(){
                return slots.toString();
            }
        },
        DEFENCE{
            final LinkedList<Card> slots = new LinkedList<>();
            final int openSlots = 3;
            public boolean hasOpenSlots(){
                return slots.size() != openSlots;
            }
            public int[] getStats(int cardIndex_onBoard){
                return slots.get(cardIndex_onBoard).getValue(DEFENCE);
            }
            public int getAtk(int cardIndex_onBoard){
                if(slots.get(cardIndex_onBoard) == null){
                    return 0;
                }else {
                    return slots.get(cardIndex_onBoard).getValue(DEFENCE)[0];
                }
            }
            public int getDef(int cardIndex_onBoard){
                if(slots.get(cardIndex_onBoard) == null){
                    return 0;
                }else {
                    return slots.get(cardIndex_onBoard).getValue(DEFENCE)[1];
                }
            }
            public LinkedList<Card> getSlots(){ return slots;}
            public String printSlots_ToBoard(){
                return slots.toString();
            }
        }
    }

    public void addToSlots(String name, Card fromHand){
        switch(name){
            case "UBER" -> UBER.getSlots().add(fromHand);
            case "ATTACK" -> ATTACK.getSlots().add(fromHand);
            case "CoreDEFENCE" -> CoreDEFENCE.getSlots().add(fromHand);
            case "CORE" -> CORE.getSlots().add(fromHand);
            case "DEFENCE" -> DEFENCE.getSlots().add(fromHand);
        }
    }
    public void removeFromSlots(String name, int posIndex){
        switch(name){
            case "UBER" -> UBER.getSlots().remove(posIndex);
            case "ATTACK" -> ATTACK.getSlots().remove(posIndex);
            case "CoreDEFENCE" -> CoreDEFENCE.getSlots().remove(posIndex);
            case "CORE" -> CORE.getSlots().remove(posIndex);
            case "DEFENCE" -> DEFENCE.getSlots().remove(posIndex);
        }

    }
    public void printBoard(){
        System.out.print(UBER.printSlots_ToBoard());
        System.out.print(ATTACK.printSlots_ToBoard());
        System.out.print(CoreDEFENCE.printSlots_ToBoard());
        System.out.print(CORE.printSlots_ToBoard());
        System.out.println(DEFENCE.printSlots_ToBoard());
    }

}
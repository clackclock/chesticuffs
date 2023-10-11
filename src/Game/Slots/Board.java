package Game.Slots;
import Game.Card;
import Game.cardDatabase;

import java.util.LinkedList;

import static Game.Slots.Board.position.*;

public class Board {
    private static cardDatabase cd = new cardDatabase();
    public interface Position{
        LinkedList<Card> slots = new LinkedList<>();
        boolean hasOpenSlots();
        int[] getStats(int cardIndex_onBoard);
        int getAtk(int cardIndex_onBoard);
        int getDef(int cardIndex_onBoard);
        LinkedList<Card> getSlots();
    }
    public enum position implements Position{
        UBER{
            public final LinkedList<Card> slots = new LinkedList<>();
            final int openSlots = 3;
            public int[] getStats(int cardIndex_onBoard){
                return slots.get(cardIndex_onBoard).getUber();
            }
            public int getAtk(int cardIndex_onBoard){
                if(slots.get(cardIndex_onBoard) == null){
                    return 0;
                }else {
                    return slots.get(cardIndex_onBoard).getUberAtk();
                }
            }
            public int getDef(int cardIndex_onBoard){
                if(slots.get(cardIndex_onBoard) == null){
                    return 0;
                }else {
                    return slots.get(cardIndex_onBoard).getUberDef();
                }
            }
            public boolean hasOpenSlots(){
                return slots.size() != openSlots;
            }
            public LinkedList<Card> getSlots(){
                return slots;
            }
        },
        ATTACK{
            final LinkedList<Card> slots = new LinkedList<>();
            final int openSlots = 2;
            public boolean hasOpenSlots(){
                return slots.size() != openSlots;
            }
            public int[] getStats(int cardIndex_onBoard){
                return slots.get(cardIndex_onBoard).getAtk();
            }
            public int getAtk(int cardIndex_onBoard){
                if(slots.get(cardIndex_onBoard) == null){
                    return 0;
                }else {
                    return slots.get(cardIndex_onBoard).getAtkAtk();
                }
            }
            public int getDef(int cardIndex_onBoard){
                if(slots.get(cardIndex_onBoard) == null){
                    return 0;
                }else {
                    return slots.get(cardIndex_onBoard).getAtkDef();
                }
            }
            public LinkedList<Card> getSlots(){
                return slots;
            }
        },
        CoreDEFENCE{
            final LinkedList<Card> slots = new LinkedList<>();
            final int openSlots = 3;
            public boolean hasOpenSlots(){
                return slots.size() != openSlots;
            }
            public int[] getStats(int cardIndex_onBoard){
                return slots.get(cardIndex_onBoard).getCDef();
            }
            public int getAtk(int cardIndex_onBoard){
                if(slots.get(cardIndex_onBoard) == null){
                    return 0;
                }else {
                    return slots.get(cardIndex_onBoard).getCDefAtk();
                }
            }
            public int getDef(int cardIndex_onBoard){
                if(slots.get(cardIndex_onBoard) == null){
                    return 0;
                }else {
                    return slots.get(cardIndex_onBoard).getCDefDef();
                }
            }
            public LinkedList<Card> getSlots(){
                return slots;
            }
        },
        CORE{
            final LinkedList<Card> slots = new LinkedList<>();
            final int openSlots = 1;
            public boolean hasOpenSlots(){
                return slots.size() != openSlots;
            }
            public int[] getStats(int cardIndex_onBoard){
                return slots.get(cardIndex_onBoard).getCore();
            }
            public int getAtk(int cardIndex_onBoard){
                if(slots.get(cardIndex_onBoard) == null){
                    return 0;
                }else {
                    return slots.get(cardIndex_onBoard).getCoreAtk();
                }
            }
            public int getDef(int cardIndex_onBoard){
                if(slots.get(cardIndex_onBoard) == null){
                    return 0;
                }else {
                    return slots.get(cardIndex_onBoard).getCoreDef();
                }
            }
            public LinkedList<Card> getSlots(){
                return slots;
            }
        },
        DEFENCE{
            final LinkedList<Card> slots = new LinkedList<>();
            final int openSlots = 3;
            public boolean hasOpenSlots(){
                return slots.size() != openSlots;
            }
            public int[] getStats(int cardIndex_onBoard){
                return slots.get(cardIndex_onBoard).getDef();
            }
            public int getAtk(int cardIndex_onBoard){
                if(slots.get(cardIndex_onBoard) == null){
                    return 0;
                }else {
                    return slots.get(cardIndex_onBoard).getDefAtk();
                }
            }
            public int getDef(int cardIndex_onBoard){
                if(slots.get(cardIndex_onBoard) == null){
                    return 0;
                }else {
                    return slots.get(cardIndex_onBoard).getDefDef();
                }
            }
            public LinkedList<Card> getSlots(){
                return slots;
            }
        };
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
        UBER.getSlots();
        ATTACK.getSlots();
        CoreDEFENCE.getSlots();
        CORE.getSlots();
        DEFENCE.getSlots();
    }

}
package Game.Slots;

import java.io.FileReader;
import java.io.IOException;

import Game.*;
import org.json.JSONArray;
import org.json.JSONObject;

import Game.Player;
import static Game.Slots.Board.Board_Positions.*;

public class Calculation {

    //numbers for final use
    private int uTotalATK, aTotalATK, cdTotalATK, dTotalATK; // only need one set bc it'll switch outside then start this again
    private int uTotalDEF, aTotalDEF, cdTotalDEF, dTotalDEF;

    public Calculation(Player first, Player second) throws IOException {
        Board one = first.getBoard();
        Board two = second.getBoard();

        //get the core stats for later
        int coreATKMod1, coreDEFMod2;
        if(one.getGrid()[2][1] == null){
            coreATKMod1 = 0;
        }else { coreATKMod1 = one.getGrid()[2][1].getAtk(); }

        if(two.getGrid()[2][1] == null) {
            coreDEFMod2 = 0;
        } else{ coreDEFMod2 = two.getGrid()[2][1].getDef(); }

        int overflow;

        //fileReader to get the file then a while loop to build a string from it
        try(FileReader modReader = new FileReader("C:\\Users\\sensa\\IdeaProjects\\testGame\\src\\Game\\CardData\\Modifier.json")) {
            StringBuilder modString = new StringBuilder(" ");
            int i;
            while ((i = modReader.read()) != -1) {
                //System.out.print((char)i);
                modString.append((char) i);
            }
            JSONObject jsonMODObject = new JSONObject(modString.toString());
            JSONArray skillList = (JSONArray) jsonMODObject.get("skills");

            //go through skillList, if the id matches card on board get mod numbers according to things (card type, whatever)
            boolean done = false;
            Board current, other;
            current = one;
            other = two;
            int l = 0;
            while (!done) {
                for (int j = 0; j < skillList.length(); j++) {
                    JSONObject tmp = skillList.getJSONObject(j);
                    int tmpI = tmp.getInt("cardID");
                    for (int k = 0; k < current.getGrid().length; k++) {
                        if(current.isBoardEmpty()) {
                            if (tmpI == current.getGrid()[j][k].getSlot().getId() && !current.getGrid()[j][k].getSlot().isItem()) {
                                //check the skill type
                                checkSkillsATK(tmp, tmp.getString("skillType"), current); // attacker then defence
                            }
                        }
                    }
                    for (int k = 0; k < other.getGrid().length; k++) {
                        if(other.isBoardEmpty()) {
                            if (tmpI == other.getGrid()[j][k].getSlot().getId() && !other.getGrid()[j][k].getSlot().isItem()) {
                                //check the skill type
                                checkSkillsDEF(tmp, tmp.getString("skillType"), other);
                            }
                        }
                    }

                }
                current = switchBoard(current, one, two);
                if (current == one) {
                    other = two;
                } else if (current == two) {
                    other = one;
                }

                if (current == two && l > 0) {
                    done = true;
                }
                l++;
            }
        } catch (IOException ex){
            throw new IOException("Something Has Failed");

        } finally {
            System.out.println("Everything went perfectly... be very afraid");
        }
        //overflow calculations + core additions
        overflow = uTotalDEF - uTotalATK; // uber gets no overflow
        if(overflow > 0){
            uTotalATK = uTotalATK + overflow + coreATKMod1;
        }else{ uTotalATK = uTotalATK + coreATKMod1;}

        overflow = aTotalDEF - aTotalATK;
        if(overflow > 0){
            cdTotalATK = cdTotalATK + overflow + coreATKMod1;
        }else { cdTotalATK = cdTotalATK + coreATKMod1;}

        overflow = cdTotalDEF - cdTotalATK;
        if(overflow > 0){
            dTotalATK = dTotalATK + overflow + coreATKMod1;
        }else{dTotalATK = dTotalATK + coreATKMod1;}

        //defence core additions
        uTotalDEF = uTotalDEF + coreDEFMod2;
        aTotalDEF = aTotalDEF + coreDEFMod2;
        cdTotalDEF = cdTotalDEF + coreDEFMod2;
        dTotalDEF = dTotalDEF + coreDEFMod2;
    }

    public boolean calculate(){ //if true game ends if false game continues
        return uTotalATK > uTotalDEF && aTotalATK > aTotalDEF && cdTotalATK > cdTotalDEF && dTotalATK > dTotalDEF;
    }

    private static Board switchBoard(Board current, Board one, Board two) {
        if (current == one) {
            current = two;
            System.out.println("Player two");
            return current;
        }
        if (current == two) {
            current = one;
            System.out.println("Player one");
            return current;

        }
        return current;
    }
    private void checkSkillsATK(JSONObject o,String skillTypeBase, Board attacker){
        switch(skillTypeBase){
            case "type" -> {
                String typeC = o.getString("typeCheck");
                for(int n = 0; n < 4; n++) {
                    for (int i = 0; i < 3; i++) {
                        if(attacker.getGrid()[n][i].isEmpty()) {
                            if (attacker.getGrid()[0][i].getSlot().activeTypeOne().equals(typeC) && attacker.getGrid()[0][i].currentPlace() == UBER || attacker.getGrid()[0][i].getSlot().activeTypeTwo().equals(typeC) && attacker.getGrid()[0][i].currentPlace() == UBER) {
                                int tno = o.getInt("modValueAtk");
                                uTotalATK = uTotalATK + tno;
                            }
                            if (attacker.getGrid()[1][i].getSlot().activeTypeOne().equals(typeC) && attacker.getGrid()[1][i].currentPlace() == ATTACK || attacker.getGrid()[1][i].getSlot().activeTypeTwo().equals(typeC) && attacker.getGrid()[1][i].currentPlace() == ATTACK) {
                                int tno = o.getInt("modValueAtk");
                                aTotalATK = aTotalATK + tno;
                            }
                            if (attacker.getGrid()[n][i].getSlot().activeTypeOne().equals(typeC) && attacker.getGrid()[n][i].currentPlace() == CoreDEFENCE || attacker.getGrid()[n][i].getSlot().activeTypeTwo().equals(typeC) && attacker.getGrid()[n][i].currentPlace() == CoreDEFENCE) {
                                int tno = o.getInt("modValueAtk");
                                cdTotalATK = cdTotalATK + tno;
                            }
                            if (attacker.getGrid()[3][i].getSlot().activeTypeOne().equals(typeC) && attacker.getGrid()[3][i].currentPlace() == DEFENCE || attacker.getGrid()[3][i].getSlot().activeTypeTwo().equals(typeC) && attacker.getGrid()[3][i].currentPlace() == DEFENCE) {
                                int tno = o.getInt("modValueAtk");
                                dTotalATK = dTotalATK + tno;
                            }
                        }
                    }
                }
            } //get the typeCheck then go through current board. for each card w/ type add mod, return total
            case "type-position" -> {
                String typeC = o.getString("typeCheck");
                String posC = o.getString("posCheck");
                for(int n = 0; n < 4; n++) {
                    for (int i = 0; i < 3; i++) {
                        if(attacker.getGrid()[n][i].isEmpty()) {
                            if (attacker.getGrid()[0][i].getSlot().activeTypeOne().equals(typeC) && String.valueOf(attacker.getGrid()[0][i].currentPlace()).contentEquals(posC) && attacker.getGrid()[0][i].currentPlace() == UBER || attacker.getGrid()[0][i].getSlot().activeTypeTwo().equals(typeC) && String.valueOf(attacker.getGrid()[0][i].currentPlace()).contentEquals(posC) && attacker.getGrid()[0][i].currentPlace() == UBER) {
                                int tno = o.getInt("modValueAtk");
                                uTotalATK = uTotalATK + tno;
                            }
                            if (attacker.getGrid()[1][i].getSlot().activeTypeOne().equals(typeC) && String.valueOf(attacker.getGrid()[1][i].currentPlace()).contentEquals(posC) && attacker.getGrid()[1][i].currentPlace() == ATTACK || attacker.getGrid()[1][i].getSlot().activeTypeTwo().equals(typeC) && String.valueOf(attacker.getGrid()[1][i].currentPlace()).contentEquals(posC) && attacker.getGrid()[1][i].currentPlace() == ATTACK) {
                                int tno = o.getInt("modValueAtk");
                                aTotalATK = aTotalATK + tno;
                            }
                            if (attacker.getGrid()[n][i].getSlot().activeTypeOne().equals(typeC) && String.valueOf(attacker.getGrid()[n][i].currentPlace()).contentEquals(posC) && attacker.getGrid()[n][i].currentPlace() == CoreDEFENCE || attacker.getGrid()[n][i].getSlot().activeTypeTwo().equals(typeC) && String.valueOf(attacker.getGrid()[n][i].currentPlace()).contentEquals(posC) && attacker.getGrid()[n][i].currentPlace() == CoreDEFENCE) {
                                int tno = o.getInt("modValueAtk");
                                cdTotalATK = cdTotalATK + tno;
                            }
                            if (attacker.getGrid()[3][i].getSlot().activeTypeOne().equals(typeC) && String.valueOf(attacker.getGrid()[3][i].currentPlace()).contentEquals(posC) && attacker.getGrid()[3][i].currentPlace() == DEFENCE || attacker.getGrid()[3][i].getSlot().activeTypeTwo().equals(typeC) && String.valueOf(attacker.getGrid()[3][i].currentPlace()).contentEquals(posC) && attacker.getGrid()[3][i].currentPlace() == DEFENCE) {
                                int tno = o.getInt("modValueAtk");
                                dTotalATK = dTotalATK + tno;
                            }
                        }
                    }
                }
            } //get typeCheck and posCheck, go through current board, return total
            case "card" -> {
                int cID = o.getInt("cardID");
                for(int i = 0; i < 4; i++){
                    for(int j = 0; j < 3; j++){
                        if(attacker.getGrid()[i][j].isEmpty()) {
                            if (attacker.getGrid()[i][j].getSlot().getId() == cID && attacker.getGrid()[i][j].currentPlace() == UBER) {
                                int tno = o.getInt("modValueAtk");
                                uTotalATK = uTotalATK + tno;
                            }
                            if (attacker.getGrid()[i][j].getSlot().getId() == cID && attacker.getGrid()[i][j].currentPlace() == ATTACK) {
                                int tno = o.getInt("modValueAtk");
                                aTotalATK = aTotalATK + tno;
                            }
                            if (attacker.getGrid()[i][j].getSlot().getId() == cID && attacker.getGrid()[i][j].currentPlace() == CoreDEFENCE) {
                                int tno = o.getInt("modValueAtk");
                                cdTotalATK = cdTotalATK + tno;
                            }
                            if (attacker.getGrid()[i][j].getSlot().getId() == cID && attacker.getGrid()[i][j].currentPlace() == DEFENCE) {
                                int tno = o.getInt("modValueAtk");
                                dTotalATK = dTotalATK + tno;
                            }
                        }
                    }
                }
            }//get cardIDCheck, if card there add mod
        }
    }
    private void checkSkillsDEF(JSONObject o,String skillTypeBase, Board defender){
        switch(skillTypeBase){
            case "type" -> {
                String typeC = o.getString("typeCheck");
                for(int n = 0; n < 4; n++) {
                    for (int i = 0; i < 3; i++) {
                        if(defender.getGrid()[n][i].isEmpty()) {
                            if (defender.getGrid()[0][i].getSlot().activeTypeOne().equals(typeC) && defender.getGrid()[0][i].currentPlace() == UBER || defender.getGrid()[0][i].getSlot().activeTypeTwo().equals(typeC) && defender.getGrid()[0][i].currentPlace() == UBER) {
                                int tno = o.getInt("modValueDef");
                                uTotalDEF = uTotalDEF + tno;
                            }
                            if (defender.getGrid()[1][i].getSlot().activeTypeOne().equals(typeC) && defender.getGrid()[1][i].currentPlace() == ATTACK || defender.getGrid()[1][i].getSlot().activeTypeTwo().equals(typeC) && defender.getGrid()[1][i].currentPlace() == ATTACK) {
                                int tno = o.getInt("modValueDef");
                                aTotalDEF = aTotalDEF + tno;
                            }
                            if (defender.getGrid()[n][i].getSlot().activeTypeOne().equals(typeC) && defender.getGrid()[n][i].currentPlace() == CoreDEFENCE || defender.getGrid()[n][i].getSlot().activeTypeTwo().equals(typeC) && defender.getGrid()[n][i].currentPlace() == CoreDEFENCE) {
                                int tno = o.getInt("modValueDef");
                                cdTotalDEF = cdTotalDEF + tno;
                            }
                            if (defender.getGrid()[3][i].getSlot().activeTypeOne().equals(typeC) && defender.getGrid()[3][i].currentPlace() == DEFENCE || defender.getGrid()[3][i].getSlot().activeTypeTwo().equals(typeC) && defender.getGrid()[3][i].currentPlace() == DEFENCE) {
                                int tno = o.getInt("modValueDef");
                                dTotalDEF = dTotalDEF + tno;
                            }
                        }
                    }
                }
            } //get the typeCheck then go through current board. for each card w/ type add mod, return total
            case "type-position" -> {
                String typeC = o.getString("typeCheck");
                String posC = o.getString("posCheck");
                for(int n = 0; n < 4; n++) {
                    for (int i = 0; i < 3; i++) {
                        if(defender.getGrid()[n][i].isEmpty()) {
                            if (defender.getGrid()[0][i].getSlot().activeTypeOne().equals(typeC) && String.valueOf(defender.getGrid()[0][i].currentPlace()).contentEquals(posC) && defender.getGrid()[0][i].currentPlace() == UBER || defender.getGrid()[0][i].getSlot().activeTypeTwo().equals(typeC) && String.valueOf(defender.getGrid()[0][i].currentPlace()).contentEquals(posC) && defender.getGrid()[0][i].currentPlace() == UBER) {
                                int tno = o.getInt("modValueDef");
                                uTotalDEF = uTotalDEF + tno;
                            }
                            if (defender.getGrid()[1][i].getSlot().activeTypeOne().equals(typeC) && String.valueOf(defender.getGrid()[1][i].currentPlace()).contentEquals(posC) && defender.getGrid()[1][i].currentPlace() == ATTACK || defender.getGrid()[1][i].getSlot().activeTypeTwo().equals(typeC) && String.valueOf(defender.getGrid()[1][i].currentPlace()).contentEquals(posC) && defender.getGrid()[1][i].currentPlace() == ATTACK) {
                                int tno = o.getInt("modValueDef");
                                aTotalDEF = aTotalDEF + tno;
                            }
                            if (defender.getGrid()[n][i].getSlot().activeTypeOne().equals(typeC) && String.valueOf(defender.getGrid()[n][i].currentPlace()).contentEquals(posC) && defender.getGrid()[n][i].currentPlace() == CoreDEFENCE || defender.getGrid()[n][i].getSlot().activeTypeTwo().equals(typeC) && String.valueOf(defender.getGrid()[n][i].currentPlace()).contentEquals(posC) && defender.getGrid()[n][i].currentPlace() == CoreDEFENCE) {
                                int tno = o.getInt("modValueDef");
                                cdTotalDEF = cdTotalDEF + tno;
                            }
                            if (defender.getGrid()[3][i].getSlot().activeTypeOne().equals(typeC) && String.valueOf(defender.getGrid()[3][i].currentPlace()).contentEquals(posC) && defender.getGrid()[3][i].currentPlace() == DEFENCE || defender.getGrid()[3][i].getSlot().activeTypeTwo().equals(typeC) && String.valueOf(defender.getGrid()[3][i].currentPlace()).contentEquals(posC) && defender.getGrid()[3][i].currentPlace() == DEFENCE) {
                                int tno = o.getInt("modValueDef");
                                dTotalDEF = dTotalDEF + tno;
                            }
                        }
                    }
                }
            } //get typeCheck and posCheck, go through current board, return total
            case "card" -> {
                int cID = o.getInt("cardID");
                for(int i = 0; i < 4; i++){
                    for(int j = 0; j < 3; j++){
                        if(defender.getGrid()[i][j].isEmpty()) {
                            if (defender.getGrid()[i][j].getSlot().getId() == cID && defender.getGrid()[i][j].currentPlace() == UBER) {
                                int tno = o.getInt("modValueDef");
                                uTotalDEF = uTotalDEF + tno;
                            }
                            if (defender.getGrid()[i][j].getSlot().getId() == cID && defender.getGrid()[i][j].currentPlace() == ATTACK) {
                                int tno = o.getInt("modValueDef");
                                aTotalDEF = aTotalDEF + tno;
                            }
                            if (defender.getGrid()[i][j].getSlot().getId() == cID && defender.getGrid()[i][j].currentPlace() == CoreDEFENCE) {
                                int tno = o.getInt("modValueDef");
                                cdTotalDEF = cdTotalDEF + tno;
                            }
                            if (defender.getGrid()[i][j].getSlot().getId() == cID && defender.getGrid()[i][j].currentPlace() == DEFENCE) {
                                int tno = o.getInt("modValueDef");
                                dTotalDEF = dTotalDEF + tno;
                            }
                        }
                    }
                }
            }//get cardIDCheck, if card there add mod
        }
    }

    public static void main(String[] args) throws IOException {
        Player b = new Human();
        Player bb = new Human();

        Calculation c = new Calculation(b, bb);
        c.calculate();
    }
}

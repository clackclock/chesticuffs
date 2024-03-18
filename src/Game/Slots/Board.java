package Game.Slots;

import Game.Card;
import Game.Player;
import Game.cardDatabase;
import Game.ComboBuild;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import static Game.Slots.Board.Board_Positions.*;

public class Board {
    public enum Board_Positions { //implements boardPosition_Action
        //3,2,3,1,3
        UBER(), ATTACK(), CoreDEFENCE(), CORE(),DEFENCE()
//        Board_Positions(int openSlots){}
    }

    private final Positions[][] board_Grid;
    private final int[] uberRow, atkRow, cDefRow, coreBlock, defRow;
    private ComboBuild currentBuild;
    private boolean isThereBuild = false;
    public Board(){
        board_Grid = new Positions[4][3]; //4 rows 3 col

        uberRow = new int[2];
        atkRow = new int[2];
        cDefRow = new int[2];
        coreBlock = new int[2];
        defRow = new int[2];
    }

    public int[] getUberRow(){ return uberRow; }
    public int[] getAtkRow(){ return atkRow; }
    public int[] getCDefRow(){ return cDefRow; }
    public int[] getCoreBlock(){ return coreBlock; }
    public int[] getDefRow(){return defRow; }

    public void calculate(Board enemy) throws IOException{
        for(int f = 0; f < 2; f++){
            for(int r = 0; r < 3; r++){
                if(board_Grid[0][r] != null){
                    if(f == 0) {
                        uberRow[f] = uberRow[f] + board_Grid[0][r].getAtk();
                    } else{
                        uberRow[f] = uberRow[f] + board_Grid[0][r].getDef();
                    }
                }
                if(enemy.getGrid()[0][r] != null){
                    if(f == 0) {
                        enemy.getUberRow()[f] = enemy.getUberRow()[f] + enemy.getGrid()[0][r].getAtk();
                    } else{
                        enemy.getUberRow()[f] = enemy.getUberRow()[f] + enemy.getGrid()[0][r].getDef();
                    }
                }

                if(board_Grid[3][r] != null){
                    if(f == 0) {
                        defRow[f] = defRow[f] + board_Grid[3][r].getAtk();
                    } else{
                        defRow[f] = defRow[f] + board_Grid[3][r].getDef();
                    }
                }
                if(enemy.getGrid()[3][r] != null){
                    if(f == 0) {
                        enemy.getDefRow()[f] = enemy.getDefRow()[f] + enemy.getGrid()[3][r].getAtk();
                    } else{
                        enemy.getDefRow()[f] = enemy.getDefRow()[f] + enemy.getGrid()[3][r].getDef();
                    }
                }

            }
        }
        if(board_Grid[1][0] != null){
            atkRow[0] = atkRow[0] + board_Grid[1][0].getAtk();
            atkRow[1] = atkRow[1] +board_Grid[1][0].getDef();
        }
        if(board_Grid[1][2] != null){
            atkRow[0] = atkRow[0] + board_Grid[1][2].getAtk();
            atkRow[1] = atkRow[1] + board_Grid[1][2].getDef();
        }
        if(enemy.getGrid()[1][0] != null){
            enemy.getAtkRow()[0] = enemy.getGrid()[1][0].getAtk() + enemy.getAtkRow()[0];
            enemy.getAtkRow()[1] = enemy.getGrid()[1][0].getDef() + enemy.getAtkRow()[1];
        }
        if(enemy.getGrid()[1][2] != null){
            enemy.getAtkRow()[0] = enemy.getAtkRow()[0] + enemy.getGrid()[1][2].getAtk();
            enemy.getAtkRow()[1] = enemy.getAtkRow()[1] + enemy.getGrid()[1][2].getDef();
        }

        if(board_Grid[1][1] != null) {
            cDefRow[0] = board_Grid[1][1].getAtk() + cDefRow[0];
            cDefRow[1] = board_Grid[1][1].getDef() + cDefRow[1];
        }
        if(board_Grid[2][0] != null){
            cDefRow[0] = board_Grid[2][0].getAtk() + cDefRow[0];
            cDefRow[1] = board_Grid[2][0].getDef() + cDefRow[1];
        }
        if(board_Grid[2][2] != null){
            cDefRow[0] = cDefRow[0] + board_Grid[2][2].getAtk();
            cDefRow[1] = cDefRow[1] + board_Grid[2][2].getDef();
        }
        if(enemy.getGrid()[1][1] != null) {
            enemy.getCDefRow()[0] = enemy.getGrid()[1][1].getAtk() + enemy.getCDefRow()[0];
            enemy.getCDefRow()[1] = enemy.getGrid()[1][1].getDef() + enemy.getCDefRow()[1];
        }
        if(enemy.getGrid()[2][0] != null){
            enemy.getCDefRow()[0] = enemy.getGrid()[2][0].getAtk() + enemy.getCDefRow()[0];
            enemy.getCDefRow()[1] = enemy.getGrid()[2][0].getDef() + enemy.getCDefRow()[1];
        }
        if(enemy.getGrid()[2][2] != null){
            enemy.getCDefRow()[0] = enemy.getCDefRow()[0] + enemy.getGrid()[2][2].getAtk();
            enemy.getCDefRow()[1] = enemy.getCDefRow()[1] + enemy.getGrid()[2][2].getDef();
        }

        if(board_Grid[2][1] != null){
            coreBlock[0] = board_Grid[2][1].getAtk();
            coreBlock[1] = board_Grid[2][1].getDef();
        }
        if(enemy.getGrid()[2][1] != null) {
            enemy.getCoreBlock()[0] = enemy.getGrid()[2][1].getAtk();
            enemy.getCoreBlock()[1] = enemy.getGrid()[2][1].getDef();
        }

        try(FileReader modReader = new FileReader("C:\\Users\\sensa\\IdeaProjects\\testGame\\src\\Game\\CardData\\Modifier.json")) {
            StringBuilder modString = new StringBuilder(" ");
            int i;
            while ((i = modReader.read()) != -1) {
                //System.out.print((char)i);
                modString.append((char) i);
            }
            JSONObject jsonMODObject = new JSONObject(modString.toString());
            JSONObject dList = jsonMODObject.getJSONObject("deck");
            JSONArray skillList = (JSONArray) dList.get("cards");

            for (int j = 0; j < skillList.length(); j++) {
                JSONObject cList = skillList.getJSONObject(j);

                int cID = cList.getInt("cardID");
                String typeBase = cList.getString("skillType");
                //if cID == card in row add mod value

                switch (typeBase) {
                    case "type" -> {
                        int mAtk = cList.getInt("modValueAtk");
                        int mDef = cList.getInt("modValueDef");

                        String tC = cList.getString("typeCheck");
                        int tik = 0;

                        for(int l = 0; l < 4; l++){
                            for (int o = 0; o < 3; o++) {
                                if(board_Grid[l][o] != null) {
                                    if (board_Grid[l][o].getSlot().activeTypeOne().equals(tC) || board_Grid[j][l].getSlot().activeTypeTwo().equals(tC)) {
                                        tik++;
                                    }
                                }
                            }
                        }
                        for (int k = 0; k < 3; k++) {
                            assert board_Grid[0][k] != null;
                            if (cID == board_Grid[0][k].getSlot().getId()) {
                                uberRow[0] = uberRow[0] + mAtk;
                                uberRow[1] = uberRow[1] + mDef;
                            }
                            if (cID == board_Grid[1][0].getSlot().getId() || cID == board_Grid[1][2].getSlot().getId()) {
                                atkRow[0] = atkRow[0] + mAtk;
                                atkRow[1] = atkRow[1] + mDef;
                            }
                            if (cID == board_Grid[1][1].getSlot().getId() || cID == board_Grid[2][0].getSlot().getId() || cID == board_Grid[2][2].getSlot().getId()) {
                                cDefRow[0] = cDefRow[0] + mAtk;
                                cDefRow[1] = cDefRow[1] + mDef;
                            }
                            assert board_Grid[3][k] != null;
                            if (cID == board_Grid[3][k].getSlot().getId()) {
                                defRow[0] = defRow[0] + mAtk;
                                defRow[1] = defRow[1] + mDef;
                            }

                        }

                        tik = 0;
                        for(int l = 0; l < 4; l++){
                            for (int o = 0; o < 3; o++) {
                                if (enemy.getGrid()[l][o].getSlot().activeTypeOne().equals(tC) || enemy.getGrid()[j][l].getSlot().activeTypeTwo().equals(tC)) {
                                    tik++;
                                }
                            }
                        }
                        for (int k = 0; k < tik; k++) {
                            if (cID == enemy.getGrid()[0][k].getSlot().getId()) {
                                enemy.getUberRow()[0] = enemy.getUberRow()[0] + mAtk;
                                enemy.getUberRow()[1] = enemy.getUberRow()[1] + mDef;
                            }
                            if (cID == enemy.getGrid()[1][0].getSlot().getId() || cID == enemy.getGrid()[1][2].getSlot().getId()) {
                                enemy.getAtkRow()[0] = enemy.getAtkRow()[0] + mAtk;
                                enemy.getAtkRow()[1] = enemy.getAtkRow()[1] + mDef;
                            }
                            if (cID == enemy.getGrid()[1][1].getSlot().getId() || cID == enemy.getGrid()[2][0].getSlot().getId() || cID == enemy.getGrid()[2][2].getSlot().getId()) {
                                enemy.getCDefRow()[0] = enemy.getCDefRow()[0] + mAtk;
                                enemy.getCDefRow()[1] = enemy.getCDefRow()[1] + mDef;
                            }
                            if (cID == enemy.getGrid()[3][k].getSlot().getId()) {
                                enemy.getDefRow()[0] = enemy.getDefRow()[0] + mAtk;
                                enemy.getDefRow()[1] = enemy.getDefRow()[1] + mDef;
                            }
                        }

                    }
                    case "position" -> {
                        int mAtk = cList.getInt("modValueAtk");
                        int mDef = cList.getInt("modValueDef");

                        String pC = cList.getString("posCheck");

                        for (int k = 0; k < 3; k++) {
                            if (cID == board_Grid[0][k].getSlot().getId() && String.valueOf(board_Grid[0][k].currentPlace()).contentEquals(pC)) {
                                uberRow[0] = uberRow[0] + mAtk;
                                uberRow[1] = uberRow[1] + mDef;
                            }
                            if (cID == board_Grid[1][0].getSlot().getId() && String.valueOf(board_Grid[1][0].currentPlace()).contentEquals(pC)|| cID == board_Grid[1][2].getSlot().getId() && String.valueOf(board_Grid[1][2].currentPlace()).contentEquals(pC)) {
                                atkRow[0] = atkRow[0] + mAtk;
                                atkRow[1] = atkRow[1] + mDef;
                            }
                            if (cID == board_Grid[1][1].getSlot().getId() && String.valueOf(board_Grid[1][1].currentPlace()).contentEquals(pC) || cID == board_Grid[2][0].getSlot().getId() && String.valueOf(board_Grid[2][0].currentPlace()).contentEquals(pC)|| cID == board_Grid[2][2].getSlot().getId() && String.valueOf(board_Grid[2][2].currentPlace()).contentEquals(pC)) {
                                cDefRow[0] = cDefRow[0] + mAtk;
                                cDefRow[1] = cDefRow[1] + mDef;
                            }
                            if (cID == board_Grid[3][k].getSlot().getId() && String.valueOf(board_Grid[3][k].currentPlace()).contentEquals(pC)) {
                                defRow[0] = defRow[0] + mAtk;
                                defRow[1] = defRow[1] + mDef;
                            }

                        }

                        for (int k = 0; k < 3; k++) {
                            if (cID == enemy.getGrid()[0][k].getSlot().getId() && String.valueOf(enemy.getGrid()[0][k].currentPlace()).contentEquals(pC)) {
                                enemy.getUberRow()[0] = enemy.getUberRow()[0] + mAtk;
                                enemy.getUberRow()[1] = enemy.getUberRow()[1] + mDef;
                            }
                            if (cID == enemy.getGrid()[1][0].getSlot().getId() && String.valueOf(enemy.getGrid()[1][0].currentPlace()).contentEquals(pC) || cID == enemy.getGrid()[1][2].getSlot().getId() && String.valueOf(enemy.getGrid()[1][2].currentPlace()).contentEquals(pC)) {
                                enemy.getAtkRow()[0] = enemy.getAtkRow()[0] + mAtk;
                                enemy.getAtkRow()[1] = enemy.getAtkRow()[1] + mDef;
                            }
                            if (cID == enemy.getGrid()[1][1].getSlot().getId() && String.valueOf(enemy.getGrid()[1][1].currentPlace()).contentEquals(pC) || cID == enemy.getGrid()[2][0].getSlot().getId() && String.valueOf(enemy.getGrid()[2][0].currentPlace()).contentEquals(pC) || cID == enemy.getGrid()[2][2].getSlot().getId() && String.valueOf(enemy.getGrid()[2][2].currentPlace()).contentEquals(pC)) {
                                enemy.getCDefRow()[0] = enemy.getCDefRow()[0] + mAtk;
                                enemy.getCDefRow()[1] = enemy.getCDefRow()[1] + mDef;
                            }
                            if (cID == enemy.getGrid()[3][k].getSlot().getId() && String.valueOf(enemy.getGrid()[3][k].currentPlace()).contentEquals(pC)) {
                                enemy.getDefRow()[0] = enemy.getDefRow()[0] + mAtk;
                                enemy.getDefRow()[1] = enemy.getDefRow()[1] + mDef;
                            }
                        }
                    }
                    case "type-position" -> {
                        String typeC = cList.getString("typeCheck");
                        String posC = cList.getString("posCheck");

                        int mAtk = cList.getInt("modValueAtk");
                        int mDef = cList.getInt("modValueDef");

                        for(int n = 0; n < 4; n++) {
                            for (int o = 0; o < 3; o++) {
                                if (!board_Grid[n][o].isEmpty()) {
                                    if (board_Grid[0][i].getSlot().activeTypeOne().equals(typeC) && String.valueOf(board_Grid[0][i].currentPlace()).contentEquals(posC) && board_Grid[0][i].currentPlace() == UBER || board_Grid[0][i].getSlot().activeTypeTwo().equals(typeC) && String.valueOf(board_Grid[0][i].currentPlace()).contentEquals(posC) && board_Grid[0][i].currentPlace() == UBER) {
                                        uberRow[0] = uberRow[0] + mAtk;
                                        uberRow[1] = uberRow[1] + mDef;
                                    }
                                    if (board_Grid[1][i].getSlot().activeTypeOne().equals(typeC) && String.valueOf(board_Grid[1][i].currentPlace()).contentEquals(posC) && board_Grid[1][i].currentPlace() == ATTACK || board_Grid[1][i].getSlot().activeTypeTwo().equals(typeC) && String.valueOf(board_Grid[1][i].currentPlace()).contentEquals(posC) && board_Grid[1][i].currentPlace() == ATTACK) {
                                        atkRow[0] = atkRow[0] + mAtk;
                                        atkRow[1] = atkRow[1] + mDef;
                                    }
                                    if (board_Grid[n][i].getSlot().activeTypeOne().equals(typeC) && String.valueOf(board_Grid[n][i].currentPlace()).contentEquals(posC) && board_Grid[n][i].currentPlace() == CoreDEFENCE || board_Grid[n][i].getSlot().activeTypeTwo().equals(typeC) && String.valueOf(board_Grid[n][i].currentPlace()).contentEquals(posC) && board_Grid[n][i].currentPlace() == CoreDEFENCE) {
                                        cDefRow[0] = cDefRow[0] + mAtk;
                                        cDefRow[1] = cDefRow[1] + mDef;
                                    }
                                    if (board_Grid[3][i].getSlot().activeTypeOne().equals(typeC) && String.valueOf(board_Grid[3][i].currentPlace()).contentEquals(posC) && board_Grid[3][i].currentPlace() == DEFENCE || board_Grid[3][i].getSlot().activeTypeTwo().equals(typeC) && String.valueOf(board_Grid[3][i].currentPlace()).contentEquals(posC) && board_Grid[3][i].currentPlace() == DEFENCE) {
                                        defRow[0] = defRow[0] + mAtk;
                                        defRow[1] = defRow[1] + mDef;
                                    }
                                }
                            }
                        }

                        for(int n = 0; n < 4; n++) {
                            for (int o = 0; o < 3; o++) {
                                if (!enemy.getGrid()[n][o].isEmpty()) {
                                    if (enemy.getGrid()[0][i].getSlot().activeTypeOne().equals(typeC) && String.valueOf(enemy.getGrid()[0][i].currentPlace()).contentEquals(posC) && enemy.getGrid()[0][i].currentPlace() == UBER || enemy.getGrid()[0][i].getSlot().activeTypeTwo().equals(typeC) && String.valueOf(enemy.getGrid()[0][i].currentPlace()).contentEquals(posC) && enemy.getGrid()[0][i].currentPlace() == UBER) {
                                        uberRow[0] = uberRow[0] + mAtk;
                                        uberRow[1] = uberRow[1] + mDef;
                                    }
                                    if (enemy.getGrid()[1][i].getSlot().activeTypeOne().equals(typeC) && String.valueOf(enemy.getGrid()[1][i].currentPlace()).contentEquals(posC) && enemy.getGrid()[1][i].currentPlace() == ATTACK || enemy.getGrid()[1][i].getSlot().activeTypeTwo().equals(typeC) && String.valueOf(enemy.getGrid()[1][i].currentPlace()).contentEquals(posC) && enemy.getGrid()[1][i].currentPlace() == ATTACK) {
                                        atkRow[0] = atkRow[0] + mAtk;
                                        atkRow[1] = atkRow[1] + mDef;
                                    }
                                    if (enemy.getGrid()[n][i].getSlot().activeTypeOne().equals(typeC) && String.valueOf(enemy.getGrid()[n][i].currentPlace()).contentEquals(posC) && enemy.getGrid()[n][i].currentPlace() == CoreDEFENCE || enemy.getGrid()[n][i].getSlot().activeTypeTwo().equals(typeC) && String.valueOf(enemy.getGrid()[n][i].currentPlace()).contentEquals(posC) && enemy.getGrid()[n][i].currentPlace() == CoreDEFENCE) {
                                        cDefRow[0] = cDefRow[0] + mAtk;
                                        cDefRow[1] = cDefRow[1] + mDef;
                                    }
                                    if (enemy.getGrid()[3][i].getSlot().activeTypeOne().equals(typeC) && String.valueOf(enemy.getGrid()[3][i].currentPlace()).contentEquals(posC) && enemy.getGrid()[3][i].currentPlace() == DEFENCE || enemy.getGrid()[3][i].getSlot().activeTypeTwo().equals(typeC) && String.valueOf(enemy.getGrid()[3][i].currentPlace()).contentEquals(posC) && enemy.getGrid()[3][i].currentPlace() == DEFENCE) {
                                        defRow[0] = defRow[0] + mAtk;
                                        defRow[1] = defRow[1] + mDef;
                                    }
                                }
                            }
                        }

                    }
                    case "card" -> {
                        int cIDCheck = cList.getInt("cardIDCheck");

                        int mAtk = cList.getInt("modValueAtk");
                        int mDef = cList.getInt("modValueDef");

                        int tik = 0;

                        for(int l = 0; l < 4; l++){
                            for (int o = 0; o < 3; o++) {
                                if (board_Grid[l][o].getSlot().getId() == cIDCheck) {
                                    tik++;
                                }
                            }
                        }
                        for (int k = 0; k < tik; k++) {
                            for(int l = 0; i < 4; i++){
                                for(int o = 0; j < 3; j++){
                                    if(!board_Grid[l][j].isEmpty()) {
                                        if (board_Grid[l][o].getSlot().getId() == cID && board_Grid[l][o].currentPlace() == UBER) {
                                            uberRow[0] = uberRow[0] + mAtk;
                                            uberRow[1] = uberRow[1] + mDef;
                                        }
                                        if (board_Grid[l][o].getSlot().getId() == cID && board_Grid[l][o].currentPlace() == ATTACK) {
                                            atkRow[0] = atkRow[0] + mAtk;
                                            atkRow[1] = atkRow[1] + mDef;
                                        }
                                        if (board_Grid[l][o].getSlot().getId() == cID && board_Grid[l][o].currentPlace() == CoreDEFENCE) {
                                            cDefRow[0] = cDefRow[0] + mAtk;
                                            cDefRow[1] = cDefRow[1] + mDef;
                                        }
                                        if (board_Grid[l][o].getSlot().getId() == cID && board_Grid[l][o].currentPlace() == DEFENCE) {
                                            defRow[0] = defRow[0] + mAtk;
                                            defRow[1] = defRow[1] + mDef;
                                        }
                                    }
                                }
                            }

                        }

                        tik = 0;
                        for(int l = 0; l < 4; l++){
                            for (int o = 0; o < 3; o++) {
                                if (enemy.getGrid()[l][o].getSlot().getId() == cIDCheck) {
                                    tik++;
                                }
                            }
                        }
                        for (int k = 0; k < tik; k++) {
                            for(int l = 0; i < 4; i++){
                                for(int o = 0; j < 3; j++) {
                                    if (!enemy.getGrid()[l][o].isEmpty()) {
                                        if (enemy.getGrid()[l][o].getSlot().getId() == cID && enemy.getGrid()[l][o].currentPlace() == UBER) {
                                            enemy.getUberRow()[0] = enemy.getUberRow()[0] + mAtk;
                                            enemy.getUberRow()[1] = enemy.getUberRow()[1] + mDef;
                                        }

                                        if (enemy.getGrid()[l][o].getSlot().getId() == cID && enemy.getGrid()[l][o].currentPlace() == ATTACK) {
                                            enemy.getAtkRow()[0] = enemy.getAtkRow()[0] + mAtk;
                                            enemy.getAtkRow()[1] = enemy.getAtkRow()[1] + mDef;
                                        }

                                        if (enemy.getGrid()[l][o].getSlot().getId() == cID && enemy.getGrid()[l][o].currentPlace() == CoreDEFENCE) {
                                            enemy.getCDefRow()[0] = enemy.getCDefRow()[0] + mAtk;
                                            enemy.getCDefRow()[1] = enemy.getCDefRow()[1] + mDef;
                                        }

                                        if (enemy.getGrid()[l][o].getSlot().getId() == cID && enemy.getGrid()[l][o].currentPlace() == DEFENCE) {
                                            enemy.getDefRow()[0] = enemy.getDefRow()[0] + mAtk;
                                            enemy.getDefRow()[1] = enemy.getDefRow()[1] + mDef;
                                        }
                                    }
                                }
                            }
                        }

                    }
                }
            }
        } catch (IOException ex){
            throw new IOException("Something Has Failed");
        }
        //add core
        for(int r = 0; r < 2; r++){ //0 is atk && 1 is def, so it sorts itself out
            uberRow[r] = uberRow[r] + coreBlock[r]; //atk
            enemy.getUberRow()[r] = enemy.getUberRow()[r] + enemy.getCoreBlock()[r];
            atkRow[r] = atkRow[r] + coreBlock[r];
            enemy.getAtkRow()[r] = enemy.getAtkRow()[r] + enemy.getCoreBlock()[r];
            cDefRow[r] = cDefRow[r] + coreBlock[r];
            enemy.getCDefRow()[r] = enemy.getCDefRow()[r] + enemy.getCoreBlock()[r];
            defRow[r] = defRow[r] + coreBlock[r];
            enemy.getDefRow()[r] = enemy.getDefRow()[r] + enemy.getCoreBlock()[r];
        }

        int overflowP1, overflowP2;
        overflowP2 = uberRow[1] - enemy.getUberRow()[0]; // uber gets no overflow
        overflowP1 = enemy.getUberRow()[1] - uberRow[0]; //p1 remainder from subtracting atk from enemy def
        int tmp1, tmp2; // the overflow goes once, so it needs to reset after

        if(overflowP1 > 0){
            atkRow[0] = atkRow[0] + overflowP1;
        }
        tmp1 = overflowP1;
        if(overflowP2 > 0){
            enemy.getAtkRow()[0] = enemy.getAtkRow()[0] + overflowP2;
        }
        tmp2 = overflowP2;

        overflowP2 = atkRow[1] - (enemy.getAtkRow()[0] - tmp2);
        overflowP1 = enemy.getAtkRow()[1] - (atkRow[0] - tmp1) ;
        if(overflowP1 > 0){
            cDefRow[0] = cDefRow[0] + overflowP1;
        }
        tmp1 = overflowP1;
        if(overflowP2 > 0){
            enemy.getCDefRow()[0] = enemy.getCDefRow()[0] + overflowP2;
        }
        tmp2 = overflowP2;

        overflowP2 = cDefRow[1] - (enemy.getCDefRow()[0] - tmp2);
        overflowP1 = enemy.getCDefRow()[1] - (cDefRow[0] - tmp1);
        if(overflowP1 > 0){
            defRow[0] = defRow[0] + overflowP1;
        }
        if(overflowP2 > 0){
            enemy.getDefRow()[0] = enemy.getDefRow()[0] + overflowP2;
        }

        //if board row is greater remove cards from other board vice versa
        //enemy lose action = take away cards
        if(uberRow[0] > enemy.getUberRow()[1] && uberRow[1] > enemy.getUberRow()[0]){
            for(int i = 0; i < 3; i++) {
                enemy.getGrid()[0][i].remove();
            }
        } else if(uberRow[0] < enemy.getUberRow()[1] && uberRow[1] < enemy.getUberRow()[0]){
            for(int i = 0; i < 3; i++) {
                board_Grid[0][i].remove();
            }
        }
        if(atkRow[0] > enemy.getAtkRow()[1] && atkRow[1] > enemy.getAtkRow()[0]){
            for(int i = 0; i < 3; i++) {
                if(enemy.getGrid()[1][i].currentPlace() == ATTACK){
                    enemy.getGrid()[1][i].remove();
                }
            }
        } else if(uberRow[0] < enemy.getUberRow()[1] && uberRow[1] < enemy.getUberRow()[0]){
            for(int i = 0; i < 3; i++) {
                if(board_Grid[1][i].currentPlace() == ATTACK){
                    board_Grid[1][i].remove();
                }
            }
        }
        if(cDefRow[0] > enemy.getCDefRow()[1] && cDefRow[1] > enemy.getCDefRow()[0]){
            for(int i = 1; i < 3; i++) {
                for(int j = 0; j < 3; j++) {
                    if (enemy.getGrid()[i][j].currentPlace() == CoreDEFENCE) {
                        enemy.getGrid()[i][j].remove();
                    }
                }
            }
        } else if(cDefRow[0] < enemy.getCDefRow()[1] && cDefRow[1] < enemy.getCDefRow()[0]){
            for(int i = 1; i < 3; i++) {
                for(int j = 0; j < 3; j++) {
                    if (board_Grid[i][j].currentPlace() == CoreDEFENCE) {
                        board_Grid[i][j].remove();
                    }
                }
            }
        }
        if(defRow[0] > enemy.getDefRow()[1] && defRow[1] > enemy.getDefRow()[0]){
            for(int i = 0; i < 3; i++) {
                enemy.getGrid()[3][i].remove();
            }
        } else if(defRow[0] < enemy.getDefRow()[1] && defRow[1] < enemy.getDefRow()[0]){
            for(int i = 0; i < 3; i++) {
                board_Grid[3][i].remove();
            }
        }
    }

    private final ArrayList<ComboBuild> possibleBuilds = new ArrayList<>();
    public ArrayList<ComboBuild> getPossibleBuilds(){ return possibleBuilds; }
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

                    int atk = board_Grid[0][colChoice].getAtk();
                    int def =board_Grid[0][colChoice].getDef();
                    getUberRow()[0] = getUberRow()[0] + atk;
                    getUberRow()[1] = getUberRow()[1] + def;
                }
            }
            case "ATTACK" -> {
                if(board_Grid[1][colChoice] == null ){ // only col 0 & 2
                    board_Grid[1][colChoice] = new Positions(ATTACK, fromHand);

                    int atk = board_Grid[1][colChoice].getAtk();
                    int def = board_Grid[1][colChoice].getDef();
                    getAtkRow()[0] = getAtkRow()[0] + atk;
                    getAtkRow()[1] = getAtkRow()[1] + def;
                }
            }
            case "CoreDEFENCE" -> {
                System.out.println("Which Row? (1 or 2)");
                int rowChoice = input.nextInt();
                if(board_Grid[rowChoice][colChoice] == null) { // only 1,1 2,0 2,2
                    board_Grid[rowChoice][colChoice] = new Positions(CoreDEFENCE, fromHand);

                    int atk = board_Grid[rowChoice][colChoice].getAtk();
                    int def = board_Grid[rowChoice][colChoice].getDef();
                    getCDefRow()[0] = getCDefRow()[0] + atk;
                    getCDefRow()[1] = getCDefRow()[1] + def;
                }
            }
            case "CORE" -> {
                if(board_Grid[2][1] == null){
                    board_Grid[2][1] = new Positions(CORE, fromHand);

                    int atk = board_Grid[2][1].getAtk();
                    int def = board_Grid[2][1].getDef();
                    getCoreBlock()[0] = atk;
                    getCoreBlock()[1] = def;
                }
            }
            case "DEFENCE" -> {
                if(board_Grid[3][colChoice] == null){
                    board_Grid[3][colChoice] = new Positions(DEFENCE, fromHand);

                    int atk = board_Grid[3][colChoice].getAtk();
                    int def = board_Grid[3][colChoice].getDef();
                    getDefRow()[0] = getDefRow()[0] + atk;
                    getDefRow()[1] = getDefRow()[1] + def;
                }
            }
        }
    }
    public void removeFromSlots(String name, int posIndex) {
        switch (name) {
            case "UBER" -> {
                getUberRow()[0] = getUberRow()[0] - board_Grid[0][posIndex].getAtk();
                getUberRow()[1] = getUberRow()[1] - board_Grid[0][posIndex].getDef();

                board_Grid[0][posIndex].remove();
            }
            case "ATTACK" -> {
                getAtkRow()[0] = getAtkRow()[0] - board_Grid[1][posIndex].getAtk();
                getAtkRow()[1] = getAtkRow()[1] - board_Grid[1][posIndex].getDef();

                board_Grid[1][posIndex].remove();
            } //only 0 and 2 are valid
            case "CoreDEFENCE" -> {
                Scanner input = new Scanner(System.in);
                System.out.println("Which row? 1 or 2");
                int rowSelect = input.nextInt();

                getCDefRow()[0] = getCDefRow()[0] - board_Grid[posIndex][rowSelect].getAtk();
                getCDefRow()[1] = getCDefRow()[1] - board_Grid[posIndex][rowSelect].getDef();

                board_Grid[posIndex][rowSelect].remove(); // only 0 and 2 are valid for row 2 and 1 is valid row 1
            }
            case "CORE" ->{
                getCoreBlock()[0] = getCoreBlock()[0] - board_Grid[2][1].getAtk();
                getCoreBlock()[1] = getCoreBlock()[1] - board_Grid[2][1].getDef();

                board_Grid[2][1].remove();
            }// only 1 is valid
            case "DEFENCE" ->{
                getDefRow()[0] = getDefRow()[0] - board_Grid[3][posIndex].getAtk();
                getDefRow()[1] = getDefRow()[1] - board_Grid[3][posIndex].getDef();

                board_Grid[3][posIndex].remove();
            }
        }

    }

    public Positions[][] getGrid(){
        return board_Grid;
    }

    public boolean isBoardEmpty(){
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 3; j++){
                if(board_Grid[0][j].isEmpty() && board_Grid[1][j].isEmpty() && board_Grid[2][0].isEmpty() && board_Grid[2][2].isEmpty() && board_Grid[3][j].isEmpty()){
                    return true;
                }
            }
        }
        return false;
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
    public void showValidBuilds(Board playerBoard) throws IOException {
        int checkResults = 0;

        if(playerBoard.hasBuild()){
//            return playerBoard.getCurrentBuild();
            System.out.println("Ye no");
            return;
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
                                    playerBoard.getPossibleBuilds().add(x);
                                }
                            }
                        }

                    }else if(needCard && !needType){
                        int tmpID = tmpIngredients.getInt("cardID");

                        for(int l = 0; l < getPos.length(); l++){
                            JSONObject tmpPos = getPos.getJSONObject(l);

                            boolean needMorePoints = tmpPos.getBoolean("additionalPoints");
                            String pName = tmpPos.getString("posName");
                            if(!needMorePoints){
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
                            } else {
                                int extraPoints = tmpPos.getInt("ePoints");
                                //switch to the corresponding row for the posName to check the types
                                switch(pName) {
                                    default -> System.exit(1);
                                    case "UBER" -> {
                                        for (int p = 0; p < 3; p++) {
                                            if (playerBoard.getGrid()[0][p].getSlot().getId() == tmpID) {
                                                checkResults++; // log the correct the go check others
                                                playerBoard.getUberRow()[0] = playerBoard.getUberRow()[0] + extraPoints;
                                                playerBoard.getUberRow()[1] = playerBoard.getUberRow()[1] + extraPoints;
                                            }
                                        }
                                    }
                                    case "ATTACK" -> {
                                        if (playerBoard.getGrid()[1][0].getSlot().getId() == tmpID ) {
                                            checkResults++; // log the correct the go check others
                                            playerBoard.getAtkRow()[0] = playerBoard.getAtkRow()[0] + extraPoints;
                                            playerBoard.getAtkRow()[1] = playerBoard.getAtkRow()[1] + extraPoints;
                                        } else if(playerBoard.getGrid()[1][2].getSlot().getId() == tmpID){
                                            checkResults++;
                                            playerBoard.getAtkRow()[0] = playerBoard.getAtkRow()[0] + extraPoints;
                                            playerBoard.getAtkRow()[1] = playerBoard.getAtkRow()[1] + extraPoints;
                                        }
                                    }
                                    case "CoreDEFENCE" -> {
                                        if (playerBoard.getGrid()[1][1].getSlot().getId() == tmpID ) {
                                            checkResults++; // log the correct the go check others
                                            playerBoard.getCDefRow()[0] = playerBoard.getCDefRow()[0] + extraPoints;
                                            playerBoard.getCDefRow()[1] = playerBoard.getCDefRow()[1] + extraPoints;
                                        } else if(playerBoard.getGrid()[2][0].getSlot().getId() == tmpID){
                                            checkResults++;
                                            playerBoard.getCDefRow()[0] = playerBoard.getCDefRow()[0] + extraPoints;
                                            playerBoard.getCDefRow()[1] = playerBoard.getCDefRow()[1] + extraPoints;
                                        } else if(playerBoard.getGrid()[2][2].getSlot().getId() == tmpID){
                                            checkResults++;
                                            playerBoard.getCDefRow()[0] = playerBoard.getCDefRow()[0] + extraPoints;
                                            playerBoard.getCDefRow()[1] = playerBoard.getCDefRow()[1] + extraPoints;
                                        }
                                    }
                                    case "CORE" -> {
                                        if (playerBoard.getGrid()[2][1].getSlot().getId() == tmpID) {
                                            checkResults++;
                                            playerBoard.getCoreBlock()[0] = playerBoard.getCoreBlock()[0] + extraPoints;
                                            playerBoard.getCoreBlock()[1] = playerBoard.getCoreBlock()[1] + extraPoints;
                                        }
                                    }
                                    case "DEFENCE" -> {
                                        for (int p = 0; p < 3; p++) {
                                            if (playerBoard.getGrid()[3][p].getSlot().getId() == tmpID) {
                                                checkResults++; // log the correct the go check others
                                                playerBoard.getDefRow()[0] = playerBoard.getDefRow()[0] + extraPoints;
                                                playerBoard.getDefRow()[1] = playerBoard.getDefRow()[1] + extraPoints;
                                            }
                                        }
                                    }
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
                                    playerBoard.getPossibleBuilds().add(x);
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
    }

    //evolutions can scan the board check for the card then check the number if it's not there or there is not enough space move on
    public void evolve(Player cc, int cID) throws IOException{
        try(FileReader modReader = new FileReader("C:\\Users\\sensa\\IdeaProjects\\testGame\\src\\Game\\CardData\\Evolutions.json")) {
            StringBuilder modString = new StringBuilder(" ");
            int i;
            while ((i = modReader.read()) != -1) {
                modString.append((char) i);
            }
            JSONObject jsonMODObject = new JSONObject(modString.toString());
            JSONArray eList = (JSONArray) jsonMODObject.get("evolve");

            for(int l = 0; l < eList.length(); l++){
                JSONObject c = eList.getJSONObject(i);
                int oCard = c.getInt("itemIDToEvolve");
                int checkNum = c.getInt("requiredNumOfItem");

                int newCard = c.getInt("finalItemID");
                int newCardNum = c.getInt("numAddToBoard");

                int tally = 0;
                if(cID == oCard) {
                    for (int n = 0; n < 4; n++) {
                        for (int o = 0; o < 3; o++) {
                            if(board_Grid[n][o].getSlot().getId() == oCard){
                                tally++;
                            }
                        }
                    }
                    if(tally == checkNum){
                        for (int n = 0; n < 4; n++) {
                            for (int o = 0; o < 3; o++) {
                                if(board_Grid[n][o].getSlot().getId() == oCard){
                                    board_Grid[n][o].remove();
                                }
                            }
                        }

                        //get the card from database, when u find card save it, then put as many as you need in hand so u can place
                        cardDatabase v = new cardDatabase();
                        for (int j = 0; j < v.pack.size(); j++) {
                            if(v.pack.get(j).getId() == newCard){
                                Card nn = v.pack.get(j);
                                for(int p = 0; p < newCardNum; p++){
                                    cc.getHand().add(nn);

                                }
                            }
                        }
                    } else{
                        System.out.println("Not enough");
                    }

                }
            }

        } catch (IOException ex){
            throw new IOException("Something Has Failed");
        }
        //check the card to evolve id# and if the number needed appears if so add the number of cards to hand
    }

}
package Game;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Combos {
    private ArrayList<ComboBuild> comboBucket = new ArrayList<>();
    private ArrayList<Card> exchangeBin = new ArrayList<>();
//    Map<ComboBuild, Set<Map<>>> checkCards = new HashMap<>();
//    Map<Card, Integer> countNumCards = new HashMap<>();
//    Map<String, Integer> typeToNum = new HashMap<>();
//    Map<Integer, Integer> IDToNumCards = new HashMap<>();
//if the combo needs 2 maps just run them twice in whatever order

    public Combos(String comboName) throws IOException{
        try(BufferedReader modReader = new BufferedReader(new InputStreamReader(Main.class.getResourceAsStream("CardData/comboItemList.json")))) {
            StringBuilder comboString = new StringBuilder(" ");
            int i;
            while ((i = modReader.read()) != -1) {
                comboString.append((char) i);
            }
            JSONObject getJSONCombo = new JSONObject(comboString.toString());
            JSONArray recList = getJSONCombo.getJSONArray("recipe_List");


            //check to make sure the combo is there or spelled correctly
            String cFName;
            int p;
            for( p = 0; p < recList.length(); p++){
                JSONObject fN = (JSONObject) recList.get(p);
                cFName = fN.getString("ItemForm");
                if(cFName.equals(comboName)){
                    break;
                } else if(p == recList.length()){
                    System.out.println("Incorrect Spelling or Item");
                }
            }

            for (int l = 0; l < recList.length(); l++) {
                JSONArray ingList = (JSONArray) recList.get(p);
                JSONObject things = ingList.getJSONObject(l);



                boolean isCard = things.getBoolean("getCard");
                boolean isSpecificCard = things.getBoolean("cardCheck");
                boolean isType = things.getBoolean("typeCheck");
                int itemCount = things.getInt("numOfItem");

            }
        }catch(IOException ex){
            throw new IOException("Something Has Failed");
        }
    }
    public void addToBin(Card x){ exchangeBin.add(x);}
    public ArrayList<Card> checkBin(){return exchangeBin;}
    public ArrayList<ComboBuild> availableCombo(){return comboBucket;}
}

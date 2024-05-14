package Game;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Combos {
    private ArrayList<ComboBuild> comboBucket = new ArrayList<>();
    private ArrayList<Card> exchangeBin = new ArrayList<>();
    Map<ComboBuild, Map<Card,Integer>> checkCards = new HashMap<>();
    Map<Card, Integer> countNumCards = new HashMap<>();
    Map<String, Integer> typeToNum = new HashMap<>();
    Map<Integer, Integer> IDToNumCards = new HashMap<>();
//if the combo needs 2 maps just run them twice in whatever order

    public Combos() throws IOException{
        try(BufferedReader modReader = new BufferedReader(new InputStreamReader(Main.class.getResourceAsStream("CardData/comboItemList.json")))) {
            StringBuilder comboString = new StringBuilder(" ");
            int i;
            while ((i = modReader.read()) != -1) {
                comboString.append((char) i);
            }

            JSONObject getJSONCombo = new JSONObject(comboString.toString());
            JSONObject recList = getJSONCombo.getJSONObject("recipe_List");
            JSONArray ingList = (JSONArray) recList.get("ingredients");
            JSONObject reSalt = (JSONObject) recList.get("result");

            for (int l = 0; l < ingList.length(); l++) {
                JSONObject greed = ingList.getJSONObject(l);

                boolean isCard = greed.getBoolean("getCard");
                boolean isSpecificCard = greed.getBoolean("cardCheck");
                boolean isType = greed.getBoolean("typeCheck");
                int itemCount = greed.getInt("numOfItem");

                if(isCard){

                }

            }
        }catch(IOException ex){
            throw new IOException("Something Has Failed");
        }
    }
    public void addToBin(Card x){ exchangeBin.add(x);}
    public ArrayList<Card> checkBin(){return exchangeBin;}
    public ArrayList<ComboBuild> availableCombo(){return comboBucket;}
}

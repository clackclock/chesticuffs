package Game;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Combos {
    cardDatabase gg = new cardDatabase();
    private final ArrayList<ComboBuild> comboBucket = new ArrayList<>();
    private final ArrayList<Card> exchangeBin = new ArrayList<>();

    public void comboSearch(String comboName) throws IOException{
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

            JSONObject selectedCombo = (JSONObject) recList.get(p);
            //System.out.println(selectedCombo);
            JSONArray ing = selectedCombo.getJSONArray("ingredients");
            JSONObject re = selectedCombo.getJSONObject("result");
            int getID = re.getInt("formID");
            System.out.println(ing);

            int ingListTik = 0;
            for (int l = 0; l < ing.length(); l++) {
                JSONObject milkSugar = ing.getJSONObject(l);

                boolean isCard = milkSugar.getBoolean("getCard");
                boolean isSpecificCard = milkSugar.getBoolean("cardCheck");
                boolean isType = milkSugar.getBoolean("typeCheck");
                int itemCount = milkSugar.getInt("numOfItem");

                int tik;
                if(isCard){ // if we are looking for 3 of or more of a kind of any card
                    tik = 0;
                    Card x;
                    int index = 0;
//                    for(int y = 0; y < exchangeBin.size(); y++){
                        x = exchangeBin.get(index);
                        for(Card c: exchangeBin){
                            if(c == x){
                                tik= tik + 1;
                            }
                            index++;
                        }
                   // }
                    if(tik == itemCount){
                        ingListTik++;
                        break;
                    }

                }
                if(isSpecificCard){ // if we are looking for more than one of a specific card
                    tik = 0;
                    int ego = milkSugar.getInt("cardID");
                    for(Card x: exchangeBin){
                        if(x.getId() == ego){
                            tik++;
                        }
                    }
                    if(tik == itemCount){
                        ingListTik++;
                        break;
                    }
                }
                if(isType){ //if we are looking for more than one of a type of card
                    tik = 0;
                    String tip = milkSugar.getString("type");
                    for(Card x: exchangeBin){
                        if(x.eitherType(tip)){
                            tik++;
                        }
                    }
                    if(tik == itemCount){
                        ingListTik++;
                        break;
                    }
                }
            }
            System.out.println(ingListTik);
            if(ingListTik == ing.length()){
                for(ComboBuild x: gg.formPack){
                    if(x.getId() == getID){
                        comboBucket.add(x);
                    }
                }

            }else{
                System.out.println("Your attack isn't ready yet");
            }
        }catch(IOException ex){
            throw new IOException("Something Has Failed");
        }
    }
    public void addToBin(Card x){exchangeBin.add(x);}
    public ArrayList<Card> checkBin(){return exchangeBin;}
    public ArrayList<ComboBuild> availableCombo(){return comboBucket;}

    public static void main(String[] args) throws IOException {
        cardDatabase g = new cardDatabase();
        //g.cardData();
        Combos  c = new Combos();

        c.addToBin(g.pack.get(4));
        c.addToBin(g.pack.get(4));
        c.addToBin(g.pack.get(4));

        c.comboSearch("format:Stairs");
        System.out.println(c.availableCombo().get(0));

    }
}

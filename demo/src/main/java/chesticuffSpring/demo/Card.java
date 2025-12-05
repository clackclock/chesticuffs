package chesticuffSpring.demo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.HashMap;

public class Card {
    private final String cardName;
    private final int id;
    private boolean item = false;
    private final String cardTypeONE;
    private final String cardTypeTWO;
    private final String imageID;
    private final HashMap<Board_Positions, int[]> positionMap = new HashMap<>();

    @JsonCreator
    public Card(
            @JsonProperty("cID") int ID,                       // Matches "cID"
            @JsonProperty("name") String name,                 // Matches "name"
            // Note: Using 'null' as the default value if a field is missing in JSON (e.g., card ID 17)
            @JsonProperty(value = "UBER", defaultValue = "null") int[] uAtk,
            @JsonProperty(value = "ATTACK", defaultValue = "null") int[] atk,
            @JsonProperty(value = "CoreDEFENCE", defaultValue = "null") int[] cDef,
            @JsonProperty(value = "CORE", defaultValue = "null") int[] corn,
            @JsonProperty(value = "DEFENCE", defaultValue = "null") int[] def,
            @JsonProperty("pTYPE") String prime,               // Matches "pTYPE"
            @JsonProperty(value = "sTYPE", defaultValue = "NEUTRAL") String secondary, // Matches "sTYPE", adds default
            @JsonProperty("imageID") String imageID            // Matches "imageID"
    ) {
        cardName = name;
        id = ID;
        this.imageID = imageID;

        positionMap.put(Board_Positions.UBER, uAtk);
        positionMap.put(Board_Positions.ATTACK, atk);
        positionMap.put(Board_Positions.CoreDEFENCE, cDef);
        positionMap.put(Board_Positions.CORE, corn);
        positionMap.put(Board_Positions.DEFENCE, def);

        // Only put the array into the map if it exists in the JSON (is not null)
        if (uAtk != null) this.positionMap.put(Board_Positions.UBER, uAtk);
        if (atk != null) this.positionMap.put(Board_Positions.ATTACK, atk);
        if (cDef != null) this.positionMap.put(Board_Positions.CoreDEFENCE, cDef);
        if (corn != null) this.positionMap.put(Board_Positions.CORE, corn);
        if (def != null) this.positionMap.put(Board_Positions.DEFENCE, def);

        cardTypeONE = prime;
        cardTypeTWO = secondary;
    }
    //for individual number stat calls *edit for type calls
    public int[] getValue(Board_Positions boardPos){
        return positionMap.get(boardPos);
    }
    public String getCardTypeONE() { return cardTypeONE; }
    public String getCardTypeTWO() { return cardTypeTWO; }
    public boolean eitherType(String type) {
        return cardTypeONE.equals(type) || cardTypeTWO.equals(type);
    }
    public void useAsItem(){ item = true; } // if item is true use item mods in calculation
    public boolean isItem(){ return item; }
    public int getId(){ return id; }
    public String getImageID() { return imageID; }
    public String getCardName() { return cardName; }

    public String toString() {
        return id + " " + cardName + " " + cardTypeONE + " " + cardTypeTWO + " ";
    }
}

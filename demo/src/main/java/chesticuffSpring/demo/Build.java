//package chesticuffSpring.demo;
//
//import com.fasterxml.jackson.annotation.JsonCreator;
//import com.fasterxml.jackson.annotation.JsonProperty;
//
//import java.util.Arrays;
//
//public class Build extends Card{
//
//    // State variable used by the Board class
//    private boolean isBuildUsed = false;
//
//    @JsonCreator
//    public Build(@JsonProperty("cID") int ID,
//                 @JsonProperty("name") String name,
//                 @JsonProperty("UBER") int[] uAtk,
//                 @JsonProperty("ATTACK") int[] atk,
//                 @JsonProperty("CoreDEFENCE") int[] cDef,
//                 @JsonProperty("CORE") int[] corn,
//                 @JsonProperty("DEFENCE") int[] def,
//                 @JsonProperty("pTYPE") String prime,
//                 @JsonProperty(value = "sTYPE", defaultValue = "NEUTRAL") String secondary,
//                 @JsonProperty("imageID") String imageID
//    )
//    {
//        super(ID, name, uAtk, atk, cDef, corn, def, prime, secondary, imageID);
//    }
//
//    // --- Getters used by Board.calculate() ---
//    public int[] getCoreMod(){ return this.getValue(Board_Positions.CORE);}
//
//    // --- Mutators/State used by Board ---
//    public void useBuild(){ isBuildUsed = true; }
//    public boolean isBuildUsed(){ return isBuildUsed; }
//
//    @Override
//    public String toString() {
//        return this.getId() + " " + this.getCardName() + " " + Arrays.toString(getCoreMod());
//    }
//
//}

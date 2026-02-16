package chesticuffSpring.demo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Arrays;

public class Build extends Card {
    private boolean isBuildUsed = false;

    @JsonCreator
    public Build(@JsonProperty("cID") int ID,
                 @JsonProperty("name") String name,
                 @JsonProperty("UBER") int[] uAtk,
                 @JsonProperty("ATTACK") int[] atk,
                 @JsonProperty("CoreDEFENCE") int[] cDef,
                 @JsonProperty("CORE") int[] corn,
                 @JsonProperty("DEFENCE") int[] def,
                 @JsonProperty("pTYPE") String prime,
                 @JsonProperty(value = "sTYPE", defaultValue = "NEUTRAL") String secondary,
                 @JsonProperty("imageID") String imageID) {
        super(ID, name, uAtk, atk, cDef, corn, def, prime, secondary, imageID);
    }

    // This is what the Board looks for when calculating total power
    public int[] getCoreMod() {
        int[] mod = this.getValue(Board_Positions.CORE);
        // Safety check: if CORE wasn't in the JSON for this build, return [0,0]
        return (mod != null) ? mod : new int[]{0, 0};
    }

    public void useBuild() { this.isBuildUsed = true; }
    public boolean isBuildUsed() { return isBuildUsed; }

    @Override
    public String toString() {
        return "BUILD: " + this.getCardName() + " Mod: " + Arrays.toString(getCoreMod());
    }
}
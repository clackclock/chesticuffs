package Game;

public class ComboBuilds {
    private final String itemName;
    private final int id;
    private final int[] coreMod;
    private final String mainType;
    private boolean isBuildUsed = false;

    public ComboBuilds(int ID, String iName, int[] core, String type){
        id = ID;
        itemName = iName;
        coreMod = core;
        mainType = type;

    }
    public int[] getCoreMod(){ return coreMod;}
    public String getItemName(){ return itemName;}
    public String activeType() { return mainType; }
    public int getId(){ return id; }
    public void useBuild(){ isBuildUsed = true; }
    public boolean isBuildUsed(){ return isBuildUsed; }

}

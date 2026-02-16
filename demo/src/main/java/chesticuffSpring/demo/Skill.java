package chesticuffSpring.demo;

public class Skill {
    public String name;
    public int cardID;
    public String skillType; // "type", "type-position", etc.
    public String typeCheck;
    public String posCheck; // Using String because it can be null or an array in your JSON
    public int modValueAtk;
    public int modValueDef;
}
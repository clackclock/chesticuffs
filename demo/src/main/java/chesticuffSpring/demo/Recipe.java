package chesticuffSpring.demo;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;

public class Recipe {
    public String needs;
    public String givesModTo;
    public List<Ingredient> ingredients;
    public Map<String, Object> makes; // Contains "name" and "id"

    public static class Ingredient {
        public String name;
        public String type;
        public Integer id;
        public int amt;
        public Boolean checkCardsSame;
    }

    public int getTargetCardId() {
        return (int) makes.get("id");
    }
}
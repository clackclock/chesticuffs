package chesticuffSpring.demo;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class CardService {
    private final List<Card> cards = new ArrayList<>();
    private final List<Skill> skills = new ArrayList<>();
    private final List<Recipe> recipes = new ArrayList<>();
    private final List<Evolution> evolutions = new ArrayList<>();
    private final ObjectMapper mapper = new ObjectMapper();

    @PostConstruct
    public void init() {
        try {
            // Safer way to load resources in Spring Boot (works in JARs)
            loadCards("/static/card_image.json");
            loadSkills("/static/Modifier.json");
            loadEvolutions("/static/Evolutions.json");
            loadRecipes("/static/BuildRecipes.json");

            System.out.println("Card Database Loaded: " + cards.size() + " cards, " + evolutions.size() + " evolutions.");
        } catch (Exception e) {
            System.err.println("CRITICAL: Failed to load game data!");
            e.printStackTrace();
        }
    }

    //Helps get Specific Array Data
    private void loadCards(String path) throws Exception {
        InputStream is = getClass().getResourceAsStream(path);
        // Using your CardDataWrapper logic
        CardDataWrapper wrapper = mapper.readValue(is, CardDataWrapper.class);
        this.cards.addAll(wrapper.getCards());
    }
    private void loadRecipes(String path) throws Exception {
        InputStream is = getClass().getResourceAsStream(path);
        JsonNode root = mapper.readTree(is);

        JsonNode recipeNodes = root.path("list");
        List<Recipe> loaded = mapper.convertValue(recipeNodes, new TypeReference<List<Recipe>>() {});
        this.recipes.addAll(loaded);
    }
    private void loadSkills(String path) throws Exception {
        InputStream is = getClass().getResourceAsStream(path);
        JsonNode root = mapper.readTree(is);
        // Matches your JSON path: deck -> cards
        JsonNode skillNodes = root.path("deck").path("cards");
        List<Skill> loadedSkills = mapper.convertValue(skillNodes, new TypeReference<List<Skill>>() {});
        this.skills.addAll(loadedSkills);
    }
    private void loadEvolutions(String path) throws Exception {
        InputStream is = getClass().getResourceAsStream(path);
        JsonNode root = mapper.readTree(is);
        JsonNode evolveNodes = root.path("evolve");
        List<Evolution> loadedEvos = mapper.convertValue(evolveNodes, new TypeReference<List<Evolution>>() {});
        this.evolutions.addAll(loadedEvos);
    }

    public Card getCardById(int id) {
        return cards.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
    }
    public List<Card> getCards() { return cards; }
    public List<Skill> getSkills() { return skills; }
    public List<Recipe> getRecipes() { return recipes; }
    public List<Evolution> getEvolutions() { return evolutions; }
}
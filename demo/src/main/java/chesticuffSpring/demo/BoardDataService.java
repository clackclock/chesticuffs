package chesticuffSpring.demo;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class BoardDataService {

    // Loaded Card Data
    private final List<Card> cardPack;

    // Loaded Modifier Data
    private final JsonNode modifierData;

    // Loaded Evolution Data
    private final JsonNode evolutionData;

    // --- Data loading moves into the constructor ---
    public BoardDataService() throws IOException {
        System.out.println("Initializing Board Data Service via Constructor...");

        // ObjectMapper for parsing
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // 1. Load Cards (card_image.json)
            File cardDataFile = new ClassPathResource("static/card_image.json").getFile();
            CardDataWrapper cardDataWrapper = objectMapper.readValue(cardDataFile, CardDataWrapper.class);
            this.cardPack = cardDataWrapper.getCards();

            // 2. Load Modifiers (Modifier.json)
            File modFile = new ClassPathResource("static/Modifier.json").getFile();
            this.modifierData = objectMapper.readTree(modFile);

            // 3. Load Evolutions (Evolutions.json)
            File evolveFile = new ClassPathResource("static/Evolutions.json").getFile();
            this.evolutionData = objectMapper.readTree(evolveFile);

            System.out.println("Board Data Service initialization complete.");

        } catch (IOException e) {
            System.err.println("FATAL ERROR: Could not load static JSON files during service creation.");
            e.printStackTrace();
            // Re-throw the exception to prevent the Spring context from loading a broken service
            throw e;
        }
    }

    // --- Getters for the data (unchanged) ---
    public JsonNode getModifierData() {
        return modifierData;
    }

    public JsonNode getEvolutionData() {
        return evolutionData;
    }

    // Utility to get a Card by ID
    public Card getCardById(int id) {
        return cardPack.stream()
                .filter(card -> card.getId() == id)
                .findFirst()
                .orElse(null);
    }
}
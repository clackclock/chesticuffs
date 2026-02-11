package chesticuffSpring.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class CardService {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<Card> getCards() {
        try {
            // 1. Load the file from the resources/static folder
            InputStream is = new ClassPathResource("static/card_image.json").getInputStream();

            // 2. Map the entire JSON structure to Wrapper class
            CardDataWrapper wrapper = objectMapper.readValue(is, CardDataWrapper.class);

            // 3. Return only the list of cards from the wrapper
            // If cards is null (empty file), return an empty list to avoid crashes
            return (wrapper.getCards() != null) ? wrapper.getCards() : new ArrayList<>();

        } catch (IOException e) {
            // Log the error and return an empty list so the website still loads
            System.err.println("Error reading card_image.json: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
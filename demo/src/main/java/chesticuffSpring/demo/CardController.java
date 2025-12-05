package chesticuffSpring.demo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.File;
import java.io.IOException;
import java.util.List;

// (Updated)
@Controller
public class CardController {

    @GetMapping("/cardBox")
    public String displayCards(Model model) {

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            File jsonFile = new ClassPathResource("static/card_image.json").getFile();

            // 1. Read the JSON into the wrapper object first.
            CardDataWrapper wrapper = objectMapper.readValue(jsonFile, CardDataWrapper.class);

            // 2. Get the List<Card> from the wrapper object.
            List<Card> cardData = wrapper.getCards();

            // 3. Add the list to the Spring Model
            model.addAttribute("cards", cardData);

            // 4. Return the name of the Thymeleaf template
            return "cardList";

        } catch (IOException e) {
            e.printStackTrace();
            return "errorPage";
        }
    }
}
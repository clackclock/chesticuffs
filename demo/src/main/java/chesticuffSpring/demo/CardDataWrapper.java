package chesticuffSpring.demo;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class CardDataWrapper {
    private String title;
    private double version;

    // Use @JsonProperty to map the JSON key "cardList" to this field
    @JsonProperty("cardList")
    private List<Card> cards;

    // Default constructor (required by Jackson)
    public CardDataWrapper() {}

    // Getters for the fields (required by Jackson to deserialize)
    public List<Card> getCards() { return cards; }
    public String getTitle() { return title; }
    public double getVersion() { return version; }

    // Setters (required by Jackson if not using a creator constructor)
    public void setCards(List<Card> cards) { this.cards = cards; }
    public void setTitle(String title) { this.title = title; }
    public void setVersion(double version) { this.version = version; }
}

package com.game.memory.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Card {
    private final String id;
    private final CardType type;
    private final String imageURL;
    private final BooleanProperty isFlipped;
    private final BooleanProperty isMatched;

    public Card(String id, CardType type, String imageURL) {
        this.id = id;
        this.type = type;
        this.imageURL = imageURL;
        this.isFlipped = new SimpleBooleanProperty(false);
        this.isMatched = new SimpleBooleanProperty(false);
    }

    // Getters
    public String getId() { return id; }
    public CardType getType() { return type; }
    public String getImageURL() { return imageURL; }

    public boolean isFlipped() { return isFlipped.get(); }
    public BooleanProperty flippedProperty() { return isFlipped; }

    public boolean isMatched() { return isMatched.get(); }
    public BooleanProperty matchedProperty() { return isMatched; }

    // Methods from diagram
    public void flip() {
        if (!isMatched.get()) {
            isFlipped.set(!isFlipped.get());
        }
    }

    public void match() {
        if (isFlipped.get() && !isMatched.get()) {
            isMatched.set(true);
            isFlipped.set(false); // Thẻ đã match không còn ở trạng thái flipped
        }
    }

    @Override
    public String toString() {
        return String.format("Card{id='%s', type=%s, matched=%s}", id, type, isMatched.get());
    }
}

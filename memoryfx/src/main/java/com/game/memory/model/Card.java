package com.game.memory.model;

public class Card {
    private String id;
    private CardType type;
    private String imageURL;
    private boolean isFlipped;
    private boolean isMatched;

    public Card(String id, CardType type, String imageURL) {
        this.id = id;
        this.type = type;
        this.imageURL = imageURL;
        this.isFlipped = false;
        this.isMatched = false;
    }

    public void flip() {
        if (!isMatched) {
            isFlipped = !isFlipped;
        }
    }

    public void match() {
        this.isMatched = true;
        this.isFlipped = true;
    }

    public void setMatched(boolean matched) {
        this.isMatched = matched;
    }

    public void setFlipped(boolean flipped) {
        this.isFlipped = flipped;
    }

    public String getId() { return id; }
    public CardType getType() { return type; }
    public String getImageURL() { return imageURL; }
    public boolean isFlipped() { return isFlipped; }
    public boolean isMatched() { return isMatched; }
}

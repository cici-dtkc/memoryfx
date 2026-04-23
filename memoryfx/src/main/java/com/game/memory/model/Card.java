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
    //lật thẻ
    public void flip() {
        if (!isMatched) {
            isFlipped = !isFlipped;
        }
    }
    // Đánh dấu thẻ đã lật
    public void match() {
        this.isMatched = true;
        this.isFlipped = true;
    }

    public String getId() { return id; }
    public CardType getType() { return type; }
    public String getImageURL() { return imageURL; }
    public boolean isFlipped() { return isFlipped; }
    public boolean isMatched() { return isMatched; }
    public void setFlipped(boolean flipped) { isFlipped = flipped; }

}

package com.game.memory.model;

import javafx.beans.property.*;
import java.util.ArrayList;
import java.util.List;

public class GameState {
    private final ObjectProperty<Card[]> cards;
    private final List<Card> flippedCards;
    private final IntegerProperty matchedPairs;
    private final int totalPairs;
    private final IntegerProperty moves;
    private final IntegerProperty elapsedTime;
    private final ObjectProperty<GameStatus> status;

    public GameState(int totalPairs) {
        this.cards = new SimpleObjectProperty<>(new Card[0]);
        this.flippedCards = new ArrayList<>(2);
        this.matchedPairs = new SimpleIntegerProperty(0);
        this.totalPairs = totalPairs;
        this.moves = new SimpleIntegerProperty(0);
        this.elapsedTime = new SimpleIntegerProperty(0);
        this.status = new SimpleObjectProperty<>(GameStatus.IDLE);
    }

    public Card[] getCards() { return cards.get(); }
    public ObjectProperty<Card[]> cardsProperty() { return cards; }
    public void setCards(Card[] cards) { this.cards.set(cards); }

    public List<Card> getFlippedCards() { return flippedCards; }

    public int getMatchedPairs() { return matchedPairs.get(); }
    public IntegerProperty matchedPairsProperty() { return matchedPairs; }
    public void setMatchedPairs(int matchedPairs) { this.matchedPairs.set(matchedPairs); }

    public int getTotalPairs() { return totalPairs; }

    public int getMoves() { return moves.get(); }
    public IntegerProperty movesProperty() { return moves; }
    public void setMoves(int moves) { this.moves.set(moves); }
    public void incrementMoves() { this.moves.set(this.moves.get() + 1); }

    public int getElapsedTime() { return elapsedTime.get(); }
    public IntegerProperty elapsedTimeProperty() { return elapsedTime; }
    public void setElapsedTime(int elapsedTime) { this.elapsedTime.set(elapsedTime); }

    public GameStatus getStatus() { return status.get(); }
    public ObjectProperty<GameStatus> statusProperty() { return status; }
    public void setStatus(GameStatus status) { this.status.set(status); }

    // Business methods from diagram
    public boolean isComplete() {
        return matchedPairs.get() == totalPairs;
    }

    public int getScore() {

        if (totalPairs == 0) return 0;
        int baseScore = matchedPairs.get() * 100;
        int movePenalty = moves.get() * 5; // Penalty nhẹ cho mỗi nước đi
        int timeBonus = Math.max(0, (60 - elapsedTime.get()) * 2);
        return Math.max(0, baseScore - movePenalty + timeBonus);
    }

    public void reset() {
        flippedCards.clear();
        matchedPairs.set(0);
        moves.set(0);
        elapsedTime.set(0);
        status.set(GameStatus.IDLE);
    }

    public void addFlippedCard(Card card) {
        if (flippedCards.size() < 2 && !flippedCards.contains(card)) {
            flippedCards.add(card);
        }
    }

    public void clearFlippedCards() {
        flippedCards.clear();
    }
}

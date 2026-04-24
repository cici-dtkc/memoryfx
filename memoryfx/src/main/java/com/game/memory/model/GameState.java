package com.game.memory.model;
import com.game.memory.model.GameStatus;


import java.util.*;

public class GameState {
    private List<Cards> cards;
    private List<Cards> flippedCards;
    private int matchedPairs;
    private int totalPairs;
    private int moves;
    private long elapsedTime;
    private GameStatus status;
    public GameState(List<Cards> cards) {
        this.cards = cards;
        this.flippedCards = new ArrayList<>();
        this.totalPairs = cards.size() / 2;
        this.matchedPairs = 0;
        this.moves = 0;
        this.elapsedTime = 0;
        this.status = GameStatus.PLAYING;
    }
    //  Kiểm tra game hoàn thành
    public boolean isComplete() {
        return matchedPairs == totalPairs;
    }

    //  Tính điểm theo SRS
    public int getScore() {
        int wrongAttempts = moves - matchedPairs;
        int score = (matchedPairs * 100) - (wrongAttempts * 10) - (int)(elapsedTime * 5);
        return Math.max(0, score);
    }

    //  Reset game
    public void reset() {
        for (Cards card : cards) {
            card.setFlipped(false);
            card.setMatched(false);
        }
        flippedCards.clear();
        matchedPairs = 0;
        moves = 0;
        elapsedTime = 0;
        status = GameStatus.PLAYING;
    }

    //  Thêm thẻ đang lật
    public void addFlippedCard(Cards card) {
        if (flippedCards.size() < 2 && !flippedCards.contains(card)) {
            flippedCards.add(card);
        }
    }

    //  Xóa danh sách thẻ đang lật
    public void clearFlippedCards() {
        flippedCards.clear();
    }

    //  Kiểm tra match
    public boolean checkMatch() {
        if (flippedCards.size() < 2) return false;

        Cards c1 = flippedCards.get(0);
        Cards c2 = flippedCards.get(1);

        if (c1.getType() == c2.getType()) {
            c1.match();
            c2.match();
            matchedPairs++;
            return true;
        } else {
            c1.setFlipped(false);
            c2.setFlipped(false);
            return false;
        }
    }

    public List<Cards> getCards() { return cards; }
    public List<Cards> getFlippedCards() { return flippedCards; }
    public int getMoves() { return moves; }
    public int getMatchedPairs() { return matchedPairs; }
    public long getElapsedTime() { return elapsedTime; }
    public GameStatus getStatus() { return status; }

    public void setStatus(GameStatus status) { this.status = status; }
    public void increaseMoves() { this.moves++; }
    public void setElapsedTime(long elapsedTime) { this.elapsedTime = elapsedTime; }
}

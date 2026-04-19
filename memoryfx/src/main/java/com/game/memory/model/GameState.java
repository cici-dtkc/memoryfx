import java.util.*;

public class GameState {
    private List<Card> card;
    private List<Card> flippedCards;
    private int matchedPairs;
    private int totalPairs;
    private int moves;
    private int elapsedTime;
    private GameStatus status;

    public GameState(List<Card> card) {
        this.card = card;
        this.flippedCards = new ArrayList<>();
        this.totalPairs = cards.size() / 2;
        this.matchedPairs = 0;
        this.moves = 0;
        this.elapsedTime = 0;
        this.status = GameStatus.PLAYING;
    }

    public boolean isComplete() {
        return matchedPairs == totalPairs;
    }

    public int getScore() {
        int wrongAttempts = moves - matchedPairs;
        return (matchedPairs * 100) - (wrongAttempts * 10) + (elapsedTime * 5);
    }

    public void reset() {
        matchedPairs = 0;
        moves = 0;
        elapsedTime = 0;
        status = GameStatus.PLAYING;
        flippedCards.clear();
        for (Card c : card) {
            // reset trạng thái
        }
    }

    // Getter
    public List<Card> getCard() { return card; }
    public List<Card> getFlippedCards() { return flippedCards; }
}

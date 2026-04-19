package com.game.memory.model;

public class ScoreRecord {
    private String playerName;
    private int score;
    private int moves;
    private long time;

    public ScoreRecord(String playerName, int score, int moves, long time) {
        this.playerName = playerName;
        this.score = score;
        this.moves = moves;
        this.time = time;
    }

    public int getScore() { return score; }
}

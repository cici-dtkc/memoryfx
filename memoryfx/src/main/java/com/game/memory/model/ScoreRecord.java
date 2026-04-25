package com.game.memory.model;

import java.time.Instant;

public record ScoreRecord(String playerName, int score, int moves, long time, Instant timestamp) {

    public ScoreRecord {
        if (playerName == null || playerName.isBlank()) {
            playerName = "Anonymous";
        }
        if (score < 0) throw new IllegalArgumentException("Score cannot be negative");
        if (moves < 0) throw new IllegalArgumentException("Moves cannot be negative");
        if (time < 0) throw new IllegalArgumentException("Time cannot be negative");
        if (timestamp == null) timestamp = Instant.now();
    }

    public static ScoreRecord of(String playerName, int score, int moves, long time) {
        return new ScoreRecord(playerName, score, moves, time, Instant.now());
    }
}

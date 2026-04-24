package com.game.memory.model;

import com.google.gson.Gson;
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
    //  Convert sang JSON
    public String toJSON() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    //  Parse từ JSON
    public static ScoreRecord fromJSON(String data) {
        Gson gson = new Gson();
        return gson.fromJson(data, ScoreRecord.class);
    }

    public String getPlayerName() { return playerName; }
    public int getScore() { return score; }
    public int getMoves() { return moves; }
    public long getTime() { return time; }
}

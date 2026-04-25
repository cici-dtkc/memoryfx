package com.game.memory.model;

public enum Difficulty {
    EASY("DỄ", "EASY", 4),
    MEDIUM("VỪA", "MEDIUM", 6),
    HARD("KHÓ", "HARD", 8);

    private final String displayVietnamese;
    private final String displayEnglish;
    private final int gridSize;

    Difficulty(
        String displayVietnamese,
        String displayEnglish,
        int gridSize
    ) {
        this.displayVietnamese = displayVietnamese;
        this.displayEnglish = displayEnglish;
        this.gridSize = gridSize;
    }

    public String getDisplayVietnamese() {
        return displayVietnamese;
    }

    public String getDisplayEnglish() {
        return displayEnglish;
    }

    public int getGridSize() {
        return gridSize;
    }
}

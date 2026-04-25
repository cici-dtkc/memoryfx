package com.game.memory.model;

import java.util.List;

public interface ScoreStorage {
    void save(List<ScoreRecord> records) throws Exception;
    List<ScoreRecord> load() throws Exception;
}

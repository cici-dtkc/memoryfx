package com.game.memory.model;

import com.game.memory.model.ScoreRecord;

import java.util.*;
import java.io.*;
public class ScoreStorage {

    private static final String FILE_PATH = "scores.json";

    //  Lưu danh sách score
    public void save(List<ScoreRecord> records) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (ScoreRecord record : records) {
                writer.write(record.toJSON());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //  Load danh sách score
    public List<ScoreRecord> load() {
        List<ScoreRecord> records = new ArrayList<>();

        File file = new File(FILE_PATH);
        if (!file.exists()) return records;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                records.add(ScoreRecord.fromJSON(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return records;
    }
}

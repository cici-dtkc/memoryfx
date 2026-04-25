package com.game.memory.model;

public enum GameStatus {
    IDLE,       // Chưa bắt đầu
    PLAYING,    // Đang chơi
    CHECKING,   // Đang kiểm tra cặp thẻ
    WON,        // Thắng
    LOST;       // Thua (hết giờ)
}

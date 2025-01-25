package com.game.cards.dto;

import java.time.LocalDateTime;

public class PlayerDataResponseDTO {

    private Long id;
    private int numberPlayer;
    private int score;
    private LocalDateTime createdAt;

    public PlayerDataResponseDTO() {
    }

    public PlayerDataResponseDTO(Long id, int numberPlayer, int score, Boolean winner, Boolean isTie,
                                 LocalDateTime createdAt) {
        this.id = id;
        this.numberPlayer = numberPlayer;
        this.score = score;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumberPlayer() {
        return numberPlayer;
    }

    public void setNumberPlayer(int numberPlayer) {
        this.numberPlayer = numberPlayer;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }


}

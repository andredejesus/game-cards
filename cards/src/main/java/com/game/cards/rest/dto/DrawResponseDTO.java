package com.game.cards.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class DrawResponseDTO {

    private boolean success;

    @JsonProperty("deck_id")
    private String deckId;

    private List<CardDTO> cards;

    private int remaining;

    public DrawResponseDTO(boolean success, String deckId, List<CardDTO> cards, int remaining) {
        this.success = success;
        this.deckId = deckId;
        this.cards = cards;
        this.remaining = remaining;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getDeckId() {
        return deckId;
    }

    public void setDeckId(String deckId) {
        this.deckId = deckId;
    }

    public List<CardDTO> getCards() {
        return cards;
    }

    public void setCards(List<CardDTO> cards) {
        this.cards = cards;
    }

    public int getRemaining() {
        return remaining;
    }

    public void setRemaining(int remaining) {
        this.remaining = remaining;
    }

    @Override
    public String toString() {
        return "DrawResponseDTO{" +
                "success=" + success +
                ", deckId='" + deckId + '\'' +
                ", cards=" + cards +
                ", remaining=" + remaining +
                '}';
    }
}

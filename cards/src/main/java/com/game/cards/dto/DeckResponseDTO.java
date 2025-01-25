package com.game.cards.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DeckResponseDTO {

    private boolean success;

    @JsonProperty("deck_id")
    private String deckId;

    private boolean shuffled;

    private int remaining;

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

    public boolean isShuffled() {
        return shuffled;
    }

    public void setShuffled(boolean shuffled) {
        this.shuffled = shuffled;
    }

    public int getRemaining() {
        return remaining;
    }

    public void setRemaining(int remaining) {
        this.remaining = remaining;
    }

    @Override
    public String toString() {
        return "DeckResponseDTO{" +
                "success=" + success +
                ", deckId='" + deckId + '\'' +
                ", shuffled=" + shuffled +
                ", remaining=" + remaining +
                '}';
    }
}

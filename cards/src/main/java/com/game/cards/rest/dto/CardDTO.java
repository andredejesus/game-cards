package com.game.cards.rest.dto;

import java.util.List;

public class CardDTO {

    private String code;

    private String image;

    private String value;

    private String suit;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public CardDTO(String code, String image, String value, String suit) {
        this.code = code;
        this.image = image;
        this.value = value;
        this.suit = suit;
    }

}

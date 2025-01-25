package com.game.cards.dto;

import java.util.List;

public class CardDTO {

    private String code;

    private String image;

    private List<ImageDTO> images;

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

    public List<ImageDTO> getImages() {
        return images;
    }

    public void setImages(List<ImageDTO> images) {
        this.images = images;
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

    @Override
    public String toString() {
        return "CardDTO{" +
                "code='" + code + '\'' +
                ", image='" + image + '\'' +
                ", images=" + images +
                ", value='" + value + '\'' +
                ", suit='" + suit + '\'' +
                '}';
    }
}

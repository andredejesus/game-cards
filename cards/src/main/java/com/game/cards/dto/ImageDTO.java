package com.game.cards.dto;

public class ImageDTO {

    private String svg;

    private String png;

    public String getSvg() {
        return svg;
    }

    public void setSvg(String svg) {
        this.svg = svg;
    }

    public String getPng() {
        return png;
    }

    public void setPng(String png) {
        this.png = png;
    }

    @Override
    public String toString() {
        return "Images{" +
                "svg='" + svg + '\'' +
                ", png='" + png + '\'' +
                '}';
    }

}

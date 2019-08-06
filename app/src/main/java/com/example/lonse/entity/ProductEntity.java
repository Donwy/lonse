package com.example.lonse.entity;

/**
 * Created by Donvy_y on 2019/8/5
 */
public class ProductEntity {
    private int img;
    private String title;

    public ProductEntity(int img, String title) {
        this.img = img;
        this.title = title;
    }

    public int getImg() {
        return img;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "ProductEntity{" +
                "img=" + img +
                ", title='" + title + '\'' +
                '}';
    }
}

package com.example.e_app.entity;

import java.io.Serializable;

public class Product implements Serializable {

    private int id;
    private String name;
    private float price;
    private String description;
    private String image;
    private int qty;
    private int cat_id;

    private String created_at;
    private String modified_at;

    public Product() {
    }

    public Product(String name, float price, String description, String image, int qty, int cat_id) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.image = image;
        this.qty = qty;
        this.cat_id = cat_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getCat_id() {
        return cat_id;
    }

    public void setCat_id(int cat_id) {
        this.cat_id = cat_id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getModified_at() {
        return modified_at;
    }

    public void setModified_at(String modified_at) {
        this.modified_at = modified_at;
    }

    @Override
    public String toString() {
        return "\nname= " + name + '\'' +
                "\nprice=" + price ;

    }
}

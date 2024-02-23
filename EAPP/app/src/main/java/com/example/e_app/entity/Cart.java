package com.example.e_app.entity;

public class Cart {
    private int u_id;
    private int p_id;
    private int qty;
    private float price;
    private float total;

    public Cart(int u_id, int p_id, int qty, float price, float total) {
        this.u_id = u_id;
        this.p_id = p_id;
        this.qty = qty;
        this.price = price;
        this.total = total;
    }
    public Cart()
    {}


    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    public int getP_id() {
        return p_id;
    }

    public void setP_id(int p_id) {
        this.p_id = p_id;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
}
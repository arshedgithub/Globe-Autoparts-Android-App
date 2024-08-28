package com.example.globemotors;

import com.google.gson.annotations.SerializedName;

public class Product {

    private int id;
    private String name;
    private String description;
    private String photo;
    private String review;
    private String offer;
    private float cost;
    private float price;
    private Integer stock;
    private Integer stockLimitMin;
    private Integer stockLimitMax;

    @SerializedName("product_brand")
    private Brand brand;

    @SerializedName("product_category")
    private Category category;

    @SerializedName("product_subcategory")
    private Subcategory subcategory;

    @SerializedName("product_origin")
    private Origin origin;

    @SerializedName("product_vehicle")
    private Vehicle vehicle;

    @SerializedName("product_use_status")
    private UseStatus useStatus;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getOffer() {
        return offer;
    }

    public void setOffer(String offer) {
        this.offer = offer;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getStockLimitMin() {
        return stockLimitMin;
    }

    public void setStockLimitMin(Integer stockLimitMin) {
        this.stockLimitMin = stockLimitMin;
    }

    public Integer getStockLimitMax() {
        return stockLimitMax;
    }

    public void setStockLimitMax(Integer stockLimitMax) {
        this.stockLimitMax = stockLimitMax;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Subcategory getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(Subcategory subcategory) {
        this.subcategory = subcategory;
    }

    public Origin getOrigin() {
        return origin;
    }

    public void setOrigin(Origin origin) {
        this.origin = origin;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public UseStatus getUseStatus() {
        return useStatus;
    }

    public void setUseStatus(UseStatus useStatus) {
        this.useStatus = useStatus;
    }

    public static class Brand {
        private int id;
        private String name;

    }

    public static class Category {
        private int id;
        private String name;

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
    }

    public static class Subcategory {
        private int id;
        private String name;
        private int categoryId;

    }

    public static class Origin {
        private int id;
        private String name;

    }

    public static class Vehicle {
        private int id;
        private String name;

    }

    public static class UseStatus {
        private int id;
        private String name;

    }
}

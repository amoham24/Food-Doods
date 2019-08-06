/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author MrAye
 */
public class Item implements Serializable {

    private String itemCode;
    private String itemName;
    private String category;
    private String description;
    private int rating;
    private String imageURL;

    public Item() {
    }

    public Item(String itemCode, String itemName, String category, String description, int rating, String imageURL) {
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.category = category;
        this.description = description;
        this.rating = rating;
        this.imageURL = imageURL;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import database.UserItemDB;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author MrAye
 */
public class UserProfile implements Serializable {

    private User user;

    public UserProfile() {
    }

    public UserProfile(User user) {
        this.user = user;
    }

    public void addItem(UserItem userItem) {
        UserItemDB.addUserItem(userItem.getItem().getItemCode(), this.user.getUserID());
    }

    public void removeItem(UserItem item) {
        UserItemDB.removeItem(item.getItem().getItemCode(), this.getUser().getUserID());
    }

    public void updateItem(UserItem userItem) {
        if (!isThere(userItem.getItem())) {
            //not there sooo....
        } else {
            UserItemDB.addItemRating(userItem.getItem().getItemCode(), this.user.getUserID(), userItem.getRating());
            UserItemDB.addMadeIt(userItem.getItem().getItemCode(), this.user.getUserID(), userItem.isMadeIt());

        }
    }

    public ArrayList<UserItem> getItems() {
        return UserItemDB.getItemsFromUser(this.user.getUserID());
    }

    public UserItem getUserItemByID(String id) {
        for (UserItem i : UserItemDB.getItemsFromUser(this.user.getUserID())) {
            if (i.getItem().getItemCode().equals(id)) {
                return i;
            }
        }
        return null;
    }

    public boolean isThere(Item item) {
        return UserItemDB.isThere(item.getItemCode(), this.user.getUserID());
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.*;
import model.Item;
import java.util.ArrayList;

/**
 *
 * @author MrAye
 */
public class ItemDB {

    static ArrayList<Item> items = new ArrayList<>();

    public static Item getItemById(String itemCode) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM Item "
                + "WHERE ItemCode = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, itemCode);
            rs = ps.executeQuery();
            Item item = null;
            if (rs.next()) {
                item = new Item();
                item.setItemCode(rs.getString("ItemCode"));
                item.setItemName(rs.getString("ItemName"));
                item.setCategory(rs.getString("Category"));
                item.setDescription(rs.getString("Description"));
                item.setRating(rs.getInt("Rating"));
                item.setImageURL(rs.getString("ImageURL"));

            }
            return item;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static ArrayList<Item> getItems() {
        // add code that returns an ArrayList<User> object of all users in the User table
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM Item";
        ArrayList<Item> list = new ArrayList<Item>();
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            Item item = null;
            while (rs.next()) {
                item = new Item();
                item.setItemCode(rs.getString("ItemCode"));
                item.setItemName(rs.getString("ItemName"));
                item.setCategory(rs.getString("Category"));
                item.setDescription(rs.getString("Description"));
                item.setRating(rs.getInt("Rating"));
                item.setImageURL(rs.getString("ImageURL"));
                list.add(item);
            }

        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        return list;
    }

    public static ArrayList<Item> getItemByCategory(String category) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM Item "
                + "WHERE Category = ?";
        ArrayList<Item> list = new ArrayList<Item>();
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, category);
            rs = ps.executeQuery();
            Item item = null;
            while (rs.next()) {
                item = new Item();
                item.setItemCode(rs.getString("ItemCode"));
                item.setItemName(rs.getString("ItemName"));
                item.setCategory(rs.getString("Category"));
                item.setDescription(rs.getString("Description"));
                item.setRating(rs.getInt("Rating"));
                item.setImageURL(rs.getString("ImageURL"));
                list.add(item);
            }

        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        return list;
    }

}

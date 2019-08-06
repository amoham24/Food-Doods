/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.*;
import java.util.ArrayList;

import model.UserItem;

/**
 *
 * @author MrAye
 */
public class UserItemDB {

    public static void addItemRating(String itemCode, String userID, int rating) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "UPDATE UserItem "
                + "SET Rating = ? "
                + "WHERE ItemCode = ? AND UserID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, rating);
            ps.setString(2, itemCode);
            ps.setString(3, userID);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static void addMadeIt(String itemCode, String userID, boolean madeIt) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "UPDATE UserItem "
                + "SET MadeIt = ? "
                + "WHERE ItemCode = ? AND UserID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setBoolean(1, madeIt);
            ps.setString(2, itemCode);
            ps.setString(3, userID);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }

    }

    public static boolean isThere(String itemCode, String userID) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM UserItem "
                + "WHERE ItemCode = ? AND UserID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, itemCode);
            ps.setString(2, userID);
            rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static void addUserItem(String itemCode, String userID) {
        if (!isThere(itemCode, userID)) {
            ConnectionPool pool = ConnectionPool.getInstance();
            Connection connection = pool.getConnection();
            PreparedStatement ps = null;

            String query = "INSERT INTO UserItem (ItemCode, UserID, Rating, MadeIt) "
                    + "VALUES ( ?, ?, 0, false)";
            try {
                ps = connection.prepareStatement(query);
                ps.setString(1, itemCode);
                ps.setString(2, userID);
                ps.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e);
            } finally {
                DBUtil.closePreparedStatement(ps);
                pool.freeConnection(connection);
            }

        }
    }

    public static ArrayList<UserItem> getItemsFromUser(String userID) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM UserItem "
                + "WHERE UserID = ?";
        ArrayList<UserItem> list = new ArrayList<UserItem>();
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, userID);
            rs = ps.executeQuery();
            UserItem item = null;
            while (rs.next()) {
                item = new UserItem();
                item.setItem(ItemDB.getItemById(rs.getString("ItemCode")));
                item.setMadeIt(rs.getBoolean("MadeIt"));
                item.setRating(rs.getInt("Rating"));
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

    public static void removeItem(String itemCode, String userID) {
        if (isThere(itemCode, userID)) {
            ConnectionPool pool = ConnectionPool.getInstance();
            Connection connection = pool.getConnection();
            PreparedStatement ps = null;

            String query = "DELETE FROM UserItem "
                    + "WHERE ItemCode = ? AND UserID = ?";
            try {
                ps = connection.prepareStatement(query);
                ps.setString(1, itemCode);
                ps.setString(2, userID);
                ps.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e);
            } finally {
                DBUtil.closePreparedStatement(ps);
                pool.freeConnection(connection);
            }

        }
    }
}

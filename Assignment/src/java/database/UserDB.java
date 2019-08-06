/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.security.*;
import java.math.BigInteger;
import java.sql.*;
import java.util.ArrayList;
import model.User;
import model.UserProfile;

/**
 *
 * @author MrAye
 */
public class UserDB {

    public static User getUser(String userID) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM User "
                + "WHERE UserID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, userID);
            rs = ps.executeQuery();
            User user = null;
            if (rs.next()) {
                user = new User();
                user.setUserID(rs.getString("UserID"));
                user.setFirstName(rs.getString("FirstName"));
                user.setLastName(rs.getString("LastName"));
                user.setEmail(rs.getString("Email"));
                user.setPassword(rs.getString("Password"));
            }
            return user;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static ArrayList<User> getUsers() {
        // add code that returns an ArrayList<User> object of all users in the User table
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM User";
        ArrayList<User> list = new ArrayList<User>();
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            User user = null;
            while (rs.next()) {
                user = new User();
                user.setUserID(rs.getString("UserID"));
                user.setFirstName(rs.getString("FirstName"));
                user.setLastName(rs.getString("LastName"));
                user.setEmail(rs.getString("Email"));
                user.setPassword(rs.getString("Password"));
                list.add(user);
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

    public static boolean verifyLogIn(String userID, String password) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM User "
                + "WHERE UserID = ? AND Password = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, userID);
            ps.setString(2, getMd5(password));
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

    public static void addUser(String id, String firstName, String lastName, String email, String password) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "INSERT INTO User (UserID, FirstName, LastName, Email, Password) "
                + "VALUES ( ?, ?, ?, ?, ?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, id);
            ps.setString(2, firstName);
            ps.setString(3, lastName);
            ps.setString(4, email);
            ps.setString(5, getMd5(password));
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

//using MD5 hashing courtesy of Geeks for Geeks
    public static String getMd5(String input) {
        try {
            // Static getInstance method is called with hashing MD5 
            MessageDigest md = MessageDigest.getInstance("MD5");

            // digest() method is called to calculate message digest 
            //  of an input digest() return array of byte 
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array into signum representation 
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value 
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } // For specifying wrong message digest algorithms 
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}

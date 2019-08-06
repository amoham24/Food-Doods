/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import database.ItemDB;
import database.UserDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;
import model.UserItem;
import model.UserProfile;
import org.owasp.esapi.*;
import org.owasp.esapi.errors.*;

/**
 *
 * @author MrAye
 */
public class ProfileController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
//        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet ProfileController</title>");
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet ProfileController at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String param = request.getParameter("action");
        if (param != null && (param.equals("save") || param.equals("updateProfile") || param.equals("updateRating") || param.equals("updateFlag") || param.equals("signout") || param.equals("deleteItem"))) {
            if (param.equals("signout")) {
                //clear the session and dispatch to the home JSP view

            } else {
                String[] list = request.getParameterValues("itemList");
                if (false) {
                    getServletContext().getRequestDispatcher("/myItems.jsp").forward(request, response);
                } else {
                    String itemCode = request.getParameter("itemCode");
                    if (itemCode == null || ItemDB.getItemById(itemCode) == null) {
                        getServletContext().getRequestDispatcher("/myItems.jsp").forward(request, response);
                    } else {
                        UserProfile userProfile = (UserProfile) request.getSession().getAttribute("userProfile");
                        switch (param) {
                            case "save":
                                UserItem newItem = new UserItem(ItemDB.getItemById(itemCode), 0, false);
                                userProfile.addItem(newItem);
                                request.getSession().setAttribute("userProfile", userProfile);
                                getServletContext().getRequestDispatcher("/myItems.jsp").forward(request, response);
                                break;
                            case "updateProfile":
                                if (!userProfile.isThere(ItemDB.getItemById(itemCode))) {
                                    UserItem newItemRate = new UserItem(ItemDB.getItemById(itemCode), 0, false);
                                    userProfile.addItem(newItemRate);
                                    request.getSession().setAttribute("userProfile", userProfile);
                                }
                                 {
                                    UserItem theItem = userProfile.getUserItemByID(itemCode);
                                    request.getSession().setAttribute("theItem", theItem);
                                    getServletContext().getRequestDispatcher("/feedback.jsp").forward(request, response);
                                }
                                break;
                            case "updateRating":
                                String rating = request.getParameter("rating");
                                UserItem daItem = (UserItem) request.getSession().getAttribute("theItem");

                                if (rating.equals("0") || rating.equals("1") || rating.equals("2") || rating.equals("3") || rating.equals("4") || rating.equals("5")) {
                                    daItem.setRating(Integer.parseInt(rating));
                                    String madeIt = request.getParameter("isMade");
                                    if (madeIt == null) {
                                        daItem.setMadeIt(false);
                                    } else {
                                        daItem.setMadeIt(true);
                                    }
                                    userProfile.updateItem(daItem);
                                    request.getSession().setAttribute("userProfile", userProfile);

                                    getServletContext().getRequestDispatcher("/myItems.jsp").forward(request, response);
                                } else {
                                    getServletContext().getRequestDispatcher("/myItems.jsp").forward(request, response);
                                }
                                break;
                            case "updateFlag":
                                String flag = request.getParameter("flag");
                                if (flag.equals(true) || flag.equals(false)) {
                                    userProfile.getUserItemByID(itemCode).setMadeIt(Boolean.parseBoolean(flag));
                                    request.getSession().setAttribute("userProfile", userProfile);
                                }
                                getServletContext().getRequestDispatcher("/myItems.jsp").forward(request, response);
                                break;
                            case "deleteItem":
                                if (userProfile.getUserItemByID(itemCode) != null) {
                                    userProfile.removeItem(userProfile.getUserItemByID(itemCode));
                                    request.getSession().setAttribute("userProfile", userProfile);
                                }
                                getServletContext().getRequestDispatcher("/myItems.jsp").forward(request, response);
                                break;
                        }
                    }
                }
            }

        } else {
            getServletContext().getRequestDispatcher("/myItems.jsp").forward(request, response);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null && action.equals("register")) {
            if (request.getParameter("username") == null || request.getParameter("password") == null || request.getParameter("email") == null || request.getParameter("firstname") == null || request.getParameter("lastname") == null
                    || request.getParameter("username").equals("") || request.getParameter("password").equals("") || request.getParameter("email").equals("") || request.getParameter("firstname").equals("") || request.getParameter("lastname").equals("")) {
                //send message to please enter username or password
                request.setAttribute("regMessage", "Please enter a propper values");
                getServletContext().getRequestDispatcher("/register.jsp").forward(request, response);
            } else {
                String username = null;
                String password = null;
                String email = null;
                String firstName = null;
                String lastName = null;
                try {
                    username = ESAPI.validator().getValidInput("username", request.getParameter("username"), "SafeString", 200, false);
                    password = ESAPI.validator().getValidInput("userpass", request.getParameter("password"), "SafeString", 200, false);
                    email = ESAPI.validator().getValidInput("email", request.getParameter("email"), "Email", 200, false);
                    firstName = ESAPI.validator().getValidInput("firstname", request.getParameter("firstname"), "SafeString", 200, false);
                    lastName = ESAPI.validator().getValidInput("lastname", request.getParameter("lastname"), "SafeString", 200, false);
                } catch (ValidationException ex) {
                    Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IntrusionException ex) {
                    Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
                }

                if (username == null || password == null || email == null || firstName == null || lastName == null) {
                    //send message to please enter username or password
                    String wrong = "";
                    if (username == null) {
                        wrong += "USERNAME ";
                    }
                    if (password == null) {
                        wrong += "PASSWORD ";
                    }
                    if (email == null) {
                        wrong += "EMAIL ";
                    }
                    if (firstName == null) {
                        wrong += "FIRSTNAME ";
                    }
                    if (lastName == null) {
                        wrong += "LASTNAME ";
                    }
                    request.setAttribute("regMessage", "Please make sure you are entering propper values for the following: " + wrong);
                    getServletContext().getRequestDispatcher("/register.jsp").forward(request, response);
                } else {
                    if (UserDB.getUser(username) != null) {
                        request.setAttribute("regMessage", "Sorry that username already exists, try a different one!");
                        getServletContext().getRequestDispatcher("/register.jsp").forward(request, response);
                    } else {
                        UserDB.addUser(username, firstName, lastName, email, password);

                        User user = UserDB.getUser(username);
                        request.getSession().setAttribute("theUser", user);

                        UserProfile profile = new UserProfile(user);
                        request.getSession().setAttribute("userProfile", profile);
                        getServletContext().getRequestDispatcher("/myItems.jsp").forward(request, response);
                    }
                }
            }

        } else if (action != null && action.equals("signIn")) {
            if (request.getParameter("username") == null || request.getParameter("password") == null || request.getParameter("username").equals("") || request.getParameter("password").equals("")) {
                //send message to please enter username or password
                request.setAttribute("logInMessage", "Please enter a propper username and password");
                getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
            } else {
                String username = null;
                String password = null;
                try {
                    username = ESAPI.validator().getValidInput("username", request.getParameter("username"), "SafeString", 200, false);
                    password = ESAPI.validator().getValidInput("userpass", request.getParameter("password"), "SafeString", 200, false);
                } catch (ValidationException ex) {
                    Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IntrusionException ex) {
                    Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
                }

                if (username == null || password == null) {
                    //send message to please enter username or password
                    request.setAttribute("logInMessage", "Please enter a propper username and password");
                    getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
                } else {
                    if (UserDB.verifyLogIn(username, password)) {
                        //loged in yay
                        User user = UserDB.getUser(username);
                        request.getSession().setAttribute("theUser", user);

                        UserProfile profile = new UserProfile(user);
                        request.getSession().setAttribute("userProfile", profile);
                        getServletContext().getRequestDispatcher("/myItems.jsp").forward(request, response);
                    } else {
                        //wrong info dude
                        request.setAttribute("logInMessage", "Your username or password is incorrect");
                        getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
                    }
                }
            }
        } else {
            processRequest(request, response);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imat;

import imat.view.CenterFlikController;
import imat.view.KategoriMenyController;
import imat.view.ToppController;
import imat.view.VarukorgController;
import java.net.URL;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import se.chalmers.ait.dat215.project.IMatDataHandler;
import se.chalmers.ait.dat215.project.ShoppingCart;

/**
 * This controller class interacts with the views' controllers, and
 * the imat backend. Thus, this controller is the connection between
 * the model and the view controllers. It takes care of what goes in
 * and out of the shopping cart.
 * 
 * @author group 11
 */
public class IMatController implements Initializable {
  
  @FXML
  private Label label;
  @FXML
  private static CenterFlikController centerController;
  @FXML
  private KategoriMenyController kategoriMenyController;
  @FXML
  private ToppController toppController;
  @FXML
  private VarukorgController varukorgController;
  @FXML
  public static IMatDataHandler imatBackend = IMatDataHandler.getInstance();
  @FXML
  private static final IMatProducts imatProducts = new IMatProducts();;
  @FXML
  private static final ShoppingCart shoppingCart = imatBackend.getShoppingCart();
  
  // The account of the currently logged in user
  private IMatUserAccount currentAccount;
 
  // Used for creating an account in the database
  private static PreparedStatement psInsert;
  
  // Used for retrieving information from the database
  private static PreparedStatement psSelect;
  
  // Used for updating information in the database
  private static PreparedStatement psUpdate;
  
  // Used when counting rows in orders table
  private static Statement select;
  
  // Used for communication with the database
  private static Connection conn;
  
  // Used to check if user is logged in
  private boolean loggedIn;
  
  // The username of the currently logged in user
  public static String currentUser;
  
  // Keeps track of the current IMat order
  public static Integer currentIMatOrderID;
  
  @Override
  public void initialize(URL url, ResourceBundle rb) {

  }
  
  /**
   * Set the username of the currently logged in user
   * 
   * @param user  the logged in user 
   */
  public static void setCurrentUser(String user) {
    currentUser = user;
  }
  
  /**
   * Retrieve the reference to the database.
   * 
   * @return  database reference 
   */
  public static Connection getConnection() {
    return conn;
  }
  
  public static ShoppingCart getShoppingCart() {
    return shoppingCart;
  }
  
  /**
   * Retrieve a reference to the iMat backend.
   * 
   * @return reference to the iMat backend
   */
  public static IMatDataHandler getIMatBackend() {
    return imatBackend;
  }
  
  /**
   * 
   * Retrieve a reference to the products.
   * 
   * @return reference to the products
   */
  public static IMatProducts getIMatProducts() {
    return imatProducts;
  }
  
  /**
   * The method creates a Connection object. Loads the embedded driver,
   * starts and connects to the database using the connection URL.
   */
  public static void createDatabaseConnection() {
    try {
      String driver = "org.apache.derby.jdbc.EmbeddedDriver";
      Class.forName(driver);
      String url = "jdbc:derby:imatDB";
      conn = DriverManager.getConnection(url);
    } catch (ClassNotFoundException | SQLException ex) {
      Logger.getLogger(IMatController.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
  /**
   * If the application is used for the first time, the database should
   * be created.
   */
  public static void createDatabase(){
    //imatBackend.resetFirstRun();   // For testing purposes only
    if (imatBackend.isFirstRun()) {
      try {
        String driver = "org.apache.derby.jdbc.EmbeddedDriver";
        Class.forName(driver);
        String url = "jdbc:derby:imatDB;create=true";
        conn = DriverManager.getConnection(url);
        Statement statement = conn.createStatement();
        DatabaseMetaData dbm = conn.getMetaData();

        // check if "UserAccount" table exists
        ResultSet tables = dbm.getTables(null, null, "USERACCOUNT", null);
        if (!tables.next()) {
          String createString = "CREATE TABLE USERACCOUNT("
          + " ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY,"
          + " USERNAME VARCHAR(30), "
          + " PASSWORD VARCHAR(30), "
          + " FIRSTNAME VARCHAR(30), "
          + " LASTNAME VARCHAR(30), "
          + " CIVIC INT, "
          + " EMAIL VARCHAR(30), "
          + " PHONE VARCHAR(15), "
          + " STREET VARCHAR(30), "
          + " POSTAL VARCHAR(10), "
          + " CITY VARCHAR(30), "
          + " CARDNUMBER VARCHAR(15), "
          + " CARDTYPE VARCHAR(15), "
          + " CARDHOLDER VARCHAR(30), "
          + " VALIDYEAR INT, "
          + " VALIDMONTH INT, "
          + " CVV INT) " ;

          statement.execute(createString);
        }
        
        // Check if "orders" table exist
        tables = dbm.getTables(null, null, "ORDERS", null);
        if (!tables.next()) {
          String createString = "CREATE TABLE ORDERS("
          + " ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY,"
          + " USERNAME VARCHAR(30), "
          + " PASSWORD VARCHAR(30), "
          + " DATE VARCHAR(30), "
          + " COST INT, "    
          + " PRODUCT1 VARCHAR(30), "
          + " UNITS1 INT, "
          + " PRODUCT2 VARCHAR(30), "
          + " UNITS2 INT, "
          + " PRODUCT3 VARCHAR(30), "
          + " UNITS3 INT, "
          + " PRODUCT4 VARCHAR(30), "
          + " UNITS4 INT, "
          + " PRODUCT5 VARCHAR(30), "
          + " UNITS5 INT, "
          + " PRODUCT6 VARCHAR(30), "
          + " UNITS6 INT, "
          + " PRODUCT7 VARCHAR(30), "
          + " UNITS7 INT, "
          + " PRODUCT8 VARCHAR(30), "
          + " UNITS8 INT, "
          + " PRODUCT9 VARCHAR(30), "
          + " UNITS9 INT, "
          + " PRODUCT10 VARCHAR(30), "
          + " UNITS10 INT, "
          + " PRODUCT11 VARCHAR(30), "
          + " UNITS11 INT, "
          + " PRODUCT12 VARCHAR(30), "
          + " UNITS12 INT, "
          + " PRODUCT13 VARCHAR(30), "
          + " UNITS13 INT, "
          + " PRODUCT14 VARCHAR(30), "
          + " UNITS14 INT) " ;

          statement.execute(createString);
        }
          
        // Check if "favorites" table exist
        tables = dbm.getTables(null, null, "FAVORITES", null);
        if (!tables.next()) {
          String createString = "CREATE TABLE FAVORITES("
          + " ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY,"
          + " USERNAME VARCHAR(30), "
          + " FAVORITE1 VARCHAR(30), "
          + " FAVORITE2 VARCHAR(30), "
          + " FAVORITE3 VARCHAR(30), "
          + " FAVORITE4 VARCHAR(30), "
          + " FAVORITE5 VARCHAR(30), "
          + " FAVORITE6 VARCHAR(30), "
          + " FAVORITE7 VARCHAR(30), "
          + " FAVORITE8 VARCHAR(30), "
          + " FAVORITE9 VARCHAR(30), "
          + " FAVORITE10 VARCHAR(30), "
          + " FAVORITE11 VARCHAR(30), "
          + " FAVORITE12 VARCHAR(30)) " ;

          statement.execute(createString);
          tables.close();
          statement.close();
          System.out.println("Database created");
        }
    } catch (ClassNotFoundException | SQLException ex) {
      Logger.getLogger(IMatController.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  }
  
  /**
   * Create a new record in the tables.
   * 
   * @param username   the username of the account
   * @param password   the password of the account
   */
  public static void createAccount(String username, String password) {
    try {
      psInsert = conn.prepareStatement("insert into USERACCOUNT(USERNAME,PASSWORD) values (?,?)");
      psInsert.setString(1, username);
      psInsert.setString(2, password);
      psInsert.execute();
      psInsert.close();
    } catch (SQLException ex) {
      Logger.getLogger(IMatController.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
    /**
   * Adds a product as a favorite in the database.
   * 
   * @param favorite  the product to be added as a favorite
   * @param index the number of the favorite product among the favorites
   */
  public static synchronized void addFavorite(String favorite, int index) {
    try {
      String updateString = "update FAVORITES set PRODUCT" + index + " = ? where USERNAME = ?";
      psUpdate = conn.prepareStatement(updateString);
      psUpdate.setString(1, favorite);
      psUpdate.setString(2, currentUser);
      psUpdate.executeUpdate();
      psUpdate.close();
    } catch (SQLException ex) {
      Logger.getLogger(IMatController.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
  /**
   * Updates given attribute with desired value.
   * 
   * @param attribute the desired attribute to update
   * @param value     the new value for the attribute
   */
  public static synchronized void updateAccount(String attribute, String value) {
    try {
      String updateString = "update USERACCOUNT set " + attribute + " = ? where USERNAME = ?";
      psUpdate = conn.prepareStatement(updateString);
      psUpdate.setString(1, value);
      psUpdate.setString(2, currentUser);
      psUpdate.executeUpdate();
      psUpdate.close();
    } catch (SQLException ex) {
      Logger.getLogger(IMatController.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
  /**
   * Removes an existing favorite product from the database.
   * 
   * @param favorite  the product to be removed
   */
  public static synchronized void removeFavorite(String favorite, int index) {
    try {
      String updateString = "update FAVORITES set FAVORITE" + index + " = ? where USERNAME = ?";
      psUpdate = conn.prepareStatement(updateString);
      psUpdate.setString(1, null);
      psUpdate.setString(2, currentUser);
      psUpdate.executeUpdate();
      psUpdate.close();
    } catch (SQLException ex) {
      Logger.getLogger(IMatController.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
  public static void addProductToIMatOrder(Integer productNr, Integer productUnits, String value, Integer totalCost) {
      try {
        productNr++;
        String updateString = "update ORDERS set PRODUCT" + productNr + " = ? where ID = ?";
        psUpdate = conn.prepareStatement(updateString);
        psUpdate.setString(1, value);
        psUpdate.setInt(2, IMatShoppingCart.cart.getOrderNumber());
        psUpdate.executeUpdate();
        updateString = "update ORDERS set UNITS" + productNr + " = ? where ID = ?";
        psUpdate = conn.prepareStatement(updateString);
        psUpdate.setInt(1, productUnits);
        psUpdate.setInt(2, IMatShoppingCart.cart.getOrderNumber());
        psUpdate.executeUpdate();
        updateString = "update ORDERS set COST = ? where ID = ?";
        psUpdate = conn.prepareStatement(updateString);
        psUpdate.setInt(1, totalCost);
        psUpdate.setInt(2, IMatShoppingCart.cart.getOrderNumber());
        psUpdate.executeUpdate();
        psUpdate.close();
      } catch (SQLException ex) {
        Logger.getLogger(IMatController.class.getName()).log(Level.SEVERE, null, ex);
      }
  }
  
  /**
   * Checks if there exists an account corresponding to the given username
   * and password combination.
   * 
   * @param username  the name of the account
   * @param password  the password of the account
   * @return          informs whether the account is valid or not 
   */
  public static String validAccount(String username, String password) {
    try {
      String selectSQL = "select USERNAME, PASSWORD from USERACCOUNT where USERNAME = ?";
      psSelect = conn.prepareStatement(selectSQL);
      psSelect.setString(1, username);
      ResultSet rs = psSelect.executeQuery();
      while (rs.next()) {
          String user = rs.getString("USERNAME");
          String pass = rs.getString("PASSWORD");
          if (user.compareTo(username) == 0) {
            if (pass.compareTo(password) == 0) {
              rs.close();
              psSelect.close();
              return "validAccount";
            }
            rs.close();
            psSelect.close();
            return "invalidPassword";
          }
      }
      rs.close();
      psSelect.close();
    } catch (SQLException ex) {
      Logger.getLogger(IMatController.class.getName()).log(Level.SEVERE, null, ex);
    }
    return "invalidUsername";
  }
  
  /**
   * Retrieves the number of records existing in a table in the database.
   * 
   * @return  the number of records 
   */
  public static int getNumberOfRecords(String table) {
    int records = 0;
    try {
      select = conn.createStatement();
      ResultSet rs = select.executeQuery("SELECT * FROM " + table);
      while (rs.next()) {
        records++;
      }
      rs.close();
      select.close();
    } catch (SQLException ex) {
      Logger.getLogger(IMatController.class.getName()).log(Level.SEVERE, null, ex);
    }
    return ++records;
  }
  
  /**
   * When the products in the shopping cart has been paid for, the receipt is
   * stored in the database as an order (order history).
   * 
   * @param imatOrder the order representing a performed payment
   */
  public static void createPaidOrder(IMatOrder imatOrder) {
    try {
      psInsert = conn.prepareStatement("insert into ORDERS(USERNAME,DATE,COST) values (?,?,?)");
      psInsert.setString(1, currentUser);
      Date orderDate = new Date();
      LocalDate date = orderDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
      imatOrder.setDate(date);
      psInsert.setString(2, imatOrder.getDate().toString());
      List<IMatShoppingItem> list = imatOrder.getAllProducts();
      int sum = 0;
      for (int i = 0; i < list.size(); i++) {
        sum += list.get(i).getSum();
      }
      imatOrder.setCost(sum);
      psInsert.setInt(3, sum);
      psInsert.execute();
      int amountOfProducts = imatOrder.getAllProducts().size();
      for (int i = 0; i < amountOfProducts; i++) {
        addProductToIMatOrder(i, imatOrder.getAllProducts().get(i).getAmount(), imatOrder.getAllProducts().get(i).getProductName(), imatOrder.getCost());
      }
      psInsert.close();
    } catch (SQLException ex) {
      Logger.getLogger(IMatController.class.getName()).log(Level.SEVERE, null, ex);
    }
    IMatShoppingCart.newCart();
    
  }
  
  /**
   * Check if current user is logged in or not.
   * 
   * @return  true if user is logged in, otherwise false 
   */
  public boolean isLoggedIn(){
    return loggedIn;
  }
  
  /**
   * 
   * @return the account of the currently logged in user
   */
  public IMatUserAccount getCurrentAccount() {
    return currentAccount;
  }
  
}

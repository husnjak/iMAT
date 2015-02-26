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
import java.sql.Array;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
 
  // Used for inserting statements into the database
  private static PreparedStatement psInsert;
  
  // Used for selecting information from the database
  private static PreparedStatement psSelect;
  
  // Used for communication with the database
  static Connection conn;
  
  // Used to check if user is logged in
  private boolean loggedIn;
  
  @Override
  public void initialize(URL url, ResourceBundle rb) {

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
  
  /*
  * The method creates a Connection object. Loads the embedded driver,
  * starts and connects to the database using the connection URL.
  */
  public static void createDatabaseConnection()
        throws SQLException, ClassNotFoundException {
    String driver = "org.apache.derby.jdbc.EmbeddedDriver";
    Class.forName(driver);
    
    // TODO: should be given username and pass as properties
    String url = "jdbc:derby:imatDB";
    conn = DriverManager.getConnection(url);
  }
  
  /**
   * If the application is used for the first time, the database should
   * be created.
   */
  public static void createDatabase(){
    // imatBackend.resetFirstRun();   // For testing purposes only
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
          tables.close();
          statement.close();
          conn.close();
          System.out.println("Database created");   // For testing purposes
          // Also create table for favorite products
        }
    } catch (ClassNotFoundException | SQLException ex) {
      Logger.getLogger(IMatController.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  }
  
  /**
   * Inserts a value into a given attribute in a given table.
   * 
   * @param table       the table for which the attribute exists
   * @param attribute   the attribute in given table for which to insert a value
   * @param value       the value to be inserted
   */
  public static synchronized void insertStatement(String table, String attribute, int value) {
    try {
      createDatabaseConnection();
      psInsert = conn.prepareStatement("insert into " + table
          + "("+ attribute +") values ("+ value +")");
    } catch (SQLException | ClassNotFoundException ex) {
      Logger.getLogger(IMatController.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
  public static void insertTestAccount(String table, String attribute, String value) {
    try {
      createDatabaseConnection();
      Statement statement = conn.createStatement();
      //psInsert = conn.prepareStatement("insert into " + table
      //    + "("+ attribute +") values (?)");
      //psInsert.setString(1,value);
      //psInsert.executeUpdate();
      statement.execute("insert into " + table + " values (" +
                    attribute + ",'" + value + "','" + value +"')");
      statement.close();
    } catch (SQLException | ClassNotFoundException ex) {
      Logger.getLogger(IMatController.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
  /**
   * Create a new record in the table
   * 
   * @param username   the attribute in given table for which to insert a value
   * @param password       the value to be inserted
   */
  public static void createAccount(String username, String password) {
    try {
      createDatabaseConnection();
      psInsert = conn.prepareStatement("insert into USERACCOUNT(USERNAME) values (?)");
      psInsert.setString(1, username);
      psInsert.execute();
      String updateString = "update USERACCOUNT set PASSWORD = ? where USERNAME = ?";
      psInsert = conn.prepareStatement(updateString);
      psInsert.setString(1, password);
      psInsert.setString(2, username);
      psInsert.executeUpdate();
      psInsert.close();
      conn.close();
    } catch (SQLException | ClassNotFoundException ex) {
      Logger.getLogger(IMatController.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
  /**
   * Updates given attribute with desired value.
   * 
   * @param attribute
   * @param value 
   */
  public static boolean updateAccount(String attribute, String value) {
    try {
      createDatabaseConnection();
      String updateString = "update USERACCOUNT set PASSWORD = ? where USERNAME = ?";
      PreparedStatement updateSales = conn.prepareStatement(updateString);
      updateSales.setString(1, value);
      updateSales.setString(2, attribute);
      updateSales.executeUpdate();
      updateSales.close();
      conn.close();
    } catch (SQLException | ClassNotFoundException ex) {
      Logger.getLogger(IMatController.class.getName()).log(Level.SEVERE, null, ex);
      return false;
    }
    return true;
  }
  
  public static String validAccount(String username, String password) {
    try {
      createDatabaseConnection();
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
              conn.close();
              return "validAccount";
            }
            rs.close();
            psSelect.close();
            conn.close();
            return "invalidPassword";
          }
      }
      rs.close();
      psSelect.close();
      conn.close();
    } catch (SQLException | ClassNotFoundException ex) {
      Logger.getLogger(IMatController.class.getName()).log(Level.SEVERE, null, ex);
    }
    return "invalidUsername";
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

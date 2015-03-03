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
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * This class set up the default views of the program.
 * 
 * @author group 11
 */
public class IMat extends Application {
  
  private Stage primaryStage;
  private Parent root;
  private BorderPane rootLayout;
  private Integer totalSum;
  private KategoriMenyController kategoriController;
  private CenterFlikController centerController;
  private ToppController toppController;
  private VarukorgController varukorgController;
 
  
  @Override
  public void start(Stage stage) throws Exception {
    this.primaryStage = stage;
    primaryStage.setTitle("iMat");
    primaryStage.setResizable(false);
    primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
      @Override public void handle(WindowEvent t) {
        try {
          IMatController.getConnection().close();
          if (IMatController.currentUser == null) {
            IMatController.getIMatBackend().shutDown();
          }
        } catch (SQLException ex) {
          Logger.getLogger(IMat.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
    });
    
    IMatController.createDatabase();
    if (IMatController.getConnection() == null) {
      IMatController.createDatabaseConnection();
    }
 
    initRootLayout();
    initKategoriMeny();
    initCenterFlik();
    initTopp();
    initVarukorg();
    
    totalSum = (int)IMatController.getShoppingCart().getTotal();
    varukorgController.updateTotalCostBackend(totalSum);
    varukorgController.initShoppingCart(varukorgController.convertBackendToIMat());
  }
  
  /**
  * Returns the main stage.
  * @return
  */
  public Stage getPrimaryStage() {
      return primaryStage;
  }
  
  /**
   * Return a KategoriMenyController reference.
   * 
   * @return a reference of the controller
   */
  public KategoriMenyController getKategoriController() {
    return kategoriController;
  }
  
  /**
   * Return a CenterFlikController reference.
   * 
   * @return a reference of the controller
   */
  public CenterFlikController getCenterController() {
    return centerController;
  }
  
  /**
   * Return a ToppController reference.
   * 
   * @return a reference of the controller
   */
  public ToppController getToppController() {
    return toppController;
  }
  
  /**
   * Return a VarukorgController reference.
   * 
   * @return a reference of the controller
   */
  public VarukorgController getVarukorgController() {
    return varukorgController;
  }
  
  /**
   * Makes the root layout the scene of the stage. All default views are
   * placed within the root layout.
   * 
   */
  public void initRootLayout() {
    try {
      // Load root layout from fxml file
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(IMat.class.getResource("view/RootLayout.fxml"));
      root = loader.load();
      
      // Show the scene containing the root layout
      Scene scene = new Scene(root);
      scene.getStylesheets().add(getClass().getResource("view/ButtonCSS.css").toExternalForm()); // Load the Stylesheets
      // Used for placing different views inside Root Layout
      rootLayout = (BorderPane) root;
      
      primaryStage.setScene(scene);
      primaryStage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
  }
  
  /**
   * Places the KategoriMeny view inside the left part of root layout.
   */
  public void initKategoriMeny() {
    try {
      // Load Handla view.
      FXMLLoader loader = new FXMLLoader(IMat.class.getResource("view/KategoriMeny.fxml"));
      AnchorPane kategoriMeny = (AnchorPane) loader.load();
      
      kategoriController = loader.getController();
      kategoriController.setMainApp(this);
      
      // Put Handla into the left part of root layout.
      rootLayout.setLeft(kategoriMeny);

    } catch (IOException e) {
        // Exception gets thrown if the fxml file could not be loaded
        e.printStackTrace();
    }
  }
  
  /**
   * Places the CenterFlik view inside the center part of root layout.
   */
  public void initCenterFlik() {
    try {
      // Load start view.
      FXMLLoader loader = new FXMLLoader(IMat.class.getResource("view/CenterFlik.fxml"));
      AnchorPane centerFlik = (AnchorPane) loader.load();
      
      centerController = loader.getController();
      centerController.setMainApp(this);

      // Put start into the center part of root layout.
      rootLayout.setCenter(centerFlik);
      
      // Load stored information for the user that is not logged in
      centerController.loadCustomerInformation();
      centerController.getOrders();
    } catch (IOException e) {
        e.printStackTrace();
    }
  }
  
  /**
   * Places the Topp view inside the upper part of root layout.
   */
  public void initTopp() {
    try {
      // Load top view.
      FXMLLoader loader = new FXMLLoader(IMat.class.getResource("view/Topp.fxml"));
      AnchorPane topp = (AnchorPane) loader.load();
      
      toppController = loader.getController();
      toppController.setMainApp(this);

      // Put top into the upper part of root layout.
      rootLayout.setTop(topp);
      
    } catch (IOException e) {
        e.printStackTrace();
    }
  }
  
  /**
   * Places the Varukorg view inside the right side of root layout.
   */
  public void initVarukorg() {
    try {
      // Load varukorg view.
      FXMLLoader loader = new FXMLLoader(IMat.class.getResource("view/Varukorg.fxml"));
      Pane varukorg = (Pane) loader.load();
      
      varukorgController = loader.getController();
      varukorgController.setMainApp(this);

      // Put varukorg into the right part of root layout.
      rootLayout.setRight(varukorg);
      
    } catch (IOException e) {
        e.printStackTrace();
    }
  }
  
  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    launch(args);
  }
  
}

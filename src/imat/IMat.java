/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imat;

import imat.view.RootLayoutController;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * This class set up the default views of the program.
 * 
 * @author grupp 11
 */
public class IMat extends Application {
  
  private Stage primaryStage;
  private BorderPane rootLayout;
  private RootLayoutController rootController;
  
  
  @Override
  public void start(Stage stage) throws Exception {
    this.primaryStage = stage;
    primaryStage.setTitle("iMat");
    primaryStage.setResizable(false);
    
    initRootLayout();
    initKategoriMeny();
    initCenterFlik();
    initTopp();
    initVarukorg();
  }
  
  /**
  * Returns the main stage.
  * @return
  */
  public Stage getPrimaryStage() {
      return primaryStage;
  }
  
  /**
   * Makes the root layout the scene of the stage. All default views are
   * placed within the root layout.
   * 
   */
  public void initRootLayout() {
    try {
      // Load root layout from fxml file.
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(IMat.class.getResource("view/RootLayout.fxml"));
      rootLayout = loader.load();
      
      rootController = loader.getController();

      // Show the scene containing the root layout.
      Scene scene = new Scene(rootLayout);
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
      // Load handla view.
      FXMLLoader loader = new FXMLLoader(IMat.class.getResource("view/KategoriMeny.fxml"));
      AnchorPane kategoriMeny = (AnchorPane) loader.load();
      
      // Put handla into the left part of root layout.
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

      // Put start into the center part of root layout.
      rootLayout.setCenter(centerFlik);
      
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
      AnchorPane varukorg = (AnchorPane) loader.load();

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

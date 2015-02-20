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
 *
 * @author joel
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
    initCenterFlikVy();
    initToppVy();
    initVarukorg();
  }
  
  /**
  * Returns the main stage.
  * @return
  */
  public Stage getPrimaryStage() {
      return primaryStage;
  }
  
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
  
  public void initCenterFlikVy() {
    try {
      // Load start view.
      FXMLLoader loader = new FXMLLoader(IMat.class.getResource("view/CenterFlik.fxml"));
      AnchorPane startVy = (AnchorPane) loader.load();

      // Put start into the center part of root layout.
      rootLayout.setCenter(startVy);
      
    } catch (IOException e) {
        e.printStackTrace();
    }
  }
  
  public void initToppVy() {
    try {
      // Load top view.
      FXMLLoader loader = new FXMLLoader(IMat.class.getResource("view/Topp.fxml"));
      AnchorPane toppVy = (AnchorPane) loader.load();

      // Put top into the upper part of root layout.
      rootLayout.setTop(toppVy);
      
    } catch (IOException e) {
        e.printStackTrace();
    }
  }
  
  public void initVarukorg() {
    try {
      // Load varukorg view.
      FXMLLoader loader = new FXMLLoader(IMat.class.getResource("view/Varukorg.fxml"));
      AnchorPane varukorgVy = (AnchorPane) loader.load();

      // Put varukorg into the right part of root layout.
      rootLayout.setRight(varukorgVy);
      
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

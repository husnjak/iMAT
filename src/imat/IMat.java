/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imat;

import imat.view.KategoriMenyController;
import imat.view.RootLayoutController;
import imat.view.CenterFlikVyController;
import imat.view.ToppVyController;
import imat.view.VarukorgController;
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
  private KategoriMenyController handlaController;
  private CenterFlikVyController startController;
  private ToppVyController toppController;
  private VarukorgController varukorgController;
  
  
  @Override
  public void start(Stage stage) throws Exception {
    this.primaryStage = stage;
    primaryStage.setTitle("iMat");
    primaryStage.setResizable(false);
    
    initRootLayout();
    //initHandlaMeny();
    initCenterFlikVy();
    //initToppVy();
    //initVarukorg();
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
      //rootController.setMainApp(this);

      // Show the scene containing the root layout.
      Scene scene = new Scene(rootLayout);
      primaryStage.setScene(scene);
      primaryStage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
  }
      
  public void initHandlaMeny() {
    try {
      // Load handla view.
      FXMLLoader loader = new FXMLLoader(IMat.class.getResource("view/KategoriMeny.fxml"));
      AnchorPane kategoriMeny = (AnchorPane) loader.load();
      
      // Put handla into the left part of root layout.
      rootLayout.setLeft(kategoriMeny);

      // Give the controller access to the main app
      //handlaController = loader.getController();
      //handlaController.setMainApp(this);

    } catch (IOException e) {
        // Exception gets thrown if the fxml file could not be loaded
        e.printStackTrace();
    }
  }
  
  public void initCenterFlikVy() {
    try {
      // Load start view.
      FXMLLoader loader = new FXMLLoader(IMat.class.getResource("view/StartVy.fxml"));
      AnchorPane startVy = (AnchorPane) loader.load();

      // Put start into the center part of root layout.
      rootLayout.setCenter(startVy);
      
      // Give the controller access to the main app.
      //startController = loader.getController();
      //startController.setMainApp(this);
      
    } catch (IOException e) {
        e.printStackTrace();
    }
  }
  
  public void initToppVy() {
    try {
      // Load top view.
      FXMLLoader loader = new FXMLLoader(IMat.class.getResource("view/ToppVy.fxml"));
      AnchorPane toppVy = (AnchorPane) loader.load();

      // Put top into the upper part of root layout.
      rootLayout.setTop(toppVy);
      
      // Give the controller access to the main app.
      //startController = loader.getController();
      //startController.setMainApp(this);
      
    } catch (IOException e) {
        e.printStackTrace();
    }
  }
  
  public void initVarukorg() {
    try {
      // Load varukorg view.
      FXMLLoader loader = new FXMLLoader(IMat.class.getResource("view/VarukorgVy.fxml"));
      AnchorPane varukorgVy = (AnchorPane) loader.load();

      // Put varukorg into the right part of root layout.
      rootLayout.setRight(varukorgVy);
      
      // Give the controller access to the main app.
      //startController = loader.getController();
      //startController.setMainApp(this);
      
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imat.view;

import imat.IMat;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;

/**
 *
 * @author joel
 */
public class CenterFlikController {
  
  @FXML
  private Tab handlaFlik;
  @FXML
  private Tab favoritvarorFlik;
  @FXML
  private Tab inkopslistorFlik;
  @FXML
  private Tab historikFlik;
  @FXML
  private Tab kontouppgifterFlik;
  
  // Reference to the main application
  private IMat mainApp;
  
  /**
  * Is called by the main application to give a reference back to itself.
  * 
  * @param mainApp
  */
  public void setMainApp(IMat mainApp) {
    this.mainApp = mainApp;
  }
  
}

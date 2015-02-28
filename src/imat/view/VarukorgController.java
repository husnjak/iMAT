/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imat.view;

import imat.IMat;
import imat.IMatController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import se.chalmers.ait.dat215.project.Order;

/**
 *
 * @author group 11
 */
public class VarukorgController implements Initializable {
  
  // Reference to the main application
  private IMat imat;
  @FXML
  private Button cartBuyButton;
  
  /**
  * Is called by the main application to give a reference back to itself.
  * 
  * @param mainApp
  */
  public void setMainApp(IMat imat) {
    this.imat = imat;
  } 

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    
    cartBuyButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Order orderCart = IMatController.getIMatBackend().placeOrder();
      }
    });

  }
        
}
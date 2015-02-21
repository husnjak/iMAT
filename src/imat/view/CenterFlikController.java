/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imat.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;

/**
 *
 * @author grupp 11
 */
public class CenterFlikController implements Initializable {
  
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
  @FXML
  private TabPane tabPane;
  @FXML
  private TextField fornamnTextField;
  @FXML
  private TextField efternamnTextField;
  @FXML
  private TextField personnummerTextField;
  @FXML
  private TextField telefonTextField;
  @FXML
  private TextField emailTextField;
  @FXML
  private TextField gatuadressTextField;
  @FXML
  private TextField postnummerTextField;
  @FXML
  private TextField stadTextField;
  @FXML
  private TextField kortnummerTextField;
  @FXML
  private TextField arTextField;
  @FXML
  private TextField manadTextField;
  @FXML
  private TextField cvvTextField;

  
 

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    
    
    arTextField.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {

      }
    });
  }

  
}

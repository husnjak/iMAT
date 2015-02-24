/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imat.view;

import imat.IMat;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 *
 * @author group 11
 */
public class ToppController implements Initializable {
  
  @FXML
  private TextField usernameTextField;
  @FXML
  private TextField passwordTextField;
  
  private IMat imat;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    
  }
  
  /**
   * Is called by the main application to give a reference back to itself.
   * 
   * @param imat
   */
  public void setMainApp(IMat imat) {
    this.imat = imat;
  } 
  
  /**
  * Called when the user clicks the login button. 
  */
  private void handleLogin() {
    String username = usernameTextField.getCharacters().toString();
    String password = passwordTextField.getCharacters().toString();
    
    // Check if this username/password combination exists in database
    // If they do, change the view to "logged in"
    // Otherwise, print "wrong username or password"
  }
  
  /**
  * Called when the user clicks the logout button. 
  */
  private void handleLogout() {

    
  }

  
}

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
import java.util.Map;
import java.util.ResourceBundle;
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
 * @author grupp 11
 */
public class IMatController implements Initializable {
  
  @FXML
  private Label label;
  @FXML
  private CenterFlikController centerFlikController;
  @FXML
  private KategoriMenyController kategoriMenyController;
  @FXML
  private ToppController toppController;
  @FXML
  private VarukorgController varukorgController;
  @FXML
  public static final IMatDataHandler imat = IMatDataHandler.getInstance();
  @FXML
  private final IMatProducts products = new IMatProducts();
  @FXML
  private final ShoppingCart shoppingCart = imat.getShoppingCart();
  
  // Used to store all IMatAccounts
  Map<String, IMatUserAccount> accounts;
  
  // Used to check if user is logged in
  public static boolean loggedIn;
  
  
  @Override
  public void initialize(URL url, ResourceBundle rb) {
     //TODO
  }
  
}

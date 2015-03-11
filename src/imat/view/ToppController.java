/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imat.view;

import imat.IMat;
import imat.IMatController;
import imat.IMatShoppingCart;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javax.imageio.ImageIO;

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
  @FXML
  private TextField searchTextField;
  @FXML
  private Button searchButton;
  @FXML
  private ImageView logoImage;
  @FXML
  private StackPane loginStackPane;
  @FXML
  private AnchorPane loginPane;
  @FXML
  private Button loginButton;
  @FXML
  private AnchorPane logoutPane;
  @FXML
  private Label loggedInLabel;
  @FXML
  private Hyperlink loggedInUser;
  @FXML
  private Button logoutButton;
  
  // Used for comparison with the database etc
  String username;
  String password;
  @FXML
  private Hyperlink registerUser;
  @FXML
  private Label loginLabel;
  
  /**
   * Set the username of the newly created account.
   * 
   * @param username the new username
   */
  public void setUsername(String username) {
    this.username = username;
  }
  
  /**
   * Set the password of the newly created account.
   * 
   * @param password the new password
   */
  public void setPassword(String password) {
    this.password = password;
  }
  
  /**
   * Change the login/logout screen from another part of the program.
   * 
   */
  public void changeLoginScreen() {
      loggedInUser.setText("  " + username);
      IMatController.setCurrentUser(username);
      imat.getCenterController().loadCustomerInformation();
      int size = loginStackPane.getChildren().size();
      String id;
      for (int i = 0; i < size; i++) {
        id = loginStackPane.getChildren().get(i).getId();
        if (id.compareTo("logoutPane") == 0) {
          loginStackPane.getChildren().get(i).toFront();
          loginStackPane.getChildren().get(i).setVisible(true);
        } else {
          loginStackPane.getChildren().get(i).setVisible(false);
        }
      }

      usernameTextField.setVisible(false);
      passwordTextField.setVisible(false);
      loginButton.setVisible(false);
      registerUser.setVisible(false);

      logoutButton.setVisible(true);
      loggedInUser.setVisible(true);
      loggedInLabel.setVisible(true);
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    //etImage(logoImage, "iMAT_Logga.png");
    registerUser.setFocusTraversable(false);
    loggedInUser.setFocusTraversable(false);
    passwordTextField.alignmentProperty().setValue(Pos.CENTER_LEFT);
    usernameTextField.alignmentProperty().setValue(Pos.CENTER_LEFT);
    searchTextField.alignmentProperty().setValue(Pos.CENTER_LEFT);
    searchTextField.setPromptText("Sök efter produkter här...");
    
    logoImage.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
     @Override
     public void handle(MouseEvent event) {
        imat.getCenterController().changeToStartPageView();
        event.consume();
     }
    });
    
    logoImage.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
        //Change cursor to hand
        @Override
        public void handle(MouseEvent event) {
            imat.getPrimaryStage().getScene().setCursor(Cursor.HAND);
        }
    });

    logoImage.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
        // Change cursor to default, arrow
        @Override
        public void handle(MouseEvent event) {
            imat.getPrimaryStage().getScene().setCursor(Cursor.DEFAULT);
        }
    });    
    
    registerUser.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        registerUser.setVisited(false);
        imat.getCenterController().changeToRegisterView();
        event.consume();
      }
    });
    
    loggedInUser.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        loggedInUser.setVisited(false);
        imat.getCenterController().changeToKontoView();
        imat.getCenterController().setTogglesSelected(false, false, true);
        event.consume();
      }
    });
    
    logoutButton.addEventHandler(ActionEvent.ANY, new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        handleLogout();
        event.consume();
      }
    });
    
    searchButton.addEventHandler(ActionEvent.ANY, new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        handleSearch();
        event.consume();
      }
    });
    
    loginButton.addEventHandler(ActionEvent.ANY, new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        handleLogin();
        event.consume();
      }
    });
    
    searchTextField.addEventHandler(ActionEvent.ANY, new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        imat.getCenterController().changeToSearchView(searchTextField.getText());
        event.consume();
      }
    });
    
    logoutButton.addEventHandler(KeyEvent.KEY_PRESSED, keyListener);
  }
  
  private EventHandler<KeyEvent> keyListener = new EventHandler<KeyEvent>() {
    @Override
    public void handle(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER) {
            handleLogout();
            event.consume();
        } 
    }
};
  
  /**
   * Is called by the main application to give a reference back to itself.
   * 
   * @param imat
   */
  public void setMainApp(IMat imat) {
    this.imat = imat;
  }
  
  /**
   * Helper method that sets the logo image of the application.
   */
  private void setImage(ImageView productImage, String imageName) {
    BufferedImage bufferedImage;
    try {
      bufferedImage = ImageIO.read(new File(imageName));
      Image image = SwingFXUtils.toFXImage(bufferedImage, null);
      productImage.setImage(image);
    } catch (IOException ex) {
      Logger.getLogger(CenterFlikController.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
  
  
  /**
   * Called when the user clicks the search button.
   */
  @FXML
  private void handleSearch() {
      if (searchTextField.getText().getClass() == String.class){
          imat.getCenterController().changeToSearchView(searchTextField.getText());
          searchTextField.setText("");
      }else {
          //give some sort of feedback
      }
      
  }
  /**
  * Called when the user clicks the login button. 
  */
  @FXML
  private void handleLogin() {
    loginLabel.setText("");
    username = usernameTextField.getCharacters().toString();
    password = passwordTextField.getCharacters().toString();
    String valid = IMatController.validAccount(username, password);
    
    if (username.length() == 0) {
      loginLabel.setText("Fyll i användarnamn");
    } else if (password.length() == 0) {
      loginLabel.setText("Fyll i lösenord");
    } else if ((valid.compareTo("validAccount") == 0)) {
      loggedInUser.setText("  " + username);
      IMatController.setCurrentUser(username);
      imat.getCenterController().showOrderHistory();
      imat.getCenterController().loadCustomerInformation();
      imat.getCenterController().loadCustomerInformationPayment();
      int size = loginStackPane.getChildren().size();
      String id;
      for (int i = 0; i < size; i++) {
        id = loginStackPane.getChildren().get(i).getId();
        if (id.compareTo("logoutPane") == 0) {
          loginStackPane.getChildren().get(i).toFront();
          loginStackPane.getChildren().get(i).setVisible(true);
        } else {
          loginStackPane.getChildren().get(i).setVisible(false);
        }
      }

      usernameTextField.setVisible(false);
      passwordTextField.setVisible(false);
      loginButton.setVisible(false);
      registerUser.setVisible(false);

      logoutButton.setVisible(true);
      loggedInUser.setVisible(true);
      loggedInLabel.setVisible(true);
      
      imat.getVarukorgController().updateTotalCostBackend(IMatShoppingCart.cart.getCost());
      imat.getVarukorgController().populateCheckoutCart(IMatShoppingCart.cart.getAllProducts());
      loginLabel.setText("");
      if (imat.getCenterController().currentPane.equals("checkCartCheckoutPane")) {
        imat.getCenterController().getListVyPane().getChildren().remove(imat.getCenterController().getCheckoutView());
        imat.getCenterController().populateCheckoutCart();
      }
    } else if (valid.compareTo("invalidUsername") == 0) {
      loginLabel.setText("Felaktigt användarnamn");
    } else {
      loginLabel.setText("Felaktigt lösenord");
    }
  imat.getVarukorgController().getList().setFocusTraversable(true);
  if (imat.getCenterController().getListVyPane().getChildren().contains(imat.getCenterController().lv)) {
    imat.getCenterController().changeToStartPageView();
  } else if (imat.getCenterController().currentPane.equals("registerPane")) {
      imat.getCenterController().changeToKontoView();
    } else if (imat.getCenterController().isFavoriteSelected()) {
     imat.getCenterController().changeToStartPageView();
    } else if (imat.getCenterController().currentPane != "listPane"){
      imat.getCenterController().changeToStartPageView();
    }
  
    if (IMatController.currentUser != null) {
      if (imat.getVarukorgController().getIMatShoppingCart().getCart().getAllProducts().isEmpty()) {
          imat.getVarukorgController().newPlaceHolder();
        imat.getVarukorgController().getEmptyButton().setDisable(true);
        imat.getVarukorgController().getPlaceHolder().setVisible(true);
        imat.getVarukorgController().getChangeLink().setDisable(true);
      } else {
        imat.getVarukorgController().getEmptyButton().setDisable(false);
        imat.getVarukorgController().getPlaceHolder().setVisible(false);
        imat.getVarukorgController().getChangeLink().setDisable(false);
      }
    } 
      //IMatController.getNumberOfFavorites();
    imat.getCenterController().populateFavorites();
    //imat.getCenterController().setCurrentIndex(IMatController.getNumberOfRecords("FAVORITES"));
  }
  
  /**
  * Called when the user clicks the logout button. 
  */
  private void handleLogout() {
    
    int size = loginStackPane.getChildren().size();
    String id;
    for (int i = 0; i < size; i++) {
      id = loginStackPane.getChildren().get(i).getId();
      if (id.compareTo("loginPane") == 0) {
        loginStackPane.getChildren().get(i).toFront();
        loginStackPane.getChildren().get(i).setVisible(true);
      } else {
        loginStackPane.getChildren().get(i).setVisible(false);
      }
    }
    
    logoutButton.setVisible(false);
    loggedInUser.setVisible(false);
    loggedInLabel.setVisible(false);
    
    usernameTextField.setVisible(true);
    passwordTextField.setVisible(true);
    loginButton.setVisible(true);
    registerUser.setVisible(true);

    IMatController.setCurrentUser(null);
    imat.getCenterController().loadCustomerInformation();
    imat.getCenterController().loadCustomerInformationPayment();
    
    usernameTextField.setText("");
    passwordTextField.setText("");
    
    imat.getCenterController().getOrders();

    
    Integer totalSum = (int)IMatController.getShoppingCart().getTotal();
    imat.getVarukorgController().updateTotalCostBackend(totalSum);
    imat.getVarukorgController().populateCheckoutCart(imat.getVarukorgController().convertBackendToIMat());
    if (imat.getCenterController().currentPane.equals("checkCartCheckoutPane")) {
      imat.getCenterController().getListVyPane().getChildren().remove(imat.getCenterController().getCheckoutView());
      imat.getCenterController().populateCheckoutCart();
    }
    if (imat.getCenterController().currentPane.equals("accordionPane")) {
      imat.getCenterController().changeToStartPageView();
    }
    
    if (imat.getCenterController().getListVyPane().getChildren().contains(imat.getCenterController().lv)) {
      imat.getCenterController().changeToStartPageView();
    }  else if (imat.getCenterController().currentPane.equals("registerPane")) {
      imat.getCenterController().changeToKontoView();
    } else if (imat.getCenterController().isFavoriteSelected()) {
     imat.getCenterController().changeToStartPageView();
    } else if (imat.getCenterController().currentPane != "listPane"){
      imat.getCenterController().changeToStartPageView();
    }
  
  }

  
}

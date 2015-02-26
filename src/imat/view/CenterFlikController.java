/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imat.view;

import imat.IMat;
import imat.IMatController;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javax.imageio.ImageIO;
import se.chalmers.ait.dat215.project.Product;

/**
 *
 * @author group 11
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
  private TextField emailTextField;
  @FXML
  private TextField cvvTextField;
  @FXML
  private TextField firstNameTextField;
  @FXML
  private TextField lastNameTextField;
  @FXML
  private TextField civicTextField;
  @FXML
  private TextField phoneTextField;
  @FXML
  private TextField streetTextField;
  @FXML
  private TextField postalTextField;
  @FXML
  private TextField cityTextField;
  @FXML
  private TextField cardNumberTextField;
  @FXML
  private TextField yearTextField;
  @FXML
  private TextField monthTextField;
  @FXML
  private Button storeInfoButton;
  @FXML
  private Label yearLabel;
  @FXML
  private Label phoneLabel;
  @FXML
  private Label postalLabel;
  @FXML
  private Label monthLabel;
  @FXML
  private Label cvvLabel;
  @FXML
  private Label cardNumberLabel;
  @FXML
  private Label civicLabel;
  
  private IMat imat;
  @FXML
  private Button rensaButton;
  
  // Used for deciding if to show the list view of products or not
  private boolean listView;
  @FXML
  private ImageView imageOneOne;
  
  // Used for holding products from specific categories
  private List<Product> products;
  @FXML
  private ImageView imageOneTwo;
  @FXML
  private ImageView imageOneThree;
  @FXML
  private ScrollPane breadScrollPane;
  @FXML
  private ImageView imageOneOne1;
  @FXML
  private ImageView imageOneTwo1;
  @FXML
  private ImageView imageOneThree1;
  @FXML
  private ScrollPane meatScrollPane;
  @FXML
  private StackPane handlaStackPane;
  @FXML
  private Label breadLabel00;
  @FXML
  private Label breadLabel01;
  @FXML
  private Label breadLabel02;
  @FXML
  private CheckBox breadFavorite00;
  @FXML
  private CheckBox breadFavorite01;
  @FXML
  private CheckBox breadFavorite02;
  @FXML
  private TextField textFieldBread00;
  @FXML
  private Button plusBread00;
  
  // Used for handling products
  Product product;
  
  // Used for handling product images
  String imageName;
  
  // Used to identify which pane should be visible
  String currentPane;
  
  @FXML
  private Button minusBread00;
  @FXML
  private Label totalCostBread00;
  @FXML
  private Label totalCostBread01;
  @FXML
  private Label totalCostBread02;
  @FXML
  private Button buyBread00;
  @FXML
  private Button buyBread01;
  @FXML
  private TextField textFieldBread01;
  @FXML
  private Button plusBread01;
  @FXML
  private Button minusBread01;
  @FXML
  private Button plusBread02;
  @FXML
  private Button minusBread02;
  @FXML
  private TextField textFieldBread02;
  @FXML
  private Button buyBread02;
  @FXML
  private ImageView imageTwoOne;
  @FXML
  private Label breadLabel10;
  @FXML
  private CheckBox breadFavorite10;
  @FXML
  private TextField textFieldBread10;
  @FXML
  private Button plusBread10;
  @FXML
  private Button minusBread10;
  @FXML
  private Button buyBread10;
  @FXML
  private Label totalCostBread10;
  @FXML
  private Label riceLabel00;
  @FXML
  private Label riceLabel01;
  @FXML
  private Label riceLabel02;
  @FXML
  private CheckBox riceFavorite00;
  @FXML
  private CheckBox riceFavorite01;
  @FXML
  private CheckBox riceFavorite02;
  @FXML
  private TextField textFieldRice00;
  @FXML
  private Button plusRice00;
  @FXML
  private Button minusRice00;
  @FXML
  private Label totalCostRice00;
  @FXML
  private Label totalCostRice01;
  @FXML
  private Label totalCostRice02;
  @FXML
  private Button buyRice00;
  @FXML
  private Button buyRice01;
  @FXML
  private TextField textFieldRice01;
  @FXML
  private Button plusRice01;
  @FXML
  private Button minusRice01;
  @FXML
  private Button plusRice02;
  @FXML
  private Button minusRice02;
  @FXML
  private TextField textFieldRice02;
  @FXML
  private Button buyRice02;
  @FXML
  private Label riceLabel10;
  @FXML
  private CheckBox riceFavorite10;
  @FXML
  private TextField textFieldRice10;
  @FXML
  private Button plusRice10;
  @FXML
  private Button minusRice10;
  @FXML
  private Button buyRice10;
  @FXML
  private Label totalCostRice10;
  @FXML
  private ScrollPane riceScrollPane;
  @FXML
  private ImageView imageZeroZeroRice;
  @FXML
  private ImageView imageZeroOneRice;
  @FXML
  private ImageView imageZeroTwoRice;
  @FXML
  private ImageView imageOneZeroRice;
  @FXML
  private ScrollPane startPage;
  @FXML
  private ImageView imageZeroZeroMeat;
  @FXML
  private ImageView imageZeroOneMeat;
  @FXML
  private ImageView imageZeroTwoMeat;
  @FXML
  private Label meatLabel00;
  @FXML
  private Label meatLabel01;
  @FXML
  private Label meatLabel02;
  @FXML
  private CheckBox meatFavorite00;
  @FXML
  private CheckBox meatFavorite01;
  @FXML
  private CheckBox meatFavorite02;
  @FXML
  private TextField textFieldMeat00;
  @FXML
  private Button plusMeat00;
  @FXML
  private Button minusMeat00;
  @FXML
  private Label totalCostMeat00;
  @FXML
  private Label totalCostMeat01;
  @FXML
  private Label totalCostMeat02;
  @FXML
  private Button buyMeat00;
  @FXML
  private Button buyMeat01;
  @FXML
  private TextField textFieldMeat01;
  @FXML
  private Button plusMeat01;
  @FXML
  private Button minusMeat01;
  @FXML
  private Button plusMeat02;
  @FXML
  private Button minusMeat02;
  @FXML
  private TextField textFieldMeat02;
  @FXML
  private Button buyMeat02;
  @FXML
  private ImageView imageOneZeroMeat;
  @FXML
  private Label meatLabel10;
  @FXML
  private CheckBox meatFavorite10;
  @FXML
  private TextField textFieldMeat10;
  @FXML
  private Button plusMeat10;
  @FXML
  private Button minusMeat10;
  @FXML
  private Button buyMeat10;
  @FXML
  private Label totalCostMeat10;
  @FXML
  private ScrollPane test;
  @FXML
  private ScrollPane test2;
  @FXML
  private ImageView imageOneOneMeat;
  @FXML
  private Label meatLabel11;
  @FXML
  private CheckBox meatFavorite11;
  @FXML
  private TextField textFieldMeat11;
  @FXML
  private Button plusMeat11;
  @FXML
  private Button minusMeat11;
  @FXML
  private Button buyMeat11;
  @FXML
  private Label totalCostMeat11;
  @FXML
  private ImageView imageOneTwoMeat;
  @FXML
  private Label meatLabel12;
  @FXML
  private CheckBox meatFavorite12;
  @FXML
  private Button plusMeat12;
  @FXML
  private Button minusMeat12;
  @FXML
  private Button buyMeat12;
  @FXML
  private Label totalCostMeat12;
  @FXML
  private TextField textFieldMeat12;
  @FXML
  private ScrollPane registerPane;
  @FXML
  private TextField createUserNameTextField;
  @FXML
  private TextField createPasswordTextField;
  @FXML
  private TextField createPasswordRepeatTextField;
  @FXML
  private Label createPasswordLabel;
  @FXML
  private Button createAccountButton;
  @FXML
  private StackPane mainStackPane;
  @FXML
  private Label createUserNameLabel;
  

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    products = new ArrayList<>();
    
    plusRice00.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = IMatController.getIMatProducts().getRiceList().get(0);
        increment(textFieldRice00, totalCostRice00, product);
      }
    });
    
    minusRice00.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = IMatController.getIMatProducts().getRiceList().get(0);
        decrement(textFieldRice00, totalCostRice00, product);
      }
    });
    
    plusRice01.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = IMatController.getIMatProducts().getRiceList().get(1);
        increment(textFieldRice01, totalCostRice01, product);
      }
    });
    
    minusRice01.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = IMatController.getIMatProducts().getRiceList().get(1);
        decrement(textFieldRice01, totalCostRice01, product);
      }
    });
    
    plusRice02.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = IMatController.getIMatProducts().getRiceList().get(2);
        increment(textFieldRice02, totalCostRice02, product);
      }
    });
    
    minusRice02.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = IMatController.getIMatProducts().getRiceList().get(2);
        decrement(textFieldRice02, totalCostRice02, product);
      }
    });
    
    plusRice10.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = IMatController.getIMatProducts().getRiceList().get(3);
        increment(textFieldRice10, totalCostRice10, product);
      }
    });
    
    minusRice10.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = IMatController.getIMatProducts().getRiceList().get(3);
        decrement(textFieldRice10, totalCostRice10, product);
      }
    });
    
    plusMeat00.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = IMatController.getIMatProducts().getMeatList().get(0);
        increment(textFieldMeat00, totalCostMeat00, product);
      }
    });
    
    minusMeat00.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = IMatController.getIMatProducts().getMeatList().get(0);
        decrement(textFieldMeat00, totalCostMeat00, product);
      }
    });
    
    plusMeat01.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = IMatController.getIMatProducts().getMeatList().get(1);
        increment(textFieldMeat01, totalCostMeat01, product);
      }
    });
    
    minusMeat01.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = IMatController.getIMatProducts().getMeatList().get(1);
        decrement(textFieldMeat01, totalCostMeat01, product);
      }
    });
    
    plusMeat02.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = IMatController.getIMatProducts().getMeatList().get(2);
        increment(textFieldMeat02, totalCostMeat02, product);
      }
    });
    
    minusMeat02.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = IMatController.getIMatProducts().getMeatList().get(2);
        decrement(textFieldMeat02, totalCostMeat02, product);
      }
    });
    
    plusMeat10.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = IMatController.getIMatProducts().getMeatList().get(3);
        increment(textFieldMeat10, totalCostMeat10, product);
      }
    });
    
    minusMeat10.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = IMatController.getIMatProducts().getMeatList().get(3);
        decrement(textFieldMeat10, totalCostMeat10, product);
      }
    });
    
    plusMeat11.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = IMatController.getIMatProducts().getMeatList().get(4);
        increment(textFieldMeat11, totalCostMeat11, product);
      }
    });
    
    minusMeat11.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = IMatController.getIMatProducts().getMeatList().get(4);
        decrement(textFieldMeat11, totalCostMeat11, product);
      }
    });
    
    plusMeat12.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = IMatController.getIMatProducts().getMeatList().get(5);
        increment(textFieldMeat12, totalCostMeat12, product);
      }
    });
    
    minusMeat12.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = IMatController.getIMatProducts().getMeatList().get(5);
        decrement(textFieldMeat12, totalCostMeat12, product);
      }
    });
    
    createAccountButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        String username = createUserNameTextField.getText();
        String password = createPasswordTextField.getText();
        String repeatPassword = createPasswordRepeatTextField.getText();
        if (username.length() < 1 ) {
          createUserNameLabel.setText("Fyll i användarnamn");
        } else if (password.length() < 1 || repeatPassword.length() < 1) {
          createPasswordLabel.setText("Fyll i lösenord");
        } else if (password.compareTo(repeatPassword) != 0) {
          createPasswordLabel.setText("Felaktigt lösenord");
        } else {
          String isValid = IMatController.validAccount(username, password);
          if (isValid.compareTo("invalidUsername") != 0) {
            createUserNameLabel.setText("Användarnamnet existerar redan");
          } else {
            IMatController.createAccount(username, password);
            IMatController.setCurrentUser(username);
          }
          
        }
      }
    });
    
    
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
   * Stores given account information in the database. Before the data is
   * stored, a check is done to see if the given information is valid. A user
   * is not required to fill in any specific fields when in 
   * "Kontoinställningar", only when performing a purchase.
   */
  @FXML
  public void storeInformation() {
    
    // Store first name if entered
    if (firstNameTextField.getLength() > 0) {
      String firstName = firstNameTextField.getText();
      IMatController.updateAccount("FIRSTNAME", firstName);
    }
    
    // Store last name if entered
    if (lastNameTextField.getLength() > 0) {
      String lastName = lastNameTextField.getText();
      IMatController.updateAccount("LASTNAME", lastName);
    }
    
    // Check if civicTextField contains valid data
    // Ändra så att man väljer datum etc i combobox?
    if (civicTextField.getLength() > 0 && civicTextField.getLength() == 10) {
      try {
        Integer civic = Integer.parseInt(civicTextField.getText());
        civicLabel.setText("");
        IMatController.updateAccount("CIVIC", civic.toString());
      } catch (NumberFormatException e) {
        civicLabel.setText("Ange personnummer med 10 siffror");
      }
    } else if (civicTextField.getLength() > 0) {
      civicLabel.setText("Ange personnummer med 10 siffror");
    } else if (civicTextField.getLength() == 0) {
      civicLabel.setText("");
    }
    
    // Check if phoneTextField contains valid data
    if (phoneTextField.getLength() > 0 && phoneTextField.getLength() < 16) {
      try {
        Integer phone = Integer.parseInt(phoneTextField.getText());
        phoneLabel.setText("");
        IMatController.updateAccount("PHONE", phone.toString());
      } catch (NumberFormatException e) {
        phoneLabel.setText("Ange maximalt 15 siffror");
      }
    } else if (phoneTextField.getLength() == 0) {
      phoneLabel.setText("");
    }
    
    // Store email address if entered
    if (emailTextField.getLength() > 0) {
      String email = emailTextField.getText();
      IMatController.updateAccount("EMAIL", email);
    }
    
    // Store street address if entered
    if (streetTextField.getLength() > 0) {
      String street = streetTextField.getText();
      IMatController.updateAccount("STREET", street);
    }
    
    // Check if postalTextField contains valid data
    if (postalTextField.getLength() > 0 && postalTextField.getLength() == 5) {
      try {
        Integer postal = Integer.parseInt(postalTextField.getText());
        postalLabel.setText("");
        IMatController.updateAccount("POSTAL", postal.toString());
      } catch (NumberFormatException e) {
        postalLabel.setText("Ange postadress med 5 stycken siffror");
      }
    } else if (postalTextField.getLength() > 0) {
      postalLabel.setText("Ange postadress med 5 stycken siffror");
    } else if (postalTextField.getLength() == 0) {
      postalLabel.setText("");
    }
    
    // Store name of city if entered
    if (cityTextField.getLength() > 0) {
      String city = cityTextField.getText();
      IMatController.updateAccount("CITY", city);
    }
    
    // Check if cardNumberTextField contains valid data
    if (cardNumberTextField.getLength() > 0) {
      try {
        Integer cardNumber = Integer.parseInt(cardNumberTextField.getText());
        cardNumberLabel.setText("");
        IMatController.updateAccount("CARDNUMBER", cardNumber.toString());
      } catch (NumberFormatException e) {
        cardNumberLabel.setText("Ange kortnummer med siffror");
      }
    } else if (cardNumberTextField.getLength() == 0) {
      cardNumberLabel.setText("");
    }
    
    // Check if yearTextField contains valid data
    if (yearTextField.getLength() > 0 && yearTextField.getLength() == 2) {
      try {
        Integer validYear = Integer.parseInt(yearTextField.getText());
        yearLabel.setText("");
        IMatController.updateAccount("VALIDYEAR", validYear.toString());
      } catch (NumberFormatException e) {
        yearLabel.setText("Ange år med två siffror");
      }
    } else if (yearTextField.getLength() > 0) {
      yearLabel.setText("Ange år med två siffror");
    } else if (yearTextField.getLength() == 0) {
      yearLabel.setText("");
    }
    
    // Check if monthTextField contains valid data
    if (monthTextField.getLength() > 0 && monthTextField.getLength() == 2) {
      try {
        Integer validMonth = Integer.parseInt(monthTextField.getText());
        monthLabel.setText("");
        IMatController.updateAccount("VALIDMONTH", validMonth.toString());
      } catch (NumberFormatException e) {
        monthLabel.setText("Ange månad med två siffror");
      }
    } else if (monthTextField.getLength() > 0) {
      monthLabel.setText("Ange månad med två siffror");
    } else if (monthTextField.getLength() == 0) {
      monthLabel.setText("");
    }
    
    // Check if cvvTextField contains valid data
    if (cvvTextField.getLength() > 0 && cvvTextField.getLength() == 3) {
      try {
        Integer cvv = Integer.parseInt(cvvTextField.getText());
        cvvLabel.setText("");
        IMatController.updateAccount("CVV", cvv.toString());
      } catch (NumberFormatException e) {
        cvvLabel.setText("Ange cvv med tre siffror");
      }
    } else if (cvvTextField.getLength() > 0) {
      cvvLabel.setText("Ange cvv med tre siffror");
    } else if (cvvTextField.getLength() == 0) {
      cvvLabel.setText("");
    }
    
  }
  
  /**
   * This method clears all visible data in the text fields.
   */
  @FXML
  public void rensaButton() {
    firstNameTextField.clear();
    lastNameTextField.clear();
    civicTextField.clear();
    civicLabel.setText("");
    emailTextField.clear();
    phoneTextField.clear();
    streetTextField.clear();
    postalTextField.clear();
    postalLabel.setText("");
    cityTextField.clear();
    cardNumberTextField.clear();
    cardNumberLabel.setText("");
    yearTextField.clear();
    yearLabel.setText("");
    monthTextField.clear();
    monthLabel.setText("");
    cvvTextField.clear();
    cvvLabel.setText("");
  }
  
  /**
   * Helper method that sets the image of the current product
   */
  private void setImage(ImageView productImage, String imageName) {
    BufferedImage bufferedImage;
    try {
      bufferedImage = ImageIO.read(new File("resources/"+ imageName));
      Image image = SwingFXUtils.toFXImage(bufferedImage, null);
      productImage.setImage(image);
    } catch (IOException ex) {
      Logger.getLogger(CenterFlikController.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
  /**
   * Used to increment the number of units of desired product.
   * 
   * @param field contains the number of units
   * @param totalCost is the product of all units
   * @param product is the incremented product
   */
  public void increment(TextField field, Label totalCost, Product product) {
    try {
      Integer inc = Integer.parseInt(field.getCharacters().toString());
      if (inc > 0) {
        inc++;
        totalCost.setText("Pris: " + (int)product.getPrice()*inc + " kr");
        String newNumber = inc.toString();
        field.setText(newNumber);
      } else {
        inc = 1;
        totalCost.setText("Pris: " + (int)product.getPrice()*inc + " kr");
        String newNumber = inc.toString();
        field.setText(newNumber);
      }
    } catch (NumberFormatException e) {
        Integer inc = 1;
        totalCost.setText("Pris: " + (int)product.getPrice()*inc + " kr");
        String newNumber = inc.toString();
        field.setText(newNumber);
    }
  }
  
  /**
   * Used to decrement the number of units of a particular product.
   * 
   * @param field contains the number of units
   * @param totalCost is the product of all units
   * @param product is the decremented product
   */
  public void decrement(TextField field, Label totalCost, Product product) {
    try {
      Integer inc = Integer.parseInt(field.getCharacters().toString());
      if (inc > 1) {
        inc--;
        totalCost.setText("Pris: " + (int)product.getPrice()*inc + " kr");
        String newNumber = inc.toString();
        field.setText(newNumber);
      } else {
        inc = 1;
        totalCost.setText("Pris: " + (int)product.getPrice()*inc + " kr");
        String newNumber = inc.toString();
        field.setText(newNumber);
      }
    } catch (NumberFormatException e) {
        Integer inc = 1;
        totalCost.setText("Pris: " + (int)product.getPrice()*inc + " kr");
        String newNumber = inc.toString();
        field.setText(newNumber);
    }
  }
  
  /**
   * Changes the center view to the start page.
   */
  public void changeToStartPageView() {
    currentPane = "startPage";
    hideOtherPanes();
    int size = handlaStackPane.getChildren().size();
    String id;
    for (int i = 0; i < size; i++) {
      id = handlaStackPane.getChildren().get(i).getId();
      if (id.compareTo(currentPane) == 0) {
        handlaStackPane.getChildren().get(i).toFront();
        handlaStackPane.getChildren().get(i).setVisible(true);
      } else {
        handlaStackPane.getChildren().get(i).setVisible(false);
      }
    }
  }
  
  public void hideOtherPanes() {
    int size = mainStackPane.getChildren().size();
    String id;
    for (int i = 0; i < size; i++) {
      id = mainStackPane.getChildren().get(i).getId();
      if (id.compareTo(currentPane) == 0) {
        mainStackPane.getChildren().get(i).toFront();
        mainStackPane.getChildren().get(i).setVisible(true);
      } else {
        mainStackPane.getChildren().get(i).setVisible(false);
      }
    }
  }
  
  /**
   * Changes the center view to the register page.
   */
  public void changeToRegisterView() {
    currentPane = "registerPane";
    hideOtherPanes();
    int size = handlaStackPane.getChildren().size();
    String id;
    for (int i = 0; i < size; i++) {
      id = handlaStackPane.getChildren().get(i).getId();
      if (id.compareTo(currentPane) == 0) {
        handlaStackPane.getChildren().get(i).toFront();
        handlaStackPane.getChildren().get(i).setVisible(true);
      } else {
        handlaStackPane.getChildren().get(i).setVisible(false);
      }
    }
  }
    
  
  /**
   * If Rice link has been clicked, show rice products in Handla view.
   */
  public void changeToRiceView() {
    currentPane = "tabPane";
    hideOtherPanes();
    int size = handlaStackPane.getChildren().size();
    String id;
    for (int i = 0; i < size; i++) {
      id = handlaStackPane.getChildren().get(i).getId();
      if (id.compareTo("riceScrollPane") == 0) {
        handlaStackPane.getChildren().get(i).toFront();
        handlaStackPane.getChildren().get(i).setVisible(true);
      } else {
        handlaStackPane.getChildren().get(i).setVisible(false);
      }
    }

    products = IMatController.getIMatProducts().getRiceList();
    product = products.get(0);
    riceLabel00.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(imageZeroZeroRice, imageName);
    totalCostRice00.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(1);
    riceLabel01.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(imageZeroOneRice, imageName);
    totalCostRice01.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(2);
    riceLabel02.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(imageZeroTwoRice, imageName);
    totalCostRice02.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(3);
    riceLabel10.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(imageOneZeroRice, imageName);
    totalCostRice10.setText("Pris: " + (int)product.getPrice() + " kr");

  }
  
  /**
   * If Meat link has been clicked, show meat products in Handla view.
   */
  public void changeToMeatView() {
    currentPane = "tabPane";
    hideOtherPanes();
    int size = handlaStackPane.getChildren().size();
    String id;
    for (int i = 0; i < size; i++) {
      id = handlaStackPane.getChildren().get(i).getId();
      if (id.compareTo("meatScrollPane") == 0) {
        handlaStackPane.getChildren().get(i).toFront();
        handlaStackPane.getChildren().get(i).setVisible(true);
      } else {
        handlaStackPane.getChildren().get(i).setVisible(false);
      }
    }

    products = IMatController.getIMatProducts().getMeatList();
    product = products.get(0);
    meatLabel00.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(imageZeroZeroMeat, imageName);
    totalCostMeat00.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(1);
    meatLabel01.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(imageZeroOneMeat, imageName);
    totalCostMeat01.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(2);
    meatLabel02.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(imageZeroTwoMeat, imageName);
    totalCostMeat02.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(3);
    meatLabel10.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(imageOneZeroMeat, imageName);
    totalCostMeat10.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(4);
    meatLabel11.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(imageOneOneMeat, imageName);
    totalCostMeat11.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(5);
    meatLabel12.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(imageOneTwoMeat, imageName);
    totalCostMeat12.setText("Pris: " + (int)product.getPrice() + " kr");
    
  }
  
}

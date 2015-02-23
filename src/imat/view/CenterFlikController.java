/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imat.view;

import imat.IMatController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
  
  private IMatController iMatController;
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

  @Override
  public void initialize(URL location, ResourceBundle resources) {
 
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
      IMatController.insertStatement("useraccount","firstname", firstName);
    }
    
    // Store last name if entered
    if (lastNameTextField.getLength() > 0) {
      String lastName = lastNameTextField.getText();
      IMatController.insertStatement("useraccount","lastname", lastName);
    }
    
    // Check if civicTextField contains valid data
    // Ändra så att man väljer datum etc i combobox?
    if (civicTextField.getLength() > 0 && civicTextField.getLength() == 10) {
      try {
        int civic = Integer.parseInt(civicTextField.getText());
        civicLabel.setText("");
        IMatController.insertStatement("useraccount","civic", civic);
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
        int phone = Integer.parseInt(phoneTextField.getText());
        phoneLabel.setText("");
        IMatController.insertStatement("useraccount","phone", phone);
      } catch (NumberFormatException e) {
        phoneLabel.setText("Ange maximalt 15 siffror");
      }
    } else if (phoneTextField.getLength() == 0) {
      phoneLabel.setText("");
    }
    
    // Store email address if entered
    if (emailTextField.getLength() > 0) {
      String email = emailTextField.getText();
      IMatController.insertStatement("useraccount","email", email);
    }
    
    // Store street address if entered
    if (streetTextField.getLength() > 0) {
      String street = streetTextField.getText();
      IMatController.insertStatement("useraccount","street", street);
    }
    
    // Check if postalTextField contains valid data
    if (postalTextField.getLength() > 0 && postalTextField.getLength() == 5) {
      try {
        int postal = Integer.parseInt(postalTextField.getText());
        postalLabel.setText("");
        IMatController.insertStatement("useraccount","postal", postal);
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
      IMatController.insertStatement("useraccount","city", city);
    }
    
    // Check if cardNumberTextField contains valid data
    if (cardNumberTextField.getLength() > 0) {
      try {
        int cardNumber = Integer.parseInt(cardNumberTextField.getText());
        cardNumberLabel.setText("");
        IMatController.insertStatement("useraccount","cardnumber", cardNumber);
      } catch (NumberFormatException e) {
        cardNumberLabel.setText("Ange kortnummer med siffror");
      }
    } else if (cardNumberTextField.getLength() == 0) {
      cardNumberLabel.setText("");
    }
    
    // Check if yearTextField contains valid data
    if (yearTextField.getLength() > 0 && yearTextField.getLength() == 2) {
      try {
        int validYear = Integer.parseInt(yearTextField.getText());
        yearLabel.setText("");
        IMatController.insertStatement("useraccount","validyear", validYear);
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
        int validMonth = Integer.parseInt(monthTextField.getText());
        monthLabel.setText("");
        IMatController.insertStatement("useraccount","validmonth", validMonth);
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
        int cvv = Integer.parseInt(cvvTextField.getText());
        cvvLabel.setText("");
        IMatController.insertStatement("useraccount","cvv", cvv);
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
    
  }
  
}

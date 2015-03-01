/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imat.view;

import imat.IMat;
import imat.IMatController;
import imat.IMatOrder;
import imat.IMatShoppingCart;
import imat.IMatShoppingItem;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javax.imageio.ImageIO;
import se.chalmers.ait.dat215.project.Order;
import se.chalmers.ait.dat215.project.Product;
import se.chalmers.ait.dat215.project.ShoppingItem;

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
  
  // Stores order history
  private ObservableList<Order> orders;
  private ObservableList<IMatOrder> imatOrders;
  
  // Contains products in a given IMatOrder
  private ObservableList<IMatShoppingItem> productsInOrder;
  private ObservableList<IMatShoppingItem> productsInDatabase;
  
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
  @FXML
  private TableView<IMatOrder> orderTable;
  @FXML
  private TableColumn<IMatOrder, Integer> orderIdColumn;
  @FXML
  private TableColumn<IMatOrder, Date> orderDateColumn;
  @FXML
  private TableColumn<IMatOrder, Integer> orderCostColumn;
  
  // Used to keep track which number the recently added product has in order
  Integer productNr = 0;
  
  // Used to keep track the number of units of the added product
  Integer productUnits;
  
  // Stores the total sum of all products in the shopping cart
  int totalCost;
  
  // Contains the converted orders for a user who is not logged in
  List<IMatOrder> imatOrderList;
  
  // Contains shopping items related to a IMat order (for logged in users)
  List<IMatShoppingItem> imatItemList;
  
  @FXML
  private TableView<IMatShoppingItem> productTable;
  @FXML
  private TableColumn<IMatShoppingItem, String> productNameColumn;
  @FXML
  private TableColumn<IMatShoppingItem, Integer> productUnitsColumn;
  @FXML
  private TableColumn<IMatShoppingItem, Integer> productCostColumn;
  
  public Integer getProductNr() {
    return productNr;
  }
  
  public void setProductNr(Integer productNr) {
    this.productNr = productNr;
  }
  
  public Integer getProductUnits() {
    return productUnits;
  }
  
  public void setProductUnits(Integer productUnits) {
    this.productUnits = productUnits;
  }
  
  public Tab getHandlaFlik() {
    return handlaFlik;
  }
  
  public TabPane getTabPane() {
    return tabPane;
  }
  
  public Tab getHistorikFlik() {
    return historikFlik;
  }
  
  public TableView<IMatOrder> getOrderTable() {
    return orderTable;
  }
  
  public TableView<IMatShoppingItem> getProductTable() {
    return productTable;
  }
  
  public ObservableList<IMatOrder> getIMatOrders() {
    return imatOrders;
  }

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
            createUserNameTextField.setText("");
            createPasswordTextField.setText("");
            createPasswordRepeatTextField.setText("");
            changeToStartPageView();
            imat.getToppController().setUsername(username);
            imat.getToppController().setPassword(password);
            imat.getToppController().changeLoginScreen();
          }
        }
      }
    });
    
    // Initialize the order table
    orderIdColumn.setCellValueFactory(new PropertyValueFactory<>("orderNumber"));
    orderDateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
    orderCostColumn.setCellValueFactory(new PropertyValueFactory<>("cost"));
    
    // Initialize the shopping item table
    productNameColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));
    productUnitsColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
    productCostColumn.setCellValueFactory(new PropertyValueFactory<>("sum"));
    
    // Listen for selection changes
    orderTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<IMatOrder>() {

    @Override
    public void changed(ObservableValue<? extends IMatOrder> observable, IMatOrder oldOrder, IMatOrder newOrder) {
      if (newOrder != null) {
        showIMatOrderDetails(newOrder.getAllProducts());
      }
    }
    });
    
    buyRice00.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = IMatController.getIMatProducts().getRiceList().get(0);
        productUnits = Integer.parseInt(textFieldRice00.getText());
        if (IMatController.currentUser != null) {
          productNr++;
          int cost = (int)product.getPrice();
          int sum = productUnits*cost;
          totalCost += sum;
          IMatShoppingCart.cart.addShoppingItem(product, productUnits, sum);
          imat.getVarukorgController().updateTotalCost();
        } else {      
          IMatController.getShoppingCart().addItem(new ShoppingItem(product, productUnits));
          Integer totalSum = (int)IMatController.getShoppingCart().getTotal();
          imat.getVarukorgController().updateTotalCostBackend(totalSum);
        }
      }
    });
    
    buyRice01.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = IMatController.getIMatProducts().getRiceList().get(1);
        productUnits = Integer.parseInt(textFieldRice01.getText());
        if (IMatController.currentUser != null) {
          productNr++;
          int cost = (int)product.getPrice();
          int sum = productUnits*cost;
          totalCost += sum;
          IMatShoppingCart.cart.addShoppingItem(product, productUnits, sum);
          imat.getVarukorgController().updateTotalCost();
        } else {
          IMatController.getShoppingCart().addItem(new ShoppingItem(product, productUnits));
          Integer totalSum = (int)IMatController.getShoppingCart().getTotal();
          imat.getVarukorgController().updateTotalCostBackend(totalSum);
        }

      }
    });
    
    buyRice02.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = IMatController.getIMatProducts().getRiceList().get(2);
        productUnits = Integer.parseInt(textFieldRice02.getText());
        if (IMatController.currentUser != null) {
          productNr++;
          int cost = (int)product.getPrice();
          int sum = productUnits*cost;
          totalCost += sum;
          IMatShoppingCart.cart.addShoppingItem(product, productUnits, sum);
          imat.getVarukorgController().updateTotalCost();
        } else {
          IMatController.getShoppingCart().addItem(new ShoppingItem(product, productUnits));
          Integer totalSum = (int)IMatController.getShoppingCart().getTotal();
          imat.getVarukorgController().updateTotalCostBackend(totalSum);
        }
      }
    });
    
    buyRice10.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = IMatController.getIMatProducts().getRiceList().get(3);
        productUnits = Integer.parseInt(textFieldRice10.getText());
        if (IMatController.currentUser != null) {
          productNr++;
          int cost = (int)product.getPrice();
          int sum = productUnits*cost;
          totalCost += sum;
          IMatShoppingCart.cart.addShoppingItem(product, productUnits, sum);
          imat.getVarukorgController().updateTotalCost();
        } else {
          IMatController.getShoppingCart().addItem(new ShoppingItem(product, productUnits));
          Integer totalSum = (int)IMatController.getShoppingCart().getTotal();
          imat.getVarukorgController().updateTotalCostBackend(totalSum);
        }
      }
    });
    
    buyMeat00.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = IMatController.getIMatProducts().getMeatList().get(0);
        productUnits = Integer.parseInt(textFieldMeat00.getText());
        if (IMatController.currentUser != null) {
          productNr++;
          int cost = (int)product.getPrice();
          int sum = productUnits*cost;
          totalCost += sum;
          IMatShoppingCart.cart.addShoppingItem(product, productUnits, sum);
          imat.getVarukorgController().updateTotalCost();
        } else {
          IMatController.getShoppingCart().addItem(new ShoppingItem(product, productUnits));
          Integer totalSum = (int)IMatController.getShoppingCart().getTotal();
          imat.getVarukorgController().updateTotalCostBackend(totalSum);
        }
      }
    });
    
    buyMeat01.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = IMatController.getIMatProducts().getMeatList().get(1);
        productUnits = Integer.parseInt(textFieldMeat01.getText());
        if (IMatController.currentUser != null) {
          productNr++;
          int cost = (int)product.getPrice();
          int sum = productUnits*cost;
          totalCost += sum;
          IMatShoppingCart.cart.addShoppingItem(product, productUnits, sum);
          imat.getVarukorgController().updateTotalCost();
        } else {
          IMatController.getShoppingCart().addItem(new ShoppingItem(product, productUnits));
          Integer totalSum = (int)IMatController.getShoppingCart().getTotal();
          imat.getVarukorgController().updateTotalCostBackend(totalSum);
        }
      }
    });
    
    buyMeat02.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = IMatController.getIMatProducts().getMeatList().get(2);
        productUnits = Integer.parseInt(textFieldMeat02.getText());
        if (IMatController.currentUser != null) {
          productNr++;
          int cost = (int)product.getPrice();
          int sum = productUnits*cost;
          totalCost += sum;
          IMatShoppingCart.cart.addShoppingItem(product, productUnits, sum);
          imat.getVarukorgController().updateTotalCost();
        } else {
          IMatController.getShoppingCart().addItem(new ShoppingItem(product, productUnits));
          Integer totalSum = (int)IMatController.getShoppingCart().getTotal();
          imat.getVarukorgController().updateTotalCostBackend(totalSum);
        }
      }
    });
    
    buyMeat10.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = IMatController.getIMatProducts().getMeatList().get(3);
        productUnits = Integer.parseInt(textFieldMeat10.getText());
        if (IMatController.currentUser != null) {
          productNr++;
          int cost = (int)product.getPrice();
          int sum = productUnits*cost;
          totalCost += sum;
          IMatShoppingCart.cart.addShoppingItem(product, productUnits, sum);
          imat.getVarukorgController().updateTotalCost();
        } else {
          IMatController.getShoppingCart().addItem(new ShoppingItem(product, productUnits));
          Integer totalSum = (int)IMatController.getShoppingCart().getTotal();
          imat.getVarukorgController().updateTotalCostBackend(totalSum);
        }
      }
    });
    
    buyMeat11.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = IMatController.getIMatProducts().getMeatList().get(4);
        productUnits = Integer.parseInt(textFieldMeat11.getText());
        if (IMatController.currentUser != null) {
          productNr++;
          int cost = (int)product.getPrice();
          int sum = productUnits*cost;
          totalCost += sum;
          IMatShoppingCart.cart.addShoppingItem(product, productUnits, sum);
          imat.getVarukorgController().updateTotalCost();
        } else {
          IMatController.getShoppingCart().addItem(new ShoppingItem(product, productUnits));
          Integer totalSum = (int)IMatController.getShoppingCart().getTotal();
          imat.getVarukorgController().updateTotalCostBackend(totalSum);
        }
      }
    });
    
    buyMeat12.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = IMatController.getIMatProducts().getMeatList().get(5);
        productUnits = Integer.parseInt(textFieldMeat12.getText());
        if (IMatController.currentUser != null) {
          productNr++;
          int cost = (int)product.getPrice();
          int sum = productUnits*cost;
          totalCost += sum;
          IMatShoppingCart.cart.addShoppingItem(product, productUnits, sum);
          imat.getVarukorgController().updateTotalCost();
        } else {
          IMatController.getShoppingCart().addItem(new ShoppingItem(product, productUnits));
          Integer totalSum = (int)IMatController.getShoppingCart().getTotal();
          imat.getVarukorgController().updateTotalCostBackend(totalSum);
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
   * Load stored information for users.
   */
  @SuppressWarnings("ConvertToTryWithResources")
  public void loadCustomerInformation() {
    if (IMatController.currentUser == null) {
      firstNameTextField.setText(IMatController.getIMatBackend().getCustomer().getFirstName());
      lastNameTextField.setText(IMatController.getIMatBackend().getCustomer().getLastName());
      civicTextField.setText(IMatController.getIMatBackend().getCustomer().getMobilePhoneNumber());
      phoneTextField.setText(IMatController.getIMatBackend().getCustomer().getPhoneNumber());
      emailTextField.setText(IMatController.getIMatBackend().getCustomer().getEmail());
      streetTextField.setText(IMatController.getIMatBackend().getCustomer().getAddress());
      postalTextField.setText(IMatController.getIMatBackend().getCustomer().getPostCode());
      cityTextField.setText(IMatController.getIMatBackend().getCustomer().getPostAddress());
      cardNumberTextField.setText(IMatController.getIMatBackend().getCreditCard().getCardNumber());
      Integer year = IMatController.getIMatBackend().getCreditCard().getValidYear();
      if (year != 0) {
        yearTextField.setText(year.toString());
      } else {
        yearTextField.setText("");
      }
      Integer month = IMatController.getIMatBackend().getCreditCard().getValidMonth();
      if (month != 0) {
        monthTextField.setText(month.toString());
      } else {
        monthTextField.setText("");
      }
      Integer cvv = IMatController.getIMatBackend().getCreditCard().getVerificationCode();
      if (cvv != 0) {
        cvvTextField.setText(cvv.toString());
      } else {
        cvvTextField.setText("");
      }
    } else {
        try {
          String selectSQL = "select FIRSTNAME, LASTNAME, CIVIC, EMAIL, "+
              "PHONE, STREET, POSTAL, CITY, CARDNUMBER, VALIDYEAR, VALIDMONTH, "+
              "CVV from USERACCOUNT where USERNAME = ?";
          PreparedStatement psSelect = IMatController.getConnection().prepareStatement(selectSQL);
          psSelect.setString(1, IMatController.currentUser);
            ResultSet rs = psSelect.executeQuery();
            while (rs.next()) {
              firstNameTextField.setText(rs.getString("FIRSTNAME"));
              lastNameTextField.setText(rs.getString("LASTNAME"));
              civicTextField.setText(rs.getString("CIVIC"));
              emailTextField.setText(rs.getString("EMAIL"));
              phoneTextField.setText(rs.getString("PHONE"));
              streetTextField.setText(rs.getString("STREET"));
              postalTextField.setText(rs.getString("POSTAL"));
              cityTextField.setText(rs.getString("CITY"));
              cardNumberTextField.setText(rs.getString("CARDNUMBER"));
              yearTextField.setText(rs.getString("VALIDYEAR"));
              monthTextField.setText(rs.getString("VALIDMONTH"));
              cvvTextField.setText(rs.getString("CVV"));
            }
            rs.close();
            psSelect.close();
        } catch (SQLException ex) {
          Logger.getLogger(IMatController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
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
      if (IMatController.currentUser != null) {
        IMatController.updateAccount("FIRSTNAME", firstName);
      } else {
        IMatController.getIMatBackend().getCustomer().setFirstName(firstName);
      }
    } else {
      if (IMatController.currentUser != null) {
        IMatController.updateAccount("FIRSTNAME", null);
      } else {
        IMatController.getIMatBackend().getCustomer().setFirstName("");
      }
    }
    
    // Store last name if entered
    if (lastNameTextField.getLength() > 0) {
      String lastName = lastNameTextField.getText();
      if (IMatController.currentUser != null) {
        IMatController.updateAccount("LASTNAME", lastName);
      } else {
        IMatController.getIMatBackend().getCustomer().setLastName(lastName);
      }
    } else {
      if (IMatController.currentUser != null) {
        IMatController.updateAccount("LASTNAME", null);
      } else {
        IMatController.getIMatBackend().getCustomer().setLastName("");
      }
    }
    
    // Check if civicTextField contains valid data
    // Ändra så att man väljer datum etc i combobox?
    if (civicTextField.getLength() > 0 && civicTextField.getLength() == 10) {
      try {
        Integer civic = Integer.parseInt(civicTextField.getText());
        civicLabel.setText("");
        if (IMatController.currentUser != null) {
          IMatController.updateAccount("CIVIC", civic.toString());
        } else {
          IMatController.getIMatBackend().getCustomer().setMobilePhoneNumber(civic.toString());
        }
      } catch (NumberFormatException e) {
        civicLabel.setText("Ange personnummer med 10 siffror");
      }
    } else if (civicTextField.getLength() > 0) {
      civicLabel.setText("Ange personnummer med 10 siffror");
    } else if (civicTextField.getLength() == 0) {
      civicLabel.setText("");
      if (IMatController.currentUser != null) {
        IMatController.updateAccount("CIVIC", null);
      } else {
        IMatController.getIMatBackend().getCustomer().setMobilePhoneNumber("");
      }
    }
    
    // Check if phoneTextField contains valid data
    if (phoneTextField.getLength() > 0 && phoneTextField.getLength() < 16) {
      try {
        Integer phone = Integer.parseInt(phoneTextField.getText());
        phoneLabel.setText("");
        if (IMatController.currentUser != null) {
          IMatController.updateAccount("PHONE", phone.toString());
        } else {
          phoneTextField.setText(IMatController.getIMatBackend().getCustomer().getPhoneNumber());
        }
      } catch (NumberFormatException e) {
        phoneLabel.setText("Ange maximalt 15 siffror");
      }
    } else if (phoneTextField.getLength() == 0) {
      phoneLabel.setText("");
      if (IMatController.currentUser != null) {
        IMatController.updateAccount("PHONE", null);
      } else {
        IMatController.getIMatBackend().getCustomer().setPhoneNumber("");
      }
    }
    
    // Store email address if entered
    if (emailTextField.getLength() > 0) {
      String email = emailTextField.getText();
      if (IMatController.currentUser != null) {
        IMatController.updateAccount("EMAIL", email);
      } else {
        IMatController.getIMatBackend().getCustomer().setEmail(email);
      }
    } else {
      if (IMatController.currentUser != null) {
        IMatController.updateAccount("EMAIL", null);
      } else {
        IMatController.getIMatBackend().getCustomer().setEmail("");
      }
    }
    
    // Store street address if entered
    if (streetTextField.getLength() > 0) {
      String street = streetTextField.getText();
      if (IMatController.currentUser != null) {
        IMatController.updateAccount("STREET", street);
      } else {
        IMatController.getIMatBackend().getCustomer().setAddress(street);
      }
    } else {
      if (IMatController.currentUser != null) {
        IMatController.updateAccount("STREET", null);
      } else {
        IMatController.getIMatBackend().getCustomer().setAddress("");
      }
    }
    
    // Check if postalTextField contains valid data
    if (postalTextField.getLength() > 0 && postalTextField.getLength() == 5) {
      try {
        Integer postal = Integer.parseInt(postalTextField.getText());
        postalLabel.setText("");
        if (IMatController.currentUser != null) {
          IMatController.updateAccount("POSTAL", postal.toString());
        } else {
          IMatController.getIMatBackend().getCustomer().setPostCode(postal.toString());
        }
      } catch (NumberFormatException e) {
        postalLabel.setText("Ange postadress med 5 stycken siffror");
      }
    } else if (postalTextField.getLength() > 0) {
      postalLabel.setText("Ange postadress med 5 stycken siffror");
    } else if (postalTextField.getLength() == 0) {
      postalLabel.setText("");
      if (IMatController.currentUser != null) {
        IMatController.updateAccount("POSTAL", null);
      } else {
        IMatController.getIMatBackend().getCustomer().setPostCode("");
      }
    }
    
    // Store name of city if entered
    if (cityTextField.getLength() > 0) {
      String city = cityTextField.getText();
      if (IMatController.currentUser != null) {
        IMatController.updateAccount("CITY", city);
      } else {
        IMatController.getIMatBackend().getCustomer().setPostAddress(city);
      }
    } else {
      if (IMatController.currentUser != null) {
        IMatController.updateAccount("CITY", null);
      } else {
        IMatController.getIMatBackend().getCustomer().setPostAddress("");
      }
    }
    
    // Check if cardNumberTextField contains valid data
    if (cardNumberTextField.getLength() > 0) {
      try {
        Integer cardNumber = Integer.parseInt(cardNumberTextField.getText());
        cardNumberLabel.setText("");
        if (IMatController.currentUser != null) {
          IMatController.updateAccount("CARDNUMBER", cardNumber.toString());
        } else {
          IMatController.getIMatBackend().getCreditCard().setCardNumber(cardNumber.toString());
        }
      } catch (NumberFormatException e) {
        cardNumberLabel.setText("Ange kortnummer med siffror");
      }
    } else if (cardNumberTextField.getLength() == 0) {
      cardNumberLabel.setText("");
      if (IMatController.currentUser != null) {
        IMatController.updateAccount("CARDNUMBER", null);
      } else {
        IMatController.getIMatBackend().getCreditCard().setCardNumber("");
      }
    }
    
    // Check if yearTextField contains valid data
    if (yearTextField.getLength() > 0 && yearTextField.getLength() == 2) {
      try {
        Integer validYear = Integer.parseInt(yearTextField.getText());
        yearLabel.setText("");
        if (IMatController.currentUser != null) {
          IMatController.updateAccount("VALIDYEAR", validYear.toString());
        } else {
          IMatController.getIMatBackend().getCreditCard().setValidYear(validYear);
        }
      } catch (NumberFormatException e) {
        yearLabel.setText("Ange år med två siffror");
      }
    } else if (yearTextField.getLength() > 0) {
      yearLabel.setText("Ange år med två siffror");
    } else if (yearTextField.getLength() == 0) {
      yearLabel.setText("");
      if (IMatController.currentUser != null) {
        IMatController.updateAccount("VALIDYEAR", null);
      } else {
        IMatController.getIMatBackend().getCreditCard().setValidYear(00);
      }
    }
    
    // Check if monthTextField contains valid data
    if (monthTextField.getLength() > 0 && monthTextField.getLength() == 2) {
      try {
        Integer validMonth = Integer.parseInt(monthTextField.getText());
        monthLabel.setText("");
        if (IMatController.currentUser != null) {
          IMatController.updateAccount("VALIDMONTH", validMonth.toString());
        } else {
          IMatController.getIMatBackend().getCreditCard().setValidMonth(validMonth);
        }
      } catch (NumberFormatException e) {
        monthLabel.setText("Ange månad med två siffror");
      }
    } else if (monthTextField.getLength() > 0) {
      monthLabel.setText("Ange månad med två siffror");
    } else if (monthTextField.getLength() == 0) {
      monthLabel.setText("");
      if (IMatController.currentUser != null) {
        IMatController.updateAccount("VALIDMONTH", null);
      } else {
        IMatController.getIMatBackend().getCreditCard().setValidMonth(00);
      }
    }
    
    // Check if cvvTextField contains valid data
    if (cvvTextField.getLength() > 0 && cvvTextField.getLength() == 3) {
      try {
        Integer cvv = Integer.parseInt(cvvTextField.getText());
        cvvLabel.setText("");
        if (IMatController.currentUser != null) {
          IMatController.updateAccount("CVV", cvv.toString());
        } else {
          IMatController.getIMatBackend().getCreditCard().setVerificationCode(cvv);
        }
      } catch (NumberFormatException e) {
        cvvLabel.setText("Ange cvv med tre siffror");
      }
    } else if (cvvTextField.getLength() > 0) {
      cvvLabel.setText("Ange cvv med tre siffror");
    } else if (cvvTextField.getLength() == 0) {
      cvvLabel.setText("");
      if (IMatController.currentUser != null) {
        IMatController.updateAccount("CVV", null);
      } else {
        IMatController.getIMatBackend().getCreditCard().setVerificationCode(000);
      }
    }
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
  
  /**
   * Used for hiding panes in the whole tree.
   */
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
   * Display the user's order history.
   */
  @FXML
  public void showOrderHistory() {
    if (IMatController.currentUser != null) {
      try {
        String selectSQL = "select ID, DATE, COST, PRODUCT1, UNITS1, PRODUCT2, "+
          "UNITS2, PRODUCT3, UNITS3, PRODUCT4, UNITS4, PRODUCT5, UNITS5, PRODUCT6, "+
          "UNITS6, PRODUCT7, UNITS7, PRODUCT8, UNITS8, PRODUCT9, UNITS9, PRODUCT10, "+
          " UNITS10, PRODUCT11, UNITS11, PRODUCT12, UNITS12, PRODUCT13, UNITS13, "+
          " PRODUCT14, UNITS14 from ORDERS where USERNAME = ?";
          PreparedStatement psSelect = IMatController.getConnection().prepareStatement(selectSQL);
          psSelect.setString(1, IMatController.currentUser);
          ResultSet rs = psSelect.executeQuery();
          List<IMatOrder> imatOrderList = new ArrayList();
          while (rs.next()) {
            String storedDate = rs.getString("DATE");
            Date orderDate = new Date();
            Calendar cal = Calendar.getInstance();
            cal.setTime(orderDate);
            IMatOrder imatOrder = new IMatOrder(rs.getInt("ID"), rs.getInt("COST"), orderDate);
            imatOrderList.add(imatOrder);
            boolean iterate = true;
            int index = 0;
            while (iterate) {
              index++;
              if (!(rs.getString("PRODUCT"+index) == null)) {
                String productName = rs.getString("PRODUCT"+index);
                int units = rs.getInt("UNITS"+index);
                List<Product> products = IMatController.getIMatBackend().findProducts(productName);
                int price = (int)products.get(0).getPrice();
                int sum = price*units;
                IMatShoppingItem item = new IMatShoppingItem(products.get(0), units, sum);
                imatOrder.addShoppingItem(item);
              } else{
                iterate = false;
              }
            }

          }
          imatOrders = FXCollections.observableArrayList(imatOrderList);
          orderTable.setItems(imatOrders);
          rs.close();
          psSelect.close();
        } catch (SQLException ex) {
          Logger.getLogger(IMatController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } else {
      //getOrders();
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
  
  /**
   * Used for retrieving order from the backend. Used for the user that is
   * not logged in.
   */
  public void getOrders() {
    imatOrderList = new ArrayList();
    
    List<Order> order;
    IMatOrder imatOrder;
    order = IMatController.getIMatBackend().getOrders();
    for (int i = 0; i < order.size(); i++) {
      List<IMatShoppingItem> imatItems = new ArrayList();
      List<ShoppingItem> orderItems = order.get(i).getItems();
      int totalSum = 0;
      for (int j = 0; j < orderItems.size(); j++) {
        ShoppingItem item = orderItems.get(j);
        imatItems.add(new IMatShoppingItem(item.getProduct(), (int)item.getAmount(), (int)item.getTotal()));
        totalSum += (int)item.getTotal();
      }
      imatOrder = new IMatOrder(order.get(i).getOrderNumber(), totalSum, order.get(i).getDate());
      imatOrder.setShoppingItemList(imatItems);
      imatOrderList.add(imatOrder);
    }
    imatOrders = FXCollections.observableArrayList(imatOrderList);
    orderTable.setItems(imatOrders);
  }
  
  public void showIMatOrderDetails(List<IMatShoppingItem> list) {
    if (IMatController.currentUser != null) {
      productsInOrder = FXCollections.observableArrayList(list);
      productTable.setItems(productsInOrder);
      productTable.setVisible(true);
    } else {
      productsInOrder = FXCollections.observableArrayList(list);
      productTable.setItems(productsInOrder);
      productTable.setVisible(true);
      }
  }

}

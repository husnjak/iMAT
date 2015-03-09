/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imat.view;

import imat.IMat;
import imat.IMatController;
import imat.IMatOrder;
import imat.IMatProduct;
import imat.IMatProducts;
import imat.IMatShoppingCart;
import imat.IMatShoppingItem;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Pagination;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToolBar;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Callback;
import javafx.util.Duration;
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
  private TextField emailTextField;
  private TextField cvvTextField;
  private TextField firstNameTextField;
  private TextField lastNameTextField;
  private TextField civicTextField;
  @FXML
  private TextField phoneTextField;
  private TextField streetTextField;
  private TextField postalTextField;
  private TextField cityTextField;
  private TextField cardNumberTextField;
  private TextField yearTextField;
  private TextField monthTextField;
  private Label yearLabel;
  @FXML
  private Label phoneLabel;
  private Label postalLabel;
  private Label monthLabel;
  private Label cvvLabel;
  private Label cardNumberLabel;
  private Label civicLabel;
  
  private IMat imat;
  
  Order orderCart;
  
  ListView<IMatShoppingItem> lv;
  
  static int cellIndex = 0;
  
  // Used for deciding if to show the list view of products or not
  private boolean listView;
  
  private boolean removeFavorite = true;
  
  // Used for holding products from specific categories
  private List<Product> products;
  private StackPane handlaStackPane;
  
  // Used for handling products
  Product product;
  
  // Used for handling product images
  String imageName;
  
  // Used to identify which pane should be visible
  String currentPane = "startPage";
  
  String defaultStyle;
  
  // Stores order history
  private ObservableList<Order> orders;
  private ObservableList<IMatOrder> imatOrders;
  
  // Contains products in a given IMatOrder
  private ObservableList<IMatShoppingItem> productsInOrder;
  private ObservableList<IMatShoppingItem> productsInDatabase;
 
  @FXML
  private ScrollPane startPage;
 
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
  private Label createUserNameLabel;
  @FXML
  private TableView<IMatOrder> orderTable;
  @FXML
  private TableColumn<IMatOrder, Integer> orderIdColumn;
  @FXML
  private TableColumn<IMatOrder, LocalDate> orderDateColumn;
  @FXML
  private TableColumn<IMatOrder, Integer> orderCostColumn;
  
  // Used to keep track which number the recently added product has in order
  Integer productNr = 0;
  
  // Used to keep track the number of units of the added product
  Integer productUnits;
  
  // Stores the total sum of all products in the shopping cart
  int totalCostInt;
  
  // Contains the converted orders for a user who is not logged in
  List<IMatOrder> imatOrderList;
  
  // Contains shopping items related to a IMat order (for logged in users)
  List<IMatShoppingItem> imatItemList;
  
  // Used to keep track of which category is selected
  Product currentProductCategory;
  
  @FXML
  private TableView<IMatShoppingItem> productTable;
  @FXML
  private TableColumn<IMatShoppingItem, String> productNameColumn;
  @FXML
  private TableColumn<IMatShoppingItem, Integer> productUnitsColumn;
  @FXML
  private TableColumn<IMatShoppingItem, Integer> productCostColumn;
  @FXML
  private ScrollPane productScrollPane;
  @FXML
  private GridPane productGrid;
  @FXML
  private AnchorPane productPane;
  @FXML
  private Button buy;
  @FXML
  private ImageView productImage;
  @FXML
  private Label productLabel;
  @FXML
  private CheckBox productFavorite;
  @FXML
  private TextField textField;
  @FXML
  private Button plus;
  @FXML
  private Button minus;
  @FXML
  private Label totalCost;
  @FXML
  private AnchorPane productPane1;
  @FXML
  private Button buy1;
  @FXML
  private ImageView productImage1;
  @FXML
  private Label productLabel1;
  @FXML
  private CheckBox productFavorite1;
  @FXML
  private TextField textField1;
  @FXML
  private Button plus1;
  @FXML
  private Button minus1;
  @FXML
  private Label totalCost1;
  @FXML
  private AnchorPane productPane2;
  @FXML
  private Button buy2;
  @FXML
  private ImageView productImage2;
  @FXML
  private Label productLabel2;
  @FXML
  private CheckBox productFavorite2;
  @FXML
  private TextField textField2;
  @FXML
  private Button plus2;
  @FXML
  private Button minus2;
  @FXML
  private Label totalCost2;
  @FXML
  private AnchorPane productPane3;
  @FXML
  private Button buy3;
  @FXML
  private ImageView productImage3;
  @FXML
  private Label productLabel3;
  @FXML
  private CheckBox productFavorite3;
  @FXML
  private TextField textField3;
  @FXML
  private Button plus3;
  @FXML
  private Button minus3;
  @FXML
  private Label totalCost3;
  @FXML
  private AnchorPane productPane4;
  @FXML
  private Button buy4;
  @FXML
  private ImageView productImage4;
  @FXML
  private Label productLabel4;
  @FXML
  private CheckBox productFavorite4;
  @FXML
  private TextField textField4;
  @FXML
  private Button plus4;
  @FXML
  private Button minus4;
  @FXML
  private Label totalCost4;
  @FXML
  private AnchorPane productPane5;
  @FXML
  private Button buy5;
  @FXML
  private ImageView productImage5;
  @FXML
  private Label productLabel5;
  @FXML
  private CheckBox productFavorite5;
  @FXML
  private TextField textField5;
  @FXML
  private Button plus5;
  @FXML
  private Button minus5;
  @FXML
  private Label totalCost5;
  @FXML
  private AnchorPane productPane6;
  @FXML
  private Button buy6;
  @FXML
  private ImageView productImage6;
  @FXML
  private Label productLabel6;
  @FXML
  private CheckBox productFavorite6;
  @FXML
  private TextField textField6;
  @FXML
  private Button plus6;
  @FXML
  private Button minus6;
  @FXML
  private Label totalCost6;
  @FXML
  private AnchorPane productPane7;
  @FXML
  private Button buy7;
  @FXML
  private ImageView productImage7;
  @FXML
  private Label productLabel7;
  @FXML
  private CheckBox productFavorite7;
  @FXML
  private TextField textField7;
  @FXML
  private Button plus7;
  @FXML
  private Button minus7;
  @FXML
  private Label totalCost7;
  @FXML
  private AnchorPane productPane8;
  @FXML
  private Button buy8;
  @FXML
  private ImageView productImage8;
  @FXML
  private Label productLabel8;
  @FXML
  private CheckBox productFavorite8;
  @FXML
  private TextField textField8;
  @FXML
  private Button plus8;
  @FXML
  private Button minus8;
  @FXML
  private Label totalCost8;
  @FXML
  private AnchorPane productPane9;
  @FXML
  private Button buy9;
  @FXML
  private ImageView productImage9;
  @FXML
  private Label productLabel9;
  @FXML
  private CheckBox productFavorite9;
  @FXML
  private TextField textField9;
  @FXML
  private Button plus9;
  @FXML
  private Button minus9;
  @FXML
  private Label totalCost9;
  @FXML
  private AnchorPane productPane10;
  @FXML
  private Button buy10;
  @FXML
  private ImageView productImage10;
  @FXML
  private Label productLabel10;
  @FXML
  private CheckBox productFavorite10;
  @FXML
  private TextField textField10;
  @FXML
  private Button plus10;
  @FXML
  private Button minus10;
  @FXML
  private Label totalCost10;
  @FXML
  private AnchorPane productPane11;
  @FXML
  private Button buy11;
  @FXML
  private ImageView productImage11;
  @FXML
  private Label productLabel11;
  @FXML
  private CheckBox productFavorite11;
  @FXML
  private TextField textField11;
  @FXML
  private Button plus11;
  @FXML
  private Button minus11;
  @FXML
  private Label totalCost11;
  @FXML
  private Label categoryLabel;
  @FXML
  private AnchorPane varaListVyParent;
  @FXML
  private ToolBar toolBar;
  @FXML
  private ToggleButton kontouppgifterButton;
  @FXML
  private ToggleButton orderhistorikButton;
  @FXML
  private ToggleButton favoritvarorButton;
  @FXML
  private ScrollPane orderHistorikPane;
  @FXML
  private ScrollPane kontouppgifterPane;
  @FXML
  private ScrollPane favoriteScrollPane;
  @FXML
  private GridPane favoriteGrid;
  @FXML
  private AnchorPane favoritePane;
  @FXML
  private AnchorPane favoritePane1;
  @FXML
  private AnchorPane favoritePane2;
  @FXML
  private AnchorPane favoritePane3;
  @FXML
  private Button buy31;
  @FXML
  private ImageView productImage31;
  @FXML
  private Label productLabel31;
  @FXML
  private CheckBox productFavorite31;
  @FXML
  private TextField textField31;
  @FXML
  private Button plus31;
  @FXML
  private Button minus31;
  @FXML
  private Label totalCost31;
  @FXML
  private AnchorPane favoritePane4;
  @FXML
  private Button buy41;
  @FXML
  private ImageView productImage41;
  @FXML
  private Label productLabel41;
  @FXML
  private CheckBox productFavorite41;
  @FXML
  private TextField textField41;
  @FXML
  private Button plus41;
  @FXML
  private Button minus41;
  @FXML
  private Label totalCost41;
  @FXML
  private AnchorPane favoritePane5;
  @FXML
  private Button buy51;
  @FXML
  private ImageView productImage51;
  @FXML
  private Label productLabel51;
  @FXML
  private CheckBox productFavorite51;
  @FXML
  private TextField textField51;
  @FXML
  private Button plus51;
  @FXML
  private Button minus51;
  @FXML
  private Label totalCost51;
  @FXML
  private AnchorPane favoritePane6;
  @FXML
  private Button buy61;
  @FXML
  private ImageView productImage61;
  @FXML
  private Label productLabel61;
  @FXML
  private CheckBox productFavorite61;
  @FXML
  private TextField textField61;
  @FXML
  private Button plus61;
  @FXML
  private Button minus61;
  @FXML
  private Label totalCost61;
  @FXML
  private AnchorPane favoritePane7;
  @FXML
  private Button buy71;
  @FXML
  private ImageView productImage71;
  @FXML
  private Label productLabel71;
  @FXML
  private CheckBox productFavorite71;
  @FXML
  private TextField textField71;
  @FXML
  private Button plus71;
  @FXML
  private Button minus71;
  @FXML
  private Label totalCost71;
  @FXML
  private AnchorPane favoritePane8;
  @FXML
  private Button buy81;
  @FXML
  private ImageView productImage81;
  @FXML
  private Label productLabel81;
  @FXML
  private CheckBox productFavorite81;
  @FXML
  private TextField textField81;
  @FXML
  private Button plus81;
  @FXML
  private Button minus81;
  @FXML
  private Label totalCost81;
  @FXML
  private AnchorPane favoritePane9;
  @FXML
  private Button buy91;
  @FXML
  private ImageView productImage91;
  @FXML
  private Label productLabel91;
  @FXML
  private CheckBox productFavorite91;
  @FXML
  private TextField textField91;
  @FXML
  private Button plus91;
  @FXML
  private Button minus91;
  @FXML
  private Label totalCost91;
  @FXML
  private AnchorPane favoritePane10;
  @FXML
  private Button buy101;
  @FXML
  private ImageView productImage101;
  @FXML
  private Label productLabel101;
  @FXML
  private CheckBox productFavorite101;
  @FXML
  private TextField textField101;
  @FXML
  private Button plus101;
  @FXML
  private Button minus101;
  @FXML
  private Label totalCost101;
  @FXML
  private AnchorPane favoritePane11;
  @FXML
  private Button buy111;
  @FXML
  private ImageView productImage111;
  @FXML
  private Label productLabel111;
  @FXML
  private CheckBox productFavorite111;
  @FXML
  private TextField textField111;
  @FXML
  private Button plus111;
  @FXML
  private Button minus111;
  @FXML
  private Label totalCost111;
  @FXML
  private Button buyfavorite;
  @FXML
  private ImageView favoriteImage;
  @FXML
  private Label favoriteLabel;
  @FXML
  private CheckBox favoriteFavorite;
  @FXML
  private TextField textFieldFavorite;
  @FXML
  private Button plusfavorite;
  @FXML
  private Button minusfavorite;
  @FXML
  private Label totalCostFavorite;
  @FXML
  private TextField civicTextField1;
  @FXML
  private Label civicLabel1;
  @FXML
  private TextField yearTextField1;
  @FXML
  private Label yearLabel1;
  private TextField phoneTextField1;
  private Label phoneLabel1;
  @FXML
  private TextField postalTextField1;
  @FXML
  private Label postalLabel1;
  @FXML
  private TextField cityTextField1;
  @FXML
  private TextField streetTextField1;
  @FXML
  private TextField lastNameTextField1;
  @FXML
  private TextField cardNumberTextField1;
  @FXML
  private Label cardNumberLabel1;
  @FXML
  private TextField cvvTextField1;
  @FXML
  private Label cvvLabel1;
  @FXML
  private TextField firstNameTextField1;
  @FXML
  private Button paymentForOrderButton;
  private TextField emailTextField1;
  @FXML
  private TextField monthTextField1;
  private Label monthLabel1;
  private Hyperlink removeProductPaymentLink;
  private Label cityLabel1;
  @FXML
  private Label streetLabel1;
  @FXML
  private Label lastNameLabel1;
  @FXML
  private Label firstNameLabel1;
  private ListView<IMatShoppingItem> checkOutCartListView;
  static String action = "init";
  @FXML
  private ScrollPane receiptPane;
  @FXML
  private Label nameOrderLabel;
  @FXML
  private Label orderNumberReceiptLabel;
  @FXML
  private Label saveInformationLabel;
  @FXML
  private Button buy21;
  @FXML
  private ImageView productImage21;
  @FXML
  private Label productLabel21;
  @FXML
  private CheckBox productFavorite21;
  @FXML
  private TextField textField21;
  @FXML
  private Button plus21;
  @FXML
  private Button minus21;
  @FXML
  private Label totalCost21;
  
  ObservableList<IMatShoppingItem> nn;
  
  Label totalCheckoutSum;
  @FXML
  private Label totalCostCartLabel;
  
  FadeTransition fader;
  FadeTransition fader2;
  private Label newCivicLabel;
  private Label newPhoneLabel;
  private Label newPostalLabel;
  private Label newCardNumberLabel;
  private Label newYearLabel;
  private Label newMonthLabel;
  private Label newCVVLabel;
  @FXML
  private Label newRequirePaymentLabel;
  @FXML
  private ScrollPane testPagination;
  @FXML
  private Pagination pagination;
  @FXML
  private Label checkOutLabel;
  @FXML
  private Label summaLabel;
  @FXML
  private Text firstNameText1;
  @FXML
  private Text lastNameText1;
  @FXML
  private Text civicText1;
  @FXML
  private Text addressText1;
  @FXML
  private Text postCityText1;
  @FXML
  private Text cardNumberText1;
  @FXML
  private Text yearMonthText1;
  @FXML
  private Text slashText1;
  @FXML
  private Text cvvText1;
  
  FadeTransition fader3;
  FadeTransition fader4;
  FadeTransition fader5;
  FadeTransition fader6;
  FadeTransition fader7;
  FadeTransition fader8;
  FadeTransition fader9;
  FadeTransition fader10;
  
  @FXML
  private Label createPasswordLabel1;
  @FXML
  private Text firstNameText11;
  @FXML
  private TextField firstNameTextField11;
  @FXML
  private Label firstNameLabel11;
  @FXML
  private Text lastNameText11;
  @FXML
  private TextField lastNameTextField11;
  @FXML
  private Label lastNameLabel11;
  @FXML
  private Text civicText11;
  @FXML
  private TextField civicTextField11;
  @FXML
  private Label civicLabel11;
  @FXML
  private Text addressText11;
  @FXML
  private TextField streetTextField11;
  @FXML
  private Label streetLabel11;
  @FXML
  private Text postCityText11;
  @FXML
  private TextField postalTextField11;
  @FXML
  private TextField cityTextField11;
  @FXML
  private Label postalLabel11;
  @FXML
  private Text cardNumberText11;
  @FXML
  private TextField cardNumberTextField11;
  @FXML
  private Label cardNumberLabel11;
  @FXML
  private Text yearMonthText11;
  @FXML
  private TextField yearTextField11;
  @FXML
  private Text slashText11;
  @FXML
  private TextField monthTextField11;
  @FXML
  private Label yearLabel11;
  @FXML
  private Text cvvText11;
  @FXML
  private TextField cvvTextField11;
  @FXML
  private Label cvvLabel11;
  @FXML
  private Button saveInformationButton;
  
  boolean monthInfo = true;
  boolean yearInfo = true;
  
  public Integer getProductNr() {
    return productNr;
  }
  
  public void setProductNr(Integer productNr) {
    this.productNr = productNr;
  }
  
  public Integer getProductUnits() {
    return productUnits;
  }
  
  public ListView<IMatShoppingItem> getCheckoutView() {
    return lv;
  }
  
  public void setProductUnits(Integer productUnits) {
    this.productUnits = productUnits;
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
  
  public Label getTotalCostCartLabel() {
    return totalCostCartLabel;
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    products = new ArrayList<>();
    productTable.setMouseTransparent(true);
    varaListVyParent.setFocusTraversable(false);
    fader = createFader(saveInformationLabel);
    fader2 = createFader(newRequirePaymentLabel);
    fader3 = createFader(civicLabel1);
    fader4 = createFader(yearLabel1);
    fader5 = createFader(firstNameLabel1);
    fader6 = createFader(cardNumberLabel1);
    fader7 = createFader(cvvLabel1);
    fader8 = createFader(lastNameLabel1);
    fader9 = createFader(streetLabel1);
    fader10 = createFader(postalLabel1);
    
    firstNameTextField1.focusedProperty().addListener(new ChangeListener<Boolean>() {
      @Override
      public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
        if (newPropertyValue) {
          
        } else {
            if (firstNameTextField1.getLength() > 0) {
              firstNameTextField1.setStyle(defaultStyle);
            }
        }
      }
    });
    
    lastNameTextField1.focusedProperty().addListener(new ChangeListener<Boolean>() {
      @Override
      public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
        if (newPropertyValue) {
          
        } else {
            if (lastNameTextField1.getLength() > 0) {
              lastNameTextField1.setStyle(defaultStyle);
            }
        }
      }
    });
    
    civicTextField1.focusedProperty().addListener(new ChangeListener<Boolean>() {
      @Override
      public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
        if (newPropertyValue) {
          
        } else {
            if (civicTextField1.getLength() > 0) {
              civicTextField1.setStyle(defaultStyle);
            }
        }
      }
    });
      
    yearTextField1.focusedProperty().addListener(new ChangeListener<Boolean>() {
      @Override
      public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
        if (newPropertyValue) {
          
        } else {
            if (yearTextField1.getLength() > 0) {
              yearTextField1.setStyle(defaultStyle);
            }
        }
      }
    });
    
    monthTextField1.focusedProperty().addListener(new ChangeListener<Boolean>() {
      @Override
      public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
        if (newPropertyValue) {
          
        } else {
            if (monthTextField1.getLength() > 0) {
              monthTextField1.setStyle(defaultStyle);
            }
        }
      }
    });
    
    streetTextField1.focusedProperty().addListener(new ChangeListener<Boolean>() {
      @Override
      public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
        if (newPropertyValue) {
          
        } else {
            if (streetTextField1.getLength() > 0) {
              streetTextField1.setStyle(defaultStyle);
            }
        }
      }
    });
    
    cityTextField1.focusedProperty().addListener(new ChangeListener<Boolean>() {
      @Override
      public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
        if (newPropertyValue) {
          
        } else {
            if (cityTextField1.getLength() > 0) {
              cityTextField1.setStyle(defaultStyle);
            }
        }
      }
    });
    
    cvvTextField1.focusedProperty().addListener(new ChangeListener<Boolean>() {
      @Override
      public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
        if (newPropertyValue) {
          
        } else {
            if (cvvTextField1.getLength() > 0) {
              cvvTextField1.setStyle(defaultStyle);
            }
        }
      }
    });
    
    postalTextField1.focusedProperty().addListener(new ChangeListener<Boolean>() {
      @Override
      public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
        if (newPropertyValue) {
          
        } else {
            if (postalTextField1.getLength() > 0) {
              postalTextField1.setStyle(defaultStyle);
            }
        }
      }
    });
    
    cardNumberTextField1.focusedProperty().addListener(new ChangeListener<Boolean>() {
      @Override
      public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
        if (newPropertyValue) {
          
        } else {
            if (cardNumberTextField1.getLength() > 0) {
              cardNumberTextField1.setStyle(defaultStyle);
            }
        }
      }
    });
    
    createUserNameTextField.focusedProperty().addListener(new ChangeListener<Boolean>() {
      @Override
      public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
        if (newPropertyValue) {
          
        } else {
            if (createUserNameTextField.getLength() > 0) {
              createUserNameTextField.setStyle(defaultStyle);
              createUserNameLabel.setText("");
            }
        }
      }
    });
    
    createPasswordTextField.focusedProperty().addListener(new ChangeListener<Boolean>() {
      @Override
      public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
        if (newPropertyValue) {
          
        } else {
            if (createPasswordTextField.getLength() > 0) {
              createPasswordTextField.setStyle(defaultStyle);
              createPasswordLabel1.setText("");
            }
        }
      }
    });
    
    createPasswordRepeatTextField.focusedProperty().addListener(new ChangeListener<Boolean>() {
      @Override
      public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
        if (newPropertyValue) {
          
        } else {
            if (createPasswordRepeatTextField.getLength() > 0) {
              createPasswordRepeatTextField.setStyle(defaultStyle);
              createPasswordLabel.setText("");
            }
        }
      }
    });
    
    firstNameTextField11.focusedProperty().addListener(new ChangeListener<Boolean>() {
      @Override
      public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
        if (newPropertyValue) {
          
        } else {
            if (firstNameTextField11.getLength() > 0) {
              firstNameTextField11.setStyle(defaultStyle);
              firstNameLabel11.setText("");
            }
        }
      }
    });
    
    lastNameTextField11.focusedProperty().addListener(new ChangeListener<Boolean>() {
      @Override
      public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
        if (newPropertyValue) {
          
        } else {
            if (lastNameTextField11.getLength() > 0) {
              lastNameTextField11.setStyle(defaultStyle);
              lastNameLabel11.setText("");
            }
        }
      }
    });
    
    civicTextField11.focusedProperty().addListener(new ChangeListener<Boolean>() {
      @Override
      public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
        if (newPropertyValue) {
          
        } else {
            if (civicTextField11.getLength() > 0) {
              civicTextField11.setStyle(defaultStyle);
              civicLabel11.setText("");
            }
        }
      }
    });
    
    phoneTextField.focusedProperty().addListener(new ChangeListener<Boolean>() {
      @Override
      public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
        if (newPropertyValue) {
          
        } else {
            if (phoneTextField.getLength() > 0) {
              phoneTextField.setStyle(defaultStyle);
              phoneLabel.setText("");
            }
        }
      }
    });
        
    emailTextField.focusedProperty().addListener(new ChangeListener<Boolean>() {
      @Override
      public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
        if (newPropertyValue) {
          
        } else {
            if (emailTextField.getLength() > 0) {
              emailTextField.setStyle(defaultStyle);
              //emailLabel.setText("");
            }
        }
      }
    });
    
    streetTextField11.focusedProperty().addListener(new ChangeListener<Boolean>() {
      @Override
      public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
        if (newPropertyValue) {
          
        } else {
            if (streetTextField11.getLength() > 0) {
              streetTextField11.setStyle(defaultStyle);
              streetLabel11.setText("");
            }
        }
      }
    });
    
    postalTextField11.focusedProperty().addListener(new ChangeListener<Boolean>() {
      @Override
      public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
        if (newPropertyValue) {
          
        } else {
            if (postalTextField11.getLength() > 0) {
              postalTextField11.setStyle(defaultStyle);
              postalLabel11.setText("");
            }
        }
      }
    });
    
    cityTextField11.focusedProperty().addListener(new ChangeListener<Boolean>() {
      @Override
      public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
        if (newPropertyValue) {
          
        } else {
            if (cityTextField11.getLength() > 0) {
              cityTextField11.setStyle(defaultStyle);
              //cityLabel.setText("");
            }
        }
      }
    });
    
    cardNumberTextField11.focusedProperty().addListener(new ChangeListener<Boolean>() {
      @Override
      public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
        if (newPropertyValue) {
          
        } else {
            if (cardNumberTextField11.getLength() > 0) {
              cardNumberTextField11.setStyle(defaultStyle);
              cardNumberLabel11.setText("");
            }
        }
      }
    });
    
    cvvTextField11.focusedProperty().addListener(new ChangeListener<Boolean>() {
      @Override
      public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
        if (newPropertyValue) {
          
        } else {
            if (cvvTextField11.getLength() > 0) {
              cvvTextField11.setStyle(defaultStyle);
              cvvLabel11.setText("");
            }
        }
      }
    });
    
    monthTextField11.focusedProperty().addListener(new ChangeListener<Boolean>() {
      @Override
      public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
        if (newPropertyValue) {
          
        } else {
            if (monthTextField11.getLength() > 0) {
              monthTextField11.setStyle(defaultStyle);
              if (yearInfo) {
                yearLabel11.setText(""); 
              }
            }
        }
      }
    });
    
    yearTextField11.focusedProperty().addListener(new ChangeListener<Boolean>() {
      @Override
      public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
        if (newPropertyValue) {
          
        } else {
            if (yearTextField11.getLength() > 0) {
              yearTextField11.setStyle(defaultStyle);
              if (monthInfo) {
                yearLabel11.setText("");
              }
            }
        }
      }
    });
    
    productFavorite.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = products.get(0);
        if (IMatController.currentUser != null) {
          if (productFavorite.isSelected()) {
            int index = IMatController.getNumberOfRecords(product.toString());
            IMatController.addFavorite(product.toString(), ++index);
          } else {
            // Implement how to find correct index
            int index = 1;
            IMatController.removeFavorite(product.toString(), index);
          }
        } else {
          if (productFavorite.isSelected()) {
            IMatController.getIMatBackend().addFavorite(product);
          } else {
              if (removeFavorite) {
                IMatController.getIMatBackend().removeFavorite(product);
            }
            
          }
        }
      }
    });
    
    productFavorite1.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = products.get(1);
        if (IMatController.currentUser != null) {
          if (productFavorite1.isSelected()) {
            int index = IMatController.getNumberOfRecords(product.toString());
            IMatController.addFavorite(product.toString(), ++index);
          } else {
            // Implement how to find correct index
            int index = 1;
            IMatController.removeFavorite(product.toString(), index);
          }
        } else {
          if (productFavorite1.isSelected()) {
            IMatController.getIMatBackend().addFavorite(product);
          } else {
              if (removeFavorite) {
                IMatController.getIMatBackend().removeFavorite(product);
            }
          }
        }
      }
    });
    
    productFavorite2.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = products.get(2);
        if (IMatController.currentUser != null) {
          if (productFavorite2.isSelected()) {
            int index = IMatController.getNumberOfRecords(product.toString());
            IMatController.addFavorite(product.toString(), ++index);
          } else {
            // Implement how to find correct index
            int index = 1;
            IMatController.removeFavorite(product.toString(), index);
          }
        } else {
          if (productFavorite2.isSelected()) {
            IMatController.getIMatBackend().addFavorite(product);
          } else {
              if (removeFavorite) {
                IMatController.getIMatBackend().removeFavorite(product);
            }
          }
        }
      }
    });
    
    productFavorite3.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = products.get(3);
        if (IMatController.currentUser != null) {
          if (productFavorite3.isSelected()) {
            int index = IMatController.getNumberOfRecords(product.toString());
            IMatController.addFavorite(product.toString(), ++index);
          } else {
            // Implement how to find correct index
            int index = 1;
            IMatController.removeFavorite(product.toString(), index);
          }
        } else {
          if (productFavorite3.isSelected()) {
            IMatController.getIMatBackend().addFavorite(product);
          } else {
              if (removeFavorite) {
                IMatController.getIMatBackend().removeFavorite(product);
            }
          }
        }
      }
    });
    
    productFavorite4.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = products.get(4);
        if (IMatController.currentUser != null) {
          if (productFavorite4.isSelected()) {
            int index = IMatController.getNumberOfRecords(product.toString());
            IMatController.addFavorite(product.toString(), ++index);
          } else {
            // Implement how to find correct index
            int index = 1;
            IMatController.removeFavorite(product.toString(), index);
          }
        } else {
          if (productFavorite4.isSelected()) {
            IMatController.getIMatBackend().addFavorite(product);
          } else {
              if (removeFavorite) {
                IMatController.getIMatBackend().removeFavorite(product);
            }
          }
        }
      }
    });
    
    productFavorite5.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = products.get(5);
        if (IMatController.currentUser != null) {
          if (productFavorite5.isSelected()) {
            int index = IMatController.getNumberOfRecords(product.toString());
            IMatController.addFavorite(product.toString(), ++index);
          } else {
            // Implement how to find correct index
            int index = 1;
            IMatController.removeFavorite(product.toString(), index);
          }
        } else {
          if (productFavorite5.isSelected()) {
            IMatController.getIMatBackend().addFavorite(product);
          } else {
              if (removeFavorite) {
                IMatController.getIMatBackend().removeFavorite(product);
            }
          }
        }
      }
    });
    
    productFavorite6.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = products.get(6);
        if (IMatController.currentUser != null) {
          if (productFavorite6.isSelected()) {
            int index = IMatController.getNumberOfRecords(product.toString());
            IMatController.addFavorite(product.toString(), ++index);
          } else {
            // Implement how to find correct index
            int index = 1;
            IMatController.removeFavorite(product.toString(), index);
          }
        } else {
          if (productFavorite6.isSelected()) {
            IMatController.getIMatBackend().addFavorite(product);
          } else {
              if (removeFavorite) {
                IMatController.getIMatBackend().removeFavorite(product);
            }
          }
        }
      }
    });
    
    productFavorite7.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = products.get(7);
        if (IMatController.currentUser != null) {
          if (productFavorite7.isSelected()) {
            int index = IMatController.getNumberOfRecords(product.toString());
            IMatController.addFavorite(product.toString(), ++index);
          } else {
            // Implement how to find correct index
            int index = 1;
            IMatController.removeFavorite(product.toString(), index);
          }
        } else {
          if (productFavorite7.isSelected()) {
            IMatController.getIMatBackend().addFavorite(product);
          } else {
              if (removeFavorite) {
                IMatController.getIMatBackend().removeFavorite(product);
            }
          }
        }
      }
    });
    
    productFavorite8.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = products.get(8);
        if (IMatController.currentUser != null) {
          if (productFavorite8.isSelected()) {
            int index = IMatController.getNumberOfRecords(product.toString());
            IMatController.addFavorite(product.toString(), ++index);
          } else {
            // Implement how to find correct index
            int index = 1;
            IMatController.removeFavorite(product.toString(), index);
          }
        } else {
          if (productFavorite8.isSelected()) {
            IMatController.getIMatBackend().addFavorite(product);
          } else {
              if (removeFavorite) {
                IMatController.getIMatBackend().removeFavorite(product);
            }
          }
        }
      }
    });
    
    productFavorite9.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = products.get(9);
        if (IMatController.currentUser != null) {
          if (productFavorite9.isSelected()) {
            int index = IMatController.getNumberOfRecords(product.toString());
            IMatController.addFavorite(product.toString(), ++index);
          } else {
            // Implement how to find correct index
            int index = 1;
            IMatController.removeFavorite(product.toString(), index);
          }
        } else {
          if (productFavorite9.isSelected()) {
            IMatController.getIMatBackend().addFavorite(product);
          } else {
              if (removeFavorite) {
                IMatController.getIMatBackend().removeFavorite(product);
            }
          }
        }
      }
    });
    
    productFavorite10.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = products.get(10);
        if (IMatController.currentUser != null) {
          if (productFavorite10.isSelected()) {
            int index = IMatController.getNumberOfRecords(product.toString());
            IMatController.addFavorite(product.toString(), ++index);
          } else {
            // Implement how to find correct index
            int index = 1;
            IMatController.removeFavorite(product.toString(), index);
          }
        } else {
          if (productFavorite10.isSelected()) {
            IMatController.getIMatBackend().addFavorite(product);
          } else {
              if (removeFavorite) {
                IMatController.getIMatBackend().removeFavorite(product);
            }
          }
        }
      }
    });
    
    productFavorite11.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = products.get(11);
        if (IMatController.currentUser != null) {
          if (productFavorite11.isSelected()) {
            int index = IMatController.getNumberOfRecords(product.toString());
            IMatController.addFavorite(product.toString(), ++index);
          } else {
            // Implement how to find correct index
            int index = 1;
            IMatController.removeFavorite(product.toString(), index);
          }
        } else {
          if (productFavorite11.isSelected()) {
            IMatController.getIMatBackend().addFavorite(product);
          } else {
               if (removeFavorite) {
                IMatController.getIMatBackend().removeFavorite(product);
            }
          }
        }
      }
    });
    
    plus.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = products.get(0);
        increment(textField, totalCost, product);
      }
    });
    
    minus.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = products.get(0);
        decrement(textField, totalCost, product);
      }
    });
    
    plus1.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = products.get(1);
        increment(textField1, totalCost1, product);
      }
    });
    
    minus1.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = products.get(1);
        decrement(textField1, totalCost1, product);
      }
    });
    
    plus2.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = products.get(2);
        increment(textField2, totalCost2, product);
      }
    });
    
    minus2.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = products.get(2);
        decrement(textField2, totalCost2, product);
      }
    });
    
    plus3.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = products.get(3);
        increment(textField3, totalCost3, product);
      }
    });
    
    minus3.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = products.get(3);
        decrement(textField3, totalCost3, product);
      }
    });
    
    plus4.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = products.get(4);
        increment(textField4, totalCost4, product);
      }
    });
    
    minus4.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = products.get(4);
        decrement(textField4, totalCost4, product);
      }
    });
    
    plus5.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = products.get(5);
        increment(textField5, totalCost5, product);
      }
    });
    
    minus5.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = products.get(5);
        decrement(textField5, totalCost5, product);
      }
    });
    
    plus6.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = products.get(6);
        increment(textField6, totalCost6, product);
      }
    });
    
    minus6.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = products.get(6);
        decrement(textField6, totalCost6, product);
      }
    });
    
    plus7.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = products.get(7);
        increment(textField7, totalCost7, product);
      }
    });
    
    minus7.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = products.get(7);
        decrement(textField7, totalCost7, product);
      }
    });
    
    plus8.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = products.get(8);
        increment(textField8, totalCost8, product);
      }
    });
    
    minus8.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = products.get(8);
        decrement(textField8, totalCost8, product);
      }
    });
    
    plus9.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = products.get(9);
        increment(textField9, totalCost9, product);
      }
    });
    
    minus9.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = products.get(9);
        decrement(textField9, totalCost9, product);
      }
    });
    
    plus10.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = products.get(10);
        increment(textField10, totalCost10, product);
      }
    });
    
    minus10.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = products.get(10);
        decrement(textField10, totalCost10, product);
      }
    });
    
    plus11.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = products.get(11);
        increment(textField11, totalCost11, product);
      }
    });
    
    minus11.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = products.get(11);
        decrement(textField11, totalCost11, product);
      }
    });

    createAccountButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        createUserNameLabel.setText("");
        createPasswordLabel.setText("");
        createPasswordLabel1.setText("");
        String username = createUserNameTextField.getText();
        String password = createPasswordTextField.getText();
        String repeatPassword = createPasswordRepeatTextField.getText();
        if (username.length() < 1 ) {
          createUserNameLabel.setText("Fyll i användarnamn");
          createUserNameTextField.setStyle("-fx-border-color: red; -fx-border-width: 1;");
        } 
        if (password.length() < 1) {
          createPasswordLabel1.setText("Fyll i lösenord");
          createPasswordTextField.setStyle("-fx-border-color: red; -fx-border-width: 1;");
        } 
        if (repeatPassword.length() < 1) {
          createPasswordLabel.setText("Fyll i lösenord");
          createPasswordRepeatTextField.setStyle("-fx-border-color: red; -fx-border-width: 1;");
        } else if (password.compareTo(repeatPassword) != 0) {
          createPasswordLabel.setText("Felaktigt lösenord");
          createPasswordRepeatTextField.setStyle("-fx-border-color: red; -fx-border-width: 1;");
        } else {
          String isValid = IMatController.validAccount(username, password);
          if (isValid.compareTo("invalidUsername") != 0) {
            createUserNameLabel.setText("Användarnamnet existerar redan");
            createUserNameTextField.setStyle("-fx-border-color: red; -fx-border-width: 1;");
          } else if (username.length() > 0) {
            IMatController.createAccount(username, password);
            IMatController.setCurrentUser(username);
            createUserNameTextField.setText("");
            createPasswordTextField.setText("");
            createPasswordRepeatTextField.setText("");
            changeToKontoView();
            imat.getToppController().setUsername(username);
            imat.getToppController().setPassword(password);
            imat.getToppController().changeLoginScreen();
            showOrderHistory();
            imat.getVarukorgController().populateCheckoutCart(imat.getVarukorgController().getIMatShoppingCart().getCart().getAllProducts());
            initCheckoutCart(imat.getVarukorgController().getIMatShoppingCart().getCart().getAllProducts());
          } else {
            createUserNameLabel.setText("Fyll i användarnamn");
            createUserNameTextField.setStyle("-fx-border-color: red; -fx-border-width: 1;");
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
    
    buy.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        boolean existed = false;
        imat.getVarukorgController().setAdded();
        Product product = products.get(0);
        ShoppingItem productItem;
        productUnits = Integer.parseInt(textField.getText());
        textField.setText("1");
        int cost = (int)product.getPrice();
        Integer oldCost = cost;
        totalCost.setText("Pris: " + (int)product.getPrice() + " kr");
        int sum = productUnits*cost;
        totalCostInt += sum;
        if (IMatController.currentUser != null) {
          productNr++;
          
          List<IMatShoppingItem> existingProducts = IMatShoppingCart.cart.getAllProducts();
          for (int i = 0; i < existingProducts.size(); i++) {
            if (existingProducts.get(i).getProduct().getName().equals(product.getName())) {
              int oldProductUnits = (int)existingProducts.get(i).getAmount();
              int newProductUnits = productUnits + oldProductUnits;
              existingProducts.get(i).setAmount(newProductUnits);
              existingProducts.get(i).addToSum(sum);
              IMatShoppingCart.cart.addCost(sum);
              existed = true;
            }
          }
          if (!existed) {
            IMatShoppingCart.cart.addShoppingItem(product, productUnits, sum);
          }
          imat.getVarukorgController().getIMatShoppingCart().getCart().setShoppingItemList(existingProducts); ;
          imat.getVarukorgController().updateTotalCost();
          List<IMatShoppingItem> list = imat.getVarukorgController().getIMatShoppingCart().getCart().getAllProducts();
          imat.getVarukorgController().populateCheckoutCart(list);
          initCheckoutCart(list);
          imat.getVarukorgController().getList().setFocusTraversable(true);
        } else {
          List<ShoppingItem> existingProducts = IMatController.getShoppingCart().getItems();
          for (int i = 0; i < existingProducts.size(); i++) {
            if (existingProducts.get(i).getProduct().getName().equals(product.getName())) {
              int oldProductUnits = (int)existingProducts.get(i).getAmount();
              int newProductUnits = productUnits + oldProductUnits;
              productItem = new ShoppingItem(product, newProductUnits);
              existed = true;
              IMatController.getShoppingCart().removeItem(i);
              IMatController.getShoppingCart().addItem(productItem);
            }
          }
          if (!existed) {
            IMatController.getShoppingCart().addItem(new ShoppingItem(product, productUnits));
          }
          Integer totalSum = (int)IMatController.getShoppingCart().getTotal();
          imat.getVarukorgController().updateTotalCostBackend(totalSum);
          imat.getVarukorgController().populateCheckoutCart(imat.getVarukorgController().convertBackendToIMat());
          initCheckoutCart(imat.getVarukorgController().convertBackendToIMat());
        }
      }
    });
    
    buy1.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        boolean existed = false;
        imat.getVarukorgController().setAdded();
        Product product = products.get(1);
        ShoppingItem productItem;
        productUnits = Integer.parseInt(textField1.getText());
        textField1.setText("1");
        int cost = (int)product.getPrice();
        int sum = productUnits*cost;
        totalCost1.setText("Pris: " + (int)product.getPrice() + " kr");
        totalCostInt += sum;
        if (IMatController.currentUser != null) {
          productNr++;
          
          List<IMatShoppingItem> existingProducts = IMatShoppingCart.cart.getAllProducts();
          for (int i = 0; i < existingProducts.size(); i++) {
            if (existingProducts.get(i).getProduct().getName().equals(product.getName())) {
              int oldProductUnits = (int)existingProducts.get(i).getAmount();
              int newProductUnits = productUnits + oldProductUnits;
              existingProducts.get(i).setAmount(newProductUnits);
              existingProducts.get(i).addToSum(sum);
              IMatShoppingCart.cart.addCost(sum);
              existed = true;
            }
          }
          if (!existed) {
            IMatShoppingCart.cart.addShoppingItem(product, productUnits, sum);
          }
          imat.getVarukorgController().getIMatShoppingCart().getCart().setShoppingItemList(existingProducts); ;
          imat.getVarukorgController().updateTotalCost();
          List<IMatShoppingItem> list = imat.getVarukorgController().getIMatShoppingCart().getCart().getAllProducts();
          imat.getVarukorgController().populateCheckoutCart(list);
          initCheckoutCart(list);
        } else {
          List<ShoppingItem> existingProducts = IMatController.getShoppingCart().getItems();
          for (int i = 0; i < existingProducts.size(); i++) {
            if (existingProducts.get(i).getProduct().getName().equals(product.getName())) {
              int oldProductUnits = (int)existingProducts.get(i).getAmount();
              int newProductUnits = productUnits + oldProductUnits;
              productItem = new ShoppingItem(product, newProductUnits);
              existed = true;
              IMatController.getShoppingCart().removeItem(i);
              IMatController.getShoppingCart().addItem(productItem);
            }
          }
          if (!existed) {
            IMatController.getShoppingCart().addItem(new ShoppingItem(product, productUnits));
          }
          Integer totalSum = (int)IMatController.getShoppingCart().getTotal();
          imat.getVarukorgController().updateTotalCostBackend(totalSum);
          imat.getVarukorgController().populateCheckoutCart(imat.getVarukorgController().convertBackendToIMat());
          initCheckoutCart(imat.getVarukorgController().convertBackendToIMat());
        }
      }
    });
    
    buy2.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        boolean existed = false;
        imat.getVarukorgController().setAdded();
        Product product = products.get(2);
        ShoppingItem productItem;
        productUnits = Integer.parseInt(textField2.getText());
        textField2.setText("1");
        int cost = (int)product.getPrice();
        int sum = productUnits*cost;
        totalCost2.setText("Pris: " + (int)product.getPrice() + " kr");
        totalCostInt += sum;
        if (IMatController.currentUser != null) {
          productNr++;
          
          List<IMatShoppingItem> existingProducts = IMatShoppingCart.cart.getAllProducts();
          for (int i = 0; i < existingProducts.size(); i++) {
            if (existingProducts.get(i).getProduct().getName().equals(product.getName())) {
              int oldProductUnits = (int)existingProducts.get(i).getAmount();
              int newProductUnits = productUnits + oldProductUnits;
              existingProducts.get(i).setAmount(newProductUnits);
              existingProducts.get(i).addToSum(sum);
              IMatShoppingCart.cart.addCost(sum);
              existed = true;
            }
          }
          if (!existed) {
            IMatShoppingCart.cart.addShoppingItem(product, productUnits, sum);
          }
          imat.getVarukorgController().getIMatShoppingCart().getCart().setShoppingItemList(existingProducts); ;
          imat.getVarukorgController().updateTotalCost();
          List<IMatShoppingItem> list = imat.getVarukorgController().getIMatShoppingCart().getCart().getAllProducts();
          imat.getVarukorgController().populateCheckoutCart(list);
          initCheckoutCart(list);
        } else {
          List<ShoppingItem> existingProducts = IMatController.getShoppingCart().getItems();
          for (int i = 0; i < existingProducts.size(); i++) {
            if (existingProducts.get(i).getProduct().getName().equals(product.getName())) {
              int oldProductUnits = (int)existingProducts.get(i).getAmount();
              int newProductUnits = productUnits + oldProductUnits;
              productItem = new ShoppingItem(product, newProductUnits);
              existed = true;
              IMatController.getShoppingCart().removeItem(i);
              IMatController.getShoppingCart().addItem(productItem);
            }
          }
          if (!existed) {
            IMatController.getShoppingCart().addItem(new ShoppingItem(product, productUnits));
          }
          Integer totalSum = (int)IMatController.getShoppingCart().getTotal();
          imat.getVarukorgController().updateTotalCostBackend(totalSum);
          imat.getVarukorgController().populateCheckoutCart(imat.getVarukorgController().convertBackendToIMat());
          initCheckoutCart(imat.getVarukorgController().convertBackendToIMat());
        }
    }
    });
    
    buy3.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        boolean existed = false;
        imat.getVarukorgController().setAdded();
        Product product = products.get(3);
        ShoppingItem productItem;
        productUnits = Integer.parseInt(textField3.getText());
        textField3.setText("1");
        int cost = (int)product.getPrice();
        int sum = productUnits*cost;
        totalCost3.setText("Pris: " + (int)product.getPrice() + " kr");
        totalCostInt += sum;
        if (IMatController.currentUser != null) {
          productNr++;
          
          List<IMatShoppingItem> existingProducts = IMatShoppingCart.cart.getAllProducts();
          for (int i = 0; i < existingProducts.size(); i++) {
            if (existingProducts.get(i).getProduct().getName().equals(product.getName())) {
              int oldProductUnits = (int)existingProducts.get(i).getAmount();
              int newProductUnits = productUnits + oldProductUnits;
              existingProducts.get(i).setAmount(newProductUnits);
              existingProducts.get(i).addToSum(sum);
              IMatShoppingCart.cart.addCost(sum);
              existed = true;
            }
          }
          if (!existed) {
            IMatShoppingCart.cart.addShoppingItem(product, productUnits, sum);
          }
          imat.getVarukorgController().getIMatShoppingCart().getCart().setShoppingItemList(existingProducts); ;
          imat.getVarukorgController().updateTotalCost();
          List<IMatShoppingItem> list = imat.getVarukorgController().getIMatShoppingCart().getCart().getAllProducts();
          imat.getVarukorgController().populateCheckoutCart(list);
          initCheckoutCart(list);
        } else {
          List<ShoppingItem> existingProducts = IMatController.getShoppingCart().getItems();
          for (int i = 0; i < existingProducts.size(); i++) {
            if (existingProducts.get(i).getProduct().getName().equals(product.getName())) {
              int oldProductUnits = (int)existingProducts.get(i).getAmount();
              int newProductUnits = productUnits + oldProductUnits;
              productItem = new ShoppingItem(product, newProductUnits);
              existed = true;
              IMatController.getShoppingCart().removeItem(i);
              IMatController.getShoppingCart().addItem(productItem);
            }
          }
          if (!existed) {
            IMatController.getShoppingCart().addItem(new ShoppingItem(product, productUnits));
          }
          Integer totalSum = (int)IMatController.getShoppingCart().getTotal();
          imat.getVarukorgController().updateTotalCostBackend(totalSum);
          imat.getVarukorgController().populateCheckoutCart(imat.getVarukorgController().convertBackendToIMat());
          initCheckoutCart(imat.getVarukorgController().convertBackendToIMat());
        }
    }
    });
    
    buy4.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        boolean existed = false;
        imat.getVarukorgController().setAdded();
        Product product = products.get(4);
        ShoppingItem productItem;
        productUnits = Integer.parseInt(textField4.getText());
        textField4.setText("1");
        int cost = (int)product.getPrice();
        int sum = productUnits*cost;
        totalCost4.setText("Pris: " + (int)product.getPrice() + " kr");
        totalCostInt += sum;
        if (IMatController.currentUser != null) {
          productNr++;
          
          List<IMatShoppingItem> existingProducts = IMatShoppingCart.cart.getAllProducts();
          for (int i = 0; i < existingProducts.size(); i++) {
            if (existingProducts.get(i).getProduct().getName().equals(product.getName())) {
              int oldProductUnits = (int)existingProducts.get(i).getAmount();
              int newProductUnits = productUnits + oldProductUnits;
              existingProducts.get(i).setAmount(newProductUnits);
              existingProducts.get(i).addToSum(sum);
              IMatShoppingCart.cart.addCost(sum);
              existed = true;
            }
          }
          if (!existed) {
            IMatShoppingCart.cart.addShoppingItem(product, productUnits, sum);
          }
          imat.getVarukorgController().getIMatShoppingCart().getCart().setShoppingItemList(existingProducts); ;
          imat.getVarukorgController().updateTotalCost();
          List<IMatShoppingItem> list = imat.getVarukorgController().getIMatShoppingCart().getCart().getAllProducts();
          imat.getVarukorgController().populateCheckoutCart(list);
          initCheckoutCart(list);
        } else {
          List<ShoppingItem> existingProducts = IMatController.getShoppingCart().getItems();
          for (int i = 0; i < existingProducts.size(); i++) {
            if (existingProducts.get(i).getProduct().getName().equals(product.getName())) {
              int oldProductUnits = (int)existingProducts.get(i).getAmount();
              int newProductUnits = productUnits + oldProductUnits;
              productItem = new ShoppingItem(product, newProductUnits);
              existed = true;
              IMatController.getShoppingCart().removeItem(i);
              IMatController.getShoppingCart().addItem(productItem);
            }
          }
          if (!existed) {
            IMatController.getShoppingCart().addItem(new ShoppingItem(product, productUnits));
          }
          Integer totalSum = (int)IMatController.getShoppingCart().getTotal();
          imat.getVarukorgController().updateTotalCostBackend(totalSum);
          imat.getVarukorgController().populateCheckoutCart(imat.getVarukorgController().convertBackendToIMat());
          initCheckoutCart(imat.getVarukorgController().convertBackendToIMat());
        }
    }
    });
    
    buy5.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        boolean existed = false;
        imat.getVarukorgController().setAdded();
        Product product = products.get(5);
        ShoppingItem productItem;
        productUnits = Integer.parseInt(textField5.getText());
        textField5.setText("1");
        int cost = (int)product.getPrice();
        int sum = productUnits*cost;
        totalCost5.setText("Pris: " + (int)product.getPrice() + " kr");
        totalCostInt += sum;
        if (IMatController.currentUser != null) {
          productNr++;
          
          List<IMatShoppingItem> existingProducts = IMatShoppingCart.cart.getAllProducts();
          for (int i = 0; i < existingProducts.size(); i++) {
            if (existingProducts.get(i).getProduct().getName().equals(product.getName())) {
              int oldProductUnits = (int)existingProducts.get(i).getAmount();
              int newProductUnits = productUnits + oldProductUnits;
              existingProducts.get(i).setAmount(newProductUnits);
              existingProducts.get(i).addToSum(sum);
              IMatShoppingCart.cart.addCost(sum);
              existed = true;
            }
          }
          if (!existed) {
            IMatShoppingCart.cart.addShoppingItem(product, productUnits, sum);
          }
          imat.getVarukorgController().getIMatShoppingCart().getCart().setShoppingItemList(existingProducts); ;
          imat.getVarukorgController().updateTotalCost();
          List<IMatShoppingItem> list = imat.getVarukorgController().getIMatShoppingCart().getCart().getAllProducts();
          imat.getVarukorgController().populateCheckoutCart(list);
          initCheckoutCart(list);
        } else {
          List<ShoppingItem> existingProducts = IMatController.getShoppingCart().getItems();
          for (int i = 0; i < existingProducts.size(); i++) {
            if (existingProducts.get(i).getProduct().getName().equals(product.getName())) {
              int oldProductUnits = (int)existingProducts.get(i).getAmount();
              int newProductUnits = productUnits + oldProductUnits;
              productItem = new ShoppingItem(product, newProductUnits);
              existed = true;
              IMatController.getShoppingCart().removeItem(i);
              IMatController.getShoppingCart().addItem(productItem);
            }
          }
          if (!existed) {
            IMatController.getShoppingCart().addItem(new ShoppingItem(product, productUnits));
          }
          Integer totalSum = (int)IMatController.getShoppingCart().getTotal();
          imat.getVarukorgController().updateTotalCostBackend(totalSum);
          imat.getVarukorgController().populateCheckoutCart(imat.getVarukorgController().convertBackendToIMat());
          initCheckoutCart(imat.getVarukorgController().convertBackendToIMat());
        }
    }
    });
    
    buy6.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        boolean existed = false;
        imat.getVarukorgController().setAdded();
        Product product = products.get(6);
        ShoppingItem productItem;
        productUnits = Integer.parseInt(textField6.getText());
        textField6.setText("1");
        int cost = (int)product.getPrice();
        int sum = productUnits*cost;
        totalCost6.setText("Pris: " + (int)product.getPrice() + " kr");
        totalCostInt += sum;
        if (IMatController.currentUser != null) {
          productNr++;
          
          List<IMatShoppingItem> existingProducts = IMatShoppingCart.cart.getAllProducts();
          for (int i = 0; i < existingProducts.size(); i++) {
            if (existingProducts.get(i).getProduct().getName().equals(product.getName())) {
              int oldProductUnits = (int)existingProducts.get(i).getAmount();
              int newProductUnits = productUnits + oldProductUnits;
              existingProducts.get(i).setAmount(newProductUnits);
              existingProducts.get(i).addToSum(sum);
              IMatShoppingCart.cart.addCost(sum);
              existed = true;
            }
          }
          if (!existed) {
            IMatShoppingCart.cart.addShoppingItem(product, productUnits, sum);
          }
          imat.getVarukorgController().getIMatShoppingCart().getCart().setShoppingItemList(existingProducts); ;
          imat.getVarukorgController().updateTotalCost();
          List<IMatShoppingItem> list = imat.getVarukorgController().getIMatShoppingCart().getCart().getAllProducts();
          imat.getVarukorgController().populateCheckoutCart(list);
          initCheckoutCart(list);
        } else {
          List<ShoppingItem> existingProducts = IMatController.getShoppingCart().getItems();
          for (int i = 0; i < existingProducts.size(); i++) {
            if (existingProducts.get(i).getProduct().getName().equals(product.getName())) {
              int oldProductUnits = (int)existingProducts.get(i).getAmount();
              int newProductUnits = productUnits + oldProductUnits;
              productItem = new ShoppingItem(product, newProductUnits);
              existed = true;
              IMatController.getShoppingCart().removeItem(i);
              IMatController.getShoppingCart().addItem(productItem);
            }
          }
          if (!existed) {
            IMatController.getShoppingCart().addItem(new ShoppingItem(product, productUnits));
          }
          Integer totalSum = (int)IMatController.getShoppingCart().getTotal();
          imat.getVarukorgController().updateTotalCostBackend(totalSum);
          imat.getVarukorgController().populateCheckoutCart(imat.getVarukorgController().convertBackendToIMat());
          initCheckoutCart(imat.getVarukorgController().convertBackendToIMat());
        }
    }
    });
    
    buy7.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        boolean existed = false;
        imat.getVarukorgController().setAdded();
        Product product = products.get(7);
        ShoppingItem productItem;
        productUnits = Integer.parseInt(textField7.getText());
        textField7.setText("1");
        int cost = (int)product.getPrice();
        int sum = productUnits*cost;
        totalCost7.setText("Pris: " + (int)product.getPrice() + " kr");
        totalCostInt += sum;
        if (IMatController.currentUser != null) {
          productNr++;
          
          List<IMatShoppingItem> existingProducts = IMatShoppingCart.cart.getAllProducts();
          for (int i = 0; i < existingProducts.size(); i++) {
            if (existingProducts.get(i).getProduct().getName().equals(product.getName())) {
              int oldProductUnits = (int)existingProducts.get(i).getAmount();
              int newProductUnits = productUnits + oldProductUnits;
              existingProducts.get(i).setAmount(newProductUnits);
              existingProducts.get(i).addToSum(sum);
              IMatShoppingCart.cart.addCost(sum);
              existed = true;
            }
          }
          if (!existed) {
            IMatShoppingCart.cart.addShoppingItem(product, productUnits, sum);
          }
          imat.getVarukorgController().getIMatShoppingCart().getCart().setShoppingItemList(existingProducts); ;
          imat.getVarukorgController().updateTotalCost();
          List<IMatShoppingItem> list = imat.getVarukorgController().getIMatShoppingCart().getCart().getAllProducts();
          imat.getVarukorgController().populateCheckoutCart(list);
          initCheckoutCart(list);
        } else {
          List<ShoppingItem> existingProducts = IMatController.getShoppingCart().getItems();
          for (int i = 0; i < existingProducts.size(); i++) {
            if (existingProducts.get(i).getProduct().getName().equals(product.getName())) {
              int oldProductUnits = (int)existingProducts.get(i).getAmount();
              int newProductUnits = productUnits + oldProductUnits;
              productItem = new ShoppingItem(product, newProductUnits);
              existed = true;
              IMatController.getShoppingCart().removeItem(i);
              IMatController.getShoppingCart().addItem(productItem);
            }
          }
          if (!existed) {
            IMatController.getShoppingCart().addItem(new ShoppingItem(product, productUnits));
          }
          Integer totalSum = (int)IMatController.getShoppingCart().getTotal();
          imat.getVarukorgController().updateTotalCostBackend(totalSum);
          imat.getVarukorgController().populateCheckoutCart(imat.getVarukorgController().convertBackendToIMat());
          initCheckoutCart(imat.getVarukorgController().convertBackendToIMat());
        }
    }
    });
    
    buy8.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        boolean existed = false;
        imat.getVarukorgController().setAdded();
        Product product = products.get(8);
        ShoppingItem productItem;
        productUnits = Integer.parseInt(textField8.getText());
        textField8.setText("1");
        int cost = (int)product.getPrice();
        int sum = productUnits*cost;
        totalCost8.setText("Pris: " + (int)product.getPrice() + " kr");
        totalCostInt += sum;
        if (IMatController.currentUser != null) {
          productNr++;
          
          List<IMatShoppingItem> existingProducts = IMatShoppingCart.cart.getAllProducts();
          for (int i = 0; i < existingProducts.size(); i++) {
            if (existingProducts.get(i).getProduct().getName().equals(product.getName())) {
              int oldProductUnits = (int)existingProducts.get(i).getAmount();
              int newProductUnits = productUnits + oldProductUnits;
              existingProducts.get(i).setAmount(newProductUnits);
              existingProducts.get(i).addToSum(sum);
              IMatShoppingCart.cart.addCost(sum);
              existed = true;
            }
          }
          if (!existed) {
            IMatShoppingCart.cart.addShoppingItem(product, productUnits, sum);
          }
          imat.getVarukorgController().getIMatShoppingCart().getCart().setShoppingItemList(existingProducts); ;
          imat.getVarukorgController().updateTotalCost();
          List<IMatShoppingItem> list = imat.getVarukorgController().getIMatShoppingCart().getCart().getAllProducts();
          imat.getVarukorgController().populateCheckoutCart(list);
          initCheckoutCart(list);
        } else {
          List<ShoppingItem> existingProducts = IMatController.getShoppingCart().getItems();
          for (int i = 0; i < existingProducts.size(); i++) {
            if (existingProducts.get(i).getProduct().getName().equals(product.getName())) {
              int oldProductUnits = (int)existingProducts.get(i).getAmount();
              int newProductUnits = productUnits + oldProductUnits;
              productItem = new ShoppingItem(product, newProductUnits);
              existed = true;
              IMatController.getShoppingCart().removeItem(i);
              IMatController.getShoppingCart().addItem(productItem);
            }
          }
          if (!existed) {
            IMatController.getShoppingCart().addItem(new ShoppingItem(product, productUnits));
          }
          Integer totalSum = (int)IMatController.getShoppingCart().getTotal();
          imat.getVarukorgController().updateTotalCostBackend(totalSum);
          imat.getVarukorgController().populateCheckoutCart(imat.getVarukorgController().convertBackendToIMat());
          initCheckoutCart(imat.getVarukorgController().convertBackendToIMat());
        }
    }
    });
    
    buy9.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        boolean existed = false;
        imat.getVarukorgController().setAdded();
        Product product = products.get(9);
        ShoppingItem productItem;
        productUnits = Integer.parseInt(textField9.getText());
        textField9.setText("1");
        int cost = (int)product.getPrice();
        int sum = productUnits*cost;
        totalCost9.setText("Pris: " + (int)product.getPrice() + " kr");
        totalCostInt += sum;
        if (IMatController.currentUser != null) {
          productNr++;
          
          List<IMatShoppingItem> existingProducts = IMatShoppingCart.cart.getAllProducts();
          for (int i = 0; i < existingProducts.size(); i++) {
            if (existingProducts.get(i).getProduct().getName().equals(product.getName())) {
              int oldProductUnits = (int)existingProducts.get(i).getAmount();
              int newProductUnits = productUnits + oldProductUnits;
              existingProducts.get(i).setAmount(newProductUnits);
              existingProducts.get(i).addToSum(sum);
              IMatShoppingCart.cart.addCost(sum);
              existed = true;
            }
          }
          if (!existed) {
            IMatShoppingCart.cart.addShoppingItem(product, productUnits, sum);
          }
          imat.getVarukorgController().getIMatShoppingCart().getCart().setShoppingItemList(existingProducts); ;
          imat.getVarukorgController().updateTotalCost();
          List<IMatShoppingItem> list = imat.getVarukorgController().getIMatShoppingCart().getCart().getAllProducts();
          imat.getVarukorgController().populateCheckoutCart(list);
          initCheckoutCart(list);
        } else {
          List<ShoppingItem> existingProducts = IMatController.getShoppingCart().getItems();
          for (int i = 0; i < existingProducts.size(); i++) {
            if (existingProducts.get(i).getProduct().getName().equals(product.getName())) {
              int oldProductUnits = (int)existingProducts.get(i).getAmount();
              int newProductUnits = productUnits + oldProductUnits;
              productItem = new ShoppingItem(product, newProductUnits);
              existed = true;
              IMatController.getShoppingCart().removeItem(i);
              IMatController.getShoppingCart().addItem(productItem);
            }
          }
          if (!existed) {
            IMatController.getShoppingCart().addItem(new ShoppingItem(product, productUnits));
          }
          Integer totalSum = (int)IMatController.getShoppingCart().getTotal();
          imat.getVarukorgController().updateTotalCostBackend(totalSum);
          imat.getVarukorgController().populateCheckoutCart(imat.getVarukorgController().convertBackendToIMat());
          initCheckoutCart(imat.getVarukorgController().convertBackendToIMat());
        }
    }
    });
  
    buy10.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        boolean existed = false;
        imat.getVarukorgController().setAdded();
        Product product = products.get(10);
        ShoppingItem productItem;
        productUnits = Integer.parseInt(textField10.getText());
        textField10.setText("1");
        int cost = (int)product.getPrice();
        int sum = productUnits*cost;
        totalCost10.setText("Pris: " + (int)product.getPrice() + " kr");
        totalCostInt += sum;
        if (IMatController.currentUser != null) {
          productNr++;
          
          List<IMatShoppingItem> existingProducts = IMatShoppingCart.cart.getAllProducts();
          for (int i = 0; i < existingProducts.size(); i++) {
            if (existingProducts.get(i).getProduct().getName().equals(product.getName())) {
              int oldProductUnits = (int)existingProducts.get(i).getAmount();
              int newProductUnits = productUnits + oldProductUnits;
              existingProducts.get(i).setAmount(newProductUnits);
              existingProducts.get(i).addToSum(sum);
              IMatShoppingCart.cart.addCost(sum);
              existed = true;
            }
          }
          if (!existed) {
            IMatShoppingCart.cart.addShoppingItem(product, productUnits, sum);
          }
          imat.getVarukorgController().getIMatShoppingCart().getCart().setShoppingItemList(existingProducts); ;
          imat.getVarukorgController().updateTotalCost();
          List<IMatShoppingItem> list = imat.getVarukorgController().getIMatShoppingCart().getCart().getAllProducts();
          imat.getVarukorgController().populateCheckoutCart(list);
          initCheckoutCart(list);
        } else {
          List<ShoppingItem> existingProducts = IMatController.getShoppingCart().getItems();
          for (int i = 0; i < existingProducts.size(); i++) {
            if (existingProducts.get(i).getProduct().getName().equals(product.getName())) {
              int oldProductUnits = (int)existingProducts.get(i).getAmount();
              int newProductUnits = productUnits + oldProductUnits;
              productItem = new ShoppingItem(product, newProductUnits);
              existed = true;
              IMatController.getShoppingCart().removeItem(i);
              IMatController.getShoppingCart().addItem(productItem);
            }
          }
          if (!existed) {
            IMatController.getShoppingCart().addItem(new ShoppingItem(product, productUnits));
          }
          Integer totalSum = (int)IMatController.getShoppingCart().getTotal();
          imat.getVarukorgController().updateTotalCostBackend(totalSum);
          imat.getVarukorgController().populateCheckoutCart(imat.getVarukorgController().convertBackendToIMat());
          initCheckoutCart(imat.getVarukorgController().convertBackendToIMat());
        }
    }
    });
    
    buy11.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        boolean existed = false;
        imat.getVarukorgController().setAdded();
        Product product = products.get(11);
        ShoppingItem productItem;
        productUnits = Integer.parseInt(textField11.getText());
        textField11.setText("1");
        int cost = (int)product.getPrice();
        int sum = productUnits*cost;
        totalCost11.setText("Pris: " + (int)product.getPrice() + " kr");
        totalCostInt += sum;
        if (IMatController.currentUser != null) {
          productNr++;
          
          List<IMatShoppingItem> existingProducts = IMatShoppingCart.cart.getAllProducts();
          for (int i = 0; i < existingProducts.size(); i++) {
            if (existingProducts.get(i).getProduct().getName().equals(product.getName())) {
              int oldProductUnits = (int)existingProducts.get(i).getAmount();
              int newProductUnits = productUnits + oldProductUnits;
              existingProducts.get(i).setAmount(newProductUnits);
              existingProducts.get(i).addToSum(sum);
              IMatShoppingCart.cart.addCost(sum);
              existed = true;
            }
          }
          if (!existed) {
            IMatShoppingCart.cart.addShoppingItem(product, productUnits, sum);
          }
          imat.getVarukorgController().getIMatShoppingCart().getCart().setShoppingItemList(existingProducts); ;
          imat.getVarukorgController().updateTotalCost();
          List<IMatShoppingItem> list = imat.getVarukorgController().getIMatShoppingCart().getCart().getAllProducts();
          imat.getVarukorgController().populateCheckoutCart(list);
          initCheckoutCart(list);
        } else {
          List<ShoppingItem> existingProducts = IMatController.getShoppingCart().getItems();
          for (int i = 0; i < existingProducts.size(); i++) {
            if (existingProducts.get(i).getProduct().getName().equals(product.getName())) {
              int oldProductUnits = (int)existingProducts.get(i).getAmount();
              int newProductUnits = productUnits + oldProductUnits;
              productItem = new ShoppingItem(product, newProductUnits);
              existed = true;
              IMatController.getShoppingCart().removeItem(i);
              IMatController.getShoppingCart().addItem(productItem);
            }
          }
          if (!existed) {
            IMatController.getShoppingCart().addItem(new ShoppingItem(product, productUnits));
          }
          Integer totalSum = (int)IMatController.getShoppingCart().getTotal();
          imat.getVarukorgController().updateTotalCostBackend(totalSum);
          imat.getVarukorgController().populateCheckoutCart(imat.getVarukorgController().convertBackendToIMat());
          initCheckoutCart(imat.getVarukorgController().convertBackendToIMat());
        }
    }
    });

    kontouppgifterButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        if (kontouppgifterButton.isFocused()) {
          kontouppgifterButton.setSelected(true);
        }
        if (orderhistorikButton.isSelected()) {
          orderhistorikButton.setSelected(false);
        }
        if (favoritvarorButton.isSelected()) {
          favoritvarorButton.setSelected(false);
        }
        changeToKontoView();
        event.consume();
      }
    });
    
    orderhistorikButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        if (orderhistorikButton.isFocused()) {
          orderhistorikButton.setSelected(true);
        } 
        if (kontouppgifterButton.isSelected()) {
          kontouppgifterButton.setSelected(false);
        }
        if (favoritvarorButton.isSelected()) {
          favoritvarorButton.setSelected(false);
        }
        changeToOrderHistorikView();
        event.consume();
      }
    });
    
    favoritvarorButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        if (favoritvarorButton.isFocused()) {
          favoritvarorButton.setSelected(true);
        }
        if (kontouppgifterButton.isSelected()) {
          kontouppgifterButton.setSelected(false);
        }
        if (orderhistorikButton.isSelected()) {
          orderhistorikButton.setSelected(false);
        }
        changeToFavoriteView();
        event.consume();
      }
    });
    
    paymentForOrderButton.addEventHandler(KeyEvent.KEY_PRESSED, keyListener);
    
    paymentForOrderButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        handleOrder();
      }
    });
    
    saveInformationButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        storeInformation();
      }
    });
    
    defaultStyle = postalTextField1.getStyle();
} 
  
  private EventHandler<KeyEvent> keyListener = new EventHandler<KeyEvent>() {
    @Override
    public void handle(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER) {
            handleOrder();
            event.consume();
        } 
    }
};
  
  public void handleOrder() {
    if (isRequiredFieldsEntered()) {
      if (IMatController.currentUser != null) {
        IMatController.createPaidOrder(imat.getVarukorgController().getIMatShoppingCart().getCart());
        imat.getCenterController().showOrderHistory();
        imat.getVarukorgController().setTotalCostLabel("0 kr");
        imat.getVarukorgController().populateCheckoutCart(imat.getVarukorgController().getIMatShoppingCart().getCart().getAllProducts());
        initCheckoutCart(imat.getVarukorgController().getIMatShoppingCart().getCart().getAllProducts());
        changeToReceiptView();
      } else {
        orderCart = IMatController.getIMatBackend().placeOrder();
        imat.getCenterController().getOrders();
        imat.getVarukorgController().setTotalCostLabel("0 kr");
        imat.getVarukorgController().populateCheckoutCart(imat.getVarukorgController().convertBackendToIMat());
        initCheckoutCart(imat.getVarukorgController().convertBackendToIMat());
        changeToReceiptView();
      }
    } else {
  // Inform user to enter required fields
  }
  }
  
  public boolean isRequiredFieldsEntered() {
    civicLabel1.setText("");
    firstNameLabel1.setText("");
    yearLabel1.setText("");
    cvvLabel1.setText("");
    cardNumberLabel1.setText("");
    postalLabel1.setText("");
    streetLabel1.setText("");
    lastNameLabel1.setText("");
    
    postalTextField1.setStyle(defaultStyle);
    firstNameTextField1.setStyle(defaultStyle);
    civicTextField1.setStyle(defaultStyle);
    lastNameTextField1.setStyle(defaultStyle);
    cardNumberTextField1.setStyle(defaultStyle);
    yearTextField1.setStyle(defaultStyle);
    monthTextField1.setStyle(defaultStyle);
    streetTextField1.setStyle(defaultStyle);
    cvvTextField1.setStyle(defaultStyle);
    cityTextField1.setStyle(defaultStyle);
    
    if (firstNameTextField1.getLength() > 0 && lastNameTextField1.getLength() > 0
        && civicTextField1.getLength() > 0 && postalTextField1.getLength() > 0
        && cityTextField1.getLength() > 0 && cardNumberTextField1.getLength() > 0
        && yearTextField1.getLength() > 0 && monthTextField1.getLength() > 0
        && cvvTextField1.getLength() > 0 && streetTextField1.getLength() > 0 ) {
      return true;
    } else {
      civicLabel1.setText("*");
      yearLabel1.setText("*");
      firstNameLabel1.setText("*");
      cardNumberLabel1.setText("*");
      cvvLabel1.setText("*");
      lastNameLabel1.setText("*");
      streetLabel1.setText("*");
      postalLabel1.setText("*");
      
      if (civicTextField1.getLength() == 0) {
        civicTextField1.setStyle("-fx-border-color: red; -fx-border-width: 1;");
      }
      if (firstNameTextField1.getLength() == 0) {
        firstNameTextField1.setStyle("-fx-border-color: red; -fx-border-width: 1;");
      }
      if (yearTextField1.getLength() == 0) {
        yearTextField1.setStyle("-fx-border-color: red; -fx-border-width: 1;");
      }
      if (monthTextField1.getLength() == 0) {
        monthTextField1.setStyle("-fx-border-color: red; -fx-border-width: 1;");
        //yearLabel1.setText("*");
      }
      //if (emailTextField1.getLength() == 0 && phoneTextField1.getLength() == 0) {
        //phoneLabel1.setText("*");
      //}
      if (cardNumberTextField1.getLength() == 0) {
        cardNumberTextField1.setStyle("-fx-border-color: red; -fx-border-width: 1;");
      }
      if (cvvTextField1.getLength() == 0) {
        cvvTextField1.setStyle("-fx-border-color: red; -fx-border-width: 1;");
      }
      if (lastNameTextField1.getLength() == 0) {
        lastNameTextField1.setStyle("-fx-border-color: red; -fx-border-width: 1;");
      }
      if (cityTextField1.getLength() == 0) {
        cityTextField1.setStyle("-fx-border-color: red; -fx-border-width: 1;");
      }
      if (streetTextField1.getLength() == 0) {
        streetTextField1.setStyle("-fx-border-color: red; -fx-border-width: 1;");
      }
      if (postalTextField1.getLength() == 0) {
        postalTextField1.setStyle("-fx-border-color: red; -fx-border-width: 1;");
      }
      newRequirePaymentLabel.setText("* Obligatoriska fält");
      fader2.play();
      fader3.play();
      fader4.play();
      fader5.play();
      fader6.play();
      fader7.play();
      fader8.play();
      fader9.play();
      fader10.play();
      return false;
    }
  }
  
  /**
   * Used when entering a new product category view.
   */
  public void setAllUnitsToOne() {
    textField.setText("1");
    textField1.setText("1");
    textField2.setText("1");
    textField3.setText("1");
    textField4.setText("1");
    textField5.setText("1");
    textField6.setText("1");
    textField7.setText("1");
    textField8.setText("1");
    textField9.setText("1");
    textField10.setText("1");
    textField11.setText("1");
  }
  
  public void setEmptyCartText() {
    VBox vbox1 = new VBox();
    Label holder = new Label("           Varukorgen är tom");
    vbox1.getChildren().add(holder);
    Pane holderPane = new Pane();
    holderPane.setMinHeight(250);
    vbox1.getChildren().add(holderPane);
    imat.getVarukorgController().getList().setPlaceholder(vbox1);
  }
  
  
  /**
   * Is called by the main application to give a reference back to itself.
   * 
   * @param imat
   */
  public void setMainApp(IMat imat) {
    this.imat = imat;
  }
  
  public Pagination getPagination() {
    return pagination;
  }
    
  /**
   * Load stored information for users.
   */
  @SuppressWarnings("ConvertToTryWithResources")
  public void loadCustomerInformation() {
    if (IMatController.currentUser == null) {
      firstNameTextField11.setText(IMatController.getIMatBackend().getCustomer().getFirstName());
      lastNameTextField11.setText(IMatController.getIMatBackend().getCustomer().getLastName());
      civicTextField11.setText(IMatController.getIMatBackend().getCustomer().getMobilePhoneNumber());
      phoneTextField.setText(IMatController.getIMatBackend().getCustomer().getPhoneNumber());
      emailTextField.setText(IMatController.getIMatBackend().getCustomer().getEmail());
      streetTextField11.setText(IMatController.getIMatBackend().getCustomer().getAddress());
      postalTextField11.setText(IMatController.getIMatBackend().getCustomer().getPostCode());
      cityTextField11.setText(IMatController.getIMatBackend().getCustomer().getPostAddress());
      cardNumberTextField11.setText(IMatController.getIMatBackend().getCreditCard().getCardNumber());
      Integer year = IMatController.getIMatBackend().getCreditCard().getValidYear();
      if (year != 0) {
        yearTextField11.setText(year.toString());
      } else {
        yearTextField11.setText("");
      }
      Integer month = IMatController.getIMatBackend().getCreditCard().getValidMonth();
      if (month != 0) {
        monthTextField11.setText(month.toString());
      } else {
        monthTextField11.setText("");
      }
      Integer cvv = IMatController.getIMatBackend().getCreditCard().getVerificationCode();
      if (cvv != 0) {
        cvvTextField11.setText(cvv.toString());
      } else {
        cvvTextField11.setText("");
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
              firstNameTextField11.setText(rs.getString("FIRSTNAME"));
              lastNameTextField11.setText(rs.getString("LASTNAME"));
              civicTextField11.setText(rs.getString("CIVIC"));
              emailTextField.setText(rs.getString("EMAIL"));
              phoneTextField.setText(rs.getString("PHONE"));
              streetTextField11.setText(rs.getString("STREET"));
              postalTextField11.setText(rs.getString("POSTAL"));
              cityTextField11.setText(rs.getString("CITY"));
              cardNumberTextField11.setText(rs.getString("CARDNUMBER"));
              yearTextField11.setText(rs.getString("VALIDYEAR"));
              monthTextField11.setText(rs.getString("VALIDMONTH"));
              cvvTextField11.setText(rs.getString("CVV"));
            }
            rs.close();
            psSelect.close();
        } catch (SQLException ex) {
          Logger.getLogger(IMatController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  }
  
    /**
   * Load stored information for users.
   */
  @SuppressWarnings("ConvertToTryWithResources")
  public void loadCustomerInformationPayment() {
    if (IMatController.currentUser == null) {
      firstNameTextField1.setText(IMatController.getIMatBackend().getCustomer().getFirstName());
      lastNameTextField1.setText(IMatController.getIMatBackend().getCustomer().getLastName());
      civicTextField1.setText(IMatController.getIMatBackend().getCustomer().getMobilePhoneNumber());
      //phoneTextField1.setText(IMatController.getIMatBackend().getCustomer().getPhoneNumber());
      //emailTextField1.setText(IMatController.getIMatBackend().getCustomer().getEmail());
      streetTextField1.setText(IMatController.getIMatBackend().getCustomer().getAddress());
      postalTextField1.setText(IMatController.getIMatBackend().getCustomer().getPostCode());
      cityTextField1.setText(IMatController.getIMatBackend().getCustomer().getPostAddress());
      cardNumberTextField1.setText(IMatController.getIMatBackend().getCreditCard().getCardNumber());
      Integer year = IMatController.getIMatBackend().getCreditCard().getValidYear();
      if (year != 0) {
        yearTextField1.setText(year.toString());
      } else {
        yearTextField1.setText("");
      }
      Integer month = IMatController.getIMatBackend().getCreditCard().getValidMonth();
      if (month != 0) {
        monthTextField1.setText(month.toString());
      } else {
        monthTextField1.setText("");
      }
      Integer cvv = IMatController.getIMatBackend().getCreditCard().getVerificationCode();
      if (cvv != 0) {
        cvvTextField1.setText(cvv.toString());
      } else {
        cvvTextField1.setText("");
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
              firstNameTextField1.setText(rs.getString("FIRSTNAME"));
              lastNameTextField1.setText(rs.getString("LASTNAME"));
              civicTextField1.setText(rs.getString("CIVIC"));
              //emailTextField1.setText(rs.getString("EMAIL"));
              //phoneTextField1.setText(rs.getString("PHONE"));
              streetTextField1.setText(rs.getString("STREET"));
              postalTextField1.setText(rs.getString("POSTAL"));
              cityTextField1.setText(rs.getString("CITY"));
              cardNumberTextField1.setText(rs.getString("CARDNUMBER"));
              yearTextField1.setText(rs.getString("VALIDYEAR"));
              monthTextField1.setText(rs.getString("VALIDMONTH"));
              cvvTextField1.setText(rs.getString("CVV"));
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
  public void storeInformation() {
    saveInformationLabel.setText("");
    // Store first name if entered
    if (firstNameTextField11.getLength() > 0) {
      String firstName = firstNameTextField11.getText();
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
    if (lastNameTextField11.getLength() > 0) {
      String lastName = lastNameTextField11.getText();
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
    if (civicTextField11.getLength() > 0 && civicTextField11.getLength() == 10) {
      try {
        Integer civic = Integer.parseInt(civicTextField11.getText());
        civicLabel11.setText("");
        if (IMatController.currentUser != null) {
          IMatController.updateAccount("CIVIC", civic.toString());
        } else {
          IMatController.getIMatBackend().getCustomer().setMobilePhoneNumber(civic.toString());
        }
      } catch (NumberFormatException e) {
        civicTextField11.setStyle("-fx-border-color: red; -fx-border-width: 1;");
        civicLabel11.setText("Ange med 10 siffror");
      }
    } else if (civicTextField11.getLength() > 0) {
      civicTextField11.setStyle("-fx-border-color: red; -fx-border-width: 1;");
      civicLabel11.setText("Ange med 10 siffror");
    } else if (civicTextField11.getLength() == 0) {
      civicLabel11.setText("");
      civicLabel11.setText("");
      civicTextField11.setStyle(defaultStyle);
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
        phoneTextField.setStyle("-fx-border-color: red; -fx-border-width: 1;");
        phoneLabel.setText("Ange med siffror");
      }
    } else if (phoneTextField.getLength() == 0) {
      phoneLabel.setText("");
      phoneTextField.setStyle(defaultStyle);
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
    if (streetTextField11.getLength() > 0) {
      String street = streetTextField11.getText();
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
    if (postalTextField11.getLength() > 0 && postalTextField11.getLength() == 5) {
      try {
        Integer postal = Integer.parseInt(postalTextField11.getText());
        postalLabel11.setText("");
        if (IMatController.currentUser != null) {
          IMatController.updateAccount("POSTAL", postal.toString());
        } else {
          IMatController.getIMatBackend().getCustomer().setPostCode(postal.toString());
        }
      } catch (NumberFormatException e) {
        postalTextField11.setStyle("-fx-border-color: red; -fx-border-width: 1;");
        postalLabel11.setText("Ange med 5 siffror");
      }
    } else if (postalTextField11.getLength() > 0) {
      postalTextField11.setStyle("-fx-border-color: red; -fx-border-width: 1;");
      postalLabel11.setText("Ange med 5 siffror");
    } else if (postalTextField11.getLength() == 0) {
      postalLabel11.setText("");
      postalLabel11.setText("");
      postalTextField11.setStyle(defaultStyle);
      if (IMatController.currentUser != null) {
        IMatController.updateAccount("POSTAL", null);
      } else {
        IMatController.getIMatBackend().getCustomer().setPostCode("");
      }
    }
    
    // Store name of city if entered
    if (cityTextField11.getLength() > 0) {
      String city = cityTextField11.getText();
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
    if (cardNumberTextField11.getLength() > 0) {
      try {
        Integer cardNumber = Integer.parseInt(cardNumberTextField11.getText());
        cardNumberLabel11.setText("");
        if (IMatController.currentUser != null) {
          IMatController.updateAccount("CARDNUMBER", cardNumber.toString());
        } else {
          IMatController.getIMatBackend().getCreditCard().setCardNumber(cardNumber.toString());
        }
      } catch (NumberFormatException e) {
        cardNumberTextField11.setStyle("-fx-border-color: red; -fx-border-width: 1;");
        cardNumberLabel11.setText("Ange med siffror");
      }
    } else if (cardNumberTextField11.getLength() == 0) {
      cardNumberLabel11.setText("");
      cardNumberTextField11.setStyle(defaultStyle);
      if (IMatController.currentUser != null) {
        IMatController.updateAccount("CARDNUMBER", null);
      } else {
        IMatController.getIMatBackend().getCreditCard().setCardNumber("");
      }
    }
    
    // Check if yearTextField contains valid data
    if (yearTextField11.getLength() > 0 && yearTextField11.getLength() == 2) {
      try {
        Integer validYear = Integer.parseInt(yearTextField11.getText());
        yearLabel11.setText("");
        if (IMatController.currentUser != null) {
          IMatController.updateAccount("VALIDYEAR", validYear.toString());
          yearInfo = true;
        } else {
          IMatController.getIMatBackend().getCreditCard().setValidYear(validYear);
          yearInfo = true;
        }
      } catch (NumberFormatException e) {
        yearLabel11.setText("Ange med två siffror");
        yearTextField11.setStyle("-fx-border-color: red; -fx-border-width: 1;");
        yearInfo = false;
      }
    } else if (yearTextField11.getLength() > 0) {
      yearTextField11.setStyle("-fx-border-color: red; -fx-border-width: 1;");
      yearLabel11.setText("Ange med två siffror");
      yearInfo = false;
    } else if (yearTextField11.getLength() == 0) {
      if (monthTextField11.getLength() == 0) {
        yearLabel11.setText("");
      }
      yearTextField11.setStyle(defaultStyle);
      yearInfo = true;
      if (IMatController.currentUser != null) {
        IMatController.updateAccount("VALIDYEAR", null);
      } else {
        IMatController.getIMatBackend().getCreditCard().setValidYear(00);
      }
    }
    
    // Check if monthTextField contains valid data
    if (monthTextField11.getLength() > 0 && monthTextField11.getLength() == 2) {
      try {
        Integer validMonth = Integer.parseInt(monthTextField11.getText());
        //monthLabel.setText("");
        if (IMatController.currentUser != null) {
          IMatController.updateAccount("VALIDMONTH", validMonth.toString());
          monthInfo = true;
        } else {
          IMatController.getIMatBackend().getCreditCard().setValidMonth(validMonth);
          monthInfo = true;
        }
      } catch (NumberFormatException e) {
        monthTextField11.setStyle("-fx-border-color: red; -fx-border-width: 1;");
        monthInfo = false;
      }
    } else if (monthTextField11.getLength() > 0) {
      monthTextField11.setStyle("-fx-border-color: red; -fx-border-width: 1;");
      yearLabel11.setText("Ange med två siffror");
      monthInfo = false;
    } else if (monthTextField11.getLength() == 0) {
      //monthLabel.setText("");
      if (yearTextField11.getLength() == 0) {
        yearLabel11.setText("");
      }
      //yearLabel11.setText("");
      monthTextField11.setStyle(defaultStyle);
      monthInfo = true;
      if (IMatController.currentUser != null) {
        IMatController.updateAccount("VALIDMONTH", null);
      } else {
        IMatController.getIMatBackend().getCreditCard().setValidMonth(00);
      }
    }
    
    // Check if cvvTextField contains valid data
    if (cvvTextField11.getLength() > 0 && cvvTextField11.getLength() == 3) {
      try {
        Integer cvv = Integer.parseInt(cvvTextField11.getText());
        cvvLabel11.setText("");
        if (IMatController.currentUser != null) {
          IMatController.updateAccount("CVV", cvv.toString());
        } else {
          IMatController.getIMatBackend().getCreditCard().setVerificationCode(cvv);
        }
      } catch (NumberFormatException e) {
        cvvLabel11.setText("Ange med tre siffror");
        cvvTextField11.setStyle("-fx-border-color: red; -fx-border-width: 1;");
      }
    } else if (cvvTextField11.getLength() > 0) {
      cvvTextField11.setStyle("-fx-border-color: red; -fx-border-width: 1;");
      cvvLabel11.setText("Ange med tre siffror");
    } else if (cvvTextField11.getLength() == 0) {
      cvvLabel11.setText("");
      cvvLabel11.setText("");
      cvvTextField11.setStyle(defaultStyle);
      if (IMatController.currentUser != null) {
        IMatController.updateAccount("CVV", null);
      } else {
        IMatController.getIMatBackend().getCreditCard().setVerificationCode(000);
      }
    }
    
    if (cvvLabel11.getText().equals("") && civicLabel11.getText().equals("") &&
        phoneLabel.getText().equals("") && postalLabel11.getText().equals("")
        && yearLabel11.getText().equals("")
        && cardNumberLabel11.getText().equals("")) {
      saveInformationLabel.setText("Sparat");
      fader.play();
    
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
  
  public void makeShoppingCartVisible() {
    imat.getVarukorgController().getCartBuyButton().setDisable(false);
    imat.getVarukorgController().getEmptyLink().setDisable(false);
    imat.getVarukorgController().getChangeLink().setDisable(false);
    if (IMatController.currentUser != null) {
      imat.getVarukorgController().updateTotalCostBackend(IMatShoppingCart.cart.getCost());
      imat.getVarukorgController().populateCheckoutCart(imat.getVarukorgController().getIMatShoppingCart().getCart().getAllProducts());
      Integer total = imat.getVarukorgController().getIMatShoppingCart().getCart().getCost();
      totalCostCartLabel.setText(total.toString()+" kr");
    } else {
      Integer totaler = (int)IMatController.getShoppingCart().getTotal();
      imat.getVarukorgController().setTotalCostLabel(totaler.toString() + " kr");
      imat.getVarukorgController().populateCheckoutCart(imat.getVarukorgController().convertBackendToIMat());
    }
    imat.getVarukorgController().getList().setStyle("-fx-background-color: white;");
    imat.getVarukorgController().getList().setPlaceholder(new Label("Varukorgen är tom"));
    imat.getVarukorgController().getSummaLabel().setDisable(false);
    imat.getVarukorgController().disableShoppingCartLabel(false);
  }
  
  /**
   * Changes the center view to the start page.
   */
  public void changeToStartPageView() {
    deSelect();
    if (imat.getVarukorgController().getCartBuyButton().isDisabled()) {
      makeShoppingCartVisible();
    }
    if (IMatController.currentUser != null) {
      if (imat.getVarukorgController().getIMatShoppingCart().getCart().getAllProducts().size() == 0) {
        setEmptyCartText();
      }
    } else {
        if (IMatController.getShoppingCart().getItems().size() == 0) {
          setEmptyCartText();
        }
    }

    getListVyPane().getChildren().remove(lv);
    int size = varaListVyParent.getChildren().size();
    currentPane = "startPage";
    String id;
    for (int i = 0; i < size; i++) {
      id = varaListVyParent.getChildren().get(i).getId();
      if (id.compareTo(currentPane) == 0) {
        varaListVyParent.getChildren().get(i).toFront();
        varaListVyParent.getChildren().get(i).setVisible(true);
      } else if (id.compareTo("toolBar") == 0) {
      } else {
        varaListVyParent.getChildren().get(i).setVisible(false);
      }
    }
  }
  
  /**
   * Used for hiding panes in the whole tree.
   */
  public void hideOtherPanes() {
    int size = varaListVyParent.getChildren().size();
    String id;
    for (int i = 0; i < size; i++) {
      id = varaListVyParent.getChildren().get(i).getId();
      if (id.compareTo(currentPane) == 0) {
        varaListVyParent.getChildren().get(i).toFront();
        varaListVyParent.getChildren().get(i).setVisible(true);
      } else {
        varaListVyParent.getChildren().get(i).setVisible(false);
      }
    }
  }
  
  /**
   * Changes the center view to the register page.
   */
  public void changeToRegisterView() {
    deSelect();
    createUserNameLabel.setText("");
    createPasswordLabel.setText("");
    createPasswordLabel1.setText("");
    createUserNameTextField.setText("");
    createPasswordTextField.setText("");
    createPasswordRepeatTextField.setText("");
    createUserNameTextField.setStyle(defaultStyle);
    createPasswordTextField.setStyle(defaultStyle);
    createPasswordRepeatTextField.setStyle(defaultStyle);
    getListVyPane().getChildren().remove(lv);
    if (imat.getVarukorgController().getCartBuyButton().isDisabled()) {
     makeShoppingCartVisible();
    }
    if (IMatController.currentUser != null) {
      if (imat.getVarukorgController().getIMatShoppingCart().getCart().getAllProducts().size() == 0) {
        setEmptyCartText();
      }
    } else {
        if (IMatController.getShoppingCart().getItems().size() == 0) {
          setEmptyCartText();
        }
    }
    //imat.getVarukorgController().populateCheckoutCart(imat.getVarukorgController().convertBackendToIMat());
    int size = varaListVyParent.getChildren().size();
    currentPane = "registerPane";
    String id;
    for (int i = 0; i < size; i++) {
      id = varaListVyParent.getChildren().get(i).getId();
      if (id.compareTo(currentPane) == 0) {
        varaListVyParent.getChildren().get(i).toFront();
        varaListVyParent.getChildren().get(i).setVisible(true);
      } else if (id.compareTo("toolBar") == 0) {
      } else {
        varaListVyParent.getChildren().get(i).setVisible(false);
      }
    }
  }
  
  /**
   * Display the user's order history.
   */
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
            LocalDate date = orderDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            Calendar cal = Calendar.getInstance();
            cal.setTime(orderDate);
            IMatOrder imatOrder = new IMatOrder(rs.getInt("ID"), rs.getInt("COST"), date);
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
    changeToCategoryView();
    setAllUnitsToOne();
    categoryLabel.setText("Ris");
    products = IMatController.getIMatProducts().getRiceList();
    product = products.get(0);
    productLabel.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage, imageName);
    totalCost.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(1);
    productLabel1.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage1, imageName);
    totalCost1.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(2);
    productLabel2.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage2, imageName);
    totalCost2.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(3);
    productLabel3.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage3, imageName);
    totalCost3.setText("Pris: " + (int)product.getPrice() + " kr");
    
    //show used product panes
    productPane.setVisible(true);
    productPane1.setVisible(true);
    productPane2.setVisible(true);
    productPane3.setVisible(true);
    
    //hides unused product panes
    productPane4.setVisible(false);
    productPane5.setVisible(false);
    productPane6.setVisible(false);
    productPane7.setVisible(false);
    productPane8.setVisible(false);
    productPane9.setVisible(false);
    productPane10.setVisible(false);
    productPane11.setVisible(false);
    
    populateFavorites(products);

  }
  
  /**
   * If Meat link has been clicked, show meat products in Handla view.
   */
  public void changeToMeatView() {
    changeToCategoryView();
    setAllUnitsToOne();
    categoryLabel.setText("Kött");
    products = IMatController.getIMatProducts().getMeatList();
    product = products.get(0);
    productLabel.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage, imageName);
    totalCost.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(1);
    productLabel1.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage1, imageName);
    totalCost1.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(2);
    productLabel2.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage2, imageName);
    totalCost2.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(3);
    productLabel3.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage3, imageName);
    totalCost3.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(4);
    productLabel4.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage4, imageName);
    totalCost4.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(5);
    productLabel5.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage5, imageName);
    totalCost5.setText("Pris: " + (int)product.getPrice() + " kr");
    
    //show used product panes
    productPane.setVisible(true);
    productPane1.setVisible(true);
    productPane2.setVisible(true);
    productPane3.setVisible(true);
    productPane4.setVisible(true);
    productPane5.setVisible(true);
    
    //hides unused product panes
    productPane6.setVisible(false);
    productPane7.setVisible(false);
    productPane8.setVisible(false);
    productPane9.setVisible(false);
    productPane10.setVisible(false);
    productPane11.setVisible(false);
    
    populateFavorites(products);
  }
  
  public void changeToPastaView() {
    changeToCategoryView();
    setAllUnitsToOne();
    categoryLabel.setText("Pasta");
    products = IMatController.getIMatProducts().getPastaList();
    product = products.get(0);
    productLabel.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage, imageName);
    totalCost.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(1);
    productLabel1.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage1, imageName);
    totalCost1.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(2);
    productLabel2.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage2, imageName);
    totalCost2.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(3);
    productLabel3.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage3, imageName);
    totalCost3.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(4);
    productLabel4.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage4, imageName);
    totalCost4.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(5);
    productLabel5.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage5, imageName);
    totalCost5.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(6);
    productLabel6.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage6, imageName);
    totalCost6.setText("Pris: " + (int)product.getPrice() + " kr");
    
    //show used product panes
    productPane.setVisible(true);
    productPane1.setVisible(true);
    productPane2.setVisible(true);
    productPane3.setVisible(true);
    productPane4.setVisible(true);
    productPane5.setVisible(true);
    productPane6.setVisible(true);
    
    //hides unused product panes
    productPane7.setVisible(false);
    productPane8.setVisible(false);
    productPane9.setVisible(false);
    productPane10.setVisible(false);
    productPane11.setVisible(false);
    
    populateFavorites(products);

    
  }
    
  public void changeToBreadView() {
    changeToCategoryView();
    setAllUnitsToOne();
    categoryLabel.setText("Bröd");
    products = IMatController.getIMatProducts().getBreadList();
    product = products.get(0);
    productLabel.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage, imageName);
    totalCost.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(1);
    productLabel1.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage1, imageName);
    totalCost1.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(2);
    productLabel2.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage2, imageName);
    totalCost2.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(3);
    productLabel3.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage3, imageName);
    totalCost3.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(4);
    productLabel4.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage4, imageName);
    totalCost4.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(5);
    productLabel5.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage5, imageName);
    totalCost5.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(6);
    productLabel6.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage6, imageName);
    totalCost6.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(7);
    productLabel7.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage7, imageName);
    totalCost7.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(8);
    productLabel8.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage8, imageName);
    totalCost8.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(9);
    productLabel9.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage9, imageName);
    totalCost9.setText("Pris: " + (int)product.getPrice() + " kr");
    
    //show used product panes
    productPane.setVisible(true);
    productPane1.setVisible(true);
    productPane2.setVisible(true);
    productPane3.setVisible(true);
    productPane4.setVisible(true);
    productPane5.setVisible(true);
    productPane6.setVisible(true);
    productPane7.setVisible(true);
    productPane8.setVisible(true);
    productPane9.setVisible(true);
    
    //hides unused product panes
    productPane10.setVisible(false);
    productPane11.setVisible(false);
    
    populateFavorites(products);
  }
  
  public void changeToDrinkView() {
    changeToCategoryView();
    setAllUnitsToOne();
    categoryLabel.setText("Dryck");
    products = IMatController.getIMatProducts().getDrinkList();
    product = products.get(0);
    productLabel.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage, imageName);
    totalCost.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(1);
    productLabel1.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage1, imageName);
    totalCost1.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(2);
    productLabel2.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage2, imageName);
    totalCost2.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(3);
    productLabel3.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage3, imageName);
    totalCost3.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(4);
    productLabel4.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage4, imageName);
    totalCost4.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(5);
    productLabel5.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage5, imageName);
    totalCost5.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(6);
    productLabel6.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage6, imageName);
    totalCost6.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(7);
    productLabel7.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage7, imageName);
    totalCost7.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(8);
    productLabel8.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage8, imageName);
    totalCost8.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(9);
    productLabel9.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage9, imageName);
    totalCost9.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(10);
    productLabel10.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage10, imageName);
    totalCost10.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(11);
    productLabel11.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage11, imageName);
    totalCost11.setText("Pris: " + (int)product.getPrice() + " kr");
    
    //show used product panes
    productPane.setVisible(true);
    productPane1.setVisible(true);
    productPane2.setVisible(true);
    productPane3.setVisible(true);
    productPane4.setVisible(true);
    productPane5.setVisible(true);
    productPane6.setVisible(true);
    productPane7.setVisible(true);
    productPane8.setVisible(true);
    productPane9.setVisible(true);
    productPane10.setVisible(true);
    productPane11.setVisible(true);
    
    populateFavorites(products);
  }
    
  public void changeToFruitView() {
    changeToCategoryView();
    setAllUnitsToOne();
    categoryLabel.setText("Frukt & bär");
    products = IMatController.getIMatProducts().getFruitList();
    product = products.get(0);
    productLabel.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage, imageName);
    totalCost.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(1);
    productLabel1.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage1, imageName);
    totalCost1.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(2);
    productLabel2.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage2, imageName);
    totalCost2.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(3);
    productLabel3.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage3, imageName);
    totalCost3.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(4);
    productLabel4.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage4, imageName);
    totalCost4.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(5);
    productLabel5.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage5, imageName);
    totalCost5.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(6);
    productLabel6.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage6, imageName);
    totalCost6.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(7);
    productLabel7.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage7, imageName);
    totalCost7.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(8);
    productLabel8.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage8, imageName);
    totalCost8.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(9);
    productLabel9.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage9, imageName);
    totalCost9.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(10);
    productLabel10.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage10, imageName);
    totalCost10.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(11);
    productLabel11.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage11, imageName);
    totalCost11.setText("Pris: " + (int)product.getPrice() + " kr");
    
    //show used product panes
    productPane.setVisible(true);
    productPane1.setVisible(true);
    productPane2.setVisible(true);
    productPane3.setVisible(true);
    productPane4.setVisible(true);
    productPane5.setVisible(true);
    productPane6.setVisible(true);
    productPane7.setVisible(true);
    productPane8.setVisible(true);
    productPane9.setVisible(true);
    productPane10.setVisible(true);
    productPane11.setVisible(true);
    
    populateFavorites(products);
  }
    
  public void changeToFishView() {
    changeToCategoryView();
    setAllUnitsToOne();
    categoryLabel.setText("Fisk");
    products = IMatController.getIMatProducts().getFishList();
    product = products.get(0);
    productLabel.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage, imageName);
    totalCost.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(1);
    productLabel1.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage1, imageName);
    totalCost1.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(2);
    productLabel2.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage2, imageName);
    totalCost2.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(3);
    productLabel3.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage3, imageName);
    totalCost3.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(4);
    productLabel4.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage4, imageName);
    totalCost4.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(5);
    productLabel5.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage5, imageName);
    totalCost5.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(6);
    productLabel6.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage6, imageName);
    totalCost6.setText("Pris: " + (int)product.getPrice() + " kr");
    
    //show used product panes
    productPane.setVisible(true);
    productPane1.setVisible(true);
    productPane2.setVisible(true);
    productPane3.setVisible(true);
    productPane4.setVisible(true);
    productPane5.setVisible(true);
    productPane6.setVisible(true);
    
    //hides unused product panes
    productPane7.setVisible(false);
    productPane8.setVisible(false);
    productPane9.setVisible(false);
    productPane10.setVisible(false);
    productPane11.setVisible(false);
    
    populateFavorites(products);
    }
    
  public void changeToVegetablesView() {
    changeToCategoryView();
    setAllUnitsToOne();
    categoryLabel.setText("Grönsaker");
    products = IMatController.getIMatProducts().getVegetableList();
    product = products.get(0);
    productLabel.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage, imageName);
    totalCost.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(1);
    productLabel1.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage1, imageName);
    totalCost1.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(2);
    productLabel2.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage2, imageName);
    totalCost2.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(3);
    productLabel3.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage3, imageName);
    totalCost3.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(4);
    productLabel4.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage4, imageName);
    totalCost4.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(5);
    productLabel5.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage5, imageName);
    totalCost5.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(6);
    productLabel6.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage6, imageName);
    totalCost6.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(7);
    productLabel7.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage7, imageName);
    totalCost7.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(8);
    productLabel8.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage8, imageName);
    totalCost8.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(9);
    productLabel9.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage9, imageName);
    totalCost9.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(10);
    productLabel10.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage10, imageName);
    totalCost10.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(11);
    productLabel11.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage11, imageName);
    totalCost11.setText("Pris: " + (int)product.getPrice() + " kr");
    
    //show used product panes
    productPane.setVisible(true);
    productPane1.setVisible(true);
    productPane2.setVisible(true);
    productPane3.setVisible(true);
    productPane4.setVisible(true);
    productPane5.setVisible(true);
    productPane6.setVisible(true);
    productPane7.setVisible(true);
    productPane8.setVisible(true);
    productPane9.setVisible(true);
    productPane10.setVisible(true);
    productPane11.setVisible(true);
    
    populateFavorites(products);
  }
    
  public void changeToSpiceView() {
    changeToCategoryView();
    setAllUnitsToOne();
    categoryLabel.setText("Kryddor & örter");
    products = IMatController.getIMatProducts().getSpiceList();
    product = products.get(0);
    productLabel.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage, imageName);
    totalCost.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(1);
    productLabel1.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage1, imageName);
    totalCost1.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(2);
    productLabel2.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage2, imageName);
    totalCost2.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(3);
    productLabel3.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage3, imageName);
    totalCost3.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(4);
    productLabel4.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage4, imageName);
    totalCost4.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(5);
    productLabel5.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage5, imageName);
    totalCost5.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(6);
    productLabel6.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage6, imageName);
    totalCost6.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(7);
    productLabel7.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage7, imageName);
    totalCost7.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(8);
    productLabel8.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage8, imageName);
    totalCost8.setText("Pris: " + (int)product.getPrice() + " kr");
    
    //show used product panes
    productPane.setVisible(true);
    productPane1.setVisible(true);
    productPane2.setVisible(true);
    productPane3.setVisible(true);
    productPane4.setVisible(true);
    productPane5.setVisible(true);
    productPane6.setVisible(true);
    productPane7.setVisible(true);
    productPane8.setVisible(true);
    
    //hides unused product panes
    productPane9.setVisible(false);
    productPane10.setVisible(false);
    productPane11.setVisible(false);
    
    populateFavorites(products);
    }
    
  public void changeToDairiesView() {
    changeToCategoryView();
    setAllUnitsToOne();
    categoryLabel.setText("Mejeriprodukter");
    products = IMatController.getIMatProducts().getDairieList();
    product = products.get(0);
    productLabel.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage, imageName);
    totalCost.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(1);
    productLabel1.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage1, imageName);
    totalCost1.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(2);
    productLabel2.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage2, imageName);
    totalCost2.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(3);
    productLabel3.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage3, imageName);
    totalCost3.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(4);
    productLabel4.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage4, imageName);
    totalCost4.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(5);
    productLabel5.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage5, imageName);
    totalCost5.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(6);
    productLabel6.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage6, imageName);
    totalCost6.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(7);
    productLabel7.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage7, imageName);
    totalCost7.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(8);
    productLabel8.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage8, imageName);
    totalCost8.setText("Pris: " + (int)product.getPrice() + " kr");
    
    //show used product panes
    productPane.setVisible(true);
    productPane1.setVisible(true);
    productPane2.setVisible(true);
    productPane3.setVisible(true);
    productPane4.setVisible(true);
    productPane5.setVisible(true);
    productPane6.setVisible(true);
    productPane7.setVisible(true);
    productPane8.setVisible(true);
    
    //hides unused product panes
    productPane9.setVisible(false);
    productPane10.setVisible(false);
    productPane11.setVisible(false);
    
    populateFavorites(products);
    }
    
  public void changeToNutsView() {
    changeToCategoryView();
    setAllUnitsToOne();
    categoryLabel.setText("Nötter & frön");
    products = IMatController.getIMatProducts().getNutsList();
    product = products.get(0);
    productLabel.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage, imageName);
    totalCost.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(1);
    productLabel1.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage1, imageName);
    totalCost1.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(2);
    productLabel2.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage2, imageName);
    totalCost2.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(3);
    productLabel3.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage3, imageName);
    totalCost3.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(4);
    productLabel4.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage4, imageName);
    totalCost4.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(5);
    productLabel5.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage5, imageName);
    totalCost5.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(6);
    productLabel6.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage6, imageName);
    totalCost6.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(7);
    productLabel7.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage7, imageName);
    totalCost7.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(8);
    productLabel8.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage8, imageName);
    totalCost8.setText("Pris: " + (int)product.getPrice() + " kr");
    
    //show used product panes
    productPane.setVisible(true);
    productPane1.setVisible(true);
    productPane2.setVisible(true);
    productPane3.setVisible(true);
    productPane4.setVisible(true);
    productPane5.setVisible(true);
    productPane6.setVisible(true);
    productPane7.setVisible(true);
    productPane8.setVisible(true);
    
    //hides unused product panes
    productPane9.setVisible(false);
    productPane10.setVisible(false);
    productPane11.setVisible(false);
    
    populateFavorites(products);
  }
    
  public void changeToPotatoView() {
    changeToCategoryView();
    setAllUnitsToOne();
    categoryLabel.setText("Potatis & rotfrukter");
    products = IMatController.getIMatProducts().getPotatoList();
    product = products.get(0);
    productLabel.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage, imageName);
    totalCost.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(1);
    productLabel1.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage1, imageName);
    totalCost1.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(2);
    productLabel2.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage2, imageName);
    totalCost2.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(3);
    productLabel3.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage3, imageName);
    totalCost3.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(4);
    productLabel4.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage4, imageName);
    totalCost4.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(5);
    productLabel5.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage5, imageName);
    totalCost5.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(6);
    productLabel6.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage6, imageName);
    totalCost6.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(7);
    productLabel7.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage7, imageName);
    totalCost7.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(8);
    productLabel8.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage8, imageName);
    totalCost8.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(9);
    productLabel9.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage9, imageName);
    totalCost9.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(10);
    productLabel10.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage10, imageName);
    totalCost10.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(11);
    productLabel11.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage11, imageName);
    totalCost11.setText("Pris: " + (int)product.getPrice() + " kr");
    
    //show used product panes
    productPane.setVisible(true);
    productPane1.setVisible(true);
    productPane2.setVisible(true);
    productPane3.setVisible(true);
    productPane4.setVisible(true);
    productPane5.setVisible(true);
    productPane6.setVisible(true);
    productPane7.setVisible(true);
    productPane8.setVisible(true);
    productPane9.setVisible(true);
    productPane10.setVisible(true);
    productPane11.setVisible(true);
    
    populateFavorites(products);
    }
    
  public void changeToSweetsView() {
    changeToCategoryView();
    setAllUnitsToOne();
    categoryLabel.setText("Sötsaker");
    products = IMatController.getIMatProducts().getSweetsList();
    product = products.get(0);
    productLabel.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage, imageName);
    totalCost.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(1);
    productLabel1.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage1, imageName);
    totalCost1.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(2);
    productLabel2.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage2, imageName);
    totalCost2.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(3);
    productLabel3.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage3, imageName);
    totalCost3.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(4);
    productLabel4.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage4, imageName);
    totalCost4.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(5);
    productLabel5.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage5, imageName);
    totalCost5.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(6);
    productLabel6.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage6, imageName);
    totalCost6.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(7);
    productLabel7.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage7, imageName);
    totalCost7.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(8);
    productLabel8.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage8, imageName);
    totalCost8.setText("Pris: " + (int)product.getPrice() + " kr");
    
    //show used product panes
    productPane.setVisible(true);
    productPane1.setVisible(true);
    productPane2.setVisible(true);
    productPane3.setVisible(true);
    productPane4.setVisible(true);
    productPane5.setVisible(true);
    productPane6.setVisible(true);
    productPane7.setVisible(true);
    productPane8.setVisible(true);
    
    //hides unused product panes
    productPane9.setVisible(false);
    productPane10.setVisible(false);
    productPane11.setVisible(false);
    
    
    populateFavorites(products);
    
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
      Date orderDate = order.get(i).getDate();
      LocalDate date = orderDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
      imatOrder = new IMatOrder(order.get(i).getOrderNumber(), totalSum, date);
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
  
  public void testAccountView() {
    deSelect();
    if (imat.getVarukorgController().getCartBuyButton().isDisabled()) {
      imat.getVarukorgController().getCartBuyButton().setDisable(false);
      Integer totaler = (int)IMatController.getShoppingCart().getTotal();
      imat.getVarukorgController().setTotalCostLabel(totaler.toString() + " kr");
      imat.getVarukorgController().getEmptyLink().setDisable(false);
      imat.getVarukorgController().getChangeLink().setDisable(false);
      imat.getVarukorgController().populateCheckoutCart(imat.getVarukorgController().convertBackendToIMat());
    }
    getListVyPane().getChildren().remove(lv);
    int size = varaListVyParent.getChildren().size();
    currentPane = "registerPane";
    String id;
    for (int i = 0; i < size; i++) {
      id = varaListVyParent.getChildren().get(i).getId();
      if (id.compareTo(currentPane) == 0) {
        varaListVyParent.getChildren().get(i).toFront();
        varaListVyParent.getChildren().get(i).setVisible(true);
      } else if (id.compareTo("toolBar") == 0) {
      } else {
        varaListVyParent.getChildren().get(i).setVisible(false);
      }
    }
    
    if (IMatController.currentUser != null) {
      if (imat.getVarukorgController().getIMatShoppingCart().getCart().getAllProducts().isEmpty()) {
        setEmptyCartText();
      }
    } else {
        if (IMatController.getShoppingCart().getItems().isEmpty()) {
          setEmptyCartText();
        }
    }
  }
  
  public void changeToKontoView() {
    if (imat.getVarukorgController().getCartBuyButton().isDisabled()) {
      makeShoppingCartVisible();
    }
    if (IMatController.currentUser != null) {
      if (imat.getVarukorgController().getIMatShoppingCart().getCart().getAllProducts().isEmpty()) {
        setEmptyCartText();
      }
    } else {
        if (IMatController.getShoppingCart().getItems().isEmpty()) {
          setEmptyCartText();
        }
    }
    if (!kontouppgifterButton.isSelected()) {
      kontouppgifterButton.setSelected(true);
    }
    if (orderhistorikButton.isSelected()) {
      orderhistorikButton.setSelected(false);
    }
    if (favoritvarorButton.isSelected()) {
      favoritvarorButton.setSelected(false);
    }
    getListVyPane().getChildren().remove(lv);
    int size = varaListVyParent.getChildren().size();
    currentPane = "kontouppgifterPane";
    String id;
    for (int i = 0; i < size; i++) {
      id = varaListVyParent.getChildren().get(i).getId();
      if (id.compareTo(currentPane) == 0) {
        varaListVyParent.getChildren().get(i).toFront();
        varaListVyParent.getChildren().get(i).setVisible(true);
      } else if (id.compareTo("toolBar") == 0) {
      } else {
        varaListVyParent.getChildren().get(i).setVisible(false);
      }
    }
    saveInformationLabel.setText("");
    if (!civicLabel11.getText().isEmpty()) {
      civicLabel11.setText("");
      civicTextField11.setText("");
      civicTextField11.setStyle(defaultStyle);
    }
    if (!cvvLabel11.getText().isEmpty()) {
      cvvLabel11.setText("");
      cvvTextField11.setText("");
      cvvTextField11.setStyle(defaultStyle);
    }
    if (!yearLabel11.getText().isEmpty()) {
      if (!monthTextField11.getStyle().equals(defaultStyle)) {
        monthTextField11.setText("");
        monthTextField11.setStyle(defaultStyle);
        yearLabel11.setText("");
      }
      if (!yearTextField11.getStyle().equals(defaultStyle)) {
        yearTextField11.setText("");
        yearTextField11.setStyle(defaultStyle);
        yearLabel11.setText("");
      }
    }
    
    if (!postalLabel11.getText().isEmpty()) {
      postalLabel11.setText("");
      postalTextField11.setText("");
      postalTextField11.setStyle(defaultStyle);
    }
    if (!cardNumberLabel11.getText().isEmpty()) {
      cardNumberLabel11.setText("");
      cardNumberTextField11.setText("");
      cardNumberTextField11.setStyle(defaultStyle);
    }
    if (!phoneLabel.getText().isEmpty()) {
      phoneLabel.setText("");
      phoneTextField.setText("");
      phoneTextField.setStyle(defaultStyle);
    }
    
  }
  
  public void changeToOrderHistorikView() {
    if (imat.getVarukorgController().getCartBuyButton().isDisabled()) {
      makeShoppingCartVisible();
    }
    if (IMatController.currentUser != null) {
      if (imat.getVarukorgController().getIMatShoppingCart().getCart().getAllProducts().isEmpty()) {
        setEmptyCartText();
      }
    } else {
        if (IMatController.getShoppingCart().getItems().isEmpty()) {
          setEmptyCartText();
        }
    }
    getListVyPane().getChildren().remove(lv);
    int size = varaListVyParent.getChildren().size();
    currentPane = "orderHistorikPane";
    String id;
    for (int i = 0; i < size; i++) {
      id = varaListVyParent.getChildren().get(i).getId();
      if (id.compareTo(currentPane) == 0) {
        varaListVyParent.getChildren().get(i).toFront();
        varaListVyParent.getChildren().get(i).setVisible(true);
      } else if (id.compareTo("toolBar") == 0) {
      } else {
        varaListVyParent.getChildren().get(i).setVisible(false);
      }
    }
  }
  
  public void changeToCategoryView() {
    deSelect();
    if (imat.getVarukorgController().getCartBuyButton().isDisabled()) {
      makeShoppingCartVisible();
    }
    if (IMatController.currentUser != null) {
      if (imat.getVarukorgController().getIMatShoppingCart().getCart().getAllProducts().isEmpty()) {
        setEmptyCartText();
      }
    } else {
        if (IMatController.getShoppingCart().getItems().isEmpty()) {
          setEmptyCartText();
        }
    }
    getListVyPane().getChildren().remove(lv);
    int size = varaListVyParent.getChildren().size();
    currentPane = "productScrollPane";
    String id;
    for (int i = 0; i < size; i++) {
      id = varaListVyParent.getChildren().get(i).getId();
      if (id.compareTo(currentPane) == 0) {
        varaListVyParent.getChildren().get(i).toFront();
        varaListVyParent.getChildren().get(i).setVisible(true);
      } else if (id.compareTo("toolBar") == 0) {
      } else {
        varaListVyParent.getChildren().get(i).setVisible(false);
      }
    }
    uncheck();
    
  }
  
  public void uncheck() {
    removeFavorite = false;
    if (productFavorite.isSelected()) {
      productFavorite.fire();
    }
  if (productFavorite1.isSelected()) {
      productFavorite1.fire();
    }
  if (productFavorite2.isSelected()) {
      productFavorite2.fire();
    }
  if (productFavorite3.isSelected()) {
      productFavorite3.fire();
    }
  if (productFavorite4.isSelected()) {
      productFavorite4.fire();
    }
  if (productFavorite5.isSelected()) {
      productFavorite5.fire();
    }
  if (productFavorite6.isSelected()) {
      productFavorite6.fire();
    }
  if (productFavorite7.isSelected()) {
      productFavorite7.fire();
    }
  if (productFavorite8.isSelected()) {
      productFavorite8.fire();
    }
  if (productFavorite9.isSelected()) {
      productFavorite9.fire();
    }
  if (productFavorite10.isSelected()) {
      productFavorite10.fire();
    }
  if (productFavorite11.isSelected()) {
      productFavorite11.fire();
    }
  removeFavorite = true;
      
  }
  
  public void changeToPaymentView() {
    deSelect();
    getListVyPane().getChildren().remove(lv);
    int size = varaListVyParent.getChildren().size();
    currentPane = "checkoutPane";
    String id;
    for (int i = 0; i < size; i++) {
      id = varaListVyParent.getChildren().get(i).getId();
      if (id.compareTo(currentPane) == 0) {
        varaListVyParent.getChildren().get(i).toFront();
        varaListVyParent.getChildren().get(i).setVisible(true);
      } else if (id.compareTo("toolBar") == 0) {
      } else {
        varaListVyParent.getChildren().get(i).setVisible(false);
      }
    }
    loadCustomerInformationPayment();
  }
  
  public void changeToFavoriteView() {
    if (imat.getVarukorgController().getCartBuyButton().isDisabled()) {
      makeShoppingCartVisible();
    }
    if (IMatController.currentUser != null) {
      if (imat.getVarukorgController().getIMatShoppingCart().getCart().getAllProducts().isEmpty()) {
        setEmptyCartText();
      }
    } else {
        if (IMatController.getShoppingCart().getItems().isEmpty()) {
          setEmptyCartText();
        }
    }
    getListVyPane().getChildren().remove(lv);
        changeToCategoryView();
        setAllUnitsToOne();
        
        products = IMatController.getIMatBackend().favorites();
        if (products.isEmpty()) {
            //hide unused products
            productPane.setVisible(false);
            productPane1.setVisible(false);
            productPane2.setVisible(false);
            productPane3.setVisible(false);
            productPane4.setVisible(false);
            productPane5.setVisible(false);
            productPane6.setVisible(false);
            productPane7.setVisible(false);
            productPane8.setVisible(false);
            productPane9.setVisible(false);
            productPane10.setVisible(false);
            productPane11.setVisible(false);
        }else if(products.size() == 1) {
            product = products.get(0);
            productLabel.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage, imageName);
            totalCost.setText("Pris: " + (int)product.getPrice() + " kr");
            //show used product panes
            productPane.setVisible(true);
            
            //hide unused product panes
            productPane1.setVisible(false);
            productPane2.setVisible(false);
            productPane3.setVisible(false);
            productPane4.setVisible(false);
            productPane5.setVisible(false);
            productPane6.setVisible(false);
            productPane7.setVisible(false);
            productPane8.setVisible(false);
            productPane9.setVisible(false);
            productPane10.setVisible(false);
            productPane11.setVisible(false);
            
        }else if(products.size() == 2) {
            product = products.get(0);
            productLabel.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage, imageName);
            totalCost.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(1);
            productLabel1.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage1, imageName);
            totalCost1.setText("Pris: " + (int)product.getPrice() + " kr");
            
            //show used product panes
            productPane.setVisible(true);
            productPane1.setVisible(true);
            
            //hide unused product panes
            productPane2.setVisible(false);
            productPane3.setVisible(false);
            productPane4.setVisible(false);
            productPane5.setVisible(false);
            productPane6.setVisible(false);
            productPane7.setVisible(false);
            productPane8.setVisible(false);
            productPane9.setVisible(false);
            productPane10.setVisible(false);
            productPane11.setVisible(false);
            
        }else if(products.size() == 3) {
            product = products.get(0);
            productLabel.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage, imageName);
            totalCost.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(1);
            productLabel1.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage1, imageName);
            totalCost1.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(2);
            productLabel2.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage2, imageName);
            totalCost2.setText("Pris: " + (int)product.getPrice() + " kr");
            
            //show used product panes
            productPane.setVisible(true);
            productPane1.setVisible(true);
            productPane2.setVisible(true);
            
            //hide unused product panes
            productPane3.setVisible(false);
            productPane4.setVisible(false);
            productPane5.setVisible(false);
            productPane6.setVisible(false);
            productPane7.setVisible(false);
            productPane8.setVisible(false);
            productPane9.setVisible(false);
            productPane10.setVisible(false);
            productPane11.setVisible(false);
            
        }else if(products.size() == 4) {
            product = products.get(0);
            productLabel.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage, imageName);
            totalCost.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(1);
            productLabel1.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage1, imageName);
            totalCost1.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(2);
            productLabel2.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage2, imageName);
            totalCost2.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(3);
            productLabel3.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage3, imageName);
            totalCost3.setText("Pris: " + (int)product.getPrice() + " kr");
            
            //show used product panes
            productPane.setVisible(true);
            productPane1.setVisible(true);
            productPane2.setVisible(true);
            productPane3.setVisible(true);
            
            //hide unused product panes
            productPane4.setVisible(false);
            productPane5.setVisible(false);
            productPane6.setVisible(false);
            productPane7.setVisible(false);
            productPane8.setVisible(false);
            productPane9.setVisible(false);
            productPane10.setVisible(false);
            productPane11.setVisible(false);
            
        }else if(products.size() == 5) {
            product = products.get(0);
            productLabel.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage, imageName);
            totalCost.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(1);
            productLabel1.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage1, imageName);
            totalCost1.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(2);
            productLabel2.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage2, imageName);
            totalCost2.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(3);
            productLabel3.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage3, imageName);
            totalCost3.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(4);
            productLabel4.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage4, imageName);
            totalCost4.setText("Pris: " + (int)product.getPrice() + " kr");
            
            //show used product panes
            productPane.setVisible(true);
            productPane1.setVisible(true);
            productPane2.setVisible(true);
            productPane3.setVisible(true);
            productPane4.setVisible(true);
            
            //hide unused product panes
            productPane5.setVisible(false);
            productPane6.setVisible(false);
            productPane7.setVisible(false);
            productPane8.setVisible(false);
            productPane9.setVisible(false);
            productPane10.setVisible(false);
            productPane11.setVisible(false);
            
        }else if(products.size() == 6) {
            product = products.get(0);
            productLabel.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage, imageName);
            totalCost.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(1);
            productLabel1.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage1, imageName);
            totalCost1.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(2);
            productLabel2.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage2, imageName);
            totalCost2.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(3);
            productLabel3.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage3, imageName);
            totalCost3.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(4);
            productLabel4.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage4, imageName);
            totalCost4.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(5);
            productLabel5.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage5, imageName);
            totalCost5.setText("Pris: " + (int)product.getPrice() + " kr");
            
            //show used product panes
            productPane.setVisible(true);
            productPane1.setVisible(true);
            productPane2.setVisible(true);
            productPane3.setVisible(true);
            productPane4.setVisible(true);
            productPane5.setVisible(true);
            
            //hide unused product panes
            productPane6.setVisible(false);
            productPane7.setVisible(false);
            productPane8.setVisible(false);
            productPane9.setVisible(false);
            productPane10.setVisible(false);
            productPane11.setVisible(false);
            
        }else if(products.size() == 7) {
            product = products.get(0);
            productLabel.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage, imageName);
            totalCost.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(1);
            productLabel1.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage1, imageName);
            totalCost1.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(2);
            productLabel2.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage2, imageName);
            totalCost2.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(3);
            productLabel3.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage3, imageName);
            totalCost3.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(4);
            productLabel4.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage4, imageName);
            totalCost4.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(5);
            productLabel5.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage5, imageName);
            totalCost5.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(6);
            productLabel6.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage6, imageName);
            totalCost6.setText("Pris: " + (int)product.getPrice() + " kr");
            
            //show used product panes
            productPane.setVisible(true);
            productPane1.setVisible(true);
            productPane2.setVisible(true);
            productPane3.setVisible(true);
            productPane4.setVisible(true);
            productPane5.setVisible(true);
            productPane6.setVisible(true);
            
            //hide unused product panes
            productPane7.setVisible(false);
            productPane8.setVisible(false);
            productPane9.setVisible(false);
            productPane10.setVisible(false);
            productPane11.setVisible(false);
            
        }else if(products.size() == 8) {
            product = products.get(0);
            productLabel.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage, imageName);
            totalCost.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(1);
            productLabel1.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage1, imageName);
            totalCost1.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(2);
            productLabel2.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage2, imageName);
            totalCost2.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(3);
            productLabel3.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage3, imageName);
            totalCost3.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(4);
            productLabel4.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage4, imageName);
            totalCost4.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(5);
            productLabel5.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage5, imageName);
            totalCost5.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(6);
            productLabel6.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage6, imageName);
            totalCost6.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(7);
            productLabel7.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage7, imageName);
            totalCost7.setText("Pris: " + (int)product.getPrice() + " kr");
            
            //show used product panes
            productPane.setVisible(true);
            productPane1.setVisible(true);
            productPane2.setVisible(true);
            productPane3.setVisible(true);
            productPane4.setVisible(true);
            productPane5.setVisible(true);
            productPane6.setVisible(true);
            productPane7.setVisible(true);
            
            //hide unused product panes
            productPane8.setVisible(false);
            productPane9.setVisible(false);
            productPane10.setVisible(false);
            productPane11.setVisible(false);
            
        }else if(products.size() == 9) {
            product = products.get(0);
            productLabel.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage, imageName);
            totalCost.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(1);
            productLabel1.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage1, imageName);
            totalCost1.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(2);
            productLabel2.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage2, imageName);
            totalCost2.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(3);
            productLabel3.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage3, imageName);
            totalCost3.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(4);
            productLabel4.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage4, imageName);
            totalCost4.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(5);
            productLabel5.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage5, imageName);
            totalCost5.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(6);
            productLabel6.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage6, imageName);
            totalCost6.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(7);
            productLabel7.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage7, imageName);
            totalCost7.setText("Pris: " + (int)product.getPrice() + " kr");
            //hide and show
            product = products.get(8);
            productLabel8.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage8, imageName);
            totalCost8.setText("Pris: " + (int)product.getPrice() + " kr");
            
            //show used product panes
            productPane.setVisible(true);
            productPane1.setVisible(true);
            productPane2.setVisible(true);
            productPane3.setVisible(true);
            productPane4.setVisible(true);
            productPane5.setVisible(true);
            productPane6.setVisible(true);
            productPane7.setVisible(true);
            productPane8.setVisible(true);
            
            //hide unused product panes
            productPane9.setVisible(false);
            productPane10.setVisible(false);
            productPane11.setVisible(false);
            
        }else if(products.size() == 10) {
            product = products.get(0);
            productLabel.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage, imageName);
            totalCost.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(1);
            productLabel1.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage1, imageName);
            totalCost1.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(2);
            productLabel2.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage2, imageName);
            totalCost2.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(3);
            productLabel3.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage3, imageName);
            totalCost3.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(4);
            productLabel4.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage4, imageName);
            totalCost4.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(5);
            productLabel5.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage5, imageName);
            totalCost5.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(6);
            productLabel6.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage6, imageName);
            totalCost6.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(7);
            productLabel7.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage7, imageName);
            totalCost7.setText("Pris: " + (int)product.getPrice() + " kr");
            //hide and show
            product = products.get(8);
            productLabel8.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage8, imageName);
            totalCost8.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(9);
            productLabel9.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage9, imageName);
            totalCost9.setText("Pris: " + (int)product.getPrice() + " kr");
            
            //show used product panes
            productPane.setVisible(true);
            productPane1.setVisible(true);
            productPane2.setVisible(true);
            productPane3.setVisible(true);
            productPane4.setVisible(true);
            productPane5.setVisible(true);
            productPane6.setVisible(true);
            productPane7.setVisible(true);
            productPane8.setVisible(true);
            productPane9.setVisible(true);
            
            //hide unused product panes
            productPane10.setVisible(false);
            productPane11.setVisible(false);
           
        }else if(products.size() == 11) {
            product = products.get(0);
            productLabel.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage, imageName);
            totalCost.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(1);
            productLabel1.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage1, imageName);
            totalCost1.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(2);
            productLabel2.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage2, imageName);
            totalCost2.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(3);
            productLabel3.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage3, imageName);
            totalCost3.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(4);
            productLabel4.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage4, imageName);
            totalCost4.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(5);
            productLabel5.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage5, imageName);
            totalCost5.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(6);
            productLabel6.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage6, imageName);
            totalCost6.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(7);
            productLabel7.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage7, imageName);
            totalCost7.setText("Pris: " + (int)product.getPrice() + " kr");
            //hide and show
            product = products.get(8);
            productLabel8.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage8, imageName);
            totalCost8.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(9);
            productLabel9.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage9, imageName);
            totalCost9.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(10);
            productLabel10.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage10, imageName);
            totalCost10.setText("Pris: " + (int)product.getPrice() + " kr");
            //show used product panes
            productPane.setVisible(true);
            productPane1.setVisible(true);
            productPane2.setVisible(true);
            productPane3.setVisible(true);
            productPane4.setVisible(true);
            productPane5.setVisible(true);
            productPane6.setVisible(true);
            productPane7.setVisible(true);
            productPane8.setVisible(true);
            productPane9.setVisible(true);
            productPane10.setVisible(true);
            
            //hide unsed product panes
            productPane11.setVisible(false);
        }else {
            product = products.get(0);
            productLabel.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage, imageName);
            totalCost.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(1);
            productLabel1.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage1, imageName);
            totalCost1.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(2);
            productLabel2.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage2, imageName);
            totalCost2.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(3);
            productLabel3.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage3, imageName);
            totalCost3.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(4);
            productLabel4.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage4, imageName);
            totalCost4.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(5);
            productLabel5.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage5, imageName);
            totalCost5.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(6);
            productLabel6.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage6, imageName);
            totalCost6.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(7);
            productLabel7.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage7, imageName);
            totalCost7.setText("Pris: " + (int)product.getPrice() + " kr");
            //hide and show
            product = products.get(8);
            productLabel8.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage8, imageName);
            totalCost8.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(9);
            productLabel9.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage9, imageName);
            totalCost9.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(10);
            productLabel10.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage10, imageName);
            totalCost10.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(11);
            productLabel11.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage11, imageName);
            totalCost11.setText("Pris: " + (int)product.getPrice() + " kr");
            
            //show used product panes
            productPane.setVisible(true);
            productPane1.setVisible(true);
            productPane2.setVisible(true);
            productPane3.setVisible(true);
            productPane4.setVisible(true);
            productPane5.setVisible(true);
            productPane6.setVisible(true);
            productPane7.setVisible(true);
            productPane8.setVisible(true);
            productPane9.setVisible(true);
            productPane10.setVisible(true);
            productPane11.setVisible(true);
        }
        removeFavorite = false;
        for (int i = 0; i < products.size(); i++) {
         switch (i) {
            case 0:  productFavorite.fire();
                     break;
            case 1:  productFavorite1.fire();
                     break;
            case 2:  productFavorite2.fire();
                     break;
            case 3:  productFavorite3.fire();
                     break;
            case 4:  productFavorite4.fire();
                     break;
            case 5:  productFavorite5.fire();
                     break;
            case 6:  productFavorite6.fire();
                     break;
            case 7:  productFavorite7.fire();
                     break;
            case 8:  productFavorite8.fire();
                     break;
            case 9: productFavorite9.fire();
                     break;
            case 10: productFavorite10.fire();
                     break;
            case 11: productFavorite11.fire();
                     break;
        }
        }
        removeFavorite = true;
  }
    public class XCell extends ListCell<IMatShoppingItem> {
        HBox hbox = new HBox();
        Label label1 = new Label("(empty)");
        Label label2 = new Label("(empty)");
        Label label3 = new Label("(empty)");
        Pane pane1 = new Pane();
        Pane pane2 = new Pane();
        Pane pane3 = new Pane();
        Button plusButton = new Button("+");
        Button minusButton = new Button("-");
        Button deleteButton = new Button("X");
        String lastItem;
        int index = 0;

        public XCell() {
            super();
            index = cellIndex;
            cellIndex++;

            hbox.setMaxHeight(15);
            pane1.setPrefWidth(20);
            label1.setPrefWidth(120);
            label2.setPrefWidth(50);
            pane2.setPrefWidth(20);
            pane3.setPrefWidth(20);
            label3.setPrefWidth(70);
            
            // Styles for buttons
            deleteButton.setMinSize(USE_PREF_SIZE, USE_PREF_SIZE);
            deleteButton.setPrefSize(15, 15);
            deleteButton.setMaxSize(USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
            deleteButton.getStyleClass().add("CancelButton");
            plusButton.setMinSize(USE_PREF_SIZE, USE_PREF_SIZE);
            plusButton.setPrefSize(15, 15);
            plusButton.setMaxSize(USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
            plusButton.getStyleClass().add("Addbutton");
            minusButton.setMinSize(USE_PREF_SIZE, USE_PREF_SIZE);
            minusButton.setPrefSize(15, 15);
            minusButton.setMaxSize(USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
            minusButton.getStyleClass().add("SubButton");
            // Styles for buttons
            
            hbox.getChildren().addAll(deleteButton, pane1, label1, pane2, plusButton, label2, minusButton, pane3, label3);
            HBox.setHgrow(pane1, Priority.ALWAYS);
            HBox.setHgrow(pane2, Priority.ALWAYS);
            HBox.setHgrow(pane3, Priority.ALWAYS);
            deleteButton.setOnAction(new EventHandler<ActionEvent>() {
              @Override
              public void handle(ActionEvent event) {
                lv.getSelectionModel().selectIndices(index-1);
                IMatShoppingItem item = lv.getSelectionModel().getSelectedItem();
                if (IMatController.currentUser != null) {
                  List<IMatShoppingItem> shopI = new ArrayList();
                  shopI.addAll(imat.getVarukorgController().getIMatShoppingCart().getCart().getAllProducts());
                  int size = shopI.size();
                  int totalSum = imat.getVarukorgController().getIMatShoppingCart().getCart().getCost();
                  for (int i = 0; i < shopI.size(); i++) {
                    if (shopI.get(i).getProduct().getName().equals(item.getProductName())) {
                      int sumToRemove = shopI.get(i).getSum();
                      shopI.remove(i);
                      imat.getVarukorgController().getIMatShoppingCart().getCart().removeCost(sumToRemove);
                      imat.getVarukorgController().getIMatShoppingCart().getCart().setNewEmptyCart();
                    }
                  }
                  for (int i = 0; i < size-1; i++) {
                    imat.getVarukorgController().getIMatShoppingCart().getCart().setShoppingItem(shopI.get(i), i);
                  }
                  populateCheckoutCart();
                  Integer total = imat.getVarukorgController().getIMatShoppingCart().getCart().getCost();
                  totalCostCartLabel.setText(total.toString() + " kr");
                  initCheckoutCart(imat.getVarukorgController().getIMatShoppingCart().getCart().getAllProducts());
                  
                } else {
                  List<ShoppingItem> shopI = new ArrayList();
                  shopI.addAll(IMatController.getShoppingCart().getItems());
                  int size = shopI.size();
                  for (int i = 0; i < shopI.size(); i++) {
                    if (shopI.get(i).getProduct().getName().equals(item.getProductName())) {
                      shopI.remove(i);
                      IMatController.getShoppingCart().clear();
                    }
                  }
                  for (int i = 0; i < size-1; i++) {
                    IMatController.getShoppingCart().addItem(shopI.get(i));
                  }
                  populateCheckoutCart();
                  Integer total = (int)IMatController.getShoppingCart().getTotal();
                  totalCostCartLabel.setText(total.toString() + " kr");
                  initCheckoutCart(imat.getVarukorgController().convertBackendToIMat());
                }
  
              }
            });
            minusButton.setOnAction(new EventHandler<ActionEvent>() {
              @Override
              public void handle(ActionEvent event) {
                lv.getSelectionModel().selectIndices(index-1);
                int currentAmount = lv.getSelectionModel().getSelectedItem().getAmount();
                int newAmount = --currentAmount;
                if (newAmount < 1) {
                  newAmount = 1;
                }
                lv.getSelectionModel().getSelectedItem().setAmount(newAmount);
                IMatShoppingItem item = lv.getSelectionModel().getSelectedItem();
                if (IMatController.currentUser != null) {
                  List<IMatShoppingItem> shopI = new ArrayList();
                  shopI.addAll(imat.getVarukorgController().getIMatShoppingCart().getCart().getAllProducts());
                  int size = shopI.size();
                  int totalSum = imat.getVarukorgController().getIMatShoppingCart().getCart().getCost();
                  for (int i = 0; i < shopI.size(); i++) {
                    if (shopI.get(i).getProduct().getName().equals(item.getProductName())) {
                      if (shopI.get(i).getAmount() > 1) {
                        int sumToRemove = (int)item.getProduct().getPrice();
                        int newSum = (int)shopI.get(i).getSum()-sumToRemove;
                        shopI.get(i).setSum(newSum);
                        shopI.get(i).setAmount(newAmount);
                        imat.getVarukorgController().getIMatShoppingCart().getCart().removeCost(sumToRemove);
                        imat.getVarukorgController().getIMatShoppingCart().getCart().setNewEmptyCart();
                      }
       
                    }
                  }
                  for (int i = 0; i < size; i++) {
                    imat.getVarukorgController().getIMatShoppingCart().getCart().setShoppingItem(shopI.get(i), i);
                  }
                  populateCheckoutCart();
                  Integer total = imat.getVarukorgController().getIMatShoppingCart().getCart().getCost();
                  totalCostCartLabel.setText(total.toString() + " kr");
                  initCheckoutCart(imat.getVarukorgController().getIMatShoppingCart().getCart().getAllProducts());
                } else {
                List<ShoppingItem> shopI = new ArrayList();
                shopI.addAll(IMatController.getShoppingCart().getItems());
                int size = shopI.size();
                for (int i = 0; i < size; i++) {
                  if (shopI.get(i).getProduct().getName().equals(item.getProductName())) {
                    shopI.get(i).setAmount(newAmount);
                    IMatController.getShoppingCart().clear();
                  }
                }
                for (int i = 0; i < size; i++) {
                  IMatController.getShoppingCart().addItem(shopI.get(i));
                }
                populateCheckoutCart();
                Integer total = (int)IMatController.getShoppingCart().getTotal();
                totalCostCartLabel.setText(total.toString()+" kr");
                initCheckoutCart(imat.getVarukorgController().convertBackendToIMat());
                }
                

              }
            });
            plusButton.setOnAction(new EventHandler<ActionEvent>() {
              @Override
              public void handle(ActionEvent event) {
                lv.getSelectionModel().selectIndices(index-1);
                int currentAmount = lv.getSelectionModel().getSelectedItem().getAmount();
                int newAmount = ++currentAmount;
                lv.getSelectionModel().getSelectedItem().setAmount(newAmount);
                IMatShoppingItem item = lv.getSelectionModel().getSelectedItem();
                if (IMatController.currentUser != null) {
                   List<IMatShoppingItem> shopI = new ArrayList();
                  shopI.addAll(imat.getVarukorgController().getIMatShoppingCart().getCart().getAllProducts());
                  int size = shopI.size();
                  int totalSum = imat.getVarukorgController().getIMatShoppingCart().getCart().getCost();
                  for (int i = 0; i < shopI.size(); i++) {
                    if (shopI.get(i).getProduct().getName().equals(item.getProductName())) {
                      int sumToAdd = (int)item.getProduct().getPrice();
                      int newSum = (int)shopI.get(i).getSum()+sumToAdd;
                      shopI.get(i).setSum(newSum);
                      shopI.get(i).setAmount(newAmount);
                      imat.getVarukorgController().getIMatShoppingCart().getCart().addCost(sumToAdd);
                      imat.getVarukorgController().getIMatShoppingCart().getCart().setNewEmptyCart();
                    }
                  }
                  for (int i = 0; i < size; i++) {
                    imat.getVarukorgController().getIMatShoppingCart().getCart().setShoppingItem(shopI.get(i), i);
                  }
                  populateCheckoutCart();
                  Integer total = imat.getVarukorgController().getIMatShoppingCart().getCart().getCost();
                  totalCostCartLabel.setText(total.toString() + " kr");
                  initCheckoutCart(imat.getVarukorgController().getIMatShoppingCart().getCart().getAllProducts());
                } else {
                  List<ShoppingItem> shopI = new ArrayList();
                  shopI.addAll(IMatController.getShoppingCart().getItems());
                  int size = shopI.size();
                  for (int i = 0; i < size; i++) {
                    if (shopI.get(i).getProduct().getName().equals(item.getProductName())) {
                      shopI.get(i).setAmount(newAmount);
                      IMatController.getShoppingCart().clear();
                    }
                  }
                  for (int i = 0; i < size; i++) {
                    IMatController.getShoppingCart().addItem(shopI.get(i));
                  }
                  populateCheckoutCart();
                  Integer total = (int)IMatController.getShoppingCart().getTotal();
                  totalCostCartLabel.setText(total.toString()+" kr");
                  initCheckoutCart(imat.getVarukorgController().convertBackendToIMat());
                }
              }
            });
       
        }

        @Override
        protected void updateItem(IMatShoppingItem item, boolean empty) {
            super.updateItem(item, empty);
            setText(null);  // No text in label of super class
            if (empty) {
              
                lastItem = null;
                setGraphic(null);
            } else {
              String productName = item.getProductName();
              Integer productUnits = item.getAmount();
              lastItem = item.getProductName();
              label1.setText(productName !=null ? productName : "<null>");
              label2.setText(item.getProductName() !=null ? "  "+productUnits.toString()+" st  " : "<null>");
              label3.setText(item.getProductName() !=null ? item.getSum().toString()+" kr" : "<null>");
              setGraphic(hbox);
            }
        }
    }
  
  public void populateCheckoutCart() {
    if (getListVyPane().getChildren().contains(lv)) {
      getListVyPane().getChildren().remove(lv);
    }
      lv = getCheckoutList();
      lv.setStyle("-fx-background-insets: 0 ;");
      lv.setCellFactory(new Callback<ListView<IMatShoppingItem>, ListCell<IMatShoppingItem>>() {
        @Override
        public ListCell<IMatShoppingItem> call(ListView<IMatShoppingItem> param) {
            XCell cell = new XCell();
            return cell;
        }
    });
    cellIndex = 0;
    getListVyPane().getChildren().add(lv);
  }
    
  public void initCheckoutCart(List<IMatShoppingItem> cartProducts) {
    //List<IMatShoppingItem> cartProducts

//    ObservableList<IMatShoppingItem> cartList = FXCollections.observableArrayList(cartProducts);
//    checkOutCartListView.setItems(cartList);
//    checkOutCartListView.setCellFactory(new Callback<ListView<IMatShoppingItem>, ListCell<IMatShoppingItem>>(){
//      @Override
//      public ListCell<IMatShoppingItem> call(ListView<IMatShoppingItem> p) {
//        ListCell<IMatShoppingItem> cell = new ListCell<IMatShoppingItem>(){
//          @Override
//          protected void updateItem(IMatShoppingItem t, boolean bln) {
//            super.updateItem(t, bln);
//            if (t != null) {
//              setText(t.getAmount() + " st  " + t.getProductName() + "   " + t.getSum() + " kr");
//            }
//          }
//        };
//      return cell;
//      }
//    });
  }

  public void changeToSearchView(String searchText) {
    if (imat.getVarukorgController().getCartBuyButton().isDisabled()) {
      makeShoppingCartVisible();
    }
    if (IMatController.currentUser != null) {
      if (imat.getVarukorgController().getIMatShoppingCart().getCart().getAllProducts().size() == 0) {
        setEmptyCartText();
      }
    } else {
        if (IMatController.getShoppingCart().getItems().size() == 0) {
          setEmptyCartText();
        }
    }
    getListVyPane().getChildren().remove(lv);
        changeToCategoryView();
        setAllUnitsToOne();
        categoryLabel.setText("Sökresultat för: " + searchText);
        products = IMatController.imatBackend.findProducts(searchText);
        if (products.size() == 0) {
            //hide unused products
            productPane.setVisible(false);
            productPane1.setVisible(false);
            productPane2.setVisible(false);
            productPane3.setVisible(false);
            productPane4.setVisible(false);
            productPane5.setVisible(false);
            productPane6.setVisible(false);
            productPane7.setVisible(false);
            productPane8.setVisible(false);
            productPane9.setVisible(false);
            productPane10.setVisible(false);
            productPane11.setVisible(false);
        }else if(products.size() == 1) {
            product = products.get(0);
            productLabel.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage, imageName);
            totalCost.setText("Pris: " + (int)product.getPrice() + " kr");
            //show used product panes
            productPane.setVisible(true);
            
            //hide unused product panes
            productPane1.setVisible(false);
            productPane2.setVisible(false);
            productPane3.setVisible(false);
            productPane4.setVisible(false);
            productPane5.setVisible(false);
            productPane6.setVisible(false);
            productPane7.setVisible(false);
            productPane8.setVisible(false);
            productPane9.setVisible(false);
            productPane10.setVisible(false);
            productPane11.setVisible(false);
            
        }else if(products.size() == 2) {
            product = products.get(0);
            productLabel.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage, imageName);
            totalCost.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(1);
            productLabel1.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage1, imageName);
            totalCost1.setText("Pris: " + (int)product.getPrice() + " kr");
            
            //show used product panes
            productPane.setVisible(true);
            productPane1.setVisible(true);
            
            //hide unused product panes
            productPane2.setVisible(false);
            productPane3.setVisible(false);
            productPane4.setVisible(false);
            productPane5.setVisible(false);
            productPane6.setVisible(false);
            productPane7.setVisible(false);
            productPane8.setVisible(false);
            productPane9.setVisible(false);
            productPane10.setVisible(false);
            productPane11.setVisible(false);
            
        }else if(products.size() == 3) {
            product = products.get(0);
            productLabel.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage, imageName);
            totalCost.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(1);
            productLabel1.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage1, imageName);
            totalCost1.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(2);
            productLabel2.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage2, imageName);
            totalCost2.setText("Pris: " + (int)product.getPrice() + " kr");
            
            //show used product panes
            productPane.setVisible(true);
            productPane1.setVisible(true);
            productPane2.setVisible(true);
            
            //hide unused product panes
            productPane3.setVisible(false);
            productPane4.setVisible(false);
            productPane5.setVisible(false);
            productPane6.setVisible(false);
            productPane7.setVisible(false);
            productPane8.setVisible(false);
            productPane9.setVisible(false);
            productPane10.setVisible(false);
            productPane11.setVisible(false);
            
        }else if(products.size() == 4) {
            product = products.get(0);
            productLabel.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage, imageName);
            totalCost.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(1);
            productLabel1.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage1, imageName);
            totalCost1.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(2);
            productLabel2.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage2, imageName);
            totalCost2.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(3);
            productLabel3.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage3, imageName);
            totalCost3.setText("Pris: " + (int)product.getPrice() + " kr");
            
            //show used product panes
            productPane.setVisible(true);
            productPane1.setVisible(true);
            productPane2.setVisible(true);
            productPane3.setVisible(true);
            
            //hide unused product panes
            productPane4.setVisible(false);
            productPane5.setVisible(false);
            productPane6.setVisible(false);
            productPane7.setVisible(false);
            productPane8.setVisible(false);
            productPane9.setVisible(false);
            productPane10.setVisible(false);
            productPane11.setVisible(false);
            
        }else if(products.size() == 5) {
            product = products.get(0);
            productLabel.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage, imageName);
            totalCost.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(1);
            productLabel1.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage1, imageName);
            totalCost1.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(2);
            productLabel2.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage2, imageName);
            totalCost2.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(3);
            productLabel3.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage3, imageName);
            totalCost3.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(4);
            productLabel4.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage4, imageName);
            totalCost4.setText("Pris: " + (int)product.getPrice() + " kr");
            
            //show used product panes
            productPane.setVisible(true);
            productPane1.setVisible(true);
            productPane2.setVisible(true);
            productPane3.setVisible(true);
            productPane4.setVisible(true);
            
            //hide unused product panes
            productPane5.setVisible(false);
            productPane6.setVisible(false);
            productPane7.setVisible(false);
            productPane8.setVisible(false);
            productPane9.setVisible(false);
            productPane10.setVisible(false);
            productPane11.setVisible(false);
            
        }else if(products.size() == 6) {
            product = products.get(0);
            productLabel.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage, imageName);
            totalCost.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(1);
            productLabel1.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage1, imageName);
            totalCost1.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(2);
            productLabel2.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage2, imageName);
            totalCost2.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(3);
            productLabel3.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage3, imageName);
            totalCost3.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(4);
            productLabel4.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage4, imageName);
            totalCost4.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(5);
            productLabel5.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage5, imageName);
            totalCost5.setText("Pris: " + (int)product.getPrice() + " kr");
            
            //show used product panes
            productPane.setVisible(true);
            productPane1.setVisible(true);
            productPane2.setVisible(true);
            productPane3.setVisible(true);
            productPane4.setVisible(true);
            productPane5.setVisible(true);
            
            //hide unused product panes
            productPane6.setVisible(false);
            productPane7.setVisible(false);
            productPane8.setVisible(false);
            productPane9.setVisible(false);
            productPane10.setVisible(false);
            productPane11.setVisible(false);
            
        }else if(products.size() == 7) {
            product = products.get(0);
            productLabel.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage, imageName);
            totalCost.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(1);
            productLabel1.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage1, imageName);
            totalCost1.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(2);
            productLabel2.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage2, imageName);
            totalCost2.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(3);
            productLabel3.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage3, imageName);
            totalCost3.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(4);
            productLabel4.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage4, imageName);
            totalCost4.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(5);
            productLabel5.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage5, imageName);
            totalCost5.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(6);
            productLabel6.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage6, imageName);
            totalCost6.setText("Pris: " + (int)product.getPrice() + " kr");
            
            //show used product panes
            productPane.setVisible(true);
            productPane1.setVisible(true);
            productPane2.setVisible(true);
            productPane3.setVisible(true);
            productPane4.setVisible(true);
            productPane5.setVisible(true);
            productPane6.setVisible(true);
            
            //hide unused product panes
            productPane7.setVisible(false);
            productPane8.setVisible(false);
            productPane9.setVisible(false);
            productPane10.setVisible(false);
            productPane11.setVisible(false);
            
        }else if(products.size() == 8) {
            product = products.get(0);
            productLabel.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage, imageName);
            totalCost.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(1);
            productLabel1.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage1, imageName);
            totalCost1.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(2);
            productLabel2.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage2, imageName);
            totalCost2.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(3);
            productLabel3.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage3, imageName);
            totalCost3.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(4);
            productLabel4.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage4, imageName);
            totalCost4.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(5);
            productLabel5.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage5, imageName);
            totalCost5.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(6);
            productLabel6.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage6, imageName);
            totalCost6.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(7);
            productLabel7.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage7, imageName);
            totalCost7.setText("Pris: " + (int)product.getPrice() + " kr");
            
            //show used product panes
            productPane.setVisible(true);
            productPane1.setVisible(true);
            productPane2.setVisible(true);
            productPane3.setVisible(true);
            productPane4.setVisible(true);
            productPane5.setVisible(true);
            productPane6.setVisible(true);
            productPane7.setVisible(true);
            
            //hide unused product panes
            productPane8.setVisible(false);
            productPane9.setVisible(false);
            productPane10.setVisible(false);
            productPane11.setVisible(false);
            
        }else if(products.size() == 9) {
            product = products.get(0);
            productLabel.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage, imageName);
            totalCost.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(1);
            productLabel1.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage1, imageName);
            totalCost1.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(2);
            productLabel2.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage2, imageName);
            totalCost2.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(3);
            productLabel3.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage3, imageName);
            totalCost3.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(4);
            productLabel4.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage4, imageName);
            totalCost4.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(5);
            productLabel5.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage5, imageName);
            totalCost5.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(6);
            productLabel6.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage6, imageName);
            totalCost6.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(7);
            productLabel7.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage7, imageName);
            totalCost7.setText("Pris: " + (int)product.getPrice() + " kr");
            //hide and show
            product = products.get(8);
            productLabel8.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage8, imageName);
            totalCost8.setText("Pris: " + (int)product.getPrice() + " kr");
            
            //show used product panes
            productPane.setVisible(true);
            productPane1.setVisible(true);
            productPane2.setVisible(true);
            productPane3.setVisible(true);
            productPane4.setVisible(true);
            productPane5.setVisible(true);
            productPane6.setVisible(true);
            productPane7.setVisible(true);
            productPane8.setVisible(true);
            
            //hide unused product panes
            productPane9.setVisible(false);
            productPane10.setVisible(false);
            productPane11.setVisible(false);
            
        }else if(products.size() == 10) {
            product = products.get(0);
            productLabel.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage, imageName);
            totalCost.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(1);
            productLabel1.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage1, imageName);
            totalCost1.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(2);
            productLabel2.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage2, imageName);
            totalCost2.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(3);
            productLabel3.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage3, imageName);
            totalCost3.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(4);
            productLabel4.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage4, imageName);
            totalCost4.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(5);
            productLabel5.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage5, imageName);
            totalCost5.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(6);
            productLabel6.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage6, imageName);
            totalCost6.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(7);
            productLabel7.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage7, imageName);
            totalCost7.setText("Pris: " + (int)product.getPrice() + " kr");
            //hide and show
            product = products.get(8);
            productLabel8.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage8, imageName);
            totalCost8.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(9);
            productLabel9.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage9, imageName);
            totalCost9.setText("Pris: " + (int)product.getPrice() + " kr");
            
            //show used product panes
            productPane.setVisible(true);
            productPane1.setVisible(true);
            productPane2.setVisible(true);
            productPane3.setVisible(true);
            productPane4.setVisible(true);
            productPane5.setVisible(true);
            productPane6.setVisible(true);
            productPane7.setVisible(true);
            productPane8.setVisible(true);
            productPane9.setVisible(true);
            
            //hide unused product panes
            productPane10.setVisible(false);
            productPane11.setVisible(false);
           
        }else if(products.size() == 11) {
            product = products.get(0);
            productLabel.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage, imageName);
            totalCost.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(1);
            productLabel1.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage1, imageName);
            totalCost1.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(2);
            productLabel2.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage2, imageName);
            totalCost2.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(3);
            productLabel3.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage3, imageName);
            totalCost3.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(4);
            productLabel4.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage4, imageName);
            totalCost4.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(5);
            productLabel5.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage5, imageName);
            totalCost5.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(6);
            productLabel6.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage6, imageName);
            totalCost6.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(7);
            productLabel7.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage7, imageName);
            totalCost7.setText("Pris: " + (int)product.getPrice() + " kr");
            //hide and show
            product = products.get(8);
            productLabel8.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage8, imageName);
            totalCost8.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(9);
            productLabel9.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage9, imageName);
            totalCost9.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(10);
            productLabel10.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage10, imageName);
            totalCost10.setText("Pris: " + (int)product.getPrice() + " kr");
            //show used product panes
            productPane.setVisible(true);
            productPane1.setVisible(true);
            productPane2.setVisible(true);
            productPane3.setVisible(true);
            productPane4.setVisible(true);
            productPane5.setVisible(true);
            productPane6.setVisible(true);
            productPane7.setVisible(true);
            productPane8.setVisible(true);
            productPane9.setVisible(true);
            productPane10.setVisible(true);
            
            //hide unsed product panes
            productPane11.setVisible(false);
        }else {
            product = products.get(0);
            productLabel.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage, imageName);
            totalCost.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(1);
            productLabel1.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage1, imageName);
            totalCost1.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(2);
            productLabel2.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage2, imageName);
            totalCost2.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(3);
            productLabel3.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage3, imageName);
            totalCost3.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(4);
            productLabel4.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage4, imageName);
            totalCost4.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(5);
            productLabel5.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage5, imageName);
            totalCost5.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(6);
            productLabel6.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage6, imageName);
            totalCost6.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(7);
            productLabel7.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage7, imageName);
            totalCost7.setText("Pris: " + (int)product.getPrice() + " kr");
            //hide and show
            product = products.get(8);
            productLabel8.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage8, imageName);
            totalCost8.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(9);
            productLabel9.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage9, imageName);
            totalCost9.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(10);
            productLabel10.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage10, imageName);
            totalCost10.setText("Pris: " + (int)product.getPrice() + " kr");
            product = products.get(11);
            productLabel11.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
            imageName = product.getImageName();
            setImage(productImage11, imageName);
            totalCost11.setText("Pris: " + (int)product.getPrice() + " kr");
            
            //show used product panes
            productPane.setVisible(true);
            productPane1.setVisible(true);
            productPane2.setVisible(true);
            productPane3.setVisible(true);
            productPane4.setVisible(true);
            productPane5.setVisible(true);
            productPane6.setVisible(true);
            productPane7.setVisible(true);
            productPane8.setVisible(true);
            productPane9.setVisible(true);
            productPane10.setVisible(true);
            productPane11.setVisible(true);
        }
        uncheck();
        populateFavorites(products);
    }
  
  
  public void changeToReceiptView() {
    int size = varaListVyParent.getChildren().size();
    currentPane = "receiptPane";
    String id;
    for (int i = 0; i < size; i++) {
      id = varaListVyParent.getChildren().get(i).getId();
      if (id.compareTo(currentPane) == 0) {
        varaListVyParent.getChildren().get(i).toFront();
        varaListVyParent.getChildren().get(i).setVisible(true);
      } else if (id.compareTo("toolBar") == 0) {
      } else {
        varaListVyParent.getChildren().get(i).setVisible(false);
      }
    }
    
    if (IMatController.currentUser != null) {
      
    } else {
      Integer orderID = orderCart.getOrderNumber();
      orderNumberReceiptLabel.setText(orderID.toString());
    }
  }
  
  public void changeToCheckoutView() {
    int size = varaListVyParent.getChildren().size();
    currentPane = "checkCartCheckoutPane";
    String id;
    for (int i = 0; i < size; i++) {
      id = varaListVyParent.getChildren().get(i).getId();
      if (id.compareTo(currentPane) == 0) {
        varaListVyParent.getChildren().get(i).toFront();
        varaListVyParent.getChildren().get(i).setVisible(true);
      } else if (id.compareTo("toolBar") == 0) {
      } else {
        varaListVyParent.getChildren().get(i).setVisible(false);
      }
    }
    
    if (IMatController.currentUser != null) {
      populateCheckoutCart();
      Integer total = imat.getVarukorgController().getIMatShoppingCart().getCart().getCost();
      totalCostCartLabel.setText(total.toString()+" kr");
      setCartNotVisible();
    } else {
      populateCheckoutCart();
      Integer total = (int)IMatController.getShoppingCart().getTotal();
      totalCostCartLabel.setText(total.toString()+" kr");
      setCartNotVisible();
    }
  deSelect();
  }
  
  public void setCartNotVisible() {
    imat.getVarukorgController().getCartBuyButton().setDisable(true);
    imat.getVarukorgController().getList().getItems().clear();
    imat.getVarukorgController().setTotalCostLabel("");
    imat.getVarukorgController().getEmptyLink().setDisable(true);
    imat.getVarukorgController().getChangeLink().setDisable(true);
    imat.getVarukorgController().getList().setStyle("-fx-background-color: #F0F0F0 ;");
    imat.getVarukorgController().getList().setPlaceholder(new Label(""));
    imat.getVarukorgController().getSummaLabel().setDisable(true);
    imat.getVarukorgController().disableShoppingCartLabel(true);
  }
  
  public AnchorPane getListVyPane(){
    return varaListVyParent;
  }
  
  public ListView<IMatShoppingItem> getCheckoutList() {
    List<IMatShoppingItem> imatItems;
    if (IMatController.currentUser != null) {
      imatItems = imat.getVarukorgController().getIMatShoppingCart().getCart().getAllProducts();
      nn = FXCollections.observableArrayList(imatItems);
    } else {
      imatItems = imat.getVarukorgController().convertBackendToIMat();
      nn = FXCollections.observableArrayList(imatItems);
    }
    checkOutCartListView = new ListView(nn);
    checkOutCartListView.setLayoutY(150);
    checkOutCartListView.setLayoutX(100);
    checkOutCartListView.setMinWidth(500);
    checkOutCartListView.setMaxHeight(300);
    return checkOutCartListView;
  }
  
  private FadeTransition createFader(Node node) {
    FadeTransition fade = new FadeTransition(Duration.seconds(4), node);
    fade.setFromValue(1);
    fade.setToValue(0);

    return fade;
}
  
  public void deSelect() {
    if (kontouppgifterButton.isSelected()) {
      kontouppgifterButton.setSelected(false);
    }
    if (orderhistorikButton.isSelected()) {
      orderhistorikButton.setSelected(false);
    }
    if (favoritvarorButton.isSelected()) {
      favoritvarorButton.setSelected(false);
    }
  }
  
    public void changeToPagination() {
    int size = varaListVyParent.getChildren().size();
    currentPane = "testPagination";
    String id;
    for (int i = 0; i < size; i++) {
      id = varaListVyParent.getChildren().get(i).getId();
      if (id.compareTo(currentPane) == 0) {
        varaListVyParent.getChildren().get(i).toFront();
        varaListVyParent.getChildren().get(i).setVisible(true);
      } else if (id.compareTo("toolBar") == 0) {
      } else {
        varaListVyParent.getChildren().get(i).setVisible(false);
      }
    }
    
    pagination.setPageFactory(new Callback<Integer, Node>() {
      public Node call(Integer pageIndex) {
        VBox vbox = new VBox();
        if (pageIndex == 0) {
          checkOutLabel.setText("Steg 1/2: Kontrollera varukorg");
          newRequirePaymentLabel.setText("");
          summaLabel.setText("Summa:");
          if (IMatController.currentUser != null) {
            showPage2(false);
            populateCheckoutCart();
            Integer total = imat.getVarukorgController().getIMatShoppingCart().getCart().getCost();
            totalCostCartLabel.setText(total.toString()+" kr");
            setCartNotVisible();
          } else {
            showPage2(false);
            populateCheckoutCart();
            Integer total = (int)IMatController.getShoppingCart().getTotal();
            totalCostCartLabel.setText(total.toString()+" kr");
            setCartNotVisible();
          }
          deSelect();
        } else {
          showPage2(true);
          summaLabel.setText("");
          totalCostCartLabel.setText("");
          checkOutLabel.setText("Steg 2/2: Kontrollera uppgifter");
          getListVyPane().getChildren().remove(lv);
        }
        return vbox;
      }
   });
    
    }
    
    public void showPage2(boolean show) {
      postalLabel1.setText("");
      civicLabel1.setText("");
      firstNameLabel1.setText("");
      yearLabel1.setText("");
      cvvLabel1.setText("");
      cardNumberLabel1.setText("");
      streetLabel1.setText("");
      lastNameLabel1.setText("");
    
      firstNameTextField1.setVisible(show);
      lastNameTextField1.setVisible(show);
      civicTextField1.setVisible(show);
      postalTextField1.setVisible(show);
      cityTextField1.setVisible(show);
      cardNumberTextField1.setVisible(show);
      yearTextField1.setVisible(show);
      monthTextField1.setVisible(show);
      cvvTextField1.setVisible(show);
      streetTextField1.setVisible(show);
      
      paymentForOrderButton.setVisible(show);
      
      firstNameText1.setVisible(show);
      lastNameText1.setVisible(show);
      civicText1.setVisible(show);
      postCityText1.setVisible(show);
      cardNumberText1.setVisible(show);
      yearMonthText1.setVisible(show);
      addressText1.setVisible(show);
      cvvText1.setVisible(show);
      slashText1.setVisible(show);
     }
    
    /**
     * Takes a list of the current product category and checks if any of the
     * products are in the favorite list. If they are, they will be checked.
     * 
     * @param productList the list of the products of current category
     */
    public void populateFavorites(List<Product> productList) {
      removeFavorite = false;
      if (IMatController.currentUser != null) {
        //List<IMatProduct> list = imat.getVarukorgController().convertBackendFavoritesToIMat();
      } else {
        List<Product> list = IMatController.getIMatBackend().favorites();
        int size = productList.size();
        for (int i = 0; i < size; i++) {
          for (int j = 0; j < list.size(); j++) {
            if (productList.get(i).equals(list.get(j))) {
            switch (i) {
            case 0:  productFavorite.fire();
                     break;
            case 1:  productFavorite1.fire();
                     break;
            case 2:  productFavorite2.fire();
                     break;
            case 3:  productFavorite3.fire();
                     break;
            case 4:  productFavorite4.fire();
                     break;
            case 5:  productFavorite5.fire();
                     break;
            case 6:  productFavorite6.fire();
                     break;
            case 7:  productFavorite7.fire();
                     break;
            case 8:  productFavorite8.fire();
                     break;
            case 9: productFavorite9.fire();
                     break;
            case 10: productFavorite10.fire();
                     break;
            case 11: productFavorite11.fire();
                     break;
        }
            }
          }
        }
      }
      removeFavorite = true;
    }

}

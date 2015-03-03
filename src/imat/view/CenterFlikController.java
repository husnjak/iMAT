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
import java.time.LocalDate;
import java.time.ZoneId;
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
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;
import javax.imageio.ImageIO;
import se.chalmers.ait.dat215.project.Order;
import se.chalmers.ait.dat215.project.Product;
import se.chalmers.ait.dat215.project.ShoppingItem;

/**
 *
 * @author group 11
 */
public class CenterFlikController implements Initializable {
  
  private Tab handlaFlik;
  private Tab historikFlik;
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
  
  // Used for holding products from specific categories
  private List<Product> products;
  private StackPane handlaStackPane;
  
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
  private AnchorPane productPane12;
  @FXML
  private Button buy12;
  @FXML
  private ImageView productImage12;
  @FXML
  private Label productLabel12;
  @FXML
  private CheckBox productFavorite12;
  @FXML
  private TextField textField12;
  @FXML
  private Button plus12;
  @FXML
  private Button minus12;
  @FXML
  private Label totalCost12;
  @FXML
  private AnchorPane productPane13;
  @FXML
  private Button buy13;
  @FXML
  private ImageView productImage13;
  @FXML
  private Label productLabel13;
  @FXML
  private CheckBox productFavorite13;
  @FXML
  private TextField textField13;
  @FXML
  private Button plus13;
  @FXML
  private Button minus13;
  @FXML
  private Label totalCost13;
  @FXML
  private AnchorPane productPane14;
  @FXML
  private Button buy14;
  @FXML
  private ImageView productImage14;
  @FXML
  private Label productLabel14;
  @FXML
  private CheckBox productFavorite14;
  @FXML
  private TextField textField14;
  @FXML
  private Button plus14;
  @FXML
  private Button minus14;
  @FXML
  private Label totalCost14;
  @FXML
  private AnchorPane productPane15;
  @FXML
  private Button buy15;
  @FXML
  private ImageView productImage15;
  @FXML
  private Label productLabel15;
  @FXML
  private CheckBox productFavorite15;
  @FXML
  private TextField textField15;
  @FXML
  private Button plus15;
  @FXML
  private Button minus15;
  @FXML
  private Label totalCost15;
  @FXML
  private AnchorPane productPane16;
  @FXML
  private Button buy16;
  @FXML
  private ImageView productImage16;
  @FXML
  private Label productLabel16;
  @FXML
  private CheckBox productFavorite16;
  @FXML
  private TextField textField16;
  @FXML
  private Button plus16;
  @FXML
  private Button minus16;
  @FXML
  private Label totalCost16;
  @FXML
  private AnchorPane productPane17;
  @FXML
  private Button buy17;
  @FXML
  private ImageView productImage17;
  @FXML
  private Label productLabel17;
  @FXML
  private CheckBox productFavorite17;
  @FXML
  private TextField textField17;
  @FXML
  private Button plus17;
  @FXML
  private Button minus17;
  @FXML
  private Label totalCost17;
  @FXML
  private AnchorPane productPane18;
  @FXML
  private Button buy18;
  @FXML
  private ImageView productImage18;
  @FXML
  private Label productLabel18;
  @FXML
  private CheckBox productFavorite18;
  @FXML
  private TextField textField18;
  @FXML
  private Button plus18;
  @FXML
  private Button minus18;
  @FXML
  private Label totalCost18;
  @FXML
  private AnchorPane productPane19;
  @FXML
  private Button buy19;
  @FXML
  private ImageView productImage19;
  @FXML
  private Label productLabel19;
  @FXML
  private CheckBox productFavorite19;
  @FXML
  private TextField textField19;
  @FXML
  private Button plus19;
  @FXML
  private Button minus19;
  @FXML
  private Label totalCost19;
  @FXML
  private AnchorPane productPane20;
  @FXML
  private Button buy20;
  @FXML
  private ImageView productImage20;
  @FXML
  private Label productLabel20;
  @FXML
  private CheckBox productFavorite20;
  @FXML
  private TextField textField20;
  @FXML
  private Button plus20;
  @FXML
  private Button minus20;
  @FXML
  private Label totalCost20;
  @FXML
  private AnchorPane productPane21;
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
  @FXML
  private AnchorPane productPane22;
  @FXML
  private Button buy22;
  @FXML
  private ImageView productImage22;
  @FXML
  private Label productLabel22;
  @FXML
  private CheckBox productFavorite22;
  @FXML
  private TextField textField22;
  @FXML
  private Button plus22;
  @FXML
  private Button minus22;
  @FXML
  private Label totalCost22;
  @FXML
  private AnchorPane productPane23;
  @FXML
  private Button buy23;
  @FXML
  private ImageView productImage23;
  @FXML
  private Label productLabel23;
  @FXML
  private CheckBox productFavorite23;
  @FXML
  private TextField textField23;
  @FXML
  private Button plus23;
  @FXML
  private Button minus23;
  @FXML
  private Label totalCost23;
  @FXML
  private AnchorPane productPane24;
  @FXML
  private Button buy24;
  @FXML
  private ImageView productImage24;
  @FXML
  private Label productLabel24;
  @FXML
  private CheckBox productFavorite24;
  @FXML
  private TextField textField24;
  @FXML
  private Button plus24;
  @FXML
  private Button minus24;
  @FXML
  private Label totalCost24;
  @FXML
  private AnchorPane productPane25;
  @FXML
  private Button buy25;
  @FXML
  private ImageView productImage25;
  @FXML
  private Label productLabel25;
  @FXML
  private CheckBox productFavorite25;
  @FXML
  private TextField textField25;
  @FXML
  private Button plus25;
  @FXML
  private Button minus25;
  @FXML
  private Label totalCost25;
  @FXML
  private AnchorPane productPane26;
  @FXML
  private Button buy26;
  @FXML
  private ImageView productImage26;
  @FXML
  private Label productLabel26;
  @FXML
  private CheckBox productFavorite26;
  @FXML
  private TextField textField26;
  @FXML
  private Button plus26;
  @FXML
  private Button minus26;
  @FXML
  private Label totalCost26;
  @FXML
  private AnchorPane productPane27;
  @FXML
  private Button buy27;
  @FXML
  private ImageView productImage27;
  @FXML
  private Label productLabel27;
  @FXML
  private CheckBox productFavorite27;
  @FXML
  private TextField textField27;
  @FXML
  private Button plus27;
  @FXML
  private Button minus27;
  @FXML
  private Label totalCost27;
  @FXML
  private AnchorPane productPane28;
  @FXML
  private Button buy28;
  @FXML
  private ImageView productImage28;
  @FXML
  private Label productLabel28;
  @FXML
  private CheckBox productFavorite28;
  @FXML
  private TextField textField28;
  @FXML
  private Button plus28;
  @FXML
  private Button minus28;
  @FXML
  private Label totalCost28;
  @FXML
  private Label categoryLabel;
  @FXML
  private AnchorPane varaListVyParent;
  @FXML
  private ToolBar toolBar;
  @FXML
  private Button kontouppgifterButton;
  @FXML
  private Button orderhistorikButton;
  @FXML
  private Button favoritvarorButton;
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
  private ScrollPane checkoutPane;
  @FXML
  private AnchorPane paymentInformationPane;
  @FXML
  private TextField civicTextField1;
  @FXML
  private Label civicLabel1;
  @FXML
  private TextField yearTextField1;
  @FXML
  private Label yearLabel1;
  @FXML
  private TextField phoneTextField1;
  @FXML
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
  @FXML
  private TextField emailTextField1;
  @FXML
  private TextField monthTextField1;
  @FXML
  private Label monthLabel1;
  @FXML
  private Hyperlink removeProductPaymentLink;
  @FXML
  private Label cityLabel1;
  @FXML
  private Label streetLabel1;
  @FXML
  private Label lastNameLabel1;
  @FXML
  private Label firstNameLabel1;
  @FXML
  private Label emailLabel1;
  @FXML
  private ListView<IMatShoppingItem> checkOutCartListView = new ListView();
  
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
    productTable.setMouseTransparent(true);
    varaListVyParent.setFocusTraversable(false);
    
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
            IMatController.getIMatBackend().removeFavorite(product);
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
            IMatController.getIMatBackend().removeFavorite(product);
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
            IMatController.getIMatBackend().removeFavorite(product);
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
            IMatController.getIMatBackend().removeFavorite(product);
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
            IMatController.getIMatBackend().removeFavorite(product);
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
            IMatController.getIMatBackend().removeFavorite(product);
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
            IMatController.getIMatBackend().removeFavorite(product);
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
            IMatController.getIMatBackend().removeFavorite(product);
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
            IMatController.getIMatBackend().removeFavorite(product);
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
            IMatController.getIMatBackend().removeFavorite(product);
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
            IMatController.getIMatBackend().removeFavorite(product);
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
            IMatController.getIMatBackend().removeFavorite(product);
          }
        }
      }
    });
    
    productFavorite12.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = products.get(12);
        if (IMatController.currentUser != null) {
          if (productFavorite12.isSelected()) {
            int index = IMatController.getNumberOfRecords(product.toString());
            IMatController.addFavorite(product.toString(), ++index);
          } else {
            // Implement how to find correct index
            int index = 1;
            IMatController.removeFavorite(product.toString(), index);
          }
        } else {
          if (productFavorite12.isSelected()) {
            IMatController.getIMatBackend().addFavorite(product);
          } else {
            IMatController.getIMatBackend().removeFavorite(product);
          }
        }
      }
    });
    
    productFavorite13.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = products.get(13);
        if (IMatController.currentUser != null) {
          if (productFavorite13.isSelected()) {
            int index = IMatController.getNumberOfRecords(product.toString());
            IMatController.addFavorite(product.toString(), ++index);
          } else {
            // Implement how to find correct index
            int index = 1;
            IMatController.removeFavorite(product.toString(), index);
          }
        } else {
          if (productFavorite13.isSelected()) {
            IMatController.getIMatBackend().addFavorite(product);
          } else {
            IMatController.getIMatBackend().removeFavorite(product);
          }
        }
      }
    });
    
    productFavorite14.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = products.get(14);
        if (IMatController.currentUser != null) {
          if (productFavorite14.isSelected()) {
            int index = IMatController.getNumberOfRecords(product.toString());
            IMatController.addFavorite(product.toString(), ++index);
          } else {
            // Implement how to find correct index
            int index = 1;
            IMatController.removeFavorite(product.toString(), index);
          }
        } else {
          if (productFavorite14.isSelected()) {
            IMatController.getIMatBackend().addFavorite(product);
          } else {
            IMatController.getIMatBackend().removeFavorite(product);
          }
        }
      }
    });
    
    productFavorite15.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = products.get(15);
        if (IMatController.currentUser != null) {
          if (productFavorite15.isSelected()) {
            int index = IMatController.getNumberOfRecords(product.toString());
            IMatController.addFavorite(product.toString(), ++index);
          } else {
            // Implement how to find correct index
            int index = 1;
            IMatController.removeFavorite(product.toString(), index);
          }
        } else {
          if (productFavorite15.isSelected()) {
            IMatController.getIMatBackend().addFavorite(product);
          } else {
            IMatController.getIMatBackend().removeFavorite(product);
          }
        }
      }
    });
    
    productFavorite16.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = products.get(16);
        if (IMatController.currentUser != null) {
          if (productFavorite16.isSelected()) {
            int index = IMatController.getNumberOfRecords(product.toString());
            IMatController.addFavorite(product.toString(), ++index);
          } else {
            // Implement how to find correct index
            int index = 1;
            IMatController.removeFavorite(product.toString(), index);
          }
        } else {
          if (productFavorite16.isSelected()) {
            IMatController.getIMatBackend().addFavorite(product);
          } else {
            IMatController.getIMatBackend().removeFavorite(product);
          }
        }
      }
    });
    
    productFavorite17.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = products.get(17);
        if (IMatController.currentUser != null) {
          if (productFavorite17.isSelected()) {
            int index = IMatController.getNumberOfRecords(product.toString());
            IMatController.addFavorite(product.toString(), ++index);
          } else {
            // Implement how to find correct index
            int index = 1;
            IMatController.removeFavorite(product.toString(), index);
          }
        } else {
          if (productFavorite17.isSelected()) {
            IMatController.getIMatBackend().addFavorite(product);
          } else {
            IMatController.getIMatBackend().removeFavorite(product);
          }
        }
      }
    });
    
    productFavorite18.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = products.get(18);
        if (IMatController.currentUser != null) {
          if (productFavorite18.isSelected()) {
            int index = IMatController.getNumberOfRecords(product.toString());
            IMatController.addFavorite(product.toString(), ++index);
          } else {
            // Implement how to find correct index
            int index = 1;
            IMatController.removeFavorite(product.toString(), index);
          }
        } else {
          if (productFavorite18.isSelected()) {
            IMatController.getIMatBackend().addFavorite(product);
          } else {
            IMatController.getIMatBackend().removeFavorite(product);
          }
        }
      }
    });
    
    productFavorite19.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = products.get(19);
        if (IMatController.currentUser != null) {
          if (productFavorite19.isSelected()) {
            int index = IMatController.getNumberOfRecords(product.toString());
            IMatController.addFavorite(product.toString(), ++index);
          } else {
            // Implement how to find correct index
            int index = 1;
            IMatController.removeFavorite(product.toString(), index);
          }
        } else {
          if (productFavorite19.isSelected()) {
            IMatController.getIMatBackend().addFavorite(product);
          } else {
            IMatController.getIMatBackend().removeFavorite(product);
          }
        }
      }
    });
    
    productFavorite20.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = products.get(20);
        if (IMatController.currentUser != null) {
          if (productFavorite20.isSelected()) {
            int index = IMatController.getNumberOfRecords(product.toString());
            IMatController.addFavorite(product.toString(), ++index);
          } else {
            // Implement how to find correct index
            int index = 1;
            IMatController.removeFavorite(product.toString(), index);
          }
        } else {
          if (productFavorite20.isSelected()) {
            IMatController.getIMatBackend().addFavorite(product);
          } else {
            IMatController.getIMatBackend().removeFavorite(product);
          }
        }
      }
    });
    
    productFavorite21.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = products.get(21);
        if (IMatController.currentUser != null) {
          if (productFavorite21.isSelected()) {
            int index = IMatController.getNumberOfRecords(product.toString());
            IMatController.addFavorite(product.toString(), ++index);
          } else {
            // Implement how to find correct index
            int index = 1;
            IMatController.removeFavorite(product.toString(), index);
          }
        } else {
          if (productFavorite21.isSelected()) {
            IMatController.getIMatBackend().addFavorite(product);
          } else {
            IMatController.getIMatBackend().removeFavorite(product);
          }
        }
      }
    });
    
    productFavorite22.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = products.get(22);
        if (IMatController.currentUser != null) {
          if (productFavorite22.isSelected()) {
            int index = IMatController.getNumberOfRecords(product.toString());
            IMatController.addFavorite(product.toString(), ++index);
          } else {
            // Implement how to find correct index
            int index = 1;
            IMatController.removeFavorite(product.toString(), index);
          }
        } else {
          if (productFavorite22.isSelected()) {
            IMatController.getIMatBackend().addFavorite(product);
          } else {
            IMatController.getIMatBackend().removeFavorite(product);
          }
        }
      }
    });
    
    productFavorite23.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = products.get(23);
        if (IMatController.currentUser != null) {
          if (productFavorite23.isSelected()) {
            int index = IMatController.getNumberOfRecords(product.toString());
            IMatController.addFavorite(product.toString(), ++index);
          } else {
            // Implement how to find correct index
            int index = 1;
            IMatController.removeFavorite(product.toString(), index);
          }
        } else {
          if (productFavorite23.isSelected()) {
            IMatController.getIMatBackend().addFavorite(product);
          } else {
            IMatController.getIMatBackend().removeFavorite(product);
          }
        }
      }
    });
    
    productFavorite24.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = products.get(24);
        if (IMatController.currentUser != null) {
          if (productFavorite24.isSelected()) {
            int index = IMatController.getNumberOfRecords(product.toString());
            IMatController.addFavorite(product.toString(), ++index);
          } else {
            // Implement how to find correct index
            int index = 1;
            IMatController.removeFavorite(product.toString(), index);
          }
        } else {
          if (productFavorite24.isSelected()) {
            IMatController.getIMatBackend().addFavorite(product);
          } else {
            IMatController.getIMatBackend().removeFavorite(product);
          }
        }
      }
    });
    
    productFavorite25.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = products.get(25);
        if (IMatController.currentUser != null) {
          if (productFavorite25.isSelected()) {
            int index = IMatController.getNumberOfRecords(product.toString());
            IMatController.addFavorite(product.toString(), ++index);
          } else {
            // Implement how to find correct index
            int index = 1;
            IMatController.removeFavorite(product.toString(), index);
          }
        } else {
          if (productFavorite25.isSelected()) {
            IMatController.getIMatBackend().addFavorite(product);
          } else {
            IMatController.getIMatBackend().removeFavorite(product);
          }
        }
      }
    });
    
    productFavorite26.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = products.get(26);
        if (IMatController.currentUser != null) {
          if (productFavorite26.isSelected()) {
            int index = IMatController.getNumberOfRecords(product.toString());
            IMatController.addFavorite(product.toString(), ++index);
          } else {
            // Implement how to find correct index
            int index = 1;
            IMatController.removeFavorite(product.toString(), index);
          }
        } else {
          if (productFavorite26.isSelected()) {
            IMatController.getIMatBackend().addFavorite(product);
          } else {
            IMatController.getIMatBackend().removeFavorite(product);
          }
        }
      }
    });
    
    productFavorite27.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = products.get(27);
        if (IMatController.currentUser != null) {
          if (productFavorite27.isSelected()) {
            int index = IMatController.getNumberOfRecords(product.toString());
            IMatController.addFavorite(product.toString(), ++index);
          } else {
            // Implement how to find correct index
            int index = 1;
            IMatController.removeFavorite(product.toString(), index);
          }
        } else {
          if (productFavorite27.isSelected()) {
            IMatController.getIMatBackend().addFavorite(product);
          } else {
            IMatController.getIMatBackend().removeFavorite(product);
          }
        }
      }
    });
    
    productFavorite28.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = products.get(28);
        if (IMatController.currentUser != null) {
          if (productFavorite28.isSelected()) {
            int index = IMatController.getNumberOfRecords(product.toString());
            IMatController.addFavorite(product.toString(), ++index);
          } else {
            // Implement how to find correct index
            int index = 1;
            IMatController.removeFavorite(product.toString(), index);
          }
        } else {
          if (productFavorite28.isSelected()) {
            IMatController.getIMatBackend().addFavorite(product);
          } else {
            IMatController.getIMatBackend().removeFavorite(product);
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
    
    plus12.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = products.get(12);
        increment(textField12, totalCost12, product);
      }
    });
    
    minus12.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = products.get(12);
        decrement(textField12, totalCost12, product);
      }
    });
    
    plus13.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = products.get(13);
        increment(textField13, totalCost13, product);
      }
    });
    
    minus13.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = products.get(13);
        decrement(textField13, totalCost13, product);
      }
    });
    
    plus14.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = products.get(14);
        increment(textField14, totalCost14, product);
      }
    });
    
    minus14.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = products.get(14);
        decrement(textField14, totalCost14, product);
      }
    });
    
    plus15.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = products.get(15);
        increment(textField15, totalCost15, product);
      }
    });
    
    minus15.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = products.get(15);
        decrement(textField15, totalCost15, product);
      }
    });
    
    plus16.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = products.get(16);
        increment(textField16, totalCost16, product);
      }
    });
    
    minus16.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = products.get(16);
        decrement(textField16, totalCost16, product);
      }
    });
    
    plus17.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = products.get(17);
        increment(textField17, totalCost17, product);
      }
    });
    
    minus17.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = products.get(17);
        decrement(textField17, totalCost17, product);
      }
    });
    
    plus18.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = products.get(18);
        increment(textField18, totalCost18, product);
      }
    });
    
    minus18.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = products.get(18);
        decrement(textField18, totalCost18, product);
      }
    });
    
    plus19.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = products.get(19);
        increment(textField19, totalCost19, product);
      }
    });
    
    minus19.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = products.get(19);
        decrement(textField19, totalCost19, product);
      }
    });
    
    plus20.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = products.get(20);
        increment(textField20, totalCost20, product);
      }
    });
    
    minus20.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = products.get(20);
        decrement(textField20, totalCost20, product);
      }
    });
    
    plus21.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = products.get(21);
        increment(textField21, totalCost21, product);
      }
    });
    
    minus21.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = products.get(21);
        decrement(textField21, totalCost21, product);
      }
    });
    
    plus22.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = products.get(22);
        increment(textField22, totalCost22, product);
      }
    });
    
    minus22.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = products.get(22);
        decrement(textField22, totalCost22, product);
      }
    });
    
    plus23.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = products.get(23);
        increment(textField23, totalCost23, product);
      }
    });
    
    minus23.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = products.get(23);
        decrement(textField23, totalCost23, product);
      }
    });
    
    plus24.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = products.get(24);
        increment(textField24, totalCost24, product);
      }
    });
    
    minus24.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = products.get(24);
        decrement(textField24, totalCost24, product);
      }
    });
    
    plus25.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = products.get(25);
        increment(textField25, totalCost25, product);
      }
    });
    
    minus25.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = products.get(25);
        decrement(textField25, totalCost25, product);
      }
    });
    
    plus26.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = products.get(26);
        increment(textField26, totalCost26, product);
      }
    });
    
    minus26.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = products.get(26);
        decrement(textField26, totalCost26, product);
      }
    });
    
    plus27.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = products.get(27);
        increment(textField27, totalCost27, product);
      }
    });
    
    minus27.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = products.get(27);
        decrement(textField27, totalCost27, product);
      }
    });
    
    plus28.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = products.get(28);
        increment(textField28, totalCost28, product);
      }
    });
    
    minus28.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = products.get(28);
        decrement(textField28, totalCost28, product);
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
            showOrderHistory();
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
        Product product = products.get(0);
        productUnits = Integer.parseInt(textField.getText());
        textField.setText("1");
        if (IMatController.currentUser != null) {
          productNr++;
          int cost = (int)product.getPrice();
          int sum = productUnits*cost;
          totalCostInt += sum;
          IMatShoppingCart.cart.addShoppingItem(product, productUnits, sum);
          imat.getVarukorgController().updateTotalCost();
          List<IMatShoppingItem> list = imat.getVarukorgController().getIMatShoppingCart().getCart().getAllProducts();
          imat.getVarukorgController().initShoppingCart(list);
          initCheckoutCart(list);
        } else {      
          IMatController.getShoppingCart().addItem(new ShoppingItem(product, productUnits));
          Integer totalSum = (int)IMatController.getShoppingCart().getTotal();
          imat.getVarukorgController().updateTotalCostBackend(totalSum);
          imat.getVarukorgController().initShoppingCart(imat.getVarukorgController().convertBackendToIMat());
          initCheckoutCart(imat.getVarukorgController().convertBackendToIMat());
        }
      }
    });
    
    buy1.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = products.get(1);
        productUnits = Integer.parseInt(textField1.getText());
        textField1.setText("1");
        if (IMatController.currentUser != null) {
          productNr++;
          int cost = (int)product.getPrice();
          int sum = productUnits*cost;
          totalCostInt += sum;
          IMatShoppingCart.cart.addShoppingItem(product, productUnits, sum);
          imat.getVarukorgController().updateTotalCost();
          List<IMatShoppingItem> list = imat.getVarukorgController().getIMatShoppingCart().getCart().getAllProducts();
          imat.getVarukorgController().initShoppingCart(list);
          initCheckoutCart(list);
        } else {
          IMatController.getShoppingCart().addItem(new ShoppingItem(product, productUnits));
          Integer totalSum = (int)IMatController.getShoppingCart().getTotal();
          imat.getVarukorgController().updateTotalCostBackend(totalSum);
          imat.getVarukorgController().initShoppingCart(imat.getVarukorgController().convertBackendToIMat());
        }

      }
    });
    
    buy2.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = products.get(2);
        productUnits = Integer.parseInt(textField2.getText());
        textField2.setText("1");
        if (IMatController.currentUser != null) {
          productNr++;
          int cost = (int)product.getPrice();
          int sum = productUnits*cost;
          totalCostInt += sum;
          IMatShoppingCart.cart.addShoppingItem(product, productUnits, sum);
          imat.getVarukorgController().updateTotalCost();
          List<IMatShoppingItem> list = imat.getVarukorgController().getIMatShoppingCart().getCart().getAllProducts();
          imat.getVarukorgController().initShoppingCart(list);
          initCheckoutCart(list);
        } else {
          IMatController.getShoppingCart().addItem(new ShoppingItem(product, productUnits));
          Integer totalSum = (int)IMatController.getShoppingCart().getTotal();
          imat.getVarukorgController().updateTotalCostBackend(totalSum);
          imat.getVarukorgController().initShoppingCart(imat.getVarukorgController().convertBackendToIMat());
        }
      }
    });
    
    buy3.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = products.get(3);
        productUnits = Integer.parseInt(textField3.getText());
        textField3.setText("1");
        if (IMatController.currentUser != null) {
          productNr++;
          int cost = (int)product.getPrice();
          int sum = productUnits*cost;
          totalCostInt += sum;
          IMatShoppingCart.cart.addShoppingItem(product, productUnits, sum);
          imat.getVarukorgController().updateTotalCost();
          List<IMatShoppingItem> list = imat.getVarukorgController().getIMatShoppingCart().getCart().getAllProducts();
          imat.getVarukorgController().initShoppingCart(list);
          initCheckoutCart(list);
        } else {
          IMatController.getShoppingCart().addItem(new ShoppingItem(product, productUnits));
          Integer totalSum = (int)IMatController.getShoppingCart().getTotal();
          imat.getVarukorgController().updateTotalCostBackend(totalSum);
          imat.getVarukorgController().initShoppingCart(imat.getVarukorgController().convertBackendToIMat());
        }
      }
    });
    
    buy4.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = products.get(4);
        productUnits = Integer.parseInt(textField4.getText());
        textField4.setText("1");
        if (IMatController.currentUser != null) {
          productNr++;
          int cost = (int)product.getPrice();
          int sum = productUnits*cost;
          totalCostInt += sum;
          IMatShoppingCart.cart.addShoppingItem(product, productUnits, sum);
          imat.getVarukorgController().updateTotalCost();
          List<IMatShoppingItem> list = imat.getVarukorgController().getIMatShoppingCart().getCart().getAllProducts();
          imat.getVarukorgController().initShoppingCart(list);
          initCheckoutCart(list);
        } else {
          IMatController.getShoppingCart().addItem(new ShoppingItem(product, productUnits));
          Integer totalSum = (int)IMatController.getShoppingCart().getTotal();
          imat.getVarukorgController().updateTotalCostBackend(totalSum);
          imat.getVarukorgController().initShoppingCart(imat.getVarukorgController().convertBackendToIMat());
        }
      }
    });
    
    buy5.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = products.get(5);
        productUnits = Integer.parseInt(textField5.getText());
        textField5.setText("1");
        if (IMatController.currentUser != null) {
          productNr++;
          int cost = (int)product.getPrice();
          int sum = productUnits*cost;
          totalCostInt += sum;
          IMatShoppingCart.cart.addShoppingItem(product, productUnits, sum);
          imat.getVarukorgController().updateTotalCost();
          List<IMatShoppingItem> list = imat.getVarukorgController().getIMatShoppingCart().getCart().getAllProducts();
          imat.getVarukorgController().initShoppingCart(list);
          initCheckoutCart(list);
        } else {
          IMatController.getShoppingCart().addItem(new ShoppingItem(product, productUnits));
          Integer totalSum = (int)IMatController.getShoppingCart().getTotal();
          imat.getVarukorgController().updateTotalCostBackend(totalSum);
          imat.getVarukorgController().initShoppingCart(imat.getVarukorgController().convertBackendToIMat());
        }
      }
    });
    
    buy6.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = products.get(6);
        productUnits = Integer.parseInt(textField6.getText());
        textField6.setText("1");
        if (IMatController.currentUser != null) {
          productNr++;
          int cost = (int)product.getPrice();
          int sum = productUnits*cost;
          totalCostInt += sum;
          IMatShoppingCart.cart.addShoppingItem(product, productUnits, sum);
          imat.getVarukorgController().updateTotalCost();
          List<IMatShoppingItem> list = imat.getVarukorgController().getIMatShoppingCart().getCart().getAllProducts();
          imat.getVarukorgController().initShoppingCart(list);
          initCheckoutCart(list);
        } else {
          IMatController.getShoppingCart().addItem(new ShoppingItem(product, productUnits));
          Integer totalSum = (int)IMatController.getShoppingCart().getTotal();
          imat.getVarukorgController().updateTotalCostBackend(totalSum);
          imat.getVarukorgController().initShoppingCart(imat.getVarukorgController().convertBackendToIMat());
        }
      }
    });
    
    buy7.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = products.get(7);
        productUnits = Integer.parseInt(textField7.getText());
        textField7.setText("1");
        if (IMatController.currentUser != null) {
          productNr++;
          int cost = (int)product.getPrice();
          int sum = productUnits*cost;
          totalCostInt += sum;
          IMatShoppingCart.cart.addShoppingItem(product, productUnits, sum);
          imat.getVarukorgController().updateTotalCost();
          List<IMatShoppingItem> list = imat.getVarukorgController().getIMatShoppingCart().getCart().getAllProducts();
          imat.getVarukorgController().initShoppingCart(list);
          initCheckoutCart(list);
        } else {
          IMatController.getShoppingCart().addItem(new ShoppingItem(product, productUnits));
          Integer totalSum = (int)IMatController.getShoppingCart().getTotal();
          imat.getVarukorgController().updateTotalCostBackend(totalSum);
          imat.getVarukorgController().initShoppingCart(imat.getVarukorgController().convertBackendToIMat());
        }
      }
    });
    
    buy8.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = products.get(8);
        productUnits = Integer.parseInt(textField8.getText());
        textField8.setText("1");
        if (IMatController.currentUser != null) {
          productNr++;
          int cost = (int)product.getPrice();
          int sum = productUnits*cost;
          totalCostInt += sum;
          IMatShoppingCart.cart.addShoppingItem(product, productUnits, sum);
          imat.getVarukorgController().updateTotalCost();
          List<IMatShoppingItem> list = imat.getVarukorgController().getIMatShoppingCart().getCart().getAllProducts();
          imat.getVarukorgController().initShoppingCart(list);
          initCheckoutCart(list);
        } else {
          IMatController.getShoppingCart().addItem(new ShoppingItem(product, productUnits));
          Integer totalSum = (int)IMatController.getShoppingCart().getTotal();
          imat.getVarukorgController().updateTotalCostBackend(totalSum);
          imat.getVarukorgController().initShoppingCart(imat.getVarukorgController().convertBackendToIMat());
        }
      }
    });
    
    buy9.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = products.get(9);
        productUnits = Integer.parseInt(textField9.getText());
        textField9.setText("1");
        if (IMatController.currentUser != null) {
          productNr++;
          int cost = (int)product.getPrice();
          int sum = productUnits*cost;
          totalCostInt += sum;
          IMatShoppingCart.cart.addShoppingItem(product, productUnits, sum);
          imat.getVarukorgController().updateTotalCost();
          List<IMatShoppingItem> list = imat.getVarukorgController().getIMatShoppingCart().getCart().getAllProducts();
          imat.getVarukorgController().initShoppingCart(list);
          initCheckoutCart(list);
        } else {
          IMatController.getShoppingCart().addItem(new ShoppingItem(product, productUnits));
          Integer totalSum = (int)IMatController.getShoppingCart().getTotal();
          imat.getVarukorgController().updateTotalCostBackend(totalSum);
          imat.getVarukorgController().initShoppingCart(imat.getVarukorgController().convertBackendToIMat());
        }
      }
    });
  
    buy10.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = products.get(0);
        productUnits = Integer.parseInt(textField.getText());
        textField10.setText("1");
        if (IMatController.currentUser != null) {
          productNr++;
          int cost = (int)product.getPrice();
          int sum = productUnits*cost;
          totalCostInt += sum;
          IMatShoppingCart.cart.addShoppingItem(product, productUnits, sum);
          imat.getVarukorgController().updateTotalCost();
          List<IMatShoppingItem> list = imat.getVarukorgController().getIMatShoppingCart().getCart().getAllProducts();
          imat.getVarukorgController().initShoppingCart(list);
          initCheckoutCart(list);
        } else {      
          IMatController.getShoppingCart().addItem(new ShoppingItem(product, productUnits));
          Integer totalSum = (int)IMatController.getShoppingCart().getTotal();
          imat.getVarukorgController().updateTotalCostBackend(totalSum);
          imat.getVarukorgController().initShoppingCart(imat.getVarukorgController().convertBackendToIMat());
        }
      }
    });
    
    buy11.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = products.get(11);
        productUnits = Integer.parseInt(textField11.getText());
        textField11.setText("1");
        if (IMatController.currentUser != null) {
          productNr++;
          int cost = (int)product.getPrice();
          int sum = productUnits*cost;
          totalCostInt += sum;
          IMatShoppingCart.cart.addShoppingItem(product, productUnits, sum);
          imat.getVarukorgController().updateTotalCost();
          List<IMatShoppingItem> list = imat.getVarukorgController().getIMatShoppingCart().getCart().getAllProducts();
          imat.getVarukorgController().initShoppingCart(list);
          initCheckoutCart(list);
        } else {
          IMatController.getShoppingCart().addItem(new ShoppingItem(product, productUnits));
          Integer totalSum = (int)IMatController.getShoppingCart().getTotal();
          imat.getVarukorgController().updateTotalCostBackend(totalSum);
          imat.getVarukorgController().initShoppingCart(imat.getVarukorgController().convertBackendToIMat());
        }

      }
    });
    
    buy12.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = products.get(12);
        productUnits = Integer.parseInt(textField12.getText());
        textField12.setText("1");
        if (IMatController.currentUser != null) {
          productNr++;
          int cost = (int)product.getPrice();
          int sum = productUnits*cost;
          totalCostInt += sum;
          IMatShoppingCart.cart.addShoppingItem(product, productUnits, sum);
          imat.getVarukorgController().updateTotalCost();
          List<IMatShoppingItem> list = imat.getVarukorgController().getIMatShoppingCart().getCart().getAllProducts();
          imat.getVarukorgController().initShoppingCart(list);
        } else {
          IMatController.getShoppingCart().addItem(new ShoppingItem(product, productUnits));
          Integer totalSum = (int)IMatController.getShoppingCart().getTotal();
          imat.getVarukorgController().updateTotalCostBackend(totalSum);
          imat.getVarukorgController().initShoppingCart(imat.getVarukorgController().convertBackendToIMat());
        }
      }
    });
    
    buy13.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = products.get(13);
        productUnits = Integer.parseInt(textField13.getText());
        textField13.setText("1");
        if (IMatController.currentUser != null) {
          productNr++;
          int cost = (int)product.getPrice();
          int sum = productUnits*cost;
          totalCostInt += sum;
          IMatShoppingCart.cart.addShoppingItem(product, productUnits, sum);
          imat.getVarukorgController().updateTotalCost();
          List<IMatShoppingItem> list = imat.getVarukorgController().getIMatShoppingCart().getCart().getAllProducts();
          imat.getVarukorgController().initShoppingCart(list);
        } else {
          IMatController.getShoppingCart().addItem(new ShoppingItem(product, productUnits));
          Integer totalSum = (int)IMatController.getShoppingCart().getTotal();
          imat.getVarukorgController().updateTotalCostBackend(totalSum);
          imat.getVarukorgController().initShoppingCart(imat.getVarukorgController().convertBackendToIMat());
        }
      }
    });
    
    buy14.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = products.get(14);
        productUnits = Integer.parseInt(textField14.getText());
        textField14.setText("1");
        if (IMatController.currentUser != null) {
          productNr++;
          int cost = (int)product.getPrice();
          int sum = productUnits*cost;
          totalCostInt += sum;
          IMatShoppingCart.cart.addShoppingItem(product, productUnits, sum);
          imat.getVarukorgController().updateTotalCost();
          List<IMatShoppingItem> list = imat.getVarukorgController().getIMatShoppingCart().getCart().getAllProducts();
          imat.getVarukorgController().initShoppingCart(list);
        } else {
          IMatController.getShoppingCart().addItem(new ShoppingItem(product, productUnits));
          Integer totalSum = (int)IMatController.getShoppingCart().getTotal();
          imat.getVarukorgController().updateTotalCostBackend(totalSum);
          imat.getVarukorgController().initShoppingCart(imat.getVarukorgController().convertBackendToIMat());
        }
      }
    });
    
    buy15.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = products.get(15);
        productUnits = Integer.parseInt(textField15.getText());
        textField15.setText("1");
        if (IMatController.currentUser != null) {
          productNr++;
          int cost = (int)product.getPrice();
          int sum = productUnits*cost;
          totalCostInt += sum;
          IMatShoppingCart.cart.addShoppingItem(product, productUnits, sum);
          imat.getVarukorgController().updateTotalCost();
          List<IMatShoppingItem> list = imat.getVarukorgController().getIMatShoppingCart().getCart().getAllProducts();
          imat.getVarukorgController().initShoppingCart(list);
        } else {
          IMatController.getShoppingCart().addItem(new ShoppingItem(product, productUnits));
          Integer totalSum = (int)IMatController.getShoppingCart().getTotal();
          imat.getVarukorgController().updateTotalCostBackend(totalSum);
          imat.getVarukorgController().initShoppingCart(imat.getVarukorgController().convertBackendToIMat());
        }
      }
    });
    
    buy16.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = products.get(16);
        productUnits = Integer.parseInt(textField16.getText());
        textField16.setText("1");
        if (IMatController.currentUser != null) {
          productNr++;
          int cost = (int)product.getPrice();
          int sum = productUnits*cost;
          totalCostInt += sum;
          IMatShoppingCart.cart.addShoppingItem(product, productUnits, sum);
          imat.getVarukorgController().updateTotalCost();
          List<IMatShoppingItem> list = imat.getVarukorgController().getIMatShoppingCart().getCart().getAllProducts();
          imat.getVarukorgController().initShoppingCart(list);
        } else {
          IMatController.getShoppingCart().addItem(new ShoppingItem(product, productUnits));
          Integer totalSum = (int)IMatController.getShoppingCart().getTotal();
          imat.getVarukorgController().updateTotalCostBackend(totalSum);
          imat.getVarukorgController().initShoppingCart(imat.getVarukorgController().convertBackendToIMat());
        }
      }
    });
    
    buy17.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = products.get(17);
        productUnits = Integer.parseInt(textField17.getText());
        textField17.setText("1");
        if (IMatController.currentUser != null) {
          productNr++;
          int cost = (int)product.getPrice();
          int sum = productUnits*cost;
          totalCostInt += sum;
          IMatShoppingCart.cart.addShoppingItem(product, productUnits, sum);
          imat.getVarukorgController().updateTotalCost();
          List<IMatShoppingItem> list = imat.getVarukorgController().getIMatShoppingCart().getCart().getAllProducts();
          imat.getVarukorgController().initShoppingCart(list);
        } else {
          IMatController.getShoppingCart().addItem(new ShoppingItem(product, productUnits));
          Integer totalSum = (int)IMatController.getShoppingCart().getTotal();
          imat.getVarukorgController().updateTotalCostBackend(totalSum);
          imat.getVarukorgController().initShoppingCart(imat.getVarukorgController().convertBackendToIMat());
        }
      }
    });
    
    buy18.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = products.get(18);
        productUnits = Integer.parseInt(textField18.getText());
        textField18.setText("1");
        if (IMatController.currentUser != null) {
          productNr++;
          int cost = (int)product.getPrice();
          int sum = productUnits*cost;
          totalCostInt += sum;
          IMatShoppingCart.cart.addShoppingItem(product, productUnits, sum);
          imat.getVarukorgController().updateTotalCost();
          List<IMatShoppingItem> list = imat.getVarukorgController().getIMatShoppingCart().getCart().getAllProducts();
          imat.getVarukorgController().initShoppingCart(list);
        } else {
          IMatController.getShoppingCart().addItem(new ShoppingItem(product, productUnits));
          Integer totalSum = (int)IMatController.getShoppingCart().getTotal();
          imat.getVarukorgController().updateTotalCostBackend(totalSum);
          imat.getVarukorgController().initShoppingCart(imat.getVarukorgController().convertBackendToIMat());
        }
      }
    });
    
    buy19.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = products.get(19);
        productUnits = Integer.parseInt(textField19.getText());
        textField19.setText("1");
        if (IMatController.currentUser != null) {
          productNr++;
          int cost = (int)product.getPrice();
          int sum = productUnits*cost;
          totalCostInt += sum;
          IMatShoppingCart.cart.addShoppingItem(product, productUnits, sum);
          imat.getVarukorgController().updateTotalCost();
          List<IMatShoppingItem> list = imat.getVarukorgController().getIMatShoppingCart().getCart().getAllProducts();
          imat.getVarukorgController().initShoppingCart(list);
        } else {
          IMatController.getShoppingCart().addItem(new ShoppingItem(product, productUnits));
          Integer totalSum = (int)IMatController.getShoppingCart().getTotal();
          imat.getVarukorgController().updateTotalCostBackend(totalSum);
          imat.getVarukorgController().initShoppingCart(imat.getVarukorgController().convertBackendToIMat());
        }
      }
    });
    
    buy21.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = products.get(21);
        productUnits = Integer.parseInt(textField21.getText());
        textField21.setText("1");
        if (IMatController.currentUser != null) {
          productNr++;
          int cost = (int)product.getPrice();
          int sum = productUnits*cost;
          totalCostInt += sum;
          IMatShoppingCart.cart.addShoppingItem(product, productUnits, sum);
          imat.getVarukorgController().updateTotalCost();
          List<IMatShoppingItem> list = imat.getVarukorgController().getIMatShoppingCart().getCart().getAllProducts();
          imat.getVarukorgController().initShoppingCart(list);
        } else {
          IMatController.getShoppingCart().addItem(new ShoppingItem(product, productUnits));
          Integer totalSum = (int)IMatController.getShoppingCart().getTotal();
          imat.getVarukorgController().updateTotalCostBackend(totalSum);
          imat.getVarukorgController().initShoppingCart(imat.getVarukorgController().convertBackendToIMat());
        }

      }
    });
    
    buy22.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = products.get(22);
        productUnits = Integer.parseInt(textField22.getText());
        textField22.setText("1");
        if (IMatController.currentUser != null) {
          productNr++;
          int cost = (int)product.getPrice();
          int sum = productUnits*cost;
          totalCostInt += sum;
          IMatShoppingCart.cart.addShoppingItem(product, productUnits, sum);
          imat.getVarukorgController().updateTotalCost();
          List<IMatShoppingItem> list = imat.getVarukorgController().getIMatShoppingCart().getCart().getAllProducts();
          imat.getVarukorgController().initShoppingCart(list);
        } else {
          IMatController.getShoppingCart().addItem(new ShoppingItem(product, productUnits));
          Integer totalSum = (int)IMatController.getShoppingCart().getTotal();
          imat.getVarukorgController().updateTotalCostBackend(totalSum);
          imat.getVarukorgController().initShoppingCart(imat.getVarukorgController().convertBackendToIMat());
        }
      }
    });
    
    buy23.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = products.get(23);
        productUnits = Integer.parseInt(textField23.getText());
        textField23.setText("1");
        if (IMatController.currentUser != null) {
          productNr++;
          int cost = (int)product.getPrice();
          int sum = productUnits*cost;
          totalCostInt += sum;
          IMatShoppingCart.cart.addShoppingItem(product, productUnits, sum);
          imat.getVarukorgController().updateTotalCost();
          List<IMatShoppingItem> list = imat.getVarukorgController().getIMatShoppingCart().getCart().getAllProducts();
          imat.getVarukorgController().initShoppingCart(list);
        } else {
          IMatController.getShoppingCart().addItem(new ShoppingItem(product, productUnits));
          Integer totalSum = (int)IMatController.getShoppingCart().getTotal();
          imat.getVarukorgController().updateTotalCostBackend(totalSum);
          imat.getVarukorgController().initShoppingCart(imat.getVarukorgController().convertBackendToIMat());
        }
      }
    });
    
    buy24.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = products.get(24);
        productUnits = Integer.parseInt(textField24.getText());
        textField24.setText("1");
        if (IMatController.currentUser != null) {
          productNr++;
          int cost = (int)product.getPrice();
          int sum = productUnits*cost;
          totalCostInt += sum;
          IMatShoppingCart.cart.addShoppingItem(product, productUnits, sum);
          imat.getVarukorgController().updateTotalCost();
          List<IMatShoppingItem> list = imat.getVarukorgController().getIMatShoppingCart().getCart().getAllProducts();
          imat.getVarukorgController().initShoppingCart(list);
        } else {
          IMatController.getShoppingCart().addItem(new ShoppingItem(product, productUnits));
          Integer totalSum = (int)IMatController.getShoppingCart().getTotal();
          imat.getVarukorgController().updateTotalCostBackend(totalSum);
          imat.getVarukorgController().initShoppingCart(imat.getVarukorgController().convertBackendToIMat());
        }
      }
    });
    
    buy25.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = products.get(25);
        productUnits = Integer.parseInt(textField25.getText());
        textField25.setText("1");
        if (IMatController.currentUser != null) {
          productNr++;
          int cost = (int)product.getPrice();
          int sum = productUnits*cost;
          totalCostInt += sum;
          IMatShoppingCart.cart.addShoppingItem(product, productUnits, sum);
          imat.getVarukorgController().updateTotalCost();
          List<IMatShoppingItem> list = imat.getVarukorgController().getIMatShoppingCart().getCart().getAllProducts();
          imat.getVarukorgController().initShoppingCart(list);
        } else {
          IMatController.getShoppingCart().addItem(new ShoppingItem(product, productUnits));
          Integer totalSum = (int)IMatController.getShoppingCart().getTotal();
          imat.getVarukorgController().updateTotalCostBackend(totalSum);
          imat.getVarukorgController().initShoppingCart(imat.getVarukorgController().convertBackendToIMat());
        }
      }
    });
    
    buy26.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = products.get(26);
        productUnits = Integer.parseInt(textField26.getText());
        textField26.setText("1");
        if (IMatController.currentUser != null) {
          productNr++;
          int cost = (int)product.getPrice();
          int sum = productUnits*cost;
          totalCostInt += sum;
          IMatShoppingCart.cart.addShoppingItem(product, productUnits, sum);
          imat.getVarukorgController().updateTotalCost();
          List<IMatShoppingItem> list = imat.getVarukorgController().getIMatShoppingCart().getCart().getAllProducts();
          imat.getVarukorgController().initShoppingCart(list);
        } else {
          IMatController.getShoppingCart().addItem(new ShoppingItem(product, productUnits));
          Integer totalSum = (int)IMatController.getShoppingCart().getTotal();
          imat.getVarukorgController().updateTotalCostBackend(totalSum);
          imat.getVarukorgController().initShoppingCart(imat.getVarukorgController().convertBackendToIMat());
        }
      }
    });
    
    buy27.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = products.get(27);
        productUnits = Integer.parseInt(textField27.getText());
        textField27.setText("1");
        if (IMatController.currentUser != null) {
          productNr++;
          int cost = (int)product.getPrice();
          int sum = productUnits*cost;
          totalCostInt += sum;
          IMatShoppingCart.cart.addShoppingItem(product, productUnits, sum);
          imat.getVarukorgController().updateTotalCost();
          List<IMatShoppingItem> list = imat.getVarukorgController().getIMatShoppingCart().getCart().getAllProducts();
          imat.getVarukorgController().initShoppingCart(list);
        } else {
          IMatController.getShoppingCart().addItem(new ShoppingItem(product, productUnits));
          Integer totalSum = (int)IMatController.getShoppingCart().getTotal();
          imat.getVarukorgController().updateTotalCostBackend(totalSum);
          imat.getVarukorgController().initShoppingCart(imat.getVarukorgController().convertBackendToIMat());
        }
      }
    });
    
    buy28.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = products.get(28);
        productUnits = Integer.parseInt(textField28.getText());
        textField28.setText("1");
        if (IMatController.currentUser != null) {
          productNr++;
          int cost = (int)product.getPrice();
          int sum = productUnits*cost;
          totalCostInt += sum;
          IMatShoppingCart.cart.addShoppingItem(product, productUnits, sum);
          imat.getVarukorgController().updateTotalCost();
          List<IMatShoppingItem> list = imat.getVarukorgController().getIMatShoppingCart().getCart().getAllProducts();
          imat.getVarukorgController().initShoppingCart(list);
        } else {
          IMatController.getShoppingCart().addItem(new ShoppingItem(product, productUnits));
          Integer totalSum = (int)IMatController.getShoppingCart().getTotal();
          imat.getVarukorgController().updateTotalCostBackend(totalSum);
          imat.getVarukorgController().initShoppingCart(imat.getVarukorgController().convertBackendToIMat());
        }
      }
    });
    
    buy20.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Product product = products.get(20);
        productUnits = Integer.parseInt(textField20.getText());
        textField20.setText("1");
        if (IMatController.currentUser != null) {
          productNr++;
          int cost = (int)product.getPrice();
          int sum = productUnits*cost;
          totalCostInt += sum;
          IMatShoppingCart.cart.addShoppingItem(product, productUnits, sum);
          imat.getVarukorgController().updateTotalCost();
          List<IMatShoppingItem> list = imat.getVarukorgController().getIMatShoppingCart().getCart().getAllProducts();
          imat.getVarukorgController().initShoppingCart(list);
        } else {
          IMatController.getShoppingCart().addItem(new ShoppingItem(product, productUnits));
          Integer totalSum = (int)IMatController.getShoppingCart().getTotal();
          imat.getVarukorgController().updateTotalCostBackend(totalSum);
          imat.getVarukorgController().initShoppingCart(imat.getVarukorgController().convertBackendToIMat());
        }
      }
    });
    
    kontouppgifterButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        changeToKontoView();
        event.consume();
      }
    });
    
    orderhistorikButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        changeToOrderHistorikView();
        event.consume();
      }
    });
    
    favoritvarorButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        changeToFavoriteView();
        event.consume();
      }
    });
    
    paymentForOrderButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        if (isRequiredFieldsEntered()) {
          if (IMatController.currentUser != null) {
            IMatController.createPaidOrder(imat.getVarukorgController().getIMatShoppingCart().getCart());
            imat.getCenterController().showOrderHistory();
            imat.getVarukorgController().setTotalCostLabel("0 kr");
            imat.getVarukorgController().initShoppingCart(imat.getVarukorgController().getIMatShoppingCart().getCart().getAllProducts());

          } else {
            Order orderCart = IMatController.getIMatBackend().placeOrder();
            imat.getCenterController().getOrders();
            imat.getVarukorgController().setTotalCostLabel("0 kr");
            imat.getVarukorgController().initShoppingCart(imat.getVarukorgController().convertBackendToIMat());
          }
        } else {
      // Inform user to enter required fields
      }
      }
    });
}  
  
  public boolean isRequiredFieldsEntered() {
    civicLabel1.setText("");
    firstNameLabel1.setText("");
    yearLabel1.setText("");
    monthLabel1.setText("");
    phoneLabel1.setText("");
    cvvLabel1.setText("");
    cardNumberLabel1.setText("");
    postalLabel1.setText("");
    streetLabel1.setText("");
    cityLabel1.setText("");
    lastNameLabel1.setText("");
    
    if (firstNameTextField1.getLength() > 0 && lastNameTextField1.getLength() > 0
        && civicTextField1.getLength() > 0 && postalTextField1.getLength() > 0
        && cityTextField1.getLength() > 0 && cardNumberTextField1.getLength() > 0
        && yearTextField1.getLength() > 0 && monthTextField1.getLength() > 0
        && (emailTextField1.getLength() > 0|| phoneTextField1.getLength() > 0)
        && cvvTextField1.getLength() > 0 && streetTextField1.getLength() > 0 ) {
      return true;
    } else {
      if (civicTextField1.getLength() == 0) {
        civicLabel1.setText("*");
      }
      if (firstNameTextField1.getLength() == 0) {
        firstNameLabel1.setText("*");
      }
      if (yearTextField1.getLength() == 0) {
        yearLabel1.setText("*");
      }
      if (monthTextField1.getLength() == 0) {
        monthLabel1.setText("*");
      }
      if (emailTextField1.getLength() == 0 && phoneTextField1.getLength() == 0) {
        phoneLabel1.setText("*");
      }
      if (cardNumberTextField1.getLength() == 0) {
        cardNumberLabel1.setText("*");
      }
      if (cvvTextField1.getLength() == 0) {
        cvvLabel1.setText("*");
      }
      if (lastNameTextField1.getLength() == 0) {
        lastNameLabel1.setText("*");
      }
      if (cityTextField1.getLength() == 0) {
        cityLabel1.setText("*");
      }
      if (streetTextField1.getLength() == 0) {
        streetLabel1.setText("*");
      }
      if (postalTextField1.getLength() == 0) {
        postalLabel1.setText("*");
      }
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
    textField12.setText("1");
    textField13.setText("1");
    textField14.setText("1");
    textField15.setText("1");
    textField16.setText("1");
    textField17.setText("1");
    textField18.setText("1");
    textField19.setText("1");
    textField20.setText("1");
    textField21.setText("1");
    textField22.setText("1");
    textField23.setText("1");
    textField24.setText("1");
    textField25.setText("1");
    textField26.setText("1");
    textField27.setText("1");
    textField28.setText("1");
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
   * Load stored information for users.
   */
  @SuppressWarnings("ConvertToTryWithResources")
  public void loadCustomerInformationPayment() {
    if (IMatController.currentUser == null) {
      firstNameTextField1.setText(IMatController.getIMatBackend().getCustomer().getFirstName());
      lastNameTextField1.setText(IMatController.getIMatBackend().getCustomer().getLastName());
      civicTextField1.setText(IMatController.getIMatBackend().getCustomer().getMobilePhoneNumber());
      phoneTextField1.setText(IMatController.getIMatBackend().getCustomer().getPhoneNumber());
      emailTextField1.setText(IMatController.getIMatBackend().getCustomer().getEmail());
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
              emailTextField1.setText(rs.getString("EMAIL"));
              phoneTextField1.setText(rs.getString("PHONE"));
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
    productPane12.setVisible(false);
    productPane13.setVisible(false);
    productPane14.setVisible(false);
    productPane15.setVisible(false);
    productPane16.setVisible(false);
    productPane17.setVisible(false);
    productPane18.setVisible(false);
    productPane19.setVisible(false);
    productPane20.setVisible(false);
    productPane21.setVisible(false);
    productPane22.setVisible(false);
    productPane23.setVisible(false);
    productPane24.setVisible(false);
    productPane25.setVisible(false);
    productPane26.setVisible(false);
    productPane27.setVisible(false);
    productPane28.setVisible(false);
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
    productPane12.setVisible(false);
    productPane13.setVisible(false);
    productPane14.setVisible(false);
    productPane15.setVisible(false);
    productPane16.setVisible(false);
    productPane17.setVisible(false);
    productPane18.setVisible(false);
    productPane19.setVisible(false);
    productPane20.setVisible(false);
    productPane21.setVisible(false);
    productPane22.setVisible(false);
    productPane23.setVisible(false);
    productPane24.setVisible(false);
    productPane25.setVisible(false);
    productPane26.setVisible(false);
    productPane27.setVisible(false);
    productPane28.setVisible(false);
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
    productPane12.setVisible(false);
    productPane13.setVisible(false);
    productPane14.setVisible(false);
    productPane15.setVisible(false);
    productPane16.setVisible(false);
    productPane17.setVisible(false);
    productPane18.setVisible(false);
    productPane19.setVisible(false);
    productPane20.setVisible(false);
    productPane21.setVisible(false);
    productPane22.setVisible(false);
    productPane23.setVisible(false);
    productPane24.setVisible(false);
    productPane25.setVisible(false);
    productPane26.setVisible(false);
    productPane27.setVisible(false);
    productPane28.setVisible(false);
    
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
    productPane12.setVisible(false);
    productPane13.setVisible(false);
    productPane14.setVisible(false);
    productPane15.setVisible(false);
    productPane16.setVisible(false);
    productPane17.setVisible(false);
    productPane18.setVisible(false);
    productPane19.setVisible(false);
    productPane20.setVisible(false);
    productPane21.setVisible(false);
    productPane22.setVisible(false);
    productPane23.setVisible(false);
    productPane24.setVisible(false);
    productPane25.setVisible(false);
    productPane26.setVisible(false);
    productPane27.setVisible(false);
    productPane28.setVisible(false);
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
    product = products.get(12);
    productLabel12.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage12, imageName);
    totalCost12.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(13);
    productLabel13.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage13, imageName);
    totalCost13.setText("Pris: " + (int)product.getPrice() + " kr");
    
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
    productPane12.setVisible(true);
    productPane13.setVisible(true);
    
    //hides unused product panes
    productPane14.setVisible(false);
    productPane15.setVisible(false);
    productPane16.setVisible(false);
    productPane17.setVisible(false);
    productPane18.setVisible(false);
    productPane19.setVisible(false);
    productPane20.setVisible(false);
    productPane21.setVisible(false);
    productPane22.setVisible(false);
    productPane23.setVisible(false);
    productPane24.setVisible(false);
    productPane25.setVisible(false);
    productPane26.setVisible(false);
    productPane27.setVisible(false);
    productPane28.setVisible(false);
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
    product = products.get(12);
    productLabel12.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage12, imageName);
    totalCost12.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(13);
    productLabel13.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage13, imageName);
    totalCost13.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(14);
    productLabel14.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage14, imageName);
    totalCost14.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(15);
    productLabel15.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage15, imageName);
    totalCost15.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(16);
    productLabel16.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage16, imageName);
    totalCost16.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(17);
    productLabel17.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage17, imageName);
    totalCost17.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(18);
    productLabel18.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage18, imageName);
    totalCost18.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(19);
    productLabel19.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage19, imageName);
    totalCost19.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(20);
    productLabel20.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage20, imageName);
    totalCost20.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(21);
    productLabel21.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage21, imageName);
    totalCost21.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(22);
    productLabel22.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage22, imageName);
    totalCost22.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(23);
    productLabel23.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage23, imageName);
    totalCost23.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(24);
    productLabel24.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage24, imageName);
    totalCost24.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(25);
    productLabel25.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage25, imageName);
    totalCost25.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(26);
    productLabel26.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage26, imageName);
    totalCost26.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(27);
    productLabel27.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage27, imageName);
    totalCost27.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(28);
    productLabel28.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage28, imageName);
    totalCost28.setText("Pris: " + (int)product.getPrice() + " kr");
    
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
    productPane12.setVisible(true);
    productPane13.setVisible(true);
    productPane14.setVisible(true);
    productPane15.setVisible(true);
    productPane16.setVisible(true);
    productPane17.setVisible(true);
    productPane18.setVisible(true);
    productPane19.setVisible(true);
    productPane20.setVisible(true);
    productPane21.setVisible(true);
    productPane22.setVisible(true);
    productPane23.setVisible(true);
    productPane24.setVisible(true);
    productPane25.setVisible(true);
    productPane26.setVisible(true);
    productPane27.setVisible(true);
    productPane28.setVisible(true);
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
    productPane12.setVisible(false);
    productPane13.setVisible(false);
    productPane14.setVisible(false);
    productPane15.setVisible(false);
    productPane16.setVisible(false);
    productPane17.setVisible(false);
    productPane18.setVisible(false);
    productPane19.setVisible(false);
    productPane20.setVisible(false);
    productPane21.setVisible(false);
    productPane22.setVisible(false);
    productPane23.setVisible(false);
    productPane24.setVisible(false);
    productPane25.setVisible(false);
    productPane26.setVisible(false);
    productPane27.setVisible(false);
    productPane28.setVisible(false);
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
    product = products.get(12);
    productLabel12.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage12, imageName);
    totalCost12.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(13);
    productLabel13.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage13, imageName);
    totalCost13.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(14);
    productLabel14.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage14, imageName);
    totalCost14.setText("Pris: " + (int)product.getPrice() + " kr");
    product = products.get(15);
    productLabel15.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
    imageName = product.getImageName();
    setImage(productImage15, imageName);
    totalCost15.setText("Pris: " + (int)product.getPrice() + " kr");
    
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
    productPane12.setVisible(true);
    productPane13.setVisible(true);
    productPane14.setVisible(true);
    productPane15.setVisible(true);
    
    //hides unused product panes
    productPane16.setVisible(false);
    productPane17.setVisible(false);
    productPane18.setVisible(false);
    productPane19.setVisible(false);
    productPane20.setVisible(false);
    productPane21.setVisible(false);
    productPane22.setVisible(false);
    productPane23.setVisible(false);
    productPane24.setVisible(false);
    productPane25.setVisible(false);
    productPane26.setVisible(false);
    productPane27.setVisible(false);
    productPane28.setVisible(false);
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
    productPane12.setVisible(false);
    productPane13.setVisible(false);
    productPane14.setVisible(false);
    productPane15.setVisible(false);
    productPane16.setVisible(false);
    productPane17.setVisible(false);
    productPane18.setVisible(false);
    productPane19.setVisible(false);
    productPane20.setVisible(false);
    productPane21.setVisible(false);
    productPane22.setVisible(false);
    productPane23.setVisible(false);
    productPane24.setVisible(false);
    productPane25.setVisible(false);
    productPane26.setVisible(false);
    productPane27.setVisible(false);
    productPane28.setVisible(false);
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
    productPane12.setVisible(false);
    productPane13.setVisible(false);
    productPane14.setVisible(false);
    productPane15.setVisible(false);
    productPane16.setVisible(false);
    productPane17.setVisible(false);
    productPane18.setVisible(false);
    productPane19.setVisible(false);
    productPane20.setVisible(false);
    productPane21.setVisible(false);
    productPane22.setVisible(false);
    productPane23.setVisible(false);
    productPane24.setVisible(false);
    productPane25.setVisible(false);
    productPane26.setVisible(false);
    productPane27.setVisible(false);
    productPane28.setVisible(false);
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
    productPane12.setVisible(false);
    productPane13.setVisible(false);
    productPane14.setVisible(false);
    productPane15.setVisible(false);
    productPane16.setVisible(false);
    productPane17.setVisible(false);
    productPane18.setVisible(false);
    productPane19.setVisible(false);
    productPane20.setVisible(false);
    productPane21.setVisible(false);
    productPane22.setVisible(false);
    productPane23.setVisible(false);
    productPane24.setVisible(false);
    productPane25.setVisible(false);
    productPane26.setVisible(false);
    productPane27.setVisible(false);
    productPane28.setVisible(false);
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
    
    //hides unused product panes
    productPane12.setVisible(false);
    productPane13.setVisible(false);
    productPane14.setVisible(false);
    productPane15.setVisible(false);
    productPane16.setVisible(false);
    productPane17.setVisible(false);
    productPane18.setVisible(false);
    productPane19.setVisible(false);
    productPane20.setVisible(false);
    productPane21.setVisible(false);
    productPane22.setVisible(false);
    productPane23.setVisible(false);
    productPane24.setVisible(false);
    productPane25.setVisible(false);
    productPane26.setVisible(false);
    productPane27.setVisible(false);
    productPane28.setVisible(false);
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
    productPane12.setVisible(false);
    productPane13.setVisible(false);
    productPane14.setVisible(false);
    productPane15.setVisible(false);
    productPane16.setVisible(false);
    productPane17.setVisible(false);
    productPane18.setVisible(false);
    productPane19.setVisible(false);
    productPane20.setVisible(false);
    productPane21.setVisible(false);
    productPane22.setVisible(false);
    productPane23.setVisible(false);
    productPane24.setVisible(false);
    productPane25.setVisible(false);
    productPane26.setVisible(false);
    productPane27.setVisible(false);
    productPane28.setVisible(false);
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
  
  public void changeToKontoView() {
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
  }
  
  public void changeToOrderHistorikView() {
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
  }
  
  public void changeToPaymentView() {
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
    favoritePane.setVisible(false);
    favoritePane1.setVisible(false);
    favoritePane2.setVisible(false);
    favoritePane3.setVisible(false);
    favoritePane4.setVisible(false);
    favoritePane5.setVisible(false);
    favoritePane6.setVisible(false);
    favoritePane7.setVisible(false);
    favoritePane8.setVisible(false);
    favoritePane9.setVisible(false);
    favoritePane10.setVisible(false);
    favoritePane11.setVisible(false);
    
    int size = varaListVyParent.getChildren().size();
    currentPane = "favoriteScrollPane";
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
    
    List<Product> favorites = IMatController.getIMatBackend().favorites();
    int nrOfFavorites = favorites.size();
    int keepCount = 0;
    for (int i = 0; i < nrOfFavorites; i++) {
      product = favorites.get(i);
      productLabel.setText(product.getName() + "  " + (int)product.getPrice() + " " + product.getUnit());
      imageName = product.getImageName();
      setImage(productImage, imageName);
      totalCost.setText("Pris: " + (int)product.getPrice() + " kr");
      keepCount = i;
      switch(keepCount) {
        case 0: favoritePane.setVisible(true);
                break;
        case 1: favoritePane1.setVisible(true);
                break;
        case 2: favoritePane2.setVisible(true);
                break;
        case 3: favoritePane3.setVisible(true);
                break;        
        case 4: favoritePane4.setVisible(true);
                break;
        case 5: favoritePane5.setVisible(true);
                break;
        case 6: favoritePane6.setVisible(true);
                break;
        case 7: favoritePane7.setVisible(true);
                break;    
        case 8: favoritePane8.setVisible(true);
                break;
        case 9: favoritePane9.setVisible(true);
                break;
        case 10: favoritePane10.setVisible(true);
                break;
        case 11: favoritePane11.setVisible(true);
                break;           
      }
          
    }
  }
  
  public void initCheckoutCart(List<IMatShoppingItem> cartProducts) {
    ObservableList<IMatShoppingItem> cartList = FXCollections.observableArrayList(cartProducts);
    checkOutCartListView.setItems(cartList);
    checkOutCartListView.setCellFactory(new Callback<ListView<IMatShoppingItem>, ListCell<IMatShoppingItem>>(){
      @Override
      public ListCell<IMatShoppingItem> call(ListView<IMatShoppingItem> p) {
        ListCell<IMatShoppingItem> cell = new ListCell<IMatShoppingItem>(){
          @Override
          protected void updateItem(IMatShoppingItem t, boolean bln) {
            super.updateItem(t, bln);
            if (t != null) {
              setText(t.getAmount() + " st  " + t.getProductName() + "   " + t.getSum() + " kr");
            }
          }
        };
      return cell;
      }
    });
  }

  public void changeToSearchView(String searchText) {
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
    }
}

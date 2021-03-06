/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imat.view;

import imat.IMat;
import imat.IMatController;
import imat.IMatProduct;
import imat.IMatShoppingCart;
import imat.IMatShoppingItem;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import se.chalmers.ait.dat215.project.Product;
import se.chalmers.ait.dat215.project.ShoppingItem;

/**
 *
 * @author group 11
 */
public class VarukorgController implements Initializable {
  
  // Reference to the main application
  private IMat imat;
  static int cellIndexV = 0;
  static int firstTimeDelete = 0;
  
  // New cart created each time the application starts
  private IMatShoppingCart imatCart = new IMatShoppingCart();
  @FXML
  private Button cartBuyButton;
  @FXML
  private Label totalCostLabel;
  @FXML
  private ListView<IMatShoppingItem> shoppingCartListView = new ListView();
  @FXML
  private Button resetShoppingCartButton;
  @FXML
  private Hyperlink changeCartLink;
  
  boolean added = false;
  @FXML
  private Label summaLabel;
  @FXML
  private Label shoppingCartLabel;
  
  VBox vbox1;
  
  /**
  * Is called by the main application to give a reference back to itself.
  * 
  * @param imat
  */
  public void setMainApp(IMat imat) {
    this.imat = imat;
  }
  
  /**
   * Retrieves a reference to the iMAT shopping cart.
   * 
   * @return  the reference to the iMAT shopping cart 
   */
  public IMatShoppingCart getIMatShoppingCart() {
    return imatCart;
  }
  
  public void setTotalCostLabel(String totalCost) {
    totalCostLabel.setText(totalCost);
  }
  
  public Button getCartBuyButton() {
    return cartBuyButton;
  }
  
  public void disableShoppingCartLabel(boolean disable) {
    shoppingCartLabel.setDisable(disable);
  }
  
  public ListView<IMatShoppingItem> getList() {
    return shoppingCartListView;
  }
  
  public Button getEmptyButton() {
    return resetShoppingCartButton;
  }
  
  public void setAdded() {
    added = true;
  }
  
  public Hyperlink getChangeLink() {
    return changeCartLink;
  }
  
  public VBox getPlaceHolder(){
    return vbox1;
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    //resetShoppingCartButton.setFocusTraversable(false);
    
    changeCartLink.setFocusTraversable(false);
    shoppingCartListView.setMaxHeight(500);
    vbox1 = new VBox();
    Label holder = new Label("           Varukorgen är tom");
    vbox1.getChildren().add(holder);
    Pane holderPane = new Pane();
    holderPane.setMinHeight(250);
    vbox1.getChildren().add(holderPane);
    shoppingCartListView.setPlaceholder(vbox1);
    
    if (!IMatController.getShoppingCart().getItems().isEmpty()) {
      shoppingCartListView.getPlaceholder().setVisible(false);
      resetShoppingCartButton.setDisable(false);
    } else {
      shoppingCartListView.getPlaceholder().setVisible(true);
      resetShoppingCartButton.setDisable(true);
      changeCartLink.setDisable(true);
    }
    
    changeCartLink.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        imat.getCenterController().setOnlyChangeCart();
        imat.getCenterController().changeToPagination();
        changeCartLink.setVisited(false);
      }
    });
    
    resetShoppingCartButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        resetShoppingCartButton.setDisable(true);
        resetShoppingCart();
        if (imat.getCenterController().getListVyPane().getChildren().contains(imat.getCenterController().lv)) {
          //imat.getCenterController().changeToCheckoutView();
        }
        shoppingCartListView.getPlaceholder().setVisible(true);
        changeCartLink.setDisable(true);
      }
    });
    
    cartBuyButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        imat.getCenterController().changeToPagination();
      }
    });

  }
  
  public void resetShoppingCart() {
    if (IMatController.currentUser != null) {
      getIMatShoppingCart().getCart().setNewEmptyCart();
      getIMatShoppingCart().getCart().setCost(0);
      List<IMatShoppingItem> list = imat.getVarukorgController().getIMatShoppingCart().getCart().getAllProducts();
      populateCheckoutCart(list);
      totalCostLabel.setText("0 kr");
    } else {
      IMatController.getShoppingCart().clear();
      Integer total = (int)IMatController.getShoppingCart().getTotal();
      imat.getCenterController().getTotalCostCartLabel().setText(total.toString() + " kr");
      updateTotalCostBackend(total);
      populateCheckoutCart(convertBackendToIMat());
    }
    
  }
  
  public void newPlaceHolder() {
        vbox1 = new VBox();
    Label holder = new Label("           Varukorgen är tom");
    vbox1.getChildren().add(holder);
    Pane holderPane = new Pane();
    holderPane.setMinHeight(250);
    vbox1.getChildren().add(holderPane);
    shoppingCartListView.setPlaceholder(vbox1);
  }
  
  /**
   * Updates the total sum of the shopping cart of a logged in user.
   */
  public void updateTotalCost() {
    totalCostLabel.setText(IMatShoppingCart.cart.getCost().toString()+ " kr");
  }
  
  /**
   * Update the total sum of the shopping cart of a user who is not logged in.
   * 
   * @param totalSum the new sum of the shopping cart
   */
  public void updateTotalCostBackend(Integer totalSum) {
    totalCostLabel.setText(totalSum.toString() + " kr");
  }
  
  /**
   * Used when displaying the products in the cart in the backend.
   * 
   * @return the backend cart converted to an iMAT cart
   */
  public List<IMatShoppingItem> convertBackendToIMat() {
    List<IMatShoppingItem> imatList = new ArrayList();
    List<ShoppingItem> backendList = IMatController.getShoppingCart().getItems();
    int backendCartSize = backendList.size();
    for (int i = 0; i < backendCartSize; i++) {
      Product product = backendList.get(i).getProduct();
      Integer amount = (int)backendList.get(i).getAmount();
      Integer sum = (int)backendList.get(i).getTotal();
      imatList.add(new IMatShoppingItem(product, amount, sum));
    }
    return imatList;
  }
  
    /**
   * Used when displaying the favorite products in the cart in the backend.
   * 
   * @return the backend favorite products converted to iMAT favorite products
   */
  public List<IMatProduct> convertBackendFavoritesToIMat() {
    List<IMatProduct> imatList = new ArrayList();
    List<Product> backendList = IMatController.getIMatBackend().favorites();
    int backendCartSize = backendList.size();
    for (int i = 0; i < backendCartSize; i++) {
      String productName = backendList.get(i).getName();
      int cost = (int)backendList.get(i).getPrice();
      imatList.add(new IMatProduct(productName, cost));
    }
    return imatList;
  }
  
  /**
   * Initialize the cart view containing the products in the shopping cart.
   * 
   * @param cartProducts  the cart containing the products to be displayed
   */
  public void initShoppingCart(List<IMatShoppingItem> cartProducts) {
    shoppingCartListView.setMouseTransparent(true);
    ObservableList<IMatShoppingItem> cartList = FXCollections.observableArrayList(cartProducts);
    shoppingCartListView.setItems(cartList);
    shoppingCartListView.setCellFactory(new Callback<ListView<IMatShoppingItem>, ListCell<IMatShoppingItem>>(){
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
  
      public class XCell extends ListCell<IMatShoppingItem> {
        HBox hbox = new HBox();
        Label label1 = new Label("(empty)");
        Label label2 = new Label("(empty)");
        Label label3 = new Label("(empty)");
        Pane pane1 = new Pane();
        Pane pane2 = new Pane();
        Button deleteButton = new Button();
        String lastItem;
        int index = 0;

        public XCell() {
            super();
            index = cellIndexV;
            cellIndexV++;
            hbox.setMaxHeight(5);
            deleteButton.setManaged(true);
            deleteButton.setCenterShape(false);
            deleteButton.setMinSize(USE_PREF_SIZE, USE_PREF_SIZE);
            deleteButton.setPrefSize(15, 15);
            deleteButton.setMaxSize(USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
            deleteButton.getStyleClass().add("CancelButton");
            pane1.setPrefWidth(5);
            pane2.setPrefWidth(5);
            hbox.getChildren().addAll(deleteButton, label1, label2, label3);
            deleteButton.setOnAction(new EventHandler<ActionEvent>() {
              @Override
              public void handle(ActionEvent event) {
                if (firstTimeDelete == 0 && !added) {
                    shoppingCartListView.getSelectionModel().selectIndices(index-1);
                    firstTimeDelete++;
                } else {
                    shoppingCartListView.getSelectionModel().selectIndices(index);
                }
                IMatShoppingItem item = shoppingCartListView.getSelectionModel().getSelectedItem();
                if (IMatController.currentUser != null) {
                  List<IMatShoppingItem> shopI = new ArrayList();
                  shopI.addAll(getIMatShoppingCart().getCart().getAllProducts());
                  int size = shopI.size();
                  int totalSum = getIMatShoppingCart().getCart().getCost();
                  for (int i = 0; i < shopI.size(); i++) {
                    if (shopI.get(i).getProduct().getName().equals(item.getProductName())) {
                      int sumToRemove = shopI.get(i).getSum();
                      shopI.remove(i);
                      getIMatShoppingCart().getCart().removeCost(sumToRemove);
                      getIMatShoppingCart().getCart().setNewEmptyCart();
                    }
                  }
                  for (int i = 0; i < size-1; i++) {
                    getIMatShoppingCart().getCart().setShoppingItem(shopI.get(i), i);
                  }
                  Integer total = getIMatShoppingCart().getCart().getCost();
                  totalCostLabel.setText(total.toString()+ " kr");
                  imat.getCenterController().getTotalCostCartLabel().setText(total.toString() + " kr");
                  populateCheckoutCart(getIMatShoppingCart().getCart().getAllProducts());
                  if (imat.getCenterController().getTotalCostCartLabel().getText().equals("0 kr")) {
                      resetShoppingCartButton.setDisable(true);
                      shoppingCartListView.getPlaceholder().setVisible(true);
                      changeCartLink.setDisable(true);
                  }
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
                    if (imat.getCenterController().getListVyPane().getChildren().contains(imat.getCenterController().lv)) {
                      imat.getCenterController().changeToPagination();
                    }

                    Integer total = (int)IMatController.getShoppingCart().getTotal();
                    imat.getCenterController().getTotalCostCartLabel().setText(total.toString() + " kr");
                    updateTotalCostBackend(total);
                    populateCheckoutCart(convertBackendToIMat());
                    if (imat.getCenterController().getTotalCostCartLabel().getText().equals("0 kr")) {
                      resetShoppingCartButton.setDisable(true);
                      shoppingCartListView.getPlaceholder().setVisible(true);
                      changeCartLink.setDisable(true);
                    }
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
              label1.setText(productName !=null ? " "+productName : "<null>");
              label2.setText(item.getProductName() !=null ? "  "+productUnits.toString()+" st  " : "<null>");
              label3.setText(item.getProductName() !=null ? " "+item.getSum().toString()+" kr" : "<null>");
              setGraphic(hbox);
            }
        }
    }
      
      public void setFirstTimeDelete(int value) {
        firstTimeDelete = value;
      }
  
  public void populateCheckoutCart(List<IMatShoppingItem> cartProducts) {
      ObservableList<IMatShoppingItem> cartList = FXCollections.observableArrayList(cartProducts);
      shoppingCartListView.setItems(cartList);
      //shoppingCartListView = imat.getCenterController().getCheckoutList();
      shoppingCartListView.setCellFactory(new Callback<ListView<IMatShoppingItem>, ListCell<IMatShoppingItem>>() {
        @Override
        public ListCell<IMatShoppingItem> call(ListView<IMatShoppingItem> param) {
            XCell cell = new XCell();
            return cell;
        }
    });
    cellIndexV = 0;
    if (cartList.size() == 0) {
      cartBuyButton.setDisable(true);
    } else {
      cartBuyButton.setDisable(false);
    }
  }
  
  public void setSummaLabel(String newText) {
    summaLabel.setText(newText);
  }
  
  public Label getSummaLabel() {
    return summaLabel;
  }
  
  public Button getResetButton(){
      return resetShoppingCartButton;
  }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imat.view;

import imat.IMat;
import imat.IMatController;
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
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import se.chalmers.ait.dat215.project.Order;
import se.chalmers.ait.dat215.project.Product;
import se.chalmers.ait.dat215.project.ShoppingItem;

/**
 *
 * @author group 11
 */
public class VarukorgController implements Initializable {
  
  // Reference to the main application
  private IMat imat;
  
  // New cart created each time the application starts
  private IMatShoppingCart imatCart = new IMatShoppingCart();
  @FXML
  private Button cartBuyButton;
  @FXML
  private Label totalCostLabel;
  @FXML
  private ListView<IMatShoppingItem> shoppingCartListView = new ListView();
  
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

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    
    cartBuyButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        if (IMatController.currentUser != null) {
          IMatController.createPaidOrder(imatCart.getCart());
          imat.getCenterController().showOrderHistory();
          totalCostLabel.setText("0 kr");
          initShoppingCart(imatCart.getCart().getAllProducts());
          
        } else {
          Order orderCart = IMatController.getIMatBackend().placeOrder();
          imat.getCenterController().getOrders();
          totalCostLabel.setText("0 kr");
          initShoppingCart(convertBackendToIMat());
        }
      }
    });

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
   * Initialize the cart view containing the products in the shopping cart.
   * 
   * @param cartProducts  the cart containing the products to be displayed
   */
  public void initShoppingCart(List<IMatShoppingItem> cartProducts) {
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
              setText(t.getProductName() + "  antal: " + t.getAmount() + "  pris: " + t.getSum());
            }
          }
        };
      return cell;
      }
    });
  }
        
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imat;

/**
 * This class represents the IMat shopping cart where products are placed
 * until the user performs a buy, after which the shopping cart is stored
 * as an history order in the database.
 * 
 * @author group 11
 */
public class IMatShoppingCart {
  
  // IMatOrder acts as a shopping cart and is stored in database after payment
  public static IMatOrder cart;
  
  public IMatShoppingCart() {
    cart = new IMatOrder();
    cart.setOrderNumber(setOrderID());
  }
  
  public IMatOrder getCart() {
    return cart;
  }
  
  /**
   * A new IMatOrder is created after each payment (represents an empty cart)
   */
  public static void newCart() {
    cart = new IMatOrder();
    cart.setOrderNumber(setOrderID());
  }
  
  /**
   * Create a unique id for the order.
   * 
   * @return  the unique id 
   */
  private static int setOrderID() {
    return IMatController.getNumberOfRecords("orders");
  }
  
  public void setCart(IMatOrder cart) {
    this.cart = cart;
  }
  
}

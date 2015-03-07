/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import se.chalmers.ait.dat215.project.Product;

/**
 * This class represents an IMat order. It is used for decorating Orders, and
 * also to create orders stored in the database for use in the History table.
 * 
 * @author group 11
 */
public class IMatOrder {
  
  private LocalDate date;
  private Integer cost = 0;
  private Integer orderNumber;
  private Product product1, product2, product3, product4, product5, product6,
      product7, product8, product9, product10, product11, product12,
      product13, product14;
  private Integer units1, units2, units3, units4, units5, units6, units7, units8,
      units9, units10, units11, units12, units13, units14;
  
  List<IMatShoppingItem> shoppingItems;
  
  
  public IMatOrder(Integer orderNumber, Integer cost, LocalDate date) {
    this.date = date;
    this.cost = cost;
    this.orderNumber = orderNumber;
    shoppingItems = new ArrayList();
  }
  
  public IMatOrder() {
    shoppingItems = new ArrayList();
  }
  
  public void addShoppingItem(Product product, Integer amount, Integer sum) {
    shoppingItems.add(new IMatShoppingItem(product, amount, sum));
    int newCost = this.cost.intValue() + sum;
    this.cost = newCost;
  }
  
  public void addShoppingItem(IMatShoppingItem item) {
    shoppingItems.add(item);
  }
  
  public List<IMatShoppingItem> getAllProducts() {
    return shoppingItems;
  }
  
  public void setShoppingItemList(List<IMatShoppingItem> shoppingItems) {
    this.shoppingItems = shoppingItems;
  }
  
  public Integer getOrderNumber() {
    return orderNumber;
  }
  
  public void setShoppingItem(IMatShoppingItem item, int index) {
    shoppingItems.add(index, item);
  }
  
  public Integer getCost() {
    return cost;
  }
  
  public LocalDate getDate() {
    return date;
  }
  
  public void setNewEmptyCart() {
    shoppingItems = new ArrayList();
  }

  /**
   * @param date the date to set
   */
  public void setDate(LocalDate date) {
    this.date = date;
  }

  /**
   * @param cost the cost to set
   */
  public void setCost(Integer cost) {
    this.cost = cost;
  }
  
  /**
   * Adds a sum to the total cost of the shopping cart.
   * 
   * @param cost the sum to add to the total cost
   */
  public void addCost(int cost) {
    int newCost = this.cost + cost;
    this.cost = newCost;
  }

  /**
   * @param orderNumber the orderNumber to set
   */
  public void setOrderNumber(Integer orderNumber) {
    this.orderNumber = orderNumber;
  }

  /**
   * @return the product1
   */
  public Product getProduct1() {
    return product1;
  }

  /**
   * @param product1 the product1 to set
   */
  public void setProduct1(Product product1) {
    this.product1 = product1;
  }

  /**
   * @return the product2
   */
  public Product getProduct2() {
    return product2;
  }

  /**
   * @param product2 the product2 to set
   */
  public void setProduct2(Product product2) {
    this.product2 = product2;
  }

  /**
   * @return the product3
   */
  public Product getProduct3() {
    return product3;
  }

  /**
   * @param product3 the product3 to set
   */
  public void setProduct3(Product product3) {
    this.product3 = product3;
  }

  /**
   * @return the product4
   */
  public Product getProduct4() {
    return product4;
  }

  /**
   * @param product4 the product4 to set
   */
  public void setProduct4(Product product4) {
    this.product4 = product4;
  }

  /**
   * @return the product5
   */
  public Product getProduct5() {
    return product5;
  }

  /**
   * @param product5 the product5 to set
   */
  public void setProduct5(Product product5) {
    this.product5 = product5;
  }

  /**
   * @return the product6
   */
  public Product getProduct6() {
    return product6;
  }

  /**
   * @param product6 the product6 to set
   */
  public void setProduct6(Product product6) {
    this.product6 = product6;
  }

  /**
   * @return the product7
   */
  public Product getProduct7() {
    return product7;
  }

  /**
   * @param product7 the product7 to set
   */
  public void setProduct7(Product product7) {
    this.product7 = product7;
  }

  /**
   * @return the product8
   */
  public Product getProduct8() {
    return product8;
  }

  /**
   * @param product8 the product8 to set
   */
  public void setProduct8(Product product8) {
    this.product8 = product8;
  }

  /**
   * @return the product9
   */
  public Product getProduct9() {
    return product9;
  }

  /**
   * @param product9 the product9 to set
   */
  public void setProduct9(Product product9) {
    this.product9 = product9;
  }

  /**
   * @return the product10
   */
  public Product getProduct10() {
    return product10;
  }

  /**
   * @param product10 the product10 to set
   */
  public void setProduct10(Product product10) {
    this.product10 = product10;
  }

  /**
   * @return the product11
   */
  public Product getProduct11() {
    return product11;
  }

  /**
   * @param product11 the product11 to set
   */
  public void setProduct11(Product product11) {
    this.product11 = product11;
  }

  /**
   * @return the product12
   */
  public Product getProduct12() {
    return product12;
  }

  /**
   * @param product12 the product12 to set
   */
  public void setProduct12(Product product12) {
    this.product12 = product12;
  }

  /**
   * @return the product13
   */
  public Product getProduct13() {
    return product13;
  }

  /**
   * @param product13 the product13 to set
   */
  public void setProduct13(Product product13) {
    this.product13 = product13;
  }

  /**
   * @return the product14
   */
  public Product getProduct14() {
    return product14;
  }

  /**
   * @param product14 the product14 to set
   */
  public void setProduct14(Product product14) {
    this.product14 = product14;
  }

  /**
   * @return the units1
   */
  public Integer getUnits1() {
    return units1;
  }

  /**
   * @param units1 the units1 to set
   */
  public void setUnits1(Integer units1) {
    this.units1 = units1;
  }

  /**
   * @return the units2
   */
  public Integer getUnits2() {
    return units2;
  }

  /**
   * @param units2 the units2 to set
   */
  public void setUnits2(Integer units2) {
    this.units2 = units2;
  }

  /**
   * @return the units3
   */
  public Integer getUnits3() {
    return units3;
  }

  /**
   * @param units3 the units3 to set
   */
  public void setUnits3(Integer units3) {
    this.units3 = units3;
  }

  /**
   * @return the units4
   */
  public Integer getUnits4() {
    return units4;
  }

  /**
   * @param units4 the units4 to set
   */
  public void setUnits4(Integer units4) {
    this.units4 = units4;
  }

  /**
   * @return the units5
   */
  public Integer getUnits5() {
    return units5;
  }

  /**
   * @param units5 the units5 to set
   */
  public void setUnits5(Integer units5) {
    this.units5 = units5;
  }

  /**
   * @return the units6
   */
  public Integer getUnits6() {
    return units6;
  }

  /**
   * @param units6 the units6 to set
   */
  public void setUnits6(Integer units6) {
    this.units6 = units6;
  }

  /**
   * @return the units7
   */
  public Integer getUnits7() {
    return units7;
  }

  /**
   * @param units7 the units7 to set
   */
  public void setUnits7(Integer units7) {
    this.units7 = units7;
  }

  /**
   * @return the units8
   */
  public Integer getUnits8() {
    return units8;
  }

  /**
   * @param units8 the units8 to set
   */
  public void setUnits8(Integer units8) {
    this.units8 = units8;
  }

  /**
   * @return the units9
   */
  public Integer getUnits9() {
    return units9;
  }

  /**
   * @param units9 the units9 to set
   */
  public void setUnits9(Integer units9) {
    this.units9 = units9;
  }

  /**
   * @return the units10
   */
  public Integer getUnits10() {
    return units10;
  }

  /**
   * @param units10 the units10 to set
   */
  public void setUnits10(Integer units10) {
    this.units10 = units10;
  }

  /**
   * @return the units11
   */
  public Integer getUnits11() {
    return units11;
  }

  /**
   * @param units11 the units11 to set
   */
  public void setUnits11(Integer units11) {
    this.units11 = units11;
  }

  /**
   * @return the units12
   */
  public Integer getUnits12() {
    return units12;
  }

  /**
   * @param units12 the units12 to set
   */
  public void setUnits12(Integer units12) {
    this.units12 = units12;
  }

  /**
   * @return the units13
   */
  public Integer getUnits13() {
    return units13;
  }

  /**
   * @param units13 the units13 to set
   */
  public void setUnits13(Integer units13) {
    this.units13 = units13;
  }

  /**
   * @return the units14
   */
  public Integer getUnits14() {
    return units14;
  }

  /**
   * @param units14 the units14 to set
   */
  public void setUnits14(Integer units14) {
    this.units14 = units14;
  }
  
  
  
}

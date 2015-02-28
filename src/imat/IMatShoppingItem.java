/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imat;

import se.chalmers.ait.dat215.project.Product;

/**
 *
 * @author group 11
 */
public class IMatShoppingItem {
  
  private String productName;
  private Integer amount;
  private Integer sum;
  
  public IMatShoppingItem(Product productName, Integer amount, Integer sum) {
    this.productName = IMatController.getIMatBackend().getProduct(productName.getProductId()).getName();
    this.amount = amount;
    this.sum = sum;
  }

  /**
   * @return the productName
   */
  public String getProductName() {
    return productName;
  }

  /**
   * @param productName the productName to set
   */
  public void setProductName(String productName) {
    this.productName = productName;
  }

  /**
   * @return the amount
   */
  public Integer getAmount() {
    return amount;
  }

  /**
   * @param amount the amount to set
   */
  public void setAmount(Integer amount) {
    this.amount = amount;
  }

  /**
   * @return the sum
   */
  public Integer getSum() {
    return sum;
  }

  /**
   * @param sum the sum to set
   */
  public void setSum(Integer sum) {
    this.sum = sum;
  }
  
}

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
  private Product product;
  private Integer amount;
  private Integer sum;
  
  public IMatShoppingItem(Product product, Integer amount, Integer sum) {
    this.productName = IMatController.getIMatBackend().getProduct(product.getProductId()).getName();
    this.amount = amount;
    this.product = product;
    this.sum = sum;
  }

  /**
   * @return the productName
   */
  public String getProductName() {
    return productName;
  }
  
  public Product getProduct() {
    return product;
  }
  
  public void setProduct(Product product) {
    this.product = product;
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
  
  public void addToSum(int add) {
    this.sum += add;
  }
  
}

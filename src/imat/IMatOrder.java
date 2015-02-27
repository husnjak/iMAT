/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imat;

import java.util.Date;

/**
 * This class represents an IMat order.
 * 
 * @author group 11
 */
public class IMatOrder {
  
  private Date date;
  private Integer cost;
  private Integer orderNumber;
  
  public IMatOrder(Integer orderNumber, Integer cost, Date date) {
    this.date = date;
    this.cost = cost;
    this.orderNumber = orderNumber;
  }
  
  public Integer getOrderNumber() {
    return orderNumber;
  }
  
  public Integer getCost() {
    return cost;
  }
  
  public Date getDate() {
    return date;
  }
  
  
  
}

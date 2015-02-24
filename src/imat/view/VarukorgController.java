/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imat.view;

import imat.IMat;

/**
 *
 * @author group 11
 */
public class VarukorgController {
  
  // Reference to the main application
  private IMat imat;
  
  /**
  * Is called by the main application to give a reference back to itself.
  * 
  * @param mainApp
  */
  public void setMainApp(IMat imat) {
    this.imat = imat;
  } 
  
}

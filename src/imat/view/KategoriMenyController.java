/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imat.view;

import imat.IMat;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;

/**
 *
 * @author group 11
 */
public class KategoriMenyController implements Initializable {
  @FXML
  private Hyperlink riceLink;
  @FXML
  private Hyperlink pastaLink;
  @FXML
  private Hyperlink breadLink;
  @FXML
  private Hyperlink drinkLink;
  @FXML
  private Hyperlink fruitLink;
  @FXML
  private Hyperlink fishLink;
  @FXML
  private Hyperlink vegetablesLink;
  @FXML
  private Hyperlink spiceLink;
  @FXML
  private Hyperlink meatLink;
  @FXML
  private Hyperlink dairiesLink;
  @FXML
  private Hyperlink nutsLink;
  @FXML
  private Hyperlink potatoLink;
  @FXML
  private Hyperlink sweetsLink;
  
  private IMat imat;
  
  
  /**
   * Request that the Handla view shows category bread.
   */
  @FXML
  public void changeCategoryBread() {
    //imat.getCenterController().changeHandlaCategory("bread");
  }
  
  /**
   * Request that the Handla view shows category drink.
   */
  @FXML
  public void changeCategoryDrink() {
    //imat.getCenterController().changeHandlaCategory("drink");
  }
  
  /**
   * Request that the Handla view shows category fruit.
   */
  @FXML
  public void changeCategoryFruit() {
    //imat.getCenterController().changeHandlaCategory("fruit");
  }
  
  /**
   * Request that the Handla view shows category fish.
   */
  @FXML
  public void changeCategoryFish() {
    //imat.getCenterController().changeHandlaCategory("fish");
  }
  
  /**
   * Request that the Handla view shows category vegetables.
   */
  @FXML
  public void changeCategoryVegetables() {
    //imat.getCenterController().changeHandlaCategory("vegetables");
  }
  
  /**
   * Request that the Handla view shows category spices.
   */
  @FXML
  public void changeCategorySpices() {
    //imat.getCenterController().changeHandlaCategory("spices");
  }
  
  /**
   * Request that the Handla view shows category meat.
   */
  @FXML
  public void changeCategoryMeat() {
    imat.getCenterController().changeToMeatView();
  }
  
  /**
   * Request that the Handla view shows category dairies.
   */
  @FXML
  public void changeCategoryDairies() {
    //imat.getCenterController().changeHandlaCategory("dairies");
  }
  
  /**
   * Request that the Handla view shows category nuts.
   */
  @FXML
  public void changeCategoryNuts() {
    //imat.getCenterController().changeHandlaCategory("nuts");
  }
  
  /**
   * Request that the Handla view shows category pasta.
   */
  @FXML
  public void changeCategoryPasta() {
    //imat.getCenterController().changeHandlaCategory("pasta");
  }
  
  /**
   * Request that the Handla view shows category potatoes.
   */
  @FXML
  public void changeCategoryPotatoes() {
    //imat.getCenterController().changeHandlaCategory("potatoes");
  }
  
  /**
   * Request that the Handla view shows category rice.
   */
  @FXML
  public void changeCategoryRice() {
    imat.getCenterController().changeToRiceView();
  }
  
  /**
   * Request that the Handla view shows category sweets.
   */
  @FXML
  public void changeCategorySweets() {
    //imat.getCenterController().changeHandlaCategory("sweets");
  }
    
  
  /**
   * Is called by the main application to give a reference back to itself.
   * 
   * @param imat
   */
  public void setMainApp(IMat imat) {
    this.imat = imat;
  } 

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    
  }
  
}

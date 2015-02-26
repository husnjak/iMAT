/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imat.view;

import imat.IMat;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Tab;
import javafx.scene.input.MouseEvent;

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
  
  // Used for selecting the Handla tab when clicking on categories
  Tab handla;
  
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    riceLink.setFocusTraversable(false);
    meatLink.setFocusTraversable(false);
    breadLink.setFocusTraversable(false);
    dairiesLink.setFocusTraversable(false);
    potatoLink.setFocusTraversable(false);
    sweetsLink.setFocusTraversable(false);
    nutsLink.setFocusTraversable(false);
    drinkLink.setFocusTraversable(false);
    fishLink.setFocusTraversable(false);
    spiceLink.setFocusTraversable(false);
    vegetablesLink.setFocusTraversable(false);
    pastaLink.setFocusTraversable(false);
    fruitLink.setFocusTraversable(false);
    
    riceLink.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        imat.getCenterController().changeToRiceView();
        riceLink.setVisited(false);
        handla = imat.getCenterController().getHandlaFlik();
        imat.getCenterController().getTabPane().getSelectionModel().select(handla);
        event.consume();
      }
    });
    
    meatLink.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        imat.getCenterController().changeToMeatView();
        meatLink.setVisited(false);
        handla = imat.getCenterController().getHandlaFlik();
        imat.getCenterController().getTabPane().getSelectionModel().select(handla);
        event.consume();
      }
    });
    
    pastaLink.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        //imat.getCenterController().changeToPastaView();
        pastaLink.setVisited(false);
        handla = imat.getCenterController().getHandlaFlik();
        imat.getCenterController().getTabPane().getSelectionModel().select(handla);
        event.consume();
      }
    });
    
    breadLink.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        //imat.getCenterController().changeToBreadView();
        breadLink.setVisited(false);
        handla = imat.getCenterController().getHandlaFlik();
        imat.getCenterController().getTabPane().getSelectionModel().select(handla);
        event.consume();
      }
    });
    
    drinkLink.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        //imat.getCenterController().changeToDrinkView();
        drinkLink.setVisited(false);
        handla = imat.getCenterController().getHandlaFlik();
        imat.getCenterController().getTabPane().getSelectionModel().select(handla);
        event.consume();
      }
    });
    
    fruitLink.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        //imat.getCenterController().changeToFruitView();
        fruitLink.setVisited(false);
        handla = imat.getCenterController().getHandlaFlik();
        imat.getCenterController().getTabPane().getSelectionModel().select(handla);
        event.consume();
      }
    });
    
    fishLink.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        //imat.getCenterController().changeToFishView();
        fishLink.setVisited(false);
        handla = imat.getCenterController().getHandlaFlik();
        imat.getCenterController().getTabPane().getSelectionModel().select(handla);
        event.consume();
      }
    });
    
    vegetablesLink.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        //imat.getCenterController().changeToVegetablesView();
        vegetablesLink.setVisited(false);
        handla = imat.getCenterController().getHandlaFlik();
        imat.getCenterController().getTabPane().getSelectionModel().select(handla);
        event.consume();
      }
    });
    
    spiceLink.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        //imat.getCenterController().changeToSpiceView();
        spiceLink.setVisited(false);
        handla = imat.getCenterController().getHandlaFlik();
        imat.getCenterController().getTabPane().getSelectionModel().select(handla);
        event.consume();
      }
    });
    
    dairiesLink.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        //imat.getCenterController().changeToDairiesView();
        dairiesLink.setVisited(false);
        handla = imat.getCenterController().getHandlaFlik();
        imat.getCenterController().getTabPane().getSelectionModel().select(handla);
        event.consume();
      }
    });
    
    nutsLink.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        //imat.getCenterController().changeToNutsView();
        nutsLink.setVisited(false);
        handla = imat.getCenterController().getHandlaFlik();
        imat.getCenterController().getTabPane().getSelectionModel().select(handla);
        event.consume();
      }
    });
    
    potatoLink.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        //imat.getCenterController().changeToPotatoView();
        potatoLink.setVisited(false);
        handla = imat.getCenterController().getHandlaFlik();
        imat.getCenterController().getTabPane().getSelectionModel().select(handla);
        event.consume();
      }
    });
    
    sweetsLink.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        //imat.getCenterController().changeToSweetsView();
        sweetsLink.setVisited(false);
        handla = imat.getCenterController().getHandlaFlik();
        imat.getCenterController().getTabPane().getSelectionModel().select(handla);
        event.consume();
      }
    });
    
  }
  
  /**
   * Is called by the main application to give a reference back to itself.
   * 
   * @param imat
   */
  public void setMainApp(IMat imat) {
    this.imat = imat;
  } 
  
}

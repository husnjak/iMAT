/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imat;

import java.util.ArrayList;
import java.util.List;
import se.chalmers.ait.dat215.project.Product;
import se.chalmers.ait.dat215.project.ProductCategory;

/**
 * This class represents the store (the storage of products). Since
 * we use our own categories (instead of all the ones in the given
 * backend) we gather the backend products into our own categories
 * in this class. 
 * 
 * @author grupp 11
 */
public class IMatProducts {
  
  // Contain products of given categories
  private List<Product> productsBread;
  private List<Product> productsDrink;
  private List<Product> productsFruit;
  private List<Product> productsFish;
  private List<Product> productsDairies;
  private List<Product> productsMeat;
  private List<Product> productsPod;
  private List<Product> productsNuts;
  private List<Product> productsPasta;
  private List<Product> productsPotatoes;
  private List<Product> productsRice;
  private List<Product> productsSweets;
  private List<Product> productsSpices;
  private List<Product> productsVegetables;
  
  
  /**
   * Initialize the products of our store.
   */
  public IMatProducts() {
    initializeProducts();
  }
  
  /**
   * Retrieves the list of bread products.
   * 
   * @return bread product list
   */
  public List<Product> getBreadList() {
    return productsBread;
  }
  
  /**
   * Retrieves the list of drink products.
   * 
   * @return drink product list
   */
  public List<Product> getDrinkList() {
    return productsDrink;
  }
  
  /**
   * Retrieves the list of fish products.
   * 
   * @return fish product list
   */
  public List<Product> getFishList() {
    return productsFish;
  }
  
  /**
   * Retrieves the list of meat products.
   * 
   * @return meat product list
   */
  public List<Product> getMeatList() {
    return productsMeat;
  }
  
  /**
   * Retrieves the list of pod products.
   * 
   * @return pod product list
   */
  public List<Product> getPodList() {
    return productsPod;
  }
  
  /**
   * Retrieves the list of sweets products.
   * 
   * @return sweets product list
   */
  public List<Product> getSweetsList() {
    return productsSweets;
  }
  
  /**
   * Retrieves the list of potato products.
   * 
   * @return potato product list
   */
  public List<Product> getPotatoList() {
    return productsPotatoes;
  }
  
  /**
   * Retrieves the list of rice products.
   * 
   * @return rice product list
   */
  public List<Product> getRiceList() {
    return productsRice;
  }
  
  /**
   * Retrieves the list of spice products.
   * 
   * @return spice product list
   */
  public List<Product> getSpiceList() {
    return productsSpices;
  }
  
  /**
   * Retrieves the list of vegetable products.
   * 
   * @return vegetable product list
   */
  public List<Product> getVegetableList() {
    return productsVegetables;
  }
  
  /**
   * Retrieves the list of fruit products.
   * 
   * @return fruit product list
   */
  public List<Product> getFruitList() {
    return productsFruit;
  }
  
  /**
   * Retrieves the list of dairy products.
   * 
   * @return dairy product list
   */
  public List<Product> getDairieList() {
    return productsDairies;
  }
  
  /**
   * Retrieves the list of nut products.
   * 
   * @return nut product list
   */
  public List<Product> getNutsList() {
    return productsNuts;
  }
  
  /**
   * Retrieves the list of pasta products.
   * 
   * @return pasta product list
   */
  public List<Product> getPastaList() {
    return productsPasta;
  }

    
  
  /**
   * Puts the products in the categories where we want them.
   */
  private void initializeProducts() {
    
    // Adds three kinds of flour to category BREAD
    productsBread = IMatController.imatBackend.getProducts(ProductCategory.BREAD);
    productsBread.add(IMatController.imatBackend.getProducts(ProductCategory.FLOUR_SUGAR_SALT).get(1));
    productsBread.add(IMatController.imatBackend.getProducts(ProductCategory.FLOUR_SUGAR_SALT).get(2));
    productsBread.add(IMatController.imatBackend.getProducts(ProductCategory.FLOUR_SUGAR_SALT).get(5));
    
    // Use FRUIT category to represent the five backend fruit categories
    productsFruit = IMatController.imatBackend.getProducts(ProductCategory.FRUIT);
    productsFruit.addAll(IMatController.imatBackend.getProducts(ProductCategory.BERRY));
    productsFruit.addAll(IMatController.imatBackend.getProducts(ProductCategory.CITRUS_FRUIT));
    productsFruit.addAll(IMatController.imatBackend.getProducts(ProductCategory.EXOTIC_FRUIT));
    productsFruit.addAll(IMatController.imatBackend.getProducts(ProductCategory.MELONS));
    
    // Use DRINKS to represent all kinds of drinks (cold and hot)
    productsDrink = IMatController.imatBackend.getProducts(ProductCategory.COLD_DRINKS);
    productsDrink.addAll(IMatController.imatBackend.getProducts(ProductCategory.HOT_DRINKS));
    
    productsDairies = IMatController.imatBackend.getProducts(ProductCategory.DAIRIES);
    
    productsFish = IMatController.imatBackend.getProducts(ProductCategory.FISH);
    
    productsMeat = IMatController.imatBackend.getProducts(ProductCategory.MEAT);
    
    productsNuts = IMatController.imatBackend.getProducts(ProductCategory.NUTS_AND_SEEDS);
    
    productsPasta = IMatController.imatBackend.getProducts(ProductCategory.PASTA);
    
    // Adds two kinds of sugar to category SWEETS
    productsSweets = IMatController.imatBackend.getProducts(ProductCategory.SWEET);
    productsSweets.add(IMatController.imatBackend.getProducts(ProductCategory.FLOUR_SUGAR_SALT).get(0));
    productsSweets.add(IMatController.imatBackend.getProducts(ProductCategory.FLOUR_SUGAR_SALT).get(4));
    
    // Category POTATOES contain both root vegetables and potatoes
    productsPotatoes = IMatController.imatBackend.getProducts(ProductCategory.ROOT_VEGETABLE);
    for (int i = 3; i < 7; i++) {
      productsPotatoes.add(IMatController.imatBackend.getProducts(ProductCategory.POTATO_RICE).get(i));
    }
    
    // Both cabbage and vegetable fruit is considered as VEGETABLE_FRUIT
    productsVegetables = IMatController.imatBackend.getProducts(ProductCategory.CABBAGE);
    productsVegetables.addAll(IMatController.imatBackend.getProducts(ProductCategory.VEGETABLE_FRUIT));
    
    // Rice is categorized as RICE
    productsRice = new ArrayList();
    productsRice.add(IMatController.imatBackend.getProducts(ProductCategory.POTATO_RICE).get(0));
    productsRice.add(IMatController.imatBackend.getProducts(ProductCategory.POTATO_RICE).get(1));
    productsRice.add(IMatController.imatBackend.getProducts(ProductCategory.POTATO_RICE).get(2));
    productsRice.add(IMatController.imatBackend.getProducts(ProductCategory.POTATO_RICE).get(7));
    
    productsPod = IMatController.imatBackend.getProducts(ProductCategory.POD);
    
    // Adds Salt as a spice to category SPICES
    productsSpices = IMatController.imatBackend.getProducts(ProductCategory.HERB);
    productsSpices.add(IMatController.imatBackend.getProducts(ProductCategory.FLOUR_SUGAR_SALT).get(3));
    
  }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imat;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import se.chalmers.ait.dat215.project.Product;
import se.chalmers.ait.dat215.project.ProductCategory;

/**
 * This class represents everything related to the products. Since
 * we use our own categories (instead of all the ones in the given
 * backend) we gather the backend products into our own categories
 * in this class. 
 * 
 * @author grupp 11
 */
public class IMatProducts {
  
  // Maps the categories we use to the products we have in them
  Map<ProductCategory, List<Product>> productCategories;
  
  // Used when initializing the product categories
  List<Product> products;
  
  /**
   * Initialize the products of our store.
   */
  public IMatProducts() {
    initializeProducts();
  }
  
  /**
   * Puts the products in the categories where we want them.
   */
  private void initializeProducts() {
    productCategories = new TreeMap();
    
    // Adds three kinds of flour to category BREAD
    products = IMatController.imat.getProducts(ProductCategory.BREAD);
    products.add(IMatController.imat.getProducts(ProductCategory.FLOUR_SUGAR_SALT).get(1));
    products.add(IMatController.imat.getProducts(ProductCategory.FLOUR_SUGAR_SALT).get(2));
    products.add(IMatController.imat.getProducts(ProductCategory.FLOUR_SUGAR_SALT).get(5));
    productCategories.put(ProductCategory.BREAD, products);
    products.clear();
    
    // Use FRUIT category to represent the five backend fruit categories
    products.addAll(IMatController.imat.getProducts(ProductCategory.FRUIT));
    products.addAll(IMatController.imat.getProducts(ProductCategory.BERRY));
    products.addAll(IMatController.imat.getProducts(ProductCategory.CITRUS_FRUIT));
    products.addAll(IMatController.imat.getProducts(ProductCategory.EXOTIC_FRUIT));
    products.addAll(IMatController.imat.getProducts(ProductCategory.MELONS));
    productCategories.put(ProductCategory.FRUIT, products);
    products.clear();
    
    // Use COLD_DRINKS to represent all kinds of drinks (cold and hot)
    products.addAll(IMatController.imat.getProducts(ProductCategory.COLD_DRINKS));
    products.addAll(IMatController.imat.getProducts(ProductCategory.HOT_DRINKS));
    productCategories.put(ProductCategory.COLD_DRINKS, products);
    products.clear();
    
    products.addAll(IMatController.imat.getProducts(ProductCategory.DAIRIES));
    productCategories.put(ProductCategory.DAIRIES, products);
    products.clear();
    
    products.addAll(IMatController.imat.getProducts(ProductCategory.FISH));
    productCategories.put(ProductCategory.FISH, products);
    products.clear();
    
    products.addAll(IMatController.imat.getProducts(ProductCategory.MEAT));
    productCategories.put(ProductCategory.MEAT, products);
    products.clear();
    
    products.addAll(IMatController.imat.getProducts(ProductCategory.NUTS_AND_SEEDS));
    productCategories.put(ProductCategory.NUTS_AND_SEEDS, products);
    products.clear();
    
    products.addAll(IMatController.imat.getProducts(ProductCategory.PASTA));
    productCategories.put(ProductCategory.PASTA, products);
    products.clear();
    
    // Adds two kinds of sugar to category SWEET
    products.addAll(IMatController.imat.getProducts(ProductCategory.SWEET));
    products.add(IMatController.imat.getProducts(ProductCategory.SWEET).get(0));
    products.add(IMatController.imat.getProducts(ProductCategory.SWEET).get(4));
    productCategories.put(ProductCategory.SWEET, products);
    products.clear();
    
    // Category ROOT_VEGETABLE contain both root vegetables and potatoes
    products.addAll(IMatController.imat.getProducts(ProductCategory.ROOT_VEGETABLE));
    for (int i = 3; i < 7; i++) {
      products.add(IMatController.imat.getProducts(ProductCategory.POTATO_RICE).get(i));
    }
    productCategories.put(ProductCategory.ROOT_VEGETABLE, products);
    products.clear();
    
    // Both cabbage and vegetable fruit is considered as VEGETABLE_FRUIT
    products.addAll(IMatController.imat.getProducts(ProductCategory.CABBAGE));
    products.addAll(IMatController.imat.getProducts(ProductCategory.VEGETABLE_FRUIT));
    productCategories.put(ProductCategory.VEGETABLE_FRUIT, products);
    products.clear();
    
    // Rice is categorized as POTATO_RICE
    products.add(IMatController.imat.getProducts(ProductCategory.POTATO_RICE).get(0));
    products.add(IMatController.imat.getProducts(ProductCategory.POTATO_RICE).get(1));
    products.add(IMatController.imat.getProducts(ProductCategory.POTATO_RICE).get(2));
    products.add(IMatController.imat.getProducts(ProductCategory.POTATO_RICE).get(7));
    productCategories.put(ProductCategory.POTATO_RICE, products);
    products.clear();
    
    products.addAll(IMatController.imat.getProducts(ProductCategory.POD));
    productCategories.put(ProductCategory.POD, products);
    products.clear();
    
    // Adds Salt as a spice to category HERB
    products.addAll(IMatController.imat.getProducts(ProductCategory.HERB));
    products.add(IMatController.imat.getProducts(ProductCategory.SWEET).get(3));
    productCategories.put(ProductCategory.HERB, products);
    products.clear();
    
  }
}

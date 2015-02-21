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
import se.chalmers.ait.dat215.project.User;

/**
 * This class represents everything related to the products. Since
 * we use our own categories (instead of all the ones in the given
 * backend) we gather the backend products into our own categories
 * in this class. Also, favorite products are stored together with
 * the login of a user.
 * 
 * @author grupp 11
 */
public class Products {
  
  // Maps given user to a list of favorite products
  Map<User, List<Product>> favoriteProducts;
  
  // Maps the categories we use to the products we have in them
  Map<ProductCategory, List<Product>> productCategories;
  
  // Used when initializing the product categories
  List<Product> products;
  
  /**
   * Initialize the products of our store.
   */
  public Products() {
    initializeProducts();
  }
  
  /**
   * Puts the products in the categories where we want them.
   */
  private void initializeProducts() {
    favoriteProducts = new TreeMap();
    productCategories = new TreeMap();
    
    products =IMatController.imat.getProducts(ProductCategory.BREAD);
    productCategories.put(ProductCategory.BREAD, products);
  }
}

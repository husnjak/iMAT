/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imat;

import java.util.Set;
import se.chalmers.ait.dat215.project.Product;

/**
 * This class represents a user account. It contains everything related to
 * a user account like credit card information, shipping address, personal
 * information, as well as favorite products.
 * 
 * @author grupp 11
 */
public class IMatUserAccount {
  
  // Contains all favorite products associated with the account
  private Set<Product> favoriteProducts;
  
  // Strings so whitespaces etc can be removed when parsing
  private String username;
  private String password;
  private String firstName;
  private String lastName;
  private String civicRegistrationNumber;
  private String phoneNumber;
  private String email;
  private String streetAddress;
  private String postalCode;
  private String city;
  private String cardNumber;
  private String cardType;
  private String cardHoldersname;
  private int validYear;
  private int validMonth;
  private int cvv;

  /**
   * @return the username
   */
  public String getUsername() {
    return username;
  }

  /**
   * @param username the username to set
   */
  public void setUsername(String username) {
    this.username = username;
  }

  /**
   * @return the password
   */
  public String getPassword() {
    return password;
  }

  /**
   * @param password the password to set
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * @return the firstName
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * @param firstName the firstName to set
   */
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  /**
   * @return the lastName
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * @param lastName the lastName to set
   */
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  /**
   * @return the civicRegistrationNumber
   */
  public String getCivicRegistrationNumber() {
    return civicRegistrationNumber;
  }

  /**
   * @param civicRegistrationNumber the civicRegistrationNumber to set
   */
  public void setCivicRegistrationNumber(String civicRegistrationNumber) {
    this.civicRegistrationNumber = civicRegistrationNumber;
  }

  /**
   * @return the phoneNumber
   */
  public String getPhoneNumber() {
    return phoneNumber;
  }

  /**
   * @param phoneNumber the phoneNumber to set
   */
  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  /**
   * @return the email
   */
  public String getEmail() {
    return email;
  }

  /**
   * @param email the email to set
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * @return the streetAddress
   */
  public String getStreetAddress() {
    return streetAddress;
  }

  /**
   * @param streetAddress the streetAddress to set
   */
  public void setStreetAddress(String streetAddress) {
    this.streetAddress = streetAddress;
  }

  /**
   * @return the postalCode
   */
  public String getPostalCode() {
    return postalCode;
  }

  /**
   * @param postalCode the postalCode to set
   */
  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }

  /**
   * @return the city
   */
  public String getCity() {
    return city;
  }

  /**
   * @param city the city to set
   */
  public void setCity(String city) {
    this.city = city;
  }

  /**
   * @return the cardNumber
   */
  public String getCardNumber() {
    return cardNumber;
  }

  /**
   * @param cardNumber the cardNumber to set
   */
  public void setCardNumber(String cardNumber) {
    this.cardNumber = cardNumber;
  }

  /**
   * @return the cardType
   */
  public String getCardType() {
    return cardType;
  }

  /**
   * @param cardType the cardType to set
   */
  public void setCardType(String cardType) {
    this.cardType = cardType;
  }

  /**
   * @return the cardHoldersname
   */
  public String getCardHoldersname() {
    return cardHoldersname;
  }

  /**
   * @param cardHoldersname the cardHoldersname to set
   */
  public void setCardHoldersname(String cardHoldersname) {
    this.cardHoldersname = cardHoldersname;
  }

  /**
   * @return the validYear
   */
  public int getValidYear() {
    return validYear;
  }

  /**
   * @param validYear the validYear to set
   */
  public void setValidYear(int validYear) {
    this.validYear = validYear;
  }

  /**
   * @return the validMonth
   */
  public int getValidMonth() {
    return validMonth;
  }

  /**
   * @param validMonth the validMonth to set
   */
  public void setValidMonth(int validMonth) {
    this.validMonth = validMonth;
  }

  /**
   * @return the cvv
   */
  public int getCvv() {
    return cvv;
  }

  /**
   * @param cvv the cvv to set
   */
  public void setCvv(int cvv) {
    this.cvv = cvv;
  }

  /**
   * @return the favoriteProducts
   */
  public Set<Product> getFavoriteProducts() {
    return favoriteProducts;
  }

  /**
   * @param favoriteProducts the favoriteProducts to set
   */
  public void setFavoriteProducts(Set<Product> favoriteProducts) {
    this.favoriteProducts = favoriteProducts;
  }
  
  
  
}

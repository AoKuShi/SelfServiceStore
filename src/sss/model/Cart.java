package sss.model;

import java.util.ArrayList;

public class Cart {
  private ArrayList<CartItem> cartItemList = new ArrayList<>();

  public boolean isEmpty() {
    return cartItemList.isEmpty();
  }

  public int getNumCartItems() {
    return cartItemList.size();
  }

  public String getCartItemInfo(int i) {
    return cartItemList.get(i).toString();
  }

  public int getTotalPrice() {
    int totalPrice = 0;
    for (CartItem item : cartItemList) {
      totalPrice += item.getPrice();
    }
    return totalPrice;
  }

  public void addItem(Product product) {
    CartItem item = getCartItem(product);

    if (item == null) {
      cartItemList.add(new CartItem(product));
    } else {
      item.addQuantity(1);
    }
  }

  public CartItem getCartItem(Product product) {
    for (CartItem item : cartItemList) {
      if (item.getProduct() == product)
        return item;
    }
    return null;
  }

  public CartItem getCartItem(String code) {
    for (CartItem item : cartItemList) {
      if (item.getCode().equals(code))
        return item;
    }
    return null;
  }

  public boolean isValidItem(String code) {
    for (CartItem item : cartItemList) {
      if (item.getCode().equals(code)) {
        return false;
      }
    }
    return true;
  }

  public void deleteCartItem(String code) {
    ArrayList<CartItem> item = cartItemList;

    for (int i = 0; i < cartItemList.size(); i++) {
      if (item.get(i).getCode().equals(code)) {
        cartItemList.remove(i);
        return;
      }
    }    
  }

  public void updateQuantity(String code, int quantity) {
    if (quantity == 0)
      deleteCartItem(code);
    else {
      CartItem item = getCartItem(code);
      item.setQuantity(quantity);
    }
  }

  public void resetCart() {
    cartItemList.clear();
  }

}

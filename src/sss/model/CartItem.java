package sss.model;

public class CartItem {
  private Product product;
  private String code;
  private int quantity;

  public CartItem(Product product) {
    this.product = product;
    this.code = product.getCode();
    this.quantity = 1;
  }

  public Product getProduct() {
    return product;
  }
  public void setProduct(Product product) {
    this.product = product;
  }

  public String getCode() {
    return code;
  }
  public void setCode(String code) {
    this.code = code;
  }

  public int getQuantity() {
    return quantity;
  }
  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public void addQuantity(int quantity) {
    this.quantity += quantity;
  }
  

  @Override
	public String toString() {
		return product.getCode() + ", " + product.getName() + ", " + quantity + "개, " + getPrice() + "원";
	}

  public int getPrice() {
    return quantity*product.getPrice();
  }

}

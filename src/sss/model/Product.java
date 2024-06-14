package sss.model;

public class Product {
  private String code;
  private String name;
  private String company;
  private int price;

  public Product(String code, String name, String company, int price) {
    this.code = code;
    this.name = name;
    this.company = company;
    this.price = price;
  }

  public String getCode() {
    return code;
  }
  public String getCompany() {
    return company;
  }
  public String getName() {
    return name;
  }
  public int getPrice() {
    return price;
  }

  @Override
  public String toString() {
    return code + ", " + company + ", " + name + ", " + price + "원";
  }
  
}

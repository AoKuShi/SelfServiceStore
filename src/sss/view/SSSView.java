package sss.view;

import java.util.Date;
import java.util.Scanner;

import sss.model.Cart;
import sss.model.Customer;
import sss.model.Warehouse;

public class SSSView {
  Scanner sc = new Scanner(System.in);
  Date date = new Date();
  
  
  public void inputCardNum(Customer customer){
    System.out.print("\n결제할 카드를 등록해주세요.\n>> 카드번호 : ");
    customer.setCardNum(sc.nextLine());
  }

  public void showMessage(String message) {
    System.out.println(message);
  }

  public int selectMenu(String[] menuList) {
    showMenu(menuList);
    int menu = readNumber("\n선택하고 싶은 메뉴의 번호를 입력하세요.\n>> 메뉴 선택 : ");

    if (menu < 0 || menu >= menuList.length) {
      System.out.println("\n0부터 " + (menuList.length-1) + "까지의 숫자를 입력하세요.");
      return selectMenu(menuList);
    }

    return menu;
  }

  public int readNumber(String str) {
    int result;
    try {
      System.out.print(str);
      result = sc.nextInt();
      sc.nextLine();
      return result;
    } catch (Exception e) {
      System.out.println("숫자를 입력해주세요.");
      return readNumber(str);
    }
  }

  public String readString(String str) {
    System.out.println(str);
    return sc.nextLine();
  }

  public void showMenu(String[] menuList) {
    System.out.println();
    for (int i = 1; i < menuList.length; i++) {
      System.out.println(menuList[i]);
    }
    System.out.println(menuList[0]);
  }

  public void showItemInfo(Warehouse warehouse) {
    for (int i = 0; i < warehouse.getNumItems(); i++) {
      System.out.println(warehouse.getItemInfo(i));
    }
  }

  public boolean showCart(Cart cart) {
    if (cart.isEmpty()) {
      System.out.println(">> 장바구니가 비어있습니다.");
      return true;
    }

    for (int i = 0; i < cart.getNumCartItems(); i++) {
      System.out.println(cart.getCartItemInfo(i));
    }

    System.out.println("총 금액 : " + cart.getTotalPrice() + "원");
    return false;
  }

  public String selectCode(Warehouse warehouse) {
    System.out.print("추가할 상품의 바코드를 입력 : ");
    String code = sc.nextLine();

    if (warehouse.isValidItem(code)) {
      System.out.println("잘못된 바코드입니다.");
      return selectCode(warehouse);
    }

    return code;
  }

  public String selectCode(Cart cart) {
    System.out.print("상품 바코드를 입력 : ");
    String code = sc.nextLine();

    if (cart.isValidItem(code)) {
      System.out.println("잘못된 바코드입니다.");
      return selectCode(cart);
    }

    return code;
  }

  public boolean askConfirm(String message, String yes) {
		System.out.print(message);

		if (sc.nextLine().equals(yes)) return true;
		return false;
  }

  public int inputQuantity(int min, int max) {
    int number;
    
    while (true) {
      number = readNumber(">> 수량 입력 (" + min + " ~ " + max + "): ");
      if (number >= min && number <= max) break;
      System.out.println("잘못된 수량입니다. 다시 입력해주세요.");
    }

    return number;
  }

  public boolean showOrder(Cart cart, Customer customer) {
    System.out.println();
		// 장바구니 보여주기
		System.out.println("***** 결제할 상품 ******");
    if (showCart(cart)) {
      System.out.println("결제할 상품이 없습니다.");
      return true;
    }
		
		// 배송 정보 보여주기 - 고객 이름, 전화번호, 주소, 이메일주소
		System.out.println("***** 결제 정보 ******");
		System.out.println(">> 카드 번호 : " + customer.getCardNum());
    System.out.println(">> 결제 날짜 : " + date);
		System.out.println();

    return false;
  }



}

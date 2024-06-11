package sss.controller;

import sss.model.Cart;
import sss.model.Customer;
import sss.model.Manager;
import sss.model.Product;
import sss.model.Warehouse;
import sss.view.SSSView;

public class SSSController {
  Warehouse warehouse;
  Cart cart;
  SSSView view;
  Customer customer;
  Manager manager = new Manager();

  String[] menuList = {
    "0. 종료",
    "1. 상품 정보 보기",
    "2. 장바구니 보기",
    "3. 장바구니에 상품 추가",
    "4. 장바구니 상품 삭제",
    "5. 장바구니 상품 수량 변경",
    "6. 장바구니 비우기",
    "7. 주문",
    "8. 관리자 모드"
  };

  String[] managerML = {
    "0. 돌아가기",
    "1. 상품 추가",
    "2. 상품 삭제"
  };

  public SSSController(Warehouse warehouse, Cart cart, SSSView view) {
    this.warehouse = warehouse;
    this.cart = cart;
    this.view = view;
  }

  public void start() {
    welcome();
    registerCardInfo();
    boolean bool1 = true;

    while (bool1) {
      switch (view.selectMenu(menuList)) {
        case 1 -> viewItemInfo();
        case 2 -> viewCart();
        case 3 -> addCart();
        case 4 -> deleteCartItem();
        case 5 -> updateCartItem();
        case 6 -> resetCart();
        case 7 -> order();
        case 8 -> adminMode();
        case 0 -> bool1 = end();
        default -> view.showMessage("\n잘못된 메뉴 번호입니다.");
      }
    }
  }

  private void adminMode() {
    if (view.readNumber("숫자 비밀번호 4자리를 입력하세요 : ") != manager.getPassword()) {
      view.showMessage("비밀번호가 다릅니다.");
      return;
    }

    boolean bool2 = true;

    while (bool2) {
      switch (view.selectMenu(managerML)) {
        case 1 -> addProduct();
        case 2 -> deleteProduct();
        case 0 -> bool2 = false;
        default -> view.showMessage("\n잘못된 메뉴 번호입니다.");
      }
    }
  }


  private void addProduct() {
    String code = view.readString("추가할 상품의 코드를 입력하세요: ");
    String name = view.readString("상품 이름을 입력하세요: ");
    String company = view.readString("상품 회사를 입력하세요: ");
    int price = view.readNumber("상품 가격을 입력하세요: ");

    Product product = new Product(code, name, company, price);
    if (warehouse.addProduct(product)) {
        view.showMessage("상품이 성공적으로 추가되었습니다.");
    } else {
        view.showMessage("상품 추가에 실패했습니다. 동일한 코드의 상품이 존재합니다.");
    }
  }

  private void deleteProduct() {
    String code = view.readString("삭제할 상품의 코드를 입력하세요: ");
    if (warehouse.deleteProduct(code)) {
        view.showMessage("상품이 성공적으로 삭제되었습니다.");
    } else {
        view.showMessage("상품을 찾을 수 없습니다.");
    }
  }

  private void viewItemInfo() {
    view.showItemInfo(warehouse);
  }

  private void viewCart() {
    view.showCart(cart);
  }

  private void addCart() {
    view.showItemInfo(warehouse);
    String code = view.selectCode(warehouse);
    cart.addItem(warehouse.getProduct(code));
    view.showMessage(">> 장바구니에 상품을 추가하였습니다.");
  }

  private void deleteCartItem() {
    view.showCart(cart);
    if (!cart.isEmpty()) {
      String code = view.selectCode(cart);
      if (view.askConfirm(">> 해당 상품을 삭제하려면 yes를 입력하세요 : ", "yes")) {
        cart.deleteCartItem(code);
        view.showMessage(">> 해당 상품을 장바구니에서 삭제했습니다.");
      }
    }
  }

  private void updateCartItem() {
    view.showCart(cart);
    if (!cart.isEmpty()) {
      String code = view.selectCode(cart);
      int quantity = view.inputQuantity(0, warehouse.getMaxQuantity());
      cart.updateQuantity(code, quantity);
    }
  }

  private void resetCart() {
    view.showCart(cart);

    if (!cart.isEmpty()) {
      if (view.askConfirm(">> 장바구니를 비우려면 yes를 입력하세요 : ", "yes")) {
				cart.resetCart();
				view.showMessage(">> 장바구니를 비웠습니다.");
      }
    }
  }

  private void order() {
    if (view.showOrder(cart, customer)) {
      return;
    }
    
    if (view.askConfirm("진짜 주문하려면 yes를 입력하세요 : ", "yes")) {
      view.showMessage("주문이 완료되었습니다.");
      cart.resetCart();
    }
  }

  private boolean end() {
    view.showMessage(">> 무인편의점을 종료합니다.");
    return false;
  }

  private void registerCardInfo() { // 고객 정보 입력
    customer = new Customer();
    view.inputCardNum(customer); // 카드 번호 입력
  }

  private void welcome() {
    view.showMessage("--- 무인편의점에 오신 것을 환영합니다. ---");
  }

}

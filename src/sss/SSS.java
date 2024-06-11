package sss;

import java.io.IOException;

import sss.controller.SSSController;
import sss.model.Cart;
import sss.model.Warehouse;
import sss.view.SSSView;

public class SSS {
  public static void main(String[] args) throws IOException {
		// model 생성
		Warehouse warehouse = new Warehouse();
		Cart cart = new Cart();
		
		// view 생성
		SSSView view = new SSSView();
		
		// controller 생성 (model, view)
		SSSController controller = new SSSController(warehouse, cart, view);
		controller.start();
		
  }
}

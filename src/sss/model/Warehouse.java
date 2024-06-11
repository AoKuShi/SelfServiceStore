package sss.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Warehouse {
	ArrayList<Product> itemList = new ArrayList<>();
	final int MAX_QUANTITY = 10;
	private String ProductFileName = "ProductFile.txt";

	public Warehouse() throws IOException {
		loadProductFile();
	}

	public void loadProductFile() {
		FileReader fr;
		try {
			fr = new FileReader(ProductFileName);
			BufferedReader br = new BufferedReader(fr);
			String idStr;
			while ((idStr = br.readLine()) != null) {
				String code = idStr;
				String name = br.readLine();
				String company = br.readLine();
				int price = Integer.parseInt(br.readLine());
				itemList.add(new Product(code, name, company, price));
			}
			fr.close();
			br.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public boolean addProduct(Product product) {
		if (isValidItem(product.getCode())) {
			itemList.add(product);
			saveProductFile();
			return true;
		}
		return false;
	}

	public boolean deleteProduct(String code) {
		for (Product product : itemList) {
			if (product.getCode().equals(code)) {
				itemList.remove(product);
				saveProductFile();
				return true;
			}
		}
		return false;
	}

	public void saveProductFile() {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(ProductFileName))) {
			for (Product product : itemList) {
				bw.write(product.getCode() + "\n");
				bw.write(product.getName() + "\n");
				bw.write(product.getCompany() + "\n");
				bw.write(product.getPrice() + "\n");
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public int getNumItems() {
		return itemList.size();
	}

	public String getItemInfo(int i) {
		return itemList.get(i).toString();
	}

	public Product getProduct(String code) {
		for (Product product : itemList) {
			if (product.getCode().equals(code)) {
				return product;
			}
		}
		return null;
	}

	public int getMaxQuantity() {
		return MAX_QUANTITY;
	}

	public boolean isValidItem(String code) {
		for (Product product : itemList) {
			if (product.getCode().equals(code)) {
				return false;
			}
		}
		return true;
	}
}

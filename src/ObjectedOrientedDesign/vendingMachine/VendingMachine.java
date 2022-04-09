package ObjectedOrientedDesign.vendingMachine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VendingMachine {

	private Map<String, Product> products = new HashMap<String, Product>();
	private int balance = 0;

	private List<Coin> coins;

    public VendingMachine() {
        List<Coin> coins = Arrays.asList(Coin.values());
        
        coins.sort((Coin a, Coin b) -> Integer.compare(b.value, a.value));
        
        this.coins = coins;
    }
    
	public void newProduct(String name, int price) {
		Product product = new Product(name, price);
		this.products.put(name, product);
	}

	public List<String> printProducts() {
		List<Product> products = new ArrayList<Product>(this.products.values());
		
		products.sort((Product a, Product b) -> Integer.compare(a.price, b.price));
		
		List<String> out = new ArrayList<String>();
		
		for (Product product : products) {
			out.add(product.toString());
		}
		
		return out;
	}
	
	public void restock(String name, int quantity) {
        Product product = this.products.get(name);
        product.quantity += quantity;
    }

	public boolean insertCoin(String name) {
        try {
            Coin coin = Coin.valueOf(name);
            this.balance += coin.value;
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

	public boolean purchase(String name) {
        Product product = products.get(name);
        
        if (product.price <= this.balance && product.quantity >= 1) {
            this.balance -= product.price;
            product.quantity -= 1;
            products.put(name, product);
            return true;
        }
        
        return false;
    }

	public List<String> checkout() {
        int balance = this.balance;
        this.balance = 0;
        List<String> out = new ArrayList<String>();
        
        for (Coin coin : this.coins) {
            int n = balance / coin.value;
            
            if (n != 0) {
                out.add(String.format("%d %s", n, coin.name()));
            }
            
            balance = balance % coin.value;
        }
        
        return out;
    }
}
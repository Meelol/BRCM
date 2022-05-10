package model.classes;

import java.util.HashMap;

public class Cart {
    private HashMap<Product, Integer> cart = new HashMap<Product, Integer>();
    
    public Cart(){

    }

    public void addToCart(Product product, int quantity) {
        this.cart.put(product, quantity);
    }
}

package model.classes;

import java.util.HashMap;

public class Cart {
    private HashMap<Product, Integer> productQuantity;
    static Cart cart = new Cart();
    Cart(){
        this.productQuantity = new HashMap<Product, Integer>();
    }
    public static void addToCart(Product product, int quantity) {
        Cart.cart.productQuantity.put(product, quantity);
    }
    
    public static void printCart(){
        System.out.println("New Shopping Cart:");
        for (Product name: cart.productQuantity.keySet()) {
            String key = name.getName();
            String value = cart.productQuantity.get(name).toString();
            System.out.println(key + " " + value);
        }
    }
}

package model.classes;

import java.util.HashMap;

public class Cart {
    private HashMap<Product, Integer> productQuantity;
    private HashMap<Activity, Integer> scheduleActivity;
    public static Cart cart = new Cart();

    Cart() {
        this.productQuantity = new HashMap<Product, Integer>();
        this.scheduleActivity = new HashMap<Activity, Integer>();
    }

    public static void addProductToCart(Product product, int quantity) {
        if(quantity > 0){
            if(Cart.cart.productQuantity.containsKey(product)){
                Cart.cart.productQuantity.replace(product, Cart.cart.productQuantity.get(product), quantity);
            }
            else{
                Cart.cart.productQuantity.put(product, quantity);
            }
        }
    }

    public static void addActivityToCart(Activity activity) {
        Cart.cart.scheduleActivity.put(activity, 1);
    }

    public HashMap<Product, Integer> getProductQuantity() {
        return this.productQuantity;
    }

    public HashMap<Activity, Integer> getActivityQuantity() {
        return this.scheduleActivity;
    }

    public static void printCart() {
        System.out.println("New Shopping Cart:");
        for (Product name : cart.productQuantity.keySet()) {
            String key = name.getName();
            String value = cart.productQuantity.get(name).toString();
            System.out.println(key + " " + value);
        }
        for (Activity name : cart.scheduleActivity.keySet()) {
            String key = name.getName();
            String value = cart.scheduleActivity.get(name).toString();
            System.out.println(key + " " + value);
        }
    }

    public static void removeProductFromCart(Product product) {
        cart.productQuantity.remove(product);
    }

    public static void removeActivityFromCart(Activity activity) {
        cart.scheduleActivity.remove(activity);
    }

    public static void orderFulfilled(){
        cart.productQuantity.clear();
        cart.scheduleActivity.clear();
    }
}

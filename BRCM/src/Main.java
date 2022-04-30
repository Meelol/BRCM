import java.time.Month;

public class Main {
    public static void main(String[] args) {
        Student omar = new Student(15973, "Omar Rodriguez", "(657)262-1879", "1018 E La Habra Blvd.", 138, "La Habra",
                "CA", 90631, 1999, Month.OCTOBER, 17, "Computer Science", 2018, Month.SEPTEMBER, 15, 2022,
                Month.DECEMBER, 20);
        Product apple = new Product(1, "Apple", (float) 2.0);
        Order order1 = new Order(1, omar.getBroncoID());
        order1.addProduct(apple, 5);
        System.out.printf("Total price: %.1f\n", order1.getTotalPrice());
        System.out.printf("Discount Type: %s (%.1f%%)\n",omar.printDiscountType(),(omar.getDiscountScheme()*100));
        System.out.printf("Discount price: %.1f\n", order1.getTotalPrice() - (order1.getTotalPrice() * omar.getDiscountScheme()));
    }
}

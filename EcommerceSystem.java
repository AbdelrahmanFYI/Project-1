package project ;
import java.util.Scanner ;

class Product {
   
    private int productId;
    private String name;
    private double price;

    public Product(int productId, String name, double price) {
        this.productId = Math.abs(productId) ;
        this.name = name ;
        this.price = Math.abs(price) ;
    }  
    public void setProductId (int productId) {
        this.productId = productId ;
    }
    public void setName (String name) {
      this.name = name ;
    }
    public void setPrice (double price) {
        this.price = price ;
    }
    public int getProductId() {
        return this.productId;
    }

    public String getName() {
        return this.name;
    }

    public double getPrice() {
        return this.price;
    }
}

class ElectronicProduct extends Product {
    
    private String brand ;
    private int warrantyPeriod ;
    
    public ElectronicProduct (int productId , String name , double price , String brand , int warrantyPeriod) {
        super (productId,name,price) ;
        this.brand = brand ;
        this.warrantyPeriod = warrantyPeriod ;
    }
    
    public void setBrand (String brand) {
        this.brand = brand ;
    }
    public void setWarranty (int warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod ;
    }
    
    public String getBrand () {
    return this.brand ;
} 
    public int getWarranty () {
        return this.warrantyPeriod ;
    }
}

class ClothingProduct extends Product {
    
    private String size;
    private String fabric;

    public ClothingProduct(int productId, String name, double price , String size , String fabric) {
        super(productId, name, price);
        this.size = size ;
        this.fabric = fabric ;
    }
    
    public void setSize (String size) {
        this.size = size ;
    }
    public void setFabric (String fabric) {
        this.fabric = fabric ;
    }
    
    public String getSize () {
        return this.size ;
    }
    public String getFabric () {
        return this.fabric ;
    }
}

class BookProduct extends Product {
   
    private String author ;
    private String publisher ;
    
    public BookProduct(int productId, String name, double price , String author , String publisher) {
        super(productId, name, price);
        this.author = author ;
        this.publisher = publisher ;
    }
    
    public void setAuthor (String author) {
        this.author = author ;
    }
    public void setPublisher (String publisher) {
        this.publisher = publisher ;
    }
    
    public String getAuthor () {
        return this.author ;
    }
    public String getPublisher () {
        return this.publisher ;
    }
}

class Customer {
    
    private int customerId ;
    private String name ;
    private String address ;
    
    public Customer (int customerId , String name , String address ) {
        this.customerId = Math.abs(customerId) ;
        this.name = name ;
        this.address = address ;
    }
    
    public void setCustomerId (int customerId) {
    this.customerId = customerId ;
    }
    public void setCustomerName (String name) {
        this.name = name ;
    }
    public void setAddress (String address) {
        this.address = address ;
    }
    
    public int getCustomerId () {
        return this.customerId ;
    }
    public String getCustomerName () {
        return this.name ;
    }
    public String getAddress () {
        return this.address ;
    }
}

class Cart {
    
    private int customerId;
    private int nProducts;
    private Product [] products;
    public Cart(int customerId, int nProducts) {
        this.customerId = Math.abs(customerId);
        this.nProducts = Math.abs(nProducts);
        this.products = new Product[nProducts];
    }
    public void setCustomerId (int customerId) {
        this.customerId = customerId ;
    }
    public void setProductsNumebr (int nProducts){
        this.nProducts = nProducts ;
    }
    public int getCustomerId () {
        return this.customerId ;
    }
    public int getProductsNummber () {
        return this.nProducts ;
    }

    public void addProduct(Product product) {
        for (int i = 0; i < products.length; i++) {
            if (products[i] == null) {
                products[i] = product;
                break ;
            }
        }
    }
    public void removeProduct(int productId) {
        for (int i = 0; i < products.length; i++) {
            if (products[i] != null && products[i].getProductId() == productId) {
                products[i] = null;
                break ;
            }
        }
    }
    public double calculatePrice() {
        double totalPrice = 0 ;
        for (Product product : products) {
            if (product != null) {
                totalPrice += product.getPrice();
            }
        }
        return totalPrice;
    }
     public Order placeOrder(int orderId) {
        return new Order (customerId , orderId ,  products, calculatePrice());
    }
}

class Order {
    private int customerId ;
    private int orderId ;
    private Product[] products ;
    private double totalPrice ;

    public Order(int customerId, int orderId , Product[] products , double totalPrice) {
        this.customerId = customerId ;
        this.orderId = orderId ;
        this.products = products ;
        this.totalPrice = totalPrice ;
    }
    
    public void printOrderInfo() {
        
        System.out.println("Here's your order's summary :");
        System.out.println("Order ID: " + orderId);
        System.out.println("Customer ID: " + customerId);
        System.out.println("Products:");
        for (Product product : products) {
            if (product != null) {
                System.out.println( product.getName() + " - $" + product.getPrice());
            }
        }
        System.out.println("Total Price: $" + totalPrice);
    }
}

public class EcommerceSystem {
public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
   
    ElectronicProduct ep = new ElectronicProduct(1, "smartphone", 599.9, "Samsunng", 1);
    ClothingProduct cp = new ClothingProduct(2, "T-shirt", 19.99, "Medium", "Cotton");
    BookProduct bp = new BookProduct(3, "OOP", 39.99, "O'Riley", "X Publications");
    
    System.out.println("Welcome to the E-Commerce System!");
    System.out.println("Please enter your id");
    int customerId = input.nextInt();
    input.nextLine();
    System.out.println("Please enter your name");
    String name = input.nextLine();
    System.out.println("Please enter your address");
    String address = input.nextLine();

    Customer c = new Customer(customerId, name, address);
    System.out.println("How many products do you want to add to your cart?");
    int nProducts = input.nextInt();
    Cart cart = new Cart(c.getCustomerId(), nProducts);
    int orderId = 1;
    for (int i = 0; i < nProducts; i++) {
        System.out.println("Which product would you like to add? 1- Smartphone 2- T-Shirt 3- OOP");
        int choice = input.nextInt();
        switch (choice) {
            case 1:
                cart.addProduct(ep);
                break;
            case 2:
                cart.addProduct(cp);
                break;
            case 3:
                cart.addProduct(bp);
                break;
            default:
                System.out.println("Invalid choice!");
                i--;
                break;
        }
    }
    System.out.println("Your total is $" + cart.calculatePrice() + ". Would you like to place the order? 1-Yes 2-No");
    int x = input.nextInt();
    if (x == 1) {
        Order order = cart.placeOrder(orderId);
        order.printOrderInfo();
    } else if (x == 2) {
        System.out.println("Order cancelled.");
    } else {
        System.out.println("Invalid choice!");
    }
}       
} 
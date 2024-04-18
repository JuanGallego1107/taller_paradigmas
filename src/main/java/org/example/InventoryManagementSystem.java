package org.example;

import java.util.ArrayList;
import java.util.Scanner;

// Class representing a product in the inventory
class Product {
    private String name;
    private int quantity;

    public Product(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

// Class representing the inventory
class Inventory {
    private ArrayList<Product> products;

    public Inventory() {
        products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(String name) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getName().equals(name)) {
                products.remove(i);
                break;
            }
        }
    }

    public void updateQuantity(String name, int newQuantity) {
        for (Product product : products) {
            if (product.getName().equals(name)) {
                product.setQuantity(newQuantity);
                break;
            }
        }
    }

    public void showInventory() {
        System.out.println("Inventory:");
        for (Product product : products) {
            System.out.println(product.getName() + ": " + product.getQuantity());
        }
    }
}

// Main class containing the main program
public class InventoryManagementSystem {
    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        Scanner scanner = new Scanner(System.in);
        int opc;

        // Add some products to the inventory
        inventory.addProduct(new Product("T-shirt", 50));
        inventory.addProduct(new Product("Pants", 30));
        inventory.addProduct(new Product("Shoes", 20));
        inventory.addProduct(new Product("Hat", 10));
        inventory.addProduct(new Product("Watch", 20));

        do{

            // Show the initial inventory
            inventory.showInventory();

            System.out.println("=======================");

            System.out.println("""
                    Select an action to do:
                    1.Add product
                    2.Update quantity
                    3.Delete product
                    4.Exit""");
            opc = scanner.nextInt();


            switch (opc) {
                case 1:
                    System.out.println("Enter product details:");
                    System.out.print("Name: ");
                    String productName = scanner.next();
                    System.out.print("Quantity: ");
                    int productQuantity = scanner.nextInt();
                    inventory.addProduct(new Product(productName, productQuantity));
                    break;

                case 2:
                    // Update the quantity of a product
                    System.out.print("Enter the name of the product to update: ");
                    String productToUpdate = scanner.next();
                    System.out.print("Enter the new quantity: ");
                    int newQuantity = scanner.nextInt();
                    inventory.updateQuantity(productToUpdate, newQuantity);                break;

                case 3:
                    // Remove a product from the inventory
                    System.out.print("Enter the name of the product to remove: ");
                    String productToRemove = scanner.next();
                    inventory.removeProduct(productToRemove);
                    break;

                case 4:
                    break;

            }
        }while(opc != 4);
    }
}

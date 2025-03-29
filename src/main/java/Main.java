import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ProductManager productManager = new ProductManager();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Product Search Program");
        System.out.println("=====================");

        // Usar datos.csv por defecto
        String filePath = "datos.csv";
        System.out.println("Usando el archivo: " + filePath);

        // Load the products
        try {
            System.out.println("Loading products...");
            productManager.loadProductsFromCSV(filePath);
            System.out.println("Loaded " + productManager.getProductCount() + " products.");
        } catch (IOException e) {
            System.err.println("Error loading products: " + e.getMessage());
            return;
        }

        // Main loop
        boolean running = true;
        while (running) {
            System.out.println("\nMenu:");
            System.out.println("1. Search for a product by SKU");
            System.out.println("2. List products by price (ascending)");
            System.out.println("3. List products by price (descending)");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    searchProduct(productManager, scanner);
                    break;
                case "2":
                    listProducts(productManager, true);
                    break;
                case "3":
                    listProducts(productManager, false);
                    break;
                case "4":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }

        scanner.close();
    }

    /**
     * Handles the product search functionality.
     * @param productManager The product manager
     * @param scanner The scanner for user input
     */
    private static void searchProduct(ProductManager productManager, Scanner scanner) {
        System.out.print("Enter the SKU: ");
        String sku = scanner.nextLine();

        Product product = productManager.searchBySKU(sku);

        if (product != null) {
            System.out.println("\nProduct found:");
            System.out.println("SKU: " + product.getSku());
            System.out.println("Name: " + product.getProductName());
            System.out.println("Category: " + product.getCategory());
            System.out.println("Retail Price: $" + product.getPriceRetail());
            System.out.println("Current Price: $" + product.getPriceCurrent());
        } else {
            System.out.println("Product not found.");
        }
    }

    /**
     * Lists the products sorted by price.
     * @param productManager The product manager
     * @param ascending True for ascending order, false for descending
     */
    private static void listProducts(ProductManager productManager, boolean ascending) {
        List<Product> products;

        if (ascending) {
            System.out.println("\nProducts sorted by price (ascending):");
            products = productManager.listProductsByPriceAscending();
        } else {
            System.out.println("\nProducts sorted by price (descending):");
            products = productManager.listProductsByPriceDescending();
        }

        int count = 0;
        for (Product product : products) {
            System.out.printf("%-20s | %-40s | $%-10.2f | $%-10.2f | %s%n",
                    product.getSku(),
                    truncate(product.getProductName(), 40),
                    product.getPriceRetail(),
                    product.getPriceCurrent(),
                    product.getCategory());

            count++;
            // Show only first 20 products to avoid flooding the console
            if (count >= 20) {
                System.out.println("... and " + (products.size() - 20) + " more products.");
                break;
            }
        }
    }

    /**
     * Truncates a string to a maximum length.
     * @param str The string to truncate
     * @param maxLength The maximum length
     * @return The truncated string
     */
    private static String truncate(String str, int maxLength) {
        if (str.length() <= maxLength) {
            return str;
        }
        return str.substring(0, maxLength - 3) + "...";
    }
}
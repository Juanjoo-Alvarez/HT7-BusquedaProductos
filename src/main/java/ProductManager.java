import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ProductManager {
    private BinarySearchTree<Product> productTree;

    public ProductManager() {
        productTree = new BinarySearchTree<>();
    }

    /**
     * Loads products from a CSV file into the BST.
     * @param filePath Path to the CSV file
     * @throws IOException If an I/O error occurs
     */
    public void loadProductsFromCSV(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;

        // Skip the header line
        line = reader.readLine();

        while ((line = reader.readLine()) != null) {
            try {
                Product product = parseProductFromCSV(line);
                if (product != null) {
                    productTree.insert(product);
                }
            } catch (Exception e) {
                System.err.println("Error parsing line: " + line);
                System.err.println(e.getMessage());
            }
        }

        reader.close();
    }

    /**
     * Parses a product from a CSV line.
     * @param line The CSV line
     * @return The parsed product
     */
    private Product parseProductFromCSV(String line) {
        String[] fields = line.split(",");

        // Make sure we have enough fields
        if (fields.length < 20) {
            return null;
        }

        // Extract the required fields based on your CSV structure
        // CATEGORY,DATE_SCRAPED,SORT_BY,RUN_START_DATE,SUBCATEGORY,SHIPPING_LOCATION,SKU,COUNTRY,BRAND,PRICE_RETAIL,PRICE_CURRENT,SELLER,PRODUCT_URL,CURRENCY,BREADCRUMBS,DEPARTMENT,PROMOTION,BESTSELLER_RANK,PRODUCT_NAME,WEBSITE_URL

        String category = fields[0];
        String sku = fields[6];
        double priceRetail = parsePrice(fields[9]);
        double priceCurrent = parsePrice(fields[10]);
        String productName = fields[18];

        return new Product(sku, priceRetail, priceCurrent, productName, category);
    }

    /**
     * Parses a price from a string.
     * @param priceStr The price string
     * @return The parsed price
     */
    private double parsePrice(String priceStr) {
        try {
            // Remove any non-numeric characters except the decimal point
            String cleanedPrice = priceStr.replaceAll("[^0-9.]", "");
            return Double.parseDouble(cleanedPrice);
        } catch (NumberFormatException e) {
            System.err.println("Error parsing price: " + priceStr);
            return 0.0;
        }
    }

    /**
     * Searches for a product by SKU.
     * @param sku The SKU to search for
     * @return The product if found, null otherwise
     */
    public Product searchBySKU(String sku) {
        // Create a dummy product with the given SKU for searching
        Product dummyProduct = new Product(sku, 0, 0, "", "");
        return productTree.search(dummyProduct);
    }

    /**
     * Returns a list of all products sorted by price (ascending).
     * @return The sorted list
     */
    public List<Product> listProductsByPriceAscending() {
        List<Product> products = productTree.inOrder();
        return products.stream()
                .sorted(Comparator.comparing(Product::getPriceCurrent))
                .collect(Collectors.toList());
    }

    /**
     * Returns a list of all products sorted by price (descending).
     * @return The sorted list
     */
    public List<Product> listProductsByPriceDescending() {
        List<Product> products = productTree.inOrder();
        return products.stream()
                .sorted(Comparator.comparing(Product::getPriceCurrent).reversed())
                .collect(Collectors.toList());
    }

    /**
     * Returns the number of products in the tree.
     * @return The number of products
     */
    public int getProductCount() {
        return productTree.size();
    }
}
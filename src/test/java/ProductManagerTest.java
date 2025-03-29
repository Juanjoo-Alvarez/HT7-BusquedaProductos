import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class ProductManagerTest {

    private ProductManager productManager;
    private Path tempCsvFile;

    @BeforeEach
    public void setUp() throws IOException {
        productManager = new ProductManager();

        // Create a temporary CSV file for testing
        tempCsvFile = Files.createTempFile("test_products", ".csv");

        // Write test data to the CSV file
        try (FileWriter writer = new FileWriter(tempCsvFile.toFile())) {
            writer.write("CATEGORY,DATE_SCRAPED,SORT_BY,RUN_START_DATE,SUBCATEGORY,SHIPPING_LOCATION,SKU,COUNTRY,BRAND,PRICE_RETAIL,PRICE_CURRENT,SELLER,PRODUCT_URL,CURRENCY,BREADCRUMBS,DEPARTMENT,PROMOTION,BESTSELLER_RANK,PRODUCT_NAME,WEBSITE_URL\n");
            writer.write("Appliances,2023-01-01,Popular,2023-01-01,Washers,US,TEST-SKU-001,US,TestBrand,199.99,179.99,TestSeller,http://example.com,USD,Home>Appliances,Home,None,1,Test Washing Machine,http://example.com\n");
            writer.write("Electronics,2023-01-01,Popular,2023-01-01,TVs,US,TEST-SKU-002,US,TestBrand,499.99,399.99,TestSeller,http://example.com,USD,Home>Electronics,Home,None,2,Test TV,http://example.com\n");
            writer.write("Furniture,2023-01-01,Popular,2023-01-01,Chairs,US,TEST-SKU-003,US,TestBrand,99.99,79.99,TestSeller,http://example.com,USD,Home>Furniture,Home,None,3,Test Chair,http://example.com\n");
        }
    }

    @Test
    public void testLoadProductsFromCSV() throws IOException {
        // Load the products from the test CSV file
        productManager.loadProductsFromCSV(tempCsvFile.toString());

        // Check that the correct number of products were loaded
        assertEquals(3, productManager.getProductCount(), "Should have loaded 3 products");

        // Check that we can find each product by SKU
        assertNotNull(productManager.searchBySKU("TEST-SKU-001"), "Should find TEST-SKU-001");
        assertNotNull(productManager.searchBySKU("TEST-SKU-002"), "Should find TEST-SKU-002");
        assertNotNull(productManager.searchBySKU("TEST-SKU-003"), "Should find TEST-SKU-003");
    }

    @Test
    public void testSearchBySKU() throws IOException {
        // Load the products from the test CSV file
        productManager.loadProductsFromCSV(tempCsvFile.toString());

        // Search for existing products
        Product product1 = productManager.searchBySKU("TEST-SKU-001");
        assertNotNull(product1, "Should find TEST-SKU-001");
        assertEquals("Test Washing Machine", product1.getProductName(), "Product name should match");
        assertEquals(179.99, product1.getPriceCurrent(), 0.001, "Current price should match");
        assertEquals("Appliances", product1.getCategory(), "Category should match");

        Product product2 = productManager.searchBySKU("TEST-SKU-002");
        assertNotNull(product2, "Should find TEST-SKU-002");
        assertEquals("Test TV", product2.getProductName(), "Product name should match");
        assertEquals(399.99, product2.getPriceCurrent(), 0.001, "Current price should match");

        // Search for non-existing product
        Product nonExistingProduct = productManager.searchBySKU("TEST-SKU-999");
        assertNull(nonExistingProduct, "Should not find non-existing product");
    }

    @Test
    public void testListProductsByPrice() throws IOException {
        // Load the products from the test CSV file
        productManager.loadProductsFromCSV(tempCsvFile.toString());

        // Test ascending order
        List<Product> ascendingProducts = productManager.listProductsByPriceAscending();
        assertEquals(3, ascendingProducts.size(), "Should have 3 products");
        assertEquals("TEST-SKU-003", ascendingProducts.get(0).getSku(), "Cheapest product should be TEST-SKU-003");
        assertEquals("TEST-SKU-001", ascendingProducts.get(1).getSku(), "Second product should be TEST-SKU-001");
        assertEquals("TEST-SKU-002", ascendingProducts.get(2).getSku(), "Most expensive product should be TEST-SKU-002");

        // Test descending order
        List<Product> descendingProducts = productManager.listProductsByPriceDescending();
        assertEquals(3, descendingProducts.size(), "Should have 3 products");
        assertEquals("TEST-SKU-002", descendingProducts.get(0).getSku(), "Most expensive product should be TEST-SKU-002");
        assertEquals("TEST-SKU-001", descendingProducts.get(1).getSku(), "Second product should be TEST-SKU-001");
        assertEquals("TEST-SKU-003", descendingProducts.get(2).getSku(), "Cheapest product should be TEST-SKU-003");
    }
}
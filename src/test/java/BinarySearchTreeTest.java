import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BinarySearchTreeTest {

    private BinarySearchTree<Product> productTree;

    @BeforeEach
    public void setUp() {
        productTree = new BinarySearchTree<>();
    }

    @Test
    public void testInsert() {
        // Create some test products
        Product product1 = new Product("SKU001", 99.99, 79.99, "Test Product 1", "Category1");
        Product product2 = new Product("SKU002", 149.99, 129.99, "Test Product 2", "Category2");
        Product product3 = new Product("SKU003", 199.99, 179.99, "Test Product 3", "Category1");

        // Insert the products
        productTree.insert(product1);
        assertEquals(1, productTree.size(), "Tree size should be 1 after inserting first product");

        productTree.insert(product2);
        assertEquals(2, productTree.size(), "Tree size should be 2 after inserting second product");

        productTree.insert(product3);
        assertEquals(3, productTree.size(), "Tree size should be 3 after inserting third product");

        // Test inserting a duplicate (should replace)
        Product product1Duplicate = new Product("SKU001", 89.99, 69.99, "Updated Product 1", "Category1");
        productTree.insert(product1Duplicate);
        assertEquals(3, productTree.size(), "Tree size should still be 3 after inserting duplicate");
    }

    @Test
    public void testSearch() {
        // Create some test products
        Product product1 = new Product("SKU001", 99.99, 79.99, "Test Product 1", "Category1");
        Product product2 = new Product("SKU002", 149.99, 129.99, "Test Product 2", "Category2");
        Product product3 = new Product("SKU003", 199.99, 179.99, "Test Product 3", "Category1");

        // Insert the products
        productTree.insert(product1);
        productTree.insert(product2);
        productTree.insert(product3);

        // Search for existing products
        Product foundProduct1 = productTree.search(new Product("SKU001", 0, 0, "", ""));
        assertNotNull(foundProduct1, "Should find product with SKU001");
        assertEquals("Test Product 1", foundProduct1.getProductName(), "Product name should match");
        assertEquals(79.99, foundProduct1.getPriceCurrent(), 0.001, "Current price should match");

        Product foundProduct2 = productTree.search(new Product("SKU002", 0, 0, "", ""));
        assertNotNull(foundProduct2, "Should find product with SKU002");
        assertEquals("Test Product 2", foundProduct2.getProductName(), "Product name should match");

        Product foundProduct3 = productTree.search(new Product("SKU003", 0, 0, "", ""));
        assertNotNull(foundProduct3, "Should find product with SKU003");
        assertEquals("Test Product 3", foundProduct3.getProductName(), "Product name should match");

        // Search for non-existing product
        Product nonExistingProduct = productTree.search(new Product("SKU999", 0, 0, "", ""));
        assertNull(nonExistingProduct, "Should not find non-existing product");
    }

    @Test
    public void testInOrderTraversal() {
        // Create some test products
        Product product1 = new Product("SKU001", 99.99, 79.99, "Test Product 1", "Category1");
        Product product2 = new Product("SKU002", 149.99, 129.99, "Test Product 2", "Category2");
        Product product3 = new Product("SKU003", 199.99, 179.99, "Test Product 3", "Category1");

        // Insert the products in non-sequential order
        productTree.insert(product2);  // SKU002
        productTree.insert(product1);  // SKU001
        productTree.insert(product3);  // SKU003

        // Get the in-order traversal
        var products = productTree.inOrder();

        // Check that products are in correct order (by SKU)
        assertEquals(3, products.size(), "Should have 3 products");
        assertEquals("SKU001", products.get(0).getSku(), "First product should be SKU001");
        assertEquals("SKU002", products.get(1).getSku(), "Second product should be SKU002");
        assertEquals("SKU003", products.get(2).getSku(), "Third product should be SKU003");
    }
}
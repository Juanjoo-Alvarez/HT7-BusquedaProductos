package com.uvg.productsearch.datastructure;

import com.uvg.productsearch.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias para el Árbol Binario de Búsqueda
 */
public class BinarySearchTreeTest {
    
    private BinarySearchTree<Product> productTree;
    
    @BeforeEach
    public void setup() {
        productTree = new BinarySearchTree<>();
    }
    
    /**
     * Prueba para insertar un producto y luego buscarlo
     */
    @Test
    public void testInsertAndSearch() {
        // Creamos un producto de prueba
        Product testProduct = new Product("ABC123", 100.0, 80.0, "Producto de Prueba", "Categoría");
        
        // Lo insertamos en el árbol
        productTree.insert(testProduct);
        
        // Creamos un producto con el mismo SKU para buscar
        Product searchKey = new Product();
        searchKey.setSku("ABC123");
        
        // Buscamos en el árbol
        Product found = productTree.search(searchKey);
        
        // Verificamos que el producto encontrado no sea nulo
        assertNotNull(found);
        
        // Verificamos que el SKU sea el mismo
        assertEquals("ABC123", found.getSku());
        
        // Verificamos el resto de propiedades
        assertEquals(100.0, found.getPriceRetail());
        assertEquals(80.0, found.getPriceCurrent());
        assertEquals("Producto de Prueba", found.getProductName());
        assertEquals("Categoría", found.getCategory());
    }
    
    /**
     * Prueba para buscar un producto que no existe
     */
    @Test
    public void testSearchNonExistent() {
        // Creamos un producto con un SKU que no hemos insertado
        Product searchKey = new Product();
        searchKey.setSku("NONEXISTENT");
        
        // Buscamos en el árbol
        Product found = productTree.search(searchKey);
        
        // Verificamos que el resultado sea nulo
        assertNull(found);
    }
    
    /**
     * Prueba para insertar múltiples productos y buscarlos
     */
    @Test
    public void testInsertMultipleProducts() {
        // Creamos varios productos de prueba
        Product product1 = new Product("ABC123", 100.0, 80.0, "Producto 1", "Categoría 1");
        Product product2 = new Product("DEF456", 200.0, 180.0, "Producto 2", "Categoría 2");
        Product product3 = new Product("GHI789", 300.0, 280.0, "Producto 3", "Categoría 1");
        
        // Los insertamos en el árbol
        productTree.insert(product1);
        productTree.insert(product2);
        productTree.insert(product3);
        
        // Buscamos cada uno
        Product searchKey1 = new Product();
        searchKey1.setSku("ABC123");
        Product found1 = productTree.search(searchKey1);
        
        Product searchKey2 = new Product();
        searchKey2.setSku("DEF456");
        Product found2 = productTree.search(searchKey2);
        
        Product searchKey3 = new Product();
        searchKey3.setSku("GHI789");
        Product found3 = productTree.search(searchKey3);
        
        // Verificamos que se hayan encontrado todos
        assertNotNull(found1);
        assertNotNull(found2);
        assertNotNull(found3);
        
        // Verificamos que los SKUs sean correctos
        assertEquals("ABC123", found1.getSku());
        assertEquals("DEF456", found2.getSku());
        assertEquals("GHI789", found3.getSku());
    }
    
    /**
     * Prueba para actualizar un producto existente
     */
    @Test
    public void testUpdateProduct() {
        // Insertamos un producto
        Product originalProduct = new Product("ABC123", 100.0, 80.0, "Producto Original", "Categoría");
        productTree.insert(originalProduct);
        
        // Creamos un producto con el mismo SKU pero datos diferentes
        Product updatedProduct = new Product("ABC123", 120.0, 90.0, "Producto Actualizado", "Nueva Categoría");
        
        // Lo insertamos en el árbol (debería actualizar el existente)
        productTree.insert(updatedProduct);
        
        // Buscamos el producto
        Product searchKey = new Product();
        searchKey.setSku("ABC123");
        Product found = productTree.search(searchKey);
        
        // Verificamos que se haya actualizado
        assertNotNull(found);
        assertEquals("ABC123", found.getSku());
        assertEquals(120.0, found.getPriceRetail());
        assertEquals(90.0, found.getPriceCurrent());
        assertEquals("Producto Actualizado", found.getProductName());
        assertEquals("Nueva Categoría", found.getCategory());
    }
}
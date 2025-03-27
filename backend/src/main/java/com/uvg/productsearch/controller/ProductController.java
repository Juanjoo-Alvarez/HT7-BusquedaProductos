package com.uvg.productsearch.controller;

import com.uvg.productsearch.model.Product;
import com.uvg.productsearch.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * Controlador REST para manejar las peticiones relacionadas con productos
 */
@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:3000") // Para permitir peticiones desde el frontend
public class ProductController {
    
    private final ProductService productService;
    
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    
    /**
     * Endpoint para buscar un producto por su SKU
     */
    @GetMapping("/{sku}")
    public ResponseEntity<Product> getProductBySku(@PathVariable String sku) {
        Product product = productService.findProductBySku(sku);
        
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(product);
    }
    
    /**
     * Endpoint para obtener todos los productos (limitado a los primeros 100)
     */
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts(100); // Limitamos a 100 para evitar sobrecarga
        return ResponseEntity.ok(products);
    }
}
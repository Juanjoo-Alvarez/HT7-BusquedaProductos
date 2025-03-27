package com.uvg.productsearch.service;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.uvg.productsearch.datastructure.BinarySearchTree;
import com.uvg.productsearch.model.Product;

/**
 * Servicio para manejar operaciones relacionadas con productos
 */
@Service
public class ProductService {
    private BinarySearchTree<Product> productTree;
    private List<Product> allProducts;
    
    public ProductService() {
        productTree = new BinarySearchTree<>();
        allProducts = new ArrayList<>();
    }
    
    /**
     * Carga los datos del CSV al iniciar el servicio
     */
    @PostConstruct
    public void init() {
        try {
            loadProductsFromCSV();
        } catch (IOException e) {
            System.err.println("Error al cargar los productos: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Carga los productos desde el archivo CSV
     */
    private void loadProductsFromCSV() throws IOException {
        Resource resource = new ClassPathResource("static/data/home appliance skus lowes.csv");
        InputStream inputStream = resource.getInputStream();
        
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            // Saltamos la cabecera
            br.readLine();
            
            while ((line = br.readLine()) != null) {
                try {
                    String[] data = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                    
                    // Extraemos los datos que necesitamos (SKU, Price_Retail, Price_Current, Product_Name, Category)
                    String sku = data[6].trim();
                    double priceRetail = parseDouble(data[9]);
                    double priceCurrent = parseDouble(data[10]);
                    String productName = data[18].replace("\"", "").trim();
                    String category = data[0].replace("\"", "").trim();
                    
                    Product product = new Product(sku, priceRetail, priceCurrent, productName, category);
                    productTree.insert(product);
                    allProducts.add(product);
                } catch (Exception e) {
                    // Saltamos líneas con errores y continuamos con la siguiente
                    System.err.println("Error procesando línea: " + line);
                    System.err.println("Error: " + e.getMessage());
                }
            }
        }
    }
    
    /**
     * Convierte un string a double, manejando casos especiales
     */
    private double parseDouble(String value) {
        if (value == null || value.trim().isEmpty()) {
            return 0.0;
        }
        return Double.parseDouble(value.trim());
    }
    
    /**
     * Busca un producto por su SKU
     */
    public Product findProductBySku(String sku) {
        // Creamos un producto "dummy" con el SKU para buscar
        Product searchKey = new Product();
        searchKey.setSku(sku);
        
        // Buscamos en el árbol
        return productTree.search(searchKey);
    }
    
    /**
     * Obtiene todos los productos, limitados a un número máximo
     */
    public List<Product> getAllProducts(int limit) {
        if (limit >= allProducts.size()) {
            return allProducts;
        } else {
            return allProducts.subList(0, limit);
        }
    }
}
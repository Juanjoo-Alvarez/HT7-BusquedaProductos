import React, { useState, useEffect } from 'react';
import SearchBar from './SearchBar';
import LoadingBar from './LoadingBar';
import ProductDetails from './ProductDetails';
import ProductList from './ProductList';
import { searchProduct, getAllProducts } from '../services/api';

const ProductSearch = () => {
  const [searchTerm, setSearchTerm] = useState('');
  const [product, setProduct] = useState(null);
  const [allProducts, setAllProducts] = useState([]);
  const [loading, setLoading] = useState(false);
  const [initialLoading, setInitialLoading] = useState(true);
  const [error, setError] = useState('');

  // Cargar todos los productos al iniciar
  useEffect(() => {
    loadAllProducts();
  }, []);

  const loadAllProducts = async () => {
    try {
      setInitialLoading(true);
      const products = await getAllProducts();
      setAllProducts(products);
    } catch (err) {
      console.error('Error al cargar productos:', err);
      setError('Error al cargar la lista de productos');
    } finally {
      setInitialLoading(false);
    }
  };

  const handleSearch = async () => {
    if (!searchTerm) {
      setProduct(null);
      return;
    }
    
    setLoading(true);
    setError('');
    
    try {
      const result = await searchProduct(searchTerm);
      setProduct(result);
    } catch (err) {
      if (err.response && err.response.status === 404) {
        setError('Producto no encontrado');
      } else {
        setError('Error al buscar el producto. Intente de nuevo.');
      }
      setProduct(null);
    } finally {
      setLoading(false);
    }
  };

  const handleSelectProduct = (selectedProduct) => {
    setProduct(selectedProduct);
    setSearchTerm(selectedProduct.sku);
  };

  return (
    <div className="w-full max-w-4xl mx-auto">
      {/* Header con estilo Frutiger Aero */}
      <div className="glass rounded-xl shadow-frutiger p-6 mb-8">
        <h1 className="text-3xl font-bold text-center text-frutiger-gradient">
          Buscador de Productos
        </h1>
        <p className="text-center text-gray-600 mt-2">
          Encuentra el mejor precio para productos de Lowes
        </p>
      </div>
      
      {/* Barra de búsqueda */}
      <div className="glass rounded-xl shadow-frutiger p-6 mb-8">
        <SearchBar 
          searchTerm={searchTerm} 
          setSearchTerm={setSearchTerm} 
          handleSearch={handleSearch} 
        />
        
        {/* Barra de carga */}
        {loading && <LoadingBar />}
        
        {/* Mensaje de error */}
        {error && (
          <div className="mt-4 p-3 bg-red-100 text-red-700 rounded-lg">
            {error}
          </div>
        )}
      </div>
      
      {/* Detalles del producto seleccionado */}
      {product && (
        <div className="mb-8">
          <ProductDetails product={product} />
        </div>
      )}
      
      {/* Lista de productos */}
      {initialLoading ? (
        <div className="glass rounded-xl shadow-frutiger p-6 text-center">
          <LoadingBar />
          <p className="text-gray-500 mt-4">Cargando productos...</p>
        </div>
      ) : (
        <ProductList products={allProducts} onSelectProduct={handleSelectProduct} />
      )}
    </div>
  );
};

export default ProductSearch;
import React from 'react';

const ProductList = ({ products, onSelectProduct }) => {
  if (!products || products.length === 0) {
    return (
      <div className="glass rounded-xl shadow-frutiger p-6 text-center">
        <p className="text-gray-500">No hay productos disponibles.</p>
      </div>
    );
  }

  return (
    <div className="glass rounded-xl shadow-frutiger overflow-hidden">
      <div className="bg-frutiger-gradient p-4 text-white">
        <h2 className="text-xl font-semibold">Productos Disponibles</h2>
        <p className="text-sm opacity-80">Mostrando {products.length} productos</p>
      </div>
      <div className="overflow-auto max-h-96">
        <table className="min-w-full">
          <thead className="bg-gray-50 sticky top-0">
            <tr>
              <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                Producto
              </th>
              <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                Categoría
              </th>
              <th className="px-6 py-3 text-right text-xs font-medium text-gray-500 uppercase tracking-wider">
                Precio
              </th>
              <th className="px-6 py-3 text-center text-xs font-medium text-gray-500 uppercase tracking-wider">
                Acción
              </th>
            </tr>
          </thead>
          <tbody className="bg-white bg-opacity-40 divide-y divide-gray-200">
            {products.map((product) => (
              <tr key={product.sku} className="hover:bg-blue-50 hover:bg-opacity-50 transition-colors">
                <td className="px-6 py-4">
                  <div className="text-sm font-medium text-gray-900">{product.productName}</div>
                  <div className="text-xs text-gray-500">SKU: {product.sku}</div>
                </td>
                <td className="px-6 py-4">
                  <div className="text-sm text-gray-500">{product.category}</div>
                </td>
                <td className="px-6 py-4 text-right">
                  <div className="text-sm font-medium text-green-600">${product.priceCurrent.toFixed(2)}</div>
                  {product.priceRetail > product.priceCurrent && (
                    <div className="text-xs text-gray-500 line-through">${product.priceRetail.toFixed(2)}</div>
                  )}
                </td>
                <td className="px-6 py-4 text-center">
                  <button 
                    onClick={() => onSelectProduct(product)}
                    className="bg-frutiger-gradient text-white text-xs px-3 py-1 rounded-full hover:shadow-md transition-all"
                  >
                    Ver detalles
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default ProductList;
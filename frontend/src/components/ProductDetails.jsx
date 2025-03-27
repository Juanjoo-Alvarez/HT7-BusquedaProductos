import React from 'react';

const ProductDetails = ({ product }) => {
  const savings = product.priceRetail - product.priceCurrent;
  const savingsPercentage = (savings / product.priceRetail) * 100;

  return (
    <div className="glass rounded-xl shadow-frutiger overflow-hidden">
      <div className="bg-frutiger-gradient p-4 text-white">
        <h2 className="text-xl font-semibold">{product.productName}</h2>
        <p className="text-sm opacity-80">SKU: {product.sku}</p>
      </div>
      <div className="p-6">
        <div className="mb-4">
          <p className="text-gray-500 text-sm">Categoría</p>
          <p className="font-medium">{product.category}</p>
        </div>
        <div className="flex flex-col sm:flex-row sm:justify-between gap-4">
          <div className="bg-white bg-opacity-40 rounded-lg p-3">
            <p className="text-gray-500 text-sm">Precio Original</p>
            <p className="font-medium line-through text-gray-600">${product.priceRetail.toFixed(2)}</p>
          </div>
          <div className="bg-white bg-opacity-40 rounded-lg p-3">
            <p className="text-gray-500 text-sm">Mejor Precio</p>
            <p className="font-bold text-2xl text-green-600">${product.priceCurrent.toFixed(2)}</p>
          </div>
        </div>
        {savings > 0 && (
          <div className="mt-4 pt-4 border-t border-gray-200">
            <div className="flex justify-between items-center">
              <p className="text-green-600 font-medium">
                ¡Ahorras ${savings.toFixed(2)}!
              </p>
              <div className="bg-green-100 text-green-800 font-bold px-3 py-1 rounded-full text-sm">
                {savingsPercentage.toFixed(1)}% OFF
              </div>
            </div>
          </div>
        )}
      </div>
    </div>
  );
};

export default ProductDetails;
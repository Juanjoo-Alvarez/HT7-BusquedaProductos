import React from 'react';
import ProductSearch from './components/ProductSearch';

function App() {
  return (
    <div className="min-h-screen bg-gradient-to-b from-blue-100 to-purple-100 p-4 sm:p-8">
      <div className="container mx-auto">
        <ProductSearch />
      </div>
    </div>
  );
}

export default App;
import React from 'react';

const SearchBar = ({ searchTerm, setSearchTerm, handleSearch }) => {
  const handleKeyPress = (e) => {
    if (e.key === 'Enter') {
      handleSearch();
    }
  };

  return (
    <div className="flex flex-col sm:flex-row space-y-2 sm:space-y-0 sm:space-x-2">
      <input
        type="text"
        value={searchTerm}
        onChange={(e) => setSearchTerm(e.target.value)}
        onKeyPress={handleKeyPress}
        placeholder="Ingresa el SKU del producto"
        className="flex-1 px-4 py-2 bg-white bg-opacity-60 rounded-lg border border-blue-200 focus:outline-none focus:ring-2 focus:ring-blue-400"
      />
      <button
        onClick={handleSearch}
        className="px-6 py-2 bg-frutiger-gradient text-white rounded-lg shadow-md hover:shadow-lg transition-all duration-200"
      >
        Buscar
      </button>
    </div>
  );
};

export default SearchBar;
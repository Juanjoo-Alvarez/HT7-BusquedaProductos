import React from 'react';

const LoadingBar = () => {
  return (
    <div className="mt-4">
      <div className="h-1 w-full bg-gray-200 rounded-full overflow-hidden">
        <div className="h-full bg-frutiger-gradient w-1/3 animate-pulse"></div>
      </div>
      <p className="text-center text-sm text-gray-500 mt-2">Buscando producto...</p>
    </div>
  );
};

export default LoadingBar;
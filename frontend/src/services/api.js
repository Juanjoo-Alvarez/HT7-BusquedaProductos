import axios from 'axios';

const API_URL = 'http://localhost:8080/api';

export const searchProduct = async (sku) => {
  const response = await axios.get(`${API_URL}/products/${sku}`);
  return response.data;
};

export const getAllProducts = async () => {
  const response = await axios.get(`${API_URL}/products`);
  return response.data;
};
import React from 'react';
import { useState, useEffect } from 'react';
import "./Main.css"
import ProductList from '../../Components/ProductList';

const Main = () => {
  const [products, setProducts] = useState([]);

const fetchProducts = () => {
  fetch(`/api/v1/products/`)
  .then((res) => res.json())
  .then((result) => {
    setProducts(result);
  })
  .catch((error) =>
  console.log(`An error occurred at fetching from /api/v1/products:${error}`)
);
}

  useEffect(() => {
    fetchProducts();
  }, []);

  return (
   <>
   <ProductList products={products}/>
   </>
  );
}

export default Main;

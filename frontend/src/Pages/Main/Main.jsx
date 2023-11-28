import React from 'react';
import { useState, useEffect } from 'react';

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
    <div className="product-list">
      <h2>All products</h2>
      <h3>{products.length}</h3>
      <ul>
        {products.map((item, index) => (
          <li key={index}>{item.title}</li>
        ))}
      </ul>
    </div>
  );
}

export default Main;

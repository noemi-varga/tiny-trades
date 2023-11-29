import React from "react";
import { useState, useEffect } from "react";
import { useParams } from "react-router";
import { useLocation } from "react-router-dom";

import "./Product.css";

const Product = () => {
const location = useLocation();
const { productType } = location.state;
  const { productId } = useParams();
  const [product, setProduct] = useState([]);

  const fetchProduct = () => {
    fetch(`/api/v1/${productType.toLowerCase()}/${productId}`)
      .then((res) => res.json())
      .then((result) => {
        setProduct(result);
      })
      .catch((error) =>
        console.log(
          `An error occurred at fetching from /api/v1/${productType.toLowerCase()}/${productId}: ${error}`
        )
      );
  };

  useEffect(() => {
    fetchProduct();
  }, [productType, productId]);

  return (
    <>
      <p>{product.title}</p>
      {product.gender && <p>Gender: {product.gender}</p>}
      {product.condition && <p>Condition: {product.condition}</p>}
      {product.ageGroup && <p>Age Group: {product.ageGroup}</p>}
      {product.description && <p>Description: {product.description}</p>}
      {product.tags && <p>Tags: {product.tags.join(', ')}</p>}
      {product.status && <p>Status: {product.status}</p>}
      {product.size && <p>Size: {product.size}</p>}
      {product.color && <p>Color: {product.color}</p>}
      {product.clothingCategory && <p>Clothing Category: {product.clothingCategory}</p>}
      {product.toyCategory && <p>Toy Category: {product.toyCategory}</p>}
    </>
  );
};

export default Product;

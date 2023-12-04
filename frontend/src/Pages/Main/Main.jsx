import React from "react";
import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import "./Main.css";
import ProductList from "../../Components/ProductList";
import WelcomeMessage from "../../Components/WelcomeMessage";

const Main = () => {
  const [products, setProducts] = useState([]);
  const navigate = useNavigate();

  const handleAddItemClick = () => {
    navigate("/product/new");
  };

  const fetchProducts = () => {
    fetch(`/api/v1/products`)
      .then((res) => res.json())
      .then((result) => {
        setProducts(result);
      })
      .catch((error) =>
        console.log(
          `An error occurred at fetching from /api/v1/products:${error}`
        )
      );
  };

  useEffect(() => {
    fetchProducts();
  }, []);

  return (
    <div className="main-page">
      <WelcomeMessage />
      <button className="upload-button" onClick={() => handleAddItemClick()}>
        + Add Your Item
      </button>
      <ProductList products={products} />
    </div>
  );
};

export default Main;

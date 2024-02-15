import React, { useState, useEffect } from "react";
import "./ProductUpload.css";
import ProductForm from "../../Components/ProductForm";
import UploadSuccessMessage from "../../Components/UploadSuccessMessage";

const ProductUpload = () => {
  const [productType, setProductType] = useState("clothing");
  const [formData, setFormData] = useState({});
  const [tags, setTags] = useState([]);
  const [imageLinks, setImageLinks] = useState([]);
  const [uploadSuccess, setUploadSuccess] = useState(false);
  const [uploadedProduct, setUploadedProduct] = useState({});

  const handleChangeProductType = (e) => {
    setProductType(e.target.value);
    setFormData({});
  };

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleAddTag = () => {
    setTags([...tags, ""]);
  };

  const handleTagChange = (index, newValue) => {
    const newTags = [...tags];
    newTags[index] = newValue;
    setTags(newTags);
  };

  const handleRemoveTag = (index) => {
    setTags(tags.filter((_, idx) => idx !== index));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const userId = 1; // TODO Fetch the userId

    const data = {
      ...formData,
      tags: tags.filter((tag) => tag.trim() !== ""),
      imageLinks: imageLinks.filter((link) => link.trim() !== ""),
    };

    console.log(data);

    const endpoint = `/api/v1/${productType}/users/${userId}`;

    try {
      const response = await fetch(endpoint, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(data),
      });

      if (response.ok) {
        const result = await response.json();
        console.log("Submission successful:", result);
        setUploadSuccess(true);
        setUploadedProduct(result);
      } else {
        console.error("Submission failed:", response.statusText);
      }
    } catch (error) {
      console.error("Error submitting form:", error);
    }
  };

  const handleAddImageLink = () => {
    setImageLinks([...imageLinks, ""]);
  };

  const handleImageLinkChange = (index, newValue) => {
    const newImageLinks = [...imageLinks];
    newImageLinks[index] = newValue;
    setImageLinks(newImageLinks);
  };

  const handleRemoveImageLink = (index) => {
    setImageLinks(imageLinks.filter((_, idx) => idx !== index));
  };

  return (
    <>
      {!uploadSuccess ? (
        <div className="product-upload-container">
          <h2>Upload Product</h2>

          <div className="product-type-selector">
            <label>
              <input
                type="radio"
                value="clothing"
                checked={productType === "clothing"}
                onChange={handleChangeProductType}
              />{" "}
              Clothing
            </label>

            <label>
              <input
                type="radio"
                value="toy"
                checked={productType === "toy"}
                onChange={handleChangeProductType}
              />{" "}
              Toy
            </label>
          </div>

          <ProductForm
            productType={productType}
            onChange={handleChange}
            onSubmit={handleSubmit}
            addImageLink={handleAddImageLink}
            changeImageLink={handleImageLinkChange}
            removeImageLink={handleRemoveImageLink}
            imageLinks={imageLinks}
            tags={tags}
            addTag={handleAddTag}
            changeTag={handleTagChange}
            removeTag={handleRemoveTag}
          />
        </div>
      ) : (
        <UploadSuccessMessage
          productName={uploadedProduct.name}
          productId={uploadedProduct.id}
        />
      )}
    </>
  );
};

export default ProductUpload;

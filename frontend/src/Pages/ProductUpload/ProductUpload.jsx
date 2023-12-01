import React, { useState, useEffect } from "react";

const ProductUpload = () => {
  const [productType, setProductType] = useState("clothing");
  const [formData, setFormData] = useState({});
  const [tags, setTags] = useState([]);
  const [imageLinks, setImageLinks] = useState([]);

  const handleChangeProductType = (e) => {
    setProductType(e.target.value);
    setFormData({});
  };

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  useEffect(() => {
    console.log(formData);
  }, [formData]);

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
    const endpoint = `/api/v1/users/${userId}/${productType}`;
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

      <form onSubmit={handleSubmit}>
        <input
          type="text"
          name="title"
          placeholder="Title"
          onChange={handleChange}
        />

        <input
          type="text"
          name="gender"
          placeholder="Gender"
          onChange={handleChange}
        />

        <input
          type="text"
          name="condition"
          placeholder="Condition"
          onChange={handleChange}
        />

        <input
          type="text"
          name="ageGroup"
          placeholder="Age Group"
          onChange={handleChange}
        />

        <textarea
          name="description"
          placeholder="Description"
          onChange={handleChange}
        ></textarea>

        {productType === "clothing" && (
          <>
            <input
              type="text"
              name="size"
              placeholder="Size"
              onChange={handleChange}
            />
            <input
              type="text"
              name="color"
              placeholder="Color"
              onChange={handleChange}
            />
            <input
              type="text"
              name="clothingCategory"
              placeholder="Clothing Category"
              onChange={handleChange}
            />
          </>
        )}

        {productType === "toy" && (
          <>
            <input
              type="text"
              name="toyCategory"
              placeholder="Toy Category"
              onChange={handleChange}
            />
          </>
        )}

        <div className="image-links-section">
          <label>Image Links:</label>
          {imageLinks.map((link, index) => (
            <div key={index} className="image-link-item">
              <input
                type="text"
                value={link}
                placeholder={`Image link #${index + 1}`}
                onChange={(e) => handleImageLinkChange(index, e.target.value)}
              />
              <button
                type="button"
                onClick={() => handleRemoveImageLink(index)}
              >
                Remove
              </button>
            </div>
          ))}
          <button type="button" onClick={handleAddImageLink}>
            Add Image Link
          </button>
        </div>

        <div className="tags-section">
          {tags.map((tag, index) => (
            <div key={index} className="tag-item">
              <input
                type="text"
                value={tag}
                onChange={(e) => handleTagChange(index, e.target.value)}
              />
              <button onClick={() => handleRemoveTag(index)}>Remove</button>
            </div>
          ))}
          <button onClick={handleAddTag}>Add Tag</button>
        </div>

        <button type="submit">Upload</button>
      </form>
    </div>
  );
};

export default ProductUpload;

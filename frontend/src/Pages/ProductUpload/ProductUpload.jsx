import React, { useState, useEffect } from "react";
import "./ProductUpload.css";

const ProductUpload = () => {
  const [productType, setProductType] = useState("clothing");
  const [ageGroups, setAgeGroups] = useState([]);
  const [clothingCategories, setClothingCategories] = useState([]);
  const [clothingColors, setClothingColors] = useState([]);
  const [clothingSizes, setClothingSizes] = useState([]);
  const [conditionTypes, setConditionTypes] = useState([]);
  const [genders, setGenders] = useState([]);
  const [toyCategories, setToyCategories] = useState([]);
  const [formData, setFormData] = useState({});
  const [tags, setTags] = useState([]);
  const [imageLinks, setImageLinks] = useState([]);

  const fetchEnums = async () => {
    try {
      const responses = await Promise.all([
        fetch("/api/v1/enums/ageGroup"),
        fetch("/api/v1/enums/clothingCategory"),
        fetch("/api/v1/enums/clothingColor"),
        fetch("/api/v1/enums/clothingSize"),
        fetch("/api/v1/enums/conditionType"),
        fetch("/api/v1/enums/gender"),
        fetch("/api/v1/enums/toyCategory"),
      ]);

      const data = await Promise.all(responses.map((res) => res.json()));

      setAgeGroups(data[0]);
      setClothingCategories(data[1]);
      setClothingColors(data[2]);
      setClothingSizes(data[3]);
      setConditionTypes(data[4]);
      setGenders(data[5]);
      setToyCategories(data[6]);
    } catch (error) {
      console.error("Error fetching enum data:", error);
    }
  };

  useEffect(() => {
    fetchEnums();
  }, []);

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
        tags: tags.filter(tag => tag.trim() !== ''),
        imageLinks: imageLinks.filter(link => link.trim() !== '')
      };

      console.log(data);

    const endpoint = `/api/v1/users/${userId}/${productType}`;

    try {
        const response = await fetch(endpoint, {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify(data)
        });
    
        if (response.ok) {
          const result = await response.json();
          console.log('Submission successful:', result);
        } else {
          console.error('Submission failed:', response.statusText);
        }
      } catch (error) {
        console.error('Error submitting form:', error);   
  };

}

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

        <select name="gender" onChange={handleChange}>
          <option value="">Select Gender</option>
          {genders.map((gender, index) => (
            <option key={index} value={gender}>
              {gender}
            </option>
          ))}
        </select>

        <select name="condition" onChange={handleChange}>
          <option value="">Select Condition</option>
          {conditionTypes.map((condition, index) => (
            <option key={index} value={condition}>
              {condition}
            </option>
          ))}
        </select>

        <select name="ageGroup" onChange={handleChange}>
          <option value="">Select Age Group</option>
          {ageGroups.map((ageGroup, index) => (
            <option key={index} value={ageGroup}>
              {ageGroup}
            </option>
          ))}
        </select>

        <textarea
          name="description"
          placeholder="Description"
          onChange={handleChange}
        ></textarea>

        {productType === "clothing" && (
          <>

            <select name="size" onChange={handleChange}>
              <option value="">Select Size</option>
              {clothingSizes.map((size, index) => (
                <option key={index} value={size}>
                  {size}
                </option>
              ))}
            </select>

            <select name="color" onChange={handleChange}>
              <option value="">Select Color</option>
              {clothingColors.map((color, index) => (
                <option key={index} value={color}>
                  {color}
                </option>
              ))}
            </select>

            <select name="clothingCategory" onChange={handleChange}>
              <option value="">Select Category</option>
              {clothingCategories.map((category, index) => (
                <option key={index} value={category}>
                  {category}
                </option>
              ))}
            </select>
          </>
        )}

        {productType === "toy" && (
          <>
            <select name="toyCategory" onChange={handleChange}>
              <option value="">Select Toy Category</option>
              {toyCategories.map((category, index) => (
                <option key={index} value={category}>
                  {category}
                </option>
              ))}
            </select>
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
                X
              </button>
            </div>
          ))}
          <button type="button" onClick={handleAddImageLink}>
            Add Image Link
          </button>
        </div>

        <div className="tags-section">
        <label>Tags:</label>
          {tags.map((tag, index) => (
            <div key={index} className="tag-item">
              <input
                type="text"
                value={tag}
                onChange={(e) => handleTagChange(index, e.target.value)}
              />
              <button type="button" onClick={() => handleRemoveTag(index)}>X</button>
            </div>
          ))}
          <button type="button" onClick={handleAddTag}>Add Tag</button>
        </div>

        <button type="submit">Upload</button>
      </form>
    </div>
  );
};

export default ProductUpload;

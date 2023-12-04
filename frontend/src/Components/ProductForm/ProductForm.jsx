import "./ProductForm.css";
import { useState, useEffect } from "react";

const ProductForm = ({
  productType,
  onChange,
  onSubmit,
  addImageLink,
  changeImageLink,
  removeImageLink,
  imageLinks,
  tags,
  addTag,
  changeTag,
  removeTag,
}) => {
  const [ageGroups, setAgeGroups] = useState([]);
  const [clothingCategories, setClothingCategories] = useState([]);
  const [clothingColors, setClothingColors] = useState([]);
  const [clothingSizes, setClothingSizes] = useState([]);
  const [conditionTypes, setConditionTypes] = useState([]);
  const [genders, setGenders] = useState([]);
  const [toyCategories, setToyCategories] = useState([]);

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

  return (
    <form onSubmit={onSubmit}>
      <input type="text" name="title" placeholder="Title" onChange={onChange} />

      <select name="gender" onChange={onChange}>
        <option value="">Select Gender</option>
        {genders.map((gender, index) => (
          <option key={index} value={gender}>
            {gender}
          </option>
        ))}
      </select>

      <select name="condition" onChange={onChange}>
        <option value="">Select Condition</option>
        {conditionTypes.map((condition, index) => (
          <option key={index} value={condition}>
            {condition}
          </option>
        ))}
      </select>

      <select name="ageGroup" onChange={onChange}>
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
        onChange={onChange}
      ></textarea>

      {productType === "clothing" && (
        <>
          <select name="size" onChange={onChange}>
            <option value="">Select Size</option>
            {clothingSizes.map((size, index) => (
              <option key={index} value={size}>
                {size}
              </option>
            ))}
          </select>

          <select name="color" onChange={onChange}>
            <option value="">Select Color</option>
            {clothingColors.map((color, index) => (
              <option key={index} value={color}>
                {color}
              </option>
            ))}
          </select>

          <select name="clothingCategory" onChange={onChange}>
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
          <select name="toyCategory" onChange={onChange}>
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
              onChange={(e) => changeImageLink(index, e.target.value)}
            />
            <button type="button" onClick={() => removeImageLink(index)}>
              X
            </button>
          </div>
        ))}
        <button type="button" onClick={addImageLink}>
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
              onChange={(e) => changeTag(index, e.target.value)}
            />
            <button type="button" onClick={() => removeTag(index)}>
              X
            </button>
          </div>
        ))}
        <button type="button" onClick={addTag}>
          Add Tag
        </button>
      </div>

      <button type="submit">Upload</button>
    </form>
  );
};

export default ProductForm;

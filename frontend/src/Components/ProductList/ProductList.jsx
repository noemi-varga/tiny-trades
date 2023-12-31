import ProductItem from "../ProductItem";
import "./ProductList.css"
import { useNavigate } from "react-router-dom";

const ProductList = ({ products }) => {

    const navigate = useNavigate();

    const handleRedirection = (id, type) => {
      navigate(`/product/${id}`, { state: { productType: type } });
    };

    return (
        <div className="product-list">
            <div className="product-list-header">
                <h2>All Products</h2>
                <h4>{products.length} products total</h4>
            </div>
            <div className="product-items">
                {products.map((item, index) => (
                    <ProductItem key={index} item={item} onOpen={handleRedirection}/>
                ))}
            </div>
        </div>
    );

}

export default ProductList;
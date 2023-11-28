import ProductItem from "../ProductItem";
import "./ProductList.css"

const ProductList = ({ products }) => {

    return (
        <div className="product-list">
        <h2>All products</h2>
        <h4>{products.length} products total</h4>
          {products.map((item, index) => (
            <ProductItem key={index} item={item}/>
          ))}
      </div>
    );

}

export default ProductList;
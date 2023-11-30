import "./ProductItem.css"

const ProductItem = ({ item, onOpen }) => {
    return (
        <div className="product-item">
            <img src={item.firstImageLink} alt={item.title} className="product-image"/>
            <div className="product-details">
                <h3>{item.title}</h3>
                <p>Type: {item.productType}</p>
                <button onClick={() => onOpen(item.id, item.productType)}>Open</button>
            </div>
        </div>
    );
}


export default ProductItem;
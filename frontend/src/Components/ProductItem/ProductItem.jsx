const ProductItem = ({ item, onOpen }) => {

    return (
        <div className="product-item">
            <p>{item.title}</p>
            <p>Type: {item.productType}</p>
            <button onClick={() => onOpen(item.id, item.productType)}>Open</button>
      </div>
    );

}

export default ProductItem;
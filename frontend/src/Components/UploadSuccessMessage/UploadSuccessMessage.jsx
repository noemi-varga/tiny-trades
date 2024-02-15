import "./UploadSuccessMessage.css"

const UploadSuccessMessage = ({ productName, productId }) => {
  return (
    <div className="upload-success-container">
      <h2>Product Upload Successful!</h2>
      <p>Your product <strong>{productName}</strong> has been successfully uploaded.</p>
      <p>Would you like to upload another one?</p>
      <div>
        <button onClick={() => window.location.reload()}>Upload Another Product</button>
      </div>
    </div>
  );
};


export default UploadSuccessMessage;
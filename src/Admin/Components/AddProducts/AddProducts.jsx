import React, { useEffect, useState } from 'react'
import './addproduct.css'
import upload_area from '../../assets/file.png'
import { json, useActionData, useLocation } from 'react-router-dom'
import axios from 'axios'

function AddProducts (props)  {
     // const { productId } = props;
     const [image, setImage] = useState(false);
     const [product, setProduct] = useState({
       name: "",
       description: "",
       price: "",
       qty: "",
       cat_id: "",
       image: "test.png",
     });
   
     const imageHandler = (e) => {
       setImage(e.target.files[0]);
     };
   
     useEffect(() => {
     //   console.log("productID" + props.product.name);
     }, []);
   

    const addProduct =()=>
    {
     console.log("inside addProduct");
     const formData = new FormData();
     formData.append('name', product.name);
     formData.append('description', product.description);
     formData.append('price', product.price);
     formData.append('qty', product.qty);
     formData.append('cat_id', product.cat_id);
     formData.append('image',image)
       axios.post("http://127.0.0.1:9898/product/addProduct",formData,{headers : {"Content-Type": 'multipart/form-data'}}).then((result)=>{
       
       console.log(result.data.status);
          if(result.data.status==="success")
          {
               alert('product added successfully');
          }
          else{
               alert('failed');
          }
     })
       }

      
    


    const OnTextChanged =(args)=>{
     //this.state.user represents user to be added in the database
     //here we need to update this.state.user with values from 
     //textboxes ..so we will use this.setState

     var copyOfProduct = {...product};
     copyOfProduct[args.target.name] = args.target.value;
     setProduct(copyOfProduct);

 }
  return (
    // <div>
        <div className='add-product'>
           <div className='addproduct-itemfield'>
                <p>Product Title</p>
                <input onChange={OnTextChanged} type="text" name='name' placeholder='Type ' />
           </div>           
           <div className='addproduct-itemfield'>
                <p>Product Description</p>
                <input onChange={OnTextChanged} type="text" name='description' placeholder='Description ' />
           </div>           
        
        <div className='addproduct-price'>
           <div className='addproduct-itemfield'>
                <p>Product Price</p>
                <input onChange={OnTextChanged}  type="text" name='price' placeholder='old Price' />
           </div>   
           <div className='addproduct-itemfield'>
                <p>Product Quantity</p>
                <input onChange={OnTextChanged} type="text" name='qty' placeholder='qty' />
           </div>           
        </div>
        <div className='addproduct-itemfield'>
                <p>Product Category</p>
                <select onChange={OnTextChanged} name='cat_id' className='add-product-selectors'>
                    <option value="3">Fatilizer</option>
                    <option value="1">Vegetables</option>
                    <option value="2">pesticide</option>
                </select>
           </div>   
           <div className='addproduct-itemfield'>
                <label htmlFor='file-input'>
                    <img src={image?URL.createObjectURL(image):upload_area} className='addproduct-thumnail-image' />
                </label>
                <input onChange={imageHandler}  type="file" name='image' id='file-input' readOnly={true} hidden />
           </div> 
           <button className='addproduct-btn' onClick={addProduct} >ADD</button>
    </div>
    // </div>

  )
}

export default AddProducts
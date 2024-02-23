import React, { useState } from 'react'
import './addproduct.css'
import upload_area from '../../assets/file.png'
import { useActionData } from 'react-router-dom'

const AddProducts = () => {
    const [image,setImage] = useState(false);
    const imageHandler = (e) =>{
        setImage(e.target.files[0]);
    }
  return (
    // <div>
        <div className='add-product'>
           <div className='addproduct-itemfield'>
                <p>Product Title</p>
                <input type="text" name='name' placeholder='Type ' />
           </div>           
           <div className='addproduct-itemfield'>
                <p>Product Description</p>
                <input type="text" name='description' placeholder='Description ' />
           </div>           
        
        <div className='addproduct-price'>
           <div className='addproduct-itemfield'>
                <p>Product Price</p>
                <input type="text" name='old_price' placeholder='old Price' />
           </div>   
           <div className='addproduct-itemfield'>
                <p>Offer Price</p>
                <input type="text" name='new_price' placeholder='New Price' />
           </div>           
        </div>
        <div className='addproduct-itemfield'>
                <p>Product Category</p>
                <select name='category' className='add-product-selectors'>
                    <option value="Fatilizer">Fatilizer</option>
                    <option value="Vegetables">Vegetables</option>
                    <option value="Fruits">Fruits</option>
                </select>
           </div>   
           <div className='addproduct-itemfield'>
                <label htmlFor='file-input'>
                    <img src={image?URL.createObjectURL(image):upload_area} className='addproduct-thumnail-image' />
                </label>
                <input onChange={imageHandler}  type="file" name='image' id='file-input' hidden />
           </div> 
           <button className='addproduct-btn' >ADD</button>
    </div>
    // </div>

  )
}

export default AddProducts
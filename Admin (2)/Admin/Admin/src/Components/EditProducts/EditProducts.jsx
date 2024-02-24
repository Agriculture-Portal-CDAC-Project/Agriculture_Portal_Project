import React, { useEffect, useState } from 'react'
import './EditProducts.css'
import upload_area from '../../assets/file.png'
import { Navigate, json, useActionData, useLocation, useNavigate } from 'react-router-dom'
import axios from 'axios'

function EditProducts (props)  {
     // const { productId } = props;
     const id = sessionStorage.getItem('productId');
     const [image, setImage] = useState(false);
     const [product, setProduct] = useState({
       name: "",
       description: "",
       price: "",
       qty: "",
       cat_id: "",
       image: "",
     });

     const navigate = useNavigate()
   
     const imageHandler = (e) => {
       setImage(e.target.files[0]);
       setImage(file);
       setProduct((prevProduct) => ({ ...prevProduct, image: file.name }));
 
     };
   
     useEffect(() => {
        getProduct();
     //   console.log("productID" + props.product.name);
     }, []);
   
     const getProduct=()=>
     {
        
        axios.get("http://127.0.0.1:9898/product/findById/"+id).then((result)=>
        {
            console.log(result.data.data[0]);
            console.log(result.status)
            if(result.data.data[0].id == id)
            {
                setProduct(result.data.data[0]);
               //  setImage(result.data.data[0].image);
                console.log(result.data.data[0].image)
               //  console.log(result.data.data[0].image.split("-")[0]);
                console.log("image name :"+image)
               
            }
        })
     }

    const editProduct =()=>
    {
     console.log("inside addProduct");
     // const formData = new FormData();
     // formData.append('name', product.name);
     // formData.append('description', product.description);
     // formData.append('price', product.price);
     // formData.append('qty', product.qty);
     // formData.append('cat_id', product.cat_id);
     
       axios.put("http://127.0.0.1:9898/product/Updateproduct/"+id , product).then((result)=>{
       
       console.log( "status:"+ result.status);
          if(result.status=== 201)
          {
               alert('product added successfully');
               navigate('/listProducts')
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
                <input onChange={OnTextChanged} value={product.name} type="text" name='name' placeholder='Type ' readOnly={true} />
           </div>           
           <div className='addproduct-itemfield'>
                <p>Product Description</p>
                <input onChange={OnTextChanged} value={product.description} type="text" name='description' placeholder='Description ' />
           </div>           
        
        <div className='addproduct-price'>
           <div className='addproduct-itemfield'>
                <p>Product Price</p>
                <input onChange={OnTextChanged} value={product.price} type="text" name='price' placeholder='old Price' />
           </div>   
           <div className='addproduct-itemfield'>
                <p>Product Quantity</p>
                <input onChange={OnTextChanged} value={product.qty} type="text" name='qty' placeholder='qty' />
           </div>           
        </div>
        <div className='addproduct-itemfield'>
                <p>Product Category</p>
                {/* <select onChange={OnTextChanged} value={product.cat_id} readOnly={true} name='cat_id' className='add-product-selectors' >
                    <option value="3">Fatilizer</option>
                    <option value="1">Vegetables</option>
                    <option value="2">pesticide</option>
                </select> */}
                <select onChange={OnTextChanged} value={product.cat_id} disabled={true} name='cat_id' className='add-product-selectors' >
                        <option value="3">Fatilizer</option>
                        <option value="1">Vegetables</option>
                        <option value="2">pesticide</option>
                </select>
           </div>   
           <div className='addproduct-itemfield'>
                <label htmlFor='file-input'>
                    <img src={"http://127.0.0.1.:9898/product/images/" + product.image} className='addproduct-thumnail-image' />
                </label>
                {/* <input onChange={imageHandler} type = "file" name ='image'  id='file-input' hidden/> */}
           </div> 
           <button className='addproduct-btn' onClick={editProduct} >UPDATE</button>
    </div>
    // </div>

  )
}

export default EditProducts
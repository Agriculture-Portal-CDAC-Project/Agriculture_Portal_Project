import React, { useEffect, useState } from 'react'
import './listProducts.css'
import axios from 'axios';
// import { Button } from 'bootstrap';
// import Button from 'react-bootstrap/Button';
import 'bootstrap/dist/css/bootstrap.min.css';
import { Link } from 'react-router-dom';
import AddProducts from '../AddProducts/AddProducts';
import EditProducts from '../EditProducts/EditProducts';

const ListProducts = () => {

  //const [image,setImage] = useState(false);
  const[Products , setProducts] = useState([

    // {"name" : 'tomato', "description" : 'fresh', "price":90, "qty" :20, "cat_id":1, "image":'tomato.png' },
    // {name : 'tomato', description : 'fresh', price:90, qty :20, cat_id:1, image:'tomato.png' },
    // {name : 'tomato', description : 'fresh', price:90, qty :20, cat_id:1, image:'tomato.png' }
  ]);
    const[product , setProduct] = useState({id:'',name:'',description:'',price:'', qty:'',cat_id:'', image:"test.png"});
    const [showAddProduct, setShowAddProduct] = useState(false);

    useEffect(()=>{
      getAllProduct()
    },[]);

    const getAllProduct = (product)=>
    {
      axios.get("http://127.0.0.1:9898/product/getAll").then((result)=>{
        console.log(result.data.data)
                 setProducts(result.data.data);
             
      })
    }

    const removeProduct =(id)=>
    {
      console.log("inside removeProduct")
      axios.delete("http://127.0.0.1:9898/product/delete/"+id).then((result)=>
      {
        alert("product deleted from database");
        getAllProduct();
      })
    }
  return (
    <div className='list-product'>
        <h1>All Products List</h1>
        <div className='listproduct-format-main'>
            <p>Name</p>
            <p>Description</p>
            <p style={{textAlign:'center'}}>Price</p>
            <p>Quantity</p>
            <p>Category_id</p>
            <p style={{textAlign:'center'}}>Image</p>
            <p>Remove / Edit</p>
          
        </div>
        <div className='listproduct-format-main'>
        
          {
                Products.map(product=>{
               return <>
               <p>{product.name}</p>
               <p>{product.description}</p>
               <p style={{textAlign:'center'}}>{product.price}</p>
               <p>{product.qty}</p>
               {product.cat_id === 1 && <p>Vegetables</p>}
               {product.cat_id === 2 && <p>Pesticide</p>}
               {product.cat_id === 3 && <p>Firtilizer</p>}
               
               <p style={{textAlign:'center'}}><img style={{width:150,height:150}} src={"http://127.0.0.1.:9898/product/images/" + product.image}></img></p>
              
              <p><button className='btn btn-primary' onClick={()=>{
                removeProduct(product.id)
               }}>remove</button> 
                {" "}
                <Link
  to={{
    pathname: '/editproduct',
    state: { productId: product.id }
  }}
  className="btn btn-primary"
  onClick={() => {
    setShowAddProduct(true);
    sessionStorage.setItem('productId', product.id);

  }}
>
  Update
</Link>
{showAddProduct && <EditProducts productId={product.id} />}
               </p>
               
             
               </>
                 
                })
          }
             </div>
        
        <div className='listproduct-allproducts'>
            <hr/>
            
        </div>
    </div>
  )
}

export default ListProducts;
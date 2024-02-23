import React, { useEffect, useState } from 'react';
import './NewProducts.css';
import new_collection from '../Assets/new_collections'
import Item  from '../Item/Item';
import axios from 'axios';
const NewProducts = () => {

  const [allProducts,setProducts] =useState([]);
  const [product,setProduct] =useState( {id : 0, name: "", price:""});
  const [oldPrice,setOldPrice] =useState(200);


  useEffect(()=>
    {
        // var usernameFromSession = sessionStorage.getItem("username")
        // setusername(usernameFromSession);

        GetAll();
    }, [])

  const GetAll=()=>
  {
      axios.get("http://127.0.0.1:9898/product/getAll").then((result)=>
                          {
                            console.log(result);
                            console.log(product.name);
                             setProducts(result.data.data);
                          });
  }

  return (
    // <div className='new-products'>
    //     <h1>New Products</h1>
    //     <hr></hr>
    //     <div className='products'>
    //         {new_collection.map((item,i)=>{
    //             return <Item key={i} id={item.id} name={item.name} image={item.image} new_price={item.new_price} old_price={item.old_price}/>

    //         })}
    //     </div>
    // </div>

    <>
       <div className='new-products'>
        <h1>New Products</h1>
        <hr></hr>
        <div className='products'>
            {allProducts.map(product=>{
              
                return <Item key={product.id} id={product.id} name={product.name} image={product.image} price={product.price} old_price={oldPrice.old_price}/>

            })}
        </div>
    </div>
    </>


  )
}

export default NewProducts
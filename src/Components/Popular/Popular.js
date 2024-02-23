import React, { useEffect, useState } from 'react'
import './Popular.css'
import data_product from '../Assets/data';
import Item from '../Item/Item';
import axios from 'axios';

const Popular = () => {
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
    <div className='popular'>
        <h1>Popular Products</h1>
        <hr />
        <div className='popular-item'>
            {allProducts.map((product)=>{ // fetch items form database
                return <Item key={product.id} id={product.id} name={product.name} image={product.image} price={product.new_price} old_price={oldPrice.old_price}/>
            })}
        </div>
    </div>
  )
}

export default Popular
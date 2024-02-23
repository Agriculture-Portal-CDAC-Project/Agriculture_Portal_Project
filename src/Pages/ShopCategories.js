import React, { useContext, useEffect, useState } from 'react'
import './CSS/ShopCategories.css';
import { ShopContext } from '../Context/ShopContext';
import dropdown_icon from '../Components/Assets/dropdown_icon.png'
import Item from '../Components/Item/Item';
import axios from 'axios';

const ShopCategories = (props) => {
  console.log("frontend category "+props.category);
  const {all_product} = useContext(ShopContext)
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
                             console.log("db category "+result.data.data.cat_id);
                          });
  }

  return (
    <div className='shop-category'>
     <img className='shopcategory-banner' src={props.banner} alt='' />
      <div className='shopcategories-indexsort'>
          <p>
            <span>Showing 1-12</span> out of 36 Products
          </p>
          <div className='shopcategory-sort'>
              sort by <img src={dropdown_icon} alt='' />
          </div>
      </div>

      <div className='shopcategory-products'>
      {allProducts.map((product) => {
        
    if (props.category === "Firtilizer" && product.cat_id ===3) {
      return <Item key={product.id} id={product.id} name={product.name} image={product.image} price={product.price} oldPrice={oldPrice}/>

    } 
    else if(props.category === "Vegetables" && product.cat_id ===1)
    {
      return <Item key={product.id} id={product.id} name={product.name} image={product.image} price={product.price} oldPrice={oldPrice}/>

    }
    else if(props.category === "Pesticides" && product.cat_id ===2)
    {
      return <Item key={product.id} id={product.id} name={product.name} image={product.image} price={product.price} oldPrice={oldPrice}/>

    }
    
    else {
        return null;
    }
})}
 
      </div>
      <div className='shopcategory-loadmore'>
          Explore More
      </div>
    </div>
  )
}

export default ShopCategories
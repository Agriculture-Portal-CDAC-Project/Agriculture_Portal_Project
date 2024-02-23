import React, { useContext, useEffect, useState } from 'react';
import { ShopContext } from "../Context/ShopContext";
import { useParams } from 'react-router-dom';
import Breadcrums from '../Components/Breadcrums/Breadcrums';
import ProductDisplay from '../Components/ProductDisplay/productDisplay'; 
import DescriptionBox from '../Components/DescriptionBox/descriptionBox';
import RelatedProducts from '../Components/RelatedProducts/RelatedProducts'
import axios from 'axios';

const Products = () => {
     const { allproduct }  = useContext(ShopContext);
    // const [allProducts,setProducts] =useState([]);
  // const [product,setProduct] =useState( {id : 0, name: "", price:""});
//   const [oldPrice,setOldPrice] =useState(200);
  const { productId } = useParams();

  useEffect(()=>
    {
        // var usernameFromSession = sessionStorage.getItem("username")
        // setusername(usernameFromSession);

        // GetAll();
    }, [])

  // const GetAll=()=>
  // {
  //     axios.get("http://127.0.0.1:9898/product/getAll").then((result)=>
  //                         {
  //                           console.log("in Products:::"+JSON.stringify(result.data.data));
  //                          // console.log(product.name);
  //                            setProducts(result.data.dat);
  //                            console.log("setProduct "+allProducts)
  //                         });
  // }

   
    const Products = allproduct.find((e) => e.id === Number(productId));
    console.log("products::"+Products)

    return (
        <div>
            <Breadcrums Products={Products} />
            <ProductDisplay Products={Products} /> 
            <DescriptionBox />
            <RelatedProducts />
        </div> 
    );
};

export default Products;

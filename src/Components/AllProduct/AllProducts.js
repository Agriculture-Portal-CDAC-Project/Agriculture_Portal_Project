// import { render } from "@testing-library/react";
// import axios from "axios";
// import { useEffect, useState } from "react";

// const AllProducts =()=>{


//     let allProd=[];
//    // const [allProducts,setProducts] =useState([]);
//     //const [product,setProduct] =useState( {id : 0, name: "", price:""});
  
  
//     useEffect(()=>
//       {
//           // var usernameFromSession = sessionStorage.getItem("username")
//           // setusername(usernameFromSession);
  
//           GetAll();
//       }, [])
  
//     const GetAll=()=>
//     {
//         axios.get("http://127.0.0.1:9898/product/getAll").then((result)=>
//                             {
//                               console.log("inside AllProducts"+result);
//                             //   console.log(product.name);
//                              //setProducts(result.data.data);
//                              allProd = result.data.data;
//                             //  allProd= [... allProducts]
//                             });
//     }

//    return allProd
//   }
  
//   export default AllProducts;
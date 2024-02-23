// import React, { createContext, useState } from 'react';
// import all_product from '../Components/Assets/all_product';

// export const ShopContext = createContext(null);

// // const [prod, setProd] = useState([]);
 
// const getDefaultCart = () => {
//     let cart = {};
//     all_product.forEach(product => {
//         cart[product.id] = 0;
//     });
//     return cart;
// }

// const ShopContextProvider = (props) => {
//     const [cartItems, setCartItems] = useState(getDefaultCart());

//     const addToCart = (itemID) => {
//         setCartItems((prev) => ({
//             ...prev, [itemID]: prev[itemID] + 1
//         }));
//     }

//     const removeFromCart = (itemID) => {
//         setCartItems((prev) => ({
//             ...prev, [itemID]: prev[itemID] - 1
//         }));
//     }
//     const getTotalCartAmount = () => {
//         let totalAmount = 0;
//         for (const item in cartItems) {
//             if (cartItems[item] > 0) {
//                 let itemInfo = all_product.find((product) => product.id === Number(item));
//                 totalAmount += itemInfo.new_price * cartItems[item];
//             }
//         }
//         return totalAmount;
//     }
    

//     const getTotalCartAmounts = () =>{
//         let totalItem = 0;
//         for(const item in cartItems){
//             if(cartItems[item]>0){
//                 totalItem += cartItems[item]
//             }
//         }
//         return totalItem;
//     }


//     console.log("all_product:", all_product); // Add this line

//     const ContextValue = {getTotalCartAmounts,getTotalCartAmount, all_product, cartItems, addToCart, removeFromCart };

//     return (
//         <ShopContext.Provider value={ContextValue}>
//             {props.children}
//         </ShopContext.Provider>
//     )
// }

// export default ShopContextProvider;



import React, { createContext, useEffect, useState } from 'react';
// import all_product from '../Components/Assets/all_product';
import axios from 'axios';
import ProductDisplay from '../Components/ProductDisplay/productDisplay';
import { useNavigate } from 'react-router-dom';

export const ShopContext = createContext(null);

// const [prod, setProd] = useState([]);
 
// const getDefaultCart = () => {
//     let cart = {};
//     all_product.forEach(product => {
//         cart[product.id] = 0;
//     });

//     return cart;
    // }
    



const ShopContextProvider = (props) => {
    const [cartItems, setCartItems] = useState([]);
    const [allproduct, setAllProduct] = useState([]); 
    const u_id = sessionStorage.getItem('u_id');
   
    const addToCart = (product) => {

        const [addProduct] = [{p_id:product.id, price:product.price, u_id : u_id, qty : 1, total:product.price }];
        axios.post("http://127.0.0.1:9898/cart/addCart",addProduct).then((result)=>
        {
            console.log(result.data.status)
          if(result.data.status=="success")
          {
            alert("added to cart");

          }
          else
          {
            alert("fail to add to cart!!!!")
          }
        });
         setCartItems(product)
        // setCartItems((prev) => ({
        //     ...prev, [product]: prev[product] + 1
        // }));
    }
    const fetchProducts = async () => {
        axios.get("http://127.0.0.1:9898/product/getAll").then((result)=>
        {
          console.log("result data : "+result);
        //   console.log(product.name);
           setAllProduct(result.data.data);

        //    let cart = {};
        //    allproduct.forEach(product => {
        //        cart[product.id] = 0;
        //    });
       
        //    setCartItems(cart);

           let cart = {};
           
       
           setCartItems(
                        allproduct.forEach(item => {
                        cart[item.id] = 0;
                 })
        );


        });
    };

    useEffect(() => {
        fetchProducts(); //Call the fetchProducts function when the component mounts
    }, []);

    // const getDefaultCart = () => {
        // let cart = {};
        // allproduct.forEach(product => {
        //     cart[product.id] = 0;
        // });
    
        //  setCartItems(cart);
       //}
        


    const removeFromCart = (itemID) => {

       
        const p_id = itemID;
        console.log(p_id)
        axios.delete("http://127.0.0.1:9898/cart/deleteCart/"+p_id+"/"+u_id).then((result)=>
        {
            console.log(result.data.status)
          if(result.data.status=="success")
          {
            alert("item removed");
          }
          else
          {
            alert("fail to remove item!!!!")
          }
        });
       
        // setCartItems((prev) => ({
        //     ...prev, [itemID]: prev[itemID] - 1
        // }));
    }
    const getTotalCartAmount = () => {
        let totalAmount = 0;
        for (const item in cartItems) {
            if (cartItems[item] > 0) {
                console.log("inside total amount if")
                let itemInfo = allproduct.find((product) => product.id === Number(item));
                totalAmount += itemInfo.price * cartItems[item];
            }
        }
        return totalAmount;
    }
    

    const getTotalCartAmounts = () =>{
        let totalItem = 0;
        for(const item in cartItems){
            if(cartItems[item]>0){
                totalItem += cartItems[item]
            }
        }
        return totalItem;
    }


    console.log("My all_product:", allproduct); // Add this line

    const ContextValue = {getTotalCartAmounts,getTotalCartAmount, allproduct, cartItems, addToCart, removeFromCart };

    return (
        <ShopContext.Provider value={ContextValue}>
            {props.children}
        </ShopContext.Provider>
    )
}

export default ShopContextProvider;

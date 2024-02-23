import React, { useContext, useEffect, useState } from 'react';
import './cartItems.css';
import { ShopContext } from '../../Context/ShopContext';
import remove_icon from '../Assets/cart_cross_icon.png';
import { toast } from 'react-toastify';
import axios from 'axios';

const CartItems = () => {
    const {getTotalCartAmount, allproduct, cartItems, removeFromCart } = useContext(ShopContext);
    const u_id = sessionStorage.getItem('u_id');
  
    const [cartProducts, setCartProducts] = useState([]);
    const placeOrder=()=>{
      axios.post("http://127.0.0.1:9898/order/"+u_id).then((result)=>
      {
        
        alert("order placed successfully!")

      });
      
    }

    useEffect(()=>
    {
        // var usernameFromSession = sessionStorage.getItem("username")
        // setusername(usernameFromSession);

        GetAll();
    }, [])

  const GetAll=()=>
  {
   
      axios.get("http://127.0.0.1:9898/cart/getCart/"+u_id).then((result)=>
                          {
                            console.log(result);
                            setCartProducts(result.data.data);
                          });
  }

  const total =()=>
  {
    var tt =0
    cartProducts.map((e)=>{
      tt = tt+(e.price * 1);
    })
    return tt;
  }

    return (
        <div className='cartItems'>
            <div className='cartitems-format-main'>
                <p>Products</p>
                <p>Title</p>
                <p>Price</p>
                <p>Quantity</p>
                <p>Total</p>
                <p>Remove</p>
            </div>
            <hr/>
            {cartProducts.map((e) => {
                // if(cartItems[e.id] > 0) {
                    return (
                        <div key={e.id}>
                            <div className='cartitems-format cartitems-format-main'>
                                <img src={"http://127.0.0.1.:9898/product/images"+"/"+e.image} alt='' className='carticon-product-icon' />
                                <p>{e.name}</p>
                                <p>₹{e.price}</p>
                                <button className='cartitems-quantity'>
                                    {/* {cartItems[e.id]} */}
                                    1
                                </button>
                                <p>₹{e.price * 1}</p>
                                <img className='cartitems-remove-icon' src={remove_icon} onClick={() => removeFromCart(e.p_id)} alt=''  />
                            </div>
                            <hr />
                        </div>
                    );
                // }
                // return null;
            })}
            
            <div className='cartitems-down'>
              <div className='cartitems-total'>
                <h1>Cart Totals</h1>
                <div>
                  <div className='cartitems-total-item'>
                    <p>Subtotal</p>
                    {/* <p>₹{getTotalCartAmount()}</p> */}
                    <p>{total()}</p>
                  </div>
                  <hr/>
                  <div className='cartitems-total-item'>
                    <p>Shipping Fee</p>
                    <p>Free</p>
                  </div>
                  <hr/>
                  <div className='cartitems-total-item'> 
                    <h3>Total</h3>
                    {/* <h3>₹{getTotalCartAmount()}</h3> */}
                    <h3>{total()}</h3>
                  </div>
                </div>
                <button onClick={placeOrder}>
                  PROCEED TO CHECKOUT
                </button>
              </div>
              <div className='cartitems-promocode'>
                <p>If you have a promo code, Enter it here.</p>
                <div className='cartitems-promobox'>
                    <input type='text' placeholder='promo code' />
                    <button>Submit</button>
                </div>
              </div>
            </div>
        </div>
    );
}

export default CartItems;

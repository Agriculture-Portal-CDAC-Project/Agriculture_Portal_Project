import React, { useContext } from 'react';
import './cartItems.css';
import { ShopContext } from '../../Context/ShopContext';
import remove_icon from '../Assets/cart_cross_icon.png';

const CartItems = () => {
    const { getTotalCartAmount, all_product, cartItems, removeFromCart } = useContext(ShopContext);

    // Calculate total cart amount
    const totalCartAmount = getTotalCartAmount();

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
            {all_product && all_product.map((product) => {
                const { id, image, name, new_price } = product;
                const quantity = cartItems[id] ? cartItems[id].quantity : 0;
                const totalPrice = new_price * quantity;

                if (quantity > 0) {
                    return (
                        <div key={id}>
                            <div className='cartitems-format cartitems-format-main'>
                                <img src={image} alt='' className='carticon-product-icon' />
                                <p>{name}</p>
                                <p>${new_price}</p>
                                <button className='cartitems-quantity'>
                                    {quantity}
                                </button>
                                <p>${totalPrice}</p>
                                <img className='cartitems-remove-icon' src={remove_icon} onClick={() => removeFromCart(id)} alt=''  />
                            </div>
                            <hr />
                        </div>
                    );
                }
                return null;
            })}
            <div className='cartitems-down'>
              <div className='cartitems-total'>
                <h1>Cart Totals</h1>
                <div>
                  <div className='cartitems-total-item'>
                    <p>Subtotal</p>
                    <p>${totalCartAmount}</p>
                  </div>
                  <hr/>
                  <div className='cartitems-total-item'>
                    <p>Shipping Fee</p>
                    <p>Free</p>
                  </div>
                  <hr/>
                  <div className='cartitems-total-item'> 
                    <h3>Total</h3>
                    <h3>${totalCartAmount}</h3>
                  </div>
                </div>
                <button>
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

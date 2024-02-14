import React, { createContext, useState } from 'react';
import all_product from '../Components/Assets/all_product';

export const ShopContext = createContext(null);

const getDefaultCart = () => {
    let cart = {};
    all_product.forEach(product => {
        cart[product.id] = { quantity: 0, price: product.new_price };
    });
    return cart;
}

const ShopContextProvider = (props) => {
    const [cartItems, setCartItems] = useState(getDefaultCart());

    const addToCart = (itemID, newPrice) => {
        setCartItems(prev => ({
            ...prev,
            [itemID]: {
                ...prev[itemID],
                quantity: prev[itemID].quantity + 1,
                price: newPrice
            }
        }));
    }

    const removeFromCart = (itemID) => {
        setCartItems(prev => ({
            ...prev,
            [itemID]: {
                ...prev[itemID],
                quantity: prev[itemID].quantity - 1
            }
        }));
    }

    const getTotalCartAmount = () => {
        let totalAmount = 0;
        for (const item in cartItems) {
            totalAmount += cartItems[item].quantity * cartItems[item].price;
        }
        return totalAmount;
    }

    const getTotalCartAmounts = () => {
        let totalItems = 0;
        for (const item in cartItems) {
            totalItems += cartItems[item].quantity;
        }
        return totalItems;
    }

    const ContextValue = {
        getTotalCartAmounts,
        getTotalCartAmount,
        all_product,
        cartItems,
        addToCart,
        removeFromCart
    };

    return (
        <ShopContext.Provider value={ContextValue}>
            {props.children}
        </ShopContext.Provider>
    );
}

export default ShopContextProvider;

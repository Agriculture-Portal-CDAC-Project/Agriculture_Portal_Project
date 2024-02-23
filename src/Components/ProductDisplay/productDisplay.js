import React, { useContext } from 'react';
import './productDisplay.css';
import star_icon from "../Assets/star_icon.png";
import star_dull_icon from "../Assets/star_dull_icon.png";
import { ShopContext } from '../../Context/ShopContext';


const ProductDisplay = (props) => {
    //console.log("props"+props.name);
    const { Products} = props;
    const { addToCart} = useContext(ShopContext);

    return (
        <div className='productdisplay'>
            <div className='productdisplay-left'>
                <div className='productdisplay-img-list'>
                    <img src={"http://127.0.0.1.:9898/product/images"+"/"+Products.image} alt='' />
                    <img src={"http://127.0.0.1.:9898/product/images"+"/"+Products.image} alt='' />
                    <img src={"http://127.0.0.1.:9898/product/images"+"/"+Products.image} alt='' />
                    <img src={"http://127.0.0.1.:9898/product/images"+"/"+Products.image} alt='' />
                </div>
                <div className='productdisplay-img'>
                    <img className='productdisplay-main-img' src={"http://127.0.0.1.:9898/product/images"+"/"+Products.image} alt=''/>
                </div>
            </div>
            <div className='productdisplay-right'>
                     <h1>{Products.name}</h1>
                <div className='productdisplay-right-stars'>
                    <img src={star_icon} alt='' />
                    <img src={star_icon} alt='' />
                    <img src={star_icon} alt='' />
                    <img src={star_icon} alt='' />
                    <img src={star_icon} alt='' />
                    <p>(122)</p>
                </div>
                <div className='productdisplay-right-prices'>
                    <div className='productdisplay-right-prices-old'>
                    ₹{Products.price}
                    </div>
                    <div className='productdisplay-right-prices-new'>
                        ₹{Products.qty}
                    </div>
                </div>

                <div className='productdisplay-right-description'>
                It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.
                </div>
                <div className='productdisplay-right-size'>
                    <h1>Select Size</h1>
                    <div className='productdisplay-right-sizes'>
                        <div>5 Kg</div>
                        <div>10 Kg</div>
                        <div>15 Kg</div>
                        <div>20 Kg</div>
                    </div>
                </div>
                <button onClick={()=>{addToCart(Products)}}>ADD TO CART</button>
                <p className='productdisplay-right-category'>
                <span>Category:</span> {Products.cat_id} || {Products.id} 
                </p>
            </div>
        </div>
    );
};

export default ProductDisplay;

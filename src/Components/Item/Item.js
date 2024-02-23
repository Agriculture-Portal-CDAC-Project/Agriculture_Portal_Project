import React from 'react'
import './item.css';
import { Link } from 'react-router-dom';


const Item = (props) => {
  return (
    <div className='item'>
        <Link to={`/Products/${props.id}`}><img  style={{ height: 235 }} onClick={window.scrollTo(0,0)} src={"http://127.0.0.1.:9898/product/images"+"/"+props.image} alt=''/></Link>
        <p>{props.name}</p>
    <div className='item-prices'>
        <div className='item-price-new'>
        ₹{props.price}
        </div>
        <div className='item-price-old'>
        ₹{props.price}
        </div>
    </div>

    </div>
  )
}

export default Item
import React from 'react';
import './NewProducts.css';
import new_collection from '../Assets/new_collections'
import Item  from '../Item/Item';
const NewProducts = () => {
  return (
    <div className='new-products'>
        <h1>New Products</h1>
        <hr></hr>
        <div className='products'>
            {new_collection.map((item,i)=>{
                return <Item key={i} id={item.id} name={item.name} image={item.image} new_price={item.new_price} old_price={item.old_price}/>

            })}
        </div>
    </div>
  )
}

export default NewProducts
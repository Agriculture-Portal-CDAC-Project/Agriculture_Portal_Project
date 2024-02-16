import React from 'react'
import './listProducts.css'

const listProducts = () => {

  return (
    <div className='list-product'>
        <h1>All Products List</h1>
        <div className='listproduct-format-main'>
            <p>Products</p>
            <p>Title</p>
            <p>Description</p>
            <p>Old Price</p>
            <p>Offer Price</p>
            <p>category</p>
            <p>Remove</p>
        </div>
        <div className='listproduct-allproducts'>
            <hr/>

        </div>
    </div>
  )
}

export default listProducts
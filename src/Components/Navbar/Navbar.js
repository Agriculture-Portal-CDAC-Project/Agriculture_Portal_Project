import React, { useContext, useRef, useState } from "react";
import './Navbar.css';
import logo from '../Assets/logo.png';    // Change the Project Logo
import cart_icon from '../Assets/cart_icon.png';
import { Link } from "react-router-dom";
import { ShopContext } from "../../Context/ShopContext";
import nav_dropdown from '../Assets/dropdown.png'


const Navbar = () => {

    const [menu,setMenu] = useState("Shop");
    const {getTotalCartAmounts} = useContext(ShopContext)
    const menuRef = useRef(); 

    const dropdown_toggle = (e) =>{
      menuRef.current.classList.toggle('nav-menu-visible');
      e.target.classList.toggle('open');
  }
  

  return (
    <div className="navbar">
        <div className="nav-logo">
            {/* Add the Project Logo here */}
            <img src={logo} alt=""/>  
        <p>Agriculter Portal</p>

        </div>
        <img onClick={dropdown_toggle} className="nav-dropdown" src={nav_dropdown} alt=""/>
        <ul ref={menuRef} className="nav-menu">
            <li onClick={() => {setMenu("Shop")}}><Link style={{textDecoration:'none'}} to='/'>Shop</Link>{menu === "Shop"?<hr/>:<></>}</li>
            <li onClick={() => {setMenu("Firtilizer")}}> <Link style={{textDecoration:'none'}} to='/Firtilizer'>Fertilizer</Link>{menu === "Firtilizer"?<hr/>:<></>}</li>
            <li onClick={() => {setMenu("Vegetables")}}><Link style={{textDecoration:'none'}} to='/Vegetables'>Vegetables</Link>{menu === "Vegetables"?<hr/>:<></>}</li>
            <li onClick={() => {setMenu("Pesticides")}}> <Link style={{textDecoration:'none'}} to='/Pesticides'>Pesticides</Link>{menu === "Pesticides"?<hr/>:<></>}</li>
        </ul>
        <div className="nav-login-cart">
          <Link to='/login'><button>Login</button></Link>

        <Link to='/Cart'> <img src={cart_icon} alt=""/></Link>
            <div className="nav-cart-count">{getTotalCartAmounts()}</div>
        </div>
    </div>
   
  )
}

export default Navbar
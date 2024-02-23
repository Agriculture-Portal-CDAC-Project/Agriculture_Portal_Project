import { Link, Navigate } from "react-router-dom";

// import AdminNavbar from "./AdminNavbar";
// import "../../node_modules/bootstrap/dist/css/bootstrap.min.css";
const AdminHome =() =>
{

    return(
        
        <>
           {/* <input type="text" name="product_name" placeholder="Enter Product Name"/>
           <button className="btn btn-primary">hello</button> */}
    <nav className='navbar navbar-expand-lg bg-primary' data-bs-theme='dark'>
  <div className='container-fluid'>
    <button className='navbar-toggler' type='button' data-bs-toggle='collapse' data-bs-target='#navbarSupportedContent' aria-controls='navbarSupportedContent' aria-expanded='false' aria-label='Toggle navigation'>
      <span className='navbar-toggler-icon'></span>
    </button>
    <div className='collapse navbar-collapse' id='navbarSupportedContent'>
      <ul className='navbar-nav'>
        <li className='nav-item me-auto'>
          <a className='nav-link' href='#'>
            Home
          </a>
        </li>
        <li className='nav-item'>
          <a className='nav-link' href='#'>
            Cart
          </a>
        </li>
        <li className='nav-item'>
          <a className='nav-link' href='#'>
            Orders
          </a>
        </li>
        <li className='nav-item'>
          <button className='nav-link' aria-current='page'>
            Logout
          </button>
        </li>
      </ul>
    </div>
  </div>
</nav>
        </>
    )
}

export default AdminHome;
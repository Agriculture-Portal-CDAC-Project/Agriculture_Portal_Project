import './App.css';
import Navbar from './Components/Navbar/Navbar';
import { BrowserRouter, Outlet, Route,Routes,Switch } from 'react-router-dom';
import ShopCategories from './Pages/ShopCategories';
import LoginSignup from './Pages/LoginSignup';
import Shop from './Pages/Shop';
import Products from './Pages/Products';
import Cart from './Pages/Cart';
import Footer from './Components/Footer/Footer';
import Fatilizer_banner from './Components/Assets/Fatilizer_banner.png'
import vegetables from './Components/Assets/vegetables.png'
import fruits from './Components/Assets/fruits.png'
import Login from './Pages/Login';
import Company  from './Components/Footer/company';
import Product from './Components/Footer/product'
import Offices from './Components/Footer/offices';

import About from './Components/Footer/about';
import Contact from './Components/Footer/contact';
import AdminHome from './Admin/AdminHome';

function App() {
  return (
    <div>
      <BrowserRouter>
      <Routes>
        {/* Routes that render the header and footer */}
        <Route path="/" element={<LayoutWithHeaderAndFooter/>}>
          <Route path='/' exact element={<Shop/>} />
          <Route path='/Firtilizer' exact element={<ShopCategories banner={Fatilizer_banner} category="Firtilizer"/>} />
          <Route path='/Vegetables' exact element={<ShopCategories banner={vegetables} category="Vegetables"/>} />
          <Route path='/Pesticides' exact element={<ShopCategories banner={fruits} category="Pesticides"/>} />
          <Route path='/Products' exact element={<Products/>}>
               <Route path=':productId' element={<Products/>} />
          </Route>
          <Route path='/Cart' exact element={<Cart/>} />
          <Route path='/login' exact element={<LoginSignup/>} />
          <Route path='/LoginPage' exact element={<Login />} />
          <Route path='/company' element={<Company />} /> 
          <Route path='/product' element={<Product />} />
          <Route path='offices' element={<Offices />} />

          <Route path='about' element={<About />} />
          <Route path='contact' element={<Contact />} />
        </Route>

        {/* Admin route that does not render the header and footer */}
        <Route path='/AdminHome' element={<AdminHome />} />
     
      </Routes>
    </BrowserRouter>

    </div>
  );
}

function LayoutWithHeaderAndFooter() {
  return (
    <>
      <Navbar/>
      <Outlet/>
      <Footer/>
    </>
  );
}

export default App;

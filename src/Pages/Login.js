import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import './CSS/Login.css';
import { signinUser } from '../servies/user';
import { toast } from 'react-toastify';
import AdminHome from '../Admin/AdminHome';

const Login = () => {

  const [email, setEmail] = useState('')
  const [password, setPassword] = useState('')

  const navigate = useNavigate()

  const onSignin = async () => {
    console.log("inside login")
    if (email.length == 0) {
      toast.warn('enter email')
    } else if (password.length == 0) {
      toast.warn('enter password')
    } else {
      console.log("inside else")
      // make the api call
      const result = await signinUser(email, password)
      if (result['status'] == 'success') {

        console.log(result.data[0]);
        console.log("Data :::: "+result.data[0].roll)
        // if(result.data[0].roll ==="ADMIN")
        // {
        //   console.log("welcome Admin")
        //   const token = result['token']
        //   sessionStorage['token'] = token
        //   sessionStorage["u_id"] = result.data[0].id;
        //   console.log(result);
        //   toast.success('Welcome to the pizza shop')
        //     navigate('/addproduct')
        // }
        // else
        // {
        //   const token = result['token']
        //   sessionStorage['token'] = token
        //   console.log(result);
        //   toast.success('Welcome to the pizza shop')
        //   navigate('/')
        // }
        const token = result['token']
        sessionStorage['token'] = token
        sessionStorage["u_id"] = result.data[0].id;
        // cache the token
        
      } else {
        toast.error(result['error'])
      }
    }
  }
  return (
    <div className='LoginSignup'>
      <div className='LoginSignup-container'>
        <h1>Login</h1>
        <div className='LoginSignup-fields'>
          <input onChange={(e) => setEmail(e.target.value)} type='email' placeholder='Email Address' />
          <input  onChange={(e) => setPassword(e.target.value)} type='password' placeholder='Password' />
        </div>
        <button onClick={onSignin}>
          Login
        </button>
        <div className='LoginSignup-agree'>
        <p>
          Don't have an account? <Link to='/login'><span>Sign Up</span></Link>
        </p>
          </div>
        
      </div>
    </div>
  );
};

export default Login;

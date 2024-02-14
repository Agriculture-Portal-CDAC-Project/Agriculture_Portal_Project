import React from 'react';
import { Link } from 'react-router-dom';
import './CSS/Login.css';

const Login = () => {
  return (
    <div className='LoginSignup'>
      <div className='LoginSignup-container'>
        <h1>Login</h1>
        <div className='LoginSignup-fields'>
          <input type='email' placeholder='Email Address' />
          <input type='password' placeholder='Password' />
        </div>
        <button>
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

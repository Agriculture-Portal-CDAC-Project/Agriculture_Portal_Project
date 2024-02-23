import React from 'react'
import './CSS/LoginSignup.css'
import {Link} from 'react-router-dom';

const LoginSignup = () => {
  return (
    <div className='LoginSignup'>
      <div className='LoginSignup-container'>
          <h1>Sign Up</h1>
          <div className='LoginSignup-fields'>
            <input type='text' placeholder='Enter FirstName LastName ' />
            {/* <input type='text' placeholder='Enter FirstName LastName ' /> */}
            <input type='email' placeholder='Email Address'/>
            <input type='password' placeholder='Password' />
          </div>
          <p className='LoginSignup-login'>
          Already have an account? <Link to='/LoginPage'><span>login here</span></Link>
        </p>
        <div className='LoginSignup-agree'>
                <input type='checkbox' name='' id='' />
                <p>By continue, i agree to the terms of use & privacy policy. </p>
          </div>
          <button>
            Continue
          </button>

          <h1></h1>
          <hr></hr>          
      </div>
    </div>
  )
}

export default LoginSignup
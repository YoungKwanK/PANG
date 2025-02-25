import React from 'react';
import '../styles/Login.css';
import GoogleLoginButton from '../components/GoogleLoginButton'

function Login() {
    return (
        <div className="login">
            <h1>Login</h1>
            <GoogleLoginButton></GoogleLoginButton>
        </div>
    )
}

export default Login;
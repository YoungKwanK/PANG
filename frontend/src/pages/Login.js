import React from 'react';
import '../styles/Login.css';
import OauthLogin from '../components/OauthLogin'

function Login() {
    return (
        <div className="login">
            <h1>Login</h1>
            <OauthLogin></OauthLogin>
        </div>
    )
}

export default Login;
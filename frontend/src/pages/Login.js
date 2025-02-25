import React from 'react';
import '../styles/Login.css';
import GoogleButton from "../components/GoogleButton";

function Login() {
    return (
        <div className="login">
            <h1>Login</h1>
            <GoogleButton></GoogleButton>
        </div>
    )
}

export default Login;
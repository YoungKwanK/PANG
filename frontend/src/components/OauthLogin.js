import React from 'react';

function OauthLogin() {
    const handleLogin = () =>{
        window.location.href = "/oauth2/authorization/google"; //인증할 URL
    }


    return (
        <div>
            <button onClick = {handleLogin}>Google로 로그인</button>
        </div>
    )
}

export default OauthLogin;
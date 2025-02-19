import React from "react";
import { GoogleLogin, GoogleOAuthProvider } from "@react-oauth/google";
import { useNavigate } from "react-router-dom";


// 구글 로그인 버튼 컴포넌트
const GoogleLoginButton = () => {
    const clientId = ""; // 구글에서 받은 클라이언트 ID
    const navigate = useNavigate();
    return (
        <div>

            <GoogleOAuthProvider clientId={clientId}>
                <GoogleLogin
                    onSuccess={(res) => {
                        //console.log(res);
                        navigate("/auth/callback", {state: {code: res}});
                    }}
                    onFailure={(err) => {
                        console.log(err);
                    }}
                />
            </GoogleOAuthProvider>
        </div>
    );
};

export default GoogleLoginButton;
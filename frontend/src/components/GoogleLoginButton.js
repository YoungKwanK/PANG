import React from "react";
import { useGoogleLogin, GoogleOAuthProvider } from "@react-oauth/google";
import { useNavigate } from "react-router-dom";
import axios from "axios";


// 구글 로그인 버튼 컴포넌트
const GoogleLoginButton = () => {
    const clientId = process.env.REACT_APP_GOOGLE_CLIENT_ID; // 환경 변수 사용

    const GoogleLoginButton = () => {
        const login = useGoogleLogin({
            onSuccess: async (response) => {
                console.log("Google Login Success:", response);

                try {
                    const res = await axios.post("/api/auth/google", {
                        code: response.code, // 인가 코드 전달
                    });
                    console.log("Backend Response:", res.data);
                } catch (error) {
                    console.error("Error sending code to backend", error);
                }
            },
            flow: "auth-code", // 인가 코드 방식 사용
        });

        return <button onClick={() => login()}>Login with Google</button>;
    };
    
    return (
        <GoogleOAuthProvider clientId={clientId}>
            <div>
                <h1>Google OAuth2 Login</h1>
                <GoogleLoginButton />
            </div>
        </GoogleOAuthProvider>
    );
};

export default GoogleLoginButton;
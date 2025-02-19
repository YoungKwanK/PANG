import React from "react";
import { GoogleOAuthProvider, useGoogleLogin } from "@react-oauth/google";
import axios from "axios";

function OauthLogin() {
    const clientId = process.env.REACT_APP_GOOGLE_CLIENT_ID; // 환경 변수 사용
    console.log(clientId);

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
}

export default OauthLogin;

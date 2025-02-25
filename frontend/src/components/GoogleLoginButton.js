const GoogleLoginButton = () => {

    const goLogin = () => {
        window.location.href = "http://localhost:8080/oauth2/authorization/google"
    };

    return (
        <div>
        <button onClick={goLogin}>구글 로그인 버튼</button>
        </div>
    );
}

export default GoogleLoginButton;
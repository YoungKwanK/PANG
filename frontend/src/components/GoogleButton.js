const GoogleButton = () =>{

    const goLogin = () => {
        window.location.href = "http://localhost:8080/oauth2/authorization/google"

    };

    return <button onClick={goLogin}>구글 로인 버튼</button>
}

export default GoogleButton;
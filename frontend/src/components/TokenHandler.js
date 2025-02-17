import React, { useEffect } from 'react';

const TokenHandler = () => {
    useEffect(() => {
        const urlParams = new URLSearchParams(window.location.search);
        const accessToken = urlParams.get('access_token');  // URL에서 access_token 추출

        if (accessToken) {
            //임시로 해놓은 방법
            // HttpOnly방법을 사용해야 안전함
            localStorage.setItem('access_token', accessToken);
            window.location.href = '/';  // 인증 후 리디렉션
        }
    }, []);

    return <div>토큰 처리 중...</div>;
};

export default TokenHandler;

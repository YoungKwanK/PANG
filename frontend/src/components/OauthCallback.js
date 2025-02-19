import axios from "axios";
import React, { useEffect, useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";


const OauthCallback = (props) => {
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const location = useLocation();
  const navigate = useNavigate(); // 회원가입을 위한 history 사용
  
  // 쿼리 파라미터에서 인가 코드 추출
  const params = new URLSearchParams(location.search);
  const state = location.state;
  const code = state?.code;
  console.log(code);

  useEffect(() => {
    if (code) {
      // 백엔드로 인가 코드 전달
      axios.post(`/api/login`, code)
        .then(response =>{
            // 받은 토큰 처리
            const token = response.data.value;

            if(token){
                setLoading(false);
                console.log(`서버에서 온 토큰 ${token}`);

                //서버에서 온 토큰을 처리함
                
                //navigate("/");
            }
            
        })
        .catch(error=>{
            // if(응답에 유저 로그인 없다고 오면)
            // navigate.push(가입 화면);

            setLoading(false);
            setError(error);
        })
    }
  }, [code]);

  if (loading) return <div>로그인 중...</div>;
  if (error) return <div>로그인 실패: {error.message}</div>;

  return <div>로그인 성공!</div>;
};

export default OauthCallback;

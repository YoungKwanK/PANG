import axios from "axios";
import React, { useEffect, useState } from "react";
import { useLocation, useNavigate, useSearchParams } from "react-router-dom";
import { useAccessToken } from "./AccessTokenContext";


const OauthCallback = (props) => {
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const location = useLocation();
  const navigate = useNavigate();

  const [searchParam] = useSearchParams();
  const token = searchParam.get("token");
  const { accessToken, setAccessToken } = useAccessToken();

  console.log("token", token);

  useEffect(() => {
    if (token) {
      localStorage.setItem("Authorization", token);
      setAccessToken(token);
      setLoading(false);
    }
  }, [token]);

  if (loading) return <div>로그인 중...</div>;
  if (error) return <div>로그인 실패: {error.message}</div>;


  console.log("local storage", localStorage.getItem("Authorization"));
  return <div>로그인 성공!</div>;
};

export default OauthCallback;



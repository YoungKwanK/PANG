import React from 'react';
import axios from "axios";


const Request = () => {


    // ✅ 하나의 함수로 JWT 포함하여 API 요청
    const fetchProtectedData = async () => {
        try {
            const response = await axios.get("api/protected", {
                withCredentials: true, // ✅ 쿠키 자동 포함
            });
            console.log("응답 데이터:", response.data);
            return response.data;
        } catch (error) {
            console.error("인증 실패:", error);
            throw error;
        }
    };

    return <button onClick={fetchProtectedData}> 요청 보내기 테스트 </button>
}
export default Request;
import axios from "axios";
import React, { useState } from "react";
import { useNavigate, useSearchParams } from "react-router-dom";


const SignUp = () => {
    const [nickname, setName] = useState()
    const [birth, setBirth] = useState();
    const [residence, setResidence] = useState()
    const [searchParam] = useSearchParams();
    const email = searchParam.get("email");
    const name = searchParam.get("name");

    const handleSubmit = (event) => {
        event.preventDefault();  // 기본 폼 제출 동작을 막기

        // 폼 데이터를 JSON 객체로 준비
        const data = {
            email,
            name,
            nickname,
            birth,
            residence,
        };

        console.log(data);

        // axios를 사용하여 POST 요청 보내기
        axios.post('/url', data, {
            headers: {
                'Content-Type': 'application/json',  // 서버에 JSON 형식으로 데이터 보내기
            },
        })
            .then((response) => {
                console.log('Success:', response.data);  // 서버 응답 처리
                navigator("/login");
            })
            .catch((error) => {
                console.error('Error:', error);  // 오류 처리
            });
    };

    return (
        <>
            <h1 className="login">회원 가입</h1>
            <form onSubmit={handleSubmit} className="login">
                <label for="email">이메일: {email}</label>
                <br /><br />
                <label for="name">이름: {name}</label>
                <br /><br />
                <label for="nickname">닉네임:</label>
                <input type="text" id="nickname" name="nickname" value={nickname} onChange={event => {
                    setName(event.target.value);
                }} /><br /><br />
                <label for="birthdate">생년월일:</label>
                <input type="date" id="birthdate" name="birthdate" value={birth} required onChange={event => {
                    setBirth(event.target.value);
                }} /><br /><br />
                <label for="residence">거주지:</label>
                <input type="text" id="residence" name="residence" value={residence} required onChange={event => {
                    setResidence(event.target.value);
                }} /><br /><br />
                <button type="submit">제출</button>
            </form>
        </>
    );
}

export default SignUp;
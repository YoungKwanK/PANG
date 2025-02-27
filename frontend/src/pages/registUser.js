import React, { useState } from "react";

const RegistUser = () => {
    const [nickname, setName] = useState()
    const [birth, setBirth] = useState();
    const [residence, setResidence] = useState()

    return (
        <>
            <h1 className="login">회원 가입</h1>
            <form action="/url" method="POST" className="login">
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

export default RegistUser;
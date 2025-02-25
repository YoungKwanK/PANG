import React from 'react';
import '../styles/Home.css'; 
import axios from 'axios';
import { useState } from "react";

function Home(){
    console.log("Home 컴포넌트 렌더링됨"); // 콘솔로 확인해보기


    return (
        <div className = "home">
        <h1>Home</h1>
        </div>
    )
}

export default Home;
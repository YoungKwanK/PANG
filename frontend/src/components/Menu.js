import React from 'react';
import { Link } from 'react-router-dom';
import '../styles/Menu.css';
import PANGLogo from '../assets/PANG-logo.png';

const Menu = () => {
    console.log("Menu Component Rendered");
    return (
        <div className="menu">
            <Link to="/"><img src={PANGLogo} alt="로고 이미지" /></Link>
            <Link to="/profile">프로필</Link>
            <Link to="/pang">PANG 생성</Link>
            <Link to="/chat">채팅</Link>
            <Link to="/location">현재 위치 기반 검색</Link>
            <Link to="/search">조건 검색</Link>
        </div>
    )
};


export default Menu;
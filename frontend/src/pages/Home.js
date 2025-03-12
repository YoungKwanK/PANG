import React from 'react';
import { Map } from "react-kakao-maps-sdk";
import '../styles/Home.css';

function Home() {
    console.log("Home 컴포넌트 렌더링됨"); // 콘솔로 확인해보기
    return (
        <div className="home">
            <h1>Home</h1>
            <Map
                center={{ lat: 33.450701, lng: 126.570667 }}
                style={{ width: '1000px', height: '600px' }}
                level={3}
            />

        </div>
    )
}

export default Home;
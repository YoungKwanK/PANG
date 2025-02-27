import React from 'react';
import '../styles/Home.css';

function UserProfile() {
    console.log("Profile 컴포넌트 렌더링됨"); // 콘솔로 확인해보기

    //useState로 사용자 정보 요청

    return (
        <div className="home">
            <h1>profile</h1>
        </div>
    )
}

export default UserProfile;
import React, { useState } from "react";
import "../styles/Home.css";

function Home() {
    console.log("Home 컴포넌트 렌더링됨");

    // 위치 상태 관리
    const [location, setLocation] = useState({
        lat: 0,
        lng: 0
    });

    // 위치 이름 관리 (문자열로 변경)
    const [residence, setResidence] = useState("");

    // Kakao 주소 검색 API 실행
    const handleSearch = () => {
        new window.daum.Postcode({
            oncomplete: function (data) {
                const selectedAddress = data.address;
                setResidence(selectedAddress);

                // Kakao 지도 API를 사용하여 주소 -> 좌표 변환
                const geocoder = new window.kakao.maps.services.Geocoder();
                geocoder.addressSearch(selectedAddress, function (result, status) {
                    if (status === window.kakao.maps.services.Status.OK) {
                        const newLocation = {
                            lat: parseFloat(result[0].y),
                            lng: parseFloat(result[0].x)
                        };
                        console.log("선택한 위치:", newLocation);
                        setLocation(newLocation);
                    } else {
                        console.error("주소 변환 실패");
                    }
                });
            }
        }).open();
    };

    return (
        <div className="home">
            <h1>Home</h1>

            {/* Kakao 주소 검색 버튼 */}
            <button onClick={handleSearch}>거주지 검색</button> <br/>{residence}
        </div>
    );
}

export default Home;

import React, { useState, useEffect } from 'react';
import { Map, MapMarker } from "react-kakao-maps-sdk";
import '../styles/Home.css';

function Home() {
    console.log("Home 컴포넌트 렌더링됨");

    // 위치 상태 관리
    const [location, setLocation] = useState({
        lat: 37.5665, // 기본값 (서울)
        lng: 126.9780
    });

    // 선택된 시/군/구 저장
    const [region, setRegion] = useState({ sido: "", sigungu: "" });

    useEffect(() => {
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(
                (pos) => {
                    const { latitude, longitude } = pos.coords;
                    console.log("현재 위치:", latitude, longitude);
                    setLocation({ lat: latitude, lng: longitude });
                },
                (error) => {
                    console.error("위치 정보를 가져오는 데 실패했습니다.", error);
                }
            );
        } else {
            console.error("이 브라우저는 Geolocation을 지원하지 않습니다.");
        }
    }, []);

    // Kakao 주소 검색 API 실행
    const handleSearch = () => {
        new window.daum.Postcode({
            oncomplete: function (data) {
                setRegion({
                    sido: data.sido, // 시/도 정보
                    sigungu: data.sigungu // 시/군/구 정보
                });

                // Kakao 지도 API를 사용하여 주소 -> 좌표 변환
                const geocoder = new window.kakao.maps.services.Geocoder();
                geocoder.addressSearch(data.sido + " " + data.sigungu, function (result, status) {
                    if (status === window.kakao.maps.services.Status.OK) {
                        const newLocation = {
                            lat: result[0].y,
                            lng: result[0].x
                        };
                        setLocation(newLocation);
                        console.log("선택한 위치:", newLocation);
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
            <button onClick={handleSearch} className="p-2 bg-blue-500 text-white rounded">
                시/군/구 검색
            </button>

            {/* 선택된 시/군/구 표시 */}
            {region.sigungu && (
                <p className="mt-2 text-gray-700">
                    선택된 지역: {region.sido} {region.sigungu}
                </p>
            )}

            {/* Kakao 지도 표시 */}
            <Map
                center={location}
                style={{ width: '1000px', height: '600px' }}
                level={3}
            >
                {/* 사용자가 선택한 위치에 마커 추가 */}
                <MapMarker position={location}>
                    <div style={{ padding: "5px", fontSize: "14px" }}>
                        {region.sido} {region.sigungu} (선택된 위치)
                    </div>
                </MapMarker>
            </Map>
        </div>
    );
}

export default Home;

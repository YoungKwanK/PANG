package org.PANG.utill;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.SerializationUtils;

import java.util.Base64;

public class CookieUtil {

    // 요청값(이름, 값, 만료 시간)을 바탕으로 쿠키 추가 (HttpOnly 적용)
    public static void addCookie(HttpServletResponse response, String name, String value, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");  // 모든 경로에서 접근 가능하도록 설정
        cookie.setMaxAge(maxAge);
        cookie.setHttpOnly(true); // JavaScript에서 접근 불가 (XSS 공격 방지)
        cookie.setSecure(true); // HTTPS에서만 쿠키 전송 (개발 환경에서는 필요에 따라 주석 처리)

        response.addCookie(cookie);
    }

    // 쿠키의 이름을 입력받아 쿠키 삭제
    public static void deleteCookie(HttpServletRequest request, HttpServletResponse response, String name) {
        Cookie[] cookies = request.getCookies();

        if (cookies == null) {
            return;
        }

        // 쿠키 삭제 (빈 값으로 설정하고 만료 시간 0으로 설정)
        for (Cookie cookie : cookies) {
            if (name.equals(cookie.getName())) {
                cookie.setValue("");
                cookie.setPath("/");
                cookie.setMaxAge(0);
                cookie.setHttpOnly(true); // 삭제 시에도 HttpOnly 적용
                cookie.setSecure(true); // HTTPS 적용

                response.addCookie(cookie);
            }
        }
    }

    // 객체를 직렬화해 쿠키의 값으로 변환 (Base64 인코딩)
    public static String serialize(Object obj) {
        return Base64.getUrlEncoder()
                .encodeToString(SerializationUtils.serialize(obj));
    }

    // 쿠키를 역직렬화해 객체로 변환
    public static <T> T deserialize(Cookie cookie, Class<T> cls) {
        return cls.cast(
                SerializationUtils.deserialize(
                        Base64.getUrlDecoder().decode(cookie.getValue())
                )
        );
    }
}

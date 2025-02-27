package org.PANG.config;

import lombok.RequiredArgsConstructor;
import org.PANG.config.jwt.TokenProvider;
import org.PANG.config.oauth.OAuth2AuthorizationRequestBasedOnCookieRepository;
import org.PANG.config.oauth.OAuth2SuccessHandler;
import org.PANG.config.oauth.OAuth2UserCustomService;
import org.PANG.repository.RefreshTokenRepository;
import org.PANG.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@RequiredArgsConstructor
@Configuration
public class WebOAuthSecurityConfig {

    private final OAuth2UserCustomService oAuth2UserCustomService;
    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserService userService;

    //스프링 시큐리티 비활성화
    @Bean
    public WebSecurityCustomizer configure() {
        return (web) -> web.ignoring()
                .requestMatchers(toH2Console())
                .requestMatchers("/img/**", "/css/**", "/js/**");
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) //csrf 비활성화
                .cors(AbstractHttpConfigurer::disable) //cors 비활성화
                .httpBasic(AbstractHttpConfigurer::disable) // 기본 인증 로그인 비활성화
                .formLogin(AbstractHttpConfigurer::disable) //기본 Login form 비활성화

                //세션 비활성화
                .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                //토큰 인증 필터 추가
                .addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)

                //접근 권환 설정
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/**").permitAll()
                       // .requestMatchers("/**").authenticated()
                        .anyRequest().permitAll()
        )

                //oauth 처리
                .oauth2Login((oauth2Login) -> oauth2Login
                        .successHandler(oAuth2SuccessHandler()) // 로그인 성공 핸들러 설정
                        .userInfoEndpoint(userInfo -> userInfo
                                .userService(oAuth2UserCustomService) // 사용자 정보 서비스 설정
                        )
                        .authorizationEndpoint(auth -> auth //인증 요청 저장소 설정
                                .authorizationRequestRepository(oAuth2AuthorizationRequestBasedOnCookieRepository()) // 인증 요청 저장소 설정
                        )
                )

                //로그아웃 처리
                .logout((logoutConfig) ->
                    logoutConfig.logoutSuccessUrl("/")
                );

        return http.build();
    }


    @Bean
    public OAuth2SuccessHandler oAuth2SuccessHandler() {
        return new OAuth2SuccessHandler(tokenProvider,
                refreshTokenRepository,
                oAuth2AuthorizationRequestBasedOnCookieRepository(),
                userService
        );
    }

    @Bean
    public TokenAuthenticationFilter tokenAuthenticationFilter() {
        return new TokenAuthenticationFilter(tokenProvider);
    }

    @Bean
    public OAuth2AuthorizationRequestBasedOnCookieRepository oAuth2AuthorizationRequestBasedOnCookieRepository() {
        return new OAuth2AuthorizationRequestBasedOnCookieRepository();
    }
}
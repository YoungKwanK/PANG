package org.PANG.oauth.config;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

import lombok.RequiredArgsConstructor;
import org.PANG.oauth.OAuth2AuthorizationRequestBasedOnCookieRepository;
import org.PANG.oauth.OAuth2SuccessHandler;
import org.PANG.oauth.OAuth2UserCustomService;
import org.PANG.oauth.TokenAuthenticationFilter;
import org.PANG.token.application.interfaces.RefreshTokenRepository;
import org.PANG.token.domain.TokenProvider;
import org.PANG.user.application.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.header.writers.StaticHeadersWriter;

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
            .headers(headers -> headers
                .addHeaderWriter(
                    new StaticHeadersWriter("Cross-Origin-Opener-Policy", "same-origin"))
            )

            .csrf(AbstractHttpConfigurer::disable)

            .sessionManagement(
                (session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

            .addFilterBefore(tokenAuthenticationFilter(),
                UsernamePasswordAuthenticationFilter.class)

            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/**").permitAll()
                // .requestMatchers("/**").authenticated()
                .anyRequest().permitAll()
            )

            //프론트에서 oauth2 인가코드 구현
            .oauth2Login((oauth2Login) -> oauth2Login
                .successHandler(oAuth2SuccessHandler()) // 로그인 성공 핸들러 설정
                .userInfoEndpoint(userInfo -> userInfo
                    .userService(oAuth2UserCustomService) // 사용자 정보 서비스 설정
                )
                .authorizationEndpoint(auth -> auth
                    .authorizationRequestRepository(
                        oAuth2AuthorizationRequestBasedOnCookieRepository()) // 인증 요청 저장소 설정
                )
            )

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

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
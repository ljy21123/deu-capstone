/*
 *   Spring Security의 환경설정 클래스입니다.
 *
 *   작성자: 이준영
 *
 * */

package com.example.infoweb.config;

import jakarta.servlet.DispatcherType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    PasswordEncoder passwordEncoder() {
        // 비밀번호를 암호화하기 위한 인코더로 BCryptPasswordEncoder를 사용
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)                                      // CSRF 보호 기능 비활성화
                .cors(AbstractHttpConfigurer::disable)                                  // CORS 정책 비활성화
                .authorizeHttpRequests(request -> request
                        .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()     // FORWARD 요청에 대해 모두 허용
                        .requestMatchers("/**").permitAll()                           // 인증 예외 처리
                        .anyRequest().authenticated()                                   // 나머지 요청은 모두 인증 필요
                )
                .formLogin(login -> login                                               // form 방식 로그인 사용
                        .loginPage("/users/login")	                                    // 커스텀 로그인 페이지 지정
                        .loginProcessingUrl("/login_process")                           // submit 받을 url
                        .usernameParameter("id")	                                    // submit할 아이디
                        .passwordParameter("pw")	                                    // submit할 비밀번호
                        .defaultSuccessUrl("/main", true)        // 성공 시 main으로
                        .failureForwardUrl("/loginError")                               // 실패 시 /loginError POST
                        .permitAll()                                                    // 로그인 페이지에 대한 접근 허용
                )
                .logout(logout -> logout                                                // 로그아웃은 기본설정으로 (/logout으로 인증해제)
                        .logoutSuccessUrl("/main")                                      // 로그아웃 성공 시 "/main"으로 리다이렉트
                        .permitAll())
                .rememberMe(rememberMe -> rememberMe                                    // rememberMe 설정 추가
                        .key("uniqueAndSecret")                                         // rememberMe 토큰을 위한 key 값 설정
                        .tokenRepository(persistentTokenRepository())                   // 토큰 저장소 설정
                        .tokenValiditySeconds(2592000)                                  // 토큰 유효기간 30일 설정
                );

        return http.build();

    }

    /**
     * InMemoryTokenRepositoryImpl 클래스는 자동 로그인 기능을 위한 토큰을 메모리에 저장하는 기본 구현입니다.
     * 이 클래스는 Spring Security가 제공하는 기본 구현 중 하나로, 실제 운영 환경에서는 데이터베이스 기반의 구현을 사용하는 것이 더 안전합니다.
     * 여기서는 예제를 간단하게 유지하기 위해 인메모리 토큰 저장소를 사용합니다.
     * 주의: 애플리케이션이 종료되면 메모리에 저장된 모든 토큰 정보는 사라집니다.
     * */
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        // 인메모리 토큰 저장소 구현
        return new InMemoryTokenRepositoryImpl();
    }

}
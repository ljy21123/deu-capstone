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

import static org.springframework.security.config.Customizer.withDefaults;

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
                        .requestMatchers("/**").permitAll()
//                        .requestMatchers("/status", "/layouts/**",
//                        "/users/signup", "/main").permitAll()                         // 인증 예외 처리
                        .anyRequest().authenticated()                                   // 나머지 요청은 모두 인증 필요
                )
                .formLogin(login -> login                                               // form 방식 로그인 사용
                        .loginPage("/users/login")	                                    // 커스텀 로그인 페이지 지정
                        .loginProcessingUrl("/login_process")                           // submit 받을 url
                        .usernameParameter("id")	                                    // submit할 아이디
                        .passwordParameter("pw")	                                    // submit할 비밀번호
                        .defaultSuccessUrl("/main", true)      // 성공 시 main으로
                        .permitAll()                                                    // 로그인 페이지에 대한 접근 허용
                )
                .logout(withDefaults());                                                // 로그아웃은 기본설정으로 (/logout으로 인증해제)

        return http.build();

    }
}
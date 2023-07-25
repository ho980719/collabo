package com.ho.collabo.common.config;

import com.ho.collabo.common.filter.CustomUsernamePasswordAuthenticationFilter;
import com.ho.collabo.common.filter.JwtAuthenticationFilter;
import com.ho.collabo.common.service.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@Slf4j
public class WebSecurityConfig {
    private final JwtService jwtService;
    private static final String[] PERMIT_URL_ARRAY = {
            "/api/v1/members/join", //회원가입
            "/api/v1/members/login", // 로그인
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .formLogin(login -> login.disable())
                .httpBasic(basic -> basic.disable())
                .csrf(csrf -> csrf.disable())
                .cors((cors) -> cors.configurationSource(corsConfigurationSource())) // cors 설정
                .headers((headers) -> headers.frameOptions((frameOptions) -> frameOptions.disable()))
                .sessionManagement((sess) -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests((authorizeRequests) -> authorizeRequests
                        .requestMatchers(PERMIT_URL_ARRAY).permitAll() // 회원가입,로그인 접근 가능
                        .requestMatchers("/api/**").hasAnyRole("USER")
                        .anyRequest().authenticated());
        http.addFilterAfter(new CustomUsernamePasswordAuthenticationFilter(), LogoutFilter.class);
//        http.addFilterBefore(jwtAuthenticationProcessingFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowCredentials(true);
        config.setAllowedOrigins(List.of("http://localhost:3000"));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setExposedHeaders(List.of("*"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

/*    @Bean
    public JwtAuthenticationFilter jwtAuthenticationProcessingFilter() {
        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(jwtService);
//        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(jwtService, memberRepository);
        return jwtAuthenticationFilter;
    }*/
}

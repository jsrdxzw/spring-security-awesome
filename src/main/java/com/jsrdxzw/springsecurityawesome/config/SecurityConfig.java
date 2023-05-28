package com.jsrdxzw.springsecurityawesome.config;

import com.jsrdxzw.springsecurityawesome.filter.CsrfCookieFilter;
import com.jsrdxzw.springsecurityawesome.filter.JWTTokenGeneratorFilter;
import com.jsrdxzw.springsecurityawesome.filter.JWTTokenValidatorFilter;
import com.jsrdxzw.springsecurityawesome.filter.RequestValidationBeforeFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import java.util.Collections;

/**
 * @author xuzhiwei
 * @date 2023/5/14 14:42
 */
@Configuration
public class SecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .csrf().disable()
                .cors().configurationSource(request -> {
                    CorsConfiguration corsConfiguration = new CorsConfiguration();
                    corsConfiguration.setAllowedOrigins(Collections.singletonList("http://localhost:8080"));
                    corsConfiguration.setAllowedMethods(Collections.singletonList(CorsConfiguration.ALL));
                    corsConfiguration.setAllowCredentials(true);
                    corsConfiguration.setAllowedHeaders(Collections.singletonList(CorsConfiguration.ALL));
                    corsConfiguration.setExposedHeaders(Collections.singletonList(AUTHORIZATION));
                    corsConfiguration.setMaxAge(3600L);
                    return corsConfiguration;
                }).and()
//                .addFilterAfter(new JWTTokenGeneratorFilter(), BasicAuthenticationFilter.class)
                .addFilterBefore(new JWTTokenValidatorFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests()
                .requestMatchers("/myAccount").hasRole("USER")
                .requestMatchers("/myBalance").hasAnyRole("USER", "ADMIN")
                .requestMatchers("/myLoans").hasRole("USER")
                .requestMatchers("/myCards").hasRole("USER")
                .requestMatchers("/user").authenticated()
                .requestMatchers("/notices", "/contact", "login", "/register").permitAll()
                .and().build();
    }

    /**
     * 传统的session & cookie方式
     *
     * @return SecurityFilterChain
     */
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        CsrfTokenRequestAttributeHandler requestHandler = new CsrfTokenRequestAttributeHandler();
//        requestHandler.setCsrfRequestAttributeName("_csrf");
//
//        http.securityContext().requireExplicitSave(false)
//                .and().sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
//                .cors().configurationSource(request -> {
//                    CorsConfiguration corsConfiguration = new CorsConfiguration();
//                    corsConfiguration.setAllowedOrigins(Collections.singletonList("http://localhost:8080"));
//                    corsConfiguration.setAllowedMethods(Collections.singletonList(CorsConfiguration.ALL));
//                    corsConfiguration.setAllowCredentials(true);
//                    corsConfiguration.setAllowedHeaders(Collections.singletonList(CorsConfiguration.ALL));
//                    corsConfiguration.setMaxAge(3600L);
//                    return corsConfiguration;
//                }).and()
//                .csrf((csrf) -> csrf.csrfTokenRequestHandler(requestHandler)
//                        .ignoringRequestMatchers("/contact", "/register")
//                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//                )
//                .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
//                .addFilterBefore(new RequestValidationBeforeFilter(), BasicAuthenticationFilter.class)
//                .authorizeHttpRequests()
////                .requestMatchers("/myAccount").hasAuthority("VIEWACCOUNT")
////                .requestMatchers("/myBalance").hasAnyAuthority("VIEWACCOUNT", "VIEWBALANCE")
////                .requestMatchers("/myLoans").hasAuthority("VIEWLOANS")
////                .requestMatchers("/myCards").hasAuthority("VIEWCARDS")
//                .requestMatchers("/myAccount").hasRole("USER")
//                .requestMatchers("/myBalance").hasAnyRole("USER", "ADMIN")
//                .requestMatchers("/myLoans").hasRole("USER")
//                .requestMatchers("/myCards").hasRole("USER")
//                .requestMatchers("/user").authenticated()
//                .requestMatchers("/notices", "/contact", "/register").permitAll()
//                .and().formLogin()
//                .and().httpBasic();
//        return http.build();
//    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

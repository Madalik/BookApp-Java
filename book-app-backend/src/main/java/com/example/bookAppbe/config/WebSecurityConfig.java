package com.example.bookAppbe.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity, RememberMeServices rememberMeServices) throws Exception {
        return httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
//                .rememberMe(rememberMeConfigurer -> rememberMeConfigurer.rememberMeServices(rememberMeServices))
                .httpBasic(Customizer.withDefaults())
                .logout((logout) -> logout.logoutUrl("/logout").clearAuthentication(true).deleteCookies("JSESSIONID"))
                .build();

//        httpSecurity
//                .csrf().disable()
//                .authorizeRequests()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/login").permitAll();
//                .httpBasic();
//        return httpSecurity.build();
    }

//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return (web) -> web.ignoring().requestMatchers("/login");
//    }

    @Bean
    public RememberMeServices rememberMeService(UserDetailsService userDetailsService) {
        TokenBasedRememberMeServices.RememberMeTokenAlgorithm encodingAlgorithm = TokenBasedRememberMeServices.RememberMeTokenAlgorithm.SHA256;
        TokenBasedRememberMeServices rememberMe = new TokenBasedRememberMeServices("myRandomKey", userDetailsService, encodingAlgorithm);
        rememberMe.setMatchingAlgorithm(TokenBasedRememberMeServices.RememberMeTokenAlgorithm.MD5);
        return rememberMe;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
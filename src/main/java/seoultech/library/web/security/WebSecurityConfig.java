package seoultech.library.web.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import seoultech.library.controller.Mappings;

import java.io.IOException;
import java.util.Set;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/resources/**");
//    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/resources/**");
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws  Exception {
        http.authorizeHttpRequests(
                auth -> auth
                        .requestMatchers(Mappings.ADMIN, String.format("%s/**", Mappings.ADMIN)).hasRole("ADMIN")
                        .requestMatchers(Mappings.USER, String.format("%s/**", Mappings.USER)).hasRole("USER")
                        .requestMatchers(Mappings.HOME, String.format("%s/**", Mappings.HOME)).permitAll()
                        .requestMatchers(String.format("%s/in",Mappings.SIGN)).permitAll()
                        )
                .formLogin()
                .loginPage(String.format("%s/in",Mappings.SIGN)).permitAll()
                .loginProcessingUrl(String.format("%s/perform_login",Mappings.SIGN))
                .defaultSuccessUrl(Mappings.HOME, true)
                .failureHandler(authenticationFailureHandler())
                .successHandler(authenticationSuccessHandler())
                .and()
                .logout()
                .logoutUrl(String.format("%s/perform_logout",Mappings.SIGN))
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .and()
                .csrf().disable();
        http.sessionManagement().maximumSessions(1).sessionRegistry(sessionRegistry());
     return http.build();
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
                if (roles.contains("ROLE_ADMIN"))
                    response.sendRedirect(String.format("%s/dashboard", Mappings.ADMIN));
                else
                    response.sendRedirect(Mappings.HOME);
            }
        };
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new AuthenticationFailureHandler() {
            @Override
            public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
                response.sendRedirect(String.format("%s/in",Mappings.SIGN));
            }
        };
    }

}

/*

 * Date : 03/01/24

 * Author : SWASTIK PREETAM DASH

 */

package speer.assesment.note_it.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import speer.assesment.note_it.constant.URLConstants;
import speer.assesment.note_it.filter.AuthenticationFilter;

@Configuration
@EnableWebSecurity
public class CustomSecurityConfig {

    @Autowired
    private AuthenticationFilter filter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                URLConstants.AUTH_BASE_ENDPOINT + URLConstants.AUTH_SIGNUP_ENDPOINT,
                                URLConstants.AUTH_BASE_ENDPOINT + URLConstants.AUTH_LOGIN_ENDPOINT
                        ).permitAll()
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
                .httpBasic(Customizer.withDefaults())
                .build();
    }
}

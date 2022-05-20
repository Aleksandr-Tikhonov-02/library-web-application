package Application.security;

import Application.security.jwt.JwtSecurityConfigurer;
import Application.security.jwt.JwtTokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    private final String[] PERMIT_ALL_URLS = {
            "/books", "/books/{id}", "/joinBooksAndTypes", "/findByName", "/books/joinBooksAndTypesFull",
            "/journal/joinClientsAndBooks", "/journal/joinClientsAndBooksByPassport", "/journal/joinClientsAndBooksByName",
            "/clients/all", "/clients/{id}", "/clients",
            "/book-types/all", "/book-types/{id}", "/book-types",
            "/auth/sign-in"
    };

    private final JwtTokenProvider jwtTokenProvider;

    public SpringSecurityConfig(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws  Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().disable()
                .csrf().disable()
                .formLogin().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/auth/**").permitAll()
                .antMatchers(HttpMethod.GET,"/**").permitAll()
                .antMatchers(HttpMethod.POST,"/**").hasRole("USER")
                .antMatchers(HttpMethod.PUT,"/**").hasRole("USER")
                .antMatchers(HttpMethod.DELETE,"/**").hasRole("USER")
                .anyRequest()
                .authenticated()
                .and()
                .apply(new JwtSecurityConfigurer(jwtTokenProvider));
    }

}

package com.company.config.springConfig;


import com.company.service.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SpringConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private CustomUserDetailService customUserDetailService;
    private static final String[] AUTH_WHITELIST = {
            "/v2/api-docs",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-resources",
            "/swagger-resources/**"
    };

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Authorization
        http.authorizeRequests()
                .antMatchers(AUTH_WHITELIST).permitAll()
                .antMatchers("/init/*").permitAll()
                .antMatchers("/auth/*").permitAll()
                .antMatchers("/attach/*").permitAll()
                .antMatchers("/product/public/*").permitAll()
                .antMatchers("/product/admin/*").hasRole( "ADMIN")
                .antMatchers("/color/admin/*").hasRole( "ADMIN")
                .antMatchers("/color/public/*").permitAll()
                .antMatchers("/brand/admin/*").hasRole( "ADMIN")
                .antMatchers("/category/admin/*").hasRole( "ADMIN")
                .antMatchers("/category/public/*").permitAll()
                .antMatchers("/types/admin/*").hasRole( "ADMIN")
                .antMatchers("/types/public/*").permitAll()
                .antMatchers("/productAdd/admin/*").hasRole( "ADMIN")
                .antMatchers("/profile/admin/*").hasRole( "ADMIN")
                .anyRequest().authenticated()
                .and().httpBasic();
                 http.cors().disable()
                .csrf().disable();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
    //    return NoOpPasswordEncoder.getInstance();
      return new BCryptPasswordEncoder();

    }


}

package health.hbp.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration @RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthenticationProvider authProvider;

    private final JWTAuthorizationFilter jwtAuthorizationFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authenticationProvider(authProvider);

        http.csrf().disable()
                .addFilterAfter(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers(HttpMethod.PUT, "/hbp/api/v1.0/edibles").authenticated()
                .antMatchers(HttpMethod.PUT, "/hbp/api/v1.0/edibles/*").authenticated()
                .antMatchers(HttpMethod.POST, "/hbp/api/v1.0/edibles").authenticated()
                .antMatchers(HttpMethod.POST, "/hbp/api/v1.0/edibles/*").authenticated()
                .antMatchers(HttpMethod.DELETE, "/hbp/api/v1.0/edibles").authenticated()
                .antMatchers(HttpMethod.DELETE, "/hbp/api/v1.0/edibles/*").authenticated()
                .antMatchers("/edibles/edit").authenticated()
                .antMatchers("/edibles/edit/*").authenticated()
                .antMatchers("/edibles/delete/*").authenticated()
                .antMatchers("/preferences").authenticated()
                .antMatchers("/token").authenticated()
                .anyRequest().permitAll().and()
                .formLogin().loginPage("/login").permitAll().and()
                .oauth2Login().loginPage("/login/oauth2").and()
                .logout().permitAll();
    }

}

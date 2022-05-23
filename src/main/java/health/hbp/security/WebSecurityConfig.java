package health.hbp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthenticationProvider authProvider;

    @Autowired
    public WebSecurityConfig(AuthenticationProvider provider) {
        this.authProvider = provider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authenticationProvider(authProvider);

        http.authorizeRequests()
                .antMatchers("/edibles/edit").authenticated()
                .antMatchers("/edibles/edit/*").authenticated()
                .antMatchers("/edibles/delete/*").authenticated()
                .antMatchers("/preferences").authenticated()
                .anyRequest().permitAll().and()
                .formLogin().loginPage("/login").permitAll().and()
                .logout().permitAll();
    }

}
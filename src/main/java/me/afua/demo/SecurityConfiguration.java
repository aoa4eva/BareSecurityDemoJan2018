package me.afua.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private SSUDS userDetailService;

    @Autowired
    private UserClassRepo userRepository;

    @Override
    public UserDetailsService userDetailsServiceBean() throws Exception{
        return new SSUDS(userRepository);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http
                .authorizeRequests()
                .antMatchers("/register").permitAll()
                .antMatchers("/").hasAuthority("USER")
                .antMatchers("/admin","/h2-console/**").hasAuthority("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login")
                .and()
                .httpBasic();

        http.headers().frameOptions().disable();
        http.csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{


        auth.inMemoryAuthentication()
                .withUser("user").password("password").authorities("USER")
                .and()
                .withUser("dave").password("begreat").authorities("ADMIN")
                .and()
                .withUser("afua").password("simplepassword").authorities("ADMIN","USER");

        auth.userDetailsService(userDetailsServiceBean());

    }

}

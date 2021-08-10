package pl.pieshakelbery.todo.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.pieshakelbery.todo.service.UserDetailsServiceImpl;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Resource
    private UserDetailsServiceImpl userDetailsService;

    @Bean
    public BCryptPasswordEncoder encodePass(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(encodePass());

        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider());
    }


    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
//                .csrf()
//                        .disable()
                .authorizeRequests()
                        .antMatchers().permitAll()
                        .anyRequest().authenticated()
                        .and()
                .formLogin()
                        .loginPage("/login")
                        .usernameParameter("email")
                        .passwordParameter("pass")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/tasks")
                        .failureUrl("/login_failure_handler")
                        .permitAll()
                        .and()
                .logout()
                        .permitAll()
                        .logoutUrl("/logout")
                        .logoutSuccessHandler(
                                (httpServletRequest, httpServletResponse, authentication) -> {
                                    httpServletResponse.sendRedirect("/");
                        });
    }

    @Override
    public void configure(WebSecurity web){
        web.ignoring().antMatchers("/");
        web.ignoring().antMatchers("/index");
        web.ignoring().antMatchers("/register");
        web.ignoring().antMatchers("/login_failure_handler");
        web.ignoring().antMatchers("/register_failure_handler");
        web.ignoring().antMatchers("/save-user");
        web.ignoring().antMatchers("/css/**");
        web.ignoring().antMatchers("/scripts/**");
        web.ignoring().antMatchers("/images/**");
    }

}

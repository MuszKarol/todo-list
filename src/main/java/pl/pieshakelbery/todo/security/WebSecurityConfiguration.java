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
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.pieshakelbery.todo.service.UserDetailsServiceImpl;

@Slf4j
@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService userDetailsService(){
        return new UserDetailsServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder encodePass(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(encodePass());

        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
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
                        .failureForwardUrl("/login_failure_handler")
                        .permitAll()
                        .and()
                .logout()
                        .permitAll()
                        .logoutUrl("/logout")
                        .logoutSuccessHandler(
                                (httpServletRequest, httpServletResponse, authentication) -> {
                                    log.info("-> " + authentication.getName() + " logged out."); //REMOVE
                                    httpServletResponse.sendRedirect("/login");
                        });
    }

    @Override
    public void configure(WebSecurity web){
        web.ignoring().antMatchers("/add-user");
        web.ignoring().antMatchers("/css/**");
        web.ignoring().antMatchers("/scripts/**");
        web.ignoring().antMatchers("/images/**");
    }

}

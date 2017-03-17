package com.company.recipes.config;

import com.company.recipes.model.User;
import com.company.recipes.services.UserService;
import com.company.recipes.web.FlashMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.query.spi.EvaluationContextExtension;
import org.springframework.data.repository.query.spi.EvaluationContextExtensionSupport;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    @Autowired
    private UserService userService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userService).passwordEncoder(User.PASSWORD_ENCODER);
    }

    @Override
    public void configure(WebSecurity web) throws Exception{
        web.ignoring().antMatchers("/css/**");
        web.ignoring().antMatchers("/images/**");
        web.ignoring().antMatchers("/js/**");
        web.ignoring().antMatchers("/h2-console/**");
        web.ignoring().antMatchers("/api/v1/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
                .antMatchers("/sign-up").permitAll()
                .anyRequest()
                .hasRole("USER")
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .successHandler(loginSuccessHandler())
                .failureHandler(loginFailureHandler())
                .and()
                .logout()
                .permitAll()
                .logoutSuccessUrl("/login")
                .and()
                .csrf();
    }

    public AuthenticationSuccessHandler loginSuccessHandler(){
        return (request, response, authentication) ->{
          response.sendRedirect("/recipes/");
        };
    }

    public AuthenticationFailureHandler loginFailureHandler(){
        return (request, response, exception) ->{
          request.getSession().setAttribute("flash",
                  new FlashMessage("Incorrect username and/or password. Try again.",
                          FlashMessage.Status.FAILURE));
            response.sendRedirect("/login");
        };
    }

    @Bean
    public EvaluationContextExtension securityExtension(){
        return new EvaluationContextExtensionSupport() {
            @Override
            public String getExtensionId() {
                return "security";
            }

            @Override
            public Object getRootObject(){
                Authentication authentication =
                        SecurityContextHolder.getContext().getAuthentication();
                return new SecurityExpressionRoot(authentication) {
                };
            }
        };
    }
}

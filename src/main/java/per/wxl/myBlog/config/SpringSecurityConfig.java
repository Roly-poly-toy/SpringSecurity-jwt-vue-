package per.wxl.myBlog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CharacterEncodingFilter;
import per.wxl.myBlog.filter.JwtAuthenticationTokenFilter;
import per.wxl.myBlog.service.UserService;

/**
 * @Auther: wxl
 * @Date: 2019/9/8 12:39
 * @Description:
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    MyAccessDeniedHandler accessDeniedHandler;
    @Autowired
    MyAuthenticationEntryPoint authenticationEntryPoint;
    @Autowired
    MyAuthenticationFailureHandler authenticationFailureHandler;
    @Autowired
    MyAuthenticationSuccessHandler authenticationSuccessHandler;
    @Autowired
    MyLogoutSuccessHandler logoutSuccessHandler;
    @Autowired
    UserService userService;
    @Autowired
    JwtAuthenticationTokenFilter authenticationTokenFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .httpBasic().authenticationEntryPoint(authenticationEntryPoint)
                .and()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler)
                .permitAll()
                .and()
                .logout()
                .logoutSuccessHandler(logoutSuccessHandler)
                .permitAll();
        http.exceptionHandling().accessDeniedHandler(accessDeniedHandler);
        http.addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        //http.addFilterBefore(characterEncodingFilter,JwtAuthenticationTokenFilter.class);
        http.headers().cacheControl();

    }
}

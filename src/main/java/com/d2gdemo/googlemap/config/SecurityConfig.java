package com.d2gdemo.googlemap.config;


import com.d2gdemo.googlemap.dao.UserDao;
import com.d2gdemo.googlemap.entity.User;
import com.d2gdemo.googlemap.security.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.context.request.RequestContextListener;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
@Autowired
    private JwtConfig jwtConfig;
    @Autowired
    private UserDao userDao;
    @Bean
    public UserDetailsService userDetailsService(){
        return new UserDetailsService();
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests().
                //.exceptionHandling().authenticationEntryPoint((req, rsp, e) -> rsp.sendError(HttpServletResponse.SC_UNAUTHORIZED))
               // .and().addFilterAfter(new JwtTokenAuthenticationFilter(jwtConfig),  UsernamePasswordAuthenticationFilter.class).authorizeRequests().
             //   antMatchers(HttpMethod.POST,jwtConfig.getUri()).permitAll().antMatchers("/places/**").hasAnyRole("USER").anyRequest().authenticated();
  //   http.authorizeRequests().
             mvcMatchers("/places/**").
             authenticated().and().httpBasic().realmName("user123").authenticationEntryPoint(new AuthenticationEntryPoint() {
         @Override
         public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
             httpServletResponse.addHeader("WWW-Authenticate", "Basic realm="+"  getRealmName()  ");
             httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
             PrintWriter writer = httpServletResponse.getWriter();
             writer.println("HTTP Status 401 - " + e.getMessage());
         }
     });
    }
    @Bean
    public JwtConfig jwtConfig(){
        return new JwtConfig();
    }
    @Bean
    public RequestContextListener requestContextListener(){
        return new RequestContextListener();
    }


    @Bean
    public PrincipalExtractor principalExtractor(UserDao user){
       return map -> {

String id = (String) map.get("sub");
          User newUser = userDao.findById(id).orElseGet(()->{
              User user1 = new User();
              user1.setId(id);
              user1.setLogin((String) map.get("name"));
              user1.setEmail((String) map.get("email"));
              userDao.save(user1);
              return user1;
          });

 return newUser;
       };

    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(8);
        return bCryptPasswordEncoder;
    }
}

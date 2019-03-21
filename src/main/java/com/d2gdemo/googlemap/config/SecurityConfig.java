package com.d2gdemo.googlemap.config;


import com.d2gdemo.googlemap.dao.UserDao;
import com.d2gdemo.googlemap.entity.Role;
import com.d2gdemo.googlemap.entity.User;
import com.d2gdemo.googlemap.security.JwtFilterConfiguer;
import com.d2gdemo.googlemap.security.JwtTokenProvider;
import com.d2gdemo.googlemap.security.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.context.request.RequestContextListener;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private JwtTokenProvider provider;
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
@Autowired
    private JwtConfig jwtConfig;
    @Autowired
    private UserDao userDao;


    @Bean(name = "detailsService")
    public UserDetailsService userDetailsService() {
        return new UserDetailsService();
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
              user1.getRoles().add(Role.USER);
              userDao.save(user1);
              return user1;
          });
    return newUser;
       };

    }
 //   .exceptionHandling().authenticationEntryPoint((req, rsp, e) -> rsp.sendError(HttpServletResponse.SC_UNAUTHORIZED))
   //  .and().addFilterAfter(new JwtTokenAuthenticationFilter(jwtConfig),  UsernamePasswordAuthenticationFilter.class).authorizeRequests().
      // antMatchers(HttpMethod.POST,jwtConfig.getUri()).permitAll().antMatchers("/places/**").hasAnyRole("USER").anyRequest().authenticated();
      // http.authorizeRequests().


    @Override
    public void configure(HttpSecurity http) throws Exception {

        // Disable CSRF (cross site request forgery)
        http.csrf().disable();

        // No session will be created or used by spring security
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // Entry points
        http.authorizeRequests()//
                .antMatchers("/user/login").permitAll()//
                // Disallow everything else..
                .anyRequest().authenticated();

        // If a user try to access a resource without having enough permissions
        http.exceptionHandling().accessDeniedPage("/login");

        // Apply JWT
        http.apply(new JwtFilterConfiguer(provider));

        // Optional, if you want to test the API from a browser
        // http.httpBasic();
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(6);
        return bCryptPasswordEncoder;
    }
}

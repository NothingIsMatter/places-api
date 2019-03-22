package com.d2gdemo.googlemap.config;


import com.d2gdemo.googlemap.dao.UserDao;
import com.d2gdemo.googlemap.entity.Role;
import com.d2gdemo.googlemap.entity.User;
import com.d2gdemo.googlemap.security.JwtFilterConfiguer;
import com.d2gdemo.googlemap.security.JwtTokenProvider;
import com.d2gdemo.googlemap.security.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
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
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.BearerTokenExtractor;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.web.context.request.RequestContextListener;


@Configuration
@EnableWebSecurity
@EnableOAuth2Sso
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private JwtTokenProvider provider;
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Autowired
    private UserDao userDao;


    @Bean(name = "detailsService")
    public UserDetailsService userDetailsService() {
        return new UserDetailsService();
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


    @Override
    public void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();

       // http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeRequests()
                .antMatchers("/user/login").permitAll().antMatchers("/user/get/**").hasRole(Role.BOT.toString()).antMatchers("/", "/login**","/callback/", "/webjars/**", "/error**")
                .permitAll().anyRequest().authenticated();


      http.apply(new JwtFilterConfiguer(provider));



    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(6);
        return bCryptPasswordEncoder;
    }
}

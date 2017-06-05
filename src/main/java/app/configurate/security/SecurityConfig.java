package app.configurate.security;


import app.configurate.handlers.CustomAuthenticationHandler;
import app.configurate.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

@Configuration
@EnableOAuth2Sso
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    CustomAuthenticationHandler customAuthenticationHandler;

    @Autowired
    AuthService authService;


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authService);
    }

//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        CharacterEncodingFilter filter = new CharacterEncodingFilter();
//        filter.setEncoding("UTF-8");
//        filter.setForceEncoding(true);
//        http.
//                csrf()
//                .disable()
//                .addFilterBefore(filter, CsrfFilter.class);
//
//        http.
//                authorizeRequests()
//                .antMatchers("/admin/**", "/user/**").hasAnyAuthority("ADMIN").anyRequest().permitAll()
//                .antMatchers("/user/**").hasAnyAuthority("USER").anyRequest().permitAll()
//                .and()
//                .formLogin()
//                .loginPage("/")
//                .usernameParameter("username")
//                .passwordParameter("password")
//                .permitAll()
//                .successHandler(customAuthenticationHandler);
//
//        http.
//                logout()
//                .permitAll()
//                .logoutUrl("/logout")
//                .logoutSuccessUrl("/")
//                .invalidateHttpSession(true);
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        // отключена защита csrf на время тестов
        http.csrf().disable().addFilterBefore(filter, CsrfFilter.class);

        http
                .antMatcher("/**")
                .authorizeRequests()
                .antMatchers("/", "/login.html").permitAll().anyRequest()
                .authenticated();

    }

}

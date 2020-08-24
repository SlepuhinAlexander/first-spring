package ru.ifmo.firstspring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.ifmo.firstspring.service.UserService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserService userService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable() /* отключение передачи csrf токена */
                .authorizeRequests()
                .antMatchers("/registration").not().fullyAuthenticated() /* эту ссылку могут использовать
                неавторизованные пользователи */
                // доступно только пользователям с ролью ROLE_USER
                /* в бд роли должны храниться под именами ROLE_ИМЯРОЛИ; в методах мы обращаемся к ролям по имени
                ИМЯРОЛИ */
//                .antMatchers("/foruser/**").hasRole("USER")
                // доступно только пользователям с ролью ROLE_ADMIN
//                .antMatchers("/foradmin/**").hasRole("ADMIN")
                .antMatchers("/").permitAll()
//                .antMatchers("/webjars/**", "/js/**", "/css/**", "/img/**").permitAll()
                /* по умолчанию, блок статики будет недоступен неавторизованным пользователям. можно указать как на
                строке 39. лучше через переопределение метода configure(WebSecurity webSecurity) (ниже) */
                .anyRequest().authenticated() /* любые другие страницы доступны всем кто авторизован (с любыми
                ролями) */
                .and()
                .formLogin() /* намерение использовать авторизацию через форму */
                .loginPage("/login") /* по какой ссылке будет доступна страница регистрации */
                /* по умолчанию при успешеной авторизации  */
                .permitAll() /* данный запрос доступен всем */
                .and()
                .logout().permitAll() /* возможность выхода доступна всем */
                .logoutSuccessUrl("/"); /* адрес, куда переотправлять после выхода */

    }

    @Override
    public void configure(WebSecurity webSecurity) throws Exception {
        webSecurity
                .ignoring()
                .antMatchers("/webjars/**", "/js/**", "/css/**", "/img/**");
    }

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder builder)
            throws Exception {
        builder.userDetailsService(userService).
                passwordEncoder(bCryptPasswordEncoder());
    }
}
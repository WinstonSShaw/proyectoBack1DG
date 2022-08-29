package com.example.winstonShaw.security;

import com.example.winstonShaw.service.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private UsuarioServiceImpl usuarioService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    public WebSecurityConfig(UsuarioServiceImpl usuarioService, BCryptPasswordEncoder bCryptPasswordEncoder){
        this.usuarioService = usuarioService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .authorizeHttpRequests()
                .antMatchers("/index.html","/turno/post_turno.html", "/turno/get_all_turno.html")
                .hasAnyRole("USER","ADMIN")
                .antMatchers("/paciente/post_paciente.html", "/paciente/get_all_paciente.html", "/odontologo/post_odontologo.html","/odontologo/get_all_odontologo.html")
                .hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .and()
                .logout()
                .and()
                .csrf().disable()
                ;
    }
}

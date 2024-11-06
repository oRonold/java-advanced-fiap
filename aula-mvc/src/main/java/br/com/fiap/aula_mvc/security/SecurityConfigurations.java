package br.com.fiap.aula_mvc.security;

import br.com.fiap.aula_mvc.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    @Autowired
    private UsuarioService usuarioService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/public/**", "/login", "/css/**").permitAll()
                        .requestMatchers("/admin/**", "/usuarios/register").hasRole("ADMIN") // Restringe o acesso para ADMIN
                        .requestMatchers("/user/**").hasRole("USER") // Restringe o acesso para USER
                        .anyRequest().permitAll()
                )
                .formLogin(form -> form
                        .loginPage("/login") // Página de login personalizada
                        .defaultSuccessUrl("/home") // Página de redirecionamento após sucesso no login
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout") // Redirecionamento após logout
                        .permitAll()
                );
        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(usuarioService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}

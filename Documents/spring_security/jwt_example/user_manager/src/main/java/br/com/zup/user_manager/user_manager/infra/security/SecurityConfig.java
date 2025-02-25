package br.com.zup.user_manager.user_manager.infra.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

// Indica que esta classe é uma classe de configuração do Spring
@Configuration
// Habilita a segurança da web no projeto
@EnableWebSecurity
public class SecurityConfig {

    // Define o bean responsável pela configuração da cadeia de filtros de segurança
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // Configura as regras de autorização para as requisições HTTP
        http.authorizeHttpRequests(request ->
                request.requestMatchers(HttpMethod.GET, "/user").permitAll() // Permite acesso público ao endpoint GET "/user"
                        .requestMatchers(HttpMethod.POST,"/user").permitAll() // Permite acesso público ao endpoint POST "/user"
                        .requestMatchers(HttpMethod.POST,"/user/login").permitAll() // Permite acesso público ao endpoint POST "/user/login"
                        .anyRequest().authenticated() // Exige autenticação para qualquer outra requisição
        );

        // Desabilita a proteção contra CSRF (Cross-Site Request Forgery)
        http.csrf(AbstractHttpConfigurer::disable);
        // Constrói e retorna a configuração de segurança
        return http.build();
    }

    // Define o bean responsável por codificar senhas usando o algoritmo BCrypt
    @Bean
    public PasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder(); // Retorna uma instância do codificador BCrypt
    }
}

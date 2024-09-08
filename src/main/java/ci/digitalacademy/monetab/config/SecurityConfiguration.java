package ci.digitalacademy.monetab.config;

import ci.digitalacademy.monetab.security.BlockReturnLogin;
import jakarta.servlet.DispatcherType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(CsrfConfigurer::disable)  // Désactiver la protection CSRF pour cette configuration
                .authorizeHttpRequests((authorize) -> authorize
                        .dispatcherTypeMatchers(DispatcherType.FORWARD, DispatcherType.ERROR).permitAll()  // Autoriser les types de requêtes FORWARD et ERROR
                        .requestMatchers("/css/**").permitAll()       // Permettre l'accès aux ressources css
                        .requestMatchers("/icons/**").permitAll()      // Permettre l'accès aux ressources icon
                        .requestMatchers("/javaScript/**").permitAll()        // Permettre l'accès aux ressources js
                        .requestMatchers("/appSettings").permitAll()  // Autoriser l'accès au point de terminaison /app-setting
                        .requestMatchers("/schools").permitAll() // Autoriser l'accès au point de terminaison /school-setting
                        .requestMatchers("/").permitAll()
                       // .requestMatchers("/absences").permitAll()
                       // .requestMatchers("/reports").permitAll()
                       // .requestMatchers("/students").permitAll()
                       // .requestMatchers("/teachers").permitAll()
                       /// .requestMatchers("/users").permitAll()
                       // .requestMatchers("/excels").permitAll()
                      //  .requestMatchers("/studentCards").permitAll()
                      //  .requestMatchers("/home").permitAll()
                        //.requestMatchers("/images/**").permitAll()    // Permettre l'accès aux ressources images
                        //.requestMatchers("/public/**").permitAll()    // Permettre l'accès aux ressources publiques (non authentifiées)
                        .anyRequest().authenticated()                 // Toutes les autres demandes nécessitent une authentification
                )
                .formLogin((login) -> login
                        .loginPage("/auth").permitAll()              // Autoriser l'accès à la page de connexion pour tout le monde
                        .defaultSuccessUrl("/home", true)             // Rediriger vers /home après une connexion réussie
                        .failureUrl("/auth?error=true")              // Rediriger vers la page de connexion avec une erreur en cas d'échec
                )
                .logout((logout) -> logout
                        .logoutUrl("/logout")                         // URL de déconnexion
                        .logoutSuccessUrl("/auth?logout=true")       // Rediriger vers la page de connexion après la déconnexion
                        .invalidateHttpSession(true)                  // Invalider la session après déconnexion
                        .deleteCookies("JSESSIONID")                  // Supprimer le cookie de session après déconnexion
                        .permitAll()                                  // Permettre à chacun de se déconnecter
                );

        http.addFilterBefore(new BlockReturnLogin(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}

// package br.cnj.projeto.config;

// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.Customizer;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

// @Configuration
// @EnableWebSecurity
// public class KeycloakSecurityConfig {
    
//     private static final String GROUPS = "groups";
//     private static final String REALM_ACCESS_CLAIM = "realm_access";
//     private static final String ROLES_CLAIM = "roles";

//     private final KeycloakLogoutHandler handler;

//     public KeycloakSecurityConfig(KeycloakLogoutHandler handler){
//         this.handler = handler;
//     }

//     public SecurityFilterChain resourceServerFilterChain(HttpSecurity http) throws Exception {
//         http.authorizeHttpRequests(auth -> auth
//             .requestMatchers(new AntPathRequestMatcher("/api/arquivos/**"))
//             .hasRole("admin")
//             .requestMatchers(new AntPathRequestMatcher("/"))
//             .permitAll()
//             .anyRequest()
//             .authenticated());
//         http.oauth2ResourceServer((oauth2) -> oauth2
//             .jwt(Customizer.withDefaults()));
//         http.oauth2Login(Customizer.withDefaults())
//             .logout(logout -> logout.addLogoutHandler(handler)
//             .logoutSuccessUrl("/")
//             .clearAuthentication(true)
//             .deleteCookies("JSESSIONID"));
//         return http.build();
//     }


// }

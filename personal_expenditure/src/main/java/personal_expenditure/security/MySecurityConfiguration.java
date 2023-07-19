package personal_expenditure.security;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
@Configuration
@EnableMethodSecurity
public class MySecurityConfiguration {
 		@Bean
	    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
// 			http.authorizeHttpRequests().requestMatchers("/all").permitAll();
// 			
	http.authorizeHttpRequests().requestMatchers("/admin/**").hasRole("ADMIN");
 			http.authorizeHttpRequests().anyRequest().authenticated();
 			
  	
 			http.httpBasic();
  			http.csrf().disable();
		    http.formLogin();
	        return http.build();
	   }
}
package personal_expenditure.security;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class Inmemorysecuritydemo {
	@Bean
    public UserDetailsService userDetailsService
            (PasswordEncoder passwordEncoder) {
        UserDetails user1 = User.withUsername("venkatesh")
            .password(passwordEncoder.encode("venkatesh@123"))
            .roles("USER")
            .build();
        UserDetails user2 = User.withUsername("sai")
            .password(passwordEncoder.encode("sai@123"))
            .roles("USER")
            .build();
        UserDetails user3 = User.withUsername("ravi")
                .password(passwordEncoder.encode("ravi@123"))
                .roles("USER")
                .build();
        UserDetails user4 = User.withUsername("hari")
                .password(passwordEncoder.encode("hari@123"))
                .roles("USER")
                .build();
        UserDetails user5 = User.withUsername("sridhar")
                .password(passwordEncoder.encode("sridhar@123"))
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user1,user2,user3,user4,user5);
    }
	 @Bean
	    public PasswordEncoder passwordEncoder() {
	        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
	        return encoder;
	    }
}
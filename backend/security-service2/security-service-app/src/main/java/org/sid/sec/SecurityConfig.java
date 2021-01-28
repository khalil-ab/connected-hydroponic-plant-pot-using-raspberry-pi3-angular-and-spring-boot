package org.sid.sec;

//c est par ici qu on fait la configuration d authentification basée sur JWT (json web token) au lieu d utiliser la configuration par defaut basée sur des sessions memory authentication 
//cette classe permet de configurer spring security de notre maniere
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration// pour préciser que c est une classe de configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
	@Autowired
    private UserDetailsServiceImpl userDetailsService;//pour utiliser automatiquement la methode permettant de cahrger un user sachant son username
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    //on precise avec cette methode les users qui ont le droit d acceder a cette application.
    //cette methode configure la maniere avec laquelle on va va verifier l username et le password lors de l authentification. Cette maniere sera adopté par la methode attemptAuthentication du premier filtre JWTAuthenticationFilter
    //pour un bon fonctionnement la variable userDetailsService doit etre de type UserDetailsService Implementé -->UserDetailsServiceImpl
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	//on demande a spring security de se baser sur l interface userDetailsService qu on a implementer pour gérer l authentification
    	//qd on fait appel a la methode attemptAuthentication spring security va utiliser la methode findbyusername de userDetailsService pour verifier si l username existe grace a l objet userdetail recu
    	//en meme temps avec cet objet Userdetail il va verifier le passwd si il est correct
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    //amdin 1234
    //user1 1234
    //dans cette methode on definit les regles 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);//si on voudrait utiliser la configuration par defaut basée sur les sessions
    	//http.formLogin();//si on voudrait garder le meme formulaire d authentification et utiliser la configuration par defaut basée sur les sessions
    	
    	 http.csrf().disable();
         http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);//demander a spring security de ne pas utiliser les session en memoire parcequ on la session a utiliser est celle de JWT  
         http.authorizeRequests().antMatchers("/login/**","/register/**").permitAll();//l acces a la page login et register est permi a tt le monde
         http.authorizeRequests().antMatchers("/appUsers/**","/appRoles/**").hasAuthority("ADMIN");//pour preciser que seul celui qui a le role d un admin qui a le droit d accéder a /appUsers et /appRoles 
         http.authorizeRequests().anyRequest().authenticated();
         //on precise les deux filtres qui vont intervenir
         http.addFilter(new JWTAuthenticationFilter(authenticationManager()));//le premier filtre qui intervient uniquemenet au momement de l authentification
         http.addFilterBefore(new JWTAuthorizationFiler(), UsernamePasswordAuthenticationFilter.class);//le deuxieme filtre qui intervient a chaque fois on envoit une requete qui doit etre verifié si elle contient le jwt et si elle est bien signée
    }
}

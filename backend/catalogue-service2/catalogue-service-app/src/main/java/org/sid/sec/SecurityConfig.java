package org.sid.sec;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
/*    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        BCryptPasswordEncoder bcpe = getBCPE();
        auth.inMemoryAuthentication().withUser("admin").password(bcpe.encode("1234")).roles("ADMIN","USER");
        auth.inMemoryAuthentication().withUser("user1").password(bcpe.encode("1234")).roles("USER");
    }
    
  @Bean
    public BCryptPasswordEncoder getBCPE(){
        return new BCryptPasswordEncoder();
    }*/
  
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();//on met cette ligne pour qu on puisse utilsier notre application externe ARC tranquilement
        //http.formLogin();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);//on demande a spring security de desactiver les sessions puisque c est nous qui allons gérer l'authentification à l'aide de JWT
        //http.authorizeRequests().antMatchers(HttpMethod.GET,"/categories/**").permitAll();//autoriser l acces a /categories qd l user envoit une requete avec GET
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/products/**").permitAll();//autoriser l acces a /products qd l user envoit une requete avec GET
        //http.authorizeRequests().antMatchers("/categories/**").hasAuthority("ADMIN");//je dois etre un ADMIN si je voudrais acceder à /categories
        http.authorizeRequests().antMatchers("/products/**").hasAuthority("USER");//je dois etre un ADMIN si je voudrais acceder à /products
        http.authorizeRequests().anyRequest().authenticated();// le reste des requetes necessite une authentification
        
        /*on va créer un filtre qui analyse chaque requete envoyé il verifie s il y a le JWT ladedans et 
          s'il est là il va signer cette requete, il charge le contexte d'utilisateur (USER et les roles)
           il demande a spring security de charger ces donnée dans le contexte de la requete puis il laisse 
           passer vers l'API Rest qui va analyser s il s agit d un admin ou un user et leur octroyer chacun 
           d'entre eux les apges qu il peuvent accéder*/
        http.addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
        //super.configure(http);//ajouter juste cette ligne si on voudrait utilisation la configuration par defaut
    }

  


}

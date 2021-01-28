package org.sid.sec;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class JWTAuthorizationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        /*System.out.println("**********");
        String jwt = httpServletRequest.getHeader("Authorization");
        if(jwt==null) throw new RuntimeException("Not Authorized");

        filterChain.doFilter(httpServletRequest,httpServletResponse);*/
        
        
      //autoriser a l user d envoyer une requete apartir de n importe quelle autre page appartenant d un autre domaine
      		//autoriser à n importe qu elle page d un autre domaine de nous envoyer une requete
              response.addHeader("Access-Control-Allow-Origin", "*");
              //preciser les entete autorisé à nous envoyer une requete telle que Authorization : on met * si on voudrait que tt le monde fasse ça
              response.addHeader("Access-Control-Allow-Headers", "Origin, Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers,authorization");
              //preciser les entetes qui ont le droit de lire les valeurs de l entete envoyé
              response.addHeader("Access-Control-Expose-Headers", "Access-Control-Allow-Origin, Access-Control-Allow-Credentials, authorization");
              //autoriser a l user de nous envoyer des requetes contenants les methods suivantes 
              response.addHeader("Access-Control-Allow-Methods","GET,POST,PUT,DELETE,PATCH");
              
              //j autorise si la requete a été envoyé avec l entete option et donc pas la peine d analyser le jwt
              //car avec OPTION on demande les autorisations qu on a : Access-Control-Allow-Origin / Access-Control-Allow-Headers / Access-Control-Expose-Headers
              if(request.getMethod().equals("OPTIONS"))
              {
                  response.setStatus(HttpServletResponse.SC_OK);
              }
              else 
              {// cest ici ou on commence a analyser le jwt
                  String jwtToken = request.getHeader(SecurityParams.JWT_HEADER_NAME);//on recupere le nom de jwt header qui se trouve dans la tete de la requete
                  System.out.println("Token="+jwtToken);
                  //s il est null ou ne commence pas par un prefix
                  if (jwtToken == null || !jwtToken.startsWith(SecurityParams.HEADER_PREFIX)) {
                      filterChain.doFilter(request, response);//je passe au filtre suivant
                      return;
                  }
                  //verifier si le token est bien signé ou non 
                  JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SecurityParams.SECRET)).build();
                  String jwt = jwtToken.substring(SecurityParams.HEADER_PREFIX.length());
                  DecodedJWT decodedJWT = verifier.verify(jwt);//genere une exception en cas d erreur
                  System.out.println("JWT="+jwt);
                  String username = decodedJWT.getSubject();//pour recuperer l username
                  List<String> roles = decodedJWT.getClaims().get("roles").asList(String.class);//pour recuperer les role de cet user
                  System.out.println("username="+username);
                  System.out.println("roles="+roles);
                  //transfomer les roles recupérés en une collection GrantedAuthority 
                  Collection<GrantedAuthority> authorities = new ArrayList<>();
                  roles.forEach(rn -> {
                      authorities.add(new SimpleGrantedAuthority(rn));
                  });
                  //demander a spring security d authentifier cet user
                  UsernamePasswordAuthenticationToken user =
                          new UsernamePasswordAuthenticationToken(username, null, authorities);
                  SecurityContextHolder.getContext().setAuthentication(user);
                //je passe au filtre suivant
                  filterChain.doFilter(request, response);
    }
    }
}

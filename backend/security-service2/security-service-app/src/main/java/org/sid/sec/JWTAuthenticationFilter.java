package org.sid.sec;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.sid.entities.AppUser;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	private AuthenticationManager authenticationManager;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	//methode appelée qd l user tente de s authentifier et c est en se basant de la maniere configuré dans la methode configure(AuthenticationManagerBuilder) que cette methode va pouvoir savoir comment verifier l username et le password
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
			AppUser appUser = new ObjectMapper().readValue(request.getInputStream(), AppUser.class);//on prend l username et le paswwd mentionné dans la requte envoyé lors de l authentification puis on va les serialise dans un objet de type AppUser
			return authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(appUser.getUsername(), appUser.getPassword()));
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	//methode appelée qd l user s authentifier avec succes
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		
		User user = (User) authResult.getPrincipal();//nous retourne l user authentifié
		//cette list va stocker les roles de l user authentifié
		List<String> roles = new ArrayList<>();
		authResult.getAuthorities().forEach(a -> {//getAuthorities nous retourne les role de l user authentifié
			roles.add(a.getAuthority());
		});
		//c est comme ça comment on crée et on fait la signature du token
		//on precise dans ce jwt l username ses roles la date d expiration du token et la signature
		String jwt = JWT.create().withIssuer(request.getRequestURI()).withSubject(user.getUsername())
				.withArrayClaim("roles", roles.toArray(new String[roles.size()]))
				.withExpiresAt(new Date(System.currentTimeMillis() + SecurityParams.EXPIRATION))//SecurityParams est une constante qu on peut créer a part pour la modier de notre maniere
				.sign(Algorithm.HMAC256(SecurityParams.SECRET));
		//le token qu on vient de créer sera mis dans l objet response avec une entete
		response.addHeader(SecurityParams.JWT_HEADER_NAME, jwt);
	}

}

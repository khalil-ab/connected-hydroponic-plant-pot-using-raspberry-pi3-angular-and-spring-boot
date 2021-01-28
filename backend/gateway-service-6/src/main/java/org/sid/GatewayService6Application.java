package org.sid;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableHystrix 
public class GatewayService6Application {

	public static void main(String[] args) {
		SpringApplication.run(GatewayService6Application.class, args);
	}
	
	//une autre methode pour configurer les path de la meme maniere que lorsqu on fait dans le fichier yaml
	//ceci est la methode statique
	@Bean
	RouteLocator gatewayRoutes(RouteLocatorBuilder builder){
	return builder.routes()
	//.route(r->r.path("/customers/**").uri("http://localhost:8081/").id("r1"))
	.route(r->r.path("/products/**").uri("http://localhost:8084/").id("r2"))
	//.route(r->r.path("/bills/**").uri("http://localhost:8083/").id("r3"))
	//.route(r->r.path("/fullBill/**").uri("http://localhost:8083/").id("r4"))
	
	
	//api country : ce qu il faut coller aupres du site apipublic sont les deux header et l url
	.route(r->r.path("/restcountries/**") 
	.filters(f->f.addRequestHeader("x-rapidapi-host","ajayakv-rest-countries-v1.p.rapidapi.com")//le premier header
	.addRequestHeader("x-rapidapi-key","73c82ac348msh70402abc0aa487bp1e55adjsn44eaaf810895")//le deuxieme header  
	.rewritePath("/restcountries/(?<segment>.*)","/${segment}")//il garde quoi si on on ne fait rien
	.hystrix(h->h.setName("rest-countries") 
	.setFallbackUri("forward:/restCountriesFallback"))//ceci est le path qu il fauut mentionner dans le @GetMapping du RestController
	)
	.uri("https://ajayakv-rest-countries-v1.p.rapidapi.com/rest/v1/all")//l url
	.id("countries")) 
	
	
	//api muslim salat : ce qu il faut coller aupres du site apipublic sont les deux header et l url
	.route(r->r.path("/muslimsalat/**") 
	.filters(f->f.addRequestHeader("x-rapidapi-host","muslimsalat.p.rapidapi.com")//le premier header 
	.addRequestHeader("x-rapidapi-key","73c82ac348msh70402abc0aa487bp1e55adjsn44eaaf810895")//le deuxieme header 
	.rewritePath("/muslimsalat/(?<segment>.*)","/${segment}")//il garde quoi si on on ne fait rien
	.hystrix(h->h.setName("muslim-salat") 
	.setFallbackUri("forward:/muslimsalatFallback"))//ceci est le path qu il fauut mentionner dans le @GetMapping du RestController
	)
	.uri("https://muslimsalat.p.rapidapi.com")//l url
	.id("muslimhoraire")) 
	
	
	.build();
	}
	
	
	@RestController 
	class FallBackRestController
	{  
		@GetMapping("/restCountriesFallback") 
		public Map<String,String> restCountriesFallback()
		{ 
			Map<String,String> map=new HashMap<>();
			map.put("message","Default Rest Countries Fallback service"); 
			map.put("countries","Algeria, Morocco"); 
			return map; 
		} 
		
	    @GetMapping("/muslimsalatFallback") 
	    public Map<String,String> muslimsalatback()
	    { 
	    	Map<String,String> map=new HashMap<>(); 
	    	map.put("message","Default Muslim Fallback service"); 
	    	map.put("Fajr","07:00"); 
	    	map.put("DOHR","14:00"); 
	    	return map; 
	    } 
	} 
	
	//une autre methode pour configurer les path de la meme maniere que lorsqu on fait dans le fichier yaml
	//ceci est la methode dynamique
	/*@Bean
	/*DiscoveryClientRouteDefinitionLocator dynamicRoutes(ReactiveDiscoveryClient rdc,DiscoveryLocatorProperties dlp){
	return new DiscoveryClientRouteDefinitionLocator(rdc,dlp);
	}*/
	
	
	  /*@Bean RouteLocator gatewayRoutes(RouteLocatorBuilder builder){ return
	  builder.routes()
	  .route(r->r.path("/customers/**").uri("lb://CUSTOMER-SERVICE") .id("r1"))
	  .route(r->r.path("/products/**").uri("lb://INVENTORY-SERVICE") .id("r2"))
	  .build(); }*/
	 

}

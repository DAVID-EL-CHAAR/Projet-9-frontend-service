package com.front;
import java.net.CookieStore;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;



//import com.front.security.JwtAuthenticationFilter;




@Configuration
public class AppConfig {
    
	 @Bean
	    public RestTemplate restTemplate() {
	        return new RestTemplate();  // Crée et retourne une instance de RestTemplate
	    } 


	 

} 
	/*
    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        return restTemplate;
    }*/
	/*
	 @Bean
	    public RestTemplate restTemplate() {
	        RestTemplate restTemplate = new RestTemplate();
	        restTemplate.getInterceptors().add(new JwtInterceptor());
	        return restTemplate;
	    }
}*/
	/*
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();  // Crée et retourne une instance de RestTemplate
    }
	                  
}

@Bean
	public RestTemplate restTemplate(List<ClientHttpRequestInterceptor> interceptors) {
	    RestTemplate restTemplate = new RestTemplate();
	    restTemplate.setInterceptors(interceptors); // Ajouter les interceptors, y compris celui pour le JWT
	    return restTemplate;
	}
	
	@Bean(name = "jwtRequestInterceptor")
	public ClientHttpRequestInterceptor jwtInterceptor() {
	        return new JwtInterceptor();  // Assure que l'intercepteur JWT est enregistré
	    }
*/
    /*
	@Bean
	public RestTemplate restTemplate() {
	    CookieStore cookieStore = new BasicCookieStore();
	    CloseableHttpClient httpClient = HttpClientBuilder.create()
	        .setDefaultCookieStore(cookieStore)
	        .build();
	    HttpComponentsClientHttpRequestFactory requestFactory =
	        new HttpComponentsClientHttpRequestFactory(httpClient);
	    return new RestTemplate(requestFactory);
	}*/

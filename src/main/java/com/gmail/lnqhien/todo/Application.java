package com.gmail.lnqhien.todo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@SpringBootApplication
@EnableTransactionManagement
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	/**
	 * Enable CORS so browser clients can make requests.
	 * 
	 * Adapted from https://www.programcreek.com/java-api-examples/?code=BakkerTom/happy-news/happy-news-master/api/src/main/java/nl/fhict/happynews/api/AuthorizationServerConfig.java
	 */
	@Bean
	FilterRegistrationBean<CorsFilter> corsFilter() {
	    CorsConfiguration config = new CorsConfiguration();
	    config.setAllowCredentials(true);
	    config.addAllowedOrigin("*");
	    config.addAllowedHeader("*");
	    config.addAllowedMethod("*");

	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**", config);

	    FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(source));
	    bean.setOrder(Ordered.HIGHEST_PRECEDENCE);

	    return bean;
	}
}

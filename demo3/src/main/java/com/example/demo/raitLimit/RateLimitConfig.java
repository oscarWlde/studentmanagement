package com.example.demo.raitLimit;

import java.time.Duration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;

@Configuration
public class RateLimitConfig implements WebMvcConfigurer {

	  @Override
	  public void addInterceptors(InterceptorRegistry registry) {
//	    Refill refill = Refill.greedy(10, Duration.ofMinutes(1));
//	    Bandwidth limit = Bandwidth.classic(10, refill).withInitialTokens(1);
//	    Bucket bucket = Bucket4j.builder().addLimit(limit).build();
//	    registry.addInterceptor(new RateLimitInterceptor(bucket, 1)).addPathPatterns("/mycompany");
//
//	    refill = Refill.intervally(3, Duration.ofMinutes(1));
//	    limit = Bandwidth.classic(3, refill);
//	    bucket = Bucket4j.builder().addLimit(limit).build();
//	    registry.addInterceptor(new RateLimitInterceptor(bucket, 1))
//	        .addPathPatterns("/mycompany/employees");

//	    registry.addInterceptor(new RateLimitInterceptor(bucket, 1))
//	        .addPathPatterns("/place/*");

	    registry.addInterceptor(new PerClientRateLimitInterceptor())
	        .addPathPatterns("/student/**");
	    registry.addInterceptor(new PerClientRateLimitInterceptor())
				.addPathPatterns("/teacher/**");
	    registry.addInterceptor(new PerClientRateLimitInterceptor())
				  .addPathPatterns("/manager/**");

	  }

	}
package com.adventurelandVillage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@SpringBootApplication
public class AdventurelandVillageApplication {

//	For customized Validation Messages
	@Bean
	public LocalValidatorFactoryBean validator(MessageSource ms) {
		LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
		 bean.setValidationMessageSource(ms);
		 return bean;
	}
	public static void main(String[] args) {
		SpringApplication.run(AdventurelandVillageApplication.class, args);
	}

}


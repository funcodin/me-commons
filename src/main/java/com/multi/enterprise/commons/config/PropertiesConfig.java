/**
 * 
 */
package com.multi.enterprise.commons.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * @author Robot
 *
 */
@Configuration
public class PropertiesConfig {

	@Bean
	public static PropertySourcesPlaceholderConfigurer properties() {
		final PropertySourcesPlaceholderConfigurer propertySourcePlaceHolder = new PropertySourcesPlaceholderConfigurer();
		propertySourcePlaceHolder.setIgnoreUnresolvablePlaceholders(true);
		return propertySourcePlaceHolder;
	}

}

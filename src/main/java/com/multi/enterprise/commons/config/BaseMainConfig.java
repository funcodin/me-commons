/**
 * 
 */
package com.multi.enterprise.commons.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.multi.enterprise.commons.bootstrap.ApplicationBootstrap;

/**
 * @author Robot
 *
 */
@Configuration
public abstract class BaseMainConfig {

	@Bean
	public ApplicationBootstrap applicationBootstrap() {
		return new ApplicationBootstrap();
	}
}

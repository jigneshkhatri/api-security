/**
 * 
 */
package com.quallit.springbootstarter.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author JIGS
 *
 */
@Configuration
@PropertySource(value = "classpath:apisecurity-application.properties")
public class AppConfig {

}

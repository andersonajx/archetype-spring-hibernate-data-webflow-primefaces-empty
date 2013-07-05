package br.com.example.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "br.com.example", scopedProxy = ScopedProxyMode.TARGET_CLASS, useDefaultFilters = false, includeFilters = {
		@ComponentScan.Filter(Controller.class), @ComponentScan.Filter(Configuration.class) })
@ImportResource("classpath*:META-INF/applicationContext-webflow.xml")
public class WebFlowConfiguration extends WebMvcConfigurerAdapter {

}
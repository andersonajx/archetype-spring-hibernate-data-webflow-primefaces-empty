package br.com.example.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;

@EnableSpringConfigured
@Configuration
@ComponentScan(basePackages = { "br.com.example" }, scopedProxy = ScopedProxyMode.TARGET_CLASS)
public class SpringConfiguration {

}

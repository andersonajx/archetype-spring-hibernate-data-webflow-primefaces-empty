package br.com.example.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource("classpath*:META-INF/applicationContext-security.xml")
public class SecurityConfiguration {

}

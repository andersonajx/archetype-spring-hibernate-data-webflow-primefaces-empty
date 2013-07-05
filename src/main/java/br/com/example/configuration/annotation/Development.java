package br.com.example.configuration.annotation;

import org.springframework.context.annotation.Profile;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Profile("development")
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Development {

}

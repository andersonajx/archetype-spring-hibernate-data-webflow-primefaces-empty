package br.com.example.configuration.hibernate;

import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;

public interface DatabaseConfiguration {

	public LocalContainerEntityManagerFactoryBean entityManagerFactory();

	public DataSource dataSource();

}
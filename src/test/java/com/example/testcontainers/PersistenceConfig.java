package com.example.testcontainers;

import com.zaxxer.hikari.HikariDataSource;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.testcontainers.containers.PostgreSQLContainer;

import javax.sql.DataSource;
import java.util.TimeZone;

@Configuration
public class PersistenceConfig {

	private static final PostgreSQLContainer POSTGRE_SQL_CONTAINER = new PostgreSQLContainer("postgres:13.4");

	static {
		POSTGRE_SQL_CONTAINER.start();
	}

	@Value(("${db.migration.locations}"))
	private String locations;

	@Bean
	public Flyway flyway() {
		Flyway flyway = Flyway.configure().dataSource(datasource()).locations(locations.split(",")).load();
		flyway.repair();
		flyway.migrate();
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		return flyway;
	}

	@Bean
	public DataSource datasource() {
		HikariDataSource dataSource = new HikariDataSource();
		dataSource.setJdbcUrl(POSTGRE_SQL_CONTAINER.getJdbcUrl());
		dataSource.setUsername(POSTGRE_SQL_CONTAINER.getUsername());
		dataSource.setPassword(POSTGRE_SQL_CONTAINER.getPassword());

		return dataSource;
	}

}

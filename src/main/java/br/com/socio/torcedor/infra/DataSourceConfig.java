package br.com.socio.torcedor.infra;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
/**
 * 
 * @author Luciano
 *
 */
@Configuration
@ComponentScan(basePackages = { "br.com.desafio.*" })
@PropertySource("classpath:config-db.properties")
public abstract class DataSourceConfig {

	
	@Bean(destroyMethod="close")
	public static DataSource dataSource(){
		return DataSourceFactory.createDataSource();
	}
	
	
	@Bean
	public static NamedParameterJdbcTemplate jdbcTemplate(){
		return new NamedParameterJdbcTemplate(dataSource());
	}
			
}

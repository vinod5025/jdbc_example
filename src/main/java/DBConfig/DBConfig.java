package DBConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
@Configuration
@ComponentScan(basePackages = {"org.techhub.org"})
public class DBConfig {
	@Bean("dataSource")
	public DriverManagerDataSource getDataSource() {
		return new DriverManagerDataSource("jdbc:mysql://localhost:3306/aud2025", "root", "AtulPatil@2002");
	}
@Bean("template")
	public JdbcTemplate getTemplate() {
		return new JdbcTemplate(this.getDataSource());
	}
}

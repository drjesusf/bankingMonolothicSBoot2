package banking.ads;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class PersistenceConfig {
	
	@Autowired
	Environment env;
	
	@Primary
    @Bean(destroyMethod = "close")
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder
				.create()
				.username(env.getProperty("DB_USERNAME"))
				.password(env.getProperty("DB_PASSWORD"))
				.url(env.getProperty("DB_URL"))
				.driverClassName(env.getProperty("DB_DRIVER"))
				.build();
	}
}

package by.kukshinov.test.app.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

@Configuration
@ComponentScan("by.kukshinov.test.app")
@EnableWebMvc
@EnableTransactionManagement
public class ConfigurationClass {

    private static final String MYSQL_DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/test_transactions";
    private static final String PASSWORD = "root";
    private static final String USERNAME = "root";
    private static final int MAX_CONNECTIONS = 8;

    @Bean(destroyMethod = "close")
    public ComboPooledDataSource getMySqlDataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(MYSQL_DRIVER);
        dataSource.setJdbcUrl(URL);
        dataSource.setPassword(PASSWORD);
        dataSource.setUser(USERNAME);
        dataSource.setMinPoolSize(MAX_CONNECTIONS);
        return dataSource;
    }

    @Bean
    public JdbcTemplate mySqlJdbcTemplate(DataSource source) {
        return new JdbcTemplate(source);
    }

    @Bean
    public DataSourceTransactionManager getTransactionManager(DataSource source) {
        return new DataSourceTransactionManager(source);
    }
}

package config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import liquibase.configuration.LiquibaseConfiguration;
import lombok.SneakyThrows;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.util.Properties;

public class LiquibaseConfig {
    public final static String CHANGELOG_FILE = "/db-migration/db.changelog-master.xml";
    private final static String PROPERTIES_FILE = "/liquibase/liquibase.properties";
    @SneakyThrows
    public  DataSource getDataSource(){
        Properties props = new Properties();
        HikariConfig hikariConfig = new HikariConfig();
        props.load((LiquibaseConfig.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE)));
        hikariConfig.setJdbcUrl(props.getProperty("url"));
        hikariConfig.setUsername(props.getProperty("username"));
        hikariConfig.setPassword(props.getProperty("password"));
        hikariConfig.setDriverClassName("org.postgresql.Driver");
        return new HikariDataSource(hikariConfig);
    };
}

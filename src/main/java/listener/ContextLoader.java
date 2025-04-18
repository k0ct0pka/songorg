package listener;

import config.HibernateConfig;
import config.LiquibaseConfig;
import dao.impls.UserDao;
import factory.UserFactory;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebListener;
import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.Scope;
import liquibase.changelog.DatabaseChangeLog;
import liquibase.configuration.LiquibaseConfiguration;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;
import lombok.AccessLevel;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import mapper.UserMapper;
import org.hibernate.SessionFactory;
import org.mapstruct.factory.Mappers;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.WebApplicationInitializer;
import service.AuthorizationService;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

@WebListener
@FieldDefaults(level = AccessLevel.PUBLIC, makeFinal = true)
public class ContextLoader implements ServletContextListener {
    public static String AUTHORIZATION_SERVICE = "authorizationService";

    @Override
    @SneakyThrows
    public void contextInitialized(ServletContextEvent sce) {
        initializeLiquibase();
        HibernateConfig hibernateConfig = new HibernateConfig();
        SessionFactory sessionFactory = hibernateConfig.createSessionFactory();
        UserDao userDao = new UserDao(sessionFactory);
        UserFactory userFactory = new UserFactory();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        UserMapper userMapper = Mappers.getMapper(UserMapper.class);
        AuthorizationService authorizationService = new AuthorizationService(userDao,userFactory,passwordEncoder,userMapper);
        Map<String,Object> objects = new HashMap<>();
        objects.put(AUTHORIZATION_SERVICE, authorizationService);
        for (Map.Entry<String, Object> entry : objects.entrySet()) {
            sce.getServletContext().setAttribute(entry.getKey(), entry.getValue());
        }

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContextListener.super.contextDestroyed(sce);
    }

    private void initializeLiquibase() throws ServletException {
        LiquibaseConfig liquibaseConfig = new LiquibaseConfig();
        DataSource dataSource = liquibaseConfig.getDataSource();
        DatabaseChangeLog databaseChangeLog = new DatabaseChangeLog(LiquibaseConfig.CHANGELOG_FILE);
        try(Connection connection = dataSource.getConnection();
            JdbcConnection jdbcConnection = new JdbcConnection(connection);
            Liquibase liquibase = new Liquibase(LiquibaseConfig.CHANGELOG_FILE,
                    new ClassLoaderResourceAccessor(),
                    jdbcConnection)){
            Contexts contexts = new Contexts();
            LabelExpression labelExpression = new LabelExpression();

            liquibase.checkLiquibaseTables(false,databaseChangeLog,
                    contexts,labelExpression);
            liquibase.update(contexts,labelExpression);
        } catch (Exception e){
            throw new ServletException(e);
        }
    }
}

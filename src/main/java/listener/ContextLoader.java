package listener;

import config.HibernateConfig;
import config.LiquibaseConfig;
import dao.impls.AuthorDao;
import dao.impls.SongDao;
import dao.impls.UserDao;
import factory.SongFactory;
import factory.UserFactory;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebListener;
import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.changelog.DatabaseChangeLog;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;
import lombok.AccessLevel;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import mapper.UserMapper;
import org.hibernate.SessionFactory;
import org.mapstruct.factory.Mappers;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import service.entityService.AuthorService;
import service.AuthorizationService;
import service.HomeService;
import service.LikeService;
import service.entityService.GenreService;
import service.entityService.SongService;
import service.entityService.UserService;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

@WebListener
@FieldDefaults(level = AccessLevel.PUBLIC, makeFinal = true)
public class ContextLoader implements ServletContextListener {
    public static final String AUTHORIZATION_SERVICE = "authorizationService";
    public static final String HOME_SERVICE = "homeService";
    public static final String USER_SERVICE = "userService";
    public static final String LIKE_SERVICE = "likeService";
    public static final String AUTHOR_SERVICE = "authorService";
    public static final String GENRE_SERVICE = "genreService";
    public static final String SONG_SERVICE = "songService";

    @Override
    @SneakyThrows
    public void contextInitialized(ServletContextEvent sce) {
        initializeLiquibase();
        HibernateConfig hibernateConfig = new HibernateConfig();
        SessionFactory sessionFactory = hibernateConfig.createSessionFactory();
        UserDao userDao = new UserDao(sessionFactory);
        UserFactory userFactory = new UserFactory();
        SongFactory songFactory = new SongFactory();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        UserMapper userMapper = Mappers.getMapper(UserMapper.class);
        SongDao songDao = new SongDao(sessionFactory);
        AuthorDao authorDao = new AuthorDao(sessionFactory);
        AuthorizationService authorizationService = new AuthorizationService(userDao,userFactory,passwordEncoder,userMapper);
        UserService userService = new UserService(userDao,userMapper);
        GenreService genreService = new GenreService(songDao);
        AuthorService authorService = new AuthorService(authorDao);
        SongService songService = new SongService(songDao,songFactory,authorService,userMapper);
        LikeService likeService = new LikeService(songService,userDao,userService);
        HomeService homeService = new HomeService(songService,authorService);


        Map<String,Object> objects = new HashMap<>();
        objects.put(AUTHORIZATION_SERVICE, authorizationService);
        objects.put(HOME_SERVICE, homeService);
        objects.put(USER_SERVICE, userService);
        objects.put(LIKE_SERVICE, likeService);
        objects.put(AUTHOR_SERVICE,authorService);
        objects.put(GENRE_SERVICE,genreService);
        objects.put(SONG_SERVICE,songService);

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

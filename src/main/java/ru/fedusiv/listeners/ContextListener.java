package ru.fedusiv.listeners;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.fedusiv.repositories.AdminsRepository;
import ru.fedusiv.repositories.AdminsRepositoryJdbc;
import ru.fedusiv.repositories.FibersRepository;
import ru.fedusiv.repositories.FibersRepositoryJdbc;
import ru.fedusiv.services.AdminsService;
import ru.fedusiv.services.AdminsServiceImpl;
import ru.fedusiv.services.FibersService;
import ru.fedusiv.services.FibersServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.TimeZone;

@WebListener
public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        TimeZone.setDefault(TimeZone.getTimeZone("GMT"));

        ServletContext context = sce.getServletContext();

        Properties properties = new Properties();
        try {
            properties.load(new FileReader("E:\\JavaLab\\fibers\\src\\main\\resources\\db.properties"));
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl         (properties.getProperty("db.url"));
        hikariConfig.setDriverClassName (properties.getProperty("db.driver"));
        hikariConfig.setUsername        (properties.getProperty("db.username"));
        hikariConfig.setPassword        (properties.getProperty("db.password"));
        hikariConfig.setMaximumPoolSize (Integer.parseInt(properties.getProperty("db.hikari.max-pool-size")));
        HikariDataSource dataSource = new HikariDataSource(hikariConfig);

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        context.setAttribute("passwordEncoder", passwordEncoder);

        FibersRepository fibersRepository = new FibersRepositoryJdbc(dataSource);
        FibersService fibersService = new FibersServiceImpl(fibersRepository);
        context.setAttribute("fibersService", fibersService);

        AdminsRepository adminsRepository = new AdminsRepositoryJdbc(dataSource);
        AdminsService adminsService = new AdminsServiceImpl(adminsRepository, passwordEncoder);
        context.setAttribute("adminsService", adminsService);

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}

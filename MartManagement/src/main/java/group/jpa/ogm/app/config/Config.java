package group.jpa.ogm.app.config;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("group.jpa.ogm.app")
public class Config {
    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("MartDB");
    @Bean
    public EntityManager entityManager(){
        return entityManagerFactory.createEntityManager();
    }
}

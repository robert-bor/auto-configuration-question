package nl._42.autoconfig;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PreDestroy;

@Configuration
@ConditionalOnProperty(prefix = "docker_42", name = "enabled", matchIfMissing = false)
@AutoConfigureBefore({LiquibaseAutoConfiguration.class })
public class Docker42AutoConfiguration {

    public Docker42AutoConfiguration() {
        System.out.println(">>> Auto-Configuring Docker 42");
    }

    @Configuration
    @EnableConfigurationProperties(Docker42Properties.class)
    public static class Docker42Configuration {

        private final Docker42Properties properties;

        public Docker42Configuration(Docker42Properties properties) {
            System.out.println(">>> Configuring Docker 42");
            this.properties = properties;
        }
    }

    @PreDestroy
    private void tearDown() {
        System.out.println(">>> Tearing down Docker 42");
    }

}

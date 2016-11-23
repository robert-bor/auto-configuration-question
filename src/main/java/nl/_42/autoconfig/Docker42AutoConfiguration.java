package nl._42.autoconfig;

import liquibase.integration.spring.SpringLiquibase;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.AllNestedConditions;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * The auto-configuration is called because it is configured in the spring.factories
 * file. This class is mentioned there under the org.springframework.boot.autoconfigure.EnableAutoConfiguration
 * key. Note that this class consists of a number of parts:
 * <ul>
 *     <li>auto configuration class; entity that is called by spring based on its entry in spring.factories</li>
 *     <li>configuration class; a wrapper to make sure the properties can be injected and eventually passed to
 *     the bean</li>
 *     <li>bean; the class that does the heavylifting, ie setting up and tearing down of the container</li>
 *     <li>post processor; class guaranteeing that every bean is dependent on this bean, ascertaining that
 *     this bean is processed first</li>
 *     <li>condition; verifies that Spring Liquibase is available and will not run if it is missing.</li>
 * </ul>
 */
@ConditionalOnProperty(prefix = "docker_42", name = "enabled", matchIfMissing = false)
@AutoConfigureAfter({LiquibaseAutoConfiguration.class })
@EnableConfigurationProperties(Docker42Properties.class)
public class Docker42AutoConfiguration {

    @Configuration
    @EnableConfigurationProperties(Docker42Properties.class)
    public static class Docker42Configuration {

        private final Docker42Properties properties;

        public Docker42Configuration(Docker42Properties properties) {
            this.properties = properties;
        }

        @Bean
        @Conditional(OnSpringLiquibaseCondition.class)
        public Docker42DatabaseBean docker42DatabaseBean() {
            return new Docker42DatabaseBean(properties);
        }

    }

    @Bean
    @Conditional(OnSpringLiquibaseCondition.class)
    public Docker42DatabaseBeanLiquibaseDependencyPostProcessor docker42DatabaseBeanLiquibaseDependencyPostProcessor() {
        return new Docker42DatabaseBeanLiquibaseDependencyPostProcessor("docker42DatabaseBean");
    }

    static class OnSpringLiquibaseCondition extends AllNestedConditions {

        OnSpringLiquibaseCondition() {
            super(ConfigurationPhase.REGISTER_BEAN);
        }

        @ConditionalOnBean(SpringLiquibase.class)
        static class HasSpringLiquibase {}

    }

}

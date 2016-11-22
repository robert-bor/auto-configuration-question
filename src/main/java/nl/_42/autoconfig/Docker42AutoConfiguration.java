package nl._42.autoconfig;

import liquibase.integration.spring.SpringLiquibase;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.AllNestedConditions;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;

@ConditionalOnProperty(prefix = "docker_42", name = "enabled", matchIfMissing = false)
@AutoConfigureAfter({LiquibaseAutoConfiguration.class })
public class Docker42AutoConfiguration {

    @Bean
    @Conditional(OnSpringLiquibaseCondition.class)
    public Docker42DatabaseBeanLiquibaseDependencyPostProcessor docker42DatabaseBeanLiquibaseDependencyPostProcessor() {
        return new Docker42DatabaseBeanLiquibaseDependencyPostProcessor("docker42DatabaseBean");
    }

    @Bean
    @Conditional(OnSpringLiquibaseCondition.class)
    public Docker42DatabaseBean docker42DatabaseBean() {
        return new Docker42DatabaseBean();
    }

    static class OnSpringLiquibaseCondition extends AllNestedConditions {

        OnSpringLiquibaseCondition() {
            super(ConfigurationPhase.REGISTER_BEAN);
        }

        @ConditionalOnBean(SpringLiquibase.class)
        static class HasSpringLiquibase {}

    }

}

package nl._42.autoconfig;

import java.util.Arrays;

import liquibase.integration.spring.SpringLiquibase;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.util.StringUtils;

/**
 * Class responsible for getting all bean factories to depend on the 'docker' bean, effectively guaranteeing
 * the 'docker' bean to run first.
 */
public class Docker42DatabaseBeanLiquibaseDependencyPostProcessor implements BeanFactoryPostProcessor {

    private final String docker42DatabaseBeanName;

    public Docker42DatabaseBeanLiquibaseDependencyPostProcessor(final String docker42DatabaseBeanName) {
        this.docker42DatabaseBeanName = docker42DatabaseBeanName;
    }

    @Override
    public void postProcessBeanFactory(final ConfigurableListableBeanFactory beanFactory) throws BeansException {
        Arrays.stream(BeanFactoryUtils.beanNamesForTypeIncludingAncestors(beanFactory, SpringLiquibase.class))
                .map(beanFactory::getBeanDefinition)
                .forEach(beanDefinition -> {
                    String[] dependsOn = beanDefinition.getDependsOn();
                    beanDefinition.setDependsOn(StringUtils.addStringToArray(dependsOn, docker42DatabaseBeanName));
                });
    }
}

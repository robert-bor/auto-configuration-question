package nl._42.autoconfig;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Responsible for the actual setting up and tearing down of the container. The setting up
 * is triggered at @PostConstruct time. The tearing down is triggered at @PreDestroy time.
 */
public class Docker42DatabaseBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(Docker42DatabaseBean.class);

    private final Docker42Properties properties;

    public Docker42DatabaseBean(Docker42Properties properties) {
        this.properties = properties;
    }

    @PostConstruct
    public void postConstruct() {
        LOGGER.info(">>> Configuring Docker 42: " + properties.getImage());
    }

    @PreDestroy
    public void preDestroy() {
        LOGGER.info(">>> Tearing down Docker 42: " + properties.getImage());
    }

}

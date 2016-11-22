package nl._42.autoconfig;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 */
public class Docker42DatabaseBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(Docker42DatabaseBean.class);

    @PostConstruct
    public void postConstruct() {
        LOGGER.info(">>> Configuring Docker 42");
    }

    @PreDestroy
    public void preDestroy() {
        LOGGER.info(">>> Tearing down Docker 42");
    }

}

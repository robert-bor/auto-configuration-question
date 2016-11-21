package nl._42.app.domain;

import nl._42.app.AbstractIntegrationTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;

public class DomainRepositoryTest extends AbstractIntegrationTest {

    @Autowired
    private DomainRepository domainRepository;

    @Test
    public void create() {
        String name = "Test Subject";
        Domain domain = new Domain();

        domain.setName(name);

        domain = domainRepository.save(domain);
        domain = domainRepository.findOne(domain.getId());

        assertEquals(name, domain.getName());
    }

}

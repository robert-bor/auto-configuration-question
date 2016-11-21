package nl._42.autoconfig;

import nl._42.autoconfig.utils.DatabaseTruncator;
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public abstract class AbstractIntegrationTest {

    @Autowired
    private DatabaseTruncator truncator;

    @After
    public void clearAll() throws Exception {
        truncator.truncate();
    }

}
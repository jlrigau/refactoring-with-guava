package fr.xebia.xke;

import org.junit.Before;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

@ContextConfiguration(locations = "classpath:/application-context-test.xml")
public abstract class AbstractTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Before
    public void globalInit() {
        executeSqlScript("insert_data.sql", false);
    }

}

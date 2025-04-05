package qa.guru.properties;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class SystemPropertiesTests {
    @Test
    void simplePropertyTest() {
        String browser = System.getProperty("browser");
        System.out.println(browser); // null
    }

    @Test
    void simpleProperty1Test() {
        System.setProperty("browser", "opera");
        String browser = System.getProperty("browser");
        System.out.println(browser); // opera
    }

    @Test
    void simpleProperty2Test() {
        String browser = System.getProperty("browser", "mozilla");
        System.out.println(browser); // mozilla
    }

    @Test
    void simpleProperty3Test() {
        System.setProperty("browser", "opera");
        String browser = System.getProperty("browser", "mozilla");
        System.out.println(browser); // opera
    }

    @Test
    @Tag("sgp")
    void simpleProperty4Test() {
        String browser = System.getProperty("browser", "mozilla");
        System.out.println(browser);

        // gradle clean one_property_test
    }
}

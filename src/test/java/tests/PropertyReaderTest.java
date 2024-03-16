package tests;

import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Test;
import utils.AppConfig;

public class PropertyReaderTest {

    @Test
    public void ownerReaderTest() {
        AppConfig appConfig = ConfigFactory.create(AppConfig.class);
        System.out.println(appConfig.url());
    }
}

package tests;

import listener.RetryListenerJunit;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import utils.AppConfig;

@Tag("UNIT")
@ExtendWith(RetryListenerJunit.class)
public class PropertyReaderTest {

    @Test
    public void ownerReaderTest() {
        AppConfig appConfig = ConfigFactory.create(AppConfig.class);
        System.out.println(appConfig.url());
    }
}

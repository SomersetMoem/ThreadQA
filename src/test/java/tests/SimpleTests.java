package tests;

import listener.RetryListener;
import model.Cat;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.IOException;

@Tag("API")
@ExtendWith(RetryListener.class)
public class SimpleTests {
    @AfterAll
    public static void saveFailed() {
        RetryListener.saveFailedTests();
    }

    private static  int age = 0;

    @Test
    public void fue() throws IOException {
        age++;
        Assertions.assertEquals(age, 6);
    }
}

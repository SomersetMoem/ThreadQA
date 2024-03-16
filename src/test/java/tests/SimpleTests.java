package tests;

import model.Cat;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class SimpleTests {
    @Test
    public void test() throws IOException {
        Cat cat = Cat.builder()
                .age(5)
                .name("Lanson")
                .build();
        System.out.println(cat);

    }
}

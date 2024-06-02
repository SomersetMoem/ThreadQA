import com.codeborne.pdftest.PDF;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

public class SelenideFileTest {
    @Test
    public void readPdfTest() throws IOException {
        File pdf = new File("src/test/resources/test.pdf");
        PDF pdfReader = new PDF(pdf);
        String pdfText = pdfReader.text;
        Assertions.assertTrue(pdfText.contains("ПУБЛИЧНАЯ ОФЕРТА"));
    }
}

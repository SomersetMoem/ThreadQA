package tests.swaggerTests;

import io.qameta.allure.Attachment;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import listener.CustomTpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import services.FileService;

import java.io.File;

import static assertions.Conditions.hasStatusCode;

public class FileTests {
    private static FileService fileService;

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "http://85.192.34.140:8080/";
        RestAssured.filters(new ResponseLoggingFilter(), new RequestLoggingFilter(),
                CustomTpl.customLogFilter().withCustomTemplate());

        fileService = new FileService();
    }

    @Test
    public void positiveDownloadTest() {
        byte[] file = fileService.downloadBaseImage()
                .asResponse()
                .asByteArray();

        attachFile(file);

        File expectedFile = new File("src/test/resources/threadqa.jpeg");

        Assertions.assertEquals(expectedFile.length(), file.length);
    }

    @Test
    public void positiveUploadTest() {
        File expectedFile = new File("src/test/resources/threadqa.jpeg");
        fileService.uploadFile(expectedFile)
                .should(hasStatusCode(200));

        byte[] actualFile = fileService.downloadLastFile().asResponse()
                .asByteArray();

        attachFile(actualFile);

        Assertions.assertTrue(actualFile.length != 0);
        Assertions.assertEquals(expectedFile.length(), actualFile.length);
    }

    @Attachment(value = "downloaded", type = "image/png")
    private byte[] attachFile(byte[] bytes) {
        return bytes;
    }
}

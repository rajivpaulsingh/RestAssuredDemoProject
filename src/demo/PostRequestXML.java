package demo;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class PostRequestXML {

    @Test
    public void postData() throws IOException {

        String postData = GenerateStringFromResource("/Users/singh2_rajiv/Automation/RestAssured/RestAssuredDemoProject/src/files/postData.xml");
        RestAssured.baseURI = "http://216.10.245.166";

        given().
                queryParam("key", "qaclick123").
                body(postData).

        when().
                post("maps/api/place/add/xml").

        then().
                assertThat().statusCode(200).and().contentType(ContentType.XML).
                body("status", equalTo("OK"));

    }

    public static String GenerateStringFromResource(String path) throws IOException {

        return new String(Files.readAllBytes(Paths.get(path)));
    }

}

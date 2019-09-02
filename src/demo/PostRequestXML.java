package demo;

import files.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
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

        Response res = given().
                queryParam("key", "qaclick123").
                body(postData).

        when().
                post("maps/api/place/add/xml").

        then().
                assertThat().statusCode(200).and().contentType(ContentType.XML).
        extract().response();

        XmlPath x = ReusableMethods.RawToXML(res);
        x.get("response.place_id").equals("f2d1136c79d6dccecd1a85663dccf6bb");
    }

    public static String GenerateStringFromResource(String path) throws IOException {

        return new String(Files.readAllBytes(Paths.get(path)));
    }

}

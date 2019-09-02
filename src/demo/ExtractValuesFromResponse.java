package demo;

import files.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ExtractValuesFromResponse {

    @Test
    public void getData() {

        //BaseURL or Host
        RestAssured.baseURI = "https://maps.googleapis.com";

        Response res = given().
                param("location", "-33.8670522,151.1957362").
                param("radius", "1500").
                param("type", "restaurant").
                param("keyword", "cruise").
                param("key", "AIzaSyDIQgAh0B4p0SdyYkyW8tlG-y0yJMfss5Y").log().all().

        when().
                get("maps/api/place/nearbysearch/json").

        then().
                assertThat().statusCode(200).and().contentType(ContentType.JSON).

        extract().response();

        JsonPath js = ReusableMethods.RawToJSON(res);
        int count = js.get("results.size()");
        System.out.println(count);

        for(int i = 0; i < count; i++) {

            System.out.println((String)js.get("results[" + i + "].name"));

        }
    }
}

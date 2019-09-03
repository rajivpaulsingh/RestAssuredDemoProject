package demo;

import files.PayLoad;
import files.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;


public class DynamicJSON {

    @Test
    public void addBook() {

        RestAssured.baseURI = "http://216.10.245.166";

        Response res = given().
                header("Content-Type", "application/json").
                body(PayLoad.addBook("erwer", "2432")).
        when().
                post("Library/Addbook.php").
        then().
                assertThat().statusCode(200).and().contentType(ContentType.JSON).
        extract().response();

        JsonPath js = ReusableMethods.RawToJSON(res);
        String id = js.get("ID");
        System.out.println(id);
    }

}

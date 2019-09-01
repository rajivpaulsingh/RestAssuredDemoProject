package demo;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class AddAndDeleteData {

    @Test
    public void AddAndDeletePlace() {

        String b = "{\n" +
                "    \"location\":{\n" +
                "        \"lat\" : -38.383494,\n" +
                "        \"lng\" : 33.427362\n" +
                "    },\n" +
                "    \"accuracy\":50,\n" +
                "    \"name\":\"Frontline house\",\n" +
                "    \"phone_number\":\"(+91) 983 893 3937\",\n" +
                "    \"address\" : \"29, side layout, cohen 09\",\n" +
                "    \"types\": [\"shoe park\",\"shop\"],\n" +
                "    \"website\" : \"http://google.com\",\n" +
                "    \"language\" : \"French-IN\"\n" +
                "}";

        //Task 1 - Grab the response
        RestAssured.baseURI = "http://216.10.245.166";

        Response res = given().
                queryParam("key", "qaclick123").
                body(b).

        when().
                post("maps/api/place/add/json").

        then().
                assertThat().statusCode(200).and().contentType(ContentType.JSON).
                body("status", equalTo("OK")).

        extract().response();

        //Convert the raw response into String
        String responseString = res.asString();
        System.out.println(responseString);

        //Task 2 - Grab the place id
        JsonPath js = new JsonPath(responseString); //Convert it to JSON format
        String placeID = js.get("place_id");
        System.out.println(placeID);

        //Task 3 - Use this placeID in the delete request
        given().
                queryParam("key", "qaclick123").
                body("{\n" +
                        "    \"place_id\":\"" + placeID + "\"         \n" +
                        "}").
        when().
                post("maps/api/place/delete/json").
        then().
                assertThat().statusCode(200).and().contentType(ContentType.JSON).
                body("status", equalTo("OK"));

    }
}

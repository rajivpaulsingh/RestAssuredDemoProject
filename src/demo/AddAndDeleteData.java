package demo;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import files.Resources;
import files.PayLoad;

public class AddAndDeleteData {

    Properties prop = new Properties();

    @BeforeTest
    public void getData() throws IOException {

        FileInputStream fis = new FileInputStream("/Users/singh2_rajiv/Automation/RestAssured/RestAssuredDemoProject/src/files/env.properties");
        prop.load(fis);
        //prop.getProperty("HOST");
    }

    @Test
    public void AddAndDeletePlace() {

        //Task 1 - Grab the response
        RestAssured.baseURI = prop.getProperty("HOST");;

        Response res = given().
                queryParam("key", prop.getProperty("KEY")).
                body(PayLoad.getPostData()).

        when().
                post(Resources.placePostData()).

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
                body(PayLoad.getDeleteData(placeID)).
        when().
                post(Resources.placeDeleteData()).
        then().
                assertThat().statusCode(200).and().contentType(ContentType.JSON).
                body("status", equalTo("OK"));

    }
}
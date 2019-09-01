package demo;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Basics {

    public static void main(String args[]) {

        /**
         *
         * BaseURI
         *
         * given()
         *      Request headers
         *      Parameters
         *      Request cookies
         *      Body
         *
         * when()
         *      get(resource)
         *      post(resource)
         *      put(resource)
         *
         * then()
         *      assertions
         *
         * extract()
         *
         */

        //BaseURL or Host
        RestAssured.baseURI = "https://maps.googleapis.com";

        given().
                param("location", "-33.8670522,151.1957362").
                param("radius", "1500").
                param("type", "restaurant").
                param("keyword", "cruise").
                param("key", "AIzaSyDIQgAh0B4p0SdyYkyW8tlG-y0yJMfss5Y").
//                header("aaa", "aaa").
//                cookie("bbb", "bbb").
//                body("ccc", "ccc")

        when().
                get("maps/api/place/nearbysearch/json").
                then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
                body("results[0].name", equalTo("Cruise Bar")).and().
                body("results[0].place_id", equalTo("ChIJi6C1MxquEmsR9-c-3O48ykI"));
    }
}

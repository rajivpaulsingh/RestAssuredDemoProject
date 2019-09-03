package demo;

import files.PayLoad;
import files.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;


public class StaticJSON {

    @Test
    public void addAndDeleteBook() throws IOException {

        RestAssured.baseURI = "http://216.10.245.166";

        //Add the book
        Response res = given().
                header("Content-Type", "application/json").
                body(GenerateStringFromResource("/Users/singh2_rajiv/Automation/RestAssured/RestAssuredDemoProject/src/files/postData.json")).
        when().
                post("Library/Addbook.php").
        then().
                assertThat().statusCode(200).and().contentType(ContentType.JSON).
        extract().response();

        JsonPath js = ReusableMethods.RawToJSON(res);
        String id = js.get("ID");
        System.out.println("Added the book: " + id);

        //Delete the book
        given().
                header("Content-Type", "application/json").
                body(PayLoad.deleteBook(id)).
        when().
                post("Library/DeleteBook.php").
        then().
                assertThat().statusCode(200).and().contentType(ContentType.JSON);
        System.out.println("Deleted the book: " + id);

    }

    public static String GenerateStringFromResource(String path) throws IOException {

        return new String(Files.readAllBytes(Paths.get(path)));
    }

}

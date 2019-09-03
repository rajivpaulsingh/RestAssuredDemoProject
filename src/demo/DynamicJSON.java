package demo;

import files.PayLoad;
import files.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;


public class DynamicJSON {

    @Test(dataProvider = "BooksData")
    public void addAndDeleteBook(String isbn, String aisle) {

        RestAssured.baseURI = "http://216.10.245.166";

        //Add the book
        Response res = given().
                header("Content-Type", "application/json").
                body(PayLoad.addBook(isbn, aisle)).
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

    @DataProvider(name = "BooksData")
    public Object[][] getData() {

        return new Object[][] {
                {"aaaaaa", "1111"},
                {"bbbbbb", "2222"},
                {"cccccc", "3333"}
        };
    }

}

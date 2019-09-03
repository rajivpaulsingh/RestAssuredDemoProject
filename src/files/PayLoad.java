package files;

public class PayLoad {

    public static String getPostData() {

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

        return b;
    }

    public static String getDeleteData(String placeID) {

        String d = "{\n" +
                "    \"place_id\":\"" + placeID + "\" \n" +
                "}";
        return d;
    }

    public static String addBook(String isbn, String aisle) {

        String book = "{\n" +
                "\"name\":\"Learn Appium Automation with Java\",\n" +
                "\"isbn\":\"" + isbn + "\",\n" +
                "\"aisle\":\"" + aisle + "\",\n" +
                "\"author\":\"John foe\"\n" +
                "}";

        return book;
    }

    public static String deleteBook(String isbnaisle) {

        String book = "{\n" +
                "\"ID\" : \"" + isbnaisle + "\"\n" +
                "}";
        return book;
    }

}

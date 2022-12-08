import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class CreateNewTodo  extends TestBase{

    private String todos = "/todos";


    String body = "{\n" +
            "        \"userId\": 9,\n" +
            "        \"id\": null,\n" +
            "        \"title\": \"I should do the homework\",\n" +
            "        \"completed\": true\n" +
            "    }";

    @Test
    public void shouldCreateNewTodo(){
        Response response =
        given()
                .body(body)
                .contentType(ContentType.JSON).
                when()
                .post(baseUrl + todos).
                then()
                .statusCode(201)
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals(jsonPath.get("userId").toString(), "9");
        Assert.assertEquals(jsonPath.get("title").toString(), "I should do the homework");
        Assert.assertEquals(jsonPath.get("completed").toString(), "true");

    }

    }


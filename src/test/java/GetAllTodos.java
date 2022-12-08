import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class GetAllTodos extends TestBase{

    private String todos = "/todos";

    @Test
    public void shouldGetAllTodos() {
        when()
                .get(baseUrl + todos).
                then()
                .statusCode(200);
    }

    @Test
    public void shouldGetFourthTodo(){
        Response response =
                given()
                        .queryParam("id", "4")
                        .when()
                        .get(baseUrl + todos)
                        .then()
                        .statusCode(200)
                        .extract().response();

        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals(jsonPath.get("[0].userId").toString(), "1");

    }

    @Test
    public void shouldGetTodosFromFirstUser(){
        given()
                .queryParam("userId", "1")
                .when()
                .get(baseUrl + todos)
                .then()
                .statusCode(200);
    }

    @Test
    public void shouldGetTodosCompletedFromFirstUser(){
        given()
                .queryParam("userId", "1")
                .queryParam("completed", "true")
                .when()
                .get(baseUrl + todos)
                .then()
                .statusCode(200);
    }

}

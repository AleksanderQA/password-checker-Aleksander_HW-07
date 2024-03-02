package Api;

import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import static io.restassured.RestAssured.*;



public class RestApiMocked {
    @BeforeAll
    public static void setup() {
        baseURI = ("http://35.208.34.242:8080");

    }

    @ParameterizedTest
    @ValueSource(ints = {1, 9, 10})
    public void getOrderByIdAndCheckResponseCodeIsOk(int orderId) {
        get("test-orders/" + orderId)
                .then()
                .log().all()
                .statusCode(200);
    }

    @DisplayName("Get order by invalid ID and check that response code 400 and had Bad request")
    @Test
    public void getOrderByInvalidIdAndCheckResponseCodeIsBadRequest() {
        given()
                .log().all()
                .when()
                .get("/test-orders/11")
                .then()
                .statusCode(400)
                .log().all();
    }

    @DisplayName("Deletion of existing valid order ID")
    @Test
    public void deletionOfExistingValidOrderId() {
        given()
                .log().all()
                .contentType(ContentType.JSON)
                .header("api_key", "1234567890123456")
                .when()
                .delete("/test-orders/10")
                .then()
                .log().all()
                .statusCode(204);

    }

    @DisplayName("Deletion of invalid order ID")
    @Test
    public void deleteInvalidOrderId() {
        given()
                .log().all()
                .when()
                .header("api_key", "1234567890123456")
                .delete("/test-orders/12")
                .then()
                .log().all()
                .statusCode(400);
    }

    @DisplayName("Deletion order with empty ID field")
    @Test
    public void deletionOrderWithEmptyIdField() {
        given()
                .log().all()
                .when()
                .header("api_key", "1234567890123456")
                .delete("/test-orders/ ")
                .then()
                .log().all()
                .statusCode(405);
    }

    @DisplayName("Deletion with wrong api key value")
    @Test
    public void deletionOfValidIdWithInvalidApiKey() {
        given()
                .log().all()
                .when()
                .header("api_key", "1234567")
                .delete("/test-orders/8")
                .then()
                .log().all()
                .statusCode(401);
    }

    @DisplayName("Deleting order with zero ID")
    @Test
    public void deletionOfOrderWithZeroId() {

        given()
                .log().all()
                .when()
                .header("api_key", "1234567890123456")
                .delete("/test-orders/0")
                .then()
                .log().all()
                .statusCode(400);
    }

    @DisplayName("Deletion order with character ID instead of digits")
    @Test
    public void deleteOrderUsingCharacterInsteadOfDigits() {
        given()
                .log().all()
                .when()
                .header("api_key", "1234567890123456")
                .delete("/test-orders/Q")
                .then()
                .log().all()
                .statusCode(400);
    }

    // HW-10
    @Tag("HW-10")
    @DisplayName("sending Get request with login and password to receive Api Key")
    @ParameterizedTest
    @CsvSource({"usr, Password",
            "User12 345678, Password002",
            "User1, Password003",
            "User, Password004",
            "Usr, Password005",
            "User006, Password006",
            "User007, Password123456789012"})
    public void requestGetWithUsernameAndPasswordToReceiveApiKey(String username, String password) {
        String responseString =
                given()
                        .queryParam("username", username)
                        .queryParam("password", password)
                        .log().all()
                        .when()
                        .get("/test-orders/")
                        .then()
                        .log().all()
                        .statusCode(HttpStatus.SC_OK)
                        .extract()
                        .path("apiKey");
        Assertions.assertTrue(responseString.length() == 16);
        Assertions.assertTrue(username.length() >= 3);
        Assertions.assertTrue(username.length() <= 13);
        Assertions.assertTrue(password.length() >= 8);
        Assertions.assertTrue(password.length() <= 20);
    }
}
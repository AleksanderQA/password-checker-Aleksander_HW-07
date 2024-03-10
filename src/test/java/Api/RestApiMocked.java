package Api;
import com.google.gson.Gson;
import dto.OrderDtoMocked;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import utils.RandomDataGenerator;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.lessThanOrEqualTo;


public class RestApiMocked {
    private  final String API_KEY = "1234567890123456";

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
                .statusCode(HttpStatus.SC_OK);
    }


    @DisplayName("Get order by invalid ID and check that response code 400 and had Bad request")
    @Test
    public void getOrderByInvalidIdAndCheckResponseCodeIsBadRequest() {
        given()
                .log().all()
                .when()
                .get("/test-orders/11")
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .log().all();
    }

    @DisplayName("Deletion of existing valid order ID gives response No content and status OK")
    @Test
    public void deletionOfExistingValidOrderIdGivesResponseNotContentStatusOK() {
        given()

                .log().all()
                .contentType(ContentType.JSON)
                .header("api_key", API_KEY)
                .when()
                .delete("/test-orders/10")
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_NO_CONTENT);

    }

    @DisplayName("Deletion of invalid order ID gives response Bad request and code 400")
    @Test
    public void deleteInvalidOrderIdGivesResponseBadRequest() {
        given()
                .log().all()
                .when()
                .header("api_key", "1234567890123456")
                .delete("/test-orders/12")
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @DisplayName("Deletion order with empty ID field gives response method not allowed")
    @Test
    public void deletionOrderWithEmptyIdFieldGivesResponseNotAllowed() {
        given()
                .log().all()
                .when()
                .header("api_key", API_KEY)
                .delete("/test-orders/ ")
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_METHOD_NOT_ALLOWED);
    }

    @DisplayName("Deletion with wrong api key value")
    @Test
    public void deletionOfValidIdWithInvalidApiKeyGivesResponseNotAuthorized() {
        given()
                .log().all()
                .when()
                .header("api_key", "1234567")
                .delete("/test-orders/8")
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_UNAUTHORIZED);
    }

    @DisplayName("Deleting order with zero I gives response Bad Request and code 400")
    @Test
    public void deletionOfOrderWithZeroIdGivesResponseBadRequest() {

        given()
                .log().all()
                .when()
                .header("api_key", API_KEY)
                .delete("/test-orders/0")
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @DisplayName("Deletion order with character ID instead of digits gives response Bad request and code 400")
    @Test
    public void deleteOrderUsingCharacterInsteadOfDigitsGivesResponseBadRequest() {
        given()
                .log().all()
                .when()
                .header("api_key", API_KEY)
                .delete("/test-orders/Q")
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
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

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    public void createOrderWithRandomDataAndCheckResponseCodeIsOk() {

        OrderDtoMocked orderDtoMocked = new OrderDtoMocked();

        orderDtoMocked.setCustomerName(RandomDataGenerator.generateRandomName());
        orderDtoMocked.setCustomerPhone(RandomDataGenerator.generateRandomPhoneNumber());
        orderDtoMocked.setComment(RandomDataGenerator.generateRandomComment());
        orderDtoMocked.setId(6);
        orderDtoMocked.setCourierId(2);
        orderDtoMocked.setStatus("OPEN");

        String response = given()
                .header("Content-Type", "application/json")
                .log().all()
                .when()
                .body(new Gson().toJson(orderDtoMocked))
                .post("/test-orders")
                .then()
                .time(lessThanOrEqualTo(1000L))
                .log().all()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .path("status");

        Assertions.assertTrue(response.contains("OPEN"));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    public void changeOrderFieldNameAndCheckResponseCodeIsOk() {
        OrderDtoMocked orderDtoMocked = new OrderDtoMocked("ACCEPTED", 3, "James", "12345678", "comment", 5);

        orderDtoMocked.setCustomerName(RandomDataGenerator.generateRandomName());
        orderDtoMocked.setCustomerPhone(RandomDataGenerator.generateRandomPhoneNumber());
        orderDtoMocked.setComment(RandomDataGenerator.generateRandomName());
        orderDtoMocked.setId(4);
        orderDtoMocked.setCourierId(8);
        orderDtoMocked.setStatus("DELIVERED");

        String response = given()
                .header("Content-Type", "application/json")
                .log().all()
                .when()
                .body(new Gson().toJson(orderDtoMocked))
                .post("/test-orders")
                .then()
                .time(lessThanOrEqualTo(1000L))
                .log().all()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .asString();

        Assertions.assertTrue(response.contains("DELIVERED"));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    public void changeOrderFieldNameByUsingMethodPutAndStatusShouldBeOk(int OrderId) {

        OrderDtoMocked orderDtoMocked = new OrderDtoMocked();

        orderDtoMocked.setCustomerName(RandomDataGenerator.generateRandomName());
        orderDtoMocked.setCustomerPhone(RandomDataGenerator.generateRandomPhoneNumber());
        orderDtoMocked.setComment(RandomDataGenerator.generateRandomComment());
        orderDtoMocked.setStatus("ACCEPTED");
        orderDtoMocked.setId(9);
        orderDtoMocked.setCourierId(4);


        given()
                .header("accept", "application/json")
                .header("api_key", API_KEY)
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .body(new Gson().toJson(orderDtoMocked))
                .put("/test-orders/{OrderId}", OrderId)
                .then()
                .time(lessThanOrEqualTo(1000L))
                .log().all()
                .statusCode(HttpStatus.SC_OK);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    public void changeOrderFieldNamesByUsingPutMethodAndStatusCodeShouldBeOk(int OrderID) {

        OrderDtoMocked orderDtoMocked = new OrderDtoMocked("DELIVERED", 5, "James", "12345678", "comment", 5);

        orderDtoMocked.setCustomerName(RandomDataGenerator.generateRandomName());
        orderDtoMocked.setCustomerPhone(RandomDataGenerator.generateRandomPhoneNumber());
        orderDtoMocked.setComment(RandomDataGenerator.generateRandomComment());
        orderDtoMocked.setId(5);
        orderDtoMocked.setStatus("ACCEPTED");
        orderDtoMocked.setCourierId(8);

        given()
                .header("accept", "application/json")
                .header("api_key", API_KEY)
                .contentType((ContentType.JSON))
                .log().all()
                .when()
                .body(new Gson().toJson(orderDtoMocked))
                .put("/test-orders/{OrderID}", OrderID)
                .then()
                .time(lessThan(1000L))
                .log().all()
                .statusCode(HttpStatus.SC_OK);
    }
}

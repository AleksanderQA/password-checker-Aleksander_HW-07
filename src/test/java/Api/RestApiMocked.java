package Api;
import com.google.gson.Gson;
import dto.OrderDto;
import dto.OrderDtoMocked;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import utils.RandomDataGenerator;
import utils.RandomObjectGenerator;

import static io.restassured.RestAssured.*;



public class RestApiMocked {
    private final String API_KEY = "1234567891234560";

    @BeforeAll
    public static void setup (){
        baseURI = ("http://35.208.34.242:8080");

    }
    @ParameterizedTest
    @ValueSource(ints = {1,9,10})
    public void getOrderByIdAndCheckResponseCodeIsOk(int orderId){
        get("test-orders/" + orderId)
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void createOrderAndCheckResponseCodeIsOk(){
        int multipleIterations = 5;
        for(int i =0; i<=multipleIterations; i++) {
            OrderDto orderDto = new OrderDto();

            orderDto.setCustomerName(RandomObjectGenerator.generateRandomCustomerName());
            orderDto.setCustomerPhone(RandomObjectGenerator.generateRandomCustomerPhone());
            orderDto.setComment(RandomObjectGenerator.generateRandomCustomerComment());
            orderDto.setStatus("ACCEPTED");
            orderDto.setId(9);
            orderDto.setCourierId(4);

            given()
                    .header("Content-Type", "application/json")
                    .log().all()
                    .when()
                    .body(new Gson().toJson(orderDto))
                    .post("/test-orders")
                    .then()
                    .log().all()
                    .statusCode(HttpStatus.SC_OK);
        }
    }
    @Test
    public void changeOrderFieldNameAndCheckResponseCodeIsOk(){
        int multipleIterations = 5;
        for(int i =0; i<=multipleIterations; i++) {

            OrderDto orderDto1 = new OrderDto("ACCEPTED", 3, "James", "12345678", "comment", 5);

            orderDto1.setCustomerName(RandomObjectGenerator.generateRandomCustomerName());
            orderDto1.setCustomerPhone(RandomObjectGenerator.generateRandomCustomerPhone());
            orderDto1.setComment(RandomObjectGenerator.generateRandomCustomerComment());
            orderDto1.setId(4);
            orderDto1.setCourierId(8);
            orderDto1.setStatus("DELIVERED");

            given()
                    .header("Content-Type", "application/json")
                    .log().all()
                    .when()
                    .body(new Gson().toJson(orderDto1))
                    .post("/test-orders")
                    .then()
                    .log().all()
                    .statusCode(HttpStatus.SC_OK);
        }
    }

    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4,5})
    public void changeOrderFieldNameByUsingMethodPutAndStatusShouldBeOk(int OrderId){

        OrderDto orderDto = new OrderDto();

        orderDto.setCustomerName(RandomObjectGenerator.generateRandomCustomerName());
        orderDto.setCustomerPhone(RandomObjectGenerator.generateRandomCustomerPhone());
        orderDto.setComment(RandomObjectGenerator.generateRandomCustomerComment());
        orderDto.setStatus("ACCEPTED");
        orderDto.setId(9);
        orderDto.setCourierId(4);

        given()
                .header("accept", "application/json")
                .header("api_key", API_KEY)
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .body(new Gson().toJson(orderDto))
                .put("/test-orders/{OrderId}", OrderId)
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK);
    }
    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4,5})
    public void changeOrderFieldNamesByUsingPutMethodAndStatusCodeShouldBeOk(int OrderID){

        OrderDto orderDto1 = new OrderDto("ACCEPTED", 3, "James", "12345678", "comment", 5);

        orderDto1.setCustomerName(RandomObjectGenerator.generateRandomCustomerName());
        orderDto1.setCustomerPhone(RandomObjectGenerator.generateRandomCustomerPhone());
        orderDto1.setComment(RandomObjectGenerator.generateRandomCustomerComment());
        orderDto1.setStatus("ACCEPTED");
        orderDto1.setId(9);
        orderDto1.setCourierId(4);

        given()
                .header("accept", "application/json")
                .header("api_key", API_KEY)
                .contentType((ContentType.JSON))
                .log().all()
                .when()
                .body(new Gson().toJson(orderDto1))
                .put("/test-orders/{OrderID}", OrderID)
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK);
    }
    // Same tests but used with OrderDtoMocked Class
    @Test
    public void createOrderAndCheckResponseCodeIsOkOrderDtoMocked(){
        int multipleIterations = 5;
        for(int i =0; i<=multipleIterations; i++) {
            OrderDtoMocked orderDtoMocked = new OrderDtoMocked();

            orderDtoMocked.setCustomerName(RandomDataGenerator.generateRandomName());
            orderDtoMocked.setCustomerPhone(RandomDataGenerator.generateRandomPhoneNumber());
            orderDtoMocked.setCustomerComment(RandomDataGenerator.generateRandomComment());

            given()
                    .header("Content-Type", "application/json")
                    .log().all()
                    .when()
                    .body(new Gson().toJson(orderDtoMocked))
                    .post("/test-orders")
                    .then()
                    .log().all()
                    .statusCode(HttpStatus.SC_OK);
        }
    }
    @Test
    public void changeOrderFieldNameAndCheckResponseCodeIsOkOrderDtoMocked(){
        int multipleIterations = 5;
        for(int i =0; i<=multipleIterations; i++) {

            OrderDtoMocked orderDtoMocked1 = new OrderDtoMocked("ACCEPTED", 3, "James", "12345678", "comment", 5);

            orderDtoMocked1.setCustomerName(RandomDataGenerator.generateRandomName());
            orderDtoMocked1.setCustomerPhone(RandomDataGenerator.generateRandomPhoneNumber());
            orderDtoMocked1.setCustomerComment(RandomDataGenerator.generateRandomName());
            orderDtoMocked1.setId(4);
            orderDtoMocked1.setCourierId(8);
            orderDtoMocked1.setStatus("DELIVERED");

            given()
                    .header("Content-Type", "application/json")
                    .log().all()
                    .when()
                    .body(new Gson().toJson(orderDtoMocked1))
                    .post("/test-orders")
                    .then()
                    .log().all()
                    .statusCode(HttpStatus.SC_OK);
        }
    }
    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4,5})
    public void changeOrderFieldNameByUsingMethodPutAndStatusShouldBeOkOrderDtoMocked(int OrderId){

        OrderDtoMocked orderDtoMocked = new OrderDtoMocked();

        orderDtoMocked.setCustomerName(RandomDataGenerator.generateRandomName());
        orderDtoMocked.setCustomerPhone(RandomDataGenerator.generateRandomPhoneNumber());
        orderDtoMocked.setCustomerComment(RandomDataGenerator.generateRandomComment());
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
                .log().all()
                .statusCode(HttpStatus.SC_OK);
    }
    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4,5})
    public void changeOrderFieldNamesByUsingPutMethodAndStatusCodeShouldBeOkOrderDtoMocked(int OrderID){
        OrderDtoMocked orderDtoMocked1 = new OrderDtoMocked("DELIVERED",5,"James", "12345678", "comment",5);

        orderDtoMocked1.setCustomerName(RandomDataGenerator.generateRandomName());
        orderDtoMocked1.setCustomerPhone(RandomDataGenerator.generateRandomPhoneNumber());
        orderDtoMocked1.setCustomerComment(RandomDataGenerator.generateRandomComment());
        orderDtoMocked1.setId(5);
        orderDtoMocked1.setStatus("DELIVERED");
        orderDtoMocked1.setCourierId(8);

        given()
                .header("accept", "application/json")
                .header("api_key", API_KEY)
                .contentType((ContentType.JSON))
                .log().all()
                .when()
                .body(new Gson().toJson(orderDtoMocked1))
                .put("/test-orders/{OrderID}", OrderID)
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK);
    }


    @DisplayName("Get order by invalid ID and check that response code 400 and had Bad request")
    @Test
    public void getOrderByInvalidIdAndCheckResponseCodeIsBadRequest (){
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
    public void deletionOfExistingValidOrderIdGivesResponseNotContentStatusOK(){
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
    public void deleteInvalidOrderIdGivesResponseBadRequest(){
        given()
                .log().all()
                .when()
                .header("api_key", API_KEY)
                .delete("/test-orders/12")
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }
    @DisplayName("Deletion order with empty ID field gives response method not allowed")
    @Test
    public void deletionOrderWithEmptyIdFieldGivesResponseNotAllowed(){
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
    public void deletionOfValidIdWithInvalidApiKeyGivesResponseNotAuthorized(){
        given()
                .log().all()
                .when()
                .header("api_key","1234567")
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
    public void deleteOrderUsingCharacterInsteadOfDigitsGivesResponseBadRequest(){
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
}
_Test Case for_ <font color = 'Orange'>_Api Testing Project_</font>
---
Object: Api [Swagger](http://35.208.34.242:8080/swagger-ui/index.html#/) method <font color = 'Lime'>*Delete*</font>
---

## Environment:
### *Windows 10, version 22H2*
### *Intellij IDEA JDK21*
### *Apache Maven 3.9.6*
### *JUint 5*

<details>
 <summary>Dependencies</summary> 

  <dependencies>

    <dependency>
      <groupId>io.rest-assured</groupId>
      <artifactId>rest-assured</artifactId>
      <version>5.4.0</version>
      <scope>test</scope>
    </dependency>

    <dependency>
      <groupId>org.junit.jupiter</groupId>
      <artifactId>junit-jupiter-engine</artifactId>
      <version>5.10.0</version>
      <scope>test</scope>
    </dependency>

    <dependency>
      <groupId>commons-codec</groupId>
      <artifactId>commons-codec</artifactId>
      <version>1.16.1</version>
    </dependency>

    <dependency>
      <groupId>org.junit.jupiter</groupId>
      <artifactId>junit-jupiter-api</artifactId>
      <version>5.10.2</version>
      <scope>test</scope>
    </dependency>
  </dependencies>


</details>

## Specifications:
### method: <font color = 'Lime'>*Delete*</font>
### Field of valid order ID's 1-10
### api_key valid length  = 16 symbols




| №   | Check name                                                                    | Expected result             | Status                             | Comment          |
|-----|-------------------------------------------------------------------------------|-----------------------------|------------------------------------|------------------|
| 1   | Get order by ID and check that response code is ok                            | Response code 200 status OK | <font color='Green'>Passed</font>  |
| 2   | Get order by invalid ID and check that response code 400 and had Bad request  | Response code 400           | <font color='Green'>Passed</font>  |
| 3   | Deletion of existing valid order ID                                           | Response code 204           | <font color='Green'>Passed</font>  |
| 4   | Deletion of invalid order ID                                                  | Response code 400           | <font color='Green'>Passed</font>  |
| 5   | Deletion order with empty ID field                                            | Response code 405           | <font color='Green'>Passed</font>  |
| 6   | Delete order using character instead of digits                                | Response code 400           | <font color='Green'>Passed</font>  |
| 7   | Deletion with wrong api key value                                             | Response code 401           | <font color='Green'>Passed</font>  |
| 8   | Deleting order with zero ID                                                   | Response code 400           | <font color='Green'>Passed</font>  |

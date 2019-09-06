package phonesystem.adrielcardoso.com.br.phonesystem.integration;

import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.junit.Before;
import org.junit.Test;
import com.jayway.restassured.RestAssured;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerControllerTests {

    @Before
    public void setBaseUri () {
        RestAssured.port = 8000;
        RestAssured.baseURI = "http://localhost"; // replace as appropriate
    }

    @Test
    public void getTestSuccess() {
        get("/api/v1/customer?page=1&size=10").then().assertThat().body("status", equalTo(200));
    }

    @Test
    public void getTestErrorParams() {
        get("/api/v1/customer?page=null").then().assertThat().body("status", equalTo(400));
    }

}

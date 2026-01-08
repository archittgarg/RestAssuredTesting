package day1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static java.lang.StrictMath.log;

public class HTTPRequests {

    private static final Logger log = LoggerFactory.getLogger(HTTPRequests.class);
    String id;

    //@Test(priority = 3, dependsOnMethods = {"updateuser"})
    void getuser() {
        given()
                .when()
                .get("http://localhost:3000/users/" + id)//https://dummy.restapiexample.com/api/v1/employees
                .then()
                .statusCode(200)
                .body("id", notNullValue())
                .log().all();
    }

    @Test(priority = 1)
    void createuser() {
        HashMap hm = new HashMap();
        hm.put("name", "vishal");
        hm.put("role", "CA");

        id = given()
                .contentType("application/json")
                .body(hm)

                .when()
                .post("http://localhost:3000/users")
                .jsonPath().getString("id");
    }

    //@Test(priority = 2, dependsOnMethods = {"createuser"})
    void updateuser() {
        HashMap hm = new HashMap();
        hm.put("name", "Silki");
        hm.put("role", "HR");

        given()
                .contentType("application/json")
                .body(hm)

                .when()
                .put("http://localhost:3000/users/" + id)

                .then()
                .statusCode(200);
        //.log().all();
    }

    //@Test()
    void deluser() {
        given()
                .when()
                .delete("http://localhost:3000/users/f24a") //+ id)
                .then()
                .statusCode(200)
                .log().all();

    }
}

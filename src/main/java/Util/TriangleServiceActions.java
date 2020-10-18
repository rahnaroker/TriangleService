package Util;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;

import java.util.List;

import static Constant.TriangleServiceBaseConstant.*;
import static io.restassured.RestAssured.given;

public class TriangleServiceActions {

    private static final Logger logger = LogManager.getLogger(TriangleServiceActions.class);

    public Response getAllTriangles() {
        logger.debug("GET all triangles");
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .baseUri(BASE_URL)
                .basePath("triangle/all")
                .header(AUTH_HEADER, AUTH_TOKEN)
                .get();
        logger.debug("Response: " + response.asString());
        return response;
    }

    public Response createNewTriangle(int firstSide, int secondSide, int thirdSide) {
        logger.debug("POST new triangle");
        JSONObject jsonBody = new JSONObject();
        jsonBody.put("separator", ";");
        jsonBody.put("input", "" + firstSide + ";" + secondSide + ";" + thirdSide + "");
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .baseUri(BASE_URL)
                .basePath("triangle")
                .header(AUTH_HEADER, AUTH_TOKEN)
                .body(jsonBody.toJSONString())
                .post();
        logger.debug("Response: " + response.asString());
        return response;
    }

    public Response deleteTriangle(String triangleId) {
        logger.debug("DELETE triangle: " + triangleId);
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .baseUri(BASE_URL)
                .basePath("triangle/" + triangleId)
                .header(AUTH_HEADER, AUTH_TOKEN)
                .delete();
        logger.debug("Response: " + response.asString());
        return response;
    }

    public int getTrianglePerimeter(String triangleId) {
        logger.debug("GET perimeter of triangle: " + triangleId);
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .baseUri(BASE_URL)
                .basePath("triangle/" + triangleId + "/perimeter")
                .header(AUTH_HEADER, AUTH_TOKEN)
                .get();
        JsonPath jsonPath = response.jsonPath();
        int perimeter = (int) jsonPath.getFloat("result");
        logger.debug("Calculated perimeter: " + response.asString());
        return perimeter;
    }

    public int getTriangleArea(String triangleId) {
        logger.debug("GET area of triangle: " + triangleId);
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .baseUri(BASE_URL)
                .basePath("triangle/" + triangleId + "/area")
                .header(AUTH_HEADER, AUTH_TOKEN)
                .get();
        JsonPath jsonPath = response.jsonPath();
        int area = (int) jsonPath.getFloat("result");
        logger.debug("Calculated area: " + response.asString());
        return area;
    }

    public void deleteAllTriangles() {
        for (String o : getTriangleIds()) {
            deleteTriangle(o);
        }
    }

    public List<String> getTriangleIds() {
        JsonPath jsonPath = getAllTriangles().jsonPath();
        return jsonPath.getList("id");
    }
}

package Test;

import org.junit.Assert;
import org.junit.Test;

import static Constant.TriangleServiceHttpStatusCodeConstant.OK_200;

public class TriangleServiceTests extends TriangleServiceTestRunner{

    @Test
    public void authSuccsess() {
        response = tsa.getAllTriangles();
        statusCode = response.getStatusCode();
        Assert.assertEquals(OK_200, statusCode);
    }

    @Test
    public void addTriangle() {
        response = tsa.createNewTriangle(FIRST_SIDE, SECOND_SIDE, THIRD_SIDE);
        statusCode = response.getStatusCode();
        Assert.assertEquals(OK_200, statusCode);
        Assert.assertEquals(1, tsa.getTriangleIds().size());
    }

    @Test
    public void deleteTriangle() {
        tsa.createNewTriangle(FIRST_SIDE, SECOND_SIDE, THIRD_SIDE);
        Assert.assertEquals(1, tsa.getTriangleIds().size());
        response = tsa.deleteTriangle(tsa.getTriangleIds().get(0));
        Assert.assertEquals(0, tsa.getTriangleIds().size());
    }

    @Test
    public void calcPerimeter() {
        tsa.createNewTriangle(FIRST_SIDE, SECOND_SIDE, THIRD_SIDE);
        countedPerimeter = tsa.getTrianglePerimeter(tsa.getTriangleIds().get(0));
        Assert.assertEquals(EXPECTED_PERIMETER, countedPerimeter);
    }

    @Test
    public void calcArea() {
        tsa.createNewTriangle(FIRST_SIDE, SECOND_SIDE, THIRD_SIDE);
        countedArea = tsa.getTriangleArea(tsa.getTriangleIds().get(0));
        Assert.assertEquals(EXPECTED_AREA, countedArea);
    }

    @Test
    public void maxLimitTriangles() {
        for (int i = 0; i < TRIANGLE_LIMIT; i++) {
            tsa.createNewTriangle(FIRST_SIDE, SECOND_SIDE, THIRD_SIDE);
        }
        Assert.assertEquals(TRIANGLE_LIMIT, tsa.getTriangleIds().size());
        response = tsa.createNewTriangle(FIRST_SIDE, SECOND_SIDE, THIRD_SIDE);
        statusCode = response.getStatusCode();
        Assert.assertNotEquals( OK_200, statusCode);
    }

    @Test
    public void unacceptableTriangleSides() {
        FIRST_SIDE = FIRST_SIDE + 100;
        response = tsa.createNewTriangle(FIRST_SIDE, SECOND_SIDE, THIRD_SIDE);
        Assert.assertNotEquals( OK_200, statusCode);
    }
}

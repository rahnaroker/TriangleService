import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import org.junit.rules.TestName;

public class TriangleServiceTests {

    private static final Logger logger = LogManager.getLogger(TriangleServiceTests.class);

    TriangleServiceActions ts = new TriangleServiceActions();
    Response response;

    int statusCode;
    int countedPerimeter;
    int countedArea;

    public static int FIRST_SIDE;
    public static int SECOND_SIDE;
    public static int THIRD_SIDE;
    public static int EXPECTED_PERIMETER;
    public static int EXPECTED_AREA;
    public static int TRIANGLE_LIMIT = 10;

    @Rule
    public TestName name = new TestName();

    @Before
    public void cleanBefore() {
        logger.debug("Running test: " + name.getMethodName());
        ts.deleteAllTriangles();
        Assert.assertEquals(0, ts.getTriangleIds().size());

        FIRST_SIDE = (int) (Math.random() * 100 + 1);
        SECOND_SIDE = FIRST_SIDE + (int) (Math.random() * 10);
        THIRD_SIDE = SECOND_SIDE + (int) (Math.random() * 10);
        EXPECTED_PERIMETER = FIRST_SIDE + SECOND_SIDE + THIRD_SIDE;
        double semiPerimeter = 0.5 * EXPECTED_PERIMETER;
        EXPECTED_AREA = (int) Math.sqrt(semiPerimeter * ((semiPerimeter - FIRST_SIDE) * (semiPerimeter - SECOND_SIDE) * (semiPerimeter - THIRD_SIDE)));
        logger.debug("Given triangle's sides: " + FIRST_SIDE + ", " + SECOND_SIDE + ", " + THIRD_SIDE);
        logger.debug("Expected perimeter: " + EXPECTED_PERIMETER);
        logger.debug("Expected area: " + EXPECTED_AREA);
    }

    @After
    public void cleanAfter() {
        logger.debug("Ending test: " + name.getMethodName());
        ts = null;
        response = null;
    }

    @Test
    public void authSuccsess() {
        response = ts.getAllTriangles();
        statusCode = response.getStatusCode();
        Assert.assertEquals(200, statusCode);
    }

    @Test
    public void addTriangle() {
        response = ts.createNewTriangle();
        statusCode = response.getStatusCode();
        Assert.assertEquals(200, statusCode);
        Assert.assertEquals(1, ts.getTriangleIds().size());
    }

    @Test
    public void deleteTriangle() {
        ts.createNewTriangle();
        Assert.assertEquals(1, ts.getTriangleIds().size());
        response = ts.deleteTriangle(ts.getTriangleIds().get(0));
        Assert.assertEquals(0, ts.getTriangleIds().size());
    }

    @Test
    public void calcPerimeter() {
        ts.createNewTriangle();
        countedPerimeter = ts.getTrianglePerimeter(ts.getTriangleIds().get(0));
        Assert.assertEquals(EXPECTED_PERIMETER, countedPerimeter);
    }

    @Test
    public void calcArea() {
        ts.createNewTriangle();
        countedArea = ts.getTriangleArea(ts.getTriangleIds().get(0));
        Assert.assertEquals(EXPECTED_AREA, countedArea);
    }

    @Test
    public void maxLimitTriangles() {
        for (int i = 0; i < TRIANGLE_LIMIT; i++) {
            ts.createNewTriangle();
        }
        Assert.assertEquals(TRIANGLE_LIMIT, ts.getTriangleIds().size());
        response = ts.createNewTriangle();
        statusCode = response.getStatusCode();
        Assert.assertNotEquals( 200, statusCode);
    }
}

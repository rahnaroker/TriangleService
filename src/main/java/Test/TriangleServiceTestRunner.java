package Test;

import Util.TriangleServiceActions;
import Util.TriangleServiceDataGenerator;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;

public class TriangleServiceTestRunner {

    private static final Logger logger = LogManager.getLogger(TriangleServiceTestRunner.class);

    TriangleServiceActions tsa = new TriangleServiceActions();
    TriangleServiceDataGenerator tsdg = new TriangleServiceDataGenerator();
    Response response;

    int statusCode;
    int countedPerimeter;
    int countedArea;

    public int FIRST_SIDE = tsdg.getFIRST_SIDE();
    public int SECOND_SIDE = tsdg.getSECOND_SIDE();
    public int THIRD_SIDE = tsdg.getTHIRD_SIDE();
    public int EXPECTED_PERIMETER = tsdg.getEXPECTED_PERIMETER();
    public int EXPECTED_AREA = tsdg.getEXPECTED_AREA();
    public static int TRIANGLE_LIMIT = 10;

    @Rule
    public TestName name = new TestName();

    @Before
    public void cleanBefore() {
        logger.debug("Running test: " + name.getMethodName());
        tsa.deleteAllTriangles();
        Assert.assertEquals(0, tsa.getTriangleIds().size());
        tsdg.dataGenerator();
    }

    @After
    public void cleanAfter() {
        logger.debug("Ending test: " + name.getMethodName());
        tsa = null;
        response = null;
    }
}

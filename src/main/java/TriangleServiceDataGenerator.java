import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TriangleServiceDataGenerator {

    private static final Logger logger = LogManager.getLogger(TriangleServiceDataGenerator.class);

    public int getFIRST_SIDE() {
        return FIRST_SIDE;
    }

    private int FIRST_SIDE;

    public int getSECOND_SIDE() {
        return SECOND_SIDE;
    }

    public int getTHIRD_SIDE() {
        return THIRD_SIDE;
    }

    public int getEXPECTED_PERIMETER() {
        return EXPECTED_PERIMETER;
    }

    public int getEXPECTED_AREA() {
        return EXPECTED_AREA;
    }

    private int SECOND_SIDE;
    private int THIRD_SIDE;
    private int EXPECTED_PERIMETER;
    private int EXPECTED_AREA;

    public void dataGenerator() {
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
}


package Constant;

import static Util.TriangleServiceConfig.getProps;

public class TriangleServiceBaseConstant {

    public static final String BASE_URL = getProps("baseURL");
    public static final String AUTH_HEADER = getProps("authHeader");
    public static final String AUTH_TOKEN = getProps("authToken");
}

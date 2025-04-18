package constants;

public class Route {
    public static final String USER = "/user";
    public static final String LOGIN = "/login";
    public static final String REGISTER = "/registration";
    public static String getJSP(String route){
        return route + ".jsp";
    }
}

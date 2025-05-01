package constants;

import java.util.List;

public class Route {
    public static final String USER = "/user";
    public static final String LOGIN = "/login";
    public static final String REGISTER = "/registration";
    public static final String HOME = "/home";
    public static final String LOGOUT = "/logout";
    public static final String LIKE_SONG = "/like-song";
    public static final String AUTHOR = "/author";
    public static final String[] ROUTES_FOR_UPDATE = {"/like-song","/home"};
    public static String getJSP(String route){
        return route + ".jsp";
    }
}

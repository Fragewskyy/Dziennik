package daos;

public class SQLmanager {
    static String url="jdbc:mysql://localhost:3306/dziennik";
    static String name="root";
    static String password="zxcv";


    public static String getUrl() {
        return url;
    }

    public static void setUrl(String url) {
        SQLmanager.url = url;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        SQLmanager.name = name;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        SQLmanager.password = password;
    }
}

package model;

public class User {
    public int userId;
    public String login;
    public String password;
    public String name;
    public String surname;
    public int roleId;

    public User(int userId, String login, String password, String name, String surname, int roleId) {
        this.userId = userId;
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.roleId = roleId;
    }

    public User(String login, String password, String name, String surname, int roleId) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.roleId = roleId;
    }
}

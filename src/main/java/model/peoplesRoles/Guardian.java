package model.peoplesRoles;

public class Guardian {
    public int guardianId;
    public String phoneNumber;
    public int userId;

    public Guardian(int guardianId, String phoneNumber, int userId) {
        this.guardianId = guardianId;
        this.phoneNumber = phoneNumber;
        this.userId = userId;
    }

    public Guardian(String phoneNumber, int userId) {
        this.phoneNumber = phoneNumber;
        this.userId = userId;
    }
}

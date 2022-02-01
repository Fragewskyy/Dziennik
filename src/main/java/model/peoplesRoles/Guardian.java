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

    public int getGuardianId() {
        return guardianId;
    }

    public void setGuardianId(int guardianId) {
        this.guardianId = guardianId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Guardian(String phoneNumber, int userId) {
        this.phoneNumber = phoneNumber;
        this.userId = userId;
    }
}

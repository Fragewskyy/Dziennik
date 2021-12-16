package controller.UserActions;

import controller.Action;

public class LogOutAction implements Action {
    @Override
    public void executeQuery() {
        System.exit(0);
    }

    @Override
    public String getlabel() {

        return "Log out";
    }
}

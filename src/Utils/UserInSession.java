package Utils;

import Users.User;

public class UserInSession {

    private static UserInSession instance;
    private static User actualUser;

    private UserInSession() {}

    public static UserInSession getInstance() {
        if (instance == null) {
            instance = new UserInSession();
        }
        return instance;
    }

    public static User getActualUser() {
        return actualUser;
    }

    public void setActualUser(User actualUser) {
        UserInSession.actualUser = actualUser;
    }

    public boolean thereIsActualUser() {
        return actualUser != null;
    }

    public void logOut() {
        actualUser = null;
        instance = null;
    }
}

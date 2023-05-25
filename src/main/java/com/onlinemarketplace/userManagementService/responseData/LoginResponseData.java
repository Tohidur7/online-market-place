package com.onlinemarketplace.userManagementService.responseData;

public class LoginResponseData {

    private String usernameOrPassword ;

    private String password ;



    public String getUsernameOrPassword() {
        return usernameOrPassword;
    }

    public void setUsernameOrPassword(String usernameOrPassword) {
        this.usernameOrPassword = usernameOrPassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

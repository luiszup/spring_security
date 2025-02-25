package br.com.zup.user_manager.user_manager.dtos;

public class UserLoginDTO {
    private String userName;
    private String password;

    public UserLoginDTO() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

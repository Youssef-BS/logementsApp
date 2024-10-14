package entities;

import java.util.Objects;

public class Users {

int id ;
private String userName ;
private String email ;
private String password ;


public Users(){

}

public Users(int id, String userName, String email, String password) {
    this.id = id;
    this.userName = userName;
    this.email = email;
    this.password = password;

}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Users users = (Users) o;
        return id == users.id && Objects.equals(userName, users.userName) && Objects.equals(email, users.email) && Objects.equals(password, users.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, email, password);
    }


}

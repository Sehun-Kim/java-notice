package com.assignment.rsupport.noticejava.domain.user;

import com.assignment.rsupport.support.domain.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class User extends AbstractEntity {
    @Column(nullable = false)
    private String userId;

    @Column(unique = true, nullable = false)
    private String userName;

    @Column(nullable = false)
    private String password;

    public User() {
        super();
    }

    public User(String userId, String userName, String password) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    // domain
    public boolean matchPassword(String password) {
        return this.password.equals(password);
    }


    @Override
    public String toString() {
        return "User{" +
                "id='" + getId() + '\'' +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", createdTime='" + getFormattedCreateDate() + '\'' +
                ", modifiedTime='" + getFormattedModifiedDate() + '\'' +
                '}';
    }
}

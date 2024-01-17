package com.example.blog.Model;

import org.springframework.web.multipart.MultipartFile;

public class CustomerForm {
    private int id;
    private String name;
    private String phone;
    private String permission;
    private MultipartFile avatar;
    private String email;
    private String password;

    public CustomerForm(int id, String name, String phone, String permission) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.permission = permission;
    }

    public CustomerForm() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public MultipartFile getAvatar() {
        return avatar;
    }

    public void setAvatar(MultipartFile avatar) {
        this.avatar = avatar;
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
}

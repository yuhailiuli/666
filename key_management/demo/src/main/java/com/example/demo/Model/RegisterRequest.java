package com.example.demo.Model;

import lombok.Data;

@Data
public class RegisterRequest {
    public String name;
    public String password;
    public String email;
    public String phone;
}

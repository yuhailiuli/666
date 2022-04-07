package com.example.demo.Model;

import lombok.Data;

@Data
public class ProfileEditRequest {
    public Long user_id;
    public String username;
    public String phone;
    public String address;
    public String gender;
    public Integer age;
    public String email;
}

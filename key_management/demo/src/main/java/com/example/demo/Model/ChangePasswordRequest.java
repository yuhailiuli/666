package com.example.demo.Model;

import lombok.Data;

@Data
public class ChangePasswordRequest {
    public Long user_id;
    public String originPassword;
    public String newPassword;
}

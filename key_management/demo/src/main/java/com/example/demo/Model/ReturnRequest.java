package com.example.demo.Model;

import lombok.Data;

import java.util.Date;

@Data
public class ReturnRequest {
    public Long key_id;
    public Long user_id;
    public Date end;
}

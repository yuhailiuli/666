package com.example.demo.Model;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Data
public class ReservationRequest {

    public Long user_id;
    public Long key_id;
    public Date start;
    public Date end;
}

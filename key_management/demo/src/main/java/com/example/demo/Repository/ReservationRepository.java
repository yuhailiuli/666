package com.example.demo.Repository;

import com.example.demo.Model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> getByKeyId(Long id);
    List<Reservation> getByUserId(Long id);
    Reservation getByUserIdAndKeyIdAndEnd(Long user_id, Long key_id, Date end);
}

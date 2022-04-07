package com.example.demo.Controller;

import com.example.demo.Model.*;
import com.example.demo.Repository.KeyRepository;
import com.example.demo.Repository.ReservationRepository;
import com.example.demo.Repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/")
public class ReservationController {
    private final UserRepository userRepository;
    private final ReservationRepository reservationRepository;
    private final KeyRepository keyRepository;

    public ReservationController(UserRepository userRepository,
                                 ReservationRepository reservationRepository,
                                 KeyRepository keyRepository) {
        this.userRepository = userRepository;
        this.reservationRepository = reservationRepository;
        this.keyRepository = keyRepository;
    }

    @PostMapping("/reservation")
    public String reservation(@RequestBody ReservationRequest reservationRequest) {
        User user = userRepository.getUserById(reservationRequest.user_id);
        Key key = keyRepository.getById(reservationRequest.key_id);
        Reservation reservation = new Reservation(reservationRequest.start, reservationRequest.end, user, key);
        reservationRepository.save(reservation);

        return "Success";
    }

    @PostMapping("/cancelReservation")
    public List<Reservation> cancelReservation(@RequestBody ReturnRequest returnRequest) {
        Reservation reservation = reservationRepository.getByUserIdAndKeyIdAndEnd(
                returnRequest.user_id,
                returnRequest.key_id,
                returnRequest.end);

        reservationRepository.delete(reservation);
        return reservationRepository.getByUserId(returnRequest.user_id);
    }

    @GetMapping("availableDate/{keyId}")
    public List<Reservation> getAvailableDate(@PathVariable Long keyId) {
        return reservationRepository.getByKeyId(keyId);
    }

    @GetMapping("userReservations/{userId}")
    public List<Reservation> userReservations(@PathVariable Long userId) {
        return reservationRepository.getByUserId(userId);
    }
}
